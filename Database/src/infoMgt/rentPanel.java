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
import javax.swing.table.TableModel;

public class rentPanel extends JPanel {
	
	private JTable rentTable;
	private JTextField rentIDTxt;
	private JTextField renterIDTxt;
	private JTextField vehicleIDTxt;
	private JTextField startDateTxt;
	private JTextField endDateTxt;
	private DefaultTableModel rentModel;
	private Connection connection;
	private PreparedStatement pst;
	private JTextField priceTxt;

	
	public rentPanel() throws ClassNotFoundException {

		setBounds(0, 0, 915, 540);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		rentModel = new DefaultTableModel();
		rentModel.addColumn("ID");
		rentModel.addColumn("Renter ID");
		rentModel.addColumn("Vehicle ID");
		rentModel.addColumn("Start Date");
		rentModel.addColumn("End Date");
		rentModel.addColumn("Price");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 90, 585, 440);
		add(scrollPane);
		rentTable = new JTable(rentModel);
		scrollPane.setViewportView(rentTable);
		rentTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rentTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedrow = rentTable.getSelectedRow();
				Object id = rentModel.getValueAt(selectedrow, 0);
				Object renter = rentModel.getValueAt(selectedrow, 1);
				Object vehicle = rentModel.getValueAt(selectedrow, 2);
				Object start = rentModel.getValueAt(selectedrow, 3);
				Object end = rentModel.getValueAt(selectedrow, 4);
				Object price = rentModel.getValueAt(selectedrow, 5);
				
