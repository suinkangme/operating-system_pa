/**
 * Class Monitor
 * To synchronize dining philosophers.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */

import java.util.concurrent.locks.*;

public class Monitor
{

	private enum State{THINKING, HUNGRY, EATING};
	private State[] state;
	


	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers)
	{
		// TODO: set appropriate number of chopsticks based on the # of philosophers
		
		this.state = new State[piNumberOfPhilosophers];
		this.self = new Condition[piNumberOfPhilosophers];

		for(int i = 0; i < piNumberOfPhilosophers; i++){
			state[i] = State.THINKING;
			self[i] = lock.newCondition();
		}
	}	

	/*
	 * -------------------------------
	 * User-defined monitor procedures
	 * -------------------------------
	 */

	 private void test(int piTID) {
		 
         	if (state[(piTID + state.length - 1) % state.length] != State.EATING &&
            	state[piTID] == State.HUNGRY &&state[(piTID + 1) % state.length] != State.EATING) 
		{
           	 	state[piTID] = State.EATING;
           	 	self[piTID].signal();
        	}
   	}
 

	/**
	 * Grants request (returns) to eat when both chopsticks/forks are available.
	 * Else forces the philosopher to wait()
	 */
	public synchronized void pickUp(final int piTID)
	{	
		

        	try{
            		state[piTID] = State.HUNGRY;
            		test(piTID);
           		if (state[piTID] != State.EATING) {
                		self[piTID].wait();
            		}
        	}catch (InterruptedException e) {
            		Thread.currentThread().interrupt();
        	} 
		
	}

	/**
	 * When a given philosopher's done eating, they put the chopstiks/forks down
	 * and let others know they are available.
	 */
	public synchronized void putDown(final int piTID)
	{
		

        	try{

            		state[piTID] = State.THINKING;
			test((piTID + state.length - 1) % state.length);
            		test((piTID + 1) % state.length);

        	}catch(Exception e){
			e.getMessage();
		}

		
	}

	/**
	 * Only one philopher at a time is allowed to philosophy
	 * (while she is not eating). check if she is eating or not 
	 */
	public synchronized void requestTalk(final int piTID)
	{
		

        	try{
            		while (state[piTID] == State.EATING) {
                	wait();
            		}
        	}catch (InterruptedException e) {
            		Thread.currentThread().interrupt();
		}

        	
        
	}

	/**
	 * When one philosopher is done talking stuff, others
	 * can feel free to start talking.
	 */
	public synchronized void endTalk()
	{
		notifyAll();
	}

	// Initialization code
    	public void initialize() {
        	for (int i = 0; i < state.length; i++) {
            		state[i] = State.THINKING;
        	}
    	}


}

// EOF
