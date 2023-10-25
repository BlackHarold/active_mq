package ru.opi.active_mq;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.opi.active_mq.entity.TransactionState;

public interface TransactionRepository extends JpaRepository<TransactionState, Long> {

    TransactionState findTransactionStateByTransactionId(String transactionId);
}
