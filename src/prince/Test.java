package prince;

import prince.model.HUser;

public class Test {

	public static void main(String[] args) {
		HUser user = new HUser().setUid(111);
		user.setUname("222");
		System.out.println(""+user.getUid()+" "+user.getUname());
	}
}
