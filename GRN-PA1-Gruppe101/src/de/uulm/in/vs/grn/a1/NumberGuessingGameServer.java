package de.uulm.in.vs.grn.a1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class NumberGuessingGameServer {
    final static int PORT =8086;


    public static void main(String args[]){

        try{
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket client = serverSocket.accept();
            int number = ThreadLocalRandom.current().nextInt(51);
            System.out.println(number);
            OutputStream outputStream = client.getOutputStream();
            InputStream inputStream = client.getInputStream();

            //init
            outputStream.write("New Game: guess the number between 0 and 50 \n".getBytes());

            //game
            for (int i=0; i<6;i++){
                int guess = readInt(inputStream);
                System.out.println(guess);
                if(guess==number){
                    outputStream.write("you won!\n".getBytes());
                    break;
                }else if(guess<number & i!=5){
                    outputStream.write("greater\n".getBytes());
                }else if(i!=5){
                    outputStream.write("lower\n".getBytes());
                }else outputStream.write("you lost!\n".getBytes());
            }
            inputStream.close();
            outputStream.close();
            client.close();
        }catch(IOException e){e.printStackTrace();}
    }

    //byte from InputStream to integer conversion even if input was String from command-line or similar
    //no eof markers needed
    public static int readInt(InputStream inputStream)throws IOException{
           byte[] in = new byte[4];
           inputStream.read(in);
        return Integer.parseInt(new String(in).trim());
    }

}
