package prince;

import javax.swing.JPanel;

import prince.model.TUser;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class UserPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public UserPanel(TUser user) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel lblUid = new JLabel("uid: ");
		panel.add(lblUid);
		
		JLabel lblNewLabel = new JLabel(""+user.getUid());
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel lblUsername = new JLabel("username: ");
		panel_1.add(lblUsername);
		
		JLabel lblNewLabel_1 = new JLabel(user.getUsername());
		panel_1.add(lblNewLabel_1);

	}

}
