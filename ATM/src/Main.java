public class Main {
    public static void main(String[] args) throws Exception {
        Account ac = new Account("Piotr", "Kozlowski");
        Account ac1 = new Account("Jan", "Kowalski");
        System.out.println(ac.getPassword());
        System.out.println(ac.getBalance());
        ac.deposit(1500);
        ac1.deposit(2000);
        try{
            ac.withdraw(2501);
        }
        catch(InvalidAmountException e){
            System.out.println("Error: "+e.getMessage());
        }
        System.out.println(ac.getBalance());
        System.out.println(ac1.getBalance());
        ac.transferMoney(ac1, 500);
        System.out.println(ac.getBalance());
        System.out.println(ac1.getBalance());
        try{
            ac.setPinNumber("123");
        }
        catch(InvalidPinException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}