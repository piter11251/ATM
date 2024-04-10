package Account;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Exceptions.InvalidPinException;
import Logger.Logger;
import lombok.Getter;

@Getter
public class AccountValidation {
    private String pinNumber;
    private boolean validatePassword(String password){
        // haslo powinno miec minimum 12 znaków, 1 wielką literę, 1 znak specjalny: @#$%^&+=!?.,-
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!?.,\\\\-_])(?=.{12,}$).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public void setPinNumber(String number) throws InvalidPinException, IOException {
        if(number.length()<4){
            Logger.log("Your pin is too short. Set pin with at least 4 characters long, but no longer than 16");
            throw new InvalidPinException("Your pin is too short. Set pin with at least 4 characters long, but no longer than 16");
        }
        if(number.length()>16){
            Logger.log("Your pin is too long. Set pin with at least 4 characters long, but no longer than 16");
            throw new InvalidPinException("Your pin is too long. Set pin with at least 4 characters long, but no longer than 16");
        }
        this.pinNumber = number;
    }
}
