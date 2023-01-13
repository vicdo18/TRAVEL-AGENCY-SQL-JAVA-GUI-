import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class branch extends JDialog {
    private JTextField textBrCode;
    private JLabel br_codeLabel;
    private JTextField textBrStreet;
    private JTextField textBrNum;
    private JTextField textBrCity;
    private JPanel branchForm;
    private JButton confirmedButton;
    private JLabel Result;


    public branch(JFrame parent) {
        super(parent);

        setTitle("branch");
        setContentPane(branchForm);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        insertBranch();
        setVisible(true);

    }


        public void insertBranch () {


            try {
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/travel_agency",
                        "root",
                        "soulele3059@");

                String br_street = textBrStreet.getText();
                int br_num = textBrNum.getX();
                String br_city = textBrCity.getText();

                String query = "INSERT INTO branch (br_code , br_street , br_num , br_city) " +
                        "VALUES(? , ? , ? , ?)";


                PreparedStatement insertStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                insertStatement.setObject(1, null);
                insertStatement.setString(2, br_street);
                insertStatement.setInt(3, br_num);
                insertStatement.setString(4, br_city);

                insertStatement.executeUpdate();
                ResultSet genKeys = insertStatement.getGeneratedKeys();
                genKeys.next();

                confirmedButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Result.setText("The insertion achieved: " + "br_code:" + genKeys.getLong(1) + "  br_street:" + br_street + "  br_num:" + br_num + " br_city:" + br_city);

                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                });


            } catch (SQLException e) {
                e.printStackTrace();
            }


        }


    }

