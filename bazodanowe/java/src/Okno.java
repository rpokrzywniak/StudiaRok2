//Robert Pokrzywniak
import java.awt.BorderLayout;
import java.awt.EventQueue;
import org.sqlite.SQLiteConfig;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import java.awt.GridBagConstraints;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.*;
import java.sql.*;
import java.util.Date;
import java.text.*;
import java.util.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;
import java.beans.PropertyChangeEvent;

public class Okno extends JFrame {

	private JPanel contentPane;
	private static JTable table1;
	private static JTable table2;
	private static JTable table3;
	private static JTable table4;
	private static JTable tablej1;
	private static JTable tablej2;
	private static JTable tablej3;
	private static JTable tablej4;
	private static int[] wybrane1j;
	private static int[] wybrane2j;
	private static int[] wybrane3j;
	private static int[] wybrane4j;
	private static int tester=0;
	private static JScrollPane scrollPane= new JScrollPane();
	public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:test1.db";
    public static int licznik1=0;
    public static int licznik2=0;
    public static int licznik3=0;
    public static int licznik4=0;
    public static int[] idklient = new int[10];
    public static int[] idzamowienie= new int[10];
    public static int[] idopis_zamowienia= new int[10];
    public static int[] idkawa= new int[10];
    private static String[] kawki = new String[10];
    private static Connection conn;
    private static Statement stmt;
    static DefaultTableModel model1 = new DefaultTableModel();
    static DefaultTableModel model2 = new DefaultTableModel();
    static DefaultTableModel model3 = new DefaultTableModel();
    static DefaultTableModel model4 = new DefaultTableModel();
    static DefaultTableModel modelj1 = new DefaultTableModel();
    static DefaultTableModel modelj2 = new DefaultTableModel();
    static DefaultTableModel modelj3 = new DefaultTableModel();
    static DefaultTableModel modelj4 = new DefaultTableModel();
    private static int btndodaj=0;
	private static JButton btnDodaj1 = new JButton("DODAJ");
	private static GridBagConstraints gbc_btnDodaj1 = new GridBagConstraints();
	private static JButton btnDodaj2 = new JButton("DODAJ");
	private static JButton btnDodaj3 = new JButton("DODAJ");
	private static JButton btnDodaj4 = new JButton("DODAJ");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Okno frame = new Okno();
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
	public Okno() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
	   	
		JMenu mnKlient = new JMenu("KLIENT");
		menuBar.add(mnKlient);
		
		JMenuItem mntmWywietlJeden = new JMenuItem("WYŚWIETL WYBRANE");
		mnKlient.add(mntmWywietlJeden);
		mntmWywietlJeden.setActionCommand("wyswietlj1");
		mntmWywietlJeden.addActionListener(new ClickListener());
		
		JMenuItem mntmWywietl = new JMenuItem("WYŚWIETL WSZYSTKIE");
		mnKlient.add(mntmWywietl);
		mntmWywietl.setActionCommand("wyswietl1");
		mntmWywietl.addActionListener(new ClickListener());
		
		JMenuItem mntmDodaj = new JMenuItem("DODAJ");
		mnKlient.add(mntmDodaj);
		mntmDodaj.setActionCommand("dodaj1");
		mntmDodaj.addActionListener(new ClickListener());
		
		JMenuItem mntmUsu = new JMenuItem("USUŃ");
		mnKlient.add(mntmUsu);
		mntmUsu.setActionCommand("usun1");
		mntmUsu.addActionListener(new ClickListener());
		
		JMenu mnZamwienie = new JMenu("ZAMÓWIENIE");
		menuBar.add(mnZamwienie);
		
		JMenuItem mntmWywietlWybrane = new JMenuItem("WYŚWIETL WYBRANE");
		mnZamwienie.add(mntmWywietlWybrane);
		mntmWywietlWybrane.setActionCommand("wyswietlj2");
		mntmWywietlWybrane.addActionListener(new ClickListener());
		
		JMenuItem mntmWywietl_1 = new JMenuItem("WYŚWIETL WSZYSTKIE");
		mnZamwienie.add(mntmWywietl_1);
		mntmWywietl_1.setActionCommand("wyswietl2");
		mntmWywietl_1.addActionListener(new ClickListener());
		
		JMenuItem mntmDodaj_1 = new JMenuItem("DODAJ");
		mnZamwienie.add(mntmDodaj_1);
		mntmDodaj_1.setActionCommand("dodaj2");
		mntmDodaj_1.addActionListener(new ClickListener());
		
		JMenuItem mntmUsu_1 = new JMenuItem("USUŃ");
		mnZamwienie.add(mntmUsu_1);
		mntmUsu_1.setActionCommand("usun2");
		mntmUsu_1.addActionListener(new ClickListener());
		
		JMenu mnOpisZamwienia = new JMenu("OPIS ZAMÓWIENIA");
		menuBar.add(mnOpisZamwienia);
		
		JMenuItem mntmWywietlWybrane_1 = new JMenuItem("WYŚWIETL WYBRANE");
		mnOpisZamwienia.add(mntmWywietlWybrane_1);
		mntmWywietlWybrane_1.setActionCommand("wyswietlj3");
		mntmWywietlWybrane_1.addActionListener(new ClickListener());
		
		JMenuItem mntmWywietl_2 = new JMenuItem("WYŚWIETL WSZYSTKIE");
		mnOpisZamwienia.add(mntmWywietl_2);
		mntmWywietl_2.setActionCommand("wyswietl3");
		mntmWywietl_2.addActionListener(new ClickListener());
		
		JMenuItem mntmDodaj_2 = new JMenuItem("DODAJ");
		mnOpisZamwienia.add(mntmDodaj_2);
		mntmDodaj_2.setActionCommand("dodaj3");
		mntmDodaj_2.addActionListener(new ClickListener());
		
		JMenuItem mntmUsu_2 = new JMenuItem("USUŃ");
		mnOpisZamwienia.add(mntmUsu_2);
		mntmUsu_2.setActionCommand("usun3");
		mntmUsu_2.addActionListener(new ClickListener());
		
		JMenu mnKawa = new JMenu("KAWA");
		menuBar.add(mnKawa);
		
		JMenuItem mntmWywietlWybrane_2 = new JMenuItem("WYŚWIETL WYBRANE");
		mnKawa.add(mntmWywietlWybrane_2);
		mntmWywietlWybrane_2.setActionCommand("wyswietlj4");
		mntmWywietlWybrane_2.addActionListener(new ClickListener());
		
		JMenuItem mntmWywietl_3 = new JMenuItem("WYŚWIETL WSZYSTKIE");
		mnKawa.add(mntmWywietl_3);
		mntmWywietl_3.setActionCommand("wyswietl4");
		mntmWywietl_3.addActionListener(new ClickListener());
		
		JMenuItem mntmDodaj_3 = new JMenuItem("DODAJ");
		mnKawa.add(mntmDodaj_3);
		mntmDodaj_3.setActionCommand("dodaj4");
		mntmDodaj_3.addActionListener(new ClickListener());
		
