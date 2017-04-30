import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.io.*;
import javax.swing.*;


public class Lab5 extends JFrame implements ActionListener {
	JTextArea chat = new JTextArea(10,50);
	JTextField userInput = new JTextField(30);
	JButton send = new JButton("SEND");
	JButton connect = new JButton("CONNECT");
	JLabel unicodes = new JLabel();
	JLabel errorLabel = new JLabel();
	JScrollPane scroller = new JScrollPane();
	Socket socket;
	BufferedReader in;
	PrintWriter out;
	Thread thread;
	final static int PORT = 4444;
	static String SERVER = "127.0.0.1";
	
	public Lab5(){
		setLayout(new FlowLayout());
		setSize(700,400);
		setBackground(new Color(154,66,161));
		chat.setBackground(new Color(202,238,255));
		userInput.setBackground(new Color(202,238,255));
		userInput.setText("FORMAT: 'SERVER IP ADDRESS' 'PORT' 'USERNAME'");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		add(new JLabel
				(new ImageIcon("/Users/dylanbarth/Desktop/Academic Papers/Cosc/Techniques of Programming/src/chatroom.png")));		
		scroller.getViewport().add(chat);
		add(scroller);
		add(userInput); userInput.addActionListener(this);
		add(send); send.addActionListener(this); send.setEnabled(false);
		add(connect); connect.addActionListener(this);
		add(errorLabel);
		add(unicodes);unicodes.setText("<html>THESE ARE THE CODES FOR UNICODE CHARACTERS:" + "<br>" +
				"<center>bear-----------ʕ•ᴥ•ʔ<br>" +
				"hmm----------( ͡° ͜ʖ ͡°)<br>" +
				"tussle----------(ง ͠° ͟ل͜ ͡°)ง<br>" +
				"not amused-----ಠ_ಠ<br></center></html>"
				);
	}

	
	
	public static void main(String[] args){
		Lab5 dumb = new Lab5();
		dumb.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent evt) {
		
		try {
			if (evt.getSource().equals(connect) && connect.getText().equals("CONNECT")) {
				System.out.print("Connect Successfully pressed");
				Scanner scanner = new Scanner(userInput.getText());
				if (!scanner.hasNext()) return;
				String host = scanner.next();
				if (!scanner.hasNextInt()) return;
				int port = scanner.nextInt();
				socket = new Socket(host, port);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), false);
				thread = new ReadThread(in, chat);
				thread.start();
				send.setEnabled(true);
				connect.setText("DISCONNECT");
				userInput.setText("");
			}
			else if (evt.getActionCommand().equals("DISCONNECT")) {
				thread.interrupt();
				socket.close();
				in.close();
				out.close();
				send.setEnabled(false);
				connect.setText("Connect");
			}
			else if (evt.getSource().equals(send) && send.isEnabled()) {
				out.print(userInput.getText() + "\r\n");
				out.flush();
				chat.append("> " + userInput.getText() + '\n');
				userInput.setText("");
			}
		} catch(UnknownHostException uhe) {
			errorLabel.setText(uhe.getMessage());
		} catch(IOException ioe) {
			errorLabel.setText(ioe.getMessage());
		}
	}
	
	
}
