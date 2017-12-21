package server.log.expand.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class LogFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void load() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogFrame frame = new LogFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setContentPane(LogCat.getLogCat());
	}

}
