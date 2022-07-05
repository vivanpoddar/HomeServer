import java.io.*;
import java.net.*;

public class Client {
    Socket socket = null;
    DataInputStream input = null;
    DataOutputStream out = null;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected to " + address + ":" + port);

            input = new DataInputStream(System.in);
            out = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException e) {
            System.out.println(e);
        }

        String line = "";

        while(!line.equals("Over")) {
            try {
                line = input.readLine();
                BufferedReader reader = new BufferedReader(new FileReader(line));
                String documentLine = reader.readLine();
                while(documentLine!=null) {
                    out.writeUTF(documentLine);
                    documentLine = reader.readLine();
                }
                reader.close();

            } catch(IOException i) {
                System.out.println(i);
            }
        }

        try {
            out.writeUTF("Over");
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            input.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main (String[] args) {
        Client client = new Client("10.0.0.34", 5000);
    }
}