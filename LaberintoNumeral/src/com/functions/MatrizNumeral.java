package com.functions;
import java.lang.reflect.InvocationTargetException;  
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/** 
 * @author TR-X Homie
 * @project LaberintoNumeral Santa-Lucia
 * @version 0.2
 * @license Creative Commons
 */
public class MatrizNumeral {
    private static final int RANDOM_LIMIT = 10;
    
    public static boolean esMenor(int A, int B){
        return A < B;
    }
    
    public static void toHacer(Object x,String mtd, Object[] args, Object ob){
        try {
            if(args.length > 0){
                Class[] a = new Class[2];
                a[0] = int[].class;
                a[1] = int.class;
                int[] z = new int[]{(int)args[0],(int)args[1]};
                x.getClass().getMethod(mtd, a).invoke(x, z , ob);
            }else{
                x.getClass().getMethod(mtd, (Class)null).invoke(x, (Object)null);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(MatrizNumeral.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void Rellenar(int[][] param){
        Random rnd = new Random();
        for(int x=0;x < param.length;x++){
            for(int z=0;z < param[x].length;z++){
                param[x][z] = rnd.nextInt(RANDOM_LIMIT);
            }
        }
    }
    
    public static void Recorrer(int[][] param, Object clase, String funcion, boolean EnviarPos){
        for(int x=0;x < param.length;x++){
            for(int z=0;z < param[x].length;z++){
                if(EnviarPos)
                    MatrizNumeral.toHacer(clase, funcion,new Object[]{x,z}, (Object)param[x][z]);
                else{
                    MatrizNumeral.toHacer(clase, funcion,null, null);
                }
            }
        }
    }
    
    private MatrizNumeral(){
    }
}
