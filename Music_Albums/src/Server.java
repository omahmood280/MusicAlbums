import java.io.*;
import java.net.*;

public class Server {

    public static void main(String args[]) throws IOException {
        int clientId = 1;
        Database db = new Database();
        // Creating a Database object and checking the connection with establishDBConnection():
        boolean connection = db.establishDBConnection();
        if(connection) {
            System.out.println("Server is now connected to DB");
            System.out.println("Server is running" );
            //Opening the server socket
            try (ServerSocket serversocket = new ServerSocket(Credentials.PORT)){
                //Continuously listening for client requests
                while(true) {
                    //Accepting the new connection and creating the client socket
                    Socket socket = serversocket.accept();
                    ClientHandler clientThread = new ClientHandler(socket, clientId, db);
                    // Displaying clientId and IP address:
                    System.out.println("Client " + clientId + " connected with IP " + socket.getInetAddress().getHostAddress());
                    //Incrementing clientId. The clientId is not reassigned once used.
                    clientId++;
                    //Creating a new client handler object and start the thread*/
                    new Thread(clientThread).start();


                }
            } catch (Exception e) {
                System.out.println("Error occured in main: " + e.getMessage().toString());
            }

        }
        //If the db connection fails:
        else {

            System.out.println("DB connection fail, stopping.");

        }




    }
}