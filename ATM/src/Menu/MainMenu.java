package Menu;

import RestClasses.Account;

import java.util.Scanner;
import Account.AccountData;

public class MainMenu {
    Scanner scanner = new Scanner(System.in);
    public void preLoginPanel() throws Exception {
        boolean end = false;
        while(!end){
            System.out.println("ATM SIMULATOR");
            System.out.println("-----------------------------");
            System.out.println("Choose one from options");
            System.out.println("1. Create an account");
            System.out.println("2. Login to account");
            System.out.println("3. Exit");
            System.out.println("--------------------------------");
            System.out.print("> ");
            int choice = pressChoice();
            AccountManager manager = new AccountManager();
            switch (choice){
                case 1:
                    System.out.print("Enter name: ");
                    String name = enterData();
                    System.out.print("Enter last name: ");
                    String lastName = enterData();
                    AccountData newUser = new AccountData(name, lastName);
                    System.out.println(newUser);
                    manager.addUserToList(newUser);
                    break;
                case 2:
                    AccountData user = manager.loginToAccount();
                    if(user != null){
                        postLoginPanel(user);
                    }
                    break;
                case 3:
                    end = true;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
    public void postLoginPanel(AccountData user) throws Exception {
        boolean end1 = false;
        MenuAccount menuAccount = new MenuAccount();
        while(!end1) {
            System.out.println("-----------------------------");
            System.out.println("Choose one from options");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Transfer money");
            System.out.println("4. Change PIN");
            System.out.println("5. Change password");
            System.out.println("6. Display info about account");
            System.out.println("7. Logout");
            System.out.println("--------------------------------");
            System.out.print("> ");

            int choice = pressChoice();
            switch (choice){
                case 1:
                    menuAccount.withdrawMoney(user);
                    break;
                case 2:
                    menuAccount.depositMoney(user);
                    break;
                case 3:
                    System.out.print("Enter the account number of receiver: ");
                    String accNumber = enterData();
                    menuAccount.transferMoney(user, accNumber);
                    break;
                case 4:
                    System.out.println("Enter new PIN: ");
                    String newPin = enterData();
                    menuAccount.changePin(user, newPin);
                    break;
                case 5:
                    menuAccount.setPassword(user);
                    break;
                case 6:
                    System.out.println(user);
                    break;
                case 7:
                    user = null;
                    end1 = true;
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }

    }

    public int pressChoice(){
        return scanner.nextInt();
    }
    public String enterData(){
        return scanner.next();
    }
}
