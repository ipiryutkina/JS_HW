package hw4.task2;

public class TerminalServer {

    private Integer amount = 0;

    public Integer balance() {
        return amount;
    }

    public void put(Integer sum) throws IllegalArgumentException {
        if (sum < 0 || sum % 100 > 0)
            throw new IllegalArgumentException("Operation error: " +
                    "cannot put negative or not divisible by 100 sum");
        amount += sum;
    }

    public void withdraw(Integer sum) throws IllegalArgumentException {
        if (sum < 0 || sum % 100 > 0 || sum > amount)
            throw new IllegalArgumentException("Operation error: sum is" +
                    " either: 1.not divisible by 100; 2.negative; 3.less than balance");
        amount -= sum;
    }
}
