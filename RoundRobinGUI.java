package exercice;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;







public class RoundRobinGUI {
	
	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoundRobinGUI window = new RoundRobinGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the application.
	 */
	public RoundRobinGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Création d'une fenetre JFrame pour la saisie des données
		JFrame frame = new JFrame();
		frame.getContentPane().setBackground(new Color(128, 0, 128));
		JTable table = new JTable();		//Creation du tableau

		Object[] columns = {"Process","Arrival Time","Execute Time"};
		DefaultTableModel model = new DefaultTableModel(new String[]{"Process", "Arrival time", "Execute time"}, 0); 
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		table.setBackground(Color.pink);
		table.setForeground(Color.white);
		Font font = new Font("",1,22);
		table.setFont(font);
		table.setRowHeight(30);
		//TextField pour saisir les données
		JTextField textProcess = new JTextField();
		textProcess.setBackground(Color.PINK);
		JTextField textAtime = new JTextField();
		textAtime.setBackground(Color.PINK);
		JTextField textEtime = new JTextField();
		textEtime.setBackground(Color.PINK);
		
		JTextField textQuantum = new JTextField();
		textQuantum.setBackground(Color.PINK);
		JTextField textProcessnb = new JTextField();
		textProcessnb.setBackground(Color.PINK);
		
		
		
		
		//Création des bouttons ADD et Execute
		
		JButton btnAdd = new JButton("ADD");
		JButton btnExecute = new JButton("Execute");
		btnExecute.setBackground(Color.PINK);
		
		//Le boutton Execute sert à exécuter le programme et afficher le résultat
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//Création d'une nouvelle fenêtre JFrame pour l'affichage du résultat
				JFrame framen = new JFrame();
				framen.getContentPane().setBackground(new Color(128, 0, 128));
				framen.setTitle("CPU Scheduling Round Robin Gantt Chart");
				
				
				
				JPanel panel = new JPanel();
				panel.setBounds(18, 284, 632, 88);
				framen.getContentPane().add(panel);
				panel.setBackground(new Color(128, 0, 128));
				
				int btime[],rtime[],atime[];
				
				btime = new int[10]; //Création du tableau temps d'exécution
				rtime = new int[10]; //Création du tableau temps restant
				atime = new int[10]; //Création du tableau temps d'arrivée
				
				//nbp représente le nombre de processus
				int nbp = Integer.parseInt(textProcessnb.getText()) ;
				
				//Temps d'arrivée
				for(int i=0;i<nbp ;i++)
				{
					atime[i] = Integer.parseInt((String) model.getValueAt(i, 1));
				}
				
				//Temps d'exécution
				for(int i=0;i<nbp;i++)
				{
					btime[i] = Integer.parseInt((String) model.getValueAt(i, 2));
					rtime[i] = btime[i];
					
				}
				
				
				//q représente le quantum de temps
				int q = Integer.parseInt(textQuantum.getText());
				
				//Calcul du temps d'exécution total
				int btsum = 0;
				for(int i=0;i< table.getRowCount();i++)
				{
					btsum = btsum + Integer.parseInt(table.getValueAt(i,2).toString());
				}
				
				textField.setText(Integer.toString(btsum));
				
				
				
				//rp représente le nombre de processus restant
				int rp = nbp; 
				int time = 0;
				
				
				
