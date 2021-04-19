package hw4.task2;

public class TerminalServer {

    private Integer amount = 0;

    public Integer balance() {
        return amount;
    }

    public void put(Integer sum) throws IllegalArgumentException {
        if (sum < 0 || sum % 100 > 0)
            throw new IllegalArgumentException("Ошибка операции: невозможно положить данную сумму");
        amount += sum;
    }

    public void withdraw(Integer sum) throws IllegalArgumentException {
        if (sum < 0 || sum % 100 > 0 || sum > amount)
            throw new IllegalArgumentException("Ошибка операции: невозможно снять данную сумму");
        amount -= sum;
    }
}
