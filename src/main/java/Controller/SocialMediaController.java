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
        

        app.post("/register", this::postRegisterByHandler);
        app.post("/login", this::postLoginByHandler);
        app.post("/messages", this::postMessagesByHandler);
        app.get("/messages", this::getAllMessagesByHandler);
        app.get("/messages/{message_id}", this::getMessagesByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessagesByIdHandler);
        app.patch("/messages/{message_id}", this::updateMessagesHandler);
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
    private void postRegisterByHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);

        if(addedAccount!=null){
            context.json(mapper.writeValueAsString(addedAccount));
            context.status(200);
        }else{
            context.status(400);
        }
        
    }

    private void postLoginByHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account userLogin = accountService.logIn(account);

        if(addedAccount!=null){
            context.json(mapper.writeValueAsString(addedAccount));
            context.status(200);
        }else{
            context.status(401);
        }
        
    }

    private void postMessagesByHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Account.class);
        Message createMessage = messageService.createMessage(message);

        if(addedMessage!=null){
            context.json(mapper.writeValueAsString(addedMessage));
        }else{
            context.status(400);
        }
        
    }

    

    private void getAllMessagesByHandler(Context context) {
        List<Message> message = messageService.getAllMessagesByHandler();
        context.json(messages);
    }

    private void getMessagesByIdHandler(Context context) {
        List<Message> message = messageService.getAllMessagesByIdHandler();
        context.json("get message by id");
    }

    private void deleteMessagesByIdHandler(Context context) JsonProcessingException{
        
        context.json("delete messages by id");
    }

    private void updateMessagesHandler(Context context) throws JsonProcessingException {
        
        context.json("update message");
    }

    private void getMessagesByAccountHandler(Context context)throws JsonMappingException, JsonProcessingException {
        
        context.json(messages);
    }

    

}