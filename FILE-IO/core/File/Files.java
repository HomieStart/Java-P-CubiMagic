// Written by F.Q Member from HomieStart group http://creativecommons.org/publicdomain/zero/1.0/

package core.File;

/*****************************
 * @title Java-IO FILES      *
 * @author TR-X Homie        *
 * @version 1.0              *
 * @github HomieStart        *
 * @license Creative Commons *
 ****************************/

public abstract class Files {
    protected long TIMER;
    protected int BUFFER_SIZE;
    /**
     * [Files]
     * @param KiloBytes define size of buffer
     */
    public Files(int KiloBytes){
        this.TIMER = 0;
        this.BUFFER_SIZE = 1024 * KiloBytes;
    }
    
    /**
     * [Get total time elapsed when finalize a action]
     * @return time elapse when execute any action 
     */
    protected long getTimeElapsed(){
        return this.TIMER;
    }
    
}
