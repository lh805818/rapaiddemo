package com.company.project.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by qince on 2015/4/9.
 */
@Entity
@Table(name="t_accountinfo")
public class AccountInfo implements Serializable {
    @Id
    @Column(name = "ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;
    @Column(name = "BALANCE")
    private Double balance;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                '}';
    }
}
