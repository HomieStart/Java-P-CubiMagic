// Written by F.Q Member from HomieStart group http://creativecommons.org/publicdomain/zero/1.0/

package core.Hilos;

import java.util.HashMap;
import java.util.Map;

/*****************************
 * @title Java-Threads       *
 * @author TR-X Homie        *
 * @version 1.0              *
 * @github HomieStart        *
 * @license Creative Commons *
 ****************************/

public class TypeThread {
    public static enum Types{
        TYPE1 ("Thread Extend"),
        TYPE2 ("Thread Runnabled"),
        TYPE3 ("Thread Anonymous");
        
        private final String type;
        
        Types(String type){
            this.type = type;
        }
        
        public String getType(){
            return this.type;
        }
    };
    
    public Types retuTyp(){
        return Types.TYPE1;
    }
    
    private static TypeThread x;
    private static HashMap<String, String> M;
    
    public static TypeThread getIntance(){
        if(x == null){
            x = new TypeThread();
            M = new HashMap<String, String>();
            M.put("Type1", "Thread Extend");
            M.put("Type2", "Thread Runnable");
            M.put("Type3", "Thread Anonymous");
        }
        return x;
    }
    
    public Map GetTypes(){
        return (Map)M;
    }
    
    public String AssingType(String key){
        if(M.containsKey(key))
            return M.get(key);
        return null;
    }
    
    private TypeThread(){
        //UnSupported
    }
}
