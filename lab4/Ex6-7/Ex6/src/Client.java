import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Ma conectez la server.");
        Socket s = new Socket("127.0.0.1", 4050);
        System.out.println("Conexiune realizata!");
        //......
        BufferedReader fluxIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter fluxOut = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);

        fluxOut.println("CJ 09 MPO");
        String response = fluxIn.readLine();
        System.out.println(response);

        fluxOut.println("CJ 09 MqO");
        response = fluxIn.readLine();
        System.out.println(response);

        fluxOut.println("CJ 09 MSO");
        response = fluxIn.readLine();
        System.out.println(response);

        fluxOut.println("CJ 09 MRO");
        response = fluxIn.readLine();
        System.out.println(response);
        Thread.sleep(5000);

        fluxOut.println("CJ 09 MPO");
        response = fluxIn.readLine();
        System.out.println(response);
        Thread.sleep(5000);

        fluxOut.println("CJ 09 MRO");
        response = fluxIn.readLine();
        System.out.println(response);

        fluxOut.println("close connection");
        response = fluxIn.readLine();
        System.out.println(response);


        s.close();

    }
}