		JMenuItem mntmUsu_3 = new JMenuItem("USUŃ");
		mnKawa.add(mntmUsu_3);
		mntmUsu_3.setActionCommand("usun4");
		mntmUsu_3.addActionListener(new ClickListener());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{980, 0};
		gbl_contentPane.rowHeights = new int[]{402, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		
		table1 = new JTable(model1);
		table1.setEnabled(true);
		model1.addColumn("lp");
		model1.addColumn("imie"); 
		model1.addColumn("nazwisko");
		model1.addColumn("pesel");
		model1.addColumn("ulica");
		model1.addColumn("nr_domu");
		model1.addColumn("kod_pocztowy");
		model1.addColumn("miasto");
		model1.addColumn("kraj");
		tablej1 = new JTable(modelj1);
		tablej1.setEnabled(true);
		modelj1.addColumn("lp");
		modelj1.addColumn("imie"); 
		modelj1.addColumn("nazwisko");
		modelj1.addColumn("pesel");
		modelj1.addColumn("ulica");
		modelj1.addColumn("nr_domu");
		modelj1.addColumn("kod_pocztowy");
		modelj1.addColumn("miasto");
		modelj1.addColumn("kraj");
		
		
		table2 = new JTable(model2); 
		table2.setEnabled(true);
		model2.addColumn("lp");
		model2.addColumn("ilosc"); 
		model2.addColumn("nazwa"); 
		model2.addColumn("data_zam");
		tablej2 = new JTable(modelj2); 
		tablej2.setEnabled(true);
		modelj2.addColumn("lp");
		modelj2.addColumn("ilosc"); 
		modelj2.addColumn("nazwa"); 
		modelj2.addColumn("data_zam");
		
		
		table3 = new JTable(model3); 
		table3.setEnabled(true);
		model3.addColumn("lp"); 
		model3.addColumn("nazwa");
		model3.addColumn("ilosc"); 
		model3.addColumn("cena"); 
		model3.addColumn("razem");
		tablej3 = new JTable(modelj3); 
		tablej3.setEnabled(true);
		modelj3.addColumn("lp"); 
		modelj3.addColumn("nazwa");
		modelj3.addColumn("ilosc"); 
		modelj3.addColumn("cena"); 
		modelj3.addColumn("razem");
		
		table4 = new JTable(model4);
		table4.setEnabled(true);
		model4.addColumn("lp"); 
		model4.addColumn("nazwa");
		model4.addColumn("cena");
		model4.addColumn("ilosc");
		tablej4 = new JTable(modelj4);
		tablej4.setEnabled(true);
		modelj4.addColumn("lp"); 
		modelj4.addColumn("nazwa");
		modelj4.addColumn("cena");
		modelj4.addColumn("ilosc");

		scrollPane.setViewportView(table1);
		gbc_btnDodaj1.gridx = 0;
		gbc_btnDodaj1.gridy = 1;
		btnDodaj1.setActionCommand("dodaj1");
		btnDodaj1.addActionListener(new ClickListener());
		btnDodaj2.setActionCommand("dodaj2");
		btnDodaj2.addActionListener(new ClickListener());
		btnDodaj3.setActionCommand("dodaj3");
		btnDodaj3.addActionListener(new ClickListener());
		btnDodaj4.setActionCommand("dodaj4");
		btnDodaj4.addActionListener(new ClickListener());
		contentPane.add(btnDodaj1, gbc_btnDodaj1);
		btndodaj=1;	
		contentPane.add(btnDodaj2, gbc_btnDodaj1);
		btnDodaj2.setVisible(false);
		contentPane.add(btnDodaj3, gbc_btnDodaj1);
		btnDodaj3.setVisible(false);
		contentPane.add(btnDodaj4, gbc_btnDodaj1);
		btnDodaj4.setVisible(false);
		try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
    	try {
    		SQLiteConfig config = new SQLiteConfig();
    		config.enforceForeignKeys(true);
            conn = DriverManager.getConnection(DB_URL,config.toProperties());
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem połączenia");
            e.printStackTrace();
        }
    		String sql = "CREATE TABLE IF NOT EXISTS klient " +
                "(idklient INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " imie VARCHAR(255) NOT NULL CHECK(imie != ''), " +
                " nazwisko VARCHAR(255) NOT NULL CHECK(nazwisko != ''), " +
                " pesel INT NOT NULL UNIQUE, " +
                " ulica VARCHAR(255) NOT NULL CHECK(ulica != ''), " +
                " nr_domu INT NOT NULL, " +
                " kod_pocztowy VARCHAR(255) NOT NULL CHECK(kod_pocztowy != ''), " +
                " miasto VARCHAR(255) NOT NULL CHECK(miasto != ''), " +
                " kraj VARCHAR(255) DEFAULT 'Polska')";
    		try {
                stmt.execute(sql);
            } catch (SQLException e) {
                System.err.println("Blad przy tworzeniu tabeli1");
                e.printStackTrace();
            }
    		sql = "CREATE TABLE IF NOT EXISTS zamowienie " +
                    "(idzamowienie INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " ilosc INT NOT NULL CHECK(ilosc > 0), " +
                    " nazwa VARCHAR(255) NOT NULL CHECK(nazwa != ''), " +
            		" idklient INTEGER NOT NULL," +
            		" data_zam DATE NOT NULL CHECK(data_zam != '')," +
            		" FOREIGN KEY(idklient) REFERENCES klient(idklient) ON DELETE CASCADE ON UPDATE CASCADE)";
    		try {
                stmt.execute(sql);
            } catch (SQLException e) {
                System.err.println("Blad przy tworzeniu tabeli2");
                e.printStackTrace();
            }
    		sql = "CREATE TABLE IF NOT EXISTS kawa " +
                    "(idkawa INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " cena DOUBLE NOT NULL CHECK(cena > 0), " +
                    " nazwa VARCHAR(255) NOT NULL UNIQUE CHECK(nazwa != ''), " +
            		" ilosc INT NOT NULL)";
    		try {
                stmt.execute(sql);
            } catch (SQLException e) {
                System.err.println("Blad przy tworzeniu tabeli4");
                e.printStackTrace();
            }
    		sql = "CREATE TABLE IF NOT EXISTS opis_zamowienia " +
                    "(idopis_zamowienia INTEGER PRIMARY KEY AUTOINCREMENT, " +
            		" idzamowienie INTEGER NOT NULL," +
            		" idkawa INTEGER NOT NULL," +
                    " nazwa VARCHAR(255) NOT NULL CHECK(nazwa != ''), " +
                    " ilosc INT NOT NULL CHECK(ilosc > 0), " +
                    " cena DOUBLE NOT NULL CHECK(cena > 0), " +
                    " razem DOUBLE NOT NULL CHECK(cena > 0), " +
            		" FOREIGN KEY(idzamowienie) REFERENCES zamowienie(idzamowienie) ON DELETE CASCADE ON UPDATE CASCADE, " +
            		" FOREIGN KEY(idkawa) REFERENCES kawa(idkawa) ON DELETE CASCADE ON UPDATE CASCADE)";
    		try {
                stmt.execute(sql);
            } catch (SQLException e) {
                System.err.println("Blad przy tworzeniu tabeli3");
                e.printStackTrace();
            }
    		refreshKlient();
    		refreshKawa();
    		refreshZamowienie();
    		refreshOpis_zamowienia();
    		model1.addTableModelListener(new TableListener1());
    		model2.addTableModelListener(new TableListener2());
    		model3.addTableModelListener(new TableListener3());
    		model4.addTableModelListener(new TableListener4());
    		modelj1.addTableModelListener(new TableListenerj1());
    		modelj2.addTableModelListener(new TableListenerj2());
    		modelj3.addTableModelListener(new TableListenerj3());
    		modelj4.addTableModelListener(new TableListenerj4());
	
	}
	
