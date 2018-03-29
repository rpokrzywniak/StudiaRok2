import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import java.awt.Choice;
import java.text.*;
import java.util.*;


 class dodaj3 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField txtYyyymmdd;
	private String kawa1;
	private Choice choice = new Choice();

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public dodaj3() {
		setTitle("DODAJ ZAMÓWIENIE");
		setBounds(100, 100, 454, 403);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{434, 0};
		gridBagLayout.rowHeights = new int[]{98, 33, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.insets = new Insets(0, 0, 5, 0);
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 0;
		getContentPane().add(contentPanel, gbc_contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		
		int dlugosc = Okno.getZamowienieDlugosc();
		String[] pokaz = Okno.getZamowienieKlient();
		String[] data_zam = Okno.getData_zam();
		int[] ilosc = Okno.getIlosc();
		String[] nazwa = Okno.getNazwa();
		for(int i=0;i<dlugosc;i++){
			pokaz[i] = pokaz[i]+"    |    "+nazwa[i]+"    |    "+ilosc[i]+"    |    "+data_zam[i];
		}
				
		contentPanel.setLayout(gbl_contentPanel);
	
		{
			JLabel lblKlient = new JLabel("zamówienie");
			GridBagConstraints gbc_lblKlient = new GridBagConstraints();
			gbc_lblKlient.gridheight = 2;
			gbc_lblKlient.anchor = GridBagConstraints.WEST;
			gbc_lblKlient.insets = new Insets(0, 0, 5, 5);
			gbc_lblKlient.gridx = 0;
			gbc_lblKlient.gridy = 2;
			contentPanel.add(lblKlient, gbc_lblKlient);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridheight = 3;
			gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
			gbc_scrollPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 3;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				for(int i =0; i<dlugosc;i++){
					choice.add(pokaz[i]);;
				}
				scrollPane.setViewportView(choice);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.anchor = GridBagConstraints.SOUTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 0;
			gbc_buttonPane.gridy = 1;
			getContentPane().add(buttonPane, gbc_buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ClickListener());
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ClickListener());
				buttonPane.add(cancelButton);
			}
		}
		
	}
	 private class ClickListener implements ActionListener{
	      public void actionPerformed(ActionEvent e) {
	         String command = e.getActionCommand();  
	         if( command.equals( "OK" ) )  {	        	
	        			 Okno.getDodaj3(choice.getSelectedIndex()+1);
	    	        	 setVisible(false);
	    	        	 dispose();
	        		 }
	         else if( command.equals( "Cancel" )){
	        	 setVisible(false);
	        	 dispose();
	         }
	      }
	 }
}
		  
