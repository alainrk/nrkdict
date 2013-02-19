/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nrkdictGUI;

import java.awt.Frame;

/**
 *
 * @author narko
 */
public class MainWindow extends Frame {
    public MainWindow (int width, int height) {
        super("NrkDict");
        setLocation(100,100);
        setSize(width,height);
        setVisible(true);
    }

}