	   private class ClickListener implements ActionListener{
		      public void actionPerformed(ActionEvent e) {
		         String command = e.getActionCommand();  
		         if( command.equals( "wyswietl1" ))  {
		            scrollPane.setViewportView(table1);
		            if(btndodaj==1) btnDodaj1.setVisible(false);
		        	 else if(btndodaj==2) btnDodaj2.setVisible(false);
		        	 else if(btndodaj==3) btnDodaj3.setVisible(false);
		        	 else if(btndodaj==4) btnDodaj4.setVisible(false);
		     		btnDodaj1.setVisible(true);
		    		btndodaj=1;
		         }
		         else if( command.equals( "wyswietl2" ) )  {
		        	 scrollPane.setViewportView(table2);
		        	 if(btndodaj==1) btnDodaj1.setVisible(false);
		        	 else if(btndodaj==2) btnDodaj2.setVisible(false);
		        	 else if(btndodaj==3) btnDodaj3.setVisible(false);
		        	 else if(btndodaj==4) btnDodaj4.setVisible(false);
		     		btnDodaj2.setVisible(true);
		    		btndodaj=2;
		         }
		         else if( command.equals( "wyswietlj1" ) )  {
		        	 wyswietlj1 wyswietlj1 = new wyswietlj1();		        	
		        	 wyswietlj1.setVisible(true);
		        	 if(btndodaj==1) btnDodaj1.setVisible(false);
		        	 else if(btndodaj==2) btnDodaj2.setVisible(false);
		        	 else if(btndodaj==3) btnDodaj3.setVisible(false);
		        	 else if(btndodaj==4) btnDodaj4.setVisible(false);
		     		btnDodaj1.setVisible(true);
		    		btndodaj=1;
		         }
		         else if( command.equals( "wyswietlj2" ) )  {
		        	 wyswietlj2 wyswietlj2 = new wyswietlj2();
		        	 wyswietlj2.setVisible(true);
		        	 if(btndodaj==1) btnDodaj1.setVisible(false);
		        	 else if(btndodaj==2) btnDodaj2.setVisible(false);
		        	 else if(btndodaj==3) btnDodaj3.setVisible(false);
		        	 else if(btndodaj==4) btnDodaj4.setVisible(false);
		     		btnDodaj2.setVisible(true);
		    		btndodaj=2;
		         }
		         else if( command.equals( "wyswietlj3" ) )  {
		        	 wyswietlj3 wyswietlj3 = new wyswietlj3();
		        	 wyswietlj3.setVisible(true);
		        	 if(btndodaj==1) btnDodaj1.setVisible(false);
		        	 else if(btndodaj==2) btnDodaj2.setVisible(false);
		        	 else if(btndodaj==3) btnDodaj3.setVisible(false);
		        	 else if(btndodaj==4) btnDodaj4.setVisible(false);
		     		btnDodaj3.setVisible(true);
		    		btndodaj=3;
		         }
		         else if( command.equals( "wyswietlj4" ) )  {
		        	 wyswietlj4 wyswietlj4 = new wyswietlj4();
		        	 wyswietlj4.setVisible(true);
		        	 if(btndodaj==1) btnDodaj1.setVisible(false);
		        	 else if(btndodaj==2) btnDodaj2.setVisible(false);
		        	 else if(btndodaj==3) btnDodaj3.setVisible(false);
		        	 else if(btndodaj==4) btnDodaj4.setVisible(false);
		     		btnDodaj4.setVisible(true);
		    		btndodaj=4;
		         }
		         else if( command.equals( "wyswietl3" ) )  {
		        	 scrollPane.setViewportView(table3);
		        	 if(btndodaj==1) btnDodaj1.setVisible(false);
		        	 else if(btndodaj==2) btnDodaj2.setVisible(false);
		        	 else if(btndodaj==3) btnDodaj3.setVisible(false);
		        	 else if(btndodaj==4) btnDodaj4.setVisible(false);
		     		btnDodaj3.setVisible(true);
		    		btndodaj=3;
		         }
		         else if( command.equals( "wyswietl4" ) )  {
		        	 scrollPane.setViewportView(table4);
		        	 if(btndodaj==1) btnDodaj1.setVisible(false);
		        	 else if(btndodaj==2) btnDodaj2.setVisible(false);
		        	 else if(btndodaj==3) btnDodaj3.setVisible(false);
		        	 else if(btndodaj==4) btnDodaj4.setVisible(false);
		     		btnDodaj4.setVisible(true);
		    		btndodaj=4;
		         }
		         else if( command.equals( "dodaj1" ) )  {
		        	 dodaj1 dialog1 = new dodaj1();
		        	 dialog1.setVisible(true);
		         }
		         else if( command.equals( "dodaj2" ) )  {
		        	 dodaj2 dialog2 = new dodaj2();
		        	 dialog2.setVisible(true);
		        	 
		         }
		         else if( command.equals( "dodaj3" ) )  {
		        	 dodaj3 dialog3 = new dodaj3();
		        	 dialog3.setVisible(true);
		        	 
		         }
		         else if( command.equals( "dodaj4" ) )  {
		        	 dodaj4 dialog4 = new dodaj4();
		        	 dialog4.setVisible(true);
		         }
		         else if( command.equals( "usun1" ) )  {
		        	 usun1 usun1 = new usun1();
		        	 usun1.setVisible(true);
		         } 	
		         else if( command.equals( "usun2" ) )  {
		        	 usun2 usun2 = new usun2();
		        	 usun2.setVisible(true);
		         }
		         else if( command.equals( "usun3" ) )  {
		        	 usun3 usun3 = new usun3();
		        	 usun3.setVisible(true);
		         }
		         else if( command.equals( "usun4" ) )  {
		        	 usun4 usun4 = new usun4();
		        	 usun4.setVisible(true);
		         }
		      }		
		   }
	   public class TableListener1 implements TableModelListener {
		    public void tableChanged(TableModelEvent e) {
		    	if(tester==0){
		    		tester=1;
		    		int row = e.getFirstRow();
		    		int column = e.getColumn();
		    		TableModel model = (TableModel)e.getSource();
			        String columnName = model.getColumnName(column);
			        Object data = model.getValueAt(row, column);
			        refreshKlient();
			        data=data.toString().replaceAll("\\s+","");
			        if(column>0 && !data.equals("") && !columnName.equals("kraj")){
			        	try{
			        		int j = idklient[row+1];
			        		System.out.println(j);
			 			   String sql="UPDATE klient SET '"+columnName+"' = '"+data+"' WHERE idklient = "+j+";";
			 			   stmt.execute(sql);
			 		   } catch (SQLException z){
			 			   z.printStackTrace();
			 		   }
			        	System.out.println("XD");
				        refreshKlient();
			        }
			        else{
			        	try{
			        		int j = idklient[row+1];
			        		System.out.println(j);
			        		if(data.equals("")){
			        			String sql="UPDATE klient SET '"+columnName+"' = 'Polska' WHERE idklient = "+j+";";
			        			stmt.execute(sql);
			        		}
			        		else{	
			        			String sql="UPDATE klient SET '"+columnName+"' = '"+data+"' WHERE idklient = "+j+";";
			        			stmt.execute(sql);
			        		}
			 		   } catch (SQLException z){
			 			   z.printStackTrace();
			 		   }
			        	System.out.println("XD");
				        refreshKlient();
			        }
		    	}
			 }
		}
	   public class TableListenerj1 implements TableModelListener {
		    public void tableChanged(TableModelEvent e) {
		    	if(tester==0){
		    		tester=1;
		    		int row = e.getFirstRow();
		    		int column = e.getColumn();
		    		TableModel model = (TableModel)e.getSource();
			        String columnName = model.getColumnName(column);
			        Object data = model.getValueAt(row, column);
			        int lp= Integer.parseInt(modelj1.getValueAt(row, 0).toString());
			        refreshKlient();
			        data=data.toString().replaceAll("\\s+","");
			        if(column>0 && !data.equals("") && !columnName.equals("kraj")){
			        	try{
			        		int j = idklient[lp];
			        		System.out.println(j);
			 			   String sql="UPDATE klient SET '"+columnName+"' = '"+data+"' WHERE idklient = "+j+";";
			 			   stmt.execute(sql);
			 		   } catch (SQLException z){
			 			   z.printStackTrace();
			 		   }
			        	System.out.println("XD");
			        	refreshKlient();
			        	Wyswietlj1(wybrane1j);
			        }
			        else{
			        	try{
			        		int j = idklient[lp];
			        		System.out.println(j);
			        		if(data.equals("")){
			        			String sql="UPDATE klient SET '"+columnName+"' = 'Polska' WHERE idklient = "+j+";";
			        			stmt.execute(sql);
			        		}
			        		else{	
			        			String sql="UPDATE klient SET '"+columnName+"' = '"+data+"' WHERE idklient = "+j+";";
			        			stmt.execute(sql);
			        		}
			 		   } catch (SQLException z){
			 			   z.printStackTrace();
			 		   }
			        	System.out.println("XD");
			        	refreshKlient();
			        	Wyswietlj1(wybrane1j);
			        }
		    	}
			 }
		}
	   public class TableListener2 implements TableModelListener {
		    public void tableChanged(TableModelEvent e) {
		    	if(tester==0){
		    		tester=1;
		    		int row = e.getFirstRow();
		    		int column = e.getColumn();
		    		TableModel model = (TableModel)e.getSource();
			        String columnName = model.getColumnName(column);
			        Object data = model.getValueAt(row, column);
			        refreshZamowienie();
			        String data1=data.toString().replaceAll("\\s+","");
			        if(column>0 && !data.equals("")){
			        	try{
			        		if(!columnName.equals("data_zam")){
				        		int j = idzamowienie[row+1];
				        		System.out.println(j);
				 			   String sql="UPDATE zamowienie SET '"+columnName+"' = '"+data+"' WHERE idzamowienie = "+j+";";
				 			   stmt.execute(sql);
			        	}
			        		else{
			        			try{
			   	        		 if(data1.length()==10){
			   	        			int j = idzamowienie[row+1];
			   	        			 Date date = new SimpleDateFormat("yyyy-MM-dd").parse(data1);
			   	        			 String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			   	        			String sql="UPDATE zamowienie SET '"+columnName+"' = '"+formattedDate+"' WHERE idzamowienie = "+j+";";
			   	        			stmt.execute(sql);
			   	        		 }
			   	        		 else throw new ParseException("if nie sprawdzony",1);
			   	        	 } catch (ParseException z){
			   	        		 System.out.println(z);
			   	        	 }
			        		}
			 		   } catch (SQLException z){
			 			   z.printStackTrace();
			 		   }
			        	System.out.println("XD");
				        refreshZamowienie();
				        refreshOpis_zamowienia();
				        refreshKawa();
			        }
		    	}
			 }
		}
	   public class TableListenerj2 implements TableModelListener {
		    public void tableChanged(TableModelEvent e) {
		    	if(tester==0){
		    		tester=1;
		    		int row = e.getFirstRow();
		    		int column = e.getColumn();
		    		TableModel model = (TableModel)e.getSource();
			        String columnName = model.getColumnName(column);
			        Object data = model.getValueAt(row, column);
			        int lp= Integer.parseInt(modelj2.getValueAt(row, 0).toString());
			        refreshZamowienie();
			        String data1=data.toString().replaceAll("\\s+","");
			        if(column>0 && !data.equals("")){
			        	try{
			        		if(!columnName.equals("data_zam")){
				        		int j = idzamowienie[lp];
				        		System.out.println(j);
				 			   String sql="UPDATE zamowienie SET '"+columnName+"' = '"+data+"' WHERE idzamowienie = "+j+";";
				 			   stmt.execute(sql);
			        	}
			        		else{
			        			try{
			   	        		 if(data1.length()==10){
			   	        			int j = idzamowienie[lp];
			   	        			 Date date = new SimpleDateFormat("yyyy-MM-dd").parse(data1);
			   	        			 String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			   	        			String sql="UPDATE zamowienie SET '"+columnName+"' = '"+formattedDate+"' WHERE idzamowienie = "+j+";";
			   	        			stmt.execute(sql);
			   	        		 }
			   	        		 else throw new ParseException("if nie sprawdzony",1);
			   	        	 } catch (ParseException z){
			   	        		 System.out.println(z);
			   	        	 }
			        		}
			 		   } catch (SQLException z){
			 			   z.printStackTrace();
			 		   }
			        	System.out.println("XD");
			        	refreshZamowienie();
			        	Wyswietlj2(wybrane2j);
				        refreshOpis_zamowienia();
				        refreshKawa();
			        }
		    	}
			 }
		}
	   public class TableListener3 implements TableModelListener {
		    public void tableChanged(TableModelEvent e) {
		    	if(tester==0){
		    		tester=1;
		    		int row = e.getFirstRow();
		    		int column = e.getColumn();
		    		TableModel model = (TableModel)e.getSource();
			        String columnName = model.getColumnName(column);
			        Object data = model.getValueAt(row, column);
			        String data1=data.toString().replaceAll("\\s+","");
				        if(columnName.equals("nazwa")){
				        	editOpisNazwa(row,data1);
					        refreshOpis_zamowienia();
					        refreshKawa();
				        }
				        else if(columnName.equals("ilosc")){
				        	editOpisIlosc(row,data1);
			 				   refreshOpis_zamowienia();
			 				   refreshKawa();
				        }
				        else if(columnName.equals("cena") || columnName.equals("razem") ){
			 				   refreshOpis_zamowienia();
				        }
				        tester=0;
				        
		    	}
			 }
		}
	   public class TableListenerj3 implements TableModelListener {
		    public void tableChanged(TableModelEvent e) {
		    	if(tester==0){
		    		tester=1;
		    		int row = e.getFirstRow();
		    		int column = e.getColumn();
		    		TableModel model = (TableModel)e.getSource();
			        String columnName = model.getColumnName(column);
			        Object data = model.getValueAt(row, column);
			        int lp= Integer.parseInt(modelj3.getValueAt(row, 0).toString());
			        String data1=data.toString().replaceAll("\\s+","");
				        if(columnName.equals("nazwa")){
				        	editOpisNazwa(lp,data1);
				        	Wyswietlj3(wybrane3j);
				        	refreshOpis_zamowienia();
				        }
				        else if(columnName.equals("ilosc")){
				        	editOpisIlosc(lp,data1);
				        	refreshOpis_zamowienia();
				        	Wyswietlj3(wybrane3j);
				        }
				        else if(columnName.equals("cena") || columnName.equals("razem") ){
				        	Wyswietlj3(wybrane3j);
				        	refreshOpis_zamowienia();
				        }
				        tester=0;
				        
		    	}
			 }
		}
	   public class TableListener4 implements TableModelListener {
		    public void tableChanged(TableModelEvent e) {
		    	if(tester==0){
		    		int row = e.getFirstRow();
		    		int column = e.getColumn();
		    		TableModel model = (TableModel)e.getSource();
			        String columnName = model.getColumnName(column);
			        Object data = model.getValueAt(row, column);
			        String kawa = kawki[row];
			        refreshKawa();
			        data=data.toString().replaceAll("\\s+","");
			        System.out.println(kawa);
			        if(column>0 && !data.equals("")){
			        	try{
			        		int j = idkawa[row+1];
			        		System.out.println(j);
			 			   String sql="UPDATE kawa SET '"+columnName+"' = '"+data+"' WHERE idkawa = "+j+";";
			 			   stmt.execute(sql);
			 			   if(columnName.equals("nazwa")){
			 				  sql="UPDATE opis_zamowienia SET '"+columnName+"' = '"+data+"' WHERE nazwa = '"+kawa+"';";
			 				 stmt.execute(sql);
			 				 sql="UPDATE zamowienie SET '"+columnName+"' = '"+data+"' WHERE nazwa = '"+kawa+"';";
			 				stmt.execute(sql);
			 			   }
			 		   } catch (SQLException z){
			 			   z.printStackTrace();
			 		   }
				        refreshKawa();
				        refreshOpis_zamowienia();
				        refreshZamowienie();
			        }
		    	}
			 }
		}
	   public class TableListenerj4 implements TableModelListener {
		    public void tableChanged(TableModelEvent e) {
		    	if(tester==0){
		    		int row = e.getFirstRow();
		    		int column = e.getColumn();
		    		TableModel model = (TableModel)e.getSource();
			        String columnName = model.getColumnName(column);
			        Object data = model.getValueAt(row, column);
			        int lp= Integer.parseInt(modelj4.getValueAt(row, 0).toString());
			        String kawa = kawki[row];
			        refreshKawa();
			        data=data.toString().replaceAll("\\s+","");
			        System.out.println(kawa);
			        if(column>0 && !data.equals("")){
			        	try{
			        		int j = idkawa[lp];
			        		System.out.println(j);
			 			   String sql="UPDATE kawa SET '"+columnName+"' = '"+data+"' WHERE idkawa = "+j+";";
			 			   stmt.execute(sql);
			 			   if(columnName.equals("nazwa")){
			 				  sql="UPDATE opis_zamowienia SET '"+columnName+"' = '"+data+"' WHERE nazwa = '"+kawa+"';";
			 				 stmt.execute(sql);
			 				 sql="UPDATE zamowienie SET '"+columnName+"' = '"+data+"' WHERE nazwa = '"+kawa+"';";
			 				stmt.execute(sql);
			 			   }
			 		   } catch (SQLException z){
			 			   z.printStackTrace();
			 		   }
				        Wyswietlj4(wybrane4j);
				        refreshKawa();
				        refreshOpis_zamowienia();
				        refreshZamowienie();
			        }
		    	}
			 }
		}

