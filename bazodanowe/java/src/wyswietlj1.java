import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class wyswietlj1 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	int[] wybrane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			wyswietlj1 dialog = new wyswietlj1();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public wyswietlj1() {
		setTitle("WYŚWIETL KLIENTA");
		setBounds(100, 100, 450, 300);
		
		String[] imiona = Okno.getImiona();
		String[] nazwiska = Okno.getNazwiska();
		int[] pesele = Okno.getPesele();
		String[] pokaz = new String[imiona.length];
		for(int i=0;i<imiona.length;i++){
			pokaz[i] = imiona[i]+", "+nazwiska[i]+","+pesele[i];
		}
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{434, 0};
		gridBagLayout.rowHeights = new int[]{228, 33, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.insets = new Insets(0, 0, 5, 0);
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 0;
		getContentPane().add(contentPanel, gbc_contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblZaznaczKlientwKtrych = new JLabel("Zaznacz klientów, których chcesz wyświetlić");
			lblZaznaczKlientwKtrych.setAlignmentY(0.0f);
			lblZaznaczKlientwKtrych.setAlignmentX(Component.LEFT_ALIGNMENT);
			contentPanel.add(lblZaznaczKlientwKtrych, BorderLayout.NORTH);
		}
		{
			DefaultListModel<String> model = new DefaultListModel<>();
			JList<String> list = new JList<>( model );
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			for(String k : pokaz){
				model.addElement( k );
			}
			list.addListSelectionListener(new ListSelectionListener() {
				
				public void valueChanged(ListSelectionEvent arg0) {
	                if (!arg0.getValueIsAdjusting()) {
	                  wybrane = list.getSelectedIndices();
	                }
	            }
	        });
				scrollPane.setViewportView(list);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.anchor = GridBagConstraints.NORTH;
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
	         if( command.equals( "OK" ) && wybrane.length>0 )  {
	        	 for(int i : wybrane){
	        		 System.out.println(i);
	        	 }
	        	Okno.Wyswietlj1(wybrane);
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
