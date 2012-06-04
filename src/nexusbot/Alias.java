/*
 * :mode=java:tabSize=4:indentSize=4:noTabs=true:
 * :folding=indent:collapseFolds=0:wrap=none:maxLineLen=120:
 *
 * $Source: /var/lib/cvsd/cvsrepo/invasion/nw/nexusbot/src/nexusbot/Alias.java,v $
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
 * This class holds and manages the aliases for NexusBot.  These allow someone to note the alt name that goes with their nick.
 */
public class Alias
{
    /**
     * DOCUMENT ME!
     */
    private String REPO;

    /**
     * DOCUMENT ME!
     */
    private Map<String, Properties> aliases = new HashMap<String, Properties>(  );

    /**
     * Creates a new Alias object.
     */
    private Alias(  )
    {
    }


    /**
     * Creates a new Alias object.
     *
     * @param filename DOCUMENT ME!
     */
    public Alias( String filename )
    {
        REPO = filename;
    }

    /**
     * Adds a new alias to the list
     *
     * @param who DOCUMENT ME!
     * @param message DOCUMENT ME!
     */
    public void putAlias( String channel, String nick, String alt )
    {
        if ( aliases.containsKey( channel ) )
        {
            Properties m = aliases.get( channel );
            m.setProperty( nick, alt );
        }
        else
        {
            Properties m = new Properties(  );
            m.setProperty( nick, alt );
            aliases.put( channel, m );
        }

        save(  );
    }


    public String getAlias( String channel, String nick )
    {
        Properties channelAliases = aliases.get( channel );
        return channelAliases.getProperty( nick );
    }


    public Properties getPropsForChannel( String channel )
    {
        return aliases.get( channel );
    }

    /**
     * returns a list of messages for the user named
     */
    public void showAlias( Info info, String who )
    {
        Properties channelAliases = aliases.get( info.getChannel() );

        /*
         *  do the edit distance thing...check the nick's first
         */
        double score = 0.0;

        for (Enumeration e = channelAliases.propertyNames(  ) ; e.hasMoreElements() ;)
        {
            String name = (String) e.nextElement();
            score = ( 1.0 * EditDistance.getEditDistance( who.toLowerCase(  ), name.toLowerCase(  ) ) ) / who.length(  );

            if ( score < 0.2 )
            {
                info.sendMessage( who + "'s alt is " + channelAliases.getProperty( name ) );
            }
            if( score == 0.0 )
            {
                return;
            }
        }

        /*
         *  do the edit distance thing...now check the alt names to see if they are looking for the nick
         */
        Collection alts = channelAliases.values(  );

        for ( Iterator i = alts.iterator(); i.hasNext(); )
        {
            String name = (String) i.next();
            score = ( 1.0 * EditDistance.getEditDistance( who.toLowerCase(  ), name.toLowerCase(  ) ) ) / who.length(  );

            if ( score < 0.20 )
            {
                for (Enumeration e = channelAliases.propertyNames(  ) ; e.hasMoreElements() ;)
                {
                    String nick = (String) e.nextElement();
                    if( channelAliases.getProperty( nick ).equals( name ) )
                    {
                        info.sendMessage( name + " is known here as " + nick );
                    }
                }
                if( score==0.0 )
                {
                    return;
                }
            }
        }

    }


    /**
     * DOCUMENT ME!
     *
     * @param who DOCUMENT ME!
     */
    public void removeAlias( String channel, String nick )
    {
        aliases.get(channel).remove( nick );
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
            objStream.writeObject( aliases );
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
            aliases = ( HashMap<String, Properties> ) oIn.readObject(  );

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
     *  saves the aliases for a channel to a file as a Java Properties file and returns the file pointer
     */
    public void exportAliases(String channel, Info info)
    {
        Properties channelAliases = aliases.get( channel );
        for (Enumeration e = channelAliases.propertyNames(  ) ; e.hasMoreElements() ;)
        {
            String name = (String) e.nextElement();
            info.sendMessage( info.getSender(), name + "'s alt is " + channelAliases.getProperty( name ) );
        }
    }

}

