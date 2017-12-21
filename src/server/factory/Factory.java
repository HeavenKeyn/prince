package server.factory;

import server.ClientThread;
import server.model.Message;

public abstract class Factory {
	protected Message message;
	protected ClientThread client;
	public Factory(Message message, ClientThread client) {
		this.message = message;
		this.client = client;
	}
	
	public abstract Message getResponse();
	public abstract Factory getFactory();
}
