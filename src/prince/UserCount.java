package prince;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class UserCount extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int allUser=98;
	private int onlineUser=11;
	private JLabel label_1;
	private JLabel label_3;
	private JProgressBar percentBar;
	private JLabel percentLabel;
	private float percent;

	/**
	 * Create the panel.
	 */
	public UserCount() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel label = new JLabel("\u7528\u6237\u603B\u6570\uFF1A");
		panel.add(label);
		
		label_1 = new JLabel("98");
		panel.add(label_1);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel label_2 = new JLabel("\u5728\u7EBF\u4EBA\u6570\uFF1A");
		panel_1.add(label_2);
		
		label_3 = new JLabel("11");
		panel_1.add(label_3);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		
		JLabel lblNewLabel = new JLabel("\u5728\u7EBF\u7387\uFF1A");
		panel_2.add(lblNewLabel);
		
		percentLabel = new JLabel("100%");
		panel_2.add(percentLabel);
		
		percentBar = new JProgressBar();
		panel_2.add(percentBar);
		
		init();
	}
	
	private void setPercent() {
		if (allUser!=0) {
			percentBar.setMaximum(allUser);
			percentBar.setValue(onlineUser);
			percent = (float)onlineUser/allUser;
			percentLabel.setText(""+percent*100+"%");
		}
		
	}
	
	private void init() {
		setPercent();
	}

}
