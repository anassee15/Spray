package tools;

import java.awt.Dimension;

import javax.swing.JComponent;

public class Sizes
{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

    public static void setH(int h, JComponent jComponent)
    {
        jComponent.setPreferredSize(new Dimension(jComponent.getPreferredSize().width, h));
        jComponent.setMaximumSize(new Dimension(jComponent.getMaximumSize().width, h));
        jComponent.setMinimumSize(new Dimension(jComponent.getMinimumSize().width, h));
    }

    public static void setW(int w, JComponent jComponent)
    {
        jComponent.setPreferredSize(new Dimension(w, jComponent.getPreferredSize().height));
        jComponent.setMaximumSize(new Dimension(w, jComponent.getMaximumSize().height));
        jComponent.setMinimumSize(new Dimension(w, jComponent.getMinimumSize().height));
    }

    public static void set(int w, int h, JComponent... jComponent)
    {
        for(JComponent component:jComponent)
        {
            component.setPreferredSize(new Dimension(w, h));
            component.setMaximumSize(new Dimension(w, h));
            component.setMinimumSize(new Dimension(w, h));
        }
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

}