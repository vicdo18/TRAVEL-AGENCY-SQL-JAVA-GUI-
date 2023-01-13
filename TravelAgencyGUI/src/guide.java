import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class guide extends JDialog {
    private JTextField textGuideAT;
    private JLabel gui_cv;
    private JTextField textGuiCV;
    private JPanel guide;
    private JButton confirmedButton;
    private JLabel Result;

    public guide(JFrame parent) {
        super(parent);

        setTitle("guide");
        setContentPane(guide);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        insertGuide();

        setVisible(true);
    }

     public void insertGuide(){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "soulele3059@");


            Statement resultStatement= connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String queryAT = "SELECT wrk_AT FROM worker";
            ResultSet result = resultStatement.executeQuery(queryAT);
            result.last();
            String gui_AT= String.valueOf(result);

            String gui_cv = textGuiCV.getText();
            String query = "INSERT INTO guide (gui_AT , gui_cv) " +
                    "VALUES(? , ? )";



            PreparedStatement insertStatementGuide = connection.prepareStatement(query);
            insertStatementGuide.setString(1, gui_AT);
            insertStatementGuide.setString(2, gui_cv);

            insertStatementGuide.executeUpdate();


            confirmedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    Result.setText("The insertion achieved: "+"  gui_AT:" + gui_AT  + "  gui_cv:" + gui_cv);
                }
            });




            insertStatementGuide.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
