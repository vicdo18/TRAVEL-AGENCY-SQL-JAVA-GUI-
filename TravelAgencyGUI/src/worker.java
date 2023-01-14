import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class worker extends JDialog {
    private JTextField textWrkAT;
    private JTextField textWrkName;
    private JTextField textWrkLname;
    private JTextField textWrkSalary;
    private JTextField textWrkBrCode;
    private JPanel worker;
    private JButton confirmedButton;
    private JComboBox BoxWorkerBrCode;
    private JLabel Result;


    public worker(JFrame parent) {
        super(parent);

        setTitle("worker");
        setContentPane(worker);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        insertWorker();

    }
    public void insertWorker(){

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "soulele3059@");



            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT br_code FROM branch");
            while (resultSet.next()) {

                int WorkerBrCode= resultSet.getInt("br_code");
                BoxWorkerBrCode.addItem(WorkerBrCode);
            }

            BoxWorkerBrCode.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int WorkBRcode=BoxWorkerBrCode.getSelectedIndex();
                }
            });

            setVisible(true);

            String wrk_AT = textWrkAT.getText();
            String wrk_name= textWrkName.getText();
            String wrk_lname= textWrkLname.getText();
            Float wrk_salary= textWrkSalary.getAlignmentX();
            int wrk_br_code=BoxWorkerBrCode.getSelectedIndex();

            String query = "INSERT INTO worker (wrk_AT , wrk_name , wrk_lname , wrk_salary, wrk_br_code) " +
                    "VALUES( ? , ? , ? , ? , ? )";


            PreparedStatement insertStatement = connection.prepareStatement(query);

            insertStatement.setString(1, wrk_AT);
            insertStatement.setString(2, wrk_name);
            insertStatement.setString(3, wrk_lname);
            insertStatement.setFloat(4, wrk_salary);
            insertStatement.setInt(5, wrk_br_code);

            insertStatement.executeUpdate();

            confirmedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    Result.setText("The insertion achieved: "+"  wrk_AT:" + wrk_AT  + "  wrk_name:" + wrk_name + " wrk_lname :" + wrk_lname
                            + " wrk_salary: " + wrk_salary + " wrk_br_code: " + wrk_br_code);


                }
            });

            insertStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}