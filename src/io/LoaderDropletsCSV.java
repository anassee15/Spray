package io;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import geste.Geste;
import geste.Sprayage;
import io.tools.CSVs;
import io.tools.LoaderCSV;
import tools.b_enum.Couleur;

public class LoaderDropletsCSV implements Runnable {

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

    public LoaderDropletsCSV(String nameFile, String separator) {
        this.nameFile = nameFile;
        this.separator = separator;
    }

    public LoaderDropletsCSV(String nameFile) {
        this(nameFile, CSVs.SEPARATOR);
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

    @Override
    public void run() {
        LoaderCSV loaderCSV = new LoaderCSV(nameFile, separator);
        loaderCSV.run();

        List<String[]> lines = loaderCSV.getLines();

        this.listGeste = toListGeste(lines);
    }

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

    public List<Geste> getListGeste() {
        return this.listGeste;
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

    /**
     * convert a list of string to a list of geste
     *
     * @param listString list to convert
     * @return listGeste
     */
    protected List<Geste> toListGeste(List<String[]> listString) {
        List<Geste> listGeste = new ArrayList<Geste>();
        List<Point> listPoint = new ArrayList<Point>();

        Iterator<String[]> it = listString.iterator();
        String[] feature = it.next();

        Couleur lastCouleur = convertFromStringToCouleur(feature[3]);
        int x = Integer.valueOf(feature[0]);
        int y = Integer.valueOf(feature[1]);
        int lastSize = Integer.valueOf(feature[2]);

        listPoint.add(new Point(x, y));

        while (it.hasNext()) {
            feature = it.next();

            Couleur actualCouleur = convertFromStringToCouleur(feature[3]);
            int actualSize = Integer.valueOf(feature[2]);
            x = Integer.valueOf(feature[0]);
            y = Integer.valueOf(feature[1]);

            //System.out.println(actualCouleur + " " + actualSize + " " + x + " " + y);

            if (actualCouleur != lastCouleur || actualSize != lastSize) {
                listGeste.add(new Sprayage(lastCouleur, lastSize, new ArrayList<Point>(listPoint)));
                listPoint.clear();
                lastCouleur = actualCouleur;
                lastSize = actualSize;
            }

            listPoint.add(new Point(x, y));
        }

        listGeste.add(new Sprayage(lastCouleur, lastSize, listPoint));
        System.out.println("export finish!!");

        return listGeste;
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

    /**
     * convert a String to Couleur Enum
     *
     * @param string string to convert
     * @return Couleur
     */
    private Couleur convertFromStringToCouleur(String string) {
        Couleur couleur;

        switch (string) {
            case "BLANC":
                couleur = Couleur.BLANC;
                break;
            case "BLEU":
                couleur = Couleur.BLEU;
                break;
            case "JAUNE":
                couleur = Couleur.JAUNE;
                break;
            case "ORANGE":
                couleur = Couleur.ORANGE;
                break;
            case "ROSE":
                couleur = Couleur.ROSE;
                break;
            case "ROUGE":
                couleur = Couleur.ROUGE;
                break;
            case "VERT":
                couleur = Couleur.VERT;
                break;
            case "NOIR":
                couleur = Couleur.NOIR;
                break;
            case "CYAN":
                couleur = Couleur.CYAN;
                break;
            default:
                System.out.println("undefinied color : " + string);
                couleur = Couleur.NOIR;
                break;
        }
        return couleur;
    }

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

    // Inputs
    private String nameFile;
    private String separator;

    // Outputs
    private List<Geste> listGeste;
}
