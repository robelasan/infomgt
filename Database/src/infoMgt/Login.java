package infoMgt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Window.Type;
import java.awt.Frame;
import java.awt.Dialog.ModalExclusionType;

public class Login extends JFrame{

	private JFrame frmAdaMayumiTransport;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private Connection connection;
	private PreparedStatement pst;
	private ResultSet rs;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					Login window = new Login();
					window.frmAdaMayumiTransport.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Login()  {
			
		frmAdaMayumiTransport = new JFrame();
		frmAdaMayumiTransport.setTitle("Ada Mayumi Transport Vehicle Monitoring System");
		frmAdaMayumiTransport.setBackground(new Color(0, 0, 0));
		frmAdaMayumiTransport.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		frmAdaMayumiTransport.setBounds(0, 0, 1200, 800);
		frmAdaMayumiTransport.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdaMayumiTransport.getContentPane().setLayout(null);
		frmAdaMayumiTransport.setResizable(false);	
			
		JLabel carLbl = new JLabel("");
		carLbl.setIcon(new ImageIcon(Login.class.getResource("/img/car.png")));
		carLbl.setBounds(-190, 220, 1006, 714);
		frmAdaMayumiTransport.getContentPane().add(carLbl);
		
		JLabel circleLbl = new JLabel("New label");
		circleLbl.setIcon(new ImageIcon(Login.class.getResource("/img/circle.png")));
		circleLbl.setBounds(-662, -78, 1478, 889);
		frmAdaMayumiTransport.getContentPane().add(circleLbl);
		
		JLabel welcomeLbl = new JLabel("WELCOME TO");
		welcomeLbl.setForeground(new Color(0, 0, 0));
		welcomeLbl.setFont(new Font("Tw Cen MT", Font.BOLD, 30));
		welcomeLbl.setBounds(621, 179, 253, 54);
		frmAdaMayumiTransport.getContentPane().add(welcomeLbl);
		
		JLabel companyNameLbl = new JLabel("<html>ADA MAYUMI'S<br>MONITORING SYSTEM</html>");
		companyNameLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 42));
		companyNameLbl.setForeground(new Color(255, 255, 255));
		companyNameLbl.setBounds(660, 206, 507, 142);
		frmAdaMayumiTransport.getContentPane().add(companyNameLbl);
		
		JLabel usernameLbl = new JLabel("Username:");
		usernameLbl.setForeground(new Color(0, 0, 0));
		usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		usernameLbl.setBounds(788, 467, 109, 31);
		frmAdaMayumiTransport.getContentPane().add(usernameLbl);
		
		JLabel passwordLbl = new JLabel("Password:");
		passwordLbl.setForeground(new Color(0, 0, 0));
		passwordLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordLbl.setBounds(788, 509, 109, 31);
		frmAdaMayumiTransport.getContentPane().add(passwordLbl);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernameField.setBounds(870, 467, 150, 31);
		frmAdaMayumiTransport.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(870, 510, 150, 31);
		frmAdaMayumiTransport.getContentPane().add(passwordField);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(Color.decode("#470E8E"));
		loginPanel.setBounds(859, 563, 101, 31);
		frmAdaMayumiTransport.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);
		
		JLabel loginLbl = new JLabel("LOGIN");
		loginLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		loginLbl.setForeground(Color.BLACK);
		loginLbl.setHorizontalAlignment(SwingConstants.CENTER);
		loginLbl.setBounds(0, 0, 101, 31);
		loginPanel.add(loginLbl);
		loginLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loginPanel.setBackground(new Color(128, 0, 128));
				loginLbl.setForeground(Color.WHITE);
				loginUser();
			}
			public void mouseEntered(MouseEvent e) {
				loginPanel.setBackground(new Color(128, 0, 128));
				loginLbl.setForeground(Color.WHITE);
			}
			public void mouseExited(MouseEvent e) {
				loginPanel.setBackground(Color.decode("#470E8E"));
				loginLbl.setForeground(Color.BLACK);
			}
			public void mousePressed(MouseEvent e) {
				loginPanel.setBackground(Color.decode("#470E8E"));
				loginLbl.setForeground(Color.BLACK);
			}
		});
		
		JLabel logoLbl = new JLabel("");
		logoLbl.setIcon(new ImageIcon(Login.class.getResource("/img/logo.png")));
		logoLbl.setBounds(1017, 11, 150, 130);
		frmAdaMayumiTransport.getContentPane().add(logoLbl);
		
		JLabel backgroundLbl = new JLabel("");
		backgroundLbl.setIcon(new ImageIcon(Login.class.getResource("/img/background.png")));
		backgroundLbl.setBounds(0, 0, 1185, 765);
		frmAdaMayumiTransport.getContentPane().add(backgroundLbl);
		frmAdaMayumiTransport.setLocationRelativeTo(null);
		
		
	}
	
	
	
	private void loginUser() {
        try {
            connection = getConnection();
            String query = "SELECT * FROM Admin WHERE admin_username = ? AND admin_password = ?";
            pst = connection.prepareStatement(query);
            pst.setString(1, usernameField.getText());
            pst.setString(2, new String(passwordField.getPassword()));
            rs = pst.executeQuery();
            if(rs.next()) {
            	frmAdaMayumiTransport.dispose();
                vehicleMain.main(null);
                
            } else {
            	JOptionPane.showMessageDialog(null, "Invalid Credentials.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	 private Connection getConnection() throws SQLException {
	        String url = "jdbc:sqlserver://FELIX;databaseName=AdaMayumi;encrypt=true;trustServerCertificate=true;";
	        String user = "sa";
	        String password = "asd";
	        System.out.println("Admin Table Connected");
	        return DriverManager.getConnection(url, user, password);
	        
	    }
}
