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
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Toolkit;


public class vehicleMain {

	private JFrame frmAdaMayumiTransport;
	private JTable vehicleTable;
	private JPanel vehicleSidePanel;
	private JTextField idTxt;
	private JTextField modelTxt;
	private JTextField seatsTxt;
	private JComboBox categoryDrop;
	private JComboBox typeDrop;
	private JComboBox statusDrop;
	private JComboBox sortDrop;
	private Connection connection;
	private DefaultTableModel vehicleModel;
	private PreparedStatement pst;
	
	private JPanel vehiclePanel;
	private JPanel vehicleTabPanel;
	private JPanel renterTabPanel;
	private JPanel rentTabPanel;
	private JPanel historyTabPanel;
	private JPanel displayPanel;
	
	private renterPanel renterpanel;
	private rentPanel rentpanel;
	private historyPanel historypanel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vehicleMain window = new vehicleMain();
					window.frmAdaMayumiTransport.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public vehicleMain() throws ClassNotFoundException, SQLException {
		
		frmAdaMayumiTransport = new JFrame();
		frmAdaMayumiTransport.setIconImage(Toolkit.getDefaultToolkit().getImage(vehicleMain.class.getResource("/img/logo.png")));
		frmAdaMayumiTransport.getContentPane().setBackground(new Color(64, 0, 64));
		frmAdaMayumiTransport.setBounds(100, 100, 1080, 700);
		frmAdaMayumiTransport.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdaMayumiTransport.getContentPane().setLayout(null);
		frmAdaMayumiTransport.setLocationRelativeTo(null);
	
		renterpanel = new renterPanel();
		renterpanel.setSize(915, 540);
		rentpanel = new rentPanel();
		rentpanel.setSize(915, 540);
		historypanel = new historyPanel();
		historypanel.setSize(915, 540);
		
		vehicleModel = new DefaultTableModel();
		vehicleModel.addColumn("ID");
		vehicleModel.addColumn("Category");
		vehicleModel.addColumn("Type");
		vehicleModel.addColumn("Model");
		vehicleModel.addColumn("Seats");
		vehicleModel.addColumn("Status");
		
		displayPanel = new JPanel();
		displayPanel.setBounds(150, 120, 915, 540);
		frmAdaMayumiTransport.getContentPane().add(displayPanel);
		displayPanel.setLayout(null);
		
		vehiclePanel = new JPanel();
		vehiclePanel.setBounds(0, 0, 915, 540);
		displayPanel.add(vehiclePanel);
		vehiclePanel.setBackground(new Color(255, 255, 255));
		vehiclePanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 90, 585, 440);
		vehiclePanel.add(scrollPane);
		vehicleTable = new JTable(vehicleModel);
		vehicleTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedrow = vehicleTable.getSelectedRow();
				Object id = vehicleModel.getValueAt(selectedrow, 0);
				Object category = vehicleModel.getValueAt(selectedrow, 1);
				Object type = vehicleModel.getValueAt(selectedrow, 2);
				Object model = vehicleModel.getValueAt(selectedrow, 3);
				Object seats = vehicleModel.getValueAt(selectedrow, 4);
				Object status = vehicleModel.getValueAt(selectedrow, 5);
				
