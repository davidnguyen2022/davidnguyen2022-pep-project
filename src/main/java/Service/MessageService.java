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
    public List<Message> retrieveMessages() {
        return messageDAO.retrieveMessages();
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

    public Message retrieveMessageByID (int message_id) {
        return messageDAO.retrieveMessageByID(message_id);
    }

    public Message messagesDeleteByID (int message_id) {
        Message messageDB = this.messageDAO.retrieveMessageByID(message_id);

        messageDAO.messagesDeleteByID(message_id);
        if(messageDB == null) {
            return null;
        }return messageDB;

    }

    public Message updateMessageByID (Message message, int message_id) {
        if(messageDAO.retrieveMessageByID(message_id) != null) {
            return messageDAO.updateMessagesById(message, message_id);
        }
        return null;
    }

    public List<Message> getAllMessagesByID(int posted_by) {
        return messageDAO.getAllMessagesByID(posted_by);
    }
    public Message messagesDeletedByID(int message_id) {
        return null;
    }
    
    
}
