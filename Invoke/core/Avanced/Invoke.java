// Written by F.Q Member from HomieStart group http://creativecommons.org/publicdomain/zero/1.0/

package core.Avanced;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/*****************************
 * @title Java-Invoke        *
 * @author TR-X Homie        *
 * @version 1.0              *
 * @github HomieStart        *
 * @license Creative Commons *
 ****************************/
public class Invoke {
    /** [Execute any method]
     * @param O Object class
     * @param MethodName Method of the Object Class to interact
     * @param Params Params of the Method class or <p>NULL<p>
    */
    public static void InvokeMethod(Object O, String MethodName, Object... Params){
        try {
            /*
            * Create a empty Method Object
            * Get class of object "O" an get any method with the Name of type
            * class (O.getClass());
            * Finnally Invoke Method
            */
            Method M = (O.getClass()).getMethod(MethodName, new Class[0]);
            M.invoke(O, Params);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
            Logger.getLogger(Invoke.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}