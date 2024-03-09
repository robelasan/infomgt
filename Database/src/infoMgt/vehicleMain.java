package infoMgt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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


public class vehicleMain {

	private JFrame frame;
	private JTable vehicleTable;
	private JPanel vehicleSidePanel;
	private JTextField idTxt;
	private JTextField modelTxt;
	private JTextField seatsTxt;
	private JTable renterTable;
	private JTextField renterIDTxt;
	private JTextField renterNameTxt;
	private JTextField phoneTxt;
	private JTextField vehicle_idTxt;
	private JTextField startDateTxt;
	private JTextField endDateTxt;
	private JComboBox categoryDrop;
	private JComboBox typeDrop;
	private JComboBox statusDrop;
	private Connection connection;
	private DefaultTableModel model;
	private PreparedStatement pst;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vehicleMain window = new vehicleMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public vehicleMain() throws ClassNotFoundException, SQLException {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(64, 0, 64));
		frame.setBounds(100, 100, 975, 628);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JPanel vehiclePanel = new JPanel();
		vehiclePanel.setBackground(new Color(255, 255, 255));
		vehiclePanel.setBounds(125, 110, 835, 480);
		frame.getContentPane().add(vehiclePanel);
		vehiclePanel.setLayout(null);
	
		model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("category");
		model.addColumn("type");
		model.addColumn("model");
		model.addColumn("seats");
		model.addColumn("status");
		vehicleTable = new JTable(model);
		vehicleTable.setBounds(10, 90, 565, 380);
		vehicleTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		vehiclePanel.add(vehicleTable);
		
		vehicleSidePanel = new JPanel();
		vehicleSidePanel.setBorder(new LineBorder(new Color(128, 0, 128), 3, true));
		vehicleSidePanel.setBounds(585, 10, 239, 459);
		vehiclePanel.add(vehicleSidePanel);
		vehicleSidePanel.setLayout(null);
		
		JLabel vehicleHeader = new JLabel("VEHICLE");
		vehicleHeader.setFont(new Font("Tahoma", Font.BOLD, 20));
		vehicleHeader.setHorizontalAlignment(SwingConstants.CENTER);
		vehicleHeader.setBounds(10, 10, 220, 60);
		vehicleSidePanel.add(vehicleHeader);
		
		JLabel vehicleID = new JLabel("ID:");
		vehicleID.setForeground(new Color(0, 0, 0));
		vehicleID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		vehicleID.setBounds(10, 95, 35, 15);
		vehicleSidePanel.add(vehicleID);
		
		JLabel categoryLbl = new JLabel("Category:");
		categoryLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		categoryLbl.setBounds(10, 125, 70, 20);
		vehicleSidePanel.add(categoryLbl);
		
		JLabel typeLbl = new JLabel("Type:");
		typeLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		typeLbl.setBounds(10, 160, 45, 20);
		vehicleSidePanel.add(typeLbl);
		
		JLabel modelLbl = new JLabel("Model:");
		modelLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		modelLbl.setBounds(10, 200, 45, 15);
		vehicleSidePanel.add(modelLbl);
		
		JLabel seatsLbl = new JLabel("Seats:");
		seatsLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		seatsLbl.setBounds(10, 235, 45, 15);
		vehicleSidePanel.add(seatsLbl);
		
		JLabel statusLbl = new JLabel("Status:");
		statusLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		statusLbl.setBounds(10, 270, 45, 15);
		vehicleSidePanel.add(statusLbl);
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		idTxt.setBounds(90, 90, 140, 30);
		vehicleSidePanel.add(idTxt);
		idTxt.setColumns(10);
		
		modelTxt = new JTextField();
		modelTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		modelTxt.setColumns(10);
		modelTxt.setBounds(90, 195, 140, 30);
		vehicleSidePanel.add(modelTxt);
		
		categoryDrop = new JComboBox();
		categoryDrop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		categoryDrop.setBounds(90, 125, 140, 30);
		vehicleSidePanel.add(categoryDrop);
		categoryDrop.addItem("SEDAN");
		categoryDrop.addItem("SUV");
		categoryDrop.addItem("VAN");
		
		typeDrop = new JComboBox();
		typeDrop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		typeDrop.setBounds(90, 160, 140, 30);
		vehicleSidePanel.add(typeDrop);
		typeDrop.addItem("Manual");
		typeDrop.addItem("Automatic");
		
		statusDrop = new JComboBox();
		statusDrop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		statusDrop.setBounds(90, 265, 140, 30);
		vehicleSidePanel.add(statusDrop);
		statusDrop.addItem("Available");
		statusDrop.addItem("Rented");
		
		
		seatsTxt = new JTextField();
		seatsTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		seatsTxt.setColumns(10);
		seatsTxt.setBounds(90, 230, 140, 30);
		vehicleSidePanel.add(seatsTxt);
		
