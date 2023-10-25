package ru.opi.active_mq.entity;


import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

@Entity
@Table(name = "transaction")
public class TransactionState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "transaction_id")
    @JsonAlias("transaction_id")
    private String transactionId;

    @Column(name = "suid_id")
    @JsonAlias("suid_id")
    private String suidId;

    public TransactionState() {
    }

    public TransactionState(String transactionId, String suidId) {
        this.transactionId=transactionId;
        this.suidId=suidId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getSuidId() {
        return suidId;
    }

    public void setSuidId(String suidId) {
        this.suidId = suidId;
    }
}
