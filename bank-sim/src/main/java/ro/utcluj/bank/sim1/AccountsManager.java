/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.bank.sim1;

/**
 *
 * @author Martin
 */
public class AccountsManager {
       BankAccount [] accounts = new BankAccount[10]; // [null null null null ....]
   

    void addAccount(BankAccount a)
    {
        for(int i=0;i<accounts.length;i++){
           if(accounts[i]==null){
               accounts[i] = a;
               System.out.println("New account added on test track.");
               return;
           }           
       }
       System.out.println("No empty position found on test track.");
    }
    
    int getTotalBalance()
    {
        int totalBlanace = 0;
        for(BankAccount a: accounts){ //foreach
            if(a!=null)
                totalBlanace+= a.getBalance();
        }
        return totalBlanace;
    }
    
    String getAllAccountsDetails(){
       String all = "";
       for(BankAccount a: accounts){ 
           if(a!=null){
            String line = "Bank account owner= "+a.getOwner()+" balance= "+a.getBalance()+"\n";
            all=all+line;
           }
       }
    return all;
   }  
    
    boolean transfer(String fromOwnerName, String toOwnerName, int amount)
    {
     
        BankAccount fromOwner = null;
        BankAccount toOwner = null;
        
        for(BankAccount a: accounts){ 
            if(a!=null){
                if(a.getOwner().equals(fromOwnerName))
                   {
                    fromOwner = a;
                }else if(a.getOwner().equals(toOwnerName)){
                    toOwner = a;
                }
            }          
       }
        if ((0 >= amount) || ( null == fromOwner)|| (null == toOwner))
        {
            return false;
        }
        
        toOwner.setBalance(amount + toOwner.getBalance());
        fromOwner.setBalance( fromOwner.getBalance() - amount);
        
       return true;     
    }
    
        public static void main(String[] args) {
        AccountsManager accountsManager = new AccountsManager();
        
        accountsManager.addAccount(new BankAccount("Misaros", 5));
        accountsManager.addAccount(new BankAccount("Andrei", 0));
        accountsManager.addAccount(new BankAccount("Vlad", 12));
        accountsManager.addAccount(new BankAccount("Alina", 100));
        accountsManager.addAccount(new BankAccount("George", 20));
              
    }
}
