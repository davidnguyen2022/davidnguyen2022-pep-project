package Main.DAO;

import Main.Util.ConnectionUtil;
import Main.Model.Message;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class MessageDAO {
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return message;


    }

    public Message insertMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
        }
        catch(SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    
}
