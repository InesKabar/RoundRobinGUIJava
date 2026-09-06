package exercice;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;


public class RoundRobinGUI {

    private JFrame frame;
    private JTextField textField;
    private JTable table;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;


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

        // Create the main application window
        frame = new JFrame();
        frame.setBounds(100, 100, 900, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);


        // Create the table model
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Process");
        model.addColumn("Arrival Time");
        model.addColumn("Execute Time");


        // Create the process table
        table = new JTable(model);
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));
        table.setRowHeight(25);


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 30, 400, 180);
        frame.getContentPane().add(scrollPane);


        // Process label
        JLabel lblProcess = new JLabel("Process");
        lblProcess.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblProcess.setBounds(470, 30, 100, 25);
        frame.getContentPane().add(lblProcess);


        // Process text field
        textField = new JTextField();
        textField.setBounds(570, 30, 150, 25);
        frame.getContentPane().add(textField);
        textField.setColumns(10);


        // Arrival time label
        JLabel lblArrivalTime = new JLabel("Arrival Time");
        lblArrivalTime.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblArrivalTime.setBounds(470, 70, 100, 25);
        frame.getContentPane().add(lblArrivalTime);


        // Arrival time text field
        textField_1 = new JTextField();
        textField_1.setBounds(570, 70, 150, 25);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);


        // Execute time label
        JLabel lblExecuteTime = new JLabel("Execute Time");
        lblExecuteTime.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblExecuteTime.setBounds(470, 110, 100, 25);
        frame.getContentPane().add(lblExecuteTime);


        // Execute time text field
        textField_2 = new JTextField();
        textField_2.setBounds(570, 110, 150, 25);
        frame.getContentPane().add(textField_2);
        textField_2.setColumns(10);


        // Quantum label
        JLabel lblQuantum = new JLabel("Quantum");
        lblQuantum.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblQuantum.setBounds(470, 150, 100, 25);
        frame.getContentPane().add(lblQuantum);


        // Quantum text field
        textField_3 = new JTextField();
        textField_3.setBounds(570, 150, 150, 25);
        frame.getContentPane().add(textField_3);
        textField_3.setColumns(10);


        // Number of processes label
        JLabel lblNumberOfProcesses = new JLabel("Process Number");
        lblNumberOfProcesses.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNumberOfProcesses.setBounds(470, 190, 100, 25);
        frame.getContentPane().add(lblNumberOfProcesses);


        // Number of processes text field
        textField_4 = new JTextField();
        textField_4.setBounds(570, 190, 150, 25);
        frame.getContentPane().add(textField_4);
        textField_4.setColumns(10);


        // ADD button
        JButton btnAdd = new JButton("ADD");
        btnAdd.setBounds(30, 230, 90, 30);
        frame.getContentPane().add(btnAdd);


        btnAdd.addActionListener(e -> {

            // Check that all required fields are filled
            if (textField.getText().trim().isEmpty()
                    || textField_1.getText().trim().isEmpty()
                    || textField_2.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Please fill in all process information."
                );
                return;
            }

            try {

                // Validate that arrival and execution times are numbers
                int arrivalTime = Integer.parseInt(textField_1.getText().trim());
                int executeTime = Integer.parseInt(textField_2.getText().trim());

                if (arrivalTime < 0 || executeTime <= 0) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Arrival time must be >= 0 and execute time must be > 0."
                    );
                    return;
                }

                // Add the process information to the table
                model.addRow(new Object[]{
                        textField.getText().trim(),
                        arrivalTime,
                        executeTime
                });

                // Clear the input fields
                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");

            } catch (NumberFormatException ex) {

                // Display an error when the user enters invalid numbers
                JOptionPane.showMessageDialog(
                        frame,
                        "Arrival Time and Execute Time must be numbers."
                );
            }
        });


        // DELETE button
        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(130, 230, 90, 30);
        frame.getContentPane().add(btnDelete);


        btnDelete.addActionListener(e -> {

            // Get the selected row
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {

                // Ask the user to select a process
                JOptionPane.showMessageDialog(
                        frame,
                        "Please select a process to delete."
                );

            } else {

                // Delete the selected process
                model.removeRow(selectedRow);
            }
        });


        // UPDATE button
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(230, 230, 90, 30);
        frame.getContentPane().add(btnUpdate);


        btnUpdate.addActionListener(e -> {

            // Get the selected row
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {

                // Ask the user to select a process
                JOptionPane.showMessageDialog(
                        frame,
                        "Please select a process to update."
                );
                return;
            }

            try {

                // Validate the arrival and execution times
                int arrivalTime = Integer.parseInt(textField_1.getText().trim());
                int executeTime = Integer.parseInt(textField_2.getText().trim());

                if (arrivalTime < 0 || executeTime <= 0) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Arrival time must be >= 0 and execute time must be > 0."
                    );
                    return;
                }

                // Update the selected process
                model.setValueAt(textField.getText().trim(), selectedRow, 0);
                model.setValueAt(arrivalTime, selectedRow, 1);
                model.setValueAt(executeTime, selectedRow, 2);

                // Clear the input fields
                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");

            } catch (NumberFormatException ex) {

                // Display an error when invalid numbers are entered
                JOptionPane.showMessageDialog(
                        frame,
                        "Arrival Time and Execute Time must be numbers."
                );
            }
        });


        // EXIT button
        JButton btnExit = new JButton("EXIT");
        btnExit.setBounds(330, 230, 90, 30);
        frame.getContentPane().add(btnExit);


        btnExit.addActionListener(e -> {

            // Close the main application
            System.exit(0);
        });


        // Execute button
        JButton btnExecute = new JButton("Execute");
        btnExecute.setBounds(570, 230, 150, 30);
        frame.getContentPane().add(btnExecute);


        btnExecute.addActionListener(e -> {

            // Check that the table contains at least one process
            if (model.getRowCount() == 0) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Please add at least one process."
                );
                return;
            }


            int quantum;

            try {

                // Read and validate the quantum value
                quantum = Integer.parseInt(textField_3.getText().trim());

                if (quantum <= 0) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Quantum must be greater than 0."
                    );
                    return;
                }

            } catch (NumberFormatException ex) {

                // Display an error when the quantum is invalid
                JOptionPane.showMessageDialog(
                        frame,
                        "Quantum must be a valid number."
                );
                return;
            }


            int nbp;

            try {

                // Read the number of processes
                nbp = Integer.parseInt(textField_4.getText().trim());

                if (nbp <= 0 || nbp > model.getRowCount()) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Invalid number of processes."
                    );
                    return;
                }

            } catch (NumberFormatException ex) {

                // Display an error when the process number is invalid
                JOptionPane.showMessageDialog(
                        frame,
                        "Process Number must be a valid number."
                );
                return;
            }


            // Arrays used for the Round Robin algorithm
            int[] btime = new int[nbp];
            int[] rtime = new int[nbp];
            int[] atime = new int[nbp];


            // Store the process information from the table
            for (int i = 0; i < nbp; i++) {

                try {

                    atime[i] = Integer.parseInt(
                            model.getValueAt(i, 1).toString()
                    );

                    btime[i] = Integer.parseInt(
                            model.getValueAt(i, 2).toString()
                    );

                    rtime[i] = btime[i];

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Invalid process information in the table."
                    );
                    return;
                }
            }


            // Create the result window
            JFrame resultFrame = new JFrame(
                    "CPU Scheduling Round Robin Gantt Chart"
            );

            resultFrame.setBounds(150, 150, 800, 500);
            resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            panel.setLayout(null);


            // Create a text area to display the Gantt chart
            JTextArea resultArea = new JTextArea();
            resultArea.setEditable(false);
            resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));


            JScrollPane resultScrollPane = new JScrollPane(resultArea);
            resultScrollPane.setBounds(20, 20, 740, 400);
            panel.add(resultScrollPane);


            resultFrame.getContentPane().add(panel);


            /*
             * Round Robin scheduling algorithm
             */

            // Create the ready queue
            Queue<Integer> readyQueue = new LinkedList<Integer>();


            // Keep track of processes that have already entered the queue
            boolean[] added = new boolean[nbp];


            // Keep track of the number of completed processes
            int completedProcesses = 0;


            // Current CPU time
            int time = 0;


            // Build the Gantt chart output
            StringBuilder output = new StringBuilder();

            output.append("ROUND ROBIN GANTT CHART\n");
            output.append("=======================\n\n");


            while (completedProcesses < nbp) {

                /*
                 * Add all processes that have arrived
                 * before or at the current CPU time.
                 */
                for (int i = 0; i < nbp; i++) {

                    if (!added[i]
                            && atime[i] <= time
                            && rtime[i] > 0) {

                        readyQueue.add(i);
                        added[i] = true;
                    }
                }


                /*
                 * If the ready queue is empty,
                 * move the CPU time to the next arriving process.
                 */
                if (readyQueue.isEmpty()) {

                    int nextArrival = Integer.MAX_VALUE;

                    for (int i = 0; i < nbp; i++) {

                        if (rtime[i] > 0 && atime[i] < nextArrival) {
                            nextArrival = atime[i];
                        }
                    }

                    if (nextArrival != Integer.MAX_VALUE) {

                        output.append(
                                "CPU Idle: "
                                        + time
                                        + " -> "
                                        + nextArrival
                                        + "\n"
                        );

                        time = nextArrival;
                    }

                    continue;
                }


                // Get the next process from the ready queue
                int currentProcess = readyQueue.poll();


                // Calculate the execution time for this quantum
                int executionTime = Math.min(
                        quantum,
                        rtime[currentProcess]
                );


                // Store the beginning time of the execution
                int startTime = time;


                // Update the remaining execution time
                rtime[currentProcess] -= executionTime;


                // Move the CPU time forward
                time += executionTime;


                // Add the execution segment to the Gantt chart
                output.append(
                        "P"
                                + (currentProcess + 1)
                                + " : "
                                + startTime
                                + " -> "
                                + time
                                + "\n"
                );


                /*
                 * Add processes that arrived while
                 * the current process was executing.
                 */
                for (int i = 0; i < nbp; i++) {

                    if (!added[i]
                            && atime[i] <= time
                            && rtime[i] > 0) {

                        readyQueue.add(i);
                        added[i] = true;
                    }
                }


                /*
                 * If the current process still has
                 * remaining execution time, add it
                 * back to the end of the queue.
                 */
                if (rtime[currentProcess] > 0) {

                    readyQueue.add(currentProcess);

                } else {

                    // The process has finished execution
                    completedProcesses++;
                }
            }


            output.append("\n=======================\n");
            output.append("All processes completed.\n");


            // Display the final Gantt chart
            resultArea.setText(output.toString());


            // Display the result window
            resultFrame.setVisible(true);
        });


        /*
         * Fill the text fields when the user
         * selects a process from the table.
         */
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int selectedRow = table.getSelectedRow();

                if (selectedRow != -1) {

                    // Get the selected process information
                    textField.setText(
                            model.getValueAt(selectedRow, 0).toString()
                    );

                    textField_1.setText(
                            model.getValueAt(selectedRow, 1).toString()
                    );

                    textField_2.setText(
                            model.getValueAt(selectedRow, 2).toString()
                    );
                }
            }
        });
    }
}
