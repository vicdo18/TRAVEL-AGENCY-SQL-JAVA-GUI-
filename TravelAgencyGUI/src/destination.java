import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class destination extends JDialog {
    private JPanel destination;
    private JTextField textDestinationID;
    private JTextField textDestName;
    private JTextField textDestDescr;
    private JComboBox BoxDestType;
    private JTextField textDestLanguage;
    private JTextField textDestLocation;
    private JButton confirmedButton;
    private JLabel Result;

    String DestinationType;

    public destination(JFrame parent) {
        super(parent);

        setTitle("destination");
        setContentPane(destination);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        DestinationInsert();


        setVisible(true);

    }
        public void DestinationInsert() {

            try {
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/travel_agency",
                        "root",
                        "soulele3059@");


                String dst_name = textDestName.getText();
                String dst_descr = textDestDescr.getText();

                BoxDestType.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DestinationType = (String) BoxDestType.getSelectedItem();
                    }
                });

                String dst_type = DestinationType;
                String dst_language = textDestLanguage.getText();


                String query = "INSERT INTO destination (dst_id ,  dst_name , dst_descr , dst_rtype , dst_language , dst_location) " +
                        "VALUES(? , ? , ? , ?, ? , ?)";

                PreparedStatement insertStatementDest = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                insertStatementDest.setObject(1, null);
                insertStatementDest.setString(2, dst_name);
                insertStatementDest.setString(3, dst_descr);
                insertStatementDest.setString(4, dst_type);
                insertStatementDest.setString(5, dst_language);

                String queryCount = "SELECT COUNT(dst_id) from destination";
                PreparedStatement insertStatementLocation = connection.prepareStatement(queryCount);

                ResultSet resultCount = insertStatementLocation.executeQuery(queryCount);
                resultCount.next();
                int DestinationnCount = resultCount.getInt(1);
                int dst_location = DestinationnCount + 1;

                insertStatementDest.setInt(6, dst_location);


                insertStatementDest.executeUpdate();

                ResultSet genKeys = insertStatementDest.getGeneratedKeys();
                genKeys.next();

                confirmedButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        try {
                            Result.setText("The insertion achieved: " + "dst_id:" + genKeys.getLong(1) + "  dst_name: " + dst_name + "  dst_descr : " + dst_descr + " dst_type : "
                                    + dst_type + "dst_language: "
                                    + dst_language + "dst_location: " + dst_location);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });


                insertStatementLocation.close();
                insertStatementDest.close();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

