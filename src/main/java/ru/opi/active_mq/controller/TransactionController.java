package ru.opi.active_mq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.opi.active_mq.TransactionRepository;
import ru.opi.active_mq.entity.TransactionState;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;


    @GetMapping("transactions")
    public ResponseEntity<TransactionState> getTransactions(@RequestParam("transaction_id") String transactionId) {
        TransactionState transactionState = transactionRepository.findTransactionStateByTransactionId(transactionId);

        if (transactionState != null) {
            return new ResponseEntity<>(transactionState, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("transactions")
    public ResponseEntity<TransactionState> createTransaction(@RequestBody TransactionState transactionState) {
        try {
            TransactionState _transactionState = transactionRepository.save(
                    new TransactionState(transactionState.getTransactionId(), transactionState.getSuidId())
            );

            return new ResponseEntity<>(_transactionState, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
