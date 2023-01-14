import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class reservation extends JDialog {
    private JPanel reservation;
    private JTextField textResTripID;
    private JTextField textResSeatNum;
    private JTextField textResName;
    private JTextField textResLastName;
    private JComboBox BoxIsAdult;
    private JPanel Result;
    private JButton confirmedButton;
    String IsAdult;

    public reservation(JFrame parent) {
        super(parent);

        setTitle("reservation");
        setContentPane(reservation);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        confirmedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertReservation();

            }
        });
        setVisible(true);
    }

    public void insertReservation(){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "soulele3059@");

            String queryCountTrip = "SELECT COUNT(*) from trip";
            PreparedStatement insertStatementTrip = connection.prepareStatement(queryCountTrip);
            ResultSet resultCountTrip = insertStatementTrip.executeQuery(queryCountTrip);
            resultCountTrip.next();
            int insertTripIDCount = resultCountTrip.getInt(1);

            int res_tr_id=insertTripIDCount;



            int res_seatnum= textResSeatNum.getX();
            String res_name = textResName.getText();
            String res_lname= textResLastName.getText();

            BoxIsAdult.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    IsAdult = (String) BoxIsAdult.getSelectedItem();

                }
            });

            String res_isadult=IsAdult;

            String query = "INSERT INTO reservation ( res_tr_id ,res_seatnum, res_name , res_lname , res_isadult) " +
                    "VALUES( ? , ? , ? , ? ,? )";

            PreparedStatement insertStatementRes = connection.prepareStatement(query);

            insertStatementRes.setInt(1,res_tr_id);
            insertStatementRes.setInt(2, res_seatnum);
            insertStatementRes.setString(3, res_name);
            insertStatementRes.setString(4, res_lname);
            insertStatementRes.setString(5, res_isadult);


            insertStatementRes.executeUpdate();

           System.out.println("The insertion achieved: "+"res_tr_id: " + res_tr_id+ " res_seatnum: "
                    + "res_name: " + res_name + "res_lname: " + res_lname + "res_isadult: " + res_isadult);

            insertStatementTrip.close();
            insertStatementRes.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
