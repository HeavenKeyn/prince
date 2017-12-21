package server.factory.sign;

import server.ClientThread;
import server.db.UDB;
import server.factory.Error;
import server.factory.Factory;
import server.factory.load.Load;
import server.model.Message;
import server.model.User;

public class EmailSignUp extends Factory{

	public EmailSignUp(Message message, ClientThread client) {
		super(message, client);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Message getResponse() {
		Message response = new server.factory.Error(message, client).getResponse();
		if (message.getUser()!=null&&message.getInfo()!=null) {
			if (message.getInfo().equals(client.vcode)) {
				if (UDB.getInstance().insert(message.getUser())) {
					User user = UDB.getInstance().getIdPassword(message.getUser().getEmail());
					if (user!=null) {
						response = new Message(Load.SIGN_IN_LOAD).add(user.setPassword(null));
						response = new Load(response, client).getFactory().getResponse();
					}
				}
			}else {
				response = new Error(message,client).getResponse(Error.VERIFICATION_CODE_ERROR);
			}
		}
		return response;
	}

	@Override
	public Factory getFactory() {
		// TODO Auto-generated method stub
		return this;
	}

}
