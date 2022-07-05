import java.io.*;
import java.net.*;

public class Server {
    Socket socket = null;
    ServerSocket server = null;
    DataInputStream in = null;
    FileWriter fw = null;

    public Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started\nWaiting for client");
            socket = server.accept();
            System.out.println("Client accepted");
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String line = "";

            while (!line.equals("Over")) {
                try {
                    line = in.readUTF();
                    System.out.println(line);
                    try {
                        File file = new File("/home/vivanp/Documents/file.txt");
                        if (file.createNewFile()) {
                            fw = new FileWriter("file.txt");
                            System.out.println("Created file");
                            System.out.println("Writing to file");
                            fw.write(in.readUTF());
                        } else {
                            System.out.println("File already exists.");
                        }
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                } catch (IOException i) {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");

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