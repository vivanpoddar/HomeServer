import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        socket = new Socket(String.valueOf(args[0]), Integer.parseInt(args[1]));

        System.out.println("Connected to " + args[0] + ":" + args[1]);
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        System.out.println("Choose destination target");
        DataInputStream input = new DataInputStream(System.in);
        String line = input.readLine();
        output.writeUTF(line);

        File file = new File(String.valueOf(args[2]));
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
