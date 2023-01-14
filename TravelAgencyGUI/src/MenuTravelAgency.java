import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.lang.System;

public class MenuTravelAgency extends JDialog {
    private JPanel menu;
    private JTextField chooseField;
    private JComboBox InsertBox;
    private JLabel LabelInsert;
    private JButton InsertButton;
    private JButton ModifyButton;
    private JButton DeleteButton;
    private JLabel TripInfoLabel;
    private JButton TripButton;
    private JLabel DepartureDate;
    private JTextField textDeparture;
    private JLabel ReturnDate;
    private JTextField textReturn;
    private JLabel ClientLabel;
    private JTextField textClient;
    private JButton findButton;
    private JLabel BranchManagerLabel;
    private JButton ManagerButton;
    private JLabel BranchEmployeeLabel;
    private JButton EmployeeButton;
    private JLabel ITinsertLabel;
    private JTextField textIT;
    private JButton insertITButton;
    private JLabel logInfoLabel;
    private JButton LogButton;
    private JTable InfoTable;
    public String TableSelected;


    public MenuTravelAgency(JFrame parent) {
        super(parent);

        setTitle("Menu of Actions");
        setContentPane(menu);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Insertion Activate Box Button
        InsertBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableSelected = (String) InsertBox.getSelectedItem();

            }
        });

        InsertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertData();

            }
        });

        //Edw teleiwnei to MenuTravelAgency
        TripButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ShowInfoAboutTrip();

            }
        });

        setVisible(true);
    }

    public void InsertData() {
        TableSelected = (String) InsertBox.getSelectedItem();

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "soulele3059@");

            System.out.println("The connection to Travel Agency: Travel Booth achivied: " + connection);
            System.out.println("---------------------------------------------------------------------");


//----------------------------------------------------------------------------------------------------------------------
            if (TableSelected.equals("branch")) {

                branch branch = new branch(null);
            }


//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("worker")) {

                worker worker = new worker(null);

            }

//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("guide")) {

                System.out.println("You have to insert a worker to insert a new guide: ");

                worker worker = new worker(null);

                guide guide = new guide(null);

            }
//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("admin")) {

                System.out.println("You have to insert a worker to insert a new admin: ");

                worker worker = new worker(null);

                admin admin = new admin(null);

            }
//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("driver")) {
                System.out.println("You have to insert a worker to insert a new driver: ");

                worker worker = new worker(null);

                driver driver = new driver(null);
            }

//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("trip")) {

                System.out.println("You have to insert a new branch to insert a new trip: ");

                branch branch = new branch(null);

                System.out.println("You have to insert a new worker to insert a new guide on the trip: ");

                worker worker1 = new worker(null);
                guide guide = new guide(null);


                System.out.println("You have to insert a new worker to insert a new admin on the trip: ");

                worker worker2 = new worker(null);
                driver driver = new driver(null);


                trip trip = new trip(null);


            }

//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("phones")) {

                System.out.println("----------------------------------------------------------");
                System.out.println("You have to insert a new branch first : ");

                branch branch = new branch(null);

                phones phones = new phones(null);

            }
//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("event")) {

                System.out.println("You have to insert a new trip first");

                trip trip = new trip(null);
                event event = new event(null);

            }

//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("destintation")) {

                destination destination = new destination(null);
            }

//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("travel_to")) {

                System.out.println("----------------------------------------------------------");
                System.out.println("Firstly you have to insert a new trip: ");

                trip trip = new trip(null);


                System.out.println("--------------------------------------------------------------------");
                System.out.println("Secondly,you have to insert a destination:");

                destination destination = new destination(null);

                travel_to travelTo = new travel_to(null);

            }

//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("manages")) {

                System.out.println("Firstly,you have to insert a new worker to add a new admin as a manager: ");
                worker worker = new worker(null);
                admin admin = new admin(null);

                System.out.println("Secondly, you have to insert a new worker to add a new branch code as a manager branch code: ");
                branch branch = new branch(null);


                manages manages = new manages(null);
            }


//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("reservation")) {

                System.out.println("Firstly, you have to insert a new trip to add a new reservation: ");
                trip trip = new trip(null);


                reservation reservation = new reservation(null);


            }


//----------------------------------------------------------------------------------------------------------------------
            if (TableSelected.equals("languages")) {

                System.out.println("Firstly, you have to insert a new guide to add a new language to the guide: ");
                worker worker = new worker(null);
                guide guide = new guide(null);

                languages languages = new languages(null);

            }

//----------------------------------------------------------------------------------------------------------------------
            if (TableSelected.equals("offers")) {

                offers offers = new offers(null);

            }


//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("reservation_offers")) {


                reservation_offers reservation_offers = new reservation_offers(null);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
//----------------------------------------------------------------------------------------------------------------------
    public void ShowInfoAboutTrip() {

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "soulele3059@");

            System.out.println("The connection to Travel Agency: Travel Booth achivied: " + connection);
            System.out.println("---------------------------------------------------------------------");


            String query = "SELECT tr_departure, tr_return,tr_maxseats,tr_cost,tr_br_code FROM trip ";
            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT tr_departure, tr_return,tr_maxseats,tr_cost,tr_br_code FROM trip ");
            while (resultSet.next()) {

             

            }








        } catch (SQLException e) {
            e.printStackTrace();
        }


        //Telos klashs
    }
}
