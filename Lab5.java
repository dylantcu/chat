import java.awt.*;
import java.awt.event.*;
import java.net.*;
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
	Socket sock;
	final static int PORT = 4445;
	static String SERVER = "127.0.0.1";
	
	public Lab5(){
		setLayout(new FlowLayout());
		setSize(700,400);
		setBackground(new Color(154,66,161));
		chat.setBackground(new Color(202,238,255));
		userInput.setBackground(new Color(202,238,255));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		add(new JLabel
				(new ImageIcon("/Users/dylanbarth/Desktop/Academic Papers/Cosc/Techniques of Programming/src/chatroom.png")));		
		scroller.getViewport().add(chat);
		add(scroller);
		add(userInput); userInput.addActionListener(this);
		add(send); send.addActionListener(this); send.setEnabled(false);
		add(connect); connect.addActionListener(this);
		add(errorLabel);
		add(unicodes);unicodes.setText("THESE ARE THE CODES FOR UNICODE CHARACTERS");
	}

	
	
	public static void main(String[] args){
		Lab5 dumb = new Lab5();
		dumb.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(connect)){
			SERVER = userInput.getText().trim();
			System.out.println(SERVER);
			send.setEnabled(true);
		}
		if(e.getSource().equals(send)){
			Socket socket = null;
			
			try {
				socket = new Socket(SERVER, PORT);
				System.out.print("CONNECTED TO SERVER");
			} catch (UnknownHostException eh) {
				System.out.println("Can't find the server");
				System.exit(1);
			} catch (IOException el) {
				System.out.println("Server not responding");
			}
			
			
			try {
				BufferedReader user  = new BufferedReader(new InputStreamReader(System.in));

				BufferedReader is = new BufferedReader(new 
										 InputStreamReader(socket.getInputStream()));
				PrintWriter os = new PrintWriter(new 
										 BufferedOutputStream(socket.getOutputStream()));

				String inputLine, resultLine;
				System.out.print("1");
				while ((inputLine = is.readLine()) != null) {
					System.out.print("2");
					os.println(inputLine);	//send user input to the server
					os.flush();
					resultLine = is.readLine();	//read server response
					chat.setText(chat.getText() + "\n" + resultLine);
					if (resultLine.equals("quit"))
						break;
				}
				user.close();
				os.close();
				is.close();
				socket.close();
				System.out.print("3");
			} 
			catch (IOException k) {
				System.out.println("I/O error: " + k);
			}


		}
		
	}
	
}
