package DAO;

import Util.ConnectionUtil;
import Model.Message;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class MessageDAO {
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            
            String sql = "SELECT * from Message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                message.add(message);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return messages;


    }

    public Message insertMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) values (?, ?, ?);" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            
            preparedStatement.setInt(1, account.getPostedBy());
            preparedStatement.setString(2, account.getMessageText());
            preparedStatement.setLong(3, account.getTimePostedEpoch());
            
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            
        }
        catch(SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    
}
