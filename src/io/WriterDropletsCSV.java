package io;

import geste.Geste;
import io.tools.CSVs;
import io.tools.WriterCSV_A;

import java.util.ArrayList;
import java.util.List;

public class WriterDropletsCSV extends WriterCSV_A
{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

    public WriterDropletsCSV(String nameFile, List<Geste> listGeste)
    {
        super(nameFile, CSVs.SEPARATOR);
        this.listGeste = listGeste;
    }

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

    @Override
    protected String[] header()
    {
        String[] features = { "x", "y", "size", "color" };
        return features;
    }

    @Override
    protected List<String[]> lines()
    {
        List<String[]> list = new ArrayList<String[]>(listGeste.size());

        for(Geste geste:listGeste)
        {
            list.addAll(geste.convertToTabString());
        }

        return list;
    }

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

    // Input
    private List<Geste> listGeste;
}
