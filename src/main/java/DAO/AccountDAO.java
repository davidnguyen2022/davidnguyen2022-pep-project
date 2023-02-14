package Main.DAO;

import Main.Util.ConnectionUtil;
import Main.Model.Account;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;


public class AccountDAO {
    public List<Account> getAllAccounts(){
        Connection connection = ConnectionUtil.getConnection();
        List<Account> accounts = new ArrayList<>();
        try {
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return account;

    }

    public Account insertAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
        }
        catch(SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    
}
