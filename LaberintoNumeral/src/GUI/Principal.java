package GUI;

import com.functions.Actionlisten;
import com.functions.IReglas;
import com.functions.MatrizNumeral;
import com.functions.UConfig; 
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension; 
import java.awt.GridLayout;
import java.awt.Image; 
import java.awt.Rectangle; 
import java.awt.Toolkit;
import java.awt.event.KeyEvent; 
import java.util.List;
import javax.swing.Action; 
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;  
import laberintonumeral.Laberinto;
 

/** 
 * @author TR-X Homie
 * @project LaberintoNumeral Santa-Lucia
 * @version 0.2
 * @license Creative Commons
 */
public class Principal extends javax.swing.JFrame implements IReglas {
    private Laberinto L;
    private int[] Dimensiones;
    private JButton Buttons[][];
    private JLabel label; 
    
    public Principal() {
        super("Proyecto Santa Lucia");
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/img/t.png"));
        this.setIconImage(icon); 
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        Reinicia();
        Buttons = null;
        L = null;
        txt_Limites.setText(UConfig.TOTAL_DE_LIMITE+"");
        txt_Estado.setForeground(Color.YELLOW);
        txt_Estado.setText("INACTIVO");
        chk_Limitar.setSelected(UConfig.LIMITAR);
        chk_MSJ_Consola.setSelected((UConfig.MENSAJES_CONSOLA));
        chk_Permitir_Todos.setSelected(UConfig.PERMITIR_TODOS_LOS_POSIBLES_CAMINOS);
        chk_Mostar_Aviso.setSelected(UConfig.MOSTRAR_AVISO_DE_PREPROCESO);
        GUI_Actualiza();
    }
    
    
    private void GUI_Inicia(){
        txt_Estado.setBackground(Color.YELLOW);
        txt_Estado.setText("INACTIVO");
         Pane_Matriz.removeAll(); 
    }
    
    private void GUI_Actualiza(){ 
        if(Pane_Matriz.getComponentCount() <= 0){
            return;
        }
        Component xy = Pane_Matriz.getComponent((Pane_Matriz.getComponentCount()-1));
        Pane_Matriz.setPreferredSize(new Dimension(xy.getLocation().x + 40,xy.getLocation().y + 80));
        Pane_Matriz.revalidate();
        Pane_Matriz.repaint(); 
    }
    
    @Override
    public void Imprimir_Matriz(){
        txt_Estado.setForeground(Color.red);
        txt_Estado.setText("PROCESANDO, ESPERE..."); 
        if(Dimensiones == null){
            System.out.println("Warning: Error de Definición - Dimensiones");
            return;
        }
        L = new Laberinto(Dimensiones);
        Dimensiones = L.getDimensiones();
        Buttons = new JButton[Dimensiones[0]][Dimensiones[1]];
        if(this == null){
            return;
        }
        GUI_Inicia(); 
        label = new JLabel(" Mejor Camino !");
        label.setForeground(Color.BLUE);
        label.setBounds(0,0, 200, 20);
        Pane_Matriz.add(label);
        MatrizNumeral.Recorrer(L.getTabla(), this, "context_GenerarMatriz", true);
        GUI_Actualiza();
    }
    
    private boolean esLimite(int t[]){
        return (t[0] < 0 || t[0] > Buttons.length-1) || (t[1] < 0 
                || t[1] > Buttons[t[0]].length-1);
    }
    
    public void context_GenerarMatriz(int[] Tablero, int Valor){ 
        if(Buttons == null){
            System.out.println("NULOS");
            return;
        }
        if(esLimite(Tablero)){
            return;
        }      
        Buttons[Tablero[0]][Tablero[1]] = new JButton();
        Buttons[Tablero[0]][Tablero[1]].setBounds(Tablero[0] * 30, label.getLocation().y + 30 + Tablero[1] * 30, 35, 35);
        Buttons[Tablero[0]][Tablero[1]].setAction(((Action) new Actionlisten(this,"Posicion","Valor: "+Valor+"\nPosicion: {"+Tablero[0]+","+Tablero[1]+"}")));
        Buttons[Tablero[0]][Tablero[1]].setText(Valor+"");
        Buttons[Tablero[0]][Tablero[1]].setBorderPainted(true);
        Buttons[Tablero[0]][Tablero[1]].setBackground(Color.red);
          
          Pane_Matriz.add(Buttons[Tablero[0]][Tablero[1]]);
    }  
    
