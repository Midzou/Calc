package server.packet;

import server.ClietnHandler;
import server.Logging;
import server.ServerLoader;

import java.io.*;
import java.util.*;

public class PackerCalcEngine extends OPacket {
    private String str;
    Map<Integer, String> equation = new HashMap<Integer, String>();

    public PackerCalcEngine(){}


    public PackerCalcEngine(String str) {
        str = this.str;
    }

    public static boolean isInteger(Character s) {
        try {
            Integer.parseInt(String.valueOf(s));
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
    public void parser(String str) //pars to the mark '='
    {
        String operation = "";
        String num1 = "";
        String num2 = "";
        char c;
        for (int i=0;i<str.length() ;i++ ) {
            c = str.charAt(i);
            if (isInteger(c) ) {
                if(operation.length()==0) {
                    num1 = num1 + c;
                }else{
                    num2=num2+c;
                }
            }else
            {
                operation=operation+c;
            }
        }
        equation.put(1, operation);
        equation.put(2, num1);
        equation.put(3, num2);
    }

    @Override
    public short getId() {
        return 3;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {}

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
        parser(str);
        int res = 0;
        if(equation.get(1).equals("-"))
        {
            res=Integer.parseInt(equation.get(2))-Integer.parseInt(equation.get(3));
        }
        if(equation.get(1).equals("+"))
        {
            res=Integer.parseInt(equation.get(2))+Integer.parseInt(equation.get(3));
        }
        if(equation.get(1).equals("*"))
        {
            res=Integer.parseInt(equation.get(2))*Integer.parseInt(equation.get(3));
        }
        if(equation.get(1).equals("/"))
        {
            res=Integer.parseInt(equation.get(2))/Integer.parseInt(equation.get(3));
        }
        try {
            Logging.read("Calc operation :" +str+"="+res);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ServerLoader.sendPacket(ClietnHandler.getSocket(), String.valueOf(res));
    }
}

