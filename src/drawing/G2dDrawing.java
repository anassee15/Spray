package drawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import geste.Geste;
public class G2dDrawing {

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

    public G2dDrawing() {
        this.listGeste = new ArrayList<Geste>();
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

    public void draw(Graphics2D g2d, int w, int h) {
        for (Geste geste : this.listGeste) {
            geste.draw(g2d);
        }

        g2d.setColor(Color.BLACK);

        // to draw a shape who is following cursor movement
        if (circle != null) {
            g2d.draw(this.circle);
        } else if (rectangle != null) {
            g2d.draw(this.rectangle);
        } else if (polygon != null) {
            g2d.drawPolygon(this.polygon);
        }
    }

    public List<Geste> getListGeste() {
        return this.listGeste;
    }

    /**
     * add geste to listGeste
     *
     * @param geste geste to add
     */
    public void add(Geste geste) {
        this.listGeste.add(geste);
    }

    /**
     * add a listGeste to listGeste
     *
     * @param listGeste list to add
     */
    public void addList(List<Geste> listGeste) {
        this.listGeste.addAll(listGeste);
    }

    /**
     * clear listGeste
     */
    public void clear() {
        this.listGeste.clear();
    }

    /**
     * remove all Points who's contain in shape of listGeste
     *
     * @param shape shape
     */
    public void remove(Shape shape) {
        for (Geste geste : this.listGeste) {
            geste.removeShape(shape);
        }
    }

	/*------------------------------------------------------------------*\
	|*				   Cursors Animation Shapes							*|
	\*------------------------------------------------------------------*/

    /**
     * all this function are used for update / disable shape who's following cursor*
     */

    public void updateCircle(Ellipse2D.Double circle) {
        this.rectangle = null;
        this.polygon = null;
        this.circle = circle;
    }

    public void updateRectangle(Rectangle2D.Double rectangle) {
        this.circle = null;
        this.polygon = null;
        this.rectangle = rectangle;
    }

    public void updatePolygon(Polygon polygon) {
        this.circle = null;
        this.rectangle = null;
        this.polygon = polygon;
    }

    public void disableCircle() {
        this.circle = null;
    }

    public void disableRectangle() {
        this.rectangle = null;
    }

    public void disablePolygon() {
        this.polygon = null;
    }

    public void disableAll() {
        this.rectangle = null;
        this.polygon = null;
        this.circle = null;
    }

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

    // Tools
    private List<Geste> listGeste;
    private Ellipse2D.Double circle = null;
    private Rectangle2D.Double rectangle = null;
    private Polygon polygon = null;
}
