package gui;

import image.ImagesShop;
import tools.a_decorateur.JFrameBaseLine;

public class UseJGui {
    public static void main(String[] args)
    {
        main();
    }

    public static void main()
    {
        new JFrameBaseLine(new JGui(), ImagesShop.ICON_APP);
    }
}
