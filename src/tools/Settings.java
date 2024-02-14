package tools;

import tools.b_enum.Shapes;
public class Settings
{

    // JToolBar
    public static final int BUTTON_HGAP = 15;
    public static final int ICON_DY = 40;
    public static final int ICON_DX = 40;

    public static final int SLIDER_WIDTH = 140;

    public static final int SIZE_MIN = 1;
    public static final int SIZE_MAX = 30;
    public static final int SIZE_INTERVAL = 2;

    public static final int AREA_MIN = 20;
    public static final int AREA_MAX = 300;
    public static final int AREA_INTERVAL = 20;

    public static final int DROPLETS_MIN = 1;
    public static final int DROPLETS_MAX = 1500;
    public static final int DROPLETS_INTERVAL = 20;
    public static final int DROPLETS_INIT = 100;

    public static final Shapes[] SPRAY_TYPE = { Shapes.CIRCLE, Shapes.RECTANGLE };
    public static final Shapes[] RUBBER_TYPE = { Shapes.CIRCLE, Shapes.LINE, Shapes.POLYGON };
}
