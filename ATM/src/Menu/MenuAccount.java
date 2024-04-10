package Menu;
import Account.AccountData;
import Account.AccountOperation;
import Exceptions.InvalidPasswordException;
import Exceptions.InvalidPinException;
import Logger.Logger;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuAccount {
    Scanner scanner = new Scanner(System.in);
    AccountOperation ao = new AccountOperation();
    public void withdrawMoney(AccountData user) {
        try{
            if(user != null){
                System.out.println("Money to withdraw: ");
                int amount = enterNumData();
                ao.withdraw(user, amount);
            }
            else{
                System.out.println("You need to login first");
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void depositMoney(AccountData user) {
        try{
            if(user != null){
                System.out.println("Money to deposit: ");
                int amount = enterNumData();
                ao.deposit(user, amount);
            }
            else{
                System.out.println("You need to login first");
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void transferMoney(AccountData sender, String accNum){
        try{
            if(sender != null && findUser(accNum)!=null){
                AccountData receiver = findUser(accNum);
                System.out.println("Enter the amount to send");
                int amount = enterNumData();
                ao.transferMoney(receiver, amount);
                System.out.println("Successfully sent money");
                System.out.println("Sender balance: " + sender.getBalance() + ", Receiver balance: " + receiver.getBalance());
            }
            else {
                System.out.println("You need to login first");
                //mainMenu();
            }
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    private AccountData findUser(String accNum){
        for(AccountData account: AccountManager.accountList){
            if(account.getAccountNumber().equals(accNum)){
                return account;
            }
        }
        return null;
    }
    public void changePin(AccountData user, String pin) throws IOException, InvalidPinException {
        if(pin.length()<4){
            Logger.log("Your pin is too short. Set pin with at least 4 characters long, but no longer than 16");
            throw new InvalidPinException("Your pin is too short. Set pin with at least 4 characters long, but no longer than 16");
        }
        if(pin.length()>16){
            Logger.log("Your pin is too short. Set pin with at least 4 characters long, but no longer than 16");
            throw new InvalidPinException("Your pin is too short. Set pin with at least 4 characters long, but no longer than 16");
        }
        user.setPinNumber(pin);
    }
    public void setPassword(AccountData user) throws InvalidPasswordException, IOException {
        System.out.println("Password should have at least 12 characters, at least 1 lower case letter, at least 1 upper " +
                "case letter, and 1 special character (@, #, $, %, ^, &, +, =, !, ?, [.], [,], - )");
        String newPassword = enterData();
        if(validatePassword(newPassword)){
            user.setPassword(newPassword);
        }
        else{
            Logger.log("Invalid password.");
            throw new InvalidPasswordException("Invalid password.");
        }
    }
    private boolean validatePassword(String password){
        // haslo powinno miec minimum 12 znaków, 1 wielką literę, 1 znak specjalny: @#$%^&+=!?.,-
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!?.,\\\\-_])(?=.{12,}$).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public int enterNumData(){
        return scanner.nextInt();
    }
    public String enterData(){
        return scanner.next();
    }
    // withdraw, deposit, transfer
}
