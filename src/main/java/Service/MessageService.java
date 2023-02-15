package Main.Service;

import Main.DAO.MessageDAO;
import Main.Model.Message;

import java.util.List;

public class MessageService{
    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message addMessage(Message message) {
        return messageDAO.insertMessage(message);
    }
    
    
}
