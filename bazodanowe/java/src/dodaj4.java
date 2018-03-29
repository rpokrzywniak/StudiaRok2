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

class dodaj4 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNazwa;
	private JLabel lblCena;
	private JLabel lblIlo;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public dodaj4() {
		setTitle("DODAJ KAWĘ");
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
			lblNazwa = new JLabel("nazwa");
			GridBagConstraints gbc_lblNazwa = new GridBagConstraints();
			gbc_lblNazwa.insets = new Insets(0, 0, 5, 5);
			gbc_lblNazwa.anchor = GridBagConstraints.WEST;
			gbc_lblNazwa.gridx = 0;
			gbc_lblNazwa.gridy = 0;
			contentPanel.add(lblNazwa, gbc_lblNazwa);
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
			lblCena = new JLabel("cena");
			GridBagConstraints gbc_lblCena = new GridBagConstraints();
			gbc_lblCena.insets = new Insets(0, 0, 5, 5);
			gbc_lblCena.anchor = GridBagConstraints.WEST;
			gbc_lblCena.gridx = 0;
			gbc_lblCena.gridy = 1;
			contentPanel.add(lblCena, gbc_lblCena);
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
			lblIlo = new JLabel("ilość");
			GridBagConstraints gbc_lblIlo = new GridBagConstraints();
			gbc_lblIlo.insets = new Insets(0, 0, 5, 5);
			gbc_lblIlo.anchor = GridBagConstraints.WEST;
			gbc_lblIlo.gridx = 0;
			gbc_lblIlo.gridy = 2;
			contentPanel.add(lblIlo, gbc_lblIlo);
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
	        	 String nazwa = textField.getText().replaceAll("\\s+","");
	        	 double cena = Double.parseDouble(textField_1.getText().replaceAll("\\s+",""));
	        	 int ilosc = Integer.parseInt(textField_2.getText().replaceAll("\\s+",""));
	        	 Okno.getDodaj4(nazwa,cena,ilosc);
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
