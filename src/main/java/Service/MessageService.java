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
        return messageDAO.createMessage(message);
        }
        else {
            return null;
        }
    }

    public Message getMessageByID (int message_id) {
        return messageDAO.getMessageByID(message_id);
    }

    public Message deleteMessageByID (int message_id) {
        Message messageDB = this.messageDAO.getMessageByID(message_id);

        if(!message_text.isBlank() && message_text.length() < 255) {
            Message updateMessage = this.messageDAO.getMessageByID(message_id);
            messageDAO.updateMessageByID(message_text, message_id);
            return updatedMessage;
            }
            return null;
    }

    public List<Message> getMessageByAccountID(int posted_by) {
        return messageDAO.getMessagesByAccountID(posted_by);
    }
    
    
}
