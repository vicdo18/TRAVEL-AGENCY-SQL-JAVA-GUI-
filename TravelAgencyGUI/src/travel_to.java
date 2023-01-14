import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class travel_to extends JDialog{
    private JPanel travel_to;
    private JTextField textToArrival;
    private JTextField textToDeparture;
    private JButton confirmedButton;
    private JLabel Result;
    private JComboBox BoxToTripID;
    private JComboBox BoxToDstID;

    public travel_to(JFrame parent) {
        super(parent);

        setTitle("travel_to");
        setContentPane(travel_to);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        insertTravelTo();


    }

    public void insertTravelTo(){

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "soulele3059@");

            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT tr_id FROM trip");
            while (resultSet.next()) {

                int TripID= resultSet.getInt("tr_id");
                BoxToTripID.addItem(TripID);

            }
            BoxToTripID.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int to_tr_id= (int) BoxToTripID.getSelectedItem();

                }
            });
            int to_tr_id= (int) BoxToTripID.getSelectedItem();

            Statement statement2= connection.createStatement();
            ResultSet resultSet2=statement2.executeQuery("SELECT dst_id FROM destintaion");
            while (resultSet2.next()) {

                int DestID= resultSet.getInt("dst_id");
                BoxToDstID.addItem(DestID);

            }
            BoxToDstID.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int to_dst_id= (int) BoxToDstID.getSelectedItem();

                }
            });
            int to_dst_id= (int) BoxToDstID.getSelectedItem();
            setVisible(true);




            Date DepartureDate = Date.valueOf(textToDeparture.getText());
            LocalDate DepartureLocalDate= DepartureDate.toLocalDate();
            String to_departure = DepartureLocalDate.format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));

            Date ReturnDate = Date.valueOf(textToArrival.getText());
            LocalDate ReturnLocalDate= ReturnDate.toLocalDate();
            String to_arrival = ReturnLocalDate.format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));


            String query = "INSERT INTO trip ( to_tr_id ,to_dst_id, to_arrival ,to_departure ) " +
                    "VALUES( ? , ? , ? , ?  )";

            PreparedStatement insertStatementToTrip = connection.prepareStatement(query);

            insertStatementToTrip.setInt(1,to_tr_id);
            insertStatementToTrip.setInt(2, to_dst_id);
            insertStatementToTrip.setString(3, to_arrival);
            insertStatementToTrip.setString(4, to_departure);


            insertStatementToTrip.executeUpdate();

            confirmedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    Result.setText("The insertion achieved: "+"to_tr_id: " + to_tr_id + "to_dst_id : " + to_dst_id + "to_arrival: " + to_arrival + "to_departure: " + to_departure);
                }
            });





        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
