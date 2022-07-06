import java.io.*;
import java.net.*;

public class Server {
    Socket socket = null;
    ServerSocket server = null;
    DataInputStream in = null;

    public Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started\nWaiting for client");
            socket = server.accept();
            System.out.println("Client accepted");
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String line = "";
            BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/vivanp/Documents/HomeServer/src/file.txt"));

            while (!line.equals("Over")) {
                try {
                    line = in.readUTF();
                    bw.write(line+"\n");


                    System.out.println(line);
                } catch (IOException i) {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");

            bw.close();
            socket.close();
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Server server = new Server(5000);
    }
}