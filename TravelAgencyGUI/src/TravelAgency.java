import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

public  class TravelAgency extends JDialog  {

        private JLabel userLabel;
        private JTextField userText;
        private JLabel passwordLabel;
        private JPasswordField passwordText;
        private JPanel panel;
        private JButton button;
        private JTextField enjoyTheWorldEnjoyTextField;
        private JFrame frame;


        public TravelAgency(JFrame parent) {
            super(parent);

            setTitle("Travel Agency Account");
            setContentPane(panel);
            setSize(650, 500);
            setModal(true);
            setLocationRelativeTo(parent);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);


            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    travelUser();
                }
            });

            setVisible(true);
        }

        private void travelUser() {
            String username = userText.getText();
            String password = String.valueOf(passwordText.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter all the fields",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.equals("root") || !username.equals("host")) {
                JOptionPane.showMessageDialog(this,
                        "The password and the username is not confirmed",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                JOptionPane.showMessageDialog(this,
                        "Login succesful",
                        "Enjoy your journay with Travel Booth",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

            private void connectToDatabase(String username, String password){

              final String DB_URL= "";
              final String USERNAME= "root";
              final String PASSWORD="";

              try{
                  Connection connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

                  connection.close();

              }catch(Exception e){
                  e.printStackTrace();
                }

        }


        public static void main(String[] args) {
            TravelAgency form = new TravelAgency(null);
        }


    }




