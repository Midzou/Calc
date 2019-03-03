package server.packet;

import server.ClietnHandler;
import server.Logging;
import server.ServerLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PacketRegistration extends OPacket {
    private String nickname;
    private String password;
    private String repitPassword;

    public PacketRegistration() {}

    public PacketRegistration(String nickname,String password,String repitPassword)
    {
        nickname =this.nickname;
        password = this.password;
        repitPassword = this.repitPassword;
    }
    @Override
    public short getId() {
        return 2;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
    }

    @Override
    public void read(Map<Integer, String> text) throws IOException {
        try {
            nickname = (String) text.get(2);   //considered a nickname
            password = (String) text.get(3);   //considered a password
            repitPassword = (String) text.get(4);  //considered a repit password
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handel(){
        if(password.equals(repitPassword)) {
            File File = new File("C://SomeDir//MyFile.txt");
            boolean haveLogin = false;
            try {
                File file = new File("C://SomeDir//MyFile.txt");
                Scanner scannerReader = new Scanner(file);
                List<String> strings = new ArrayList<String>();
                while (scannerReader.hasNextLine()) {
                    String text1 = scannerReader.next();
                    String text2 = scannerReader.next();
                    if ((nickname.equals(text1))) { //если нашли совпадения
                        haveLogin = true;
                        ServerLoader.sendPacket(ClietnHandler.getSocket(),"error");
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (haveLogin == false) {
                try {
                    Logging.read("Registration successful: "+password +"  nickname: "+nickname);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ServerLoader.sendPacket(ClietnHandler.getSocket(),"successful");
                FileWriter writer = null;
                try {
                    writer = new FileWriter("C://SomeDir//MyFile.txt", true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedWriter bufferWriter = new BufferedWriter(writer);
                try {
                    bufferWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    bufferWriter.write(nickname +" " + password);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bufferWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            try {
                Logging.read("Registration unsuccessful");
            } catch (IOException e) {
                e.printStackTrace();
            }
            ServerLoader.sendPacket(ClietnHandler.getSocket(),"error");
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {}
        //ServerLoader.end();
    }
}
