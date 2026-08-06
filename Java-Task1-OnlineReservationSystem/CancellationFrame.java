package reservation;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class CancellationFrame extends JFrame {
    private JTextField pnrField;
    private JButton fetchButton, cancelButton;
    private JTextArea detailsArea;

    public CancellationFrame() {
        setTitle("Cancellation");
        setSize(400, 300);
        setLayout(null);

        JLabel pnrLabel = new JLabel("Enter PNR:");
        pnrLabel.setBounds(30, 30, 100, 25);
        add(pnrLabel);

        pnrField = new JTextField();
        pnrField.setBounds(140, 30, 150, 25);
        add(pnrField);

        fetchButton = new JButton("Fetch");
        fetchButton.setBounds(300, 30, 80, 25);
        add(fetchButton);

        detailsArea = new JTextArea();
        detailsArea.setBounds(30, 70, 350, 100);
        add(detailsArea);

        cancelButton = new JButton("Cancel Ticket");
        cancelButton.setBounds(140, 200, 120, 25);
        add(cancelButton);

        fetchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fetchDetails();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelTicket();
            }
        });
    }

    private void fetchDetails() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM reservations WHERE pnr=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(pnrField.getText()));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                detailsArea.setText("Passenger: " + rs.getString("passenger_name") +
                        "\nTrain: " + rs.getString("train_name") +
                        "\nDate: " + rs.getString("date_of_journey") +
                        "\nFrom: " + rs.getString("source") +
                        " To: " + rs.getString("destination"));
            } else {
                JOptionPane.showMessageDialog(this, "PNR not found!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void cancelTicket() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM reservations WHERE pnr=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(pnrField.getText()));
            int rows = ps.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Ticket Cancelled Successfully!");
                detailsArea.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "PNR not found!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CancellationFrame().setVisible(true);
    }
}
