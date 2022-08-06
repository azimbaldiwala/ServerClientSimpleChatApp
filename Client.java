import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;


public class Client extends Frame implements ActionListener{

    Socket s;
    PrintWriter pr;
    InputStreamReader in;
    BufferedReader bf;

    Label d; // To display any message.


    TextArea chat;
    TextField reply;
    Button send;

    String displayText;


    Client(){

        super("Client side interface");
        


        setVisible(true);
        setLayout(null);
        setSize(500, 500);

        d  = new Label("Waiting for the connection to the sever....");
        d.setBounds(100,150,300,300);
        add(d);

        displayText = "";

        try{

            s = new Socket("localhost", 4999);

            d.setText("");
            
            pr = new PrintWriter(s.getOutputStream());
            in = new InputStreamReader(s.getInputStream());
            bf = new BufferedReader(in);
        }catch(Exception e){
            e.printStackTrace();
        }
        

            d.setBounds(0,0,0,0);  // removing label form the screeen

            chat = new TextArea("");
            chat.setBounds(50, 50, 400, 300);
            add(chat);

            reply = new TextField();
            reply.setBounds(50, 380, 320, 50);
            add(reply);
            
            send = new Button("Send");
            send.setBounds(385, 380, 70, 50);
            add(send);
            send.addActionListener(this);

            recMsg();

    }


    
   public void recMsg(){

        String msg = "";
        while(true){


            
            try{
                msg += "Server: " +  bf.readLine() + "\n";

            

            chat.setText("");
            displayText += msg;
            chat.setText(displayText);

            }catch(Exception e){}
            
          
            

        }
    }



    public void actionPerformed(ActionEvent e){

         String tex;
        tex = reply.getText();

        pr.println(tex);
        pr.flush();

        reply.setText("");

        chat.setText(" ");
        displayText += "You: " + tex + "\n";
        chat.setText(displayText);
    }

    

    public static void main(String args[]){
        new Client();

    }    


    

}
