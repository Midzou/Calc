package client.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class PackerAuthorize extends OPacket {
    private String nickname;
    private String password;
    private  Map<Integer, String> states;

    public PackerAuthorize(String nickname,String password)
    {
        this.password=password;
        this.nickname = nickname;
       // System.out.println(password);
    }
    @Override
    public String getId() {
        return  "1";
    }

    @Override
    public String getNickname() {

        return nickname;
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getRepitPassword() {
        return null;
    }


    @Override
    public Object getState() {
        states = new HashMap<Integer, String>();
        states.put(1, getId());
        states.put(2, getNickname());
        states.put(3, getPassword());

        return states;
    }

    @Override
    public Object getStr() {
        return null;
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
