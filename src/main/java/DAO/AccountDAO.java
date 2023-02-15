package DAO;

import Util.ConnectionUtil;
import Model.Account;


import java.sql.*;


public class AccountDAO {
 

    public Account addAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO account (username, password) values (?, ?);" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
           
            
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int generated_account_id = (int) rs.getLong(1);
                return new Account(generated_account_id, account.getUsername(), account.getPassword());

            }
        }
        catch(SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public Account userLogin(String username, String password){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
           
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Account accountLogin = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                return accountLogin;
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());

        }
        return null;
    }

    
}
