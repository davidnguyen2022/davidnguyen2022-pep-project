package DAO;

import Util.ConnectionUtil;
import Model.Account;


import java.sql.*;


public class AccountDAO {
 

    public Account insertAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO account (username, password) values (?, ?);" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
           
            
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
        }
        catch(SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    
}
