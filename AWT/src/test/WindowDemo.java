package test;

import java.awt.*;

public class WindowDemo {
    public static void main(String[] args) {
        Frame frame=new Frame("this is first window");
        Panel p1 =new Panel();
        p1.add(new TextField("this is a test TXT"));
        p1.add(new Button("this is a test button"));
        frame.add(p1);
        frame.setSize(500,500);
        frame.setLocation(300,300);
        frame.setVisible(true);
    }
}
