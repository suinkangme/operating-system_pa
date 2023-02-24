
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kerly Titus
 */

public class Driver extends Thread{

    /** 
     * main class
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        

    	Network objNetwork = new Network("network");
        
        /* Complete here the code for the main method ...*/
        // we don't have to create each thread!  
        
        Thread network_thread = new Thread(objNetwork);
        network_thread.start();
        
        Server objServer = new Server();
        Thread server_thread = new Thread(objServer);
        server_thread.start();
        
        Client sender_thread = new Client("sending");
        Thread send_thread = new Thread(sender_thread);
        send_thread.start();
        
        Client receiver_thread = new Client("receiving");
        Thread receive_thread = new Thread(receiver_thread);
        receive_thread.start();


    }



}
