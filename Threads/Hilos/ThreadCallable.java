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
    
    public ThreadCallable(String name) {
        super(name);
    }
    
    @Override
    public String call(){
        try{
        //TODO: WRITE YOU CODE HERE
        
        }catch(Exception e){
            System.out.println(e);
        }
        return "Finish " + this.name;
    }
}
