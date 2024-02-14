package io.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * class used in data exploration course
 */
public class LoaderCSV implements Runnable
{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

    public LoaderCSV(String nameFile, String separator)
    {
        //Assertions.assertTrue(new File(nameFile).isFile());
        this.nameFile = nameFile;
        this.separator = separator;
    }

    public LoaderCSV(String nameFile)
    {
        this(nameFile, CSVs.SEPARATOR);
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

    @Override
    public void run()
    {
        try
        {
            //System.out.println("[LoaderCSV] : " + nameFile + " : try loading this csv file with separator [" + separator + "]");
            read();
            //System.out.println("[LoaderCSV] : " + nameFile + " : loaded");
        }
        catch (IOException e)
        {
            System.err.println("[LoaderCSV] : " + nameFile + " : loading fail");
            e.printStackTrace();
        }
    }

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

    public String[] getHeader()
    {
        return tabMotHeader;
    }

    public List<String[]> getLines()
    {
        return listLine;
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

    private void read() throws IOException
    {
        FileReader fr = new FileReader(nameFile);
        BufferedReader br = new BufferedReader(fr);

        this.tabMotHeader = readHeader(br);
        this.listLine = readCore(br);

        br.close();
        fr.close();
    }

    private String[] readHeader(BufferedReader br) throws IOException
    {
        String line = br.readLine();

        return toArray(line);
    }

    private List<String[]> readCore(BufferedReader br) throws IOException
    {
        List<String[]> listLine = new LinkedList<String[]>();
        String line;
        while((line = br.readLine()) != null)
        {
            //System.out.println(line);

            String[] tabMot = toArray(line);
            listLine.add(tabMot);
        }
        return listLine;
    }

    private String[] toArray(String line)
    {
        String linePurge = removeMultiSpace(line);

        return linePurge.split(separator);
    }

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

    private static String removeMultiSpace(String line)
    {
        return line.replaceAll("\\s+", " ");
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

    // Inputs
    private String nameFile;
    private String separator;

    // Outputs
    private String[] tabMotHeader;
    private List<String[]> listLine;

}

