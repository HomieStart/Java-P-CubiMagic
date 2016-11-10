// Written by F.Q Member from HomieStart group http://creativecommons.org/publicdomain/zero/1.0/

package window.chat.jframes;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket; 
import javax.swing.JFrame;
import javax.swing.JTextArea;

/*****************************
 * @title Java-Sercer        *
 * @author TR-X Homie        *
 * @version 1.0              *
 * @github HomieStart        *
 * @license Creative Commons *
 ****************************/

public class fromServer extends JFrame implements Runnable {
 	private static final long serialVersionUID = 3298614648869037947L;
	private JTextArea txtmsjs;
	private int PORT = 2002;
	/**
	 * [Construct]
	 */
	public fromServer() {
		txtmsjs = new JTextArea();
		txtmsjs.setBounds(10,10,400,300);

		//Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		//int x = (int) ((dimension.getWidth() - txtmsjs.getWidth()) / 2);
		//int y = (int) ((dimension.getHeight() - txtmsjs.getHeight()) / 2);
		//txtmsjs.setLocation(x, y);
		add(txtmsjs);

		setLayout(null);
		setSize(500,500);
		setVisible(true);
		Thread hilo = new Thread(this);
		hilo.start();
	}

	@Override
	public void run() {
		try {
			ServerSocket servidor = new ServerSocket(PORT);
			Socket client;
			while(true){
				client = servidor.accept();
				DataInputStream flujo = new DataInputStream(client.getInputStream());
				String msg = flujo.readUTF();
				txtmsjs.append("\n"+ client.getInetAddress() +":"+msg);
				client.close();
				if(msg.equalsIgnoreCase("FIN")){
					servidor.close();
					break;
				}
			}	
		} catch (IOException e) { 
			e.printStackTrace();
		}


	}
	

	public static void main(String[] args) {
		new fromServer();
	}

}
