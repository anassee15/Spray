package gui.settings;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tools.Settings;
import tools.b_enum.Shapes;

public class JRubberSettings extends Box
{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

    public JRubberSettings()
    {
        super(BoxLayout.X_AXIS);

        geometry();
        control();
        appearance();
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

    public Shapes getRubberType()
    {
        return (Shapes)this.jComboBoxRubberType.getSelectedItem();
    }

    public int getSliderValue()
    {
        return this.jSlider.getValue();
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

    private void geometry()
    {
        this.jComboBoxRubberType = new JComboBox<Shapes>(Settings.RUBBER_TYPE);

        this.jLabel = new JLabel("size ");
        this.jSlider = new JSlider(SwingConstants.HORIZONTAL, Settings.AREA_MIN, Settings.AREA_MAX, Settings.AREA_INTERVAL);
        this.jLabelSlider = new JLabel(this.jSlider.getValue() + " px");

        add(createHorizontalStrut(Settings.BUTTON_HGAP));
        add(new JLabel("Type "));
        add(createHorizontalStrut(Settings.BUTTON_HGAP));
        add(this.jComboBoxRubberType);

        add(createHorizontalStrut(Settings.BUTTON_HGAP));
        add(this.jLabel);
        add(createHorizontalStrut(Settings.BUTTON_HGAP));
        add(this.jSlider);
        add(this.jLabelSlider);

        add(createHorizontalStrut(Settings.BUTTON_HGAP));

    }

    private void control()
    {
        this.jSlider.addChangeListener(createChangeListener());
    }

    private void appearance()
    {
        setBorder(BorderFactory.createTitledBorder("Rubber settings"));
        jSlider.setPreferredSize(new Dimension(Settings.SLIDER_WIDTH, -1));
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

    private ChangeListener createChangeListener()
    {
        return new ChangeListener()
        {

            @Override
            public void stateChanged(ChangeEvent e)
            {
                jLabelSlider.setText(jSlider.getValue() + " px");
            }
        };
    }

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

    // Tools
    private JComboBox<Shapes> jComboBoxRubberType;

    private JLabel jLabel;
    private JSlider jSlider;
    private JLabel jLabelSlider;
}
