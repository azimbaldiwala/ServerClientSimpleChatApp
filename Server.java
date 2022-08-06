import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class Server extends Frame implements ActionListener {
    

    ServerSocket ss;
    Socket s;
    PrintWriter pr;
    InputStreamReader in;
    BufferedReader bf;

    Label d;
    

    TextArea chat;
    TextField reply;
    Button send;


    String displayText;


    Server(){

        super("Server side interface");


        setVisible(true);
        setLayout(null);
        setSize(500, 500);


        d = new Label("Waiting for client to connect");
        d.setBounds(100,150,300,300);
        add(d);

        displayText = "";

        try{

            // Creating a Server socket..

            ss = new ServerSocket(4999);
            s = ss.accept();

                System.out.println("Client connected");

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


            recMsg(); //call here and after action is performed.


    }


    public void recMsg(){

        String msg = "";
        while(true){

            try{
                msg += "Client: " +  bf.readLine() + "\n";
                

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

        displayText += "You: " + tex + "\n";
        chat.setText(displayText);
    }

    

    public static void main(String args[]){

        new Server();
    }
    
}