		JPanel createVehicle = new JPanel();
		createVehicle.setBounds(20, 355, 100, 40);
		vehicleSidePanel.add(createVehicle);
		createVehicle.setLayout(null);
		
		JLabel bigCreate = new JLabel("");
		bigCreate.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/bigCreate.png")));
		bigCreate.setBounds(0, 0, 100, 40);
		createVehicle.add(bigCreate);
		bigCreate.setVisible(false);
		
		JLabel smallCreate = new JLabel("");
		smallCreate.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/smallCreate.png")));
		smallCreate.setBounds(10, 5, 80, 30);
		createVehicle.add(smallCreate);
		
		JPanel editVehicle = new JPanel();
		editVehicle.setLayout(null);
		editVehicle.setBounds(120, 355, 100, 40);
		vehicleSidePanel.add(editVehicle);
		
		JLabel smallEdit = new JLabel("");
		smallEdit.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/smallEdit.png")));
		smallEdit.setBounds(10, 5, 80, 30);
		editVehicle.add(smallEdit);
		
		JLabel bigEdit = new JLabel("");
		bigEdit.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/bigEdit.png")));
		bigEdit.setBounds(0, 0, 100, 40);
		editVehicle.add(bigEdit);
		bigEdit.setVisible(false);
		
		editVehicle.addMouseListener(new IconMouseAdapter(editVehicle, smallEdit, bigEdit) {
			@Override
			public void mouseClicked(MouseEvent e) {
				smallEdit.setVisible(false);
				bigEdit.setVisible(true);
				
				edit();
			}
		});
		
		JPanel deleteVehicle = new JPanel();
		deleteVehicle.setLayout(null);
		deleteVehicle.setBounds(70, 400, 100, 40);
		vehicleSidePanel.add(deleteVehicle);	
		
		JLabel smallDelete = new JLabel("");
		smallDelete.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/smallDelete.png")));
		smallDelete.setBounds(10, 5, 80, 30);
		deleteVehicle.add(smallDelete);
		
		JLabel bigDelete = new JLabel("");
		bigDelete.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/bigDelete.png")));
		bigDelete.setBounds(0, 0, 100, 40);
		deleteVehicle.add(bigDelete);
		bigDelete.setVisible(false);
		
		deleteVehicle.addMouseListener(new IconMouseAdapter(deleteVehicle, smallDelete, bigDelete) {
			@Override
			public void mouseClicked(MouseEvent e) {
				smallDelete.setVisible(false);
				bigDelete.setVisible(true);

				delete();
			}
		});
		
		createVehicle.addMouseListener(new IconMouseAdapter(createVehicle, smallCreate, bigCreate) {
			@Override
			public void mouseClicked(MouseEvent e) {
				smallCreate.setVisible(false);
				bigCreate.setVisible(true);
				
				create();
			}
		});
		
		JComboBox sortDrop = new JComboBox();
		sortDrop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sortDrop.setBounds(457, 10, 118, 22);
		vehiclePanel.add(sortDrop);
		sortDrop.addItem("SEDAN");
		sortDrop.addItem("SUV");
		sortDrop.addItem("VAN");
		sortDrop.addItem("AVAILABLE");
		sortDrop.addItem("RENTED");
		
		JLabel sortLbl = new JLabel("SORT:");
		sortLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sortLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		sortLbl.setBounds(401, 15, 46, 14);
		vehiclePanel.add(sortLbl);
		
		JLabel idHeader = new JLabel("ID");
		idHeader.setBounds(49, 65, 46, 14);
		vehiclePanel.add(idHeader);
		
		JLabel categoryHeader = new JLabel("Category");
		categoryHeader.setBounds(120, 65, 56, 14);
		vehiclePanel.add(categoryHeader);
		
		JLabel typeHeader = new JLabel("Type");
		typeHeader.setBounds(227, 65, 46, 14);
		vehiclePanel.add(typeHeader);
		
		JLabel modelHeader = new JLabel("Model");
		modelHeader.setBounds(320, 65, 46, 14);
		vehiclePanel.add(modelHeader);
		
		JLabel seatsHeader = new JLabel("Seats");
		seatsHeader.setBounds(418, 65, 46, 14);
		vehiclePanel.add(seatsHeader);
		
		JLabel statusHeader = new JLabel("Status");
		statusHeader.setBounds(508, 65, 46, 14);
		vehiclePanel.add(statusHeader);
		
