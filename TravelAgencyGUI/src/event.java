import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class event extends JDialog {
    private JPanel event;
    private JTextField textEventStart;
    private JTextField textEventEnd;
    private JTextField textEventDescr;
    private JButton confirmedButton;
    private JLabel Result;
    private JComboBox BoxEventTripID;


    public event(JFrame parent) {
        super(parent);

        setTitle("event");
        setContentPane(event);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        insertEvent();

    }

        public void insertEvent(){

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "soulele3059@");



            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT tr_id FROM trip");
            while (resultSet.next()) {

                int TripID= resultSet.getInt("tr_id");
                BoxEventTripID.addItem(TripID);

            }

            BoxEventTripID.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int ev_tr_id =(int) BoxEventTripID.getSelectedItem();

                }
            });
            setVisible(true);


            int ev_tr_id = (int) BoxEventTripID.getSelectedItem();

            Date startDate = Date.valueOf(textEventStart.getText());
            LocalDate startLocalDate = startDate.toLocalDate();
            String ev_start = startLocalDate.format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));

            Date endDate = Date.valueOf(textEventEnd.getText());
            LocalDate endLocalDate = endDate.toLocalDate();
            String ev_end = endLocalDate.format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));


            String ev_descr = textEventDescr.getText();
            String queryEvent = "INSERT INTO event (ev_tr_id,ev_start,ev_end ,ev_descr) " +
                    "VALUES(? , ? , ? , ?)";

            PreparedStatement insertStatementEvent = connection.prepareStatement(queryEvent);


            insertStatementEvent.setInt(1, ev_tr_id);
            insertStatementEvent.setString(2, ev_start);
            insertStatementEvent.setString(3,ev_end);
            insertStatementEvent.setString(4, ev_descr);


            insertStatementEvent.executeUpdate();


            confirmedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

               Result.setText("The insertion achieved: "+"ev_tr_id: " + ev_tr_id + "ev_srtart : " + ev_start + "ev_end: " + ev_end + "ev_descr: " + ev_descr);

                }
            });
            insertStatementEvent.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
