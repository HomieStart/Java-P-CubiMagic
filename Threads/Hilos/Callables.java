// Written by F.Q Member from HomieStart group http://creativecommons.org/publicdomain/zero/1.0/
package core.Hilos;

import java.util.concurrent.Callable;

/*****************************
 * @title Java-Threads       *
 * @author TR-X Homie        *
 * @version 1.0              *
 * @github HomieStart        *
 * @license Creative Commons *
 ****************************/

/*
* <This class Callable, return any value defined by Dev>
*/
public class Callables implements Callable<String> {
    protected String name;
    /**
     * [Construct]
     * @param name Name of this thread object 
     */
    public Callables(String name){
        this.name = name;
    }
    /**
     * [Run Action of this thread]
     * @return value when finalize all loops.
     */
    @Override
    public String call() {
        try{
        //TODO: Enter You code Here;
        
        }catch(Exception e){
            System.out.println(e);
        }
        //Every return any state value defined
        //Example return this.name; OR return "Any string value";
        return null;
    }
}
