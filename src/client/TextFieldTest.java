package client;

import client.packet.PackerAuthorize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;

public class TextFieldTest extends JFrame
{
    JTextField smallField, bigField;

    public TextFieldTest()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        smallField = new JTextField(12);
        smallField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(TextFieldTest.this,
                        "Ваше слово: " + smallField.getText());
            }
        });
        JPasswordField password = new JPasswordField(12);
       password.setEchoChar('*');

        JPanel contents = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contents.add(smallField);
        contents.add(password  );
        setContentPane(contents);
        // Определяем размер окна и выводим его на экран



        setSize(400,100); //Размер окна
       // setResizable(false); //Запрен а изменение
        setLocation(800,200); //Где будет окно на экране
        setTitle("authorization"); //Названеи окношка
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        JPanel jpBotton = new JPanel(new FlowLayout(FlowLayout.LEFT));

       add(jpBotton,BorderLayout.SOUTH); //Кнопки в таблицу


        JButton jbStart = new JButton("Login"); //Кнопка новой игры
        JButton jbExite = new JButton("Register"); //Кнопка закрытия окна


        jpBotton.add(jbStart);
        jpBotton.add(jbExite); //Добавленеи на панель jpBotton


        jbStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientLoader.sendPacket(new PackerAuthorize(smallField.getText(),password.getText()));





                try {

                    DataInputStream dis = new DataInputStream(ClientLoader.getSocket().getInputStream()); //создаем поток для приема с сервера
                    if (dis.available() <= 0) {
                        if(dis.readUTF().equals("error"))
                        {
                            JOptionPane.showMessageDialog(null, "Error!");

                            System.out.println("error");
                        }else
                        {



                            new JFrameCalc();
                            setVisible(false);
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
        });

        jbExite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               Register log = new Register();
                setVisible(false);
            }
        });

        setVisible(true);
    }

}