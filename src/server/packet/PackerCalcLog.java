package server.packet;


import server.Logging;

import java.io.*;
import java.util.*;

public class PackerCalcLog extends OPacket {
    private String str;
    Map<Integer, String> equation = new HashMap<Integer, String>();

    public PackerCalcLog() {}


    public PackerCalcLog(String str) {
        str = this.str;
    }



    @Override
    public short getId() {
        return 3;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
    }

    @Override
    public void read(Map<Integer, String> text) throws IOException {
        try {
            str = (String) text.get(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handel() {
        try {
            Logging.read(str +" Derived from cache ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
