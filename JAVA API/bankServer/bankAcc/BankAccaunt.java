package bankServer.bankAcc;

import java.sql.*;
import java.util.Scanner;

public class BankAccaunt {
    private int ID;
    private String NAME;
    private String LAST_NAME;
    private Date START_BILL;
    private Time START_T_BILL;
    private double BALANCE;

    public BankAccaunt() {
        ID = 0;
        NAME = "Null";
        LAST_NAME = "Null";
        BALANCE = 0;
        START_BILL = new Date(100, 9, 10);
        START_T_BILL = new Time(13, 56, 48);

    }

    public BankAccaunt(int ID, String NAME, String LAST_NAME, Date START_BILL, Time START_T_BILL, double BALANCE) {
        this.ID = ID;
        this.NAME = NAME;
        this.LAST_NAME = LAST_NAME;
        this.START_BILL = START_BILL;
        this.START_T_BILL = START_T_BILL;
        this.BALANCE = BALANCE;
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

    public String convertToJson() {
        StringBuffer sBuffer = new StringBuffer("{");
        sBuffer.append("\"ID\": \"" + this.ID + "\",");
        sBuffer.append("\"NAME\": \"" + this.NAME + "\",");
        sBuffer.append("\"LAST_NAME\": \"" + this.LAST_NAME + "\",");
        sBuffer.append("\"START_BILL\": \"" + this.START_BILL.toString() + "\",");
        sBuffer.append("\"START_T_BILL\": \"" + this.START_T_BILL.toString() + "\",");
        sBuffer.append("\"BALANCE\": \"" + String.valueOf(this.BALANCE) + "\"");
        sBuffer.append("}");
        return sBuffer.toString();
    }

    public void createAcc(Connection connection) throws SQLException {
        System.out.println("creating Acc");
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

    public void changeBalance(Connection connection, double money) {
        if (!isExist(connection)) {
            System.out.println("The ID does no exist");
            ID = 0;
            return;
        }

        getRow(connection);

        if (BALANCE + money < 0) {
            System.out.println("There is not enough money on your balance");
            return;
        }

        try (PreparedStatement ps = connection.prepareStatement("UPDATE bankacc.bankacc SET BALANCE = ? WHERE ID = ?;");) {
            ps.setDouble(1, money + BALANCE);
            ps.setInt(2, ID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("in showAcc");
            System.out.println(e.getErrorCode() + "   " + e.getSQLState());
        }
    }

    public void fromJSON(String json) {
        System.out.println("start");
        System.out.println(json);

        // System.out.println(json.split("\"")[3] + "  "+ json.split("\"")[7] + "  "+ json.split("\"")[11] + "  "+ json.split("\"")[23]);

        ID = Integer.parseInt(json.split("\"")[3]);
        NAME = json.split("\"")[7];
        BALANCE = Double.parseDouble(json.split("\"")[23]);
        LAST_NAME = json.split("\"")[11];
        //START_BILL = Date.valueOf(json.split("\"")[15]);
        //START_T_BILL = Time.valueOf(json.split("\"")[19]);


        //System.out.println(toString());
    }

    @Override
    public String toString() {
        return "BankAcc{" +
                "ID=" + ID +
                ", NAME='" + NAME + '\'' +
                ", LAST_NAME='" + LAST_NAME + '\'' +
                ", START_BILL=" + START_BILL +
                ", START_T_BILL=" + START_T_BILL +
                ", BALANCE=" + BALANCE +
                '}';
    }

    @Deprecated
    public void withdrawMoney(Connection connection, double money) {
        money = -money;
        if (!isExist(connection)) {
            System.out.println("The ID does no exist");
            return;
        }
        getRow(connection);

        if (BALANCE + money < 0) {
            System.out.println("There is not enough money on your balance");
            return;
        }


        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO bankacc.TRANSACT(ID,TRANSACTIONS) "
                + " VALUES(?,?)");) {
            ps.setInt(1, ID);
            ps.setDouble(2, money);
            ps.executeUpdate();
            System.out.println("Debited from account: " + -money);
        } catch (SQLException e) {
            System.out.println("exeption withdraw");
            System.out.println(e.getErrorCode() + "   " + e.getSQLState());
        }
    }

    @Deprecated
    public void addMoney(Connection connection, double money) {
        if (!isExist(connection)) {
            System.out.println("The ID does no exist");
            return;
        }

        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO bankacc.TRANSACT(ID,TRANSACTIONS) "
                + " VALUES(?,?)");) {
            ps.setInt(1, ID);
            ps.setDouble(2, money);
            ps.executeUpdate();
            System.out.println("Balance replenished");
        } catch (SQLException e) {
            System.out.println("exeption in showAcc");
            System.out.println(e.getErrorCode() + "   " + e.getSQLState());
        }
    }

    public boolean isExist(Connection connection) {
        int id = 0;
        ResultSet resultSet = null;
        System.out.println("id is " + ID);

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
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM bankacc.BANKACC WHERE ID = ?");) {
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

        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM bankacc.BANKACC WHERE ID = ?");) {
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
