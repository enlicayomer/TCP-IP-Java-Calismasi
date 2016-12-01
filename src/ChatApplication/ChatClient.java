package ChatApplication;

import java.io.*;
import java.net.*;

public class ChatClient implements Runnable {

    private static Socket clientSocket = null;
    private static PrintStream ps = null;
    private static DataInputStream dis = null;
    private static BufferedReader giris = null;
    private static boolean kapaliMi = false;

    public static void main(String[] args) {

        int portNo = 3336;
        String host = "localhost";

       

        /*
         * Girilen host ve port numaralar� ile Client a��lmas�
         */
        try {
            clientSocket = new Socket(host, portNo);
            giris = new BufferedReader(new InputStreamReader(System.in));
            ps = new PrintStream(clientSocket.getOutputStream());
            dis = new DataInputStream(clientSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("Ba�lant� sa�lanamad�");
        }

        /*
         * Ba�lant�n�n ba�ar�l� �ekilde ger�ekle�mesi halinde mesaj yazma
         */
        if (clientSocket != null && ps != null && dis != null) {
            try {

                /* Serverdan okuma i�in Thread */
                new Thread(new ChatClient()).start();
                while (!kapaliMi) {
                    ps.println(giris.readLine().trim());
                }

                ps.close();
                dis.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }

    @Override
    public void run() {
        String cevap;
        try {
            while ((cevap = dis.readLine()) != null) {
                System.out.println(cevap);
                if (cevap.indexOf("Gule") != -1) {
                    break;
                }
            }
            kapaliMi = true;
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
}