package server.factory;

import server.ClientThread;
import server.model.Message;

public class Error extends Factory{
	public static final int NAME_SIGN_IN_ERROR = 901; 
	public static final int PASSWORD_SIGN_IN_ERROR = 902; 
	public static final int NAME_SIGN_UP_ERROR = 903; 
	public static final int VERIFICATION_CODE_ERROR = 904; 

	public Error(Message message, ClientThread client) {
		super(message, client);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Message getResponse() {
		return new Message(900).setInfo("�Ҳ�֪��������ʲô���һ��Ǹ�������");
	}
	
	public Message getResponse(int code) {
		Message message = new Message(code);
		if (code==NAME_SIGN_IN_ERROR) {
			message.setInfo("�û��������ڣ�");
		}else if (code==PASSWORD_SIGN_IN_ERROR) {
			message.setInfo("�������");
		}else if (code==NAME_SIGN_UP_ERROR) {
			message.setInfo("�û����Ѵ��ڣ�");
		}else if (code==VERIFICATION_CODE_ERROR) {
			message.setInfo("��֤�����");
		}
		return message;
	}

	@Override
	public Factory getFactory() {
		return this;
	}

}
