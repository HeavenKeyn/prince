package server.factory.load;

import java.util.List;

import server.ClientThread;
import server.db.CDB;
import server.db.FDB;
import server.db.KDB;
import server.db.LDB;
import server.db.RDB;
import server.factory.Error;
import server.factory.Factory;
import server.model.Character;
import server.model.Link;
import server.model.Message;

public class Load extends Factory{
	public static final int SIGN_IN_LOAD = 201;

	public Load(Message message, ClientThread client) {
		super(message, client);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Message getResponse() {
		Link link = LDB.getInstance().get(message.getUser());
		if (link==null) {
			return message;
		}
		message.add(link);
		message.add(FDB.getInstance().get(link));
		message.add(KDB.getInstance().get(link));
		List<Character> characters = CDB.getInstance().getLocateInKingdom(link);
		for (Character character : characters) {
			character.setRelations(RDB.getInstance().get(character));
		}
		message.setCharacters(characters);
		return message;
	}

	@Override
	public Factory getFactory() {
		int code = message.getCode();
		if (code==SIGN_IN_LOAD) {
			return this;
		}
		return new Error(message,client).getFactory();
	}

}
