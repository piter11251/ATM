public class Main {
    public static void main(String[] args) throws Exception {
        Account ac = new Account("Piotr", "Kozlowski");
        System.out.println(ac.getPassword());
        //ac.setPassword();
        //System.out.println(ac.getPassword());
        System.out.println(ac.getBalance());
        ac.deposit(1500);
        System.out.println(ac.getBalance());

        try{
            ac.withdraw(2501);
        }
        catch(InvalidAmountException e){
            System.out.println("Error: "+e.getMessage());
        }
        try{
            ac.setPassword();
        }
        catch(InvalidPasswordException e){
            System.out.println("Error: "+e.getMessage());
        }

    }
}