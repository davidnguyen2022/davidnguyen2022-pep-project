package Service;

import DAO.MessageDAO;
import Model.Message;

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

    public Message createMessage(Message message) {
        String message_text = message.getMessage_text();
        if(!message_text.isBlank() && message_text.length() < 255) {
        return messageDAO.insertMessage(message);
        }
        else {
            return null;
        }
    }

    public Message getMessageByID (int message_id) {
        return messageDAO.getMessageById(message_id);
    }

    public Message deleteMessageByID (int message_id) {
        Message messageDB = this.messageDAO.getMessageById(message_id);

        messageDAO.deleteMessagesById(message_id);
        if(messageDB == null) {
            return null;
        }return messageDB;

    }

    public Message updateMessageByID (Message message, int message_id) {
        if(messageDAO.getMessageById(message_id) != null) {
            return messageDAO.updateMessagesById(message, message_id);
        }
        return null;
    }

    public List<Message> getMessagesByAccountID(int posted_by) {
        return messageDAO.getMessagesByAccountID(posted_by);
    }
    
    
}
