package Account;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class AccountGenerator {
    private final String name;
    private final String lastName;
    public AccountGenerator(String name, String lastName){
        this.name = name;
        this.lastName = lastName;
    }
    public String generateAccountNumber(){
        String prefix = "8290904512";
        StringBuilder accNum= new StringBuilder();
        Random random = new Random();
        for(int i=0;i<16;i++){
            int num = random.nextInt(10);
            accNum.append(num);
        }
        return prefix+accNum;
    }
    public String generatePinNumber(){
        Random random = new Random();
        String pin = "";
        for(int i=0;i<4;i++){
            int num = random.nextInt(10);
            pin += num;
        }
        return pin;
    }
    public String generateLogin(){
        String login = getName().substring(0,3).toLowerCase() + getLastName().substring(0,3).toLowerCase();
        for(int i=0;i<3;i++){
            Random random = new Random();
            login += random.nextInt(10);
        }
        return login;
    }

    public String generatePassword(){
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

}