	   public static void getDodaj1(String imie, String nazwisko, int pesel, String ulica, int nr_domu, String kod_pocztowy, String miasto, String kraj ){
		   try {
       		if(kraj.equals("")){
       			String sql="INSERT INTO klient (imie,nazwisko,pesel,ulica,nr_domu,kod_pocztowy,miasto) VALUES ('"+imie+"', '"+nazwisko+"', "+pesel+", '"+ulica+"', "+nr_domu+", '"+kod_pocztowy+"', '"+miasto+"');";
           		stmt.execute(sql);
       		}
       		else{
      			String sql="INSERT INTO klient (imie,nazwisko,pesel,ulica,nr_domu,kod_pocztowy,miasto,kraj) VALUES ('"+imie+"', '"+nazwisko+"', "+pesel+", '"+ulica+"', "+nr_domu+", '"+kod_pocztowy+"', '"+miasto+"', '"+kraj+"');";
           		stmt.execute(sql);
       		}
       		refreshKlient();
           } catch (SQLException z) {
           	System.err.println("Początek już wpisany");
            z.printStackTrace();
           }
	   }
	   public static void getDodaj4(String nazwa, double cena, int ilosc){
		   try {
       			String sql="INSERT INTO kawa (nazwa,cena,ilosc) VALUES ('"+nazwa+"', "+cena+", "+ilosc+");";
           		stmt.execute(sql);
           		refreshKawa();
           } catch (SQLException z) {
           	System.err.println("Początek już wpisany");
            z.printStackTrace();
           }
	   }
	   public static void getDodaj3(int index){
		   int id=idzamowienie[index];
		   int ilosc;
		   String nazwa;
		   double cena;
		   double razem;
		   int idk;
		   int ilosc1;
		   try {
      			ResultSet sql= stmt.executeQuery("SELECT * FROM zamowienie WHERE idzamowienie = "+id+";");
          		sql.next();
          		ilosc = sql.getInt("ilosc");
          		nazwa = sql.getString("nazwa");
          		sql= stmt.executeQuery("SELECT * FROM kawa WHERE nazwa = '"+nazwa+"';");
          		sql.next();
          		ilosc1 = sql.getInt("ilosc");
          		cena = sql.getDouble("cena");
          		idk = sql.getInt("idkawa");
          		if(ilosc>ilosc1)throw new SQLException();
          		else{
          			razem=ilosc*cena;
          			String q="INSERT INTO opis_zamowienia (idzamowienie,idkawa,nazwa,ilosc,cena,razem) VALUES ("+id+", "+idk+", '"+nazwa+"', "+ilosc+", "+cena+", "+razem+");";
               		stmt.execute(q);
               		q="UPDATE kawa SET ilosc = ilosc - "+ilosc+" WHERE idkawa = "+idk+";";
               		stmt.execute(q);
          		}
          } catch (SQLException z) {
          	System.err.println("Początek już wpisany");
           z.printStackTrace();
          }
		   refreshOpis_zamowienia();
		   refreshKawa();
	   }
	   public static void getDodaj2(int ilosc, String data_zam, String nazwa, int index){
		   try {
			   System.out.println(index);
			   index=idklient[index];
			   System.out.println("idklienta");
			   double[] jdkawa=getKawe(nazwa);   
			   if(ilosc>(int)jdkawa[2]) throw new SQLException();
			   else{
				double razem = jdkawa[1]*ilosc;
				jdkawa[0]++;
				jdkawa[0]=idkawa[(int)jdkawa[0]];
				setIloscK(ilosc,(int)jdkawa[2], (int)jdkawa[0]);
				System.out.println(index);
       			String sql="INSERT INTO zamowienie (ilosc,nazwa,idklient,data_zam) VALUES ("+ilosc+", '"+nazwa+"', "+index+", '"+data_zam+"');";
           		stmt.execute(sql);
       			refreshZamowienie();
       			System.out.println("liczniki");
       			System.out.println(licznik1);
       			System.out.println(licznik2);
       			System.out.println(licznik3);
       			System.out.println(licznik4);
       			System.out.println("numery id");
       			System.out.println(idklient[licznik1]);
       			System.out.println(idzamowienie[licznik2]);
       			System.out.println(idopis_zamowienia[licznik3]);
       			System.out.println(idkawa[licznik4]);
       			System.out.println("idzamowienia"+ licznik2);
           		int jdzamowienie=idzamowienie[licznik2];
           		System.out.println(jdzamowienie);
           		System.out.println("idkawy"+(int)jdkawa[0]);
       			sql="INSERT INTO opis_zamowienia (idzamowienie,idkawa,nazwa,ilosc,cena,razem) VALUES ("+jdzamowienie+", "+(int)jdkawa[0]+", '"+nazwa+"', "+ilosc+", "+jdkawa[1]+", "+razem+");";
       			System.out.println("xd");
       			stmt.execute(sql);
       			System.out.println("xd");
       			refreshKawa();
       			refreshOpis_zamowienia();
			   }
           } catch (SQLException z) {
           	System.err.println("Początek już wpisany");
            z.printStackTrace();
           }
	   }
	   public static double[] getKawe(String k){
		   int h  = model4.getRowCount();
		   String p;
		   double[] r = new double[3];
		   for(int i=0;i<h;i++){
			   p=model4.getValueAt(i,1).toString();
			   if(p.equals(k)){
				   r[0]=i;
				   r[1]=Double.parseDouble(model4.getValueAt(i,2).toString());
				   r[2]=Double.parseDouble(model4.getValueAt(i,3).toString());
				   break;
			   }
		   }
		   return r;
	   }
	   public static void setIloscK(int k,int g, int j){
		   int w=g-k;
		   try{
			   String sql="UPDATE kawa SET ilosc = "+w+" WHERE idkawa = "+j+";";
			   stmt.execute(sql);
		   } catch (SQLException e){
			   e.printStackTrace();
		   }

	   }
	   public static String[] getKawy(){
		   int h = model4.getRowCount();
		   String[] kawy = new String[h];
		   for(int i=0;i<h;i++){
			   kawy[i] = model4.getValueAt(i,1).toString();
		   }
		   return kawy;
	   }
	   public static int[] getIlosc(){
		   int h = model2.getRowCount();
		   int[] ilosci = new int[h];
		   for(int i=0;i<h;i++){
			   ilosci[i] = Integer.parseInt(model2.getValueAt(i,1).toString());
		   }
		   return ilosci;
	   }
	   public static String[] getData_zam(){
		   int h = model2.getRowCount();
		   String[] kawy = new String[h];
		   for(int i=0;i<h;i++){
			   kawy[i] = model2.getValueAt(i,3).toString();
		   }
		   return kawy;
	   }
	   public static String[] getNazwa(){
		   int h = model2.getRowCount();
		   String[] kawy = new String[h];
		   for(int i=0;i<h;i++){
			   kawy[i] = model2.getValueAt(i,2).toString();
		   }
		   return kawy;
	   }
	   public static String[] getNazwakawy(){
		   int h = model4.getRowCount();
		   String[] kawy = new String[h];
		   for(int i=0;i<h;i++){
			   kawy[i] = model4.getValueAt(i,1).toString();
		   }
		   return kawy;
	   }
	   public static String[] getNazwy(int[] wybrane){
		   ResultSet sql;
		   String[] nazwy = new String[wybrane.length];
		   int j = 0;
		   try{
			   for(int i : wybrane){
				   i++;
				   int id = idkawa[i];
				   sql = stmt.executeQuery("SELECT nazwa FROM kawa WHERE idkawa = "+id+";");
				   sql.next();
				   nazwy[j]=sql.getString("nazwa");
				   j++;
			   }
		   }catch(SQLException e){}
		   return nazwy;
	   }
	   public static String[] getImiona(){
		   int h = model1.getRowCount();
		   String[] imiona = new String[h];
		   for(int i=0;i<h;i++){
			   imiona[i] = model1.getValueAt(i,1).toString();
		   }
		   return imiona;
	   }
	   public static String[] getInformacje(){
		   int h = model2.getRowCount();
		   int[] ids = new int[h];
		   String[] info = new String[h];
		   try{
			   ResultSet result;	
			   ResultSet sql;
			   result = stmt.executeQuery("SELECT * FROM zamowienie;");
			   int i =0;
			   while(result.next()){
				   ids[i] = result.getInt("idklient"); 
				   i++;
          	}
			   for(int k =0;k<i;k++){
				   sql= stmt.executeQuery("SELECT * FROM klient WHERE idklient = "+ids[k]+";");
				   sql.next();
				   info[k]=sql.getString("imie")+", "+sql.getString("nazwisko")+", "+sql.getInt("pesel"); 
			   }
		   } catch (SQLException e){
			   System.out.println("Błąd przy pobieraniu informacji");
		   }
		   return info;
	   }
	   public static String[] getNazwiska(){
		   int h = model1.getRowCount();
		   String[] imiona = new String[h];
		   for(int i=0;i<h;i++){
			   imiona[i] = model1.getValueAt(i,2).toString();
		   }
		   return imiona;
	   }
	   public static String[] getZamowienieKlient(){
		   String[] imie = new String[idzamowienie.length];
		   String[] nazwisko = new String[idzamowienie.length];
		   String[] pokaz = new String[idzamowienie.length];
		   int[] pesel = new int[idzamowienie.length];
		   try{
			    ResultSet q = stmt.executeQuery("SELECT idklient FROM zamowienie;");
			    int[] klient = new int[idzamowienie.length];
			    int i = 0;
	   			while(q.next()){
	   				klient[i]=q.getInt("idklient");
	   				i++;
	   			}
	   			for(int j=0;j<i;j++){
	   				q = stmt.executeQuery("SELECT * FROM klient WHERE idklient = "+klient[j]+";");
	   				q.next();
	   				imie[j]=q.getString("imie");
	   				nazwisko[j]=q.getString("nazwisko");
	   				pesel[j]=q.getInt("pesel");
	   				pokaz[j] = imie[j]+", "+nazwisko[j]+","+pesel[j];
	   			}   			
		   }catch(SQLException e){}
		   return pokaz;
	   }
	   public static int getZamowienieDlugosc(){
		    int i = 0;
		   try{
			    ResultSet q = stmt.executeQuery("SELECT idklient FROM zamowienie;");
	   			while(q.next()){
	   				i++;
	   			}
	   						
		   }catch(SQLException e){}
		   return i;
	   }
	   public static String[] getOpis_zamowienia(){
		   int[] ilosc = new int[idopis_zamowienia.length];
		   String[] nazwa = new String[idopis_zamowienia.length];
		   double[] cena = new double[idopis_zamowienia.length];
		   double[] razem = new double[idopis_zamowienia.length];
		   String[] pokaz = new String[idopis_zamowienia.length];
		   try{
			    ResultSet q = stmt.executeQuery("SELECT * FROM opis_zamowienia;");
			    int[] zamowienie = new int[idzamowienie.length];
			    int i = 0;
	   			while(q.next()){
	   				zamowienie[i]=q.getInt("idzamowienie");
	   				nazwa[i]=q.getString("nazwa");
	   				ilosc[i]=q.getInt("ilosc");
	   				cena[i]=q.getDouble("cena");
	   				razem[i]=q.getDouble("razem");
	   				pokaz[i] =nazwa[i]+"    |    "+ilosc[i]+"    |    "+cena[i]+"    |    "+razem[i];
	   				i++;
	   			}		
		   }catch(SQLException e){}
		   return pokaz;
	   }
	   public static int getOpis_zamowieniaDlugosc(){
		    int i = 0;
		   try{
			    ResultSet q = stmt.executeQuery("SELECT idopis_zamowienia FROM opis_zamowienia;");
	   			while(q.next()){
	   				i++;
	   			}
	   						
		   }catch(SQLException e){}
		   return i;
	   }
	   public static int[] getPesele(){
		   int h = model1.getRowCount();
		   int[] pesele = new int[h];
		   for(int i=0;i<h;i++){
			   pesele[i] = Integer.parseInt(model1.getValueAt(i,3).toString());
		   }
		   return pesele;
	   }
	   public static void editOpisNazwa(int row, String data){
	    		boolean pozwolenie=false;
		        try{
		        	for(String n : kawki){
		        		if(n.equals(data)){
		        			pozwolenie=true;
		        		}
		        	}
			        }catch ( NullPointerException ee){	
			        }
		        ResultSet q;
		        int j = idopis_zamowienia[row+1];
		        int ilosc=1,ilosc1=0;
		        try{
			        q = stmt.executeQuery("SELECT ilosc FROM opis_zamowienia WHERE idopis_zamowienia = "+j+";");
	        		q.next();
	        		 ilosc=q.getInt("ilosc");
	        		 q = stmt.executeQuery("SELECT ilosc FROM kawa WHERE nazwa = '"+data+"';");
	         		q.next();
	         		ilosc1=q.getInt("ilosc");
		        } catch(SQLException e){}
			        if(ilosc<= ilosc1 && !data.equals("") && pozwolenie==true){			    
		        	try{
			 			 String sql="UPDATE kawa SET ilosc = ilosc - "+ilosc+" WHERE nazwa = '"+data+"';";
			 			  stmt.execute(sql);
			        		 q = stmt.executeQuery("SELECT idkawa FROM opis_zamowienia WHERE idopis_zamowienia = "+j+";");
				         	q.next();
				         	int id=q.getInt("idkawa");
				         	 sql="UPDATE kawa SET ilosc = ilosc + "+ilosc+" WHERE idkawa = "+id+";";
				 			  stmt.execute(sql);
		 			   q = stmt.executeQuery("SELECT cena FROM kawa WHERE nazwa = '"+data+"';");
		 			   q.next();
		 			   double cena = q.getDouble("cena");
		 			  q = stmt.executeQuery("SELECT idkawa FROM kawa WHERE nazwa = '"+data+"';");
		        		q.next();
		        		int idkawa=q.getInt("idkawa");
		        		 sql="UPDATE opis_zamowienia SET nazwa = '"+data+"' WHERE idopis_zamowienia = "+j+";";
			 			stmt.execute(sql);
		 			  sql="UPDATE opis_zamowienia SET cena = "+cena+" WHERE idopis_zamowienia = "+j+";";
		 			  stmt.execute(sql);
		 			  sql="UPDATE opis_zamowienia SET idkawa = "+idkawa+" WHERE idopis_zamowienia = "+j+";";
		 			  stmt.execute(sql);
		 			  sql="UPDATE opis_zamowienia SET razem = "+cena+" * ilosc WHERE idopis_zamowienia = "+j+";";
		 			  stmt.execute(sql);
		 		   } catch (SQLException z){
		 			   z.printStackTrace();
		 		   }        	
		        }

	   }
	   
