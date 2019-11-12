package _10avoiding_liveness_hazards;


import _10avoiding_liveness_hazards.DynamicOrderDeadlock.Account;
import _10avoiding_liveness_hazards.DynamicOrderDeadlock.DollarAmount;
import _10avoiding_liveness_hazards.DynamicOrderDeadlock.InsufficientFundsException;

import java.util.Random;

import static _10avoiding_liveness_hazards.InduceLockOrder.*;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/8
 */
public class DemonstrateDeadlock {
    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATIONS = 1000;

    public static void main(String[] args) {
        final Random rnd = new Random();
        final Account[] accounts = new Account[NUM_ACCOUNTS];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account();
        }
        class TransferThread extends Thread {
            @Override
            public void run() {
                int fromAcct, toAcct;
                DollarAmount amount;
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    toAcct = rnd.nextInt(NUM_ACCOUNTS);
                    amount = new DollarAmount(rnd.nextInt(1000));
                  /*  System.out.println(Thread.currentThread().getName() + "fromAcct: " + fromAcct + "  toAcct: " + toAcct);*/
                    try {
                        transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                    } catch (InsufficientFundsException ignored) {

                    }
                }
            }
        }
        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }

    }
}
