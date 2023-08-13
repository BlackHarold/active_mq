package ru.opi.active_mq.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
//@Builder
//@Data
public class TransactionState {
    @JsonAlias("transactionId")
    protected String transactionId;

    @JsonAlias("code")
    protected Integer code;

    @JsonAlias("State")
    protected String state;

    @JsonAlias("codeTxt")
    protected String codeTxt;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCodeTxt() {
        return codeTxt;
    }

    public void setCodeTxt(String codeTxt) {
        this.codeTxt = codeTxt;
    }

    @Override
    public String toString() {
        return "TransactionState{" +
                "transactionId='" + transactionId + '\'' +
                ", code=" + code +
                ", state='" + state + '\'' +
                ", codeTxt='" + codeTxt + '\'' +
                '}';
    }
}
