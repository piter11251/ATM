package Account;

import Exceptions.InvalidAmountException;
import Logger.Logger;

public class AccountOperation implements BankAccount1 {
    @Override
    public void withdraw(AccountData user, int amount) throws Exception {
        if(amount > user.getBalance()){
            Logger.log("You cannot withdraw more money than you have in your account!");
            throw new InvalidAmountException("You cannot withdraw more money than you have in your account!");
        }
        user.setBalance(user.getBalance()-amount);
    }

    @Override
    public void deposit(AccountData user, int amount) throws Exception {
        if(amount > 0){
            user.setBalance(user.getBalance()+amount);
        }
        else{
            Logger.log("You cannot deposit non positive amount of cash");
            throw new Exception("You cannot deposit non positive amount of cash");
        }
    }
    public void transferMoney(AccountData account, int cash) throws Exception {
        withdraw(account, cash);
        deposit(account, cash);
    }
}
