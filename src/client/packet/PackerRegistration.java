package client.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PackerRegistration extends OPacket {
    private String nickname;
    private String password;
    private String repitPassword;
    private  Map<Integer, String> states;

    public PackerRegistration(String nickname,String password,String repitPassword)
    {
        this.password=password;
        this.nickname = nickname;
        this.repitPassword=repitPassword;

    }
    @Override
    public String getId() {
        return  "2";
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

        return repitPassword;
    }


    @Override
    public Object getState() {
        states = new HashMap<Integer, String>();
        states.put(1, getId());
        states.put(2, getNickname());
        states.put(3, getPassword());
        states.put(4, getRepitPassword());

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

