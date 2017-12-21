package server.factory.sign;

import server.ClientThread;
import server.db.UDB;
import server.factory.Error;
import server.factory.Factory;
import server.factory.load.Load;
import server.model.Message;
import server.model.User;

public class NameSignIn extends Factory{

	public NameSignIn(Message message, ClientThread client) {
		super(message, client);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Message getResponse() {
		Message response = new Error(message,client).getFactory().getResponse();
		if (message.getUser()==null) {
			return response;
		}
		User user = message.getUser();
		User in = UDB.getInstance().getIdPassword(user.getUsername());
		if (in!=null) {
			if (in.getPassword().equals(user.getPassword())) {
				//response = new Message(message.getCode());
				response = new Message(Load.SIGN_IN_LOAD);
				response.add(new User().setId(in.getId()).setUsername(user.getUsername()));
				response = new Load(response,client).getFactory().getResponse();
			}else {
				response = new Error(message,client).getResponse(Error.PASSWORD_SIGN_IN_ERROR); 
			}
		}else {
			response = new Error(message,client).getResponse(Error.NAME_SIGN_IN_ERROR); 
		}
		
		return response;
	}

	@Override
	public Factory getFactory() {
		// TODO Auto-generated method stub
		return this;
	}

}
