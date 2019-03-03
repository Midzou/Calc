package server.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Map;

public abstract class OPacket {


    private Socket socket;

    public Socket getSocket() {

        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public abstract short getId();

    public abstract void write(DataOutputStream dos) throws IOException;

    public abstract void read(Map<Integer, String> text) throws IOException;

    public abstract void handel();



}
