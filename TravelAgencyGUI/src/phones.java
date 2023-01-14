import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class phones extends JDialog{
    private JPanel phones;
    private JTextField textPhoneBrCode;
    private JTextField textPhoneNumber;
    private JButton confirmedButton;
    private JLabel Result;

    public phones(JFrame parent) {
        super(parent);

        setTitle("phones");
        setContentPane(phones);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        confirmedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertPhones();

            }
        });

        setVisible(true);
    }

     public void insertPhones(){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "soulele3059@");


            String queryCount = "SELECT COUNT(*) from branch";

            PreparedStatement insertStatementBranch = connection.prepareStatement(queryCount);
            ResultSet resultCount = insertStatementBranch.executeQuery(queryCount);
            resultCount.next();
            int insertBrCodeCount = resultCount.getInt(1);

            int ph_br_code=insertBrCodeCount;


               String ph_number = textPhoneNumber.getText();

            String queryPhones = "INSERT INTO phones(ph_br_code, ph_number ) " +
                    "VALUES( ? , ? )";
            PreparedStatement insertStatementPhones = connection.prepareStatement(queryPhones);
            insertStatementPhones.setInt(1, ph_br_code);
            insertStatementPhones.setString(2, ph_number);


            insertStatementPhones.executeUpdate();



           Result.setText("The insertion achieved: "+" ph_br_code: " + ph_br_code + " ph_number: " + ph_number);

            insertStatementPhones.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}