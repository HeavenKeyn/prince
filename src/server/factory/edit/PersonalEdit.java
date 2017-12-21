package server.factory.edit;

import server.ClientThread;
import server.factory.Factory;
import server.model.Message;

public class PersonalEdit extends Factory{

	public PersonalEdit(Message message, ClientThread client) {
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
		// TODO Auto-generated method stub
		return this;
	}

}
