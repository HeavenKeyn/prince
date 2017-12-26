package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

//import org.eclipse.osgi.internal.signedcontent.Base64;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.gson.Gson;

import server.db.KDB;
import server.db.MySQL;
import server.db.UDB;
import server.factory.Entrance;
import server.factory.sign.EmailVerify;
import server.log.Log;
import server.log.LogFactory;
import server.log.expand.db.LogDB;
import server.log.expand.ui.LogCat;
import server.log.expand.ui.LogFrame;
import server.model.Character;
import server.model.Family;
import server.model.Kingdom;
import server.model.Link;
import server.model.Message;
import server.model.User;
import server.util.Code;
import server.util.JavaEmail;

public class Test {
	public static void main(String[] args) {
		//KDB.getInstance().insert(new Kingdom().setName("����"));
		//System.out.println(KDB.getInstance().get(new Link().setKid(2)).getName());
		//Thread.currentThread().getThreadGroup().getName();
		LogFrame.load();
		new JavaEmail().sendEmail("13261708682@163.com", "aaaaa");
		//new Test();
	}
	
	public Test() {
		LogFactory.getLog(getClass()).add(LogCat.getLogCat()).info(Code.getMD5_32("admin888"));
		LogFactory.getLog(getClass()).add(LogDB.getInstance()).info(Code.getMD5_24("admin888"));
	}
	


}
