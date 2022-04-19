package bankServer;

import ConsoleApp.BankAcc;
import bankServer.endpoints.Endpoint2;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainBankServer {
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
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql",
                    "root", "Ol4la1il7Ol4la1il7");
            BankAcc ba = new BankAcc();
            ba.setID(200);
            ba.showAcc(connection);

            HttpServer server = HttpServer.create();
            server.bind(new InetSocketAddress(48818), 0);
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
            HttpContext context = server.createContext("/endpoint2", new Endpoint2(sc, connection));
            //context = server.createContext("/endpoint2", new bankServer.endpoints.Endpoint2());
            server.setExecutor(threadPoolExecutor);
            server.start();
            System.out.println("Server started");


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getErrorCode() + "   " + e.getSQLState());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}