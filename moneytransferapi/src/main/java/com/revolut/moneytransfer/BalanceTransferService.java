package com.revolut.io;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.StampedLock;

//
class Account {
    private AtomicReference<BigDecimal> amount;
    private String accountId;

    // take out
    public void withdraw(BigDecimal amount) {
        this.amount.updateAndGet(a -> {
            if(a.compareTo(amount) < 0) {
                throw new IllegalArgumentException("Not sufficent balance");
            }
            return a.subtract(amount);
        });
    }

    // put in
    public void deposit(BigDecimal amount) {
        this.amount.updateAndGet(a -> {
            if(a.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Invalid Value");
            }
            return a.add(amount);
        });
    }

//    public BigDecimal getAmount() {
//        return amount;
//    }

//    public void setAmount(BigDecimal amount) {
//        this.amount = amount;
//    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}

public class BalanceTransferService {
    private final StampedLock lock;
    public BalanceTransferService() {
        this.lock = new StampedLock();
    }

    public void transfer(Account from, Account to, BigDecimal amount) {
        // empty skeleton, fill in
            from.withdraw(amount);
            to.deposit(amount);

            /*
                accountId, balance
            *
            * Read commited (*snapshot isolation)
            *
            * t1 , t2
            * from "Aqib", 120
            * to "Maciej", 400
            *
            * select balance from account where accountId = ''
            *
            * update into account(accountId, balance)  Values ('', )
            *
            *
            **/

    }
}
