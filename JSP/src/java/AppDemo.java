/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.applet.Applet;
import static java.awt.AWTEventMulticaster.add;
import static java.awt.AWTEventMulticaster.add;
import static java.awt.AWTEventMulticaster.add;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author umash
 */
public class AppDemo extends Applet implements MouseMotionListener {
    
    String Str="";
    int x,y;

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    @Override
    public void init() {
        setBackground(Color.BLACK);
        setForeground(Color.BLUE);
        addMouseMotionListener(this);
        // TODO start asynchronous download of heavy resources
    }
    @Override
    public void paint(Graphics g){
     g.drawString(Str, x, y);
    }
    
    @Override
    public void mouseMoved(MouseEvent e){
        Str="Mouse Moved";
        x=e.getX();
        y=e.getY();
        repaint();
    }
    @Override
    public void mouseDragged(MouseEvent e){
        Str="Mouse dragged";
        x=e.getX();
        y=e.getY();
        repaint();
    }

    // TODO overwrite start(), stop() and destroy() methods
}
