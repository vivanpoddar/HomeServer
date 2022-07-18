import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(5000);
            System.out.println("Server started.");
        } catch (IOException ex) {
            System.out.println("Can't setup server on this port number. ");
        }

        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        DataInputStream din = null;
        String path = null;

        try {
            socket = serverSocket.accept();
            System.out.println("Connected to " + serverSocket.getInetAddress());
        } catch (IOException ex) {
            System.out.println("Can't accept client connection. ");
        }

        try {
            in = socket.getInputStream();
        } catch (IOException ex) {
            System.out.println("Can't get socket input stream. ");
        }

        while(din.readUTF() != "over") {
            try {
                din = new DataInputStream(socket.getInputStream());
                String[] msg = din.readUTF().split("\\s+");
                if (msg[0] == "target") {
                    path = msg[1];
                } else if (msg.length == 1) {
                    out = new FileOutputStream(path);
                    byte[] bytes = new byte[16*1024];

                    int count;
                    while ((count = in.read(bytes)) > 0) {
                        out.write(bytes, 0, count);
                    }
                }
            } catch (FileNotFoundException ex) {
                System.out.println("File not found. ");
            }
        }

        out.close();
        in.close();
        socket.close();
        serverSocket.close();
    }
}