				idTxt.setText(id.toString());
				categoryDrop.setSelectedItem(category);
				typeDrop.setSelectedItem(type);
				modelTxt.setText(model.toString());
				seatsTxt.setText(seats.toString());
				statusDrop.setSelectedItem(status);
			}
		});
		scrollPane.setViewportView(vehicleTable);
		vehicleTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		vehicleSidePanel = new JPanel();
		vehicleSidePanel.setBorder(new LineBorder(new Color(128, 0, 128), 3, true));
		vehicleSidePanel.setBounds(605, 10, 300, 520);
		vehiclePanel.add(vehicleSidePanel);
		vehicleSidePanel.setLayout(null);
		
		JLabel vehicleHeader = new JLabel("VEHICLE");
		vehicleHeader.setFont(new Font("Tahoma", Font.BOLD, 32));
		vehicleHeader.setHorizontalAlignment(SwingConstants.CENTER);
		vehicleHeader.setBounds(10, 10, 280, 70);
		vehicleSidePanel.add(vehicleHeader);
		
		JLabel vehicleID = new JLabel("ID:");
		vehicleID.setForeground(new Color(0, 0, 0));
		vehicleID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		vehicleID.setBounds(20, 105, 35, 15);
		vehicleSidePanel.add(vehicleID);
		
		JLabel categoryLbl = new JLabel("Category:");
		categoryLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		categoryLbl.setBounds(20, 145, 70, 20);
		vehicleSidePanel.add(categoryLbl);
		
		JLabel typeLbl = new JLabel("Type:");
		typeLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		typeLbl.setBounds(20, 185, 45, 20);
		vehicleSidePanel.add(typeLbl);
		
		JLabel modelLbl = new JLabel("Model:");
		modelLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		modelLbl.setBounds(20, 225, 45, 15);
		vehicleSidePanel.add(modelLbl);
		
		JLabel seatsLbl = new JLabel("Seats:");
		seatsLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		seatsLbl.setBounds(20, 265, 45, 15);
		vehicleSidePanel.add(seatsLbl);
		
		JLabel statusLbl = new JLabel("Status:");
		statusLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		statusLbl.setBounds(20, 305, 45, 15);
		vehicleSidePanel.add(statusLbl);
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		idTxt.setBounds(140, 100, 140, 30);
		vehicleSidePanel.add(idTxt);
		idTxt.setColumns(10);
		
		modelTxt = new JTextField();
		modelTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		modelTxt.setColumns(10);
		modelTxt.setBounds(140, 220, 140, 30);
		vehicleSidePanel.add(modelTxt);
		
		categoryDrop = new JComboBox();
		categoryDrop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		categoryDrop.setBounds(140, 140, 140, 30);
		vehicleSidePanel.add(categoryDrop);
		categoryDrop.addItem("SEDAN");
		categoryDrop.addItem("SUV");
		categoryDrop.addItem("VAN");
		
		typeDrop = new JComboBox();
		typeDrop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		typeDrop.setBounds(140, 180, 140, 30);
		vehicleSidePanel.add(typeDrop);
		typeDrop.addItem("Manual");
		typeDrop.addItem("Automatic");
		
		statusDrop = new JComboBox();
		statusDrop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		statusDrop.setBounds(140, 300, 140, 30);
		vehicleSidePanel.add(statusDrop);
		statusDrop.addItem("Available");
		statusDrop.addItem("Rented");
		
		
		seatsTxt = new JTextField();
		seatsTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		seatsTxt.setColumns(10);
		seatsTxt.setBounds(140, 260, 140, 30);
		vehicleSidePanel.add(seatsTxt);
		
		JPanel createVehicle = new JPanel();
		createVehicle.setBounds(50, 410, 100, 40);
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
		editVehicle.setBounds(150, 410, 100, 40);
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
		deleteVehicle.setBounds(110, 450, 100, 40);
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
		
		sortDrop = new JComboBox();
		sortDrop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sortDrop.setBounds(475, 60, 120, 20);
		vehiclePanel.add(sortDrop);
		sortDrop.addItem("NONE");
		sortDrop.addItem("SEDAN");
		sortDrop.addItem("SUV");
		sortDrop.addItem("VAN");
		sortDrop.addItem("AVAILABLE");
		sortDrop.addItem("RENTED");
		
		vehicleTabPanel = new JPanel();
		vehicleTabPanel.setBackground(new Color(255, 255, 255));
		vehicleTabPanel.setBounds(0, 120, 150, 50);
		frmAdaMayumiTransport.getContentPane().add(vehicleTabPanel);
		vehicleTabPanel.setLayout(null);
		vehicleTabPanel.setOpaque(false);
		
		renterTabPanel = new JPanel();
		renterTabPanel.setBackground(new Color(255, 255, 255));
		renterTabPanel.setBounds(0, 170, 150, 50);
		frmAdaMayumiTransport.getContentPane().add(renterTabPanel);
		renterTabPanel.setLayout(null);
		renterTabPanel.setOpaque(false);
		
		JLabel vehicleTab = new JLabel("VEHICLE");
		vehicleTab.setHorizontalAlignment(SwingConstants.CENTER);
		vehicleTab.setBounds(0, 0, 150, 50);
		vehicleTabPanel.add(vehicleTab);
		vehicleTab.setForeground(new Color(0, 0, 0));
		vehicleTab.setFont(new Font("Lucida Fax", Font.BOLD, 21));
		vehicleTab.addMouseListener(new TabButton(vehicleTab){
			@Override
			public void mouseClicked(MouseEvent e) {
				vehicleTabPanel.setBackground(Color.decode("#470E8E"));
				vehicleTab.setForeground(Color.WHITE);
				menuClicked(vehiclePanel);				
				Opaque(vehicleTabPanel);			
		}
	});
		
		JLabel renterTab = new JLabel("RENTER");
		renterTab.setBackground(new Color(255, 255, 255));
		renterTab.setBounds(0, 0, 150, 50);
		renterTabPanel.add(renterTab);
		renterTab.setForeground(new Color(0, 0, 0));
		renterTab.setHorizontalAlignment(SwingConstants.CENTER);
		renterTab.setFont(new Font("Lucida Fax", Font.BOLD, 21));
		renterTab.addMouseListener(new TabButton(renterTab){
			@Override
			public void mouseClicked(MouseEvent e) {
				renterTab.setForeground(Color.WHITE);
				renterTabPanel.setBackground(Color.decode("#470E8E"));
				menuClicked(renterpanel);
				Opaque(renterTabPanel);
		}
	});
		
		rentTabPanel = new JPanel();
		rentTabPanel.setLayout(null);
		rentTabPanel.setOpaque(false);
		rentTabPanel.setBackground(Color.WHITE);
		rentTabPanel.setBounds(0, 220, 150, 50);
		frmAdaMayumiTransport.getContentPane().add(rentTabPanel);
		
		JLabel rentTab = new JLabel("RENT");
		rentTab.setHorizontalAlignment(SwingConstants.CENTER);
		rentTab.setForeground(Color.BLACK);
		rentTab.setFont(new Font("Lucida Fax", Font.BOLD, 21));
		rentTab.setBackground(Color.WHITE);
		rentTab.setBounds(0, 0, 150, 50);
		rentTabPanel.add(rentTab);
		
		historyTabPanel = new JPanel();
		historyTabPanel.setLayout(null);
		historyTabPanel.setOpaque(false);
		historyTabPanel.setBackground(Color.WHITE);
		historyTabPanel.setBounds(0, 270, 150, 50);
		frmAdaMayumiTransport.getContentPane().add(historyTabPanel);
		
		JLabel historyTab = new JLabel("HISTORY");
		historyTab.setHorizontalAlignment(SwingConstants.CENTER);
		historyTab.setForeground(Color.BLACK);
		historyTab.setFont(new Font("Lucida Fax", Font.BOLD, 21));
		historyTab.setBackground(Color.WHITE);
		historyTab.setBounds(0, 0, 150, 50);
		historyTabPanel.add(historyTab);
		historyTabPanel.setVisible(false);
		
		rentTab.addMouseListener(new TabButton(rentTab) {
			@Override
			public void mouseClicked(MouseEvent e) {
				rentTab.setForeground(Color.WHITE);
				rentTabPanel.setBackground(Color.decode("#470E8E"));
				menuClicked(rentpanel);
				Opaque(rentTabPanel);
			}
		});
		
		historyTab.addMouseListener(new TabButton(historyTab) {
			@Override
			public void mouseClicked(MouseEvent e) {
				historyTab.setForeground(Color.WHITE);
				historyTabPanel.setBackground(Color.decode("#470E8E"));
				menuClicked(historypanel);
				Opaque(historyTabPanel);
				
			}
		});
		
		
		JLabel logoLbl = new JLabel("");
		logoLbl.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/logo.png")));
		logoLbl.setBounds(15, 15, 138, 78);
		frmAdaMayumiTransport.getContentPane().add(logoLbl);
		
		JLabel companyLbl = new JLabel("ADA MAYUMI TRANSPORT VEHICLE MONITORING SYSTEM");
		companyLbl.setForeground(new Color(255, 255, 255));
		companyLbl.setFont(new Font("Tw Cen MT", Font.BOLD, 30));
		companyLbl.setBounds(160, 20, 870, 80);
		frmAdaMayumiTransport.getContentPane().add(companyLbl);
		
		JLabel bgLbl = new JLabel("");
		bgLbl.setIcon(new ImageIcon(vehicleMain.class.getResource("/img/background.png")));
		bgLbl.setBounds(0, 0, 1064, 661);
		frmAdaMayumiTransport.getContentPane().add(bgLbl);
		
		displayPanel.add(renterpanel);
		displayPanel.add(rentpanel);
		displayPanel.add(historypanel);
		
		menuClicked(vehiclePanel);
		
