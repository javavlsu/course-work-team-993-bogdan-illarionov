package com.company.logic;

import com.company.abstractions.*;
import com.company.abstractions.storage.IRepository;
import com.company.abstractions.storage.IUserRepository;
import com.company.models.account.User;
import com.company.models.casino.Bet;
import com.company.models.casino.BetStatus;
import com.company.models.casino.PlayingResult;
import com.company.models.enums.BonusTriggerAction;
import com.company.storage.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Objects;

@Service
@Transactional
public class BetApplicator implements IBetApplicator {
    @Autowired
    private IRepository<StorageBet, Long> betRepository;
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRepository<StorageOutcome, Long> outcomeRepository;
    @Autowired
    private IGamePlayerFactory gamePlayerFactory;

    @Autowired
    private IRepository<StorageLot, Long> lotRepository;

    @Autowired
    private  IBonusService bonusService;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public IReadOnlyPlayingResult applyBet(Bet bet) {
        var user = userRepository.getByLogin(bet.getUserLogin());

        if (user.isEmpty()) {
            return null;
        }

        if (user.get().getBalance().compareTo(bet.getPrice()) < 0) {
            return null;
        }

        var outcome = outcomeRepository.getById(bet.getOutcomeId());

        if (outcome.isEmpty()) {
            return null;
        }

        var storageBet = new StorageBet(
                User.ToStorage(user.get()),
                outcome.get(),
                bet.getPrice(),
                bet.getStatus());

        betRepository.add(storageBet);

        userService.changeUserBalance(user.get().getLogin() ,bet.getPrice().negate());

        var result = getPlayingResult(storageBet);

        if (result.isWin()) {
            var winSum = bet.getPrice().multiply(result.getWinPrice());
            userService.changeUserBalance(user.get().getLogin(), winSum);
        }

        //todo
        //bonusService.expireBonus(user.get(), BonusTriggerAction.LotPlay);

        userService.updateAuthorizeUserData(userRepository.getByLogin(bet.getUserLogin()).get());

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

            var user = StorageUser.toModel(bet.getUser());

            var result = bonusService.triggerBonuses(user, BonusTriggerAction.LotPlay);

            var increase = false;

            if (result.isPresent()) {
                increase = result.get().getLotsList().stream().anyMatch(x -> Objects.equals(x, bet.getOutcome().getLot().getId()));
            }

            if (increase) {
                user.setIncreased(true);
            }

            var player = gamePlayerFactory.createGamePlayer(user, bet.getOutcome());


            var outcomes = bet.getOutcome().getLot()
                    .getGameOutcomes()
                    .stream()
                    .toList();

            return player.playGame(outcomes);
        }catch (RuntimeException ex){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw ex;
        }
    }

    private boolean betIsWin(StorageOutcome outcome, StorageGameOutcome result){
        return outcome.getRelatedGameOutcomes().contains(result);
    }
}
