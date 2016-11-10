// Written by F.Q Member from HomieStart group http://creativecommons.org/publicdomain/zero/1.0/
package core.Hilos;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
/*****************************
 * @title Java-Threads       *
 * @author TR-X Homie        *
 * @version 1.0              *
 * @github HomieStart        *
 * @license Creative Commons *
 ****************************/

public final class ExecutorThreads {
    /**
     * DO NOT USE, NOT FINISH
     * [Execute a service with Callers threads]
     * @param c Callable thread
     * @param ThreadsPools threads pools sub process <p> define One <p>
     * @param CompletionService 
     */
    public final static void ExecutorService(Callables c, int ThreadsPools, boolean CompletionService){
        ExecutorService exec = Executors.newFixedThreadPool(ThreadsPools); //a idea how much threads executed
        Future<?> task;
        if(CompletionService){
            //ExecutorCompletionService<?>
            /*
                Entrega el primero thread que termina y luego el subsiguiente
            */
            ExecutorCompletionService es = new ExecutorCompletionService(exec);
            task = es.submit(c);
        }else{
            /*
                Entrega los thread despues que el ultimo termina
            */
            task = exec.submit(c);
        }
        while(!task.isDone()){
            // Do Any Thing
        }
        ShowTaskFinish(task);
    }
    
     /**
     * DO NOT USE, NOT FINISH
     * [Execute a service with Callers threads]
     * @param c Runnable thread
     * @param ThreadsPools threads pools sub process <p> define One <p>
     * @param CompletionService 
     */
    public final static void ExecutorService(Runnable c, int ThreadsPools, boolean CompletionService){
        ExecutorService exec = Executors.newFixedThreadPool(ThreadsPools);
        Future<?> task;
        if(CompletionService){
            //ExecutorCompletionService<?>
            /*
                Entrega el primero thread que termina y luego el subsiguiente
            */
            ExecutorCompletionService es = new ExecutorCompletionService(exec);
            task = es.submit(c, null); // OBJECT Resutl OPTIONAL
        }else{
            /*
                Entrega los thread despues que el ultimo termina
            */
            task = exec.submit(c);
        }
        while(!task.isDone()){
            // Do Any Thing
        }
        ShowTaskFinish(task);
    }
    
    /**
     * Show the final value for Callables threads
     * @param task a future task for interact
     */
    private static void ShowTaskFinish(Future<?> task){
        try {
            System.out.println(task.get());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(ExecutorThreads.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ExecutorThreads(){
        //not supported
    }
    
}
