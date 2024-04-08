import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Account implements BankAccount {
    @Getter
    private final String name;
    @Getter
    private final String lastName;
    @Getter
    private final String loginName;
    @Getter
    private final String accountNumber;
    @Getter
    @Setter
    private String password;
    public static int id=0;
    @Getter
    @Setter
    private int balance;
    @Getter
    private String pinNumber;

    public Account(String name, String lastName) {
        id++;
        this.name = name;
        this.lastName = lastName;
        // function generating accountnumber, password, pinnumber
        this.accountNumber=generateAccountNumber();
        this.pinNumber = generatePinNumber();
        this.loginName = generateLogin();
        this.password = generatePassword();
        this.balance = 1000;
    }

    @Override
    public void deposit(int amount) throws Exception {
        if(amount > 0){
            this.balance += amount;
        }
        else{
            throw new Exception("You cannot deposit non positive amount of cash");
        }
    }

    @Override
    public void withdraw(int amount) throws InvalidAmountException {
        if(amount > balance){
            Logger.log("You cannot withdraw more money than you have in your account!");
            throw new InvalidAmountException("You cannot withdraw more money than you have in your account!");
        }
        this.balance -= amount;
    }

    private String generateAccountNumber(){
        String prefix = "8290904512";
        //tring controllSum = "82", routingNum = "90904512";
        StringBuilder accNum= new StringBuilder();
        Random random = new Random();
        for(int i=0;i<16;i++){
            int num = random.nextInt(10);
            accNum.append(num);
        }
        String fullAccNum = prefix + accNum;
        return fullAccNum;
    }

    private String generatePinNumber(){
        Random random = new Random();
        String pin = "";
        for(int i=0;i<4;i++){
            int num = random.nextInt(10);
            pin += num;
        }
        return pin;
    }

    private String generateLogin(){
        String login = name.substring(0,2) + lastName.substring(0,2);
        for(int i=0;i<2;i++){
            Random random = new Random(10);
            login += random.nextInt();
        }
        return login;
    }

    private String generatePassword(){
        String pattern = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#$%^&+=!?.,-_"; // 76 signs  @#$%^&+=!?.,-
        int lenPassword=24;
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<lenPassword;i++){
            int randomNum = random.nextInt(75);
            password.append(pattern.charAt(randomNum));
        }
        return password.toString();
    }
    public void setPassword() throws InvalidPasswordException {
        System.out.println("Password should have at least 12 characters, at least 1 lower case letter, at least 1 upper " +
                "case letter, and 1 special character (@, #, $, %, ^, &, +, =, !, ?, [.], [,], - )");
        Scanner scanner = new Scanner(System.in);
        String newPassword = scanner.next();
        if(validatePassword(newPassword)){
            this.password = newPassword;
        }
        else{
            throw new InvalidPasswordException("Invalid password.");
        }
    }
    public void setPinNumber(String number) throws InvalidPinException {
        if(number.length()<4){
            throw new InvalidPinException("Your pin is too short. Set pin with at least 4 characters long, but no longer than 16");
        }
        if(number.length()>16){
            throw new InvalidPinException("Your pin is too short. Set pin with at least 4 characters long, but no longer than 16");
        }
        this.pinNumber = number;
    }
    private boolean validatePassword(String password){
        // haslo powinno miec minimum 12 znaków, 1 wielką literę, 1 znak specjalny: @#$%^&+=!?.,-
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!?.,\\\\-_])(?=.{12,}$).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public boolean transferMoney(Account account, int cash) throws Exception {
        if(getBalance() <= cash){
            return false;
        }
        withdraw(cash);
        account.deposit(cash);
        return true;
    }
}

