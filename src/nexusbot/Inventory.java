/*
 * :mode=java:tabSize=4:indentSize=4:noTabs=true:
 * :folding=indent:collapseFolds=0:wrap=none:maxLineLen=120:
 *
 * $Source: /var/lib/cvsd/cvsrepo/invasion/nw/nexusbot/src/nexusbot/Inventory.java,v $
 * Copyright (C) 2007 Jeffrey Hoyt
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package nexusbot;

import java.io.*;

import java.util.*;


/**
 * This class holds and manages a list of stuff for NexusBot.
 */
public class Inventory
{
    /**
     * DOCUMENT ME!
     */
    private String REPO;

    /**
     * DOCUMENT ME!
     */
    private List<String> stuff = new ArrayList<String>(  );

    /**
     * Creates a new Inventory object.
     */
    private Inventory(  )
    {
    }


    /**
     * Creates a new Inventory object.
     *
     * @param filename DOCUMENT ME!
     */
    public Inventory( String filename )
    {
        REPO = filename;
    }

    /**
     * DOCUMENT ME!
     *
     * @param who DOCUMENT ME!
     * @param message DOCUMENT ME!
     */
    public void addItem( String item )
    {
        stuff.add(item);
        Utils.save( stuff, REPO );
    }


    /**
     * returns a list of messages for the user named
     */
    public String getItem(  )
    {
        double randomValue = Math.random(  );
        int index = (int) ( randomValue * stuff.size() );
        String ret = stuff.get(index);
        stuff.remove(index);
        Utils.save( stuff, REPO );
        return ret;
    }


    /**
     * DOCUMENT ME!
     */
    public void load(  )
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(REPO));
            String thisLine;
            while ((thisLine = br.readLine()) != null)
            {
                stuff.add(thisLine);
            }
        }
        catch ( IOException e1 )
        {
            e1.printStackTrace(  );
        }
    }


    public int getInventoryCount()
    {
        return stuff.size();
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    // public List reviewAllInventory(  )
    // {
    //     List ret = new ArrayList(  );

    //     for ( String o : messages.keySet(  ) )
    //     {
    //         ret.add( "Inventory for " + o );
    //         ret.addAll( messages.get( o ) );
    //     }

    //     return ret;
    // }
}

