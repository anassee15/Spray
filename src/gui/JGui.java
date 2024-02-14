package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import gui.drawing_layout.JDrawing;
import gui.settings.JRubberSettings;
import gui.settings.JSpraySettings;
import image.ImagesShop;
import io.LoaderDropletsCSV;
import tools.Settings;
import tools.Sizes;

public class JGui extends JPanel
{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

    public JGui()
    {
        geometry();
        control();
        appearance();
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

    private void geometry()
    {
        this.jToolBar = new JToolBar();

        this.buttonFromCSV = new JButton(ImagesShop.IMPORT_CSV);
        this.buttonToCSV = new JButton(ImagesShop.EXPORT_CSV);

        this.radioButtonSpray = new JRadioButton("", true);
        this.jLabelSpray = new JLabel(ImagesShop.SPRAY);
        this.jSpraySettings = new JSpraySettings();

        this.radioButtonRubber = new JRadioButton();
        this.jLabelRubber = new JLabel(ImagesShop.RUBBER);
        this.jRubberSettings = new JRubberSettings();

        this.buttonClear = new JButton(ImagesShop.CLEAR);
        this.jDrawing = new JDrawing(radioButtonSpray, radioButtonRubber, jSpraySettings, jRubberSettings);

        jToolBar.add(this.buttonFromCSV);
        jToolBar.add(this.buttonToCSV);
        jToolBar.addSeparator();
        jToolBar.add(this.radioButtonSpray);
        jToolBar.add(this.jLabelSpray);
        jToolBar.addSeparator();
        jToolBar.add(this.jSpraySettings);
        jToolBar.addSeparator();
        jToolBar.add(this.radioButtonRubber);
        jToolBar.add(this.jLabelRubber);
        jToolBar.addSeparator();
        jToolBar.add(this.jRubberSettings);
        jToolBar.addSeparator();
        jToolBar.add(this.buttonClear);

        setLayout(new BorderLayout());
        add(this.jToolBar, BorderLayout.NORTH);
        add(this.jDrawing, BorderLayout.CENTER);
    }

    private void control()
    {
        buttonGroup = new ButtonGroup();
        buttonGroup.add(this.buttonFromCSV);
        buttonGroup.add(this.buttonToCSV);
        buttonGroup.add(this.radioButtonSpray);
        buttonGroup.add(this.radioButtonRubber);

        this.buttonFromCSV.addMouseListener(createMouseListenerFromCSV());
        this.buttonToCSV.addMouseListener(createMouseListenerToCSV());
        this.buttonClear.addMouseListener(createMouseListenerClear());
    }

    private void appearance()
    {
        Sizes.set(Settings.ICON_DX, Settings.ICON_DY, buttonFromCSV, buttonToCSV, buttonClear, jLabelRubber, jLabelSpray);

        removeButtonBackground(buttonFromCSV, buttonToCSV, buttonClear);

        buttonFromCSV.setToolTipText("Import from csv");
        buttonToCSV.setToolTipText("Export to csv");
        radioButtonSpray.setToolTipText("Spray");
        radioButtonRubber.setToolTipText("Rubber");
        buttonClear.setToolTipText("clear");
    }

    private MouseListener createMouseListenerFromCSV()
    {
        return new MouseAdapter()
        {

            @Override
            public void mouseClicked(MouseEvent e)
            {
                JFileChooser choose = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                choose.setDialogTitle("choose CSV file to import : ");
                choose.setAcceptAllFileFilterUsed(false);

                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv"); // allow only to get csv file
                choose.addChoosableFileFilter(filter);

                int res = choose.showOpenDialog(null);

                if (res == JFileChooser.APPROVE_OPTION)
                {
                    String fileName = choose.getSelectedFile().getPath();
                    LoaderDropletsCSV loader = new LoaderDropletsCSV(fileName);
                    loader.run();
                    jDrawing.importFromCSV(loader.getListGeste());
                }
            }
        };
    }

    private MouseListener createMouseListenerToCSV()
    {
        return new MouseAdapter()
        {

            @Override
            public void mouseClicked(MouseEvent e)
            {
                jDrawing.exportToCSV(JOptionPane.showInputDialog("put your file name", "TP_sprayage" + System.currentTimeMillis()));
            }
        };

    }

    private MouseListener createMouseListenerClear()
    {
        return new MouseAdapter()
        {

            @Override
            public void mouseClicked(MouseEvent e)
            {
                jDrawing.clear();
            }
        };
    }

    private void removeButtonBackground(JButton... jButton)
    {
        for(JButton button:jButton)
        {
            //button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
        }
    }

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

    // Tools
    private JLabel jLabelSpray;
    private JLabel jLabelRubber;

    private JButton buttonFromCSV;
    private JButton buttonToCSV;
    private JButton buttonClear;
    private JRadioButton radioButtonSpray;
    private JRadioButton radioButtonRubber;
    private ButtonGroup buttonGroup;

    private JToolBar jToolBar;

    private JSpraySettings jSpraySettings;
    private JRubberSettings jRubberSettings;
    private JDrawing jDrawing;
}

