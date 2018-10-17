package de.uulm.vs.grn.workshop.pizza.service;

import de.uulm.vs.grn.workshop.pizza.Italiana;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static de.uulm.vs.grn.workshop.pizza.Italiana.PORT_NUMBER;

/*
 * Aufgabe 2
 * Single-Threaded Server
 */
public class PizzaService {
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(Italiana.PORT_NUMBER);

            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();

                System.out.println("neuer Client");
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream));
                String input = readNext(inputStream,6);
                System.out.println(input);

                outputStream.write(("Pizza "+input+" wird zubereitet...\n").getBytes());
                try{Thread.sleep(Italiana.menu.get(input) * 1000);}catch(InterruptedException e){}
               outputStream.write(("Pizza "+input+" kann abgeholt werden!\n").getBytes());

                socket.close();
            }

        }catch(IOException e){
            e.printStackTrace();
        }



    }
    public static String readNext(InputStream inputStream,int length){
        StringBuffer input= new StringBuffer();
        for(int i=0;i<length;i++){
            int tmp;
            try {
                if ((tmp = inputStream.read()) != -1) {
                    input.append((char) tmp);
                }else break;
            }catch(IOException e){}
        }
        return input.toString();
    }
}
