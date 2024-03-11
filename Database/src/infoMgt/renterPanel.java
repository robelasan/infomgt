package infoMgt;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;

import java.sql.*;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Choice;
import java.awt.Checkbox;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import java.awt.Panel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JScrollPane;

public class renterPanel extends JPanel {

	
	private JTable renterTable;
	private JTextField renterIDTxt;
	private JTextField fnameTxt;
	private JTextField lnameTxt;
	private JTextField phoneTxt;
	private Connection connection;
	private DefaultTableModel renterModel;
	private JTextField addressTxt;
	private PreparedStatement pst;
	private JPanel renterSidePanel;
	
	public renterPanel() throws ClassNotFoundException {

		setBounds(0, 0, 915, 540);
		setLayout(null);
		setBackground(new Color(255, 255, 255));
		
		renterModel = new DefaultTableModel();
		renterModel.addColumn("ID");
		renterModel.addColumn("First name");
		renterModel.addColumn("Last name");
		renterModel.addColumn("Phone");
		renterModel.addColumn("Address");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 90, 585, 440);
		add(scrollPane);
		renterTable = new JTable(renterModel);
		scrollPane.setViewportView(renterTable);
		renterTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		renterTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedrow = renterTable.getSelectedRow();
				Object id = renterModel.getValueAt(selectedrow, 0);
				Object fname = renterModel.getValueAt(selectedrow, 1);
				Object lname = renterModel.getValueAt(selectedrow, 2);
				Object phone = renterModel.getValueAt(selectedrow, 3);
				Object address = renterModel.getValueAt(selectedrow, 4);
				
				
				renterIDTxt.setText(id.toString());
				fnameTxt.setText(fname.toString());
				lnameTxt.setText(lname.toString());
				phoneTxt.setText(phone.toString());
				addressTxt.setText(address.toString());
				
			}
		});
		
		renterSidePanel = new JPanel();
		renterSidePanel.setBorder(new LineBorder(new Color(128, 0, 128), 3));
		renterSidePanel.setBounds(605, 10, 300, 520);
		add(renterSidePanel);
		renterSidePanel.setLayout(null);
	
		JLabel renterHeader = new JLabel("RENTER");
		renterHeader.setHorizontalAlignment(SwingConstants.CENTER);
		renterHeader.setFont(new Font("Tahoma", Font.BOLD, 32));
		renterHeader.setBounds(10, 10, 280, 70);
		renterSidePanel.add(renterHeader);
		
		JLabel renterID = new JLabel("ID:");
		renterID.setForeground(Color.BLACK);
		renterID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		renterID.setBounds(20, 105, 35, 15);
		renterSidePanel.add(renterID);
	
		JLabel fnameLbl = new JLabel("First name:");
		fnameLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fnameLbl.setBounds(20, 145, 70, 15);
		renterSidePanel.add(fnameLbl);
		
		JLabel lnameLbl = new JLabel("Last name:");
		lnameLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lnameLbl.setBounds(20, 185, 70, 15);
		renterSidePanel.add(lnameLbl);
		
		JLabel phoneLbl = new JLabel("Phone:");
		phoneLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		phoneLbl.setBounds(20, 225, 65, 15);

		renterSidePanel.add(phoneLbl);
		
		JLabel addressLbl = new JLabel("Address:");
		addressLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addressLbl.setBounds(20, 265, 65, 15);
		renterSidePanel.add(addressLbl);
		
		renterIDTxt = new JTextField();
		renterIDTxt.setEditable(false);
		renterIDTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		renterIDTxt.setBounds(140, 100, 140, 30);
		renterIDTxt.setColumns(10);
		renterSidePanel.add(renterIDTxt);	
		
		fnameTxt = new JTextField();
		fnameTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fnameTxt.setColumns(10);
		fnameTxt.setBounds(140, 140, 140, 30);
		renterSidePanel.add(fnameTxt);
		
		lnameTxt = new JTextField();
		lnameTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lnameTxt.setColumns(10);
		lnameTxt.setBounds(140, 180, 140, 30);
		renterSidePanel.add(lnameTxt);
		
		phoneTxt = new JTextField();
		phoneTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		phoneTxt.setColumns(10);
		phoneTxt.setBounds(140, 220, 140, 30);
		renterSidePanel.add(phoneTxt);
		
		addressTxt = new JTextField();
		addressTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addressTxt.setColumns(10);
		addressTxt.setBounds(140, 260, 140, 30);
		renterSidePanel.add(addressTxt);
		
		JPanel createRenter = new JPanel();
		createRenter.setBounds(50, 410, 100, 40);
		renterSidePanel.add(createRenter);
		createRenter.setLayout(null);
	
		JLabel smallC = new JLabel("");
		smallC.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/smallCreate.png")));
		smallC.setBounds(10, 5, 80, 30);
		createRenter.add(smallC);
		
		JLabel bigC = new JLabel("");
		bigC.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/bigCreate.png")));
		bigC.setBounds(0, 0, 100, 40);
		createRenter.add(bigC);
		
		JPanel editRenter = new JPanel();
		editRenter.setBounds(150, 410, 100, 40);
		renterSidePanel.add(editRenter);
		editRenter.setLayout(null);
		
		JLabel smallE = new JLabel("");
		smallE.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/smallEdit.png")));
		smallE.setBounds(10, 5, 80, 30);
		editRenter.add(smallE);
		
		JLabel bigE = new JLabel("");
		bigE.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/bigEdit.png")));
		bigE.setBounds(0, 0, 100, 40);
		editRenter.add(bigE);
		
		JPanel deleteRenter = new JPanel();
		deleteRenter.setBounds(110, 450, 100, 40);
		renterSidePanel.add(deleteRenter);
		deleteRenter.setLayout(null);
		
		JLabel smallD = new JLabel("");
		smallD.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/smallDelete.png")));
		smallD.setBounds(10, 5, 80, 30);
		deleteRenter.add(smallD);
		
		JLabel bigD = new JLabel("");
		bigD.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/bigDelete.png")));
		bigD.setBounds(0, 0, 100, 40);
		deleteRenter.add(bigD);
		
		bigD.setVisible(false);
		bigE.setVisible(false);
		bigC.setVisible(false);
		
		createRenter.addMouseListener(new IconMouseAdapter(createRenter, smallC, bigC) {
			@Override
			public void mouseClicked(MouseEvent e) {
				smallC.setVisible(false);
				bigC.setVisible(true);
				//execute create method
				create();
			}
		});
		
		editRenter.addMouseListener(new IconMouseAdapter(editRenter, smallE, bigE) {
			@Override
			public void mouseClicked(MouseEvent e) {
				smallE.setVisible(false);
				bigE.setVisible(true);
				//execute create method
				edit();
			}
		});
		
		deleteRenter.addMouseListener(new IconMouseAdapter(deleteRenter, smallD, bigD) {
			@Override
			public void mouseClicked(MouseEvent e) {
				smallD.setVisible(false);
				bigD.setVisible(true);
				//execute create method
				delete();
			}
		});
	

	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://FELIX;databaseName=AdaMayumi;encrypt=true;trustServerCertificate=true;";
		String username = "sa";
		String password = "asd";
		connection = DriverManager.getConnection(url, username, password);
		System.out.println("Renter Table Connected");
		loadRecords();
	} 
	catch (SQLException e){
		System.out.println("error");
		e.printStackTrace();
	}
	}
	
	private void loadRecords() {
		renterModel.setRowCount(0); // Clear existing table data
	    try {
	    
	        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Renter");
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            int id = resultSet.getInt("renter_id");
	            String fname = resultSet.getString("renter_fname");
	            String lname = resultSet.getString("renter_lname");
	            String phone = resultSet.getString("renter_phone"); // Rename this variable
	            String address = resultSet.getString("renter_address");	             
	            renterModel.addRow(new Object[]{id, fname, lname, phone, address});
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Failed to load records.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void create() {
		
		String fname = fnameTxt.getText();
		String lname = lnameTxt.getText();
		String phone = phoneTxt.getText();
		String address = addressTxt.getText();
		
		
		try {
			pst = connection.prepareStatement("insert into Renter(renter_fname, renter_lname, renter_phone, renter_address)values(?,?,?,?)");
			
			pst.setObject(1, fname);
			pst.setObject(2, lname);
			pst.setObject(3, phone);
			pst.setObject(4, address);
			pst.executeUpdate();
			loadRecords();
		}
		
		catch(SQLException e1) {
			
			e1.printStackTrace();
		}
	}
	
	private void edit() {
		
		int selectedRow = renterTable.getSelectedRow();
		
		if (selectedRow != -1) {
			
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to edit this row?", "Warning!", JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION);
			
				if(reply == JOptionPane.YES_OPTION) {
			 
					String fname = fnameTxt.getText();
					String lname = lnameTxt.getText();
					String phone = phoneTxt.getText();
					String address = addressTxt.getText();
			 
					try {
						Object id = renterTable.getValueAt(selectedRow, 0);
					pst = connection.prepareStatement("UPDATE Renter SET renter_fname = ?, renter_lname = ?, renter_phone = ?, renter_address = ? WHERE renter_id = ?");
					pst.setObject(1, fname);
					pst.setObject(2, lname);
					pst.setObject(3, phone);
			        pst.setObject(4, address);
			        pst.setObject(5, id);
				 
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record updated successfully.");
					loadRecords();

					}
					catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
		else {
			JOptionPane.showMessageDialog(null, "Please select a row to update.");
		}
	}
	
	private void delete() {
		int i = renterTable.getSelectedRow();
		if(i != -1) {
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Warning!", JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION) {
			
				try {
			
					Object id = renterModel.getValueAt(i, 0);
					renterModel.removeRow(i);
					System.out.println(id);
			
					pst = connection.prepareStatement("DELETE FROM Renter WHERE renter_id = " + id + ";");
					pst.executeUpdate();
					loadRecords();
				}
				catch(SQLException e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "This renter is currently renting a vehicle.", "Failed to delete renter", JOptionPane.ERROR_MESSAGE);
					loadRecords();
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Please select a row to delete.");
		}
	}
	
	private class IconMouseAdapter extends MouseAdapter {
		JPanel panel;
		JLabel small;
		JLabel big;
		public IconMouseAdapter(JPanel panel, JLabel small, JLabel big) {
			this.panel = panel;
			this.small = small;
			this.big = big;
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			small.setVisible(false);
			big.setVisible(true);
		}
		public void mouseExited(MouseEvent e) {
			small.setVisible(true);
			big.setVisible(false);
		}
		public void mousePressed(MouseEvent e) {
			small.setVisible(true);
			big.setVisible(false);
		}
	}
}
