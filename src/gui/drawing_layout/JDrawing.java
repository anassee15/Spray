package gui.drawing_layout;

import drawing.G2dDrawing;
import geste.Geste;
import geste.Gommage;
import geste.Sprayage;
import gui.settings.JRubberSettings;
import gui.settings.JSpraySettings;
import io.WriterDropletsCSV;
import tools.b_enum.Couleur;
import tools.b_enum.Shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class JDrawing extends JPanel
{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

    public JDrawing(JRadioButton jRadioButtonSpray, JRadioButton jRadioButtonRubber, JSpraySettings jSpraySettings, JRubberSettings jRubberSettings)
    {

        // Input
        {
            this.jRadioButtonSpray = jRadioButtonSpray;
            this.jRadioButtonRubber = jRadioButtonRubber;
            this.jSpraySettings = jSpraySettings;
            this.jRubberSettings = jRubberSettings;
        }

        // Tools
        {
            this.g2dDrawing = new G2dDrawing();
            this.listPoint = new ArrayList<Point>();
        }

        geometry();
        control();
        appearance();
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        AffineTransform backup = g2d.getTransform();
        this.g2dDrawing.draw(g2d, this.getSize().width, this.getSize().height);
        g2d.setTransform(backup);
    }

    public void clear()
    {
        this.g2dDrawing.clear();
        repaint();
    }

    public void exportToCSV(String filename)
    {
        WriterDropletsCSV writer = new WriterDropletsCSV(filename + ".csv", this.g2dDrawing.getListGeste());
        writer.run();
    }

    public void importFromCSV(List<Geste> listGeste)
    {
        this.g2dDrawing.addList(listGeste);
        repaint();
    }

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

    private void geometry()
    {
        //rien
    }

    private void control()
    {
        addMouseListener(createMouseListener());
        addMouseMotionListener(createMouseMotionListener());
    }

    private void appearance()
    {
        setBackground(Couleur.BLANC.getColor());
    }

    private MouseMotionListener createMouseMotionListener()
    {
        return new MouseMotionListener()
        {

            @Override
            public void mouseMoved(MouseEvent e)
            {
                int x = e.getX();
                int y = e.getY();

                if (jRadioButtonRubber.isSelected())
                {
                    switch(jRubberSettings.getRubberType())
                    {
                        case CIRCLE:
                            setToolTipText(null);

                            int radius = jRubberSettings.getSliderValue();
                            g2dDrawing.updateCircle(new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius));

                            break;
                        case LINE:
                            setToolTipText(null);

                            if (listPoint.size() >= 1)
                            {
                                int thickness = jRubberSettings.getSliderValue();
                                g2dDrawing.updatePolygon(Gommage.createThicknessLine(new Point(listPoint.get(0)), e.getPoint(), thickness));
                            }
                            break;
                        case POLYGON:
                            setToolTipText("double click to close your polygon");

                            if (listPoint.size() >= 1)
                            {
                                List<Point> listTemp = new ArrayList<Point>(listPoint);
                                listTemp.add(e.getPoint());
                                g2dDrawing.updatePolygon(Gommage.createPolygone(listTemp));
                            }
                            break;
                        default:
                            System.out.println("undefinied : " + jRubberSettings.getRubberType());
                            break;
                    }
                }
                else if (jRadioButtonSpray.isSelected())
                {
                    setToolTipText(null);
                    switch(jSpraySettings.getBoxShapes())
                    {
                        case RECTANGLE:
                            int h = jSpraySettings.getSliderArea();
                            int w = 2 * h;
                            g2dDrawing.updateRectangle(new Rectangle2D.Double(x - w / 2, y - h / 2, w, h));
                            break;

                        case CIRCLE:
                            int radius = jSpraySettings.getSliderArea();
                            g2dDrawing.updateCircle(new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius));
                            break;
                        default:
                            System.out.println("undefinied : " + jSpraySettings.getBoxShapes());
                            break;
                    }
                }

                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e)
            {
                drawing(e, false);
            }
        };
    }

    private MouseListener createMouseListener()
    {
        return new MouseAdapter()
        {

            @Override
            public void mousePressed(MouseEvent e)
            {
                drawing(e, true);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                g2dDrawing.disableAll();
                listPoint.clear();
                repaint();
            }
        };
    }

    private void drawing(MouseEvent e, boolean isClick)
    {
        if (jRadioButtonRubber.isSelected())
        {
            drawRubber(e, isClick);
        }
        else if (jRadioButtonSpray.isSelected() && isClick) // want click for sprayage
        {
            drawSpray(e);
        }
        repaint();
    }

    private void drawRubber(MouseEvent e, boolean isClick)
    {
        int x = e.getX();
        int y = e.getY();

        switch(jRubberSettings.getRubberType())
        {
            case CIRCLE:
                int radius = jRubberSettings.getSliderValue();
                Ellipse2D.Double circle = new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
                g2dDrawing.updateCircle(circle);

                List<Point> circleCenter = new ArrayList<Point>();
                circleCenter.add(new Point((int)circle.getX(), (int)circle.getY()));

                g2dDrawing.add(new Gommage(circleCenter, (int)circle.getHeight(), Shapes.CIRCLE));
                g2dDrawing.remove(circle); // gommage num�rique
                break;

            case LINE:
                if (isClick)
                {
                    listPoint.add(e.getPoint());

                    if (listPoint.size() == 2)
                    {
                        g2dDrawing.add(new Gommage(listPoint, jRubberSettings.getSliderValue(), Shapes.LINE));
                        g2dDrawing.remove(Gommage.createThicknessLine(listPoint.get(0), listPoint.get(1), jRubberSettings.getSliderValue())); // gommage num�rique
                        listPoint.clear();
                        g2dDrawing.disablePolygon();
                    }
                }
                break;

            case POLYGON:
                if (isClick)
                {
                    if (e.getClickCount() == 2 && !e.isConsumed())
                    {
                        e.consume();

                        // polygone defined with 3 points minimum
                        if (listPoint.size() >= 3)
                        {
                            g2dDrawing.add(new Gommage(listPoint, Shapes.POLYGON));
                            g2dDrawing.remove(Gommage.createPolygone(listPoint)); // gommage num�rique
                        }
                        listPoint.clear();
                        g2dDrawing.disablePolygon();
                    }
                    else
                    {
                        listPoint.add(e.getPoint());
                    }
                }
                break;

            default:
                System.out.println("undefinied : " + jRubberSettings.getRubberType());
                break;
        }
    }

    private void drawSpray(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        List<Point> listDroplets = new ArrayList<Point>();

        switch(jSpraySettings.getBoxShapes())
        {
            case RECTANGLE:
                int h = jSpraySettings.getSliderArea();
                int w = 2 * h;
                Rectangle2D.Double rectangle = new Rectangle2D.Double(x - w / 2, y - h / 2, w, h);

                listDroplets = Sprayage.fillRectangle(rectangle, jSpraySettings.getNbDroplets());

                g2dDrawing.updateRectangle(rectangle);
                g2dDrawing.add(new Sprayage(jSpraySettings.getColor(), jSpraySettings.getSliderSize(), listDroplets));
                break;

            case CIRCLE:
                int radius = jSpraySettings.getSliderArea();
                Ellipse2D.Double circle = new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);

                listDroplets = Sprayage.fillCircle(circle, jSpraySettings.getNbDroplets());

                g2dDrawing.updateCircle(circle);
                g2dDrawing.add(new Sprayage(jSpraySettings.getColor(), jSpraySettings.getSliderSize(), listDroplets));
                break;

            default:
                System.out.println("undefinied : " + jSpraySettings.getBoxShapes());
                break;
        }
    }

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

    // Input
    private JRadioButton jRadioButtonSpray;
    private JRadioButton jRadioButtonRubber;
    private JSpraySettings jSpraySettings;
    private JRubberSettings jRubberSettings;

    // Tools
    private G2dDrawing g2dDrawing;
    private List<Point> listPoint;
}