    private void GUI_Procesa(boolean b){
        if(b){
            
        }else{
            
        }
    }
    
    @Override
    public void Inicia_Proceso(){
        txt_Estado.setForeground(Color.red);
        txt_Estado.setText("PROCESANDO, ESPERE...");
        if(!isDefineMatriz()){
            System.out.println("Warning: Error de Definición - Matriz");
            return;
        }
        GUI_Procesa(true);
        L.Inicia_Proceso();
        GUI_Procesa(false);
    }
    
    public void GUI_MuestraMejoresCaminos(){
        List<List> x = L.get_Mejores_Caminos();
        if(x.size() > 1){
            ((JLabel)(Pane_Matriz .getComponent(0))).setText("Mejores Caminos, Total: "+x.size());
        }
        if(x.size() <= 0){
            ((JLabel)(Pane_Matriz .getComponent(0))).setText("Matriz Invalida");
        } 
        List<int[]> l;
        int[]aux = new int[2]; 
        for(int m = x.size(), i=0;i<m;i++){ 
                l = x.get(i);
                if(i == 0){
                    for(int c = l.size(), j=0;j<c;j++ ){
                        aux = l.get(j);

                        if(!esLimite(aux)){ 
                            if(Buttons[aux[0]][aux[1]].getBackground() != Color.GREEN){

                                Buttons[aux[0]][aux[1]].setBackground(Color.GREEN);
                            }   
                        }
                  } 
                }else{
                        JButton b[][] = new JButton[Buttons.length][Buttons[Buttons.length-1].length];
                        //bug. Incremento factorial inecesario: Hace que los cuadrados nuevos se alejen entre si.
                        int xs;
                        if(i == 1){
                           xs = Buttons[Buttons.length-1][Buttons[Buttons.length-1].length-1].getLocation().x + 50;
                        }else if (i == 2){
                            xs = (Buttons[Buttons.length-1][Buttons[Buttons.length-1].length-1].getLocation().x + 50)/2;
                        }else{
                            xs = ((Buttons[Buttons.length-1][Buttons[Buttons.length-1].length-1].getLocation().x + 50)/2)-100;
                        }
                        System.out.println("INICIO "+xs);
                        for(int r=0; r<b.length;r++){
                            for(int t=0; t<b[r].length;t++){ 
                                Rectangle re = Buttons[r][t].getBounds();
                                re.setLocation(re.getLocation().x + xs, re.getLocation().y);
                                b[r][t] = new JButton();
                                b[r][t].setBounds(re);
                                b[r][t].setBackground(Color.red);
                                b[r][t].setAction(((Action) new Actionlisten(this,"Posicion","Valor: "+Buttons[r][t].getText()+"\nPosicion: {"+r+","+t+"}")));
                                b[r][t].setText(Buttons[r][t].getText());
                                Pane_Matriz.add(b[r][t]);
                            }
                        }
                            for(int c = l.size(), j=0;j<c;j++ ){
                                aux = l.get(j);

                                if(!esLimite(aux)){ 
                                    if(b[aux[0]][aux[1]].getBackground() != Color.GREEN){
                                        b[aux[0]][aux[1]].setBackground(Color.GREEN);
                                    }   
                                }
                        } 
                            Buttons = b;
                    }
                        GUI_Actualiza();
                }  
    }
    
    @Override
    public void Imprimir_Mejores_Caminos(){
        if(!isDefineMatriz()){
            System.out.println("Warning: Error de Definición - Matriz");
            return;
        }
        L.Imprimir_Mejores_Caminos();
        GUI_MuestraMejoresCaminos();
    }
    
    private boolean isDefineMatriz(){
        return this.L != null;
    }
    
