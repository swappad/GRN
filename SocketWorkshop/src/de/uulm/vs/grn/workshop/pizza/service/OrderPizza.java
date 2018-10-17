package de.uulm.vs.grn.workshop.pizza.service;

import de.uulm.vs.grn.workshop.pizza.Italiana;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/*
 * Aufgabe 2
 * Single-Threaded Server
 */
public class OrderPizza {
    public static void main(String[] args) {
        if (args.length < 1 || !Italiana.menu.containsKey(args[0])) {
            System.err.println("Please specify a valid pizza.");
            System.err.println();
            System.err.println("Italiana Menu:");

            for (String pizza: Italiana.menu.keySet()) {
                System.err.println(" * " + pizza);
            }
            System.exit(1);
        }
        try{
            System.out.print(InetAddress.getLocalHost());
            Socket socket = new Socket(InetAddress.getLocalHost(),Italiana.PORT_NUMBER);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            System.out.println(args[0]);
            outputStream.write(("Hawaii").getBytes());

            System.out.println(PizzaService.readNext(inputStream,200));


            socket.close();

        }catch(IOException e){
            e.printStackTrace();
        }




    }
}
