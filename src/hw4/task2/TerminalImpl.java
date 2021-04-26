package hw4.task2;

import java.io.IOException;

public class TerminalImpl implements Terminal {

    private final TerminalServer server;
    private final PinValidator validator;

    double timeOfLock = -100000.0;

    /**
     * Constructs Termainal object with TerminalServer
     * and PinValidator.
     *
     * @param ts TerminalServer
     *           pv PinValidator
     */
    public TerminalImpl(TerminalServer ts, PinValidator pv) {
        this.server = ts;
        this.validator = pv;
    }

    /**
     * Checks balance.
     */
    public Integer balance() {
        return server.balance();
    }

    /**
     * Checks how much time has passed since last lock.
     */
    private void checkLock() throws AccountIsLockedException {
        double tdiff = (System.currentTimeMillis() - timeOfLock) / 1000.0;
        if (tdiff < 10.0) {
            throw new AccountIsLockedException(String.format(
                    "Access error: account will be locked for %.2f seconds", 10.0 - tdiff));
        }
    }

    /**
     * Checks PIN.
     */
    private boolean checkPIN() throws Exception {

        int pin_counter = 0;
        do {
            System.out.println("Enter 4-digit pin:");
            int i = 0;
            String pin = "";
            while (i < 4) {
                Integer c = System.in.read() - 48;
                if (c >= 0 && c <= 9) {
                    pin += c.toString();
                    ++i;
                } else {
                    System.out.print("Input is not a digit. Repeat");
                    break;
                }
            }
            if (validator.check(pin))
                break;
            ++pin_counter;

        } while (pin_counter <= 3);

        if (pin_counter == 4) {
            timeOfLock = System.currentTimeMillis();//lock
            return false;
        }
        return true;
    }

    /**
     * Puts sum onto account, if possible.
     *
     * @param sum sum to put
     */
    public void put(Integer sum) throws Exception {
        checkLock();
        checkPIN();
        server.put(sum);
    }

    /**
     * Withdraws sum from account, if possible.
     *
     * @param sum sum to withdraw
     */
    public void withdraw(Integer sum) throws Exception {
        checkLock();
        checkPIN();
        server.withdraw(sum);
    }
}
