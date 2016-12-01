
package laberintonumeral;
import com.functions.IReglas;
import com.functions.UConfig;
import java.util.ArrayList;
import java.util.List;
/** 
 * @author TR-X Homie
 * @project LaberintoNumeral Santa-Lucia
 * @version 0.2
 * @license Creative Commons
 */
public final class Laberinto extends Tabla implements IReglas{
    private boolean MOSTRAR_POR_CONSOLA = UConfig.MENSAJES_CONSOLA;
    private boolean LIMITAR_INTERACCIONES = UConfig.LIMITAR;
    private boolean IGNORAR_PEORES_CAMINOS = UConfig.PERMITIR_TODOS_LOS_POSIBLES_CAMINOS;
    private int LIMITE_DE_INERACCIONES = UConfig.TOTAL_DE_LIMITE;
    private int i_LIMITE = 0;
    private int Menor = -1;
    private List<List> Mejores_Caminos;
    
    public List<List> get_Mejores_Caminos(){
        return this.Mejores_Caminos;
    }
    
    
    public int get_ElLimiteMenor(){
        return this.Menor;
    }
    
    /*public static void main(String[] args) {
        //  <INICIAR/> //
        Laberinto L = new Laberinto();
        // <PROCESAR/> //
        L.Inicia_Proceso();
        // <SALIDA //
        L.Imprimir_Mejores_Caminos();
    }*/

    public Laberinto() {
        super(); 
        Imprimir_Matriz();
    }
    
    public Laberinto(int i[]) {
        super(i);
        Imprimir_Matriz();
    }
    
    
    @Override
    public void Imprimir_Matriz(){
        Msj(this.toString(), true);
    }
    
    @Override
    public void Inicia_Proceso(){
        Mejores_Caminos = new ArrayList<List>();
        Logica(new int[]{0,0} , new int[]{0,0},0,new ArrayList<int[]>(),"");
        Msj("FIN\n",true);
    }
    
    @Override
    public void Imprimir_Mejores_Caminos(){
        if(this.Mejores_Caminos.size() <= 0){
            Msj("(?) No se ha Evaluado o no hay Caminos mejores",true);
            return;
        } 
        if(MOSTRAR_POR_CONSOLA){
            Msj("TOTAL DE MEJOR/ES CAMINO/S: "+Mejores_Caminos.size(),true);
            List<int[]> l;
            int aux[];
            Msj("Mejor/es Camino/s:",true);
            for(int m = this.Mejores_Caminos.size(), i=0;i<m;i++){
                Msj((i+1)+":",false);
                l = this.Mejores_Caminos.get(i);
                for(int c = l.size(), j=0;j<c;j++ ){
                    aux = l.get(j);
                    Msj(" {"+aux[0]+","+aux[1]+"}",false);
                }
                Msj("",true);
            }
        }
    }
     
    
    @Override
    public String toString(){
        StringBuilder st = new StringBuilder();
        st.append(super.toString());
        return st.toString();
    }
    
       
    /**
     * TR-XHOMIE
     * Fuerza a salirse del Iterator
     */
    private void Limita(){
        if(LIMITAR_INTERACCIONES)
            i_LIMITE++;
    }
    
    private boolean esMovimientoDiagonal(int[] pos1, int[] pos2){
        return (pos1[0] == pos2[0]+1 && pos1[1] == pos2[1]+1)
                || (pos1[0] == pos2[0]-1 && pos1[1] == pos2[1]-1)
                || (pos1[0] == pos2[0]-1 && pos1[1] == pos2[1]+1)
                || (pos1[0] == pos2[0]+1 && pos1[1] == pos2[1]-1);
    }
    
