package ConsoleAppv2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainBank {
    public static void main(String[] args) {

        int choice = 0;
        Scanner sc = new Scanner(System.in);
        String s = "";

        // Step 1: Loading or
        // registering IBM DB2 JDBC driver class
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Problem in"
                    + " loading or registering IBM DB2 JDBC driver");
            e.printStackTrace();
        }
// Step 2: Opening database connection
        System.out.println("Hello");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql",
                "root", "Ol4la1il7Ol4la1il7");
        ) {


            // Step 2.B: Creating JDBC Statement
            while (true) {
                do {
                    System.out.println("Menu:");
                    System.out.println(" 1. Create a bank account");
                    System.out.println(" 2. Drop a bank account");
                    System.out.println(" 3. To put money into the account");
                    System.out.println(" 4. Withdraw money from an account");
                    System.out.println(" 5. Show account");
                    System.out.print(" 99. to quit: ");
                    s = sc.next();
                    try {
                        choice = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter number 1-5 or 99 for exit");
                    }

                } while (choice < 1 | choice > 5 & choice != 99);
                if (choice == 99) {
                    break;
                }
                System.out.println("\n");
                switch (choice) {
                    case 1: {
                        BankAcc ba = new BankAcc();
                        System.out.println("Create a bank account:\n");
                        ba.createAcc(sc, connection);
                        break;
                    }

                    case 2: {
                        BankAcc ba = new BankAcc();
                        System.out.println("Drop a bank account:\n");
                        ba.setID(writeID(sc));
                        ba.dropAcc(connection);
                        break;
                    }
                    case 3: {
                        System.out.println("To put money into the account:\n");
                        BankAcc ba = new BankAcc();
                        ba.setID(writeID(sc));
                        ba.changeBalance(connection, writeMoney(sc));
                        break;
                    }
                    case 4: {
                        System.out.println("Withdraw money from an account:\n");
                        BankAcc ba = new BankAcc();
                        ba.setID(writeID(sc));
                        ba.changeBalance(connection, (-writeMoney(sc)));
                        break;
                    }
                    case 5: {
                        System.out.println("Show account:\n");
                        BankAcc ba = new BankAcc();
                        ba.setID(writeID(sc));
                        ba.showAcc(connection);
                        break;
                    }
                }
                System.out.println();
            }
            System.out.println("Good luck!!!");


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getErrorCode() + "   " + e.getSQLState());
        }

    }
    private static int writeID(Scanner sc) {
        System.out.println("Enter your ID: ");
        int id = 0;
        while (true) {
            try {
                id = Integer.parseInt(sc.next());
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Enter integer number");
            }
        }
        return id;
    }
    private static double writeMoney(Scanner sc) {
        System.out.println("Enter Sum: ");
        double money = 0;
        while (true) {
            try {
                money = Double.parseDouble(sc.next());
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Enter double number");
            }
        }
        return money;
    }
}