//		selectedRow();
		
		JButton sortBtn = new JButton("SORT");
		sortBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sortBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sort();
			}
		});
		sortBtn.setBounds(395, 60, 70, 20);
		vehiclePanel.add(sortBtn);
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://FELIX;databaseName=AdaMayumi;encrypt=true;trustServerCertificate=true;";
			String username = "sa";
			String password = "asd";
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Vehicle Table Connected");
			loadRecords();
		} 
		catch (SQLException e){
			System.out.println("error");
			e.printStackTrace();
		}
	}
	
	private void menuClicked(JPanel panel) {
		vehiclePanel.setVisible(false);
		renterpanel.setVisible(false);
		rentpanel.setVisible(false);
		historypanel.setVisible(false);
		
		panel.setVisible(true);
	}
	
	private void loadRecords() {
		vehicleModel.setRowCount(0); // Clear existing table data
	    try {
	        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Vehicle");
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            int id = resultSet.getInt("vehicle_id");
	            String category = resultSet.getString("vehicle_category");
	            String type = resultSet.getString("vehicle_type");
	            String vehiclemodel = resultSet.getString("vehicle_model"); // Rename this variable
	            String seats = resultSet.getString("seats");	             
	            String status = resultSet.getString("status");
	            vehicleModel.addRow(new Object[]{id, category, type, vehiclemodel, seats, status});
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(frmAdaMayumiTransport, "Failed to load records.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void create() {
		String modeltxt = modelTxt.getText();
		String seats = seatsTxt.getText();
		Object rentstat = statusDrop.getSelectedItem();
		
		
		try {
			pst = connection.prepareStatement("insert into Vehicle(vehicle_category, vehicle_type, vehicle_model, seats, status)values(?,?,?,?,?)");
			
			pst.setObject(1, categoryDrop.getSelectedItem());
			pst.setObject(2, typeDrop.getSelectedItem());
			pst.setObject(3, modeltxt);
			pst.setObject(4, seats);
			pst.setObject(5, rentstat);
			pst.executeUpdate();
			loadRecords();
		}
		
		catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	private void Opaque(JPanel panel) {
		vehicleTabPanel.setOpaque(false);
		renterTabPanel.setOpaque(false);
		rentTabPanel.setOpaque(false);
		historyTabPanel.setOpaque(false);
		
		panel.setOpaque(true);
	}
	
	private void delete() {
		int i = vehicleTable.getSelectedRow();
		if(i != -1) {
	
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Warning!", JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION) {
			
				try {
			
					Object id = vehicleModel.getValueAt(i, 0);
					vehicleModel.removeRow(i);
					System.out.println(id);
			
					pst = connection.prepareStatement("DELETE FROM Vehicle WHERE vehicle_id = " + id + ";");
					pst.executeUpdate();
					loadRecords();
				}
				catch(SQLException e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "This vehicle is currently being rented.", "Failed to delete vehicle", JOptionPane.ERROR_MESSAGE);
					loadRecords();
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Please select a row to delete.");
		}
	}
	
	private void edit() {
		
		int selectedRow = vehicleTable.getSelectedRow();
		
		if (selectedRow != -1) {
			
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to edit this row?", "Warning!", JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION);
			
				if(reply == JOptionPane.YES_OPTION) {
			 
					Object category = categoryDrop.getSelectedItem();
					Object type = typeDrop.getSelectedItem();
					String model = modelTxt.getText();
					String seats = seatsTxt.getText();
					Object status = statusDrop.getSelectedItem();
			 
					try {
						Object id = vehicleTable.getValueAt(selectedRow, 0);
					pst = connection.prepareStatement("UPDATE Vehicle SET vehicle_category = ?, vehicle_type = ?, vehicle_model = ?, seats = ?, status = ? WHERE vehicle_id = ?");
					pst.setObject(1, category);
					pst.setObject(2, type);
					pst.setObject(3, model);
			        pst.setObject(4, seats);
			        pst.setObject(5, status);
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
	
	private void sort() {
		vehicleModel.setRowCount(0);
		Object sort = sortDrop.getSelectedItem();
		try {	
			if(sort.equals("SEDAN") || sort.equals("SUV") || sort.equals("VAN")) {
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM Vehicle WHERE vehicle_category = '" + sort + "'");
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
		            int id = resultSet.getInt("vehicle_id");
		            String category = resultSet.getString("vehicle_category");
		            String type = resultSet.getString("vehicle_type");
		            String vehiclemodel = resultSet.getString("vehicle_model"); // Rename this variable
		            String seats = resultSet.getString("seats");	             
		            String status = resultSet.getString("status");
		            vehicleModel.addRow(new Object[]{id, category, type, vehiclemodel, seats, status});
		        }
			}
			else if(sort.equals("NONE")) {
				loadRecords();
			}
			else if(sort.equals("AVAILABLE")) {
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM Vehicle WHERE status = '" + sort + "'");
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
		            int id = resultSet.getInt("vehicle_id");
		            String category = resultSet.getString("vehicle_category");
		            String type = resultSet.getString("vehicle_type");
		            String vehiclemodel = resultSet.getString("vehicle_model"); // Rename this variable
		            String seats = resultSet.getString("seats");	             
		            String status = resultSet.getString("status");
		            vehicleModel.addRow(new Object[]{id, category, type, vehiclemodel, seats, status});
		        }
			}
			else if(sort.equals("RENTED")) {
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM Vehicle WHERE status = '" + sort + "'");
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
		            int id = resultSet.getInt("vehicle_id");
		            String category = resultSet.getString("vehicle_category");
		            String type = resultSet.getString("vehicle_type");
		            String vehiclemodel = resultSet.getString("vehicle_model"); // Rename this variable
		            String seats = resultSet.getString("seats");	             
		            String status = resultSet.getString("status");
		            vehicleModel.addRow(new Object[]{id, category, type, vehiclemodel, seats, status});
		        }
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	private class TabButton extends MouseAdapter{
		JLabel label;
		
		public TabButton(JLabel label) {
			
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
	