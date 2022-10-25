import java.sql.*;

public class Database {
    Connection c = null;

    //Executing a query and returning the number of albums for the artist with name artistName
    public int getTitles(String artistName)  {
        int titleNum = 0;

        String sql = "SELECT album.albumid, album.artistid FROM album INNER JOIN artist ON album.artistid=artist.artistid WHERE artist.name= ?";
        PreparedStatement ps = null;
        try {
            if(c.isClosed()){
                establishDBConnection();
                ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ps.setString(1, artistName);
                ResultSet rs = ps.executeQuery();
                rs.last();
                titleNum = rs.getRow();
            }
            else{
                ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ps.setString(1, artistName);
                ResultSet rs = ps.executeQuery();
                rs.last();
                titleNum = rs.getRow();

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage().toString());
            e.printStackTrace();
        }
        return titleNum;

    }

    // Establishing a DB connection & returning a boolean status
    public boolean establishDBConnection() {

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(Credentials.URL,
                            Credentials.USERNAME, Credentials.PASSWORD);
            //5 sec timeout
            return c.isValid(5);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
