/*
 * This is an example of standard Java wait/notify mechanism
 */

//init
enum State {
        Initialized,
        Running,
        Paused,
        Finished,
        Idle
    }

State state = State.Initialized; // state variable
Object stateChanged = new Object(); //lock object

//calling from UI thread
@Override 
public void onPause() {        
    synchronized(stateChanged) { // lock???
        state = State.Paused;
        while(true) {
            try {
                stateChanged.wait(); // wait for notifyall
                break;
            } catch(InterruptedException e) { 
            }
        }
    }
    super.onPause();
}

//calling from loop in helper thread
//both UI thread and helper thread share the state and lock objects
State state = null;

synchronized(stateChanged) {
    state = this.state;
}

if(state == State.Paused) {
    // ...do onPause processing            
    synchronized(stateChanged) {
        this.state = State.Idle;
        stateChanged.notifyAll(); // notify UI thread on processing finished
    }
}

