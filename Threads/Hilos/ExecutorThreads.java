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
    
    private ExecutorThreads(){
        
    }
    
    public final static void ExecutorService(Callables c, int ThreadsPools, boolean CompletionService){
        ExecutorService exec = Executors.newFixedThreadPool(ThreadsPools);
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
    
    
    private static void ShowTaskFinish(Future<?> task){
        try {
            System.out.println(task.get());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(ExecutorThreads.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
