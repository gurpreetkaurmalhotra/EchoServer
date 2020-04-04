import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static final String HOST = "127.0.0.1";
    public static final int PORT = 8899;

    public static void main(String[] args) throws IOException{

        Socket socket = new Socket(HOST, PORT);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        while(true){

            System.out.println(">");
            String command = keyboard.readLine();

            if (command.equals("quit")) {
                break;
            }

            out.println(command);
            String serverResponse = input.readLine();

            System.out.println("Count -> " + serverResponse);

        }

        socket.close();
        System.exit(0);

    }
}