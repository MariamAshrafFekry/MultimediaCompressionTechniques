package p;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Window.Type;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Label;

public class gui {

	private JFrame frmLzw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frmLzw.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLzw = new JFrame();
		frmLzw.setIconImage(Toolkit.getDefaultToolkit().getImage(gui.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		frmLzw.setForeground(Color.WHITE);
		frmLzw.setFont(UIManager.getFont("Button.font"));
		frmLzw.setTitle("NonUniformQuantizer");
		frmLzw.setBounds(100, 100, 402, 200);
		frmLzw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLzw.getContentPane().setLayout(null);
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Times New Roman", Font.BOLD, 14));
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner.setBounds(212, 30, 60, 20);
		frmLzw.getContentPane().add(spinner);
	
		Label label = new Label("Number of Bits");
		label.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label.setForeground(Color.BLACK);
		label.setAlignment(Label.CENTER);
		label.setBounds(60, 30, 156, 22);
		frmLzw.getContentPane().add(label);
		JButton btnNewButton = new JButton("Compress");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int levels;
				try {
					levels=(int) spinner.getValue();
				
					Compress c=new Compress(levels);
				} catch (IOException e) {
				}
			}
		});
		btnNewButton.setBounds(61, 61, 118, 23);
		frmLzw.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("DeCompess");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try {
					new DeCompress();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(212, 61, 118, 23);
		frmLzw.getContentPane().add(btnNewButton_1);
		
		
	}
}
