import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class driver extends JDialog{
    private JPanel driver;
    private JTextField textDriverAT;
    private JComboBox BoxDriverLicense;
    private JComboBox BoxDriverRoute;
    private JTextField textDriverExperience;
    private JButton confirmedButton;
    private JLabel Result;

    String DriverLicense;
    String DriverRoute;

    public driver(JFrame parent) {
        super(parent);

        setTitle("driver");
        setContentPane(driver);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        insertDriver();


        setVisible(true);
    }


    public void insertDriver(){

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "soulele3059@");


            Statement resultStatement= connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String queryAT = "SELECT wrk_AT FROM worker";
            ResultSet result = resultStatement.executeQuery(queryAT);
            result.last();
            String drv_AT= String.valueOf(result);

            BoxDriverLicense.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DriverLicense = (String) BoxDriverLicense.getSelectedItem();

                }
            });
            String drv_license = DriverLicense;


            BoxDriverRoute.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DriverRoute= (String) BoxDriverRoute.getSelectedItem();

                }
            });
            String drv_route = DriverRoute;

            String drv_experience= textDriverExperience.getText();

            String query = "INSERT INTO guide (drv_AT, drv_license , drv_route , drv_experience) " +
                    "VALUES(? , ? , ? , ?)";



            PreparedStatement insertStatementDriver = connection.prepareStatement(query);
            insertStatementDriver.setString(1,drv_AT);
            insertStatementDriver.setString(2,drv_license);
            insertStatementDriver.setString(3,drv_route);
            insertStatementDriver.setString(4,drv_experience);

            insertStatementDriver.executeUpdate();

            confirmedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    Result.setText("The insertion achieved: "+ " drv_AT:" + drv_AT  + "drv_license"+ drv_license + " drv_route:" + drv_route+ "drv_experience:" +drv_experience);
                }
            });


            insertStatementDriver.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }


}
