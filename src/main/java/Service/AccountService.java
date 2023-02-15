package Service;

import DAO.AccountDAO;
import Model.Account;



public class AccountService{
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account addAccount(Account account) {
        if(account.username != "" && account.password.length() >=4) {
            return accountDAO.addAccount(account);
        }
        return null;
    }

    public Account logIn(Account account) {

    String username = account.getUsername();
    String password = account.getPassword();
    Account accountDB = accountDAO.getAccountByUsername(username);
        if(accountDB != null || !username.isBlank()) {
            return this.accountDAO.userLogin(username, password);
        }
        else{
            return null;
        }
        
    }

    

    

    
    


}