		JPanel renterPanel = new JPanel();
		renterPanel.setBackground(new Color(255, 255, 255));
		renterPanel.setBounds(125, 110, 835, 480);
		frame.getContentPane().add(renterPanel);
		renterPanel.setLayout(null);
		
		renterTable = new JTable();
		renterTable.setBounds(10, 44, 565, 426);
		renterPanel.add(renterTable);
		
		JPanel renterSidePanel = new JPanel();
		renterSidePanel.setBorder(new LineBorder(new Color(128, 0, 128), 3));
		renterSidePanel.setBounds(585, 10, 239, 459);
		renterPanel.add(renterSidePanel);
		renterSidePanel.setLayout(null);
		
		JLabel renterHeader = new JLabel("RENTER");
		renterHeader.setHorizontalAlignment(SwingConstants.CENTER);
		renterHeader.setFont(new Font("Tahoma", Font.BOLD, 20));
		renterHeader.setBounds(10, 10, 220, 60);
		renterSidePanel.add(renterHeader);
		
		JLabel renterID = new JLabel("ID:");
		renterID.setForeground(Color.BLACK);
		renterID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		renterID.setBounds(10, 95, 35, 15);
		renterSidePanel.add(renterID);
		
		JLabel nameLbl = new JLabel("Name:");
		nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLbl.setBounds(10, 130, 46, 15);
		renterSidePanel.add(nameLbl);
		
		JLabel phoneLbl = new JLabel("Phone:");
		phoneLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		phoneLbl.setBounds(10, 165, 46, 15);
		renterSidePanel.add(phoneLbl);
		
		JLabel vehicle_id = new JLabel("Vehicle ID:");
		vehicle_id.setFont(new Font("Tahoma", Font.PLAIN, 14));
		vehicle_id.setBounds(10, 200, 65, 15);
		renterSidePanel.add(vehicle_id);
		
		JLabel startDate = new JLabel("Start Date:");
		startDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		startDate.setBounds(10, 235, 70, 15);
		renterSidePanel.add(startDate);
		
		JLabel endDate = new JLabel("End Date:");
		endDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		endDate.setBounds(10, 270, 70, 15);
		renterSidePanel.add(endDate);
		
		renterIDTxt = new JTextField();
		renterIDTxt.setBounds(90, 90, 140, 30);
		renterSidePanel.add(renterIDTxt);
		renterIDTxt.setColumns(10);
		
		renterNameTxt = new JTextField();
		renterNameTxt.setColumns(10);
		renterNameTxt.setBounds(90, 125, 140, 30);
		renterSidePanel.add(renterNameTxt);
		
		phoneTxt = new JTextField();
		phoneTxt.setColumns(10);
		phoneTxt.setBounds(90, 160, 140, 30);
		renterSidePanel.add(phoneTxt);
		
		vehicle_idTxt = new JTextField();
		vehicle_idTxt.setColumns(10);
		vehicle_idTxt.setBounds(90, 195, 140, 30);
		renterSidePanel.add(vehicle_idTxt);
		
		startDateTxt = new JTextField();
		startDateTxt.setColumns(10);
		startDateTxt.setBounds(90, 230, 140, 30);
		renterSidePanel.add(startDateTxt);
		
		endDateTxt = new JTextField();
		endDateTxt.setColumns(10);
		endDateTxt.setBounds(90, 265, 140, 30);
		renterSidePanel.add(endDateTxt);
		
		JPanel createRent = new JPanel();
		createRent.setBounds(20, 355, 100, 40);
		renterSidePanel.add(createRent);
		createRent.setLayout(null);
		
		JLabel smallC = new JLabel("");
		smallC.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/smallCreate.png")));
		smallC.setBounds(10, 5, 80, 30);
		createRent.add(smallC);
		
		JLabel bigC = new JLabel("");
		bigC.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/bigCreate.png")));
		bigC.setBounds(0, 0, 100, 40);
		createRent.add(bigC);
		
		JPanel editRent = new JPanel();
		editRent.setBounds(120, 355, 100, 40);
		renterSidePanel.add(editRent);
		editRent.setLayout(null);
		
		JLabel smallE = new JLabel("");
		smallE.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/smallEdit.png")));
		smallE.setBounds(10, 5, 80, 30);
		editRent.add(smallE);
		
		JLabel bigE = new JLabel("");
		bigE.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/bigEdit.png")));
		bigE.setBounds(0, 0, 100, 40);
		editRent.add(bigE);
		
		JPanel deleteRent = new JPanel();
		deleteRent.setBounds(70, 400, 100, 40);
		renterSidePanel.add(deleteRent);
		deleteRent.setLayout(null);
		
