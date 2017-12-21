package server.log.expand.ui;

import javax.swing.JPanel;

import server.log.Item;
import server.log.Logger;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.UIManager;

public class LogCat extends JPanel implements Logger{
	private static final long serialVersionUID = 1L;
	private static LogCat logCat;
	private DefaultListModel<Item> model;
	private final String DEFAULT="----";

	/**
	 * Create the panel.
	 */
	private LogCat() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u5E74", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		comboBox.addItem(DEFAULT);
		comboBox.addItem("2017");comboBox.addItem("2018");
		panel.add(comboBox);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBorder(new TitledBorder(null, "\u6708", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		comboBox_1.addItem(DEFAULT);
		for (int i = 1; i < 13; i++) {
			comboBox_1.addItem(""+i);
		}
		panel.add(comboBox_1);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u65E5", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		comboBox_2.addItem(DEFAULT);
		for (int i = 1; i < 32; i++) {
			comboBox_2.addItem(""+i);
		}
		panel.add(comboBox_2);
		
		JComboBox<String> comboBox_3 = new JComboBox<String>();
		comboBox_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u65F6", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		comboBox_3.addItem(DEFAULT);
		for (int i = 0; i < 24; i++) {
			comboBox_3.addItem(""+i);
		}
		panel.add(comboBox_3);
		
		JComboBox<String> comboBox_4 = new JComboBox<String>();
		comboBox_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u5206", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		comboBox_4.addItem(DEFAULT);
		for (int i = 0; i < 60; i++) {
			comboBox_4.addItem(""+i);
		}
		panel.add(comboBox_4);
		
		JComboBox<String> comboBox_5 = new JComboBox<String>();
		comboBox_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thread", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		comboBox_5.addItem(DEFAULT);
		panel.add(comboBox_5);
		
		JComboBox<String> comboBox_6 = new JComboBox<String>();
		comboBox_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tag", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		comboBox_6.addItem(DEFAULT);
		panel.add(comboBox_6);
		
		model = new DefaultListModel<Item>();
		JList<Item> list = new JList<Item>(model);
		
		JScrollPane scrollPane = new JScrollPane(list);
		add(scrollPane);

		
	}
	
	public synchronized static LogCat getLogCat() {
		if (logCat==null) {
			logCat = new LogCat();
		}
		return logCat;
	}

	@Override
	public void print(Item item) {
		model.addElement(item);
		if (model.size()>2000) {
			model.removeRange(0, 1000);
		}
	}
	

}
