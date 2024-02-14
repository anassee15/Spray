package tools.b_enum;

import java.awt.Color;

public enum Couleur
{

    NOIR(Color.BLACK), //
    ROUGE(Color.RED), //
    BLEU(Color.BLUE), //
    VERT(Color.GREEN), //
    JAUNE(Color.YELLOW), //
    ROSE(Color.PINK), //
    ORANGE(Color.ORANGE), //
    CYAN(Color.CYAN), //
    BLANC(Color.WHITE);

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

    private Couleur(Color color)
    {
        this.color = color;
    }

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

    public Color getColor()
    {
        return this.color;
    }

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

    private Color color;
}