		JLabel smallD = new JLabel("");
		smallD.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/smallDelete.png")));
		smallD.setBounds(10, 5, 80, 30);
		deleteRent.add(smallD);
		
		JLabel bigD = new JLabel("");
		bigD.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/bigDelete.png")));
		bigD.setBounds(0, 0, 100, 40);
		deleteRent.add(bigD);
		
		bigD.setVisible(false);
		bigE.setVisible(false);
		bigC.setVisible(false);
		
		deleteRent.addMouseListener(new IconMouseAdapter(deleteRent, smallD, bigD) {
			@Override
			public void mouseClicked(MouseEvent e) {
				smallD.setVisible(false);
				bigD.setVisible(true);
				//execute create method
			}
		});
		
		createRent.addMouseListener(new IconMouseAdapter(createRent, smallC, bigC) {
			@Override
			public void mouseClicked(MouseEvent e) {
				smallC.setVisible(false);
				bigC.setVisible(true);
				//execute create method
			}
		});
		
		editRent.addMouseListener(new IconMouseAdapter(editRent, smallE, bigE) {
			@Override
			public void mouseClicked(MouseEvent e) {
				smallE.setVisible(false);
				bigE.setVisible(true);
				//execute create method
			}
		});
		
		JComboBox sortRenter = new JComboBox();
		sortRenter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sortRenter.setBounds(457, 10, 118, 22);
		renterPanel.add(sortRenter);
		sortRenter.addItem("ID");
		
		
		JLabel sortRenterLbl = new JLabel("SORT:");
		sortRenterLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		sortRenterLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sortRenterLbl.setBounds(401, 15, 46, 14);
		renterPanel.add(sortRenterLbl);
		
		JLabel companyLbl = new JLabel("ADA MAYUMI TRANSPORT AND LOGISTICS MONITORING SYSTEM");
		companyLbl.setForeground(new Color(255, 255, 255));
		companyLbl.setFont(new Font("Tw Cen MT", Font.BOLD, 28));
		companyLbl.setBounds(158, 15, 791, 78);
		frame.getContentPane().add(companyLbl);
		
		
		JLabel logoLbl = new JLabel("");
		logoLbl.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/logo.png")));
		logoLbl.setBounds(10, 11, 138, 78);
		frame.getContentPane().add(logoLbl);
		
		JPanel vehicleTabPanel = new JPanel();
		vehicleTabPanel.setBackground(new Color(255, 255, 255));
		vehicleTabPanel.setBounds(0, 110, 125, 50);
		frame.getContentPane().add(vehicleTabPanel);
		vehicleTabPanel.setLayout(null);
		vehicleTabPanel.setOpaque(false);
		
		JPanel renterTabPanel = new JPanel();
		renterTabPanel.setBackground(new Color(255, 255, 255));
		renterTabPanel.setBounds(0, 160, 125, 50);
		frame.getContentPane().add(renterTabPanel);
		renterTabPanel.setLayout(null);
		renterTabPanel.setOpaque(false);
		
