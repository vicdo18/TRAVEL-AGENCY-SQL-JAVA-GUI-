import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class trip extends JDialog {
    private JPanel trip;
    private JTextField textTripID;
    private JTextField textTripDeparture;
    private JTextField textTripReturn;
    private JTextField textTripMaxSeats;
    private JTextField textTripCost;
    private JTextField textTripGuiAT;
    private JTextField textTripDrvAT;
    private JLabel Result;
    private JButton confirmedButton;
    private JComboBox BoxTripBrCode;


    public trip(JFrame parent) {
        super(parent);

        setTitle("trip");
        setContentPane(trip);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        insertTrip();


        setVisible(true);

    }

    public void insertTrip(){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "soulele3059@");



            Date DepartureDate = Date.valueOf(textTripDeparture.getText());
            LocalDate DepartureLocalDate= DepartureDate.toLocalDate();
            String tr_departure = DepartureLocalDate.format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));

            Date ReturnDate = Date.valueOf(textTripReturn.getText());
            LocalDate ReturnLocalDate= ReturnDate.toLocalDate();
            String tr_return = ReturnLocalDate.format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));


            int tr_max_seats= textTripMaxSeats.getX();
            float tr_cost=textTripCost.getAlignmentX();


            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT br_code FROM branch");
            while (resultSet.next()) {

                int Tripbr= resultSet.getInt("br_code");
                BoxTripBrCode.addItem(Tripbr);

            }

            BoxTripBrCode.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int tr_br_code= (int) BoxTripBrCode.getSelectedItem();
                }
            });

            int tr_br_code= (int) BoxTripBrCode.getSelectedItem();

            Statement resultStatementGuide= connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String queryGAT = "SELECT gui_AT FROM guide";
            ResultSet resultGuide = resultStatementGuide.executeQuery(queryGAT);
            resultGuide.last();
            String tr_gui_AT= String.valueOf(resultGuide);

            Statement resultStatementDriver= connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String queryDAT = "SELECT gui_AT FROM guide";
            ResultSet resultDriver = resultStatementDriver.executeQuery(queryDAT);
            resultDriver.last();
            String tr_drv_AT= String.valueOf(resultDriver);


            String query = "INSERT INTO trip (tr_id , tr_departure , tr_return, tr_maxseats , tr_cost , tr_br_code , tr_gui_AT ,tr_drv_AT) " +
                    "VALUES( ? , ? , ? , ? , ? )";

            PreparedStatement insertStatementTrip = connection.prepareStatement(query);
            insertStatementTrip.setObject(1,null);
            insertStatementTrip.setString(2, tr_departure);
            insertStatementTrip.setString(3, tr_return);
            insertStatementTrip.setInt(4, tr_max_seats);
            insertStatementTrip.setFloat(5,tr_cost);
            insertStatementTrip.setInt(6,tr_br_code);
            insertStatementTrip.setString(7,tr_gui_AT);
            insertStatementTrip.setString(8,tr_drv_AT);



            insertStatementTrip.executeUpdate();

            ResultSet genKeys=insertStatementTrip.getGeneratedKeys();
            genKeys.next();

            confirmedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        Result.setText("The insertion achieved: "+"tr_id" + genKeys.getLong(1) + "tr_departure "+tr_departure + "tr_return" + tr_return +"tr_maxseats" + tr_max_seats
                                + "tr_cost" +tr_cost + "tr_br_code"+ tr_br_code + "tr_gui_AT" + tr_gui_AT+ "tr_drv_AT" +tr_drv_AT);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            });

            insertStatementTrip.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
