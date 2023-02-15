package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */

     

    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    public Javalin startAPI() {

        Javalin app = Javalin.create();
        

        app.post("/register", this::registerForHandler);
        app.post("/login", this::loginForHandler);
        app.post("/messages", this::postMessagesByHandler);
        app.get("/messages", this::getAllMessagesForHandler);
        app.get("/messages/{message_id}", this::getMessagesByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessagesByIdHandler);
        app.patch("/messages/{message_id}", this::updateMessagesByHandler);
        app.get("/accounts/{account_id}/messages", this::getMessagesByAccountHandler);
        
        

        return app;

    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    /**private void exampleHandler(Context context) {
        //context.json("sample text");
    } **/
    private void registerForHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account addAccount = accountService.addedAccount(account);

        if(addAccount!=null){
            context.json(mapper.writeValueAsString(addAccount));
            context.status(200);
        }else{
            context.status(400);
        }
        
    }

    private void loginForHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account userLogin = accountService.logIn(account);

        if(userLogin !=null || account != null){
            context.json(mapper.writeValueAsString(userLogin));
            
        }else{
            context.status(401);
        }
        
    }

    private void postMessagesByHandler(Context context) throws JsonProcessingException, com.fasterxml.jackson.databind.JsonMappingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message addedMessage = messageService.createMessage(message);

        if(addedMessage!=null){
            context.json(mapper.writeValueAsString(addedMessage));
            context.status(100);
        }else{
            context.status(400);
        }
        
    }

    

    private void getAllMessagesForHandler(Context context) {
        List<Message> message = messageService.getAllMessages();
        context.json(messages);
    }

    private void getMessagesByIdHandler(Context context) {
        ObjectMapper mapper = new ObjectMapper();
        int message_id = Integer.parseInt(ctx.pathParam(arg0:"message_id"));
        Message messageID = messageService.createMessage(message_id);

        if(messageID!=null){
            context.json(mapper.writeValueAsString(messageID));
            context.status(100);
        }else{
            context.status(400);
        }
        
    }

    private void deleteMessagesByIdHandler(Context context) throws JsonProcessingException{
        
        
        int message_id = Integer.parseInt(ctx.pathParam(arg0:"message_id"));
        Message deleteMessage = messageService.deleteMessageByID(message_id);

        if(deleteMessage != null){
            context.json(deleteMessage);
            context.status(200);
        }
        
    }

    private void updateMessagesByHandler(Context context) throws JsonProcessingException {
        
        ObjectMapper mapper = new ObjectMapper();
        int message_id = Integer.parseInt(ctx.pathParam(arg0: "message_id"));
        Message updatedMesage = messageService.updatedMessageByID(message, message_id);

        if(updatedMesage!=null){
            context.json(updatedMessage);
            context.status(200);
        }
        
    }

    private void getMessagesByAccountHandler(Context context) throws JsonMappingException, JsonProcessingException {
        
        int posted_by = Integer.parseInt(ctx.pathParam(arg0: "account_id"));
    List<Message> messages = messageService.getMessagesByAccountID(posted_by);
        ctx.json(message);
    }


    

}