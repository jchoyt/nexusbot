/*
 * :mode=java:tabSize=4:indentSize=4:noTabs=true:
 * :folding=indent:collapseFolds=0:wrap=none:maxLineLen=120:
 *
 * $Source: /var/lib/cvsd/cvsrepo/invasion/nw/nexusbot/src/nexusbot/Messages.java,v $
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
 * This class holds and manages the messages for NexusBot.  These are messages someone leaves for other people to
 * be delivered when they next sign on.
 */
public class Messages
{
    /**
     * DOCUMENT ME!
     */
    private String REPO;

    /**
     * DOCUMENT ME!
     */
    private Map<String, ArrayList> messages = new HashMap<String, ArrayList>(  );

    /**
     * Creates a new Messages object.
     */
    private Messages(  )
    {
    }


    /**
     * Creates a new Messages object.
     *
     * @param filename DOCUMENT ME!
     */
    public Messages( String filename )
    {
        REPO = filename;
    }

    /**
     * DOCUMENT ME!
     *
     * @param who DOCUMENT ME!
     * @param message DOCUMENT ME!
     */
    public void putMessage( String who, String message )
    {
        who = who.toLowerCase();
        if ( messages.containsKey( who ) )
        {
            List m = messages.get( who );
            m.add( message );
        }
        else
        {
            ArrayList m = new ArrayList(  );
            m.add( message );
            messages.put( who, m );
        }

        save(  );
    }


    /**
     * returns a list of messages for the user named
     */
    public List getMessages( String who )
    {
        who = who.toLowerCase();
        List ret = messages.get( who );
        messages.remove( who );

        /*
         *  do the edit distance thing
         */
        Set<String> recipients = messages.keySet(  );
        double score = 0.0;
        List<String> toRemove = new ArrayList<String>(  );

        for ( String name : recipients )
        {
            score = ( 1.0 * EditDistance.getEditDistance( who.toLowerCase(  ), name.toLowerCase(  ) ) ) / who.length(  );

            if ( score < 0.1 )
            {
                if ( ret == null )
                {
                    ret = messages.get( name );
                }
                else
                {
                    ret.addAll( messages.get( name ) );
                }
                toRemove.add( name );
            }
        }

        for ( String name : toRemove )
        {
            messages.remove( name );
        }

        save(  );

        return ret;
    }


    /**
     * DOCUMENT ME!
     *
     * @param who DOCUMENT ME!
     */
    public void removeMessages( String who )
    {
        who = who.toLowerCase();
        messages.remove( who );
    }


    /**
     * DOCUMENT ME!
     */
    public void save(  )
    {
        try
        {
            // setup a stream to a physical file on the filesystem
            FileOutputStream outStream = new FileOutputStream( REPO );

            // attach a stream capable of writing objects to the stream that is connected to the file
            ObjectOutputStream objStream = new ObjectOutputStream( outStream );
            objStream.writeObject( messages );
            objStream.flush(  );
            objStream.close(  );
        }
        catch ( IOException e )
        {
            System.err.println( "Things not going as planned." );
            e.printStackTrace(  );
        } // catch
    }


    /**
     * DOCUMENT ME!
     */
    public void load(  )
    {
        File f = new File( REPO );

        if ( !f.exists(  ) )
        {
            return;
        }

        FileInputStream fIn = null;
        ObjectInputStream oIn = null;

        try
        {
            fIn = new FileInputStream( REPO );
            oIn = new ObjectInputStream( fIn );
            //de-serializing object
            messages = ( HashMap<String, ArrayList> ) oIn.readObject(  );
            // System.out.println("Deserialized " + emp.fName + " " + emp.lName + " from NewEmployee.ser ");
        }
        catch ( IOException e )
        {
            e.printStackTrace(  );
        }
        catch ( ClassNotFoundException e )
        {
            e.printStackTrace(  );
        }
        finally
        {
            try
            {
                oIn.close(  );
                fIn.close(  );
            }
            catch ( IOException e1 )
            {
                e1.printStackTrace(  );
            }
        }
    }


    /**
     * DOCUMENT ME!
     *
     * @param who DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getMessageCount( String who )
    {
        who = who.toLowerCase();
        List ret = messages.get( who );

        if ( ret == null )
        {
            return 0;
        }

        return ret.size(  );
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public List reviewAllMessages(  )
    {
        List ret = new ArrayList(  );

        for ( String o : messages.keySet(  ) )
        {
            ret.add( "Messages for " + o );
            ret.addAll( messages.get( o ) );
        }

        return ret;
    }
}

