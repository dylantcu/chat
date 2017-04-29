import java.net.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.io.*;

public class ServerSide {
  
	public static ArrayList<String> messages = new ArrayList<String>();
	
	
	public synchronized void postMessage(String messageString)
	{
		
	}
	
	public static void main(String[] args) throws IOException {
	   System.out.println("We Working");
	 while(true)
	 {
      ServerSocket serverSocket = null;
      try {
         serverSocket = new ServerSocket(4445);
      } catch (IOException e) {
         System.err.println("Could not listen on port: 4444.");
         System.exit(1);
      }

      Socket clientSocket = null;
      try {
         clientSocket = serverSocket.accept();
      } catch (IOException e) {
         System.err.println("Accept failed.");
         System.exit(1);
      }

      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(
                              new InputStreamReader(
                              clientSocket.getInputStream()));
      String inputLine, outputLine;
      outputLine = "Starting Up Server";
      //KnockKnockProtocol kkp = new KnockKnockProtocol();

      //outputLine = kkp.processInput(null);
      //I think sends output line to the interface
      out.println(outputLine);

      while ((inputLine = in.readLine()) != null) {
          //outputLine = kkp.processInput(inputLine);
    	  Hashtable<String, String> specials
    	     = new Hashtable<String, String>();
    	  specials.put("hmm", "( ͡° ͜ʖ ͡°)");
    	  specials.put("bear", "ʕ•ᴥ•ʔ");
    	  specials.put("tussle", "(ง ͠° ͟ل͜ ͡°)ง");
    	  specials.put("not amused", "ಠ_ಠ");
    	  
    	  if(specials.containsKey(inputLine))
    	  {
    		  addMessage(specials.get(inputLine), out);
    		  //out.println(specials.get(inputLine));
    	  }
    	  else
    	  {
    		  addMessage(inputLine, out);
    	  }
    	  
      }
      out.close();
      in.close();
      clientSocket.close();
      serverSocket.close();
	 }
   }
	
	public synchronized static void addMessage(String newMessage, PrintWriter outin)
	{
		messages.add(newMessage);
		outin.println(newMessage);
	}
	
}

class ClientThread extends Thread
{
	ClientThread()
	{
		
	}
	
	public void run()
	{
		
	}
}
	

