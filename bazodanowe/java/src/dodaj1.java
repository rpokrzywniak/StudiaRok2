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

import javax.swing.JLabel;

class dodaj1 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public dodaj1() {
		setTitle("DODAJ KLIENTA");
		setBounds(100, 100, 450, 321);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{434, 0};
		gridBagLayout.rowHeights = new int[]{155, 33, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
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
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblImie = new JLabel("imie");
			GridBagConstraints gbc_lblImie = new GridBagConstraints();
			gbc_lblImie.anchor = GridBagConstraints.WEST;
			gbc_lblImie.insets = new Insets(0, 0, 5, 5);
			gbc_lblImie.gridx = 0;
			gbc_lblImie.gridy = 0;
			contentPanel.add(lblImie, gbc_lblImie);
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
			JLabel lblNazwisko = new JLabel("nazwisko");
			GridBagConstraints gbc_lblNazwisko = new GridBagConstraints();
			gbc_lblNazwisko.anchor = GridBagConstraints.WEST;
			gbc_lblNazwisko.insets = new Insets(0, 0, 5, 5);
			gbc_lblNazwisko.gridx = 0;
			gbc_lblNazwisko.gridy = 1;
			contentPanel.add(lblNazwisko, gbc_lblNazwisko);
		}
		{
			textField_1 = new JTextField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 5, 0);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 1;
			contentPanel.add(textField_1, gbc_textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel lblPesel = new JLabel("pesel");
			GridBagConstraints gbc_lblPesel = new GridBagConstraints();
			gbc_lblPesel.insets = new Insets(0, 0, 5, 5);
			gbc_lblPesel.anchor = GridBagConstraints.WEST;
			gbc_lblPesel.gridx = 0;
			gbc_lblPesel.gridy = 2;
			contentPanel.add(lblPesel, gbc_lblPesel);
		}
		{
			textField_2 = new JTextField();
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.insets = new Insets(0, 0, 5, 0);
			gbc_textField_2.gridx = 1;
			gbc_textField_2.gridy = 2;
			contentPanel.add(textField_2, gbc_textField_2);
			textField_2.setColumns(10);
		}
		{
			JLabel lblUlica = new JLabel("ulica");
			GridBagConstraints gbc_lblUlica = new GridBagConstraints();
			gbc_lblUlica.insets = new Insets(0, 0, 5, 5);
			gbc_lblUlica.anchor = GridBagConstraints.WEST;
			gbc_lblUlica.gridx = 0;
			gbc_lblUlica.gridy = 3;
			contentPanel.add(lblUlica, gbc_lblUlica);
		}
		{
			textField_3 = new JTextField();
			GridBagConstraints gbc_textField_3 = new GridBagConstraints();
			gbc_textField_3.insets = new Insets(0, 0, 5, 0);
			gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_3.gridx = 1;
			gbc_textField_3.gridy = 3;
			contentPanel.add(textField_3, gbc_textField_3);
			textField_3.setColumns(10);
		}
		{
			JLabel lblNrDomu = new JLabel("nr domu");
			GridBagConstraints gbc_lblNrDomu = new GridBagConstraints();
			gbc_lblNrDomu.insets = new Insets(0, 0, 5, 5);
			gbc_lblNrDomu.anchor = GridBagConstraints.WEST;
			gbc_lblNrDomu.gridx = 0;
			gbc_lblNrDomu.gridy = 4;
			contentPanel.add(lblNrDomu, gbc_lblNrDomu);
		}
		{
			textField_4 = new JTextField();
			GridBagConstraints gbc_textField_4 = new GridBagConstraints();
			gbc_textField_4.insets = new Insets(0, 0, 5, 0);
			gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_4.gridx = 1;
			gbc_textField_4.gridy = 4;
			contentPanel.add(textField_4, gbc_textField_4);
			textField_4.setColumns(10);
		}
		{
			JLabel lblKodPocztowy = new JLabel("kod pocztowy");
			GridBagConstraints gbc_lblKodPocztowy = new GridBagConstraints();
			gbc_lblKodPocztowy.insets = new Insets(0, 0, 5, 5);
			gbc_lblKodPocztowy.anchor = GridBagConstraints.WEST;
			gbc_lblKodPocztowy.gridx = 0;
			gbc_lblKodPocztowy.gridy = 5;
			contentPanel.add(lblKodPocztowy, gbc_lblKodPocztowy);
		}
		{
			textField_5 = new JTextField();
			GridBagConstraints gbc_textField_5 = new GridBagConstraints();
			gbc_textField_5.insets = new Insets(0, 0, 5, 0);
			gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_5.gridx = 1;
			gbc_textField_5.gridy = 5;
			contentPanel.add(textField_5, gbc_textField_5);
			textField_5.setColumns(10);
		}
		{
			JLabel lblMiasto = new JLabel("miasto");
			GridBagConstraints gbc_lblMiasto = new GridBagConstraints();
			gbc_lblMiasto.insets = new Insets(0, 0, 5, 5);
			gbc_lblMiasto.anchor = GridBagConstraints.WEST;
			gbc_lblMiasto.gridx = 0;
			gbc_lblMiasto.gridy = 6;
			contentPanel.add(lblMiasto, gbc_lblMiasto);
		}
		{
			textField_6 = new JTextField();
			GridBagConstraints gbc_textField_6 = new GridBagConstraints();
			gbc_textField_6.insets = new Insets(0, 0, 5, 0);
			gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_6.gridx = 1;
			gbc_textField_6.gridy = 6;
			contentPanel.add(textField_6, gbc_textField_6);
			textField_6.setColumns(10);
		}
		{
			JLabel lblKraj = new JLabel("kraj");
			GridBagConstraints gbc_lblKraj = new GridBagConstraints();
			gbc_lblKraj.anchor = GridBagConstraints.WEST;
			gbc_lblKraj.insets = new Insets(0, 0, 0, 5);
			gbc_lblKraj.gridx = 0;
			gbc_lblKraj.gridy = 7;
			contentPanel.add(lblKraj, gbc_lblKraj);
		}
		{
			textField_7 = new JTextField();
			GridBagConstraints gbc_textField_7 = new GridBagConstraints();
			gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_7.gridx = 1;
			gbc_textField_7.gridy = 7;
			contentPanel.add(textField_7, gbc_textField_7);
			textField_7.setColumns(10);
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
		         if( command.equals( "OK" ))  {
		        	 String imie = textField.getText().replaceAll("\\s+","");
		        	 String nazwisko = textField_1.getText().replaceAll("\\s+","");
		        	 int pesel = Integer.parseInt(textField_2.getText().replaceAll("\\s+",""));
		        	 String ulica = textField_3.getText().replaceAll("\\s+","");
		        	 int nr_domu = Integer.parseInt(textField_4.getText().replaceAll("\\s+",""));
		        	 String kod_pocztowy = textField_5.getText().replaceAll("\\s+","");
		        	 String miasto = textField_6.getText().replaceAll("\\s+","");
		        	 String kraj = textField_7.getText().replaceAll("\\s+","");
		        	 Okno.getDodaj1(imie,nazwisko,pesel,ulica,nr_domu,kod_pocztowy,miasto,kraj);
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
