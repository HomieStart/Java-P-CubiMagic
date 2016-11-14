package cubemagic;

import java.util.Scanner;

/**
 *
 * @author FQ Trx-homie
 * @project CubeMagic - Santa Lucia
 * @version 0.1
 * @license Creative Commons
 */
public final class CubeMagic {
    private final int DIMENSION;
    private int[][] Cube;
    
     /**
     * Constructor
     * @param DIMENSION 
     */
    public CubeMagic(int DIMENSION){
        this.DIMENSION = DIMENSION;
        this.init();
    }
    
    /**
     * Inicializador
     */
    private void init(){
        int Col=(this.DIMENSION)/2;
        //int contenido = 1;
        this.Cube = new int[this.DIMENSION][this.DIMENSION];
        this.Cube[0][Col] = 1;
        for(int val = 1, Fila = 0;val < (this.DIMENSION*this.DIMENSION);){
          if(val % this.DIMENSION == 0){
              Fila++;
          }  
          else{
              if(Fila == 0){
                  Fila = this.DIMENSION-1;
              }else{
                  Fila--;
              }if(Col == this.DIMENSION-1){
                  Col = 0;
              }else{
                  Col++;
              }
          }
              val++;
              this.Cube[Fila][Col] = val;
        }
            
    }
    /**
     * Muestra datos finales
     * @return 
     */
    @Override
    public String toString(){
        System.out.println("Cubo Magico");
        for(int Fila=0;Fila<this.DIMENSION;Fila++){
            for(int Col=0;Col<this.DIMENSION;Col++)
 	  	         {  String Cnv=String.valueOf(this.Cube[Fila][Col]);
 	  	            if(Cnv.length()==1 )System.out.print("  ["+Cnv+"]");
                            else if(Cnv.length()==2 )System.out.print(" ["+Cnv+"]");
                            else if(Cnv.length()>=3 )System.out.print("["+ Cnv+"]"); 
 	  	          }
 	  	          System.out.println("");
 	  	       }
        return ("Dimension:"+this.DIMENSION+"x"+this.DIMENSION);
    } 
    
   
    /**
     * Menu principal por Consola
     * @param args 
     */
    public static void main(String[] args) {
        int r = 0;
        boolean e = false;
        System.out.println("Cubo Magico de Dimensiones Impar");
        while(r % 2 == 0){
            if(!e){
                e = true;
            }else{
                System.out.println("INTRODUSCA DIMENSION IMPAR VALIDA");
                for(int z=0;z < 5;z++)
                    System.out.println();
            }
                System.out.print("INTRODUSCA DIMENSION: ");
                Scanner sc = new Scanner(System.in);
                try{
                    r = sc.nextInt();
                    if(r < 0){
                        throw new Exception();
                    }
                }catch(Exception c){
                    System.out.println("ERROR : INTRODUSCA NUMEROS VALIDOS POSITIVOS!!");
                    r = 0;
                }
        }
        CubeMagic c = new CubeMagic(r);
        System.out.println(c.toString());
    }
    
}