				for(int i=0;i<nbp;i++)
				{
					{while(rp!=0)  //Tant qu'il reste des processus à exécuter
					{if(atime[i] <= time) 

							if(rtime[i] > q )
							{
								JTextArea textArea1 = new JTextArea();
								textArea1.setText((q) +" unités | \t \n");
								textArea1.setBackground(Color.PINK);
								textArea1.setEditable(false);
								textArea1.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
								panel.add(textArea1);
								rtime[i] = rtime[i] - q;
								JTextArea textArea2 = new JTextArea();
								textArea2.setText("P" + (i)+  "\n");
								textArea2.setBackground(Color.PINK);
								textArea2.setEditable(false);
								textArea2.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
								panel.add(textArea2);
								time+=q;
								JTextArea textArea3 = new JTextArea();
								textArea3.setText("\n" + (time));
								textArea3.setBackground(Color.PINK);
								textArea3.setEditable(false);
								textArea3.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
								panel.add(textArea3);
							}
							else if (rtime[i] <= q && rtime[i]>0)
							{
								
								time+=rtime[i];
								JTextArea textArea1 = new JTextArea();
								textArea1.setText((rtime[i]) + "unités | \t \n");
								textArea1.setBackground(Color.PINK);
								textArea1.setEditable(false);
								textArea1.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
								panel.add(textArea1);
								
								rtime[i] = rtime[i] - rtime[i];
								JTextArea textArea2 = new JTextArea();
								textArea2.setText("P" + (i));
								textArea2.setBackground(Color.PINK);
								textArea2.setEditable(false);
								textArea2.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
								panel.add(textArea2);
								
								rp --; 
								JTextArea textArea4 = new JTextArea();
								textArea4.setText("Fin de P" +(i));
								textArea4.setBackground(Color.PINK);
								textArea4.setEditable(false);
								textArea4.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
								panel.add(textArea4);
								

								JTextArea textArea3 = new JTextArea();
								textArea3.setText("\n" + (time));
								textArea3.setBackground(Color.PINK);
								textArea3.setEditable(false);
								textArea3.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
								panel.add(textArea3);
								
							}
						
						i++;
						
					if(i==nbp)
					{
						i=0;
					}
					}
				}
				}
				
				
				framen.setSize(1200, 200);
				framen.setLocationRelativeTo(null);
				framen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				framen.setVisible(true);
			}
	
		});
			
		
		 
		 
		//Création des bouttons Delete et Update
		
		JButton btnDelete = new JButton("Delete");
		JButton btnUpdate = new JButton("Update");
		
		
		
		
		textProcess.setBounds(156, 65, 100, 25);
		textAtime.setBounds(156, 102, 100, 25);
		textEtime.setBounds(156, 139, 100, 25);
		textQuantum.setBounds(156,176,100,25);
		textProcessnb.setBounds(156,213,100,25);
		
		
		btnAdd.setBounds(281, 67, 100, 25);
		btnExecute.setBounds(673, 335, 110, 25);
		btnDelete.setBounds(281, 141, 100, 25);
		btnUpdate.setBounds(281, 104, 100, 25);
		
		
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(441, 6, 439, 194);
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(pane);
		frame.getContentPane().add(textProcess);
		frame.getContentPane().add(textAtime);
		frame.getContentPane().add(textEtime);
		frame.getContentPane().add(textQuantum);
		frame.getContentPane().add(textProcessnb);
		frame.getContentPane().add(btnAdd);
		frame.getContentPane().add(btnExecute);
		frame.getContentPane().add(btnUpdate);
		frame.getContentPane().add(btnDelete);
	
		
		JLabel lblNewLabel = new JLabel("Process ID");
		lblNewLabel.setBounds(20, 70, 100, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Arrival time");
		lblNewLabel_1.setBounds(20, 107, 100, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Execute time");
		lblNewLabel_2.setBounds(20, 144, 88, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Round Robin");
		lblNewLabel_3.setForeground(Color.PINK);
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_3.setBackground(Color.PINK);
		lblNewLabel_3.setBounds(15, 18, 203, 35);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Quantum");
		lblNewLabel_4.setBounds(20, 181, 61, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Process number");
		lblNewLabel_5.setBounds(20, 218, 100, 16);
		frame.getContentPane().add(lblNewLabel_5);
		
		//Le boutton EXIT sert à quitter la fenêtre
		JButton btnNewButton = new JButton("EXIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setBounds(777, 333, 103, 29);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBackground(Color.PINK);
		textField.setBounds(166, 250, 110, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Total execution time");
		lblNewLabel_6.setBounds(20, 256, 153, 16);
		frame.getContentPane().add(lblNewLabel_6);
		
		lblNewLabel_7.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		lblNewLabel_7.setForeground(Color.PINK);
		lblNewLabel_7.setBounds(15, 356, 105, 16);
		frame.getContentPane().add(lblNewLabel_7);
		
		
		
		Object[] row = new Object[3];
		//Le boutton ADD sert à ajouter de nouveaux processus 
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				row[0] = textProcess.getText();
				row[1] = textAtime.getText();
				row[2] = textEtime.getText();
				
				model.addRow(row);
				
				
			}});
		
		//Le boutton Delete sert à supprimer une ligne seléctionnée dans le tableau
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if(i >= 0) {
					model.removeRow(i);
				}
				else {
					System.out.println("Delete Error");
				}
				
				
			}});
		
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int i = table.getSelectedRow();
				textProcess.setText(model.getValueAt(i, 0).toString());
				textAtime.setText(model.getValueAt(i, 1).toString());
				textEtime.setText(model.getValueAt(i, 2).toString());
				
			}
		});
		
		
		//Le boutton Update sert à mettre à jour une ligne sélectionnée dans le tableau
		btnUpdate.addActionListener(new ActionListener( ) {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int i = table.getSelectedRow();
				if(i >= 0)
				{
					model.setValueAt(textProcess.getText(), i, 0);
					model.setValueAt(textAtime.getText(), i, 1);
					model.setValueAt(textEtime.getText(), i, 2);
				}
				else {
					System.out.println("Update Error");
				}
				
				
				
			}});
		
		
		
		frame.setSize(900, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
