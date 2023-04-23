package com.company.logic;

import com.company.abstractions.IBetApplicator;
import com.company.abstractions.IGamePlayerFactory;
import com.company.abstractions.IReadOnlyPlayingResult;
import com.company.abstractions.IRepository;
import com.company.models.casino.Bet;
import com.company.models.casino.BetStatus;
import com.company.models.casino.PlayingResult;
import com.company.storage.jpa.IUserRepository;
import com.company.storage.models.StorageBet;
import com.company.storage.models.StorageGameOutcome;
import com.company.storage.models.StorageOutcome;
import com.company.storage.models.StorageUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
@Transactional
public class BetApplicator implements IBetApplicator {
    @Autowired
    private IRepository<StorageBet, Long> betRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRepository<StorageOutcome, Long> outcomeRepository;
    @Autowired
    private IGamePlayerFactory gamePlayerFactory;

    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.REPEATABLE_READ)
    @Override
    public IReadOnlyPlayingResult applyBet(Bet bet) {
        var user = userRepository.findByUserLogin(bet.getUserLogin());
        var outcome = outcomeRepository.getById(bet.getOutcomeId());

        var storageBet = new StorageBet(
                user,
                outcome,
                bet.getPrice(),
                bet.getStatus());

        betRepository.add(storageBet);

        return getPlayingResult(storageBet);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private PlayingResult getPlayingResult(StorageBet bet){
        var resultGameOutcome = getResultGame(bet);

        var outcome = bet.getOutcome();
        var isWin = betIsWin(outcome, resultGameOutcome);

        bet.setBetStatus(isWin
                ? BetStatus.Win
                : BetStatus.Loss);
        betRepository.update(bet);

        return new PlayingResult(
                isWin,
                outcome.getKoef(),
                bet.getId(),
                resultGameOutcome.getView());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    private StorageGameOutcome getResultGame(StorageBet bet){
        try{
            var player = gamePlayerFactory.createGamePlayer(bet.getUser(), bet.getOutcome());

            return player.playGame(bet.getOutcome()
                    .getLot()
                    .getGameOutcomes().stream()
                    .toList());
        }catch (RuntimeException ex){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw ex;
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    private boolean betIsWin(StorageOutcome outcome, StorageGameOutcome result){
        return outcome.getRelatedGameOutcomes().contains(result);
    }
}
