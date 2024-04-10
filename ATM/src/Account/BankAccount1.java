package Account;
import Account.AccountData;
public interface BankAccount1 {
    void deposit(AccountData user, int amount) throws Exception;
    void withdraw(AccountData user, int amount) throws Exception;
}
