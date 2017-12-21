package server.factory;

import server.ClientThread;
import server.factory.edit.Edit;
import server.factory.load.Load;
import server.factory.sign.Sign;
import server.model.Message;

public class Entrance extends Factory{

	public Entrance(Message message, ClientThread client) {
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
		if (code>100&&code<200) {
			return new Sign(message,client).getFactory();
		}else if (code>200&&code<300) {
			return new Load(message, client).getFactory();
		}else if (code>300&&code<400) {
			return new Edit(message, client).getFactory();
		}
		return new Error(message,client).getFactory();
	}
	
	

}
