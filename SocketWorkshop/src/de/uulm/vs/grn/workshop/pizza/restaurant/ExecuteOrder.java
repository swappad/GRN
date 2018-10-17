package de.uulm.vs.grn.workshop.pizza.restaurant;

import de.uulm.vs.grn.workshop.pizza.Italiana;

import java.io.*;

/**
 * Aufgabe 1
 */
public class ExecuteOrder {

    // add your code to take orders
    public static void main(String[] args) {

        try{
        FileInputStream fileInputStream = new FileInputStream("bestellungen.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        String tmp;

        while((tmp = bufferedReader.readLine())!= null){
            if(!Italiana.menu.containsKey(tmp)){
                System.out.println("Bestellung falsch!");
            }else{
                System.out.println("Pizza "+tmp+" wird zubereitet");
            }
        }
        bufferedReader.close();

        }catch(IOException e){
            e.printStackTrace();
        }


    }
}
