package geste;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tools.b_enum.Couleur;
import tools.b_enum.Shapes;
public abstract class Geste implements Iterable<Point>
{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

    public Geste(Couleur couleur, int size, Shapes shape)
    {
        // Inputs
        {
            this.couleur = couleur;
            this.size = size;
            this.shape = shape;
        }

        // Tool
        {
            this.listPoint = new ArrayList<Point>();
        }
    }

    public Geste(Couleur couleur, int size)
    {
        this(couleur, size, Shapes.CIRCLE);
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Abstract						*|
	\*------------------------------------------------------------------*/

    /**
     * draw the geste
     * @param g2d graphics context
     */
    public abstract void draw(Graphics2D g2d);

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

    @Override
    public Iterator<Point> iterator()
    {
        return this.listPoint.iterator();
    }

    /**
     * remove all points of listPoint who are contain in shape
     * @param shape shape
     */
    public void removeShape(Shape shape)
    {
        List<Point> listCopy = new ArrayList<Point>(this.listPoint);

        for(Point point:listCopy)
        {
            // to only remove sprayage and keep gommage
            if (this.couleur != Couleur.BLANC)
            {
                if (shape.contains(point))
                {
                    this.listPoint.remove(point);
                }
            }
        }
    }

    /**
     * convert Geste to List<String[]> to export on csv files
     * @return list contains lines of csv file
     */
    public List<String[]> convertToTabString()
    {
        List<String[]> tabStringList = new ArrayList<String[]>();

        // don't export white Geste because points are removed by "numerical" way
        if (this.couleur != Couleur.BLANC)
        {
            for(Point p:this.listPoint)
            {
                String[] feature = { "" + p.x, "" + p.y, "" + this.size, this.couleur.toString() };
                tabStringList.add(feature);
            }
        }

        return tabStringList;
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

    // Inputs
    protected Couleur couleur;
    protected Shapes shape;
    protected int size;

    //Tool
    protected List<Point> listPoint;
}