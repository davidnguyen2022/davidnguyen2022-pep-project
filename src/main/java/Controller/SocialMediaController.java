package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;

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
        

        app.post("/register", this::userRegistrationForHandler);
        app.post("/login", this::userLoginForHandler);
        app.post("/messages", this::creationOfNewMessagesByHandler);
        app.get("/messages", this::retrieveMessagesForHandler);
        app.get("/messages/{message_id}", this::retrieveMessagesByIdForHandler);
        app.delete("/messages/{message_id}", this::messagesDeletedByIDForHandler);
        app.patch("/messages/{message_id}", this::updateMessagesByIdForHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByAccountIDForHandler);
        
        

        return app;

    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    /**private void exampleHandler(Context context) {
        //context.json("sample text");
    } **/
    private void userRegistrationForHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account registerAccount = accountService.registerAccount(account);

        if(registerAccount!=null){
            context.json(mapper.writeValueAsString(registerAccount));
            context.status(200);
        }else{
            context.status(400);
        }
        
    }

    private void userLoginForHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account loginForUser = accountService.logIn(account.getUsername(), account.getPassword());

        if(loginForUser !=null){
            context.json(mapper.writeValueAsString(loginForUser));
            
        }else{
            context.status(401);
        }
        
    }

    private void creationOfNewMessagesByHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message messageAdded = messageService.createMessage(message);

        if(messageAdded!=null){
            context.json(mapper.writeValueAsString(messageAdded));
            context.status(200);
        }else{
            context.status(400);
        }
        
    }

    

    private void retrieveMessagesForHandler(Context ctx) {
        List<Message> message = messageService.retrieveMessages();
        ctx.json(message);
    }

    private void retrieveMessagesByIdForHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message messageID = messageService.retrieveMessageByID(message_id);

        if(messageID!=null){
            ctx.json(mapper.writeValueAsString(messageID));
            ctx.status(200);
        }else{
            ctx.status(400);
        }
        
    }

    private void messagesDeletedByIDForHandler(Context ctx) throws JsonProcessingException{
        
        
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        //Message messageDelete = messageService.messagesDeletedByID(message_id);
        Message messageDelete = messageService.messagesDeleteByID(message_id);

        if(messageDelete != null){
            ctx.json(messageDelete);
            
        } 
        else {
            ctx.status(200);
        }
        
    }

    private void updateMessagesByIdForHandler(Context ctx) throws JsonProcessingException {
        
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message messageUpdated = messageService.updateMessageByID(message, message_id);

        if(messageUpdated == null || messageUpdated.message_text.isBlank()){
            
            ctx.status(400);
        }
        else {
            ctx.json(messageUpdated);
        }
        
    }

    private void getAllMessagesByAccountIDForHandler(Context ctx) throws JsonProcessingException {
        
        int posted_by = Integer.parseInt(ctx.pathParam("account_id"));
    List<Message> messages = messageService.getAllMessagesByID(posted_by);
        ctx.json(messages);
    }


    

}