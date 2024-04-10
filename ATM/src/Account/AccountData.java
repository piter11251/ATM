package Account;

import lombok.Getter;
import lombok.Setter;

@Getter
public class AccountData {
    private final String name;
    private final String lastName;
    private final String loginName;
    private final String accountNumber;
    @Setter
    private String password;
    public static int id=0;
    @Setter
    private int balance;
    @Setter
    private String pinNumber;

    public AccountData(String name, String lastName){
        id++;
        this.name = name;
        this.lastName = lastName;
        AccountGenerator accGen = new AccountGenerator(name, lastName);
        // function generating accountnumber, password, pinnumber
        this.accountNumber = accGen.generateAccountNumber();
        this.loginName = accGen.generateLogin();
        this.password = accGen.generatePassword();
        this.pinNumber = accGen.generatePinNumber();
        this.balance = 1000;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", Last Name: " + getLastName() + ", Account Number: " + getAccountNumber() +
                ", Pin Number: " + getPinNumber() + ", Login: " + getLoginName() + ", Password: " + getPassword() +
                ", balance: " + getBalance();
    }
}
