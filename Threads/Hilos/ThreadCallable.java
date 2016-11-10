// Written by F.Q Member from HomieStart group http://creativecommons.org/publicdomain/zero/1.0/

package core.Hilos;

/*****************************
 * @title Java-Threads       *
 * @author TR-X Homie        *
 * @version 1.0              *
 * @github HomieStart        *
 * @license Creative Commons *
 ****************************/

public class ThreadCallable extends Callables {
    /**
     * [Construct]
     * @param name set name of this intance 
     */
    public ThreadCallable(String name) {
        super(name);
    }
    
    /**
     * [Run thread]
     * @return a value when finally the loops 
     */
    @Override
    public String call(){
        try{
        //TODO: WRITE YOU CODE HERE
        
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}
