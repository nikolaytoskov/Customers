
package customersdb;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

public final class CustomerPanel extends JFrame{
        int id = 0;
	Connection conn;
	Statement state = null;
	PreparedStatement prepState = null;
	ResultSet result = null;
        MyModel model;
    
        JFrame jFrame = new JFrame();
        JTable table = new JTable();

	JPanel topPanel = new JPanel();
	JPanel midPanel = new JPanel();
	JPanel downPanel = new JPanel();

	JLabel fNameLabel = new JLabel("Име");
	JLabel lNameLabel = new JLabel("Фамилия");
	JLabel addressLabel = new JLabel("Адрес");
	JLabel townLabel = new JLabel("Град");
	JLabel phoneLabel = new JLabel("Телефон");
        JLabel emailLabel = new JLabel("Поща");

	JTextField fNameField = new JTextField(20);
	JTextField lNameField = new JTextField(20);
	JTextField addressField = new JTextField(20);
	JTextField townField = new JTextField(20);
        JTextField phoneField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        
        JButton addButton = new JButton("Добави");
        JButton searchButton = new JButton("Търси");
	JButton refreshButton = new JButton("Покажи Всички");
	JButton editButton = new JButton("Промени");
    
    public CustomerPanel(){
        super();
	init();
    }
    
    public void init(){
        this.setLayout(new GridLayout(3,1));
        this.setSize(1024, 900);
        this.add(topPanel);
        this.add(midPanel);
        this.add(downPanel);
        
        //topPanel Starts here
        topPanel.setLayout(new GridLayout(6,2));
        topPanel.setBackground(Color.red);
        topPanel.add(fNameLabel);
        topPanel.add(fNameField);
	topPanel.add(lNameLabel);
        topPanel.add(lNameField);
        topPanel.add(addressLabel);
        topPanel.add(addressField);
        topPanel.add(townLabel);
        topPanel.add(townField);
        topPanel.add(phoneLabel);
        topPanel.add(phoneField);
        topPanel.add(emailLabel);
        topPanel.add(emailField);
        
        //midPanel starts here
        midPanel.setLayout(new FlowLayout());
        midPanel.add(addButton);
        midPanel.add(searchButton);
        midPanel.add(refreshButton);
        midPanel.add(editButton);
        
        //downPanel
	JTableHeader header = table.getTableHeader();
	header.setBackground(Color.red);
	downPanel.setBackground(Color.green);
	downPanel.setLayout(new FlowLayout());
	JScrollPane tableScroll = new JScrollPane(table);
	tableScroll.setPreferredSize(new Dimension(1000, 250));
	downPanel.add(tableScroll);//slagame skrolira6t panel i wytre slagame tablicata
		try {
		    table.setModel(getModel());
		} catch (Exception e) {
		}
               
    }

    public MyModel getModel() throws Exception{
	    state = DBUtil.getConnected().createStatement();
	    result = state.executeQuery("select* from customers ");
	    model = new MyModel (result);
	    return model;
	}

	public void refreshContent(){
	    try{
		model = getModel();
		model.fireTableDataChanged();
		table.setModel(model);
		table.repaint();
	    }
	    catch (Exception ex){

	    }
	}
	
    public static void mainCustomerPanel(String[] args) {
		// TODO Auto-generated method stub
		CustomerPanel jFrame = new CustomerPanel();
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
