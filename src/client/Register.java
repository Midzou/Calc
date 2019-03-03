package client;

import client.packet.PackerRegistration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Register
{
    public static JFrame frame = new JFrame("BoxLayoutTest");
    public static void createUI(Container container) {



        JButton button = new JButton("Registration"); //static что бы закрыть
        JButton calc = new JButton("Calc_test"); //static что бы закрыть
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        calc.setAlignmentY(Component.CENTER_ALIGNMENT);



        JTextField smallField,password,repitPassword;
        smallField = new JTextField(12);
        password = new JTextField(12);
        repitPassword = new JTextField(12);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(new JLabel("Logim:"));
        container.add(smallField);

        container.add(new JLabel("Password :"));
        container.add(password);
        container.add(new JLabel("Repit password :"));
        container.add(repitPassword);
        container.add(button);
        container.add(calc);





        calc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new JFrameCalc();
                }
                }
        );

                button.addActionListener(new ActionListener() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void actionPerformed(ActionEvent e) {

                if(e.getSource()==button){
                    Map<Integer, String> states = new HashMap<Integer, String>();
                    states.put(1, smallField.getText());
                    states.put(2, password.getText());
                    states.put(3, repitPassword.getText());

                    System.out.println(ClientLoader.getSocket());
                    ClientLoader.sendPacket(new PackerRegistration(states.get(1),states.get(2),states.get(3))); //по идеи можно перенести
                    //sendPacket(new PackerRegistration(states.get(1),states.get(2),states.get(3));


                        try {

                            DataInputStream dis = new DataInputStream(ClientLoader.getSocket().getInputStream()); //создаем поток для приема с сервера
                            if (dis.available() <= 0) {
                                if(dis.readUTF().equals("error"))
                                {
                                    JOptionPane.showMessageDialog(null, "Error!");

                                    System.out.println("error");
                                }else
                                {
                                   // JOptionPane.showMessageDialog(null, "successful");
                                    frame.dispose();
                                    TextFieldTest p=new TextFieldTest();


                                    System.out.println("successful");
                                }

                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException ex) {
                                }

                            }
                        } catch (IOException ex) {
                        }


                }
            }
        }



        );


    }







    public Register()
    {
        // Создание окна

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Определение интерфейса окна
        createUI(frame.getContentPane());



        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Открытие окна
                frame.setLocation(800,200); //Где будет окно на экране
                frame.setTitle("authorization"); //Названеи окношка
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}