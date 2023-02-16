package DAO;

import Util.ConnectionUtil;
import Model.Message;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class MessageDAO {
    public List<Message> retrieveMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            
            String sql = "SELECT * from Message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                messages.add(message);
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

            
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                int generated_message_id = (int) rs.getLong(1);
                return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
            
        }
        catch(SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    
    public Message retrieveMessageByID(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            
            String sql = "SELECT * FROM message WHERE message_id = ?" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            
            preparedStatement.setInt(1, message_id);
            
            
            ResultSet rs = preparedStatement.executeQuery();
           
            while(rs.next()) {
                return new Message(rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                
                
            }
            
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());

        }
        return null;
    }
    public Message messagesDeleteByID(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "DELETE FROM message WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, message_id);
            preparedStatement.executeUpdate();
            return retrieveMessageByID(message_id);

        }
        catch (SQLException e) {
                System.out.println(e.getMessage());
        }
        return null;
    }

        public Message updateMessagesById(Message message, int message_id){
            Connection connection = ConnectionUtil.getConnection();
            try {
                String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
    
                preparedStatement.setString(1, message.getMessage_text());
                preparedStatement.setInt(2, message_id);
                preparedStatement.executeUpdate();
                return retrieveMessageByID(message_id);
    
            }
            catch (SQLException e) {
                    System.out.println(e.getMessage());
            }
            return null;
    


} 

public List<Message> getAllMessagesByID(int posted_by){
    Connection connection = ConnectionUtil.getConnection();
    List <Message> messages = new ArrayList <>();
    try {
        String sql = "SELECT * FROM message WHERE posted_by = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, posted_by);
        
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            Message message = new Message(rs.getInt("message_id"),
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
