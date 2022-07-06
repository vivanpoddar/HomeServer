import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        DataInputStream input = new DataInputStream(System.in);
        System.out.println("Select destination computer");
        String host = String.valueOf(input.readLine());
        System.out.println("Select destination port");
        int port = Integer.parseInt(String.valueOf(input.readLine()));

        socket = new Socket(host, port);
        System.out.println("Connected to " + host + ":" + port);
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        System.out.println("File pathname");
        String pathname = String.valueOf(input.readLine());
        File file = new File(pathname);
        // Get the size of the file
        long length = file.length();
        byte[] bytes = new byte[16 * 1024];
        InputStream in = new FileInputStream(file);
        OutputStream out = socket.getOutputStream();

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        socket.close();
    }
}
