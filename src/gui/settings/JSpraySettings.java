package gui.settings;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tools.Settings;
import tools.b_enum.Couleur;
import tools.b_enum.Shapes;

public class JSpraySettings extends Box
{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

    public JSpraySettings()
    {
        super(BoxLayout.X_AXIS);

        geometry();
        control();
        appearance();
    }

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

    public Shapes getBoxShapes()
    {
        return (Shapes)this.jComboBoxSprayType.getSelectedItem();
    }

    public Couleur getColor()
    {
        return (Couleur)this.jComboBoxColor.getSelectedItem();
    }

    public int getNbDroplets()
    {
        return (int)this.jSpinnerDroplets.getValue();
    }

    public int getSliderArea()
    {
        return this.jSliderArea.getValue();
    }

    public int getSliderSize()
    {
        return this.jSliderSize.getValue();
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

    private void geometry()
    {
        this.jComboBoxColor = new JComboBox<Couleur>(Couleur.values());
        this.jComboBoxSprayType = new JComboBox<Shapes>(Settings.SPRAY_TYPE);

        SpinnerModel model = new SpinnerNumberModel(Settings.DROPLETS_INIT, Settings.DROPLETS_MIN, Settings.DROPLETS_MAX, Settings.DROPLETS_INTERVAL);
        this.jSpinnerDroplets = new JSpinner(model);

        this.jSliderSize = new JSlider(SwingConstants.HORIZONTAL, Settings.SIZE_MIN, Settings.SIZE_MAX, Settings.SIZE_INTERVAL);
        this.jSliderArea = new JSlider(SwingConstants.HORIZONTAL, Settings.AREA_MIN, Settings.AREA_MAX, Settings.AREA_INTERVAL);

        this.jLabelSliderSize = new JLabel(this.jSliderSize.getValue() + " px");
        this.jLabelSliderArea = new JLabel(this.jSliderArea.getValue() + " px");
        add(createHorizontalStrut(Settings.BUTTON_HGAP));

        add(new JLabel("Brushes"));
        add(createHorizontalStrut(Settings.BUTTON_HGAP));

        add(this.jComboBoxSprayType);
        add(createHorizontalStrut(Settings.BUTTON_HGAP));

        add(new JLabel("Area "));
        add(this.jSliderArea);
        add(this.jLabelSliderArea);
        add(createHorizontalStrut(Settings.BUTTON_HGAP));

        add(new JLabel("Color"));
        add(createHorizontalStrut(Settings.BUTTON_HGAP));

        add(this.jComboBoxColor);
        add(createHorizontalStrut(Settings.BUTTON_HGAP));

        add(new JLabel("Nb droplets"));
        add(createHorizontalStrut(Settings.BUTTON_HGAP));

        add(this.jSpinnerDroplets);
        add(createHorizontalStrut(Settings.BUTTON_HGAP));

        add(new JLabel("Size "));
        add(this.jSliderSize);
        add(this.jLabelSliderSize);
        add(createHorizontalStrut(Settings.BUTTON_HGAP));
    }

    private void control()
    {
        this.jSliderSize.addChangeListener(createChangeListenerSize());
        this.jSliderArea.addChangeListener(createChangeListenerArea());
    }

    private void appearance()
    {
        setBorder(BorderFactory.createTitledBorder("Spray settings"));
        jSliderSize.setPreferredSize(new Dimension(Settings.SLIDER_WIDTH, -1));
        jSliderSize.setValue(Settings.SIZE_MAX / 2);
        jSliderArea.setPreferredSize(new Dimension(Settings.SLIDER_WIDTH, -1));
        jSliderArea.setValue(Settings.AREA_MAX / 2);
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

    private ChangeListener createChangeListenerSize()
    {
        return new ChangeListener()
        {

            @Override
            public void stateChanged(ChangeEvent e)
            {
                jLabelSliderSize.setText(jSliderSize.getValue() + " px");
            }
        };
    }

    private ChangeListener createChangeListenerArea()
    {
        return new ChangeListener()
        {

            @Override
            public void stateChanged(ChangeEvent e)
            {
                jLabelSliderArea.setText(jSliderArea.getValue() + " px");
            }
        };
    }

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

    // Tools
    private JComboBox<Couleur> jComboBoxColor;
    private JComboBox<Shapes> jComboBoxSprayType;

    private JSpinner jSpinnerDroplets;

    private JSlider jSliderArea;
    private JSlider jSliderSize;

    private JLabel jLabelSliderArea;
    private JLabel jLabelSliderSize;
}

