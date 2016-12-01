
package laberintonumeral;

import com.functions.MatrizNumeral; 
import com.functions.UConfig;
/** 
 * @author TR-X Homie
 * @project LaberintoNumeral Santa-Lucia
 * @version 0.2
 * @license Creative Commons
 */
public abstract class Tabla {
    public int TABLERO[][];
    private int[] DIMENSIONES = UConfig.DIMENSIONES_POR_DEFECTO;
  
    public Tabla(int Dimension[]) {
        this.DIMENSIONES = Dimension;
        this.TABLERO = new int[this.DIMENSIONES[0]][this.DIMENSIONES[1]];
        this.Rellenar(); 
    }
    
    public Tabla() {
        this.TABLERO = new int[this.DIMENSIONES[0]][this.DIMENSIONES[1]];
        this.Rellenar(); 
    }
    
    public final void Rellenar(){
        MatrizNumeral.Rellenar(TABLERO);
    }
    
    @Override
    public String toString(){
        StringBuilder st = new StringBuilder();
        st.append("\nDimensiones: ")
                .append(this.DIMENSIONES[0])
                .append("x")
                .append(this.DIMENSIONES[1]);
        st.append("\nArea Total: ")
                .append(this.DIMENSIONES[0]*this.DIMENSIONES[1]);
        st.append("\n====<TABLERO>====");
        for(int x=0;x < this.TABLERO.length;x++){
            st.append("\n{");
            for(int z=0;z < this.TABLERO[x].length;z++){
                st.append(" ")
                        .append(this.TABLERO[x][z])
                        .append(" ");
            }
            st.append("}");
        }
        st.append("\n====</TABLERO=====\n"); 
        return st.toString();
    } 
    
    public int[] getDimensiones(){
        return this.DIMENSIONES;
    }
    
    public int[][] getTabla(){
        return this.TABLERO;
    }
    
    public static void Mostrar(int param){ 
        System.out.println(param);
    }
}
