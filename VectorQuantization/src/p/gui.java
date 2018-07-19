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
import javax.swing.JLabel;

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
		frmLzw.setTitle("VectorQuantization");
		frmLzw.setBounds(100, 100, 402, 217);
		frmLzw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLzw.getContentPane().setLayout(null);
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Times New Roman", Font.BOLD, 14));
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner.setBounds(222, 30, 50, 20);
		frmLzw.getContentPane().add(spinner);
	
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		spinner_1.setBounds(222, 61, 50, 20);
		frmLzw.getContentPane().add(spinner_1);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		spinner_2.setBounds(222, 95, 50, 20);
		frmLzw.getContentPane().add(spinner_2);
		
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
				int bits;
				int wid;
				int len;
				bits=(int) spinner.getValue();
				wid=(int) spinner_1.getValue();
				len=(int) spinner_2.getValue();
				try {
					new Compress(bits,len,wid);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(71, 126, 118, 23);
		frmLzw.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("DeCompess");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try {
					DeCompress d=new DeCompress();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(218, 126, 118, 23);
		frmLzw.getContentPane().add(btnNewButton_1);
		
		JLabel lblWidthOfVector = new JLabel("Width Of Vector");
		lblWidthOfVector.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblWidthOfVector.setBounds(84, 60, 128, 22);
		frmLzw.getContentPane().add(lblWidthOfVector);
		
		JLabel lblLengthOfVector = new JLabel("Length of Vector");
		lblLengthOfVector.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblLengthOfVector.setBounds(84, 91, 119, 29);
		frmLzw.getContentPane().add(lblLengthOfVector);
		
		
		
		
	}
}
