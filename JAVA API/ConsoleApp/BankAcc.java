package ConsoleApp;

import java.sql.*;
import java.util.Scanner;

public class BankAcc {
    private int ID;
    private String NAME;
    private String LAST_NAME;
    private Date START_BILL;
    private Time START_T_BILL;
    private double BALANCE;

    public BankAcc() {
        ID = 0;
        NAME = "Ivan";
        LAST_NAME = "Ivanov";
        BALANCE = 0;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public double getBALANCE() {
        return BALANCE;
    }

    public void setBALANCE(double BALANCE) {
        this.BALANCE = BALANCE;
    }

    public void createAcc(Scanner sc, Connection connection) throws SQLException {


        System.out.println("Enter ID:");

        String s = "";

        while (true) {
            s = sc.next();
            try {
                ID = Integer.parseInt(s);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Enter integer number");
            }
        }
        System.out.println("Enter Name:");
        while (true) {
            NAME = sc.next();
            if (NAME.length() <= 10) {
                break;
            } else {
                System.out.println("Enter name less than 11");
            }
        }
        System.out.println("Enter Last Name:");
        while (true) {
            LAST_NAME = sc.next();
            if (LAST_NAME.length() <= 10) {
                break;
            } else {
                System.out.println("Enter last name less than 11");
            }
        }
        BALANCE = 0;

        if (isExist(connection)) {
            System.out.println("The ID already exist");
            return;
        }

        String sql = "INSERT INTO bankacc.BANKACC(ID,NAME,LAST_NAME,BALANCE) " +
                "VALUES(?,?,?,?);";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, ID);
            ps.setString(2, NAME);
            ps.setString(3, LAST_NAME);
            ps.setDouble(4, BALANCE);
            ps.executeUpdate();
        }


    }

    public void withdrawMoney(Connection connection, double money) {
    	money = -money;
        if (!isExist(connection)) {
            System.out.println("The ID does no exist");
            return;
        }
        getRow(connection);

        if(BALANCE + money < 0) {
            System.out.println("There is not enough money on your balance");
            return;
        }


        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO bankacc.TRANSACT(ID,TRANSACTIONS) "
                +" VALUES(?,?)" );) {
            ps.setInt(1, ID);
            ps.setDouble(2, money);
            ps.executeUpdate();
            System.out.println("Debited from account: " + -money);
        } catch (SQLException e) {
            System.out.println("in showAcc");
            System.out.println(e.getErrorCode() + "   " + e.getSQLState());
        }
    }
    public void addMoney(Connection connection, double money) {
        if (!isExist(connection)) {
            System.out.println("The ID does no exist");
            return;
        }

        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO bankacc.TRANSACT(ID,TRANSACTIONS) "
                +" VALUES(?,?)" );) {
            ps.setInt(1, ID);
            ps.setDouble(2, money);
            ps.executeUpdate();
            System.out.println("Balance replenished");
        } catch (SQLException e) {
            System.out.println("in showAcc");
            System.out.println(e.getErrorCode() + "   " + e.getSQLState());
        }
    }

    public boolean isExist(Connection connection) {
        int id = 0;
        ResultSet resultSet = null;

        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM bankacc.bankacc WHERE  ID = ?;");) {
            ps.setInt(1, ID);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("exeption in isExist");
            System.out.println(e.getErrorCode() + "   " + e.getSQLState());
        } finally {
            try {
                if (null != connection) {
                    resultSet.close();
                }
            } catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
        if (id == 0) {
            return false;
        }
        return true;

    }

    public void getRow(Connection connection) {
        ResultSet resultSet = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM bankacc.bankacc WHERE ID = ?");) {
            ps.setInt(1, ID);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                ID = resultSet.getInt(1);
                NAME = resultSet.getString(2);
                LAST_NAME = resultSet.getString(3);
                START_BILL = resultSet.getDate(4);
                START_T_BILL = resultSet.getTime(5);
                BALANCE = resultSet.getDouble(6);
            }
            System.out.println("balanse is " + BALANCE);
        } catch (SQLException e) {
            System.out.println("in showAcc");
            System.out.println(e.getErrorCode() + "   " + e.getSQLState());
        } finally {
            try {
                if (null != connection) {
                    resultSet.close();
                }
            } catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
    }

    public void dropAcc(Connection connection) throws SQLException {
        if (!isExist(connection)) {
            System.out.println("The ID does no exist");
            return;
        }

        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM bankacc.bankacc WHERE ID = ?");) {
            ps.setInt(1, ID);
            ps.executeUpdate();
            System.out.println("Deletion completed successfully");

        } catch (SQLException e) {
            System.out.println("in showAcc");
            System.out.println(e.getErrorCode() + "   " + e.getSQLState());
        }
    }

    public void showAcc(Connection connection) throws SQLException {
        if (!isExist(connection)) {
            System.out.println("The ID does no exist");
            return;
        }
        getRow(connection);
        System.out.println("Id: " + ID + ".Name: " + NAME +
                ". Last name: " + LAST_NAME + ". Balance: " + BALANCE + ". Time starting account: " +
                START_BILL + " " + START_T_BILL);

    }
}

