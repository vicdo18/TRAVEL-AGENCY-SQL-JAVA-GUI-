import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class manages extends JDialog {
    private JPanel manages;
    private JTextField textManagerAT;
    private JButton confirmedButton;
    private JLabel Result;
    private JComboBox BoxManagerBrCode;

    public manages(JFrame parent) {
        super(parent);

        setTitle("manages");
        setContentPane(manages);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        insertManages();


        setVisible(true);

    }
    public void insertManages(){

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "soulele3059@");

            Statement resultStatement= connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String queryAT = "SELECT adm_AT FROM admin";
            ResultSet result = resultStatement.executeQuery(queryAT);
            result.last();
            String mng_adm_AT= String.valueOf(result);

            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT br_code FROM branch");
            while (resultSet.next()) {

                int WorkerBrCode= resultSet.getInt("br_code");
                BoxManagerBrCode.addItem(WorkerBrCode);
            }

            BoxManagerBrCode.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int mng_br_code= (int) BoxManagerBrCode.getSelectedItem();

                }
            });
            int mng_br_code= (int) BoxManagerBrCode.getSelectedItem();

            String query = "INSERT INTO manages ( mng_adm_AT ,mng_br_code ) " +
                    "VALUES( ? , ?  )";

            PreparedStatement insertStatementManages = connection.prepareStatement(query);

            insertStatementManages.setString(1,mng_adm_AT);
            insertStatementManages.setInt(2, mng_br_code);



            insertStatementManages.executeUpdate();

            confirmedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    Result.setText("The insertion achieved: "+"mng_adm_AT: " + mng_adm_AT + "mng_br_code : " + mng_br_code);
                }
            });


            insertStatementManages.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
