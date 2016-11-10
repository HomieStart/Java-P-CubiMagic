// Written by F.Q Member from HomieStart group http://creativecommons.org/publicdomain/zero/1.0/

package window.chat.jframes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.xml.ws.Holder;

/*****************************
 * @title Java-Client        *
 * @author TR-X Homie        *
 * @version 1.0              *
 * @github HomieStart        *
 * @license Creative Commons *
 ****************************/

public class fromclient extends JFrame implements ActionListener {
	private static final long serialVersionUID = -6592737477818639105L;
	private String HOST = "127.0.0.1";
	private JTextField txtmsj;
	private JButton btnenv;
	private int PORT = 2002;
	
	//private int[] dimensions = new int[]{10, 10, 200, 20};
	
	/**
	 * [Construct]
	 */
	public fromclient() {
		txtmsj = new JTextField();
		//x,y,h,w
		txtmsj.setBounds(10,10,200,20);
		add(txtmsj);
		
		btnenv = new JButton();
		btnenv.setText("Enviar");
		btnenv.setBounds(10,40,150,20);
		btnenv.addActionListener(this);
		add(btnenv);
		
		setLayout(null);
		setSize(400,400);
		setVisible(true);
	}

	public static void main(String[] args) { 
		new fromclient();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource() == btnenv){
			 try{
				 Socket cli = new Socket(HOST, PORT);
				 DataOutputStream flujo= new DataOutputStream(cli.getOutputStream());
				 flujo.writeUTF(txtmsj.getText());
				 cli.close();
			 }catch(Exception ex){
				 System.out.println("Error Connection: " + ex.getMessage());
			 }
		 }
		
	}

}
