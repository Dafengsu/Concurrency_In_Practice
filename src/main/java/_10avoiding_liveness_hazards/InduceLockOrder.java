package _10avoiding_liveness_hazards;

import _10avoiding_liveness_hazards.DynamicOrderDeadlock.DollarAmount;

import static _10avoiding_liveness_hazards.DynamicOrderDeadlock.Account;
import static _10avoiding_liveness_hazards.DynamicOrderDeadlock.InsufficientFundsException;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/8
 */
public class InduceLockOrder {
    private static final Object tieLock = new Object();
    public static void  transferMoney(final Account fromAcct,
                              final Account toAcct,
                              final DollarAmount amount)
            throws InsufficientFundsException {

        class Helper {
            public void transfer() throws InsufficientFundsException {
                if (fromAcct.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientFundsException();
                } else {
                    fromAcct.debit(amount);
                    toAcct.credit(amount);
                }
            }
        }

        int formHash = System.identityHashCode(fromAcct);
        int toHash = System.identityHashCode(toAcct);
        if (formHash < toHash) {
            synchronized (fromAcct) {
                synchronized (toAcct) {
                    new Helper().transfer();
                }
            }
        } else if (formHash > toHash) {
            synchronized (toAcct) {
                synchronized (fromAcct) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                System.out.println("相同");
                synchronized (fromAcct) {
                    synchronized (toAcct) {
                        new Helper().transfer();

                    }
                }
            }
        }

    }

  /*  interface DollarAmount extends Comparable<DollarAmount> {
    }
    interface Account {
        void debit(DollarAmount d);

        void credit(DollarAmount d);

        DollarAmount getBalance();

        int getAcctNo();
    }*/

   /* static class InsufficientFundsException extends Exception {
    }*/
}
