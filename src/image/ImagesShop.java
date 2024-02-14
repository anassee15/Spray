package image;

import javax.swing.ImageIcon;
import tools.ImageLoader;

public class ImagesShop {
    private static final String PATH_IN_JAR = "icon/";

	/*------------------------------------------------------------------*\
	|*		 Version Synchrone (bloquant)								*|
	\*------------------------------------------------------------------*/

    public static final ImageIcon EXPORT_CSV = ImageLoader.loadSynchroneJar(PATH_IN_JAR + "export_csv.png");
    public static final ImageIcon IMPORT_CSV = ImageLoader.loadSynchroneJar(PATH_IN_JAR + "import_csv.png");
    public static final ImageIcon SPRAY = ImageLoader.loadSynchroneJar(PATH_IN_JAR + "spray.png");
    public static final ImageIcon RUBBER = ImageLoader.loadSynchroneJar(PATH_IN_JAR + "rubber.png");
    public static final ImageIcon CLEAR = ImageLoader.loadSynchroneJar(PATH_IN_JAR + "clear.png");
    public static final ImageIcon ICON_APP = ImageLoader.loadSynchroneJar(PATH_IN_JAR + "icon_app.png");
}