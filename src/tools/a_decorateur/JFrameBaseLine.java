package tools.a_decorateur;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class JFrameBaseLine extends JFrame {

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

    public JFrameBaseLine(JComponent jComponent, ImageIcon imageIcon)
    {
        this.jComponent = jComponent;
        geometry();
        control();
        appearance(imageIcon);
    }

    public JFrameBaseLine(JComponent jComponent)
    {
        this(jComponent, null);
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

    private void geometry()
    {
        add(this.jComponent);
    }

    private void control()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void appearance(ImageIcon imageIcon)
    {
        if (imageIcon != null)
        {
            setIconImage(imageIcon.getImage());
        }

        setExtendedState(Frame.MAXIMIZED_BOTH); // full screen directly
        setMinimumSize(new Dimension(1200, 720));
        setLocationRelativeTo(null); // frame centrer
        setVisible(true); // last!
    }

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

    // Inputs
    private JComponent jComponent;
}