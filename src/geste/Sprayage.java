package geste;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import tools.b_enum.Couleur;

public class Sprayage extends Geste
{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

    public Sprayage(Couleur couleur, int dropletsRadius, List<Point> listPoint)
    {
        super(couleur, dropletsRadius);
        this.listPoint = listPoint;
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

    @Override
    public void draw(Graphics2D g2d)
    {
        g2d.setColor(this.couleur.getColor());

        for(Point point:listPoint)
        {
            g2d.fill(new Ellipse2D.Double(point.getX(), point.getY(), this.size, this.size));
        }
    }

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

    /**
     * generate a random points in a circle
     * @param circle circle
     * @param nbDroplets number of generate point
     * @return list of generates random points
     */
    public static List<Point> fillCircle(Ellipse2D.Double circle, int nbDroplets)
    {
        double x;
        double y;

        List<Point> listDroplets = new ArrayList<Point>();

        for(int i = 0; i < nbDroplets; i++)
        {
            do
            {
                //source : https://dev.to/seanpgallivan/solution-generate-random-point-in-a-circle-ldh
                double ang = Math.random() * 2 * Math.PI;
                double hyp = Math.sqrt(Math.random()) * circle.getWidth();
                double adj = Math.cos(ang) * hyp;
                double opp = Math.sin(ang) * hyp;

                x = circle.getCenterX() + adj;
                y = circle.getCenterY() + opp;

            } while(!circle.contains(x, y));

            listDroplets.add(new Point((int)x, (int)y));
        }

        return listDroplets;
    }

    /**
     * generate random points in a rectangle
     * @param rectangle rectangle
     * @param nbDroplets number of generate point
     * @return list of generates random points
     */
    public static List<Point> fillRectangle(Rectangle2D.Double rectangle, int nbDroplets)
    {
        double x;
        double y;
        List<Point> listDroplets = new ArrayList<Point>();

        for(int i = 0; i < nbDroplets; i++)
        {
            do
            {
                x = rectangle.getMinX() + (Math.random() * rectangle.getMaxX());
                y = rectangle.getMinY() + (Math.random() * rectangle.getMaxY());

            } while(!rectangle.contains(x, y));

            listDroplets.add(new Point((int)x, (int)y));
        }

        return listDroplets;
    }
}