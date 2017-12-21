package server.factory.edit;

import server.ClientThread;
import server.factory.Factory;
import server.model.Message;

public class Edit extends Factory{
	public static int PERSONAL = 301;

	public Edit(Message message, ClientThread client) {
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
		if (code==PERSONAL) {
			return new PersonalEdit(message, client).getFactory();
		}
		return null;
	}

}
