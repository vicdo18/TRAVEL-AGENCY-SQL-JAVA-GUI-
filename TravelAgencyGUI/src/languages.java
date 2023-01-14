import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class languages extends JDialog {
    private JPanel languages;
    private JTextField textLngGuiAT;
    private JTextField textLngLanguage;
    private JButton confirmedButton;
    private JLabel Result;

    public languages(JFrame parent) {
        super(parent);

        setTitle("reservation");
        setContentPane(languages);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        insertLanguage();


        setVisible(true);
    }

   public void insertLanguage(){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "soulele3059@");

            Statement resultStatement= connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String queryAT = "SELECT gui_AT FROM guide";
            ResultSet result = resultStatement.executeQuery(queryAT);
            result.last();
            String lng_gui_AT= String.valueOf(result);

            String lng_language=textLngLanguage.getText();

            String query = "INSERT INTO languages ( lng_gui_AT ,lng_language) " +
                    "VALUES( ? , ? )";

            PreparedStatement insertStatementLanguage = connection.prepareStatement(query);

            insertStatementLanguage.setString(1,lng_gui_AT);
            insertStatementLanguage.setString(2, lng_language);

            insertStatementLanguage.executeUpdate();

            confirmedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Result.setText("The insertion achieved: "+"lng_gui_AT: " + lng_gui_AT+ " lng_language: "
                            + lng_language );
                }
            });



            insertStatementLanguage.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
