package gui;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Miguel on 30-05-2015.
 */
class GuiOutputStream extends OutputStream
{
    private JTextArea m_textArea;

    public GuiOutputStream(JTextArea textArea)
    {
        m_textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException
    {
        // redirects data to the text area
        m_textArea.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        m_textArea.setCaretPosition(m_textArea.getDocument().getLength());
    }
}
