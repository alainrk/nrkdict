/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nrkdictGUI;

/**
 *
 * @author narko
 */

import nrkdict.*;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;

/**
 *
 * @author narko
 */
public class GuiController {
    public static MainForm mainForm;
    public void start(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("swing.boldMetal", Boolean.FALSE);
        }
            catch(UnsupportedLookAndFeelException ex) {ex.printStackTrace();}
            catch (IllegalAccessException ex){ex.printStackTrace();}
            catch (InstantiationException ex){ex.printStackTrace();}
            catch (ClassNotFoundException ex){ex.printStackTrace();}

        try {
            createGui();
        } catch (Exception e){System.out.println(e.toString());}
    }
    
    private void createGui() throws Exception {
        mainForm = new MainForm();
        //throw new Exception();
    }
}