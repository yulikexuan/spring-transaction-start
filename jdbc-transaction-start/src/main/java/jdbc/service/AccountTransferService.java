//: jdbc.service.TransferService.java


package jdbc.service;


import jdbc.domain.model.Account;
import jdbc.domain.model.TransferRequestDto;
import jdbc.repository.AccountRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface AccountTransferService {

    List<Account> allAccounts();

    Account findById(int id);

    List<Account> transferMoney(TransferRequestDto transRequest);
}

@Service
@RequiredArgsConstructor
class AccountTransferServiceImpl implements AccountTransferService {

    private final AccountRepository accountRepository;

    @Override
    public List<Account> allAccounts() {
        return this.accountRepository.findAll();
    }

    @Override
    public Account findById(int id) {
        return this.accountRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Account> transferMoney(
            @NonNull final TransferRequestDto transRequest) {

        int senderAccountId = transRequest.senderAccountId();
        int receiverAccountId = transRequest.receiverAccountId();

        final var sender = accountRepository.findById(senderAccountId);
        final var receiver = accountRepository.findById(receiverAccountId);

        long senderNewAmount = sender.amount() - transRequest.amount();

        if (senderNewAmount < 0) {
            throw InsufficientFundsException.of(senderAccountId);
        }

        long receiverNewAmount = receiver.amount() + transRequest.amount();

        accountRepository.change(senderAccountId, senderNewAmount);
        accountRepository.change(receiverAccountId, receiverNewAmount);

//        if (senderAccountId == 1 && receiverAccountId == 2) {
//            throw new RuntimeException();
//        }

        return List.of(
                accountRepository.findById(senderAccountId),
                accountRepository.findById(receiverAccountId));
    }

}

///:~