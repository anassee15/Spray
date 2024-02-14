package io.tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * class used in data exploration course
 */
public abstract class WriterCSV_A implements Runnable
{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

    public WriterCSV_A(String nameFile, String separator)
    {
        this.nameFile = nameFile;
        this.separator = separator;
    }

    public WriterCSV_A(String nameFile)
    {
        this(nameFile, CSVs.SEPARATOR);
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Abstract						*|
	\*------------------------------------------------------------------*/

    protected abstract String[] header();

    protected abstract List<String[]> lines();

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

    @Override
    public void run()
    {
        try
        {
            //System.out.println("[WriterCSV] : " + nameFile + " : try writing  csv file with separator [" + separator + "]");
            writeCSV();
            //System.out.println("[WriterCSV] : " + nameFile + " : writed");
        }
        catch (IOException e)
        {
            System.err.println("[WriterCSV] : " + nameFile + " : writing fail");
            e.printStackTrace();
        }
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

    private void writeCSV() throws IOException
    {
        FileWriter fw = new FileWriter(nameFile);
        BufferedWriter bw = new BufferedWriter(fw);

        writeHeader(bw);
        writeCore(bw);

        bw.close();
        fw.close();
    }

    private void writeHeader(BufferedWriter bw) throws IOException
    {
        writeLine(bw, header());
        bw.write(END_LINE);
    }

    private void writeCore(BufferedWriter bw) throws IOException
    {
        for(String[] line:lines())
        {
            writeLine(bw, line);

            bw.write(END_LINE);
        }
    }

	/*------------------------------*\
	|*				tools			*|
	\*------------------------------*/

    private void writeLine(BufferedWriter bw, String[] line) throws IOException
    {
        int m = line.length;

        int j = 0;
        for(String mot:line)
        {
            bw.write(mot);
            if (j < m - 1) // pas de separateur pour le dernier
            {
                bw.write(separator);
            }
            j++;
        }
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

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

    private static final String END_LINE = "\n";

}
