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
                Message message = new Message(rs.getInt(columnLabel: "message_id"),
                    rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                message.add(message);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;


    }

    public Message insertMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) values (?, ?, ?);" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            
            preparedStatement.setInt(1, message.getPostedBy());
            preparedStatement.setString(2, message.getMessageText());
            preparedStatement.setLong(3, message.getTimePostedEpoch());
            
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                int generated_message_id = int(rs.getLong(1));
                return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
            
        }
        catch(SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    
    public Message getMessagesById(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            
            String sql = "SELECT * FROM message WHERE message_id = ?" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            
            preparedStatement.setInt(1, message.id());
            
            
            ResultSet rs = preparedStatement.executeQuery();
           
            while(rs.next()) {
                Message message = new Message(rs.getInt(columnLabel: "message_id"),
                    rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                
                return message;
            }
            
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());

        }
        return null;
    }
    public Message deleteMessagesById(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql "DELETE FROM message WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.preparedStatement(sql);

            preparedStatement.setInt(parameterIndex: 1, message_id);
            preparedStatement.executeQuery();
            return getMessageByID(message_id);

        }
        catch (SQLException e) {
                System.out.println(e.getMessage());
        }
        return null;
    }

        public Message updateMessagesById(String message_text, int message_id){
            Connection connection = ConnectionUtil.getConnection();
            try {
                String sql "UPDATE message SET message_text = ? WHERE message_id = ?";
                PreparedStatement preparedStatement = connection.preparedStatement(sql);
    
                preparedStatement.setString(parameterIndex: 1, message_text);
                preparedStatement.setInt(parameterIndex: 2, message_id);
                preparedStatement.executeUpdate();
                return getMessageByID(message_id);
    
            }
            catch (SQLException e) {
                    System.out.println(e.getMessage());
            }
            return null;
    


} 

public List<Message> getMessagesByAccountID(int posted_by){
    Connection connection = ConnectionUtil.getConnection();
    try {
        String sql "SELECT * FROM message WHERE posted_by = ?";
        PreparedStatement preparedStatement = connection.preparedStatement(sql);

        preparedStatement.setString(parameterIndex: 1, posted_by);
        
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            Message message = new Message(rs.getInt(columnLabel: "message_id"),
                    rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
            

            messages.add(message);
        }

    }
    catch (SQLException e) {
            System.out.println(e.getMessage());
    }
    return messages;



} 
}
