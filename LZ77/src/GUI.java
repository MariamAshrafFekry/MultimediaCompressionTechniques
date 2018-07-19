
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Window.Type;
import java.awt.Font;

public class GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 393, 126);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{109, 0, 99, 111, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JButton btnNewButton = new JButton("Compress File");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton.setBackground(UIManager.getColor("Button.light"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Compression c=new Compression();
				//btnDecompressFile.setEnabled(false);
				JOptionPane.showMessageDialog(null, "Compress Is Complete");
				//btnDecompressFile.setEnabled(true);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 5;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		JButton btnDecompressFile = new JButton("DeCompress File");      //added
		
		//JButton btnDecompressFile = new JButton("DeCompress File");
		btnDecompressFile.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnDecompressFile.setBackground(UIManager.getColor("Button.light"));
		btnDecompressFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				 new Decompression();
				btnNewButton.setEnabled(false);
				JOptionPane.showMessageDialog(null, "DeCompress Is Complete");
				btnNewButton.setEnabled(true);
			}
		});
		GridBagConstraints gbc_btnDecompressFile = new GridBagConstraints();
		gbc_btnDecompressFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnDecompressFile.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnDecompressFile.gridx = 2;
		gbc_btnDecompressFile.gridy = 5;
		frame.getContentPane().add(btnDecompressFile, gbc_btnDecompressFile);
	}

}