    private void Generar(int Dimension_A,int Dimension_B){
            /** <INICIAR/> **/
            Dimensiones = new int[]{Dimension_A,Dimension_B}; 
            this.Imprimir_Matriz();
             /** <PROCESAR/> **/
            this.Inicia_Proceso();
            /** <SALIDA/>**/
             this.Imprimir_Mejores_Caminos();
            txt_Estado.setForeground(Color.GREEN);
            txt_Estado.setText("FINALIZADO");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        JPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        txt_Dimension_A = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_Dimension_B = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_Estado = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Pane_Matriz = new javax.swing.JPanel(new GridLayout(2, 5));
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txt_Limites = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        chk_Limitar = new javax.swing.JCheckBox();
        chk_MSJ_Consola = new javax.swing.JCheckBox();
        chk_Permitir_Todos = new javax.swing.JCheckBox();
        chk_Mostar_Aviso = new javax.swing.JCheckBox();
        Tuerca = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jTabbedPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        JPanel.setBackground(new java.awt.Color(0, 102, 0));
        JPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton1.setText("INICIAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txt_Dimension_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_Dimension_AFocusLost(evt);
            }
        });
        txt_Dimension_A.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_Dimension_AMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txt_Dimension_AMouseReleased(evt);
            }
        });
        txt_Dimension_A.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Dimension_AKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Dimensiones");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("X");

        txt_Dimension_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_Dimension_BFocusLost(evt);
            }
        });
        txt_Dimension_B.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_Dimension_BMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txt_Dimension_BMouseReleased(evt);
            }
        });
        txt_Dimension_B.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Dimension_BKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 204, 204));
        jLabel4.setText("GitHub");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("ESTADO:");

        txt_Estado.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        txt_Estado.setForeground(new java.awt.Color(0, 0, 0));

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("HomieStart");

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("PROYECTO");

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("SANTA LUCIA");

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("ver: 0.2");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setToolTipText("");
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setViewportBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jScrollPane1.setInheritsPopupMenu(true);
        jScrollPane1.setNextFocusableComponent(Pane_Matriz);

        Pane_Matriz.setBackground(new java.awt.Color(255, 255, 255));
        Pane_Matriz.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout Pane_MatrizLayout = new javax.swing.GroupLayout(Pane_Matriz);
        Pane_Matriz.setLayout(Pane_MatrizLayout);
        Pane_MatrizLayout.setHorizontalGroup(
            Pane_MatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
        );
        Pane_MatrizLayout.setVerticalGroup(
            Pane_MatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 276, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(Pane_Matriz);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("sansserif", 1, 10)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("Tr.x,Homie@Gmail.com");

        jLabel11.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        int extra = 80;
        jLabel11.setForeground(new java.awt.Color(0, 204, 204));
        ImageIcon i = new javax.swing.ImageIcon(getClass().getResource("/GUI/img/git.png"));
        Icon icon = new ImageIcon(i.getImage().getScaledInstance(jLabel1.getWidth()+extra,jLabel1.getHeight()+extra,Image.SCALE_DEFAULT));
        jLabel11.setIcon(icon);

        javax.swing.GroupLayout JPanelLayout = new javax.swing.GroupLayout(JPanel);
        JPanel.setLayout(JPanelLayout);
        JPanelLayout.setHorizontalGroup(
            JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(JPanelLayout.createSequentialGroup()
                        .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPanelLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel4))
                            .addGroup(JPanelLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel9))
                            .addGroup(JPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
            .addGroup(JPanelLayout.createSequentialGroup()
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel10)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8))
                    .addGroup(JPanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Dimension_A, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Dimension_B, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Estado)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPanelLayout.setVerticalGroup(
            JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelLayout.createSequentialGroup()
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(txt_Dimension_A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txt_Dimension_B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(txt_Estado))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Principal", JPanel);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        txt_Limites.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_LimitesKeyReleased(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Limite total de Interacciones");

        chk_Limitar.setForeground(new java.awt.Color(255, 255, 255));
        chk_Limitar.setText("Limitar Iterator de bucle");
        chk_Limitar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chk_LimitarStateChanged(evt);
            }
        });
        chk_Limitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_LimitarActionPerformed(evt);
            }
        });

        chk_MSJ_Consola.setForeground(new java.awt.Color(255, 255, 255));
        chk_MSJ_Consola.setText("Mostrar Texto por Consola");
        chk_MSJ_Consola.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chk_MSJ_ConsolaStateChanged(evt);
            }
        });
        chk_MSJ_Consola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_MSJ_ConsolaActionPerformed(evt);
            }
        });

        chk_Permitir_Todos.setForeground(new java.awt.Color(255, 255, 255));
        chk_Permitir_Todos.setText("No Ingorar peores Caminos");
        chk_Permitir_Todos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chk_Permitir_TodosStateChanged(evt);
            }
        });
        chk_Permitir_Todos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_Permitir_TodosActionPerformed(evt);
            }
        });

        chk_Mostar_Aviso.setForeground(new java.awt.Color(255, 255, 255));
        chk_Mostar_Aviso.setText("Mostrar Aviso de Pre-Proceso");
        chk_Mostar_Aviso.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chk_Mostar_AvisoStateChanged(evt);
            }
        });
        chk_Mostar_Aviso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_Mostar_AvisoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(chk_Permitir_Todos, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(chk_MSJ_Consola, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(174, 174, 174)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chk_Limitar)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txt_Limites, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)))
                                .addContainerGap())))
                    .addComponent(chk_Mostar_Aviso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chk_Permitir_Todos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chk_MSJ_Consola)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chk_Mostar_Aviso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chk_Limitar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Limites, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        int extra2 = 80;
        jLabel11.setForeground(new java.awt.Color(0, 204, 204));
        i = new javax.swing.ImageIcon(getClass().getResource("/GUI/img/tuerca.png"));
        icon = new ImageIcon(i.getImage().getScaledInstance(jLabel2.getWidth()+extra,jLabel2.getHeight()+extra,Image.SCALE_DEFAULT));
        Tuerca.setIcon(icon);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Tuerca, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(209, 209, 209))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(194, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(Tuerca, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jTabbedPane1.addTab("Opciones Generales", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_Dimension_BKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Dimension_BKeyPressed
        try {
            if(evt.getExtendedKeyCode() != KeyEvent.VK_BACK_SPACE
                && evt.getExtendedKeyCode() != KeyEvent.VK_ENTER){
                String s = new StringBuilder().append(evt.getKeyChar()).toString();
                int a = Integer.parseInt(s);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Los campos Deben ser solo Numeros!", "Error de Dimensiones", JOptionPane.ERROR_MESSAGE); // ICON);
        System.out.println(e);
        txt_Dimension_B.setText("0");
        }
    }//GEN-LAST:event_txt_Dimension_BKeyPressed

    private void txt_Dimension_BMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_Dimension_BMouseReleased
        if(txt_Dimension_B.getText().equals("") || txt_Dimension_B.getText().equals(" ")){
            txt_Dimension_B.setText("0");
        }
    }//GEN-LAST:event_txt_Dimension_BMouseReleased

    private void txt_Dimension_BMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_Dimension_BMouseClicked
        if(txt_Dimension_B.getText().equals("0")|| txt_Dimension_B.getText().equals(" ")){
            txt_Dimension_B.setText("");
        }
    }//GEN-LAST:event_txt_Dimension_BMouseClicked

    private void txt_Dimension_AKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Dimension_AKeyPressed
        try {
            if(evt.getExtendedKeyCode() != KeyEvent.VK_BACK_SPACE
                && evt.getExtendedKeyCode() != KeyEvent.VK_ENTER){
                String s = new StringBuilder().append(evt.getKeyChar()).toString();
                int a = Integer.parseInt(s);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Los campos Deben ser solo Numeros!", "Error de Dimensiones", JOptionPane.ERROR_MESSAGE); // ICON);
        System.out.println(e);
        txt_Dimension_A.setText("0");
        }
    }//GEN-LAST:event_txt_Dimension_AKeyPressed

    private void txt_Dimension_AMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_Dimension_AMouseReleased
        
    }//GEN-LAST:event_txt_Dimension_AMouseReleased

    private void txt_Dimension_AMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_Dimension_AMouseClicked
        if(txt_Dimension_A.getText().equals("0")){
            txt_Dimension_A.setText("");
        }
    }//GEN-LAST:event_txt_Dimension_AMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            
        txt_Estado.setForeground(Color.red);
        txt_Estado.setText("PROCESANDO, ESPERE..."); 
            if(UConfig.MOSTRAR_AVISO_DE_PREPROCESO)
                JOptionPane.showMessageDialog(null, "Comenzara el Proceso, Haga Click en Aceptar");
            int a = Integer.parseInt(txt_Dimension_A.getText());
            int b = Integer.parseInt(txt_Dimension_B.getText());
            Generar(a,b);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Los campos Deben ser solo Numeros!", "Error de Dimensiones", JOptionPane.ERROR_MESSAGE); // ICON);
        System.out.println(e);
        Reinicia();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void chk_Permitir_TodosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chk_Permitir_TodosStateChanged
       
    }//GEN-LAST:event_chk_Permitir_TodosStateChanged

    private void chk_MSJ_ConsolaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chk_MSJ_ConsolaStateChanged
        
    }//GEN-LAST:event_chk_MSJ_ConsolaStateChanged

    private void chk_LimitarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chk_LimitarStateChanged
        
    }//GEN-LAST:event_chk_LimitarStateChanged

    private void txt_LimitesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_LimitesKeyReleased
        try {
            if(evt.getExtendedKeyCode() != KeyEvent.VK_BACK_SPACE
                && evt.getExtendedKeyCode() != KeyEvent.VK_ENTER){
                String s = new StringBuilder().append(evt.getKeyChar()).toString();
                int a = Integer.parseInt(s);
                if(a <0){
                    txt_Limites.setText("20");
                    UConfig.TOTAL_DE_LIMITE = 20;
                }else{
                    UConfig.TOTAL_DE_LIMITE = a;
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Los campos Deben ser solo Numeros!", "Error de Dimensiones", JOptionPane.ERROR_MESSAGE); // ICON);
            txt_Dimension_A.setText("20");
            UConfig.TOTAL_DE_LIMITE = 20;
            System.out.println(e);
        }
    }//GEN-LAST:event_txt_LimitesKeyReleased

    private void txt_Dimension_AFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_Dimension_AFocusLost
        if(txt_Dimension_A.getText().equals("")){
            txt_Dimension_A.setText("0");
        }
    }//GEN-LAST:event_txt_Dimension_AFocusLost

    private void txt_Dimension_BFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_Dimension_BFocusLost
        if(txt_Dimension_B.getText().equals("")){
            txt_Dimension_B.setText("0");
        }
    }//GEN-LAST:event_txt_Dimension_BFocusLost

    private void chk_Permitir_TodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_Permitir_TodosActionPerformed
 
        UConfig.PERMITIR_TODOS_LOS_POSIBLES_CAMINOS = chk_Permitir_Todos.isSelected();
    }//GEN-LAST:event_chk_Permitir_TodosActionPerformed

    private void chk_MSJ_ConsolaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_MSJ_ConsolaActionPerformed
        UConfig.MENSAJES_CONSOLA = chk_MSJ_Consola.isSelected();
    }//GEN-LAST:event_chk_MSJ_ConsolaActionPerformed

    private void chk_LimitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_LimitarActionPerformed
        UConfig.LIMITAR = chk_Limitar.isSelected();
    }//GEN-LAST:event_chk_LimitarActionPerformed

    private void chk_Mostar_AvisoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chk_Mostar_AvisoStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_Mostar_AvisoStateChanged

    private void chk_Mostar_AvisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_Mostar_AvisoActionPerformed
        UConfig.MOSTRAR_AVISO_DE_PREPROCESO = chk_Mostar_Aviso.isSelected();
    }//GEN-LAST:event_chk_Mostar_AvisoActionPerformed

    private void Reinicia(){
        txt_Dimension_A.setText("0");
        txt_Dimension_B.setText("0");
        
        txt_Estado.setForeground(Color.YELLOW);
        txt_Estado.setText("INACTIVO"); 
    }
    
    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanel;
    private javax.swing.JPanel Pane_Matriz;
    private javax.swing.JLabel Tuerca;
    private javax.swing.JCheckBox chk_Limitar;
    private javax.swing.JCheckBox chk_MSJ_Consola;
    private javax.swing.JCheckBox chk_Mostar_Aviso;
    private javax.swing.JCheckBox chk_Permitir_Todos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField txt_Dimension_A;
    private javax.swing.JTextField txt_Dimension_B;
    private javax.swing.JLabel txt_Estado;
    private javax.swing.JTextField txt_Limites;
    // End of variables declaration//GEN-END:variables
}
