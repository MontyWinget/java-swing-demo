import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JTextField textFieldEmail;
	private JTextField textFieldFind;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 257, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(10, 23, 221, 30);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(10, 131, 221, 30);
		contentPane.add(textFieldPassword);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(10, 76, 221, 30);
		contentPane.add(textFieldEmail);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(10, 11, 97, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 64, 97, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 117, 97, 14);
		contentPane.add(lblPassword);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver"); // Loading driver with method 
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");
					PreparedStatement ps = conn.prepareStatement("insert into user(user_name,user_email,user_pass) values(?,?,?)");
					String name, email, pass;
					name = textFieldUsername.getText();
					email = textFieldEmail.getText();
					pass = textFieldPassword.getText();
					ps.setString(1, name);
					ps.setString(2, email);
					ps.setString(3, pass);
					int x = ps.executeUpdate();
					if(x > 0) {
						JOptionPane.showMessageDialog(null,"Registration Successful " + "\n" + "Name : " + name + "\n" + "Email : " + email + "\n");
					}else {
						JOptionPane.showMessageDialog(null,"Registration Failed");
					}
					setVisible(false);
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnRegister.setBounds(10, 172, 89, 23);
		contentPane.add(btnRegister);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(142, 172, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel_1 = new JLabel("Find record by ID");
		lblNewLabel_1.setBounds(10, 216, 97, 14);
		contentPane.add(lblNewLabel_1);
		
		textFieldFind = new JTextField();
		textFieldFind.setBounds(145, 213, 86, 20);
		contentPane.add(textFieldFind);
		textFieldFind.setColumns(10);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver"); // Loading driver with method 
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");
					String num = textFieldFind.getText();
					
					PreparedStatement ps = conn.prepareStatement("SELECT user_name,user_email FROM user WHERE id = ");
					ps.executeUpdate();
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null,"Cannot Find");
				}
				
			}
		});
		btnFind.setBounds(142, 244, 89, 23);
		contentPane.add(btnFind);
	}
}
