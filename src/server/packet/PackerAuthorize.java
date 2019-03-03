package server.packet;

import server.ClietnHandler;
import server.Logging;
import server.ServerLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PackerAuthorize extends OPacket {
    private String nickname;
    private String password;

    public PackerAuthorize() {}

    public PackerAuthorize(String nickname)
    {
        nickname =this.nickname;
    }
    @Override
    public short getId() {
        return 1;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {}

    @Override
    public void read(Map<Integer, String> text) throws IOException {
        try {
            nickname = (String) text.get(2); //considered a nickname
            password = (String) text.get(3); //considered a password
            System.out.println("Its read " +nickname + " " + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handel(){
        File File = new File("C://SomeDir//MyFile.txt");
        boolean haveLogin=false;
        try {
            File file = new File("C://SomeDir//MyFile.txt");
            Scanner scannerReader = new Scanner(file);
            List<String> strings = new ArrayList<String>();
            while (scannerReader.hasNextLine()) {
                String text1 = scannerReader.next();
                String text2 = scannerReader.next();
                if((nickname.equals(text1))) {
                    haveLogin=true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(haveLogin==true)
        {
            ServerLoader.sendPacket(ClietnHandler.getSocket(),"successful");
            try {
                Logging.read("Connect successful password: "+password +"  nickname: "+nickname);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("login succesful "+nickname+" " + password);
        }else {
            ServerLoader.sendPacket(ClietnHandler.getSocket(),"error");
            try {
                Logging.read("Connect unsuccessful: "+password +"  nickname: "+nickname);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {}
      //  ServerLoader.end();
    }
}
