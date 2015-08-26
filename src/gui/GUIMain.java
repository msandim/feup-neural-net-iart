package gui;

import javax.swing.*;

/**
 * Created by Miguel on 29-05-2015.
 */
public class GUIMain
{
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException
    {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        GuiForm form = new GuiForm();
    }
}
