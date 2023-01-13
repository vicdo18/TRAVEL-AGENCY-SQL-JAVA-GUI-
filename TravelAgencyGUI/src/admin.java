import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class admin extends JDialog{
    private JPanel admin;
    private JTextField textAdminAT;
    private JComboBox AdmTypeBox;
    private JTextField textAdminDiploma;
    private JButton confirmedButton;
    private JLabel Result;

    String AdminType;


    public admin(JFrame parent) {
        super(parent);

        setTitle("admin");
        setContentPane(admin);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        insertAdmin();



        setVisible(true);
    }


    public  void insertAdmin() {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "soulele3059@");


            Statement resultStatement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String queryAT = "SELECT wrk_AT FROM worker";
            ResultSet result = resultStatement.executeQuery(queryAT);
            result.last();
            String adm_AT = String.valueOf(result);

            AdmTypeBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AdminType = (String) AdmTypeBox.getSelectedItem();

                }
            });

            String adm_type = AdminType;
            String adm_diploma = textAdminDiploma.getText();
            String query = "INSERT INTO guide (adm_AT ,adm_type,adm_diploma) " +
                    "VALUES(? , ? , ?)";


            PreparedStatement insertStatementAdmin = connection.prepareStatement(query);
            insertStatementAdmin.setString(1, adm_AT);
            insertStatementAdmin.setString(2, adm_type);
            insertStatementAdmin.setString(3, adm_diploma);

            insertStatementAdmin.executeUpdate();


            confirmedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    Result.setText("The insertion achieved: " + " adm_AT:" + adm_AT + "adm_type " + adm_type + " adm_diploma:" + adm_diploma);

                }
            });


            insertStatementAdmin.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}







