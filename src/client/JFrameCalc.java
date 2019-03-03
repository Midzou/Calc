package client;

import javax.swing.*;

public class JFrameCalc extends JFrame
{
    public JFrameCalc()
    {
        setBounds(100, 100, 265, 290);
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add( new Calc());
        setVisible(true);
    }
}