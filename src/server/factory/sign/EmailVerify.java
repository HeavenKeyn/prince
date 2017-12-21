package server.factory.sign;

import server.ClientThread;
import server.db.UDB;
import server.factory.Error;
import server.factory.Factory;
import server.model.Message;
import server.util.JavaEmail;
import server.util.RandomUtil;

public class EmailVerify extends Factory{

	public EmailVerify(Message message, ClientThread client) {
		super(message, client);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Message getResponse() {
		Message response = new Error(message, client).getFactory().getResponse();
		if (message.getUser()!=null&&message.getUser().getEmail()!=null) {
			if (UDB.getInstance().exsists(message.getUser().getEmail())) {
				response = new Error(message, client).getResponse(Error.NAME_SIGN_UP_ERROR);
			}else {
				client.vcode = RandomUtil.getRandomString(4);
				new JavaEmail().sendEmail(message.getUser().getEmail(), client.vcode);
				response = message;
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
