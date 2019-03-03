package client.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class OPacket {


    public abstract String getId();

    public abstract void write(DataOutputStream dos) throws IOException;

    public abstract void read(DataInputStream dis) throws IOException;

    public abstract void handle();

    public abstract Object getState();
    public abstract Object getStr();

    public abstract String getNickname();

    public abstract String getPassword();

    public abstract String getRepitPassword();




}
