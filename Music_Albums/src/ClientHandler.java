import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    //declaring variables
    Socket socket;
    int clientID;
    Database db;


    //Constructor
    public ClientHandler (Socket socket, int clientId, Database db) {
        this.socket = socket;
        this.clientID = clientId;
        this.db = db;

    }

    public void run() {
        System.out.println("ClientHandler started...");
        try {
            // Creating I/O streams to read/write data, PrintWriter and BufferedReader
            BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream(), "UTF-8"));
            PrintWriter outToClient = new PrintWriter(socket.getOutputStream(),true);
            String clientMessage;
            int titlesNum;
            while(true) {
                //Receiving messages from the client and send replies, until the user types "stop"
                if(!(clientMessage = input.readLine()).equals("stop")) {
                    System.out.println("Client sent the artist name " + clientMessage);
                    //  Requesting the number of titles from the db
                    titlesNum = db.getTitles(clientMessage);


                    outToClient.println("Number of titles: " + titlesNum + " records found");

                }
                else {
                    System.out.println("Client " + this.clientID + " has disconnected");
                    outToClient.println("Connection closed, Goodbye!");

                    //Closing I/O streams and socket*/
                    input.close();
                    outToClient.close();
                    socket.close();
                    break;

                }

            }
        }

        catch(Exception ex) {
            System.out.println("Error Occurred" + ex.getMessage());
        }

    }
}