    /**
     * TR-XHOMIE
     * @param s Mensaje
     * @param line Salto de linea
     * Muestra mensajes por Consola
     */
    private void Msj(String s, boolean line){
        if(!MOSTRAR_POR_CONSOLA){
            return;
        }
        if(line)
            System.out.println(s);
        else
            System.out.print(s);
    }
        /**
         * TR-XHOMIE
     * @param posAnterior
         * @param posActual Posicion Actual
         * @param acum Acumulador
         * @param Lista Recorrido en Memoria 
         * @param Cadena Anexos de memoria sobre posiciion y valor
         * Logica Principal
         */
        protected void Logica(int posAnterior[], int posActual[], int acum, List<int[]> Lista, String Cadena){
        Limita();
        if(i_LIMITE > LIMITE_DE_INERACCIONES){
            Msj("LIMITADO", true);
            return;
        } 
        if(Meta(posActual)){
            acum += TABLERO[posActual[0]][posActual[1]]; 
            Lista.add(posActual);
            if(sobreLimite(acum) || esMovimientoDiagonal(posActual,posAnterior)){
               return;
            }else if(Menor == -1 || acum <= Menor){//Lista.add(posActual);
                /** <COMENTARIOS/> **/
                    if(Menor != -1 && acum < Menor){
                        this.Mejores_Caminos.clear();
                        Msj("!! NUEVO MEJOR CAMINO ENCONTRADO !!",true);
                    }else if(Menor != -1 && acum == Menor){
                        Msj("!! MEJOR CAMINO ACUMULADO !!",true);
                    }else{
                        Msj("!! ASIGNADO COMO PRIMER MEJOR CAMINO !!",true);
                    }
                /** </COMENTARIOS/> **/
                Menor = acum;
            }
            /** <COMENTARIOS/> **/
                if(MOSTRAR_POR_CONSOLA){
                    Msj(Cadena+"\nPOSICIÓN: {"+posActual[0]+","+posActual[1]+"} "
                            + "VALOR: "+TABLERO[posActual[0]][posActual[1]]
                            + " ACUMULADO: "+acum
                            +"\n TOTAL ACUMULADO: "+acum
                            +"\n ::::::::::::::: ", true);
                    Msj("\nCAMINO RECORRIDO:",false);
                    int[] aux;
                    for(int i=0,m=Lista.size() ; i <m ;i++){
                        aux = Lista.get(i);
                        Msj(" {"+aux[0]+","+aux[1]+"}",false);
                    }
                    Msj("\n ----------------------------- ",true);
                }
            /** </COMENTARIOS/> **/
            /**
             * Mejores_Caminos <= #Variable que contiene el Recorrido/s Final Tomado/s/>
             */
            this.Mejores_Caminos.add(Lista);
            /** <FIN DEL CAMINO> **/
            return; 
        }
        if(!Limite_M(posActual)){
            acum += TABLERO[posActual[0]][posActual[1]]; 
            /** <COMENTARIOS/> **/
                Cadena +=("\nPOSICIÓN: {"+posActual[0]+","+posActual[1]+"} VALOR: " +
                        TABLERO[posActual[0]][posActual[1]])
                        + " ACUMULADO: "+acum;
            /** </COMENTARIOS/> **/
            if(sobreLimite(acum) || esMovimientoDiagonal(posActual,posAnterior)){
               return;
            } 
            List<int[]> ls = new ArrayList<int[]>();
            ls.addAll(Lista);
            ls.add(posActual);
            //Lista.clear();
            //Lista = null;
            int S[] = new int[]{posActual[0],posActual[1]+1};
            int D[] = new int[]{posActual[0]+1,posActual[1]};
            Logica(posActual, D,acum, ls, Cadena);
            Logica(posActual, S,acum, ls,Cadena);
        }
    }
        
    /**
     * TR-XHOMIE
     * @param x Variable a Evaluar
     * @return true o false
     * Verifica si sobre pasa el limite
     */           
    private boolean Limite_M(int x[]){
      return ((x[0] < 0 || x[0] > TABLERO.length-1) || (x[1] < 0 || x[1] > TABLERO[x[0]].length-1)) ;
    }
    
     
        /**
         * TR-XHOMIE
         * @param acum Acumulador
         * @return true or false
         * Verifica si Sobre pasa el Acumulador
         */
    private boolean sobreLimite(int acum){
        return !IGNORAR_PEORES_CAMINOS && (Menor != -1 && acum > Menor);
    }
    /**
     * TR-XHOMIE
     * @param x Variable a Evaluar
     * @return true or false
     * Verifica si llego al final de la matriz
     */ 
    private boolean Meta(int x[]){
        return ((x[0] == TABLERO.length-1) && (x[1] == TABLERO[x[0]].length-1));
    } 
    
    public void Mostrar_MensajesPorConsola(boolean b){
        this.MOSTRAR_POR_CONSOLA = b;
    }
    
    public void Mostrar_TodosLosPosiblesCaminos(boolean b){
        this.IGNORAR_PEORES_CAMINOS = b;
    }
    
    public void Limitar_Interacciones(boolean b){
        this.LIMITAR_INTERACCIONES = b;
    }
    
    public void Limitar_Interacciones(boolean b, int l){
        this.LIMITAR_INTERACCIONES = b;
        this.LIMITE_DE_INERACCIONES = l;
    }
    
}
