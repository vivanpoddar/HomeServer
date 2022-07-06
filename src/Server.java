import java.io.*;
import java.net.*;

public class Server {
    Socket socket = null;
    ServerSocket server = null;
    FileInputStream in = null;
    FileOutputStream fout = null;

    public Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started\nWaiting for client");
            socket = server.accept();
            System.out.println("Client accepted");
            in = new FileInputStream(String.valueOf(socket.getInputStream()));
            String line = "";
            fout = new FileOutputStream("/home/vivanp/Downloads/HomeServer/src/file.txt");
            fout.write(in.readAllBytes());
            fout.close();
            in.close();


        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Server server = new Server(5000);
    }
}