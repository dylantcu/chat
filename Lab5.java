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
	JLabel errorLabel = new JLabel();
	JScrollPane scroller = new JScrollPane();
	Socket sock;
	
	public Lab5(){
		setLayout(new FlowLayout());
		setSize(700,300);
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
	}
	
	public static void main(String[] args){
		Lab5 dumb = new Lab5();
		dumb.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		
	}
	
}
