package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    // connecting to database method
public class DBconnection {
        public static Connection DBcon() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://tommy.heliohost.org/ultimazs_CIS3368project", "ultimazs","Ultima123");
                return conn;

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.out.println("Fail to connect" + e);
            }

            return null;
        }
    }
