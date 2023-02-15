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
            if(rs.next()) {
                Account accountLogin = new Account(rs.getInt(columnLabel:"account_id"), rs.getString(columnLabel:"username"), rs.getString(columnLabel:"password"));
                return accountLogin;
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());

        }
        return null;
    }

    public Account getAccountByUsername(String username){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username = ?" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            
            preparedStatement.setString(1, username);
            
           
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Account account = new Account(rs.getInt(columnLabel:"account_id"), rs.getString(columnLabel: "username"), rs.getString(columnLabel: "password"));
                System.out.print(account.getUsername());
                return account;
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());

        }
        return null;
    }

    public Account getAccountByAccountId(int posted_by){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE account_id = ?" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            
            preparedStatement.setInt(1, posted_by);
            
           
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Account account = new Account(rs.getInt(columnLabel:"account_id"), rs.getString(columnLabel: "username"), rs.getString(columnLabel: "password"));
                
                return account;
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());

        }
        return null;
    }

    
    
}
