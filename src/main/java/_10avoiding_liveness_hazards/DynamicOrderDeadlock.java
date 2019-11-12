package _10avoiding_liveness_hazards;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/8
 */
public class DynamicOrderDeadlock {
    public static void transferMoney(Account fromAccount, Account toAccount, DollarAmount amount) throws InsufficientFundsException {
        synchronized (fromAccount) {
            synchronized (toAccount) {
                if (fromAccount.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientFundsException();
                } else {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        }
    }

   public static class Account {
        private DollarAmount balance;
        private final int acctNo;
        private static final AtomicInteger sequence = new AtomicInteger();

        public Account() {
            balance = new DollarAmount(1000);
            acctNo = sequence.incrementAndGet();
        }

        void debit(DollarAmount d) {
            balance = balance.subtract(d);
        }

        void credit(DollarAmount d) {
            balance = balance.add(d);
        }
        DollarAmount getBalance() {
            return balance;
        }

        int getAcctNo() {
            return acctNo;
        }
    }

    public static class DollarAmount implements Comparable<DollarAmount>{
        private long dollar;
        public DollarAmount(int amount) {
            dollar = amount;
        }

        public DollarAmount add(DollarAmount d) {
            dollar += d.dollar;
            return this;
        }

        public DollarAmount subtract(DollarAmount d) {
            dollar -= d.dollar;
            return this;
        }

        @Override
        public int compareTo(DollarAmount o) {
            return 0;
        }


    }
    static class InsufficientFundsException extends Exception {
    }
}