		JLabel vehicleTab = new JLabel("VEHICLE");
		vehicleTab.setBounds(0, 0, 125, 50);
		vehicleTabPanel.add(vehicleTab);
		vehicleTab.setForeground(new Color(0, 0, 0));
		vehicleTab.setFont(new Font("Lucida Fax", Font.BOLD, 21));
		vehicleTab.setHorizontalAlignment(SwingConstants.CENTER);
		vehicleTab.addMouseListener(new TabButton(vehicleTab,vehicleTabPanel){
			@Override
			public void mouseClicked(MouseEvent e) {
				vehicleTab.setForeground(Color.WHITE);
				renterPanel.setVisible(false);
				vehiclePanel.setVisible(true);
				renterTabPanel.setOpaque(false);
				vehicleTabPanel.setOpaque(true);
				vehicleTabPanel.setBackground(Color.decode("#470E8E"));
		}
	});
		
		
		JLabel renterTab = new JLabel("RENTER");
		renterTab.setBackground(new Color(255, 255, 255));
		renterTab.setBounds(0, 0, 125, 50);
		renterTabPanel.add(renterTab);
		renterTab.setForeground(new Color(0, 0, 0));
		renterTab.setHorizontalAlignment(SwingConstants.CENTER);
		renterTab.setFont(new Font("Lucida Fax", Font.BOLD, 21));
		renterTab.addMouseListener(new TabButton(renterTab,renterTabPanel){
			@Override
			public void mouseClicked(MouseEvent e) {
				renterTab.setForeground(Color.WHITE);
				vehiclePanel.setVisible(false);
				renterPanel.setVisible(true);
				renterTabPanel.setOpaque(true);
				renterTabPanel.setBackground(Color.decode("#470E8E"));
				vehicleTabPanel.setOpaque(false);
				
		}
	});
		JLabel bgLbl = new JLabel("");
		bgLbl.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/background.png")));
		bgLbl.setBounds(0, 0, 959, 589);
		frame.getContentPane().add(bgLbl);
		
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://FELIX;databaseName=AdaMayumi;encrypt=true;trustServerCertificate=true;";
			String username = "sa";
			String password = "asd";
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			loadRecords();
		} 
		catch (SQLException e){
			System.out.println("error");
			e.printStackTrace();
		}
		
		
		
	}
	
	private void loadRecords() {
	    model.setRowCount(0); // Clear existing table data
	    try {
	        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Vehicle");
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            int id = resultSet.getInt("vehicle_id");
	            String category = resultSet.getString("vehicle_category");
	            String type = resultSet.getString("vehicle_type");
	            String vehicleModel = resultSet.getString("vehicle_model"); // Rename this variable
	            String seats = resultSet.getString("seats");	             
	            int availability = resultSet.getInt("status");
	            model.addRow(new Object[]{id, category, type, vehicleModel, seats, availability});
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(frame, "Failed to load records.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void create() {
		String modeltxt = modelTxt.getText();
		String seats = seatsTxt.getText();
		Object rentstat = statusDrop.getSelectedItem();
		boolean rented = false;
		if(rentstat.equals("Rented")) {
			rented = true;
		}
		else {
			rented = false;
		}
		
		
		try {
			pst = connection.prepareStatement("insert into Vehicle(vehicle_category, vehicle_type, vehicle_model, seats, status)values(?,?,?,?,?)");
			
			pst.setObject(1, categoryDrop.getSelectedItem());
			pst.setObject(2, typeDrop.getSelectedItem());
			pst.setObject(3, modeltxt);
			pst.setObject(4, seats);
			pst.setObject(5, rented);
			pst.executeUpdate();
			loadRecords();
		}
		
		catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	private void delete() {
		int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this row?", "Warning!", JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION);
		if(reply == JOptionPane.YES_OPTION) {
			
		try {
			
			int i = vehicleTable.getSelectedRow();
			
			Object id = model.getValueAt(i, 0);
			model.removeRow(i);
			System.out.println(id);
			
			pst = connection.prepareStatement("DELETE FROM Vehicle WHERE vehicle_id = " + id + ";");
			pst.executeUpdate();
			loadRecords();
		}
		catch(SQLException e2) {
			e2.printStackTrace();
		}
		}
	}
	
	private void edit() {
		int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to edit this row?", "Warning!", JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION);
		if(reply == JOptionPane.YES_OPTION) {
		int selectedRow = vehicleTable.getSelectedRow();
		
		 if (selectedRow != -1) {
			 
			 Object category = categoryDrop.getSelectedItem();
			 Object type = typeDrop.getSelectedItem();
			 String model = modelTxt.getText();
			 String seats = seatsTxt.getText();
			 Object status = statusDrop.getSelectedItem();
			 Object rentstat = statusDrop.getSelectedItem();
			 boolean rented = false;
			 if(rentstat.equals("Rented")) {
				 rented = true;
			 }
			 else {
				 rented = false;
			 }
			 
			 
			 try {
				Object id = vehicleTable.getValueAt(selectedRow, 0);
				pst = connection.prepareStatement("UPDATE Vehicle SET vehicle_category = ?, vehicle_type = ?, vehicle_model = ?, seats = ?, status = ? WHERE vehicle_id = ?");
				pst.setObject(1, category);
				pst.setObject(2, type);
				pst.setObject(3, model);
		        pst.setObject(4, seats);
		        pst.setObject(5, rented);
		        pst.setObject(6, id);
		       
				 
				 pst.executeUpdate();
				 JOptionPane.showMessageDialog(null, "Record updated successfully.");
				 loadRecords();

			 }
			 catch(SQLException e) {
				 e.printStackTrace();
			 }
		 }
		 else {
			 JOptionPane.showMessageDialog(null, "Please select a row to update.");
		 }
	}
	}
	
	private class TabButton extends MouseAdapter{
		JLabel label;
		JPanel panel;
		public TabButton(JLabel label, JPanel panel) {
			this.panel = panel;
			this.label = label;
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			label.setForeground(Color.WHITE);
		}
		public void mouseExited(MouseEvent e) {
			label.setForeground(Color.BLACK);
		}
		public void mousePressed(MouseEvent e) {
			label.setForeground(Color.BLACK);
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
	