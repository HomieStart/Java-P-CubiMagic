import GUI.Principal;
import com.functions.UConfig;
import laberintonumeral.Laberinto;
 

/** 
 * @author TR-X Homie
 * @project LaberintoNumeral Santa-Lucia
 * @version 0.2
 * @license Creative Commons
 */
public final class main {
    /**
     * @USAR_INTERFAZ <= PERMITE EL USO DE LA GUI
     * @DIMENSION_A & DIMENSION_B <= Define las Dimensiones de la Matriz
     */
    private static final boolean USAR_INTERFAZ = UConfig.USAR_INTERFAZ_GRAFICA;
    private static final int DIMENSION_A = UConfig.DIMENSIONES_POR_DEFECTO[0];
    private static final int DIMENSION_B = UConfig.DIMENSIONES_POR_DEFECTO[1];
    
    private main(){
        
    }
    
    public static void Iniciar(){
        if(USAR_INTERFAZ){ 
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
             * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
             */
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new Principal().setVisible(true);
                }
            });
        }else{
            /** <DEFINIR_DIMENSIONES_DE_MATRIZ/> 
             *  Dimension_A X Dimension_B
             **/ 
            int DEFINIR_DIMENSIONES[] = new int[]{DIMENSION_A,DIMENSION_B}; 
            /** <INICIAR/> **/
            Laberinto L = new Laberinto(DEFINIR_DIMENSIONES);
             /** <PROCESAR/> **/
            L.Inicia_Proceso();
            /** <SALIDA/>**/
            L.Imprimir_Mejores_Caminos();
        }
    }
    public static void main(String[] args) {
         Iniciar();
    }
}
