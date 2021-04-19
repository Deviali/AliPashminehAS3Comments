package ada.edu.LibraryMid.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class DBUtils { static DataSource dataSource;

    static{
        try {
            Context initContext = new InitialContext();
            Context webContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) webContext.lookup("jdbc:postgresql://localhost:5432/LibraryMid");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static void execQuery(String sql){
        try {
            Connection connection = DBUtils.dataSource.getConnection();
            Statement statement = connection.createStatement();
            boolean result = statement.execute(sql);
            if (result){
                ResultSet resultSet = statement.getResultSet();
            }else{
                System.out.println("exception valla");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private static Connection connection;
    public static Connection getConnection(){
        if(connection==null){
            try{
                Class.forName("org.postgresql.Driver");
                connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/LibraryMid","postgres","root");
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        return connection;

    }
}
