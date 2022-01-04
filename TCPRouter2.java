import java.io.*;  
import java.net.*;  
import java.util.*;    

public class TCPRouter2
{  
   private static ServerSocket ServerSocket; 
   private static InetAddress host;
   private static final int Server_PORT = 20; 
   private static final int Client_PORT = 25;
   private static Socket ClientLink = null;
   
   public static void main(String[] args)  
   {  
      System.out.println("Port Açýlýyor");
      {  
          try  
          {  
        	  System.out.println("TCPRoute2 Baþlatýlýyor..\nLocalhost'un ip Adresi Alýnýyor..");
        	  host = InetAddress.getLocalHost();  
          }  
          catch(Exception uhEx)  
          {  
              System.out.println("Host'un ID'si bulunamadý !");  
              System.exit(1);  
          }  
            
      }
      try  
      {  
    	  
    	 ServerSocket = new ServerSocket(Server_PORT);  
         ClientLink = new Socket(host,Client_PORT);
         
      }  
      catch(IOException ioEx)  
      {  
         System.out.println("Yönlendirici için baðlantý noktasýna baðlanamýyor!!!");  
         System.exit(1);  
      }  
      
      handleClient();  
      
   }    
   private static String handleClient()  
   {  
      Socket ServerLink = null;                         
      
      try  
      {  
    	 ServerLink = ServerSocket.accept();            
        
         Scanner ServerInput =  
            new Scanner(ServerLink.getInputStream());  
         
         PrintWriter senderOutput = new PrintWriter(ServerLink.getOutputStream(), true);       
         
           String message = ServerInput.nextLine(); 
           
           
           Scanner ClientInput = new Scanner(ClientLink.getInputStream());      
   
           PrintWriter ClientOutput = new PrintWriter(ClientLink.getOutputStream(), true);   
        
           
         while (!message.equals("***KAPAT***")){ 
        	 
        	 if(!message.isEmpty())
        		 System.out.print("Sunucu mesajý "+message+"\t");
        	 
        	 ClientOutput.println(message);
        	 
        	 String str=ClientInput.nextLine();
        	 
        	 if(!str.isEmpty() && !message.isEmpty())
        		 System.out.println("Alýcadan mesaj: "+str);

        	 senderOutput.println(str);
        	 
        	 message = ServerInput.nextLine();
       
        }  
      
         
       }  

       catch(IOException ioEx)  
       {  
           ioEx.printStackTrace();  
       }    
       finally  
       {  
          try  
          {  
             System.out.println( "\n* Baðlantý kapatýlýyor*");  
             ServerLink.close(); 
             ClientLink.close();
          }  
          catch(IOException ioEx)  
          {  
              System.out.println( "Baðlanýlamadý!");  
            System.exit(1);  
          }  
       }
	return null;  
   }  
   
   }
  
