// Written by F.Q Member from HomieStart group http://creativecommons.org/publicdomain/zero/1.0/
package core.Hilos;

import core.Avanced.Invoke;
import java.util.logging.Level;
import java.util.logging.Logger;

/*****************************
 * @title Java-Threads       *
 * @author TR-X Homie        *
 * @version 1.0              *
 * @github HomieStart        *
 * @license Creative Commons *
 ****************************/

public final class ExecuteThread {    
    /** [Execute any method with synchronized threads]
     * @param O Object class
     * @param MethodName Method of the Object Class to interact
     * @param Params Params of the Method class or <p>NULL<p>
    */
    public final static synchronized void ExecuteSynchronizedMethodThread(Object O, String MethodName, Object... Params){
        Invoke.InvokeMethod(O, MethodName, Params);
    }
    /** [Wait for Thread finish]
     *@param T Thread to interact
    */
    public final static void ThreadJoin(Thread T){
        try {
            T.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ExecuteThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /** [Only start a Thread]
     * 
     * @param T Thread to interact
     **/
    public final static void ThreadStart(Thread T){
        if(!T.isAlive()){
            T.start();
        }
    }
    /** [do sleep a thread]
     * @param T Thread
     * @param Sleep Time in miliseconds to sleep thread 
     **/
    public final static void ThreadSleep(Thread T, long Sleep){
        try {
            T.sleep(Sleep);
        } catch (InterruptedException ex) {
            Logger.getLogger(ExecuteThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private ExecuteThread(){}
}
