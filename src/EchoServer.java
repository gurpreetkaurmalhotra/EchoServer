import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {

    public static final int PORT = 8899;
    public static final int N_THREADS = 5;
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(N_THREADS);


    public static void main(String[] args) throws IOException{

        ServerSocket serverSocket = null;

        try{
            serverSocket = new ServerSocket(PORT);
        }
        catch(IOException e){
            System.out.println("Can't listen on port - " + PORT);
            System.exit(1);
        }

        while(true) {
            Socket clientSocket = null;
            System.out.println("Listening for connection..");
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("Accept failed, Exception - " + e.getMessage());
                System.exit(1);
            }

            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clients.add(clientHandler);
            pool.execute(clientHandler);
        }
    }

}