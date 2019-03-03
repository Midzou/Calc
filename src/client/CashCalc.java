package client;

import client.packet.PackerCalcEngine;
import client.packet.PackerCalcLog;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class CashCalc {


    ArrayList<String> cashOneLvl = new ArrayList<String>(8); //список для кеша первого уровня
    ArrayList<String> cashTwoLvl = new ArrayList<String>(10); //список для кеша первого уровня

    public String parsStr(String str) //убираем до равно
    {

        String strOperation = "";
        int i = 0;
        char c;
        for (; ; ) {
            c = str.charAt(i);
            if (c == '=') {
                break;
            }
            strOperation = strOperation + c;
            i++;
        }


        return strOperation;
    }

    public String parsResult(String str) //убираем до равно
    {

        String result = "";
        int i = str.length();
        char c;
        for (; ; ) {
            c = str.charAt(i-1);
            if (c == '=') {
                break;
            }

            result=c+result;
            i--;
        }

        return result;
    }

    public boolean searchElementInCashLvlOne(String str) //Есть ли str в кеше 1-ого уровня
    {
        boolean flag = false;
        for (int i = 0; i < cashOneLvl.size(); i++) {
            if (str.equals(parsStr(cashOneLvl.get(i)))) {
                flag=true;
            }
        }

        return flag;
    }
    public boolean searchElementInCashLvlTwo(String str) //Есть ли str в кеше 2-ого уровня
    {
        boolean flag = false;
        for (int i = 0; i < cashTwoLvl.size(); i++) {
            if (str.equals(parsStr(cashTwoLvl.get(i)))) {
                flag=true;
            }
        }

        return flag;
    }
    public int index(String str, ArrayList<String> list) //Есть ли str в кеше 2-ого уровня
    {

        for (int i = 0; i < list.size(); i++) {
            if (str.equals(parsStr(list.get(i)))) {
                return i;
            }
        }

        return 0;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String cash(int num, String strOp, int num1) throws IOException {

        String str = num + strOp + num1;
        System.out.println(str);


        String result = "";

//parsStr(cashOneLvl.get(i))


        if(searchElementInCashLvlOne(str))//ищем str в 1ом уровне
        {
            System.out.println("ищем str во 1ом уровне ");
            int index = index(str,cashOneLvl);
            cashOneLvl.add(0, cashOneLvl.get(index));//помещаем ответ в самое начало
            cashOneLvl.remove(index+1); //удаляем
            result=parsResult(cashOneLvl.get(0));
            ClientLoader.sendPacket(new PackerCalcLog(str+"="+result));
            System.out.println("cashOneLvl "+cashOneLvl);
            System.out.println("cashTwoLvl "+cashTwoLvl);
        }else if(searchElementInCashLvlTwo(str)) //ищем str в 2ом уровне
        {
            System.out.println("ищем str во 2ом уровне ");
            int index = index(str,cashTwoLvl);
            cashOneLvl.add(0,cashTwoLvl.get(index));
            cashTwoLvl.remove(index);
            cashTwoLvl.add(0,cashOneLvl.get(8));
            cashOneLvl.remove(8);
            result=parsResult(cashOneLvl.get(0));
            ClientLoader.sendPacket(new PackerCalcLog(str+"="+result));
            System.out.println("cashOneLvl "+cashOneLvl);
            System.out.println("cashTwoLvl "+cashTwoLvl);

        }else //если не нашли в кеше,добавляем
        {

            //отпарвляем запрос на сервер и считаем в answer ответ
            System.out.println("Не нашли ,обращаемся к серверу за результатом =((((((");
            ClientLoader.sendPacket(new PackerCalcEngine(str));
            try {

                DataInputStream dis = new DataInputStream(ClientLoader.getSocket().getInputStream()); //создаем поток для приема с сервера
                if (dis.available() <= 0) {
                    result = dis.readUTF();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                    }

                }
            } catch (IOException ex) {
            }

            if (cashOneLvl.size() >= 8)//если кеш 1ого уровня переполнен
            {
                //int index = cashOneLvl.indexOf(str); //получаем индекс
                cashTwoLvl.add(cashOneLvl.get(7)); //переносим во второй кеш
              //  System.out.println("TEST!!"+String.valueOf(cashOneLvl.indexOf(str)));
                cashOneLvl.remove(7); //удаляем последний кеш
                cashOneLvl.add(0, str+'='+result);//помещаем ответ в самое начало

            }else
            {
                cashOneLvl.add(0, str+'='+result);//помещаем ответ в самое начало
            }
            System.out.println("cashOneLvl "+cashOneLvl);
            System.out.println("cashTwoLvl "+cashTwoLvl);
        }

        return result;//вернули подсчитанный/найденный результат
    }
}



