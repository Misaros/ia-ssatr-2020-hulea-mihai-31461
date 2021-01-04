import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Ma conectez la server.");
        Socket s = new Socket("127.0.0.1", 8080);
        System.out.println("Conexiune realizata!");
        //......
        BufferedReader fluxIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter fluxOut = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);

        fluxOut.println("25 + 20");
        String response = fluxIn.readLine();
        System.out.println(response);

        fluxOut.println("6 * 23");
        response = fluxIn.readLine();
        System.out.println(response);

        fluxOut.println("69 - 58");
        response = fluxIn.readLine();
        System.out.println(response);

        fluxOut.println("54 / 6");
        response = fluxIn.readLine();
        System.out.println(response);

        fluxOut.println("54 asd 6");
        response = fluxIn.readLine();
        System.out.println(response);

        fluxOut.println("54 / 6 asd");
        response = fluxIn.readLine();
        System.out.println(response);

        fluxOut.println("Exit");
        response = fluxIn.readLine();
        System.out.println(response);


        s.close();
    }
}
