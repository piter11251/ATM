import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Account> accounts = new ArrayList<>();
    public void mainMenu() throws Exception {
        while(true){
            accounts.add(new Account("Jan", "Kowalski"));
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
                    depositMoney(loggedUser);
                    break;
                case 3:
                    System.out.print("Enter the account number of receiver: ");
                    String accNumber = scanner.next();
                    transferMoney(loggedUser, accNumber);
                    break;
                case 4:
                    changePin(loggedUser);
                    break;
                case 5:
                    changePassword(loggedUser);
                    break;
                case 6:
                    loggedUser = null;
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid option");
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
    public void withdrawMoney(Account user) {
        try{
            if(user != null){
                System.out.println("Enter the amount to withdraw: ");
                int amount = scanner.nextInt();
                user.withdraw(amount);
                System.out.println("Succesfully withdrawed money");
                System.out.println(user.getBalance());
            }
            else{
                System.out.println("You need to login first");
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void depositMoney(Account user) {
        try{
            if(user != null){
                System.out.println("Enter the amount to deposit:");
                int amount = scanner.nextInt();
                user.deposit(amount);
                System.out.println("Successfully deposited money");
                System.out.println(user.getBalance());
            }
            else{
                System.out.println("You need to login first");
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void transferMoney(Account sender, String accNum) {
        // drugi parametr to musi byc numer konta, potem trzeba sprawdzic czy taki numer istnieje w Arrayliscie i wtedy mozna dopiero kase przeslac
        try {
            if (sender != null && searchForAccount(accNum)!=null) {
                Account receiver = searchForAccount(accNum);
                System.out.println("Enter the amount to send");
                int amount = scanner.nextInt();
                sender.transferMoney(receiver, amount);
                System.out.println("Successfully sent money");
                System.out.println("Sender balance: " + sender.getBalance() + ", Receiver balance: " + receiver.getBalance());
            } else {
                System.out.println("You need to login first");
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
    private Account searchForAccount(String accountNumber){
        for(Account account: accounts){
            if(account.getAccountNumber().equals(accountNumber)){
                return account;
            }
        }
        return null;
    }
    public void changePin(Account user){
        try{
            if(user!=null){
                System.out.println("Enter new PIN");
                String newPin = scanner.next();
                user.setPinNumber(newPin);
                System.out.println("Successfully changed PIN");
            }
            else{
                System.out.println("You need to login first");
            }
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void changePassword(Account user){
        try{
            if(user!=null){
                System.out.println("Enter new password");
                String newPassword = scanner.next();
                user.setPassword(newPassword);
                System.out.println("Successfully changed password");
            }
            else{
                System.out.println("You need to login first");
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
