import javax.naming.OperationNotSupportedException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, OperationNotSupportedException {
        ServerSocket ss = new ServerSocket(8080);

        while (true) {
            System.out.println("Astept conexiune de la client...");
            Socket s = ss.accept(); //metoda blocanta
            System.out.println("Clientul s-a conectat!");
            //......
            BufferedReader fluxIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter fluxOut = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
            //......
            String line = "";
            while (!line.equals("Exit")) {
                line = fluxIn.readLine();
                if (!line.equals("Exit")) {
                    fluxOut.println("Result: " + getResult(line.split(" ")));
                }
            }

            s.close();
        }
    }

    private static String getResult(String[] operation) throws OperationNotSupportedException {
        double firstOperand = Double.parseDouble(operation[0]);
        String operator = operation[1];
        double secondOperand = Double.parseDouble(operation[2]);

        if (operation.length != 3) {
            return "Wrong expresion";
        }
        switch (operator) {
            case "+":
                return String.valueOf(firstOperand + secondOperand);
            case "*":
                return String.valueOf(firstOperand * secondOperand);
            case "-":
                return String.valueOf(firstOperand - secondOperand);
            case "/":
                return String.valueOf(firstOperand / secondOperand);
            default:
                return "Not implemented yet";
        }
    }
}
