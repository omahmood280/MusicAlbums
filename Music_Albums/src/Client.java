import java.io.*;
import java.net.*;


public class Client {

    public static void main(String args[]) throws IOException {
        System.out.println("Client is running");
        //Open a connection to the server, create the client socket

        Socket socket = new Socket(Credentials.HOST, Credentials.PORT);
        BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream(), "UTf-8"));
        PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));



        //Reading messages continuously until the user types "stop"
        while(true) {
            System.out.println("Enter the artist name:");
            String artistName = inFromUser.readLine();
            System.out.println("You entered " + artistName);
            //Sending a message to the server
            output.println(artistName);

            // Receiving a response from the server
            String serverMessage = input.readLine();
            System.out.println("FROM SERVER: " + serverMessage);
            //Closing I/O streams and socket*/
            if(artistName.equals("stop")) {
                input.close();
                output.close();
                socket.close();
                break;
            }

        }
    }
}
