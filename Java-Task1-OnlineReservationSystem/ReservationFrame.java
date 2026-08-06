package reservation;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import reservation.DBConnection;


public class ReservationFrame extends JFrame {
    private JTextField nameField, trainNoField, trainNameField, classField, dateField, sourceField, destField;
    private JButton bookButton;

    public ReservationFrame() {
        setTitle("Reservation");
        setSize(400, 400);
        setLayout(null);

        JLabel nameLabel = new JLabel("Passenger Name:");
        nameLabel.setBounds(30, 30, 120, 25);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(160, 30, 150, 25);
        add(nameField);

        JLabel trainNoLabel = new JLabel("Train Number:");
        trainNoLabel.setBounds(30, 70, 120, 25);
        add(trainNoLabel);
        trainNoField = new JTextField();
        trainNoField.setBounds(160, 70, 150, 25);
        add(trainNoField);

        JLabel trainNameLabel = new JLabel("Train Name:");
        trainNameLabel.setBounds(30, 110, 120, 25);
        add(trainNameLabel);
        trainNameField = new JTextField();
        trainNameField.setBounds(160, 110, 150, 25);
        add(trainNameField);

        JLabel classLabel = new JLabel("Class Type:");
        classLabel.setBounds(30, 150, 120, 25);
        add(classLabel);
        classField = new JTextField();
        classField.setBounds(160, 150, 150, 25);
        add(classField);

        JLabel dateLabel = new JLabel("Date (yyyy-mm-dd):");
        dateLabel.setBounds(30, 190, 150, 25);
        add(dateLabel);
        dateField = new JTextField();
        dateField.setBounds(160, 190, 150, 25);
        add(dateField);

        JLabel sourceLabel = new JLabel("Source:");
        sourceLabel.setBounds(30, 230, 120, 25);
        add(sourceLabel);
        sourceField = new JTextField();
        sourceField.setBounds(160, 230, 150, 25);
        add(sourceField);

        JLabel destLabel = new JLabel("Destination:");
        destLabel.setBounds(30, 270, 120, 25);
        add(destLabel);
        destField = new JTextField();
        destField.setBounds(160, 270, 150, 25);
        add(destField);

        bookButton = new JButton("Book");
        bookButton.setBounds(150, 310, 80, 25);
        add(bookButton);

        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookTicket();
            }
        });
    }

    private void bookTicket() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO reservations(passenger_name, train_number, train_name, class_type, date_of_journey, source, destination) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nameField.getText());
            ps.setInt(2, Integer.parseInt(trainNoField.getText()));
            ps.setString(3, trainNameField.getText());
            ps.setString(4, classField.getText());
            ps.setString(5, dateField.getText());
            ps.setString(6, sourceField.getText());
            ps.setString(7, destField.getText());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int pnr = rs.getInt(1);
                JOptionPane.showMessageDialog(this, "Booking Successful! PNR: " + pnr);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
