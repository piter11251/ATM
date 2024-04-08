import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Account> accounts = new ArrayList<>();
    public void mainMenu() throws Exception {
        while(true){
            System.out.println("ATM SIMULATOR");
            System.out.println("-----------------------------");
            System.out.println("Choose one from options");
            System.out.println("1. Create an account");
            System.out.println("2. Login to account");
            System.out.println("3. Exit");
            System.out.println("--------------------------------");
            System.out.print("> ");
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    createAnAccount();
                    break;
                case 2:
                    loginToAccount();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    public void createAnAccount(){
        System.out.println("Enter your name: ");
        String name = scanner.next();
        System.out.println("Enter your last name: ");
        String lastName = scanner.next();

        Account acc = new Account(name, lastName);
        accounts.add(acc);
        System.out.println(acc.toString());
    }

    public void loginToAccount() throws Exception {
        System.out.println("Enter your login");
        String login = scanner.next();
        System.out.println("Enter your password");
        String password = scanner.next();
        Account loggedUser = validateAndLoginToAccount(login, password);
        if(loggedUser==null){
            throw new Exception("Invalid login or password");
        }
        while(true){
            System.out.println("-----------------------------");
            System.out.println("Choose one from options");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Transfer money");
            System.out.println("4. Change PIN");
            System.out.println("5. Change password");
            System.out.println("6. Logout");
            System.out.println("--------------------------------");
            System.out.print("> ");

            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    withdrawMoney(loggedUser);
                    break;
                case 2:
                    // deposit function
                case 3:
                    // transfer money function
                case 4:
                    // change pin function
                case 5:
                    // change password function
                case 6:
                    break;
                default:
                    break;
            }
        }

    }
    private Account validateAndLoginToAccount(String login, String password){
        for(Account account: accounts){
            if(account.getLoginName().equals(login) && account.getPassword().equals(password)){
                return account;
            }
        }
        return null;
    }
    public void withdrawMoney(Account user) throws InvalidAmountException, IOException {
        System.out.println("Enter the amount to withdraw: ");
        int amount = scanner.nextInt();
        user.withdraw(amount);
        System.out.println("Succesfully withdrawed money");
        System.out.println(user.getBalance());
    }
}
