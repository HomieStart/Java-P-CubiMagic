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
    
    public final Runnable getThread(){
        return this.thread;
    }
    
    public final void changeMehtod(Object o, String MethodName, Object... Paramsargs) {
        this.O = o;
        this.Method = MethodName;
        this.args = Paramsargs;
    }
    
    public final Runnable GetThread(){
        return this.thread;
    }
    
}
