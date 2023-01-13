import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class offers extends JDialog{
    private JPanel offers;
    private JTextField textOfferID;
    private JTextField textStartDate;
    private JTextField textEndDate;
    private JTextField textCostPerPerson;
    private JTextField textDestID;

    private JButton confirmedButton;
    private JComboBox BoxDestID;
    private JLabel Result;

    public offers(JFrame parent) {
        super(parent);

        setTitle("offers");
        setContentPane(offers);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        insertOffer();

    }

    public void insertOffer(){

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "soulele3059@");



            Date startDate = Date.valueOf(textStartDate.getText());
            LocalDate startLocalDate = startDate.toLocalDate();
            String start_date = startLocalDate.format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));


            Date endDate = Date.valueOf(textEndDate.getText());
            LocalDate endLocalDate = endDate.toLocalDate();
            String end_date = endLocalDate.format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
            float cost_per_person=textCostPerPerson.getAlignmentX();



            String query = "INSERT INTO offers ( offer_id ,start_date,end_date, cost_per_person,dest_id ) " +
                    "VALUES( ? , ? , ? , ? , ? )";

            PreparedStatement insertStatementOffer = connection.prepareStatement(query);

            insertStatementOffer.setObject(1,null);
            insertStatementOffer.setString(2, start_date);
            insertStatementOffer.setString(3, end_date);
            insertStatementOffer.setFloat(4,cost_per_person);

            BoxDestID.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int dest_id=(int)BoxDestID.getSelectedItem();

                }
            });
            int dest_id=(int)BoxDestID.getSelectedItem();

            setVisible(true);

            insertStatementOffer.setInt(5,dest_id);

            insertStatementOffer.executeUpdate();

            ResultSet genKeys=insertStatementOffer.getGeneratedKeys();
            genKeys.next();

            confirmedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                       Result.setText("The insertion achieved: "+" offer_id" + genKeys.getLong(1) + "start_date" + start_date + "end_date"
                        +end_date + "cost_per_person: " + cost_per_person + "dest_id: " + dest_id);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            });

            insertStatementOffer.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
