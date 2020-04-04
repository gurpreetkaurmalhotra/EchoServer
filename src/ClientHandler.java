import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ClientHandler implements Runnable {

    private BufferedReader input;
    private PrintWriter output;
    private static ConcurrentHashMap<String, AtomicLong> countString = new ConcurrentHashMap<>();


    public ClientHandler(Socket clientSocket) throws IOException{
        output = new PrintWriter(clientSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    @Override
    public void run(){

        try {
            String inputline;

            while ((inputline = input.readLine()) != null) {
                System.out.println("Server: " + inputline);
                output.println(countString.computeIfAbsent(inputline, k -> new AtomicLong()).incrementAndGet());
                if (inputline.equals("quit")) {
                    break;
                }
            }

        } catch (IOException e){
            System.err.println("IO Exception in Client Handler - " + e.getMessage());
        } finally {
            output.close();

            try {
                input.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }

        }

    }

}