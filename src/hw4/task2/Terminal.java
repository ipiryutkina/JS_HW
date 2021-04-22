package hw4.task2;

public interface Terminal {

    /**
     * Checks balance.
     */
    public Integer balance();

    /**
     * Puts sum onto account, if possible.
     *
     * @param sum sum to put
     */
    public void put(Integer sum) throws Exception;

    /**
     * Withdraws sum from account, if possible.
     *
     * @param sum sum to withdraw
     */
    public void withdraw(Integer sum) throws Exception;
}
