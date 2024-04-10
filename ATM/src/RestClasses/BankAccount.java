package RestClasses;

public interface BankAccount {
    void deposit(int amount) throws Exception;
    void withdraw(int amount) throws Exception;
}
