// Written by F.Q Member from HomieStart group http://creativecommons.org/publicdomain/zero/1.0/

package core.Hilos;

import core.Avanced.Invoke;

/*****************************
 * @title Java-Threads       *
 * @author TR-X Homie        *
 * @version 1.0              *
 * @github HomieStart        *
 * @license Creative Commons *
 ****************************/

public class ThreadAnon{
    private final Runnable thread;
    private Object O;
    private String Method;
    private Object[] args;
    
    /**
     * [Anonymous Thread]
     * @param o any object Class
     * @param MethodName a method of the object class
     * @param Paramsargs params of the method <p>OR NULL<p> 
     */
    public ThreadAnon(Object o, String MethodName, Object... Paramsargs) {
        this.O = o;
        this.Method = MethodName;
        this.args = Paramsargs;
        thread = new Runnable() {
            @Override
            public void run() {
                 Invoke.InvokeMethod(O, Method, args);
            }
        };
    }
    
    /**
     * [Return the thread]
     * @return this intance of thread
     */
    public final Runnable getThread(){
        return this.thread;
    }
    
    /**
     * [Chage method used by the Thread]
     * @param o any object Class
     * @param MethodName a method of the object class
     * @param Paramsargs params of the method <p>OR NULL<p> 
     */
    public final void changeMehtod(Object o, String MethodName, Object... Paramsargs) {
        this.O = o;
        this.Method = MethodName;
        this.args = Paramsargs;
    }    
}