	   public static void editOpisIlosc(int row, String data){
   		ResultSet q;
   		int j = idopis_zamowienia[row+1];
	        try{
	        		q = stmt.executeQuery("SELECT idkawa FROM opis_zamowienia WHERE idopis_zamowienia = "+j+";");
	        		q.next();
	        		int idkawa=q.getInt("idkawa");
	        		q = stmt.executeQuery("SELECT ilosc FROM kawa WHERE idkawa = "+idkawa+";");
	 			   q.next();
	 			   int ilosc1 = q.getInt("ilosc");
	 			   System.out.println(ilosc1);
	 				  q = stmt.executeQuery("SELECT ilosc FROM opis_zamowienia WHERE idopis_zamowienia = "+j+";");
	 				  q.next();
	 				 int ilosc2 = q.getInt("ilosc");
	 			   int l = Integer.parseInt(data);
	 			  ilosc2=ilosc1+ilosc2;
	 			   if(l>0 && l<=ilosc2 && !data.equals("")){	 
	 				   l=ilosc2-l;
	 				   System.out.println(l);
	 				   String sql="UPDATE kawa SET ilosc = "+l+" WHERE idkawa = "+idkawa+";";
	 				   stmt.execute(sql);
	 				   sql="UPDATE opis_zamowienia SET ilosc = "+Integer.parseInt(data)+" WHERE idopis_zamowienia = "+j+";";
	 				   stmt.execute(sql);
	 				   sql="UPDATE opis_zamowienia SET razem = ilosc * cena WHERE idopis_zamowienia = "+j+";";
	 				   stmt.execute(sql);
	 			   }
		        }catch ( SQLException ee){	
		        }
  }
	   public static void refreshKlient(){
		   try{
			   tester=1;
			   int k = model1.getRowCount();   
			   for(int i = k-1;i>=0;i--){
				   System.out.println(k);
				   model1.removeRow(i);
			   }
			   licznik1=0;
               int[] liczby = new int[2];
               String[] stringi = new String[6];
			   ResultSet result;
			   result = stmt.executeQuery("SELECT * FROM klient;");
			   while(result.next()){
				   licznik1++;
				   idklient[licznik1] = result.getInt("idklient");
				   liczby[0] = result.getInt("pesel");
				   liczby[1] = result.getInt("nr_domu");
				   stringi[0] = result.getString("imie");
				   stringi[1] = result.getString("nazwisko");
				   stringi[2] = result.getString("ulica");
				   stringi[3] = result.getString("kod_pocztowy");
				   stringi[4] = result.getString("miasto");
				   stringi[5] = result.getString("kraj");
				   model1.addRow(new Object[]{licznik1,stringi[0],stringi[1],liczby[0],stringi[2],liczby[1],stringi[3],stringi[4],stringi[5]});
           }
			   tester=0;
			   System.out.println("KLIENCI");
			   for(int ks : idklient){
				   System.out.println(ks);
			   }
       } catch (SQLException e) {
           System.err.println("Blad przy wczytywaniu klienta");
           e.printStackTrace();
       }
	   }
		   public static void refreshZamowienie(){
			   try{
				   tester=1;
				   int k = model2.getRowCount();
				   for(int i = k-1;i>=0;i--){
					   model2.removeRow(i);
				   }
				   licznik2=0;
				   ResultSet result;
	                result = stmt.executeQuery("SELECT * FROM zamowienie;");
	                while(result.next()){
	                	licznik2++;
	                	idzamowienie[licznik2] = result.getInt("idzamowienie");
	                	int ilosc = result.getInt("ilosc");
	                	String nazwa = result.getString("nazwa");
	                	String data_zam = result.getString("data_zam");
	                	model2.addRow(new Object[]{licznik2,ilosc,nazwa,data_zam});
	           }
	                System.out.println("ZAMOWIENIA");
	                tester=0;
	                for(int ks : idzamowienie){
	                	
	 				   System.out.println(ks);
	 			   }
	       } catch (SQLException e) {
	           System.err.println("Blad przy wczytywaniu klienta");
	           e.printStackTrace();
	       }
	   }
		   public static void refreshOpis_zamowienia(){
			   try{
				   tester=1;
				   int k = model3.getRowCount();
				   System.out.println("opis zamowienia: "+k);
				   for(int i = k-1;i>=0;i--){
					   model3.removeRow(i);
				   }
				   licznik3=0;
				   ResultSet result;
	                result = stmt.executeQuery("SELECT * FROM opis_zamowienia;");
	                while(result.next()){
	                	licznik3++;
	                	idopis_zamowienia[licznik3] = result.getInt("idopis_zamowienia");
		                String nazwa = result.getString("nazwa");
	                	int ilosc = result.getInt("ilosc");
	                	double cena = result.getDouble("cena");
	                	double razem = result.getDouble("razem");
	                	model3.addRow(new Object[]{licznik3,nazwa,ilosc,cena,razem});
	           }
	                tester=0;
	                System.out.println("OPISY");
	       } catch (SQLException e) {
	           System.err.println("Blad przy wczytywaniu klienta");
	           e.printStackTrace();
	       }
	   }
		   public static void refreshKawa(){
			   try{
				   tester=1;
				   int k = model4.getRowCount();
				   for(int i = k-1;i>=0;i--){
					   model4.removeRow(i);
				   }
				   licznik4=0;
				   int ilosc;
				   double cena;
				   String nazwa;
				   ResultSet result;
	                result = stmt.executeQuery("SELECT * FROM kawa;");
	                while(result.next()){
	                	licznik4++;
	                	idkawa[licznik4] = result.getInt("idkawa");
	                	ilosc = result.getInt("ilosc");
	                	cena = result.getDouble("cena");
	                	nazwa = result.getString("nazwa");
	                	model4.addRow(new Object[]{licznik4,nazwa,cena,ilosc});
	                	kawki[licznik4-1] = nazwa;
	                }
	                System.out.println("KAWY");
	                tester=0;
	                for(int ks : idkawa){
	                	
	 				   System.out.println(ks);
	 			   }
	       } catch (SQLException e) {
	           System.err.println("Blad przy wczytywaniu klienta");
	           e.printStackTrace();
	       }
	   }
		public static void Usun1(int[] k){
			tester=1;
			for(int i : k){
				i++;
				int id=idklient[i];
				System.out.println("xd"+i);
				try{
					String sql="DELETE FROM klient WHERE idklient = "+id+";";
					stmt.execute(sql);
					scrollPane.setViewportView(table1);
				}catch (SQLException e){
					System.err.println("Blad przy usuwaniu klienta");
				}				
			}
			refreshKlient();
			refreshZamowienie();
			refreshOpis_zamowienia();
		}
		public static void Usun2(int[] k){
			tester=1;
			for(int i : k){
				i++;
				int id=idzamowienie[i];
				System.out.println("xd"+i);
				try{
					String sql="DELETE FROM zamowienie WHERE idzamowienie = "+id+";";
					stmt.execute(sql);
					scrollPane.setViewportView(table2);
				}catch (SQLException e){
					System.err.println("Blad przy usuwaniu zamowienia");
				}				
			}
			refreshKlient();
			refreshZamowienie();
			refreshOpis_zamowienia();
		}
		public static void Usun3(int[] k){
			tester=1;
			ResultSet q;
			String sql;
			int ilosc;
			int idk;
			for(int i : k){
				i++;
				int id=idopis_zamowienia[i];
				System.out.println("xd"+i);
				try{
					q = stmt.executeQuery("SELECT * FROM opis_zamowienia WHERE idopis_zamowienia = "+id+";");
					q.next();
					ilosc=q.getInt("ilosc");
					idk=q.getInt("idkawa");
					sql="UPDATE kawa SET ilosc = ilosc + "+ilosc+" WHERE idkawa = "+idk+";";
					stmt.execute(sql);
					sql="DELETE FROM opis_zamowienia WHERE idopis_zamowienia = "+id+";";
					stmt.execute(sql);		
					scrollPane.setViewportView(table3);
				}catch (SQLException e){
					System.err.println("Blad przy usuwaniu kawa");
				}				
			}
			refreshKawa();
			refreshZamowienie();
			refreshOpis_zamowienia();
		}
		public static void Usun4(int[] k){
			tester=1;
			for(int i : k){
				i++;
				int id=idkawa[i];
				System.out.println("xd"+i);
				try{
					String sql="DELETE FROM kawa WHERE idkawa = "+id+";";
					stmt.execute(sql);
					scrollPane.setViewportView(table4);
				}catch (SQLException e){
					System.err.println("Blad przy usuwaniu kawa");
				}				
			}
			refreshKawa();
			refreshZamowienie();
			refreshOpis_zamowienia();
		}
		public static void Wyswietlj1(int[] k){
			tester=1;
			ResultSet result;
			int idk;
			wybrane1j=k;
			try{
				int f = modelj1.getRowCount();   
				   for(int i = f-1;i>=0;i--){
					   System.out.println(f);
					   modelj1.removeRow(i);
				   }				 
				for(int i : k){
					int[] liczby = new int[2];
					String[] stringi = new String[6];
					i++;
					idk=idklient[i];
					System.out.println("XD");
					result=stmt.executeQuery("SELECT * FROM klient WHERE idklient = "+idk+";");
					result.next();
					liczby[0] = result.getInt("pesel");
					liczby[1] = result.getInt("nr_domu");
					   stringi[0] = result.getString("imie");
					   stringi[1] = result.getString("nazwisko");
					   stringi[2] = result.getString("ulica");
					   stringi[3] = result.getString("kod_pocztowy");
					   stringi[4] = result.getString("miasto");
					   stringi[5] = result.getString("kraj");
					   modelj1.addRow(new Object[]{i,stringi[0],stringi[1],liczby[0],stringi[2],liczby[1],stringi[3],stringi[4],stringi[5]});
					   
				}				
			}catch (SQLException e){
				System.err.println("Blad przy wyswietlaniu klienta");
			}
			 scrollPane.setViewportView(tablej1);
			 tester=0;
			}
		public static void Wyswietlj2(int[] k){
			tester=1;
			ResultSet result;
			int idk;
			wybrane2j=k;
			try{
				int f = modelj2.getRowCount();   
				   for(int i = f-1;i>=0;i--){
					   System.out.println(f);
					   modelj2.removeRow(i);
				   }
				for(int i : k){
					i++;
					idk=idzamowienie[i];
					result=stmt.executeQuery("SELECT * FROM zamowienie WHERE idzamowienie = "+idk+";");
					result.next();
                	int ilosc = result.getInt("ilosc");
                	String nazwa = result.getString("nazwa");
                	String data_zam = result.getString("data_zam");
                	modelj2.addRow(new Object[]{i,ilosc,nazwa,data_zam});
				}
				scrollPane.setViewportView(tablej2);
			}catch (SQLException e){
				System.err.println("Blad przy wyswietlaniu klienta");
			}
			tester=0;
			}
		public static void Wyswietlj3(int[] k){
			tester=1;
			ResultSet result;
			int idk;
			wybrane3j=k;
			try{
				int f = modelj3.getRowCount();   
				   for(int i = f-1;i>=0;i--){
					   System.out.println(f);
					   modelj3.removeRow(i);
				   }
				for(int i : k){
					i++;
					idk=idopis_zamowienia[i];
					result=stmt.executeQuery("SELECT * FROM opis_zamowienia WHERE idopis_zamowienia = "+idk+";");
					result.next();
					String nazwa = result.getString("nazwa");
                	int ilosc = result.getInt("ilosc");
                	double cena = result.getDouble("cena");
                	double razem = result.getDouble("razem");
                	modelj3.addRow(new Object[]{i,nazwa,ilosc,cena,razem});
				}
				scrollPane.setViewportView(tablej3);
			}catch (SQLException e){
				System.err.println("Blad przy usuwaniu klienta");
			}
			tester=0;
			}
		public static void Wyswietlj4(int[] k){
			tester=1;
			ResultSet result;
			int idk;
			wybrane4j=k;
			try{
				int f = modelj4.getRowCount();   
				   for(int i = f-1;i>=0;i--){
					   System.out.println(f);
					   modelj4.removeRow(i);
				   }
				for(int i : k){
					i++;
					idk=idkawa[i];
					result=stmt.executeQuery("SELECT * FROM kawa WHERE idkawa = "+idk+";");
					result.next();
					int ilosc = result.getInt("ilosc");
                	double cena = result.getDouble("cena");
                	String nazwa = result.getString("nazwa");
                	modelj4.addRow(new Object[]{i,nazwa,cena,ilosc});
				}
				scrollPane.setViewportView(tablej4);
			}catch (SQLException e){
				System.err.println("Blad przy usuwaniu klienta");
			}
			tester=0;
			}
	   
}

