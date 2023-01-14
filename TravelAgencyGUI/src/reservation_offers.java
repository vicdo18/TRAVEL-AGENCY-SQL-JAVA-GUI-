import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class reservation_offers extends JDialog{
    private JPanel reservation_offers;
    private JTextField textResName;
    private JTextField textResLname;
    private JTextField textResOfferID;
    private JTextField textAdvance;

    private JButton confirmedButton;
    private JLabel Result;
    private JComboBox BoxResOfferID;

    public reservation_offers(JFrame parent) {
        super(parent);

        setTitle("reservation_offers");
        setContentPane(reservation_offers);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        insertResOffer();

    }

    public void insertResOffer(){

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "soulele3059@");



            String r_name = textResName.getText();
            String r_lname= textResLname.getText();
            float advance = textAdvance.getAlignmentX();


            String query = "INSERT INTO offers ( res_id ,r_name, r_lname , res_offer_id, advance) " +
                    "VALUES( ? , ? , ? , ? , ? )";

            PreparedStatement insertStatementResOffer = connection.prepareStatement(query);

            insertStatementResOffer.setObject(1,null);
            insertStatementResOffer.setString(2, r_name);
            insertStatementResOffer.setString(3, r_lname);

            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT offer_id FROM offers");
            while (resultSet.next()) {

                int OfferID= resultSet.getInt("offer_id");
                BoxResOfferID.addItem(OfferID);

            }

            BoxResOfferID.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int res_offer_id=(int)BoxResOfferID.getSelectedItem();

                }
            });
            setVisible(true);

            int res_offer_id=(int)BoxResOfferID.getSelectedItem();

            insertStatementResOffer.setInt(4,res_offer_id);
            insertStatementResOffer.setFloat(5,advance);


            insertStatementResOffer.executeUpdate();

            ResultSet genKeys=insertStatementResOffer.getGeneratedKeys();
            genKeys.next();

            confirmedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        Result.setText("The insertion achieved: "+" res_id" +genKeys.getLong(1) + "r_name: " +r_name + "r_lname: " + r_lname+
                                "res_offer_id" + res_offer_id + "advance: " + advance);

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            insertStatementResOffer.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
