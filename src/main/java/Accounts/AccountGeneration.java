package Accounts;

public class AccountGeneration {
    private static int id = 0;

    public static void incrementId(int inc) {
        AccountGeneration.id += inc;
    }

    public Account createAccount(String name, int clientId){
        return new Account(name, clientId, id++);
    }

}
