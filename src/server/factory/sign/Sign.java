package server.factory.sign;

import server.ClientThread;
import server.factory.Error;
import server.factory.Factory;
import server.model.Message;

public class Sign extends Factory{
	private final int NAME_SIGN_IN = 101;
	private final int EMAIL_VERIFY = 112;
	private final int EMAIL_REGISTER = 113;

	public Sign(Message message, ClientThread client) {
		super(message, client);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Message getResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Factory getFactory() {
		int code = message.getCode();
		if (code==NAME_SIGN_IN) {
			return new NameSignIn(message,client).getFactory();
		}else if (code==EMAIL_VERIFY) {
			return new EmailVerify(message,client).getFactory();
		}else if (code==EMAIL_REGISTER) {
			return new EmailSignUp(message,client).getFactory();
		}
		return new Error(message,client).getFactory();
	}

}
