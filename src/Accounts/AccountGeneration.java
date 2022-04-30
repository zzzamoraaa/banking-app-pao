package Accounts;

public class AccountGeneration {
    private static int id = 0;

    public static void incrementId(int inc) {
        AccountGeneration.id += inc;
    }

    public Account createAccount(String name, int customerId){
        return new Account(name, customerId, id++);
    }

    /*public SavingsAccount createSavingsAccount(String name, int customerId){
        return new SavingsAccount(name, customerId, id++);
    }*/
}
