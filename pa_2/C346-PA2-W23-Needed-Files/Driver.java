
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kerly Titus
 */
public class Driver {

  /**
   * main class
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {

    Thread network = new Thread(new Network());/* Activate the network */
    network.start();

    Thread server1 = new Thread(new Server("1"));
    server1.start();

    Thread server2 = new Thread(new Server("2"));
    server2.start();

    Thread sendingClient = new Thread(new Client("sending")); /* Start the sending client thread */
    sendingClient.start();

    Thread receivingClient = new Thread(new Client("receiving")); /* Start the receiving client thread */
    receivingClient.start();

    /*
     * .............................................................................
     * .............................................................................
     * ....
     */

  }

}
