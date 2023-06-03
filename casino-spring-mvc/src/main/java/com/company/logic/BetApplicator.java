package com.company.logic;

import com.company.abstractions.*;
import com.company.models.account.User;
import com.company.models.casino.Bet;
import com.company.models.casino.BetStatus;
import com.company.models.casino.PlayingResult;
import com.company.storage.models.StorageBet;
import com.company.storage.models.StorageGameOutcome;
import com.company.storage.models.StorageOutcome;
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
    private IUserService userService;
    @Autowired
    private IRepository<StorageOutcome, Long> outcomeRepository;
    @Autowired
    private IGamePlayerFactory gamePlayerFactory;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public IReadOnlyPlayingResult applyBet(Bet bet) {
        var user = userService.findByLogin(bet.getUserLogin());

        if (user.isEmpty())
        {
            return null;
        }


        if (user.get().getBalance().compareTo(bet.getPrice()) == -1)
        {
            return null;
        }

        var outcome = outcomeRepository.getById(bet.getOutcomeId());

        var storageBet = new StorageBet(
                User.ToStorage(user.get()),
                outcome,
                bet.getPrice(),
                bet.getStatus());

        betRepository.add(storageBet);

        userService.ChangeUserBalance(user.get().getLogin() ,bet.getPrice().negate());

        var result = getPlayingResult(storageBet);

        if (result.isWin())
        {
            var winSum = bet.getPrice().multiply(result.getWinPrice());
            userService.ChangeUserBalance(user.get().getLogin(), winSum);
        }

        userService.UpdateAuthorizeUserData(userService.findByLogin(bet.getUserLogin()).get());

        return result;
    }

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

    private boolean betIsWin(StorageOutcome outcome, StorageGameOutcome result){
        return outcome.getRelatedGameOutcomes().contains(result);
    }
}
