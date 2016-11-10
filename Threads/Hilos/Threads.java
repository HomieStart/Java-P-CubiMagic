// Written by F.Q Member from HomieStart group http://creativecommons.org/publicdomain/zero/1.0/

package core.Hilos;

/*****************************
 * @param <Types>            *
 * @title Java-Threads       *
 * @author TR-X Homie        *
 * @version 1.0              *
 * @github HomieStart        *
 * @license Creative Commons *
 ****************************/

public abstract class Threads<Types> extends Thread{
    private final TypeThread.Types type;
    /**
     * [Construct]
     * @param name set name of intances 
     * @param T set type of the thread
     */
    public Threads(String name, TypeThread.Types T){
        super(name);
        this.type = T;
    }
    
    /**
     * GetType T
     * @return Type of Thread
     */
    public TypeThread.Types getType(){
        return type;
    }
    
     
}
