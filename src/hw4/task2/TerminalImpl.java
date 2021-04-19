package hw4.task2;

import java.io.IOException;

public class TerminalImpl implements Terminal {

    private final TerminalServer server;
    private final PinValidator pinValidator;
    double timeOfLock = -1000.0;


    public TerminalImpl(String pin) {
        this.server = new TerminalServer();
        this.pinValidator = new PinValidator(pin);
    }

    public Integer balance() {
        return server.balance();
    }

    private void checkLock() throws AccountIsLockedException {
        double tdiff = (System.currentTimeMillis() - timeOfLock) / 1000.0;
        if (tdiff < 10.0) {
            throw new AccountIsLockedException(String.format(
                    "Ошибка доступа: аккаунт заблокирован на %.2f секунд", tdiff));
        }
    }

    private boolean checkPIN() throws IOException {

        // Scanner scan = new Scanner(System.in);

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
                    System.out.println("Это не цифра.Повторите ввод.");
                }
            }
            if (pinValidator.check(pin))
                return true;
            ++pin_counter;

        } while (pin_counter <= 3);
        //scan.close();
        if (pin_counter == 4) {
            timeOfLock = System.currentTimeMillis();//lock
            return false;
        }
        return true;
    }

    public void put(Integer sum) {
        try {
            checkLock();
            checkPIN();
            server.put(sum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void withdraw(Integer sum) {
        try {
            checkLock();
            checkPIN();
            server.withdraw(sum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