				rentIDTxt.setText(id.toString());
				renterIDTxt.setText(renter.toString());
				vehicleIDTxt.setText(vehicle.toString());
				startDateTxt.setText(start.toString());
				endDateTxt.setText(end.toString());
				priceTxt.setText(price.toString());
			}
		});
		
		JPanel rentSidePanel = new JPanel();
		rentSidePanel.setBorder(new LineBorder(new Color(128, 0, 128), 3));
		rentSidePanel.setBounds(605, 10, 300, 520);
		add(rentSidePanel);
		rentSidePanel.setLayout(null);
		
		JLabel rentHeader = new JLabel("RENT");
		rentHeader.setHorizontalAlignment(SwingConstants.CENTER);
		rentHeader.setFont(new Font("Tahoma", Font.BOLD, 32));
		rentHeader.setBounds(10, 10, 280, 70);
		rentSidePanel.add(rentHeader);
		
		JLabel rentID = new JLabel("ID:");
		rentID.setForeground(Color.BLACK);
		rentID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rentID.setBounds(20, 105, 35, 15);
		rentSidePanel.add(rentID);
		
		rentIDTxt = new JTextField();
		rentIDTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rentIDTxt.setEditable(false);
		rentIDTxt.setColumns(10);
		rentIDTxt.setBounds(140, 100, 140, 30);
		rentSidePanel.add(rentIDTxt);
		
		renterIDTxt = new JTextField();
		renterIDTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		renterIDTxt.setColumns(10);
		renterIDTxt.setBounds(140, 140, 140, 30);
		rentSidePanel.add(renterIDTxt);
		
		vehicleIDTxt = new JTextField();
		vehicleIDTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		vehicleIDTxt.setColumns(10);
		vehicleIDTxt.setBounds(140, 180, 140, 30);
		rentSidePanel.add(vehicleIDTxt);
		
		startDateTxt = new JTextField();
		startDateTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		startDateTxt.setColumns(10);
		startDateTxt.setBounds(140, 220, 140, 30);
		rentSidePanel.add(startDateTxt);
		
		endDateTxt = new JTextField();
		endDateTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		endDateTxt.setColumns(10);
		endDateTxt.setBounds(140, 260, 140, 30);
		rentSidePanel.add(endDateTxt);
		
		priceTxt = new JTextField();
		priceTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceTxt.setColumns(10);
		priceTxt.setBounds(140, 300, 140, 30);
		rentSidePanel.add(priceTxt);
		
		JLabel renterIDLbl = new JLabel("Renter ID:");
		renterIDLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		renterIDLbl.setBounds(20, 145, 70, 15);
		rentSidePanel.add(renterIDLbl);
		
		JLabel vehicleIDLbl = new JLabel("Vehicle ID:");
		vehicleIDLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		vehicleIDLbl.setBounds(20, 185, 70, 15);
		rentSidePanel.add(vehicleIDLbl);
		
		JLabel startDateLbl = new JLabel("Start date:");
		startDateLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		startDateLbl.setBounds(20, 225, 70, 15);
		rentSidePanel.add(startDateLbl);
		
		JLabel endDateLbl = new JLabel("End date:");
		endDateLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		endDateLbl.setBounds(20, 265, 65, 15);
		rentSidePanel.add(endDateLbl);
		
		JLabel priceLbl = new JLabel("Price:");
		priceLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceLbl.setBounds(20, 305, 65, 15);
		rentSidePanel.add(priceLbl);
		
		JPanel createRent = new JPanel();
		createRent.setBounds(50, 410, 100, 40);
		rentSidePanel.add(createRent);
		createRent.setLayout(null);
		
		JLabel smallC = new JLabel("");
		smallC.setIcon(new ImageIcon(rentPanel.class.getResource("/img/smallCreate.png")));
		smallC.setBounds(10, 5, 80, 30);
		createRent.add(smallC);
		
		JLabel bigC = new JLabel("");
		bigC.setIcon(new ImageIcon(rentPanel.class.getResource("/img/bigCreate.png")));
		bigC.setBounds(0, 0, 100, 40);
		createRent.add(bigC);
		bigC.setVisible(false);
		
		JPanel editRent = new JPanel();
		editRent.setBounds(150, 410, 100, 40);
		rentSidePanel.add(editRent);
		editRent.setLayout(null);
		
		JLabel smallE = new JLabel("");
		smallE.setIcon(new ImageIcon(rentPanel.class.getResource("/img/smallEdit.png")));
		smallE.setBounds(10, 5, 80, 30);
		editRent.add(smallE);
		
		JLabel bigE = new JLabel("");
		bigE.setIcon(new ImageIcon(rentPanel.class.getResource("/img/bigEdit.png")));
		bigE.setBounds(0, 0, 100, 40);
		editRent.add(bigE);
		bigE.setVisible(false);
		
		JPanel deleteRent = new JPanel();
		deleteRent.setBounds(110, 450, 100, 40);
		rentSidePanel.add(deleteRent);
		deleteRent.setLayout(null);
		
		JLabel smallD = new JLabel("");
		smallD.setIcon(new ImageIcon(rentPanel.class.getResource("/img/smallDelete.png")));
		smallD.setBounds(10, 5, 80, 30);
		deleteRent.add(smallD);
		
		JLabel bigD = new JLabel("");
		bigD.setIcon(new ImageIcon(rentPanel.class.getResource("/img/bigDelete.png")));
		bigD.setBounds(0, 0, 100, 40);
		deleteRent.add(bigD);
		bigD.setVisible(false);
		
		createRent.addMouseListener(new IconMouseAdapter(createRent, smallC, bigC) {
			@Override
			public void mouseClicked(MouseEvent e) {
				smallC.setVisible(false);
				bigC.setVisible(true);
				//execute create method
				create();
			}
		});
		
		editRent.addMouseListener(new IconMouseAdapter(editRent, smallE, bigE) {
			@Override
			public void mouseClicked(MouseEvent e) {
				smallE.setVisible(false);
				bigE.setVisible(true);
				//execute create method
				edit();
			}
		});
		
		deleteRent.addMouseListener(new IconMouseAdapter(deleteRent, smallD, bigD) {
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
			System.out.println("Rent Table Connected");
			loadRecords();
		} 
		catch (SQLException e){
			System.out.println("error");
			e.printStackTrace();
		}
		
	}
	
	private void loadRecords() {
		rentModel.setRowCount(0); // Clear existing table data
	    try {
	    
	        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Rent");
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            int id = resultSet.getInt("rent_id");
	            int renterID = resultSet.getInt("renter_id");
	            int vehicleID = resultSet.getInt("vehicle_id");
	            String startDate = resultSet.getString("start_date"); // Rename this variable
	            String endDate = resultSet.getString("end_date");
	            float price = resultSet.getFloat("price");
	            rentModel.addRow(new Object[]{id, renterID, vehicleID, startDate, endDate, price});
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Failed to load records.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void create() {
		
			try {
				pst = connection.prepareStatement("insert into Rent(renter_id, vehicle_id, start_date, end_date, price)values(?,?,?,?,?)");
			
				pst.setObject(1, renterIDTxt.getText());
				pst.setObject(2, vehicleIDTxt.getText());
				pst.setObject(3, startDateTxt.getText());
				pst.setObject(4, endDateTxt.getText());
				pst.setObject(5, priceTxt.getText());
				pst.executeUpdate();
				loadRecords();
			}
			catch(SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Failed to create rent. Invalid Renter ID or Vehicle ID", "Error", JOptionPane.ERROR_MESSAGE);							
			}
		}
	
	private void edit() {
		
		int selectedRow = rentTable.getSelectedRow();
		
		if (selectedRow != -1) {
			
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to edit this record?", "Warning!", JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION);
			
				if(reply == JOptionPane.YES_OPTION) {
			 
					try {
						Object id = rentTable.getValueAt(selectedRow, 0);
					pst = connection.prepareStatement("UPDATE Rent SET renter_id = ?, vehicle_id = ?, start_date = ?, end_date = ?, price = ? WHERE rent_id = ?");
					pst.setObject(1, renterIDTxt.getText());
					pst.setObject(2, vehicleIDTxt.getText());
					pst.setObject(3, startDateTxt.getText());
			        pst.setObject(4, endDateTxt.getText());
			        pst.setObject(5, priceTxt.getText());
			        pst.setObject(6, id);
				 
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
		int i = rentTable.getSelectedRow();
		if(i != -1) {
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this row?", "Warning!", JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION) {
			
				try {
			
					Object id = rentModel.getValueAt(i, 0);
					rentModel.removeRow(i);
					System.out.println(id);
			
					pst = connection.prepareStatement("DELETE FROM Rent WHERE rent_id = " + id + ";");
					pst.executeUpdate();
					loadRecords();
				}
				catch(SQLException e2) {
					e2.printStackTrace();
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
