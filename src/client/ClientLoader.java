package client;

import client.packet.OPacket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientLoader {

    private static Socket socket;
    public static void main(String[] args) throws IOException {
        connect();
        handle();
      //  end();

    }

    public static void sendPacket(OPacket packet)
    {
         try {

             ObjectOutputStream dos=new ObjectOutputStream(socket.getOutputStream());
             dos.writeObject(packet.getState());
             dos.flush();
         }catch (IOException ex) {ex.printStackTrace();}
    }

    public static Socket getSocket()
    {
        return socket;
    }
    public static void connect()
    {
        try {
            socket=new Socket("localhost",8888);
            System.out.println( socket );
        }catch(IOException ex) {ex.printStackTrace();}
    }

    public static void handle() {
        TextFieldTest p = new TextFieldTest();
    }

    public static void end()
    {
       try {
           System.out.println( "close clbent" );
           socket.close();
       }catch (IOException ex) {ex.printStackTrace();}
    }
}
