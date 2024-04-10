package Menu;

import Account.AccountData;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {
    public Scanner scanner = new Scanner(System.in);
    public static ArrayList<AccountData> accountList;
    static{
        accountList = new ArrayList<>();
    }
    public void addUserToList(AccountData user){
        accountList.add(user);
    }
    public AccountData loginToAccount(){
        System.out.println("Enter login: ");
        String login = scanner.next();
        System.out.println("Enter password: ");
        String password = scanner.next();
        return validateAndLoginToAccount(login, password);
    }
    private AccountData validateAndLoginToAccount(String login, String password){
        for(AccountData account: accountList){
            if(account.getLoginName().equals(login) && account.getPassword().equals(password)){
                return account;
            }
        }
        return null;
    }
}
