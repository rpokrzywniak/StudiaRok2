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


 class dodaj2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField txtYyyymmdd;
	private String kawa1;
	private Choice choice = new Choice();
	int[] wybrane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public dodaj2() {
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
		
		String[] kawy = Okno.getKawy();
		String[] imiona = Okno.getImiona();
		String[] nazwiska = Okno.getNazwiska();
		int[] pesele = Okno.getPesele();
		String[] pokaz = new String[imiona.length];
		for(int i=0;i<imiona.length;i++){
			pokaz[i] = imiona[i]+", "+nazwiska[i]+","+pesele[i];
		}
		
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblIlo = new JLabel("ilość");
			GridBagConstraints gbc_lblIlo = new GridBagConstraints();
			gbc_lblIlo.anchor = GridBagConstraints.WEST;
			gbc_lblIlo.insets = new Insets(0, 0, 5, 5);
			gbc_lblIlo.gridx = 0;
			gbc_lblIlo.gridy = 0;
			contentPanel.add(lblIlo, gbc_lblIlo);
		}
		{
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 0;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		{
			JLabel lblDatazam = new JLabel("data_zam");
			GridBagConstraints gbc_lblDatazam = new GridBagConstraints();
			gbc_lblDatazam.insets = new Insets(0, 0, 5, 5);
			gbc_lblDatazam.anchor = GridBagConstraints.WEST;
			gbc_lblDatazam.gridx = 0;
			gbc_lblDatazam.gridy = 1;
			contentPanel.add(lblDatazam, gbc_lblDatazam);
		}
		{
			txtYyyymmdd = new JTextField();
			txtYyyymmdd.setToolTipText("YYYY-MM-DD");
			GridBagConstraints gbc_txtYyyymmdd = new GridBagConstraints();
			gbc_txtYyyymmdd.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYyyymmdd.insets = new Insets(0, 0, 5, 0);
			gbc_txtYyyymmdd.gridx = 1;
			gbc_txtYyyymmdd.gridy = 1;
			contentPanel.add(txtYyyymmdd, gbc_txtYyyymmdd);
			txtYyyymmdd.setColumns(10);
		}
		{
			JLabel lblNazwa = new JLabel("nazwa");
			GridBagConstraints gbc_lblNazwa = new GridBagConstraints();
			gbc_lblNazwa.insets = new Insets(0, 0, 5, 5);
			gbc_lblNazwa.anchor = GridBagConstraints.WEST;
			gbc_lblNazwa.gridx = 0;
			gbc_lblNazwa.gridy = 2;
			contentPanel.add(lblNazwa, gbc_lblNazwa);
		}
		{
			DefaultListModel<String> model = new DefaultListModel<>();
			JList<String> list = new JList<>( model );
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
			gbc_scrollPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 2;
			//list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			GridBagConstraints gbc_list = new GridBagConstraints();
			gbc_list.insets = new Insets(0, 0, 5, 0);
			gbc_list.fill = GridBagConstraints.BOTH;
			gbc_list.gridx = 1;
			gbc_list.gridy = 2;
			for(String k : kawy){
				model.addElement( k );
			}
			list.addListSelectionListener(new ListSelectionListener() {
				
				public void valueChanged(ListSelectionEvent arg0) {
	                if (!arg0.getValueIsAdjusting()) {
	                	wybrane = list.getSelectedIndices();
	                }
	            }
	        });
			scrollPane.add(list, gbc_list);
			contentPanel.add(scrollPane, gbc_scrollPane);
			scrollPane.setViewportView(list);
		}
		{
			JLabel lblKlient = new JLabel("klient");
			GridBagConstraints gbc_lblKlient = new GridBagConstraints();
			gbc_lblKlient.gridheight = 2;
			gbc_lblKlient.anchor = GridBagConstraints.WEST;
			gbc_lblKlient.insets = new Insets(0, 0, 5, 5);
			gbc_lblKlient.gridx = 0;
			gbc_lblKlient.gridy = 3;
			contentPanel.add(lblKlient, gbc_lblKlient);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridheight = 3;
			gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
			gbc_scrollPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 3;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				for(String k : pokaz){
					choice.add(k);
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
	         if( command.equals( "OK" ) && wybrane.length>0)  {
	        	 int ilosc = Integer.parseInt(textField.getText().replaceAll("\\s+",""));
	        	 String data = txtYyyymmdd.getText().replaceAll("\\s+","");
	        	 try{
	        		 if(data.length()==10){
	        			 String[] nazwy = Okno.getNazwy(wybrane);
	        			 Date date = new SimpleDateFormat("yyyy-MM-dd").parse(data);
	        			 String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
	        			 for(int i = 0;i<nazwy.length;i++){
	        				 Okno.getDodaj2(ilosc,formattedDate,nazwy[i],choice.getSelectedIndex()+1);
	        			 }
	        		 }
	        		 else throw new ParseException("if nie sprawdzony",1);
	        	 } catch (ParseException z){
	        		 System.out.println(z);
	        	 }
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
		  
