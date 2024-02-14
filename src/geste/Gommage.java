package geste;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import tools.b_enum.Couleur;
import tools.b_enum.Shapes;

public class Gommage extends Geste
{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

    public Gommage(List<Point> listPoint, int size, Shapes shape)
    {
        super(Couleur.BLANC, size, shape);

        for(Point point:listPoint)
        {
            this.listPoint.add(point);
        }
    }

    public Gommage(List<Point> listPoint, Shapes shape)
    {
        this(listPoint, -1, shape); // set size equals -1 because size won't be use if this constructor is called
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

    @Override
    public void draw(Graphics2D g2d)
    {
        g2d.setColor(this.couleur.getColor());

        switch(this.shape)
        {
            case CIRCLE:
                for(Point p:this.listPoint)
                {
                    g2d.fill(new Ellipse2D.Double(p.getX(), p.getY(), this.size, this.size));
                }
                break;
            case LINE:
                Assertions.assertEquals(2, listPoint.size());
                g2d.fill(createThicknessLine(listPoint.get(0), listPoint.get(1), this.size));
                break;
            case POLYGON:
                // polygon is defined with 3 points or more
                Assertions.assertTrue(listPoint.size() >= 3);
                g2d.fill(createPolygone(this.listPoint));
                break;
            default:
                System.out.println("Shape not for this usage : " + this.shape);
                break;
        }
    }

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

    /**
     * create a line with tickness from 2 points
     * @param p1 point 1
     * @param p2 point 2
     * @param thickness thickness of line
     * @return line polygon
     */
    public static Polygon createThicknessLine(Point p1, Point p2, int thickness)
    {
        /*
         * calculate tickness of the line :
         * 1 : set a normal vector from a director vector
         * 2 : change norm of normal vector to be equal to thickness / 2;
         * 3 : translate each point by dx and dy.
         */

        double distance = thickness / 2;
        Point p1p2 = new Point(p2.x - p1.x, p2.y - p1.y);
        Point normalVector = new Point(-p1p2.y, p1p2.x);

        double k = distance / Math.sqrt(Math.pow(normalVector.x, 2) + Math.pow(normalVector.y, 2));
        double dx = k * normalVector.x;
        double dy = k * normalVector.y;

        int xValues[] = { (int)(p1.x - dx), (int)(p1.x + dx), (int)(p2.x + dx), (int)(p2.x - dx) };
        int yValues[] = { (int)(p1.y - dy), (int)(p1.y + dy), (int)(p2.y + dy), (int)(p2.y - dy) };

        return new Polygon(xValues, yValues, 4);
    }

    /**
     * create a polygone from list of point
     * @param listPoint list of point
     * @return polygon
     */
    public static Polygon createPolygone(List<Point> listPoint)
    {
        int nbPoint = listPoint.size();
        int[] xValues = new int[nbPoint];
        int[] yValues = new int[nbPoint];
        int i = 0;

        for(Point point:listPoint)
        {
            xValues[i] = point.x;
            yValues[i] = point.y;
            i++;
        }

        return new Polygon(xValues, yValues, nbPoint);
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
}
