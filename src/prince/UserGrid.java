package prince;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import prince.model.TUser;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JScrollBar;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;

public class UserGrid extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPanel gridPanel;
	private JScrollPane scrollPane;
	private JSplitPane splitPane;
	//private JList<JPanel> list_1;

	/**
	 * Create the panel.
	 */
	public UserGrid() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u641C\u7D22");
		panel.add(button);
		
		gridPanel = new JPanel();
		
		scrollPane = new JScrollPane(gridPanel);
		gridPanel.setLayout(new GridLayout(0, 5, 0, 0));
		
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,panel,scrollPane);
		splitPane.setAlignmentY(Component.CENTER_ALIGNMENT);
		splitPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(splitPane);
		


		init();
	}
	
	private void init() {
		List<TUser> list = new ArrayList<>();
		for(int i=0;i<100;i++) {
			TUser user = new TUser(i, "aaa");
			list.add(user);
			JButton button = new JButton();
			button.add(new UserPanel(user));
			//button.setPreferredSize(new Dimension(190,250));
			gridPanel.add(button);
		}
		
	}

}
