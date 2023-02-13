package batch3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;


//THIS IS THE SEVER APPLICATION
public final class App {
    private App() {
    }

    public static void main(String[] args) throws IOException {
            
        //need the random class to carry out randomize operation
        Random random = new Random();
        //generate a random number between 0 and 99
        Integer randomNumber = random.nextInt(100);

        //store my guess
        Integer myGuess = 0;
        
        //open socket to listen on port 1234
        ServerSocket ss = new ServerSocket(1234);
        System.out.println("Server started on 1234...");
        Socket s = ss.accept();
        System.out.println("Client connected");

        //preparing input coming in from socket
        InputStream is = s.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        //prepare sending data out using socket to client (sending out)
        OutputStream os = s.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        String msgRecv = "";

        //System.out.println("Guess the number between 0 - 99: ");

        //while input is not equals to quit
        while(!msgRecv.equalsIgnoreCase("quit")){

            //guess XX
            msgRecv = dis.readUTF();

            if(msgRecv.contains("guess")){
                myGuess = Integer.parseInt(msgRecv.substring(6)); //guess xx the number xx starts from index 6
            }

            if(myGuess < randomNumber){
                dos.writeUTF("Go higher");

            }
            else if(myGuess > randomNumber){
                dos.writeUTF("Go lower");
            }
            else{
                dos.writeUTF("Your guess is correct!");
                //scanner.close();
            }

            //ensure records are written and send across the socket
            dos.flush();

        }

        //close the input and output streams
        dos.close();
        bos.close();
        os.close();

        dis.close();
        bis.close();
        is.close();


        //expect input from keyboard using scanner
        //convert to expect from inputStream if it is a socket app
        //Scanner scanner = new Scanner(System.in);

        //allow user to guess until guess is correct
        //while(myGuess != randomNumber){
            //get the input and assign to myGuess
            //myGuess = scanner.nextInt();

        }
}

