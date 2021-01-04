/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.curs2.ssatr.ia;

/**
 *
 * @author Martin
 */
public class BankAccount {
    //atribute
    private String owner;
    private  int balance;
  
    //constructori
    BankAccount(String owner, int balance){
        this.owner = owner;
        this.balance = balance;
    }
    
    public String getOwner() {
        return owner;
    }

    public int getBalance() {
        return balance;
    }
    
    public void setBalance(int balance) {
        this.balance = balance;
    }       
}
