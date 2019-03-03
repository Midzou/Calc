package client.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PackerCalcEngine extends OPacket {
    private String str;
    private  Map<Integer, String> states;

    public PackerCalcEngine(String str)
    {
        this.str=str;
    }
    @Override
    public String getId() {
        return  "3";
    }


    @Override
    public String getStr() {

        return str;
    }

    @Override
    public String getNickname() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getRepitPassword() {
        return null;
    }


    @Override
    public Object getState() {
        states = new HashMap<Integer, String>();
        states.put(1, getId());
        states.put(2, getStr());


        return states;
    }


    @Override
    public void write(DataOutputStream dos) throws IOException {

    }

    @Override
    public void read(DataInputStream dis) throws IOException {

    }

    @Override
    public void handle() {

    }
}

