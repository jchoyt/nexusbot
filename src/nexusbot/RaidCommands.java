/*
 * :mode=java:tabSize=4:indentSize=4:noTabs=true:
 * :folding=indent:collapseFolds=0:wrap=none:maxLineLen=120:
 *
 * $Source: /var/lib/cvsd/cvsrepo/invasion/nw/nexusbot/src/nexusbot/RaidCommands.java,v $
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

import java.text.*;

import org.jibble.pircbot.*;

import java.io.*;
import java.util.*;
import java.net.*;
import java.text.*;


/**
 * This provides functionality that is more closely tied to channel management or dealing with RL objects (like time)
 *
 * @author jchoyt
  */
public class RaidCommands implements BotCommandHandler
{

    private Properties raidTarget = new Properties();
    private Properties raidLocation = new Properties();
    private Properties raidPath = new Properties();
    private String COLOR = Colors.RED;
    private int OFFSET = -1;
    private String REPO = "raids.ser";
    private String tickSetter = "";
    private long tickTime = 0;
    private Timer timer = new Timer(true);
    private Map<String, GregorianCalendar> raids = new HashMap<String, GregorianCalendar>();

    public RaidCommands()
    {
        loadRaids();
    }

    public boolean handleMessage( Info info )
    {
        String message = info.getMessage();
        if ( message.equalsIgnoreCase( "!go" ) )
        {
            go(info);
        }
        else if ( message.equalsIgnoreCase( "!ward" ) )
        {
            ward(info);
        }
        else if ( message.startsWith( "!aoe" ) )
        {
            aoe(info);
        }
        else if ( message.equalsIgnoreCase( "!pile" ) )
        {
            pile(info);
        }
        else if ( message.startsWith( "!setPath" ) )
        {
            setPath(info);
        }
        else if ( message.startsWith( "!setTarget" ) )
        {
            setTarget(info);
        }
        else if ( message.equalsIgnoreCase( "!path" ) )
        {
            path(info);
        }
        else if ( message.equalsIgnoreCase( "!target" ) )
        {
            target(info);
        }
        else if ( message.equalsIgnoreCase( "!tanks" ) )
        {
            tanks(info);
        }
        else if ( message.equalsIgnoreCase( "!clearAll" ) )
        {
            clearStats(info);
        }
        else if ( message.equalsIgnoreCase( "!tick" ) )
        {
            tick(info);
        }
        else if ( message.equalsIgnoreCase( "!setTick" ) )
        {
            setTick(info);
        }
        else if ( message.startsWith( "!setTick " ) )
        {
            String time = message.substring( 9 );
            setTick(info, time);
        }
        else if ( message.equalsIgnoreCase( "!whoSetTick" ) )
        {
            info.sendMessage( "The tick was set by " + tickSetter + " at " + new Date(tickTime) );
        }
        else if (message.equalsIgnoreCase( "!tickAlarm"))
        {
            tickAlarm(info);
        }
        else if ( message.startsWith( "!setnextraid " ) )
        {
            setNextRaid(info);
        }
        else if ( message.startsWith( "!nextraid" ) )
        {
            nextRaid(info);
        }
        else
            return false;
        return true;
    }

    private void clearStats( Info info )
    {
        raidTarget.remove( info.getChannel() );
        raidLocation.remove( info.getChannel() );
        raidPath.remove( info.getChannel() );
        info.sendMessage( "Raid status cleared.");
    }

    private void go( Info info )
    {
        String msg = COLOR + Colors.BOLD + "MOVE OUT!";
        if( raidTarget.get(info.getChannel()) != null )
        {
            msg += Colors.NORMAL + COLOR + " Target is " + raidTarget.get( info.getChannel() ) + " " + raidLocation.get( info.getChannel() );
        }
        info.sendMessage( msg );
    }

    private void ward( Info info )
    {
        String msg = COLOR + Colors.BOLD + "Start bashing.  Call out when the ward is down.";
        info.sendMessage( msg );
    }

    private void tanks( Info info )
    {
        String msg = COLOR + Colors.BOLD + "Tanks in!  Call out when pets are cleared!";
        info.sendMessage( msg );
    }

    private void aoe( Info info )
    {
        String msg = COLOR + Colors.BOLD;
        if( info.getMessage().length() > 4 )
        {
            msg += info.getMessage().substring(5) + " ";
        }
        msg += "AOE in!  Call out periodic status of how many hostiles are left.";
        info.sendMessage( msg );
    }

    private void pile( Info info )
    {
        String msg = COLOR + Colors.BOLD + "Everyone in!  Let's clear them out!";
        info.sendMessage( msg );
    }

    private void setPath( Info info )
    {
        String path = info.getMessage().substring(8);
        raidPath.put( info.getChannel(), path );
        info.sendMessage( "The path to the target is set." );
    }

    private void path( Info info )
    {
        if( raidPath.containsKey( info.getChannel() ) )
        {
            info.sendMessage( COLOR + raidPath.get(info.getChannel()));
        }
        else
            info.sendMessage( "No path to the target is set.");
    }

    private void setTarget( Info info )
    {
        String factionInfo = info.getMessage().substring(11);
        String[] splitInfo = factionInfo.split("/");
        if( splitInfo.length != 2 )
        {
            info.sendMessage( "usage for !setTarget is !setTarget <faction>/<location>");
        }
        else
        {
            raidTarget.put(info.getChannel(), splitInfo[0]);
            raidLocation.put(info.getChannel(), splitInfo[1]);
            target(info);
        }
    }

    private void target( Info info )
    {
        if( raidTarget.containsKey( info.getChannel() ) )
        {
            info.sendMessage( COLOR + "The raid target is " + raidTarget.get(info.getChannel()) + " located at " + raidLocation.get(info.getChannel()));
        }
        else
            info.sendMessage( "No target is set.");
    }

    private void tick( Info info )
    {
        if( Utils.shouldDefer( info ) ) return;
        //else
        if( OFFSET == -1 )
        {
            info.sendMessage( "Tick not sychronized yet. Wait for the next tick and execute /msg " + info.getBot().getName() +
                " !setTickor find out how long until the next tick and execute /msg " + info.getBot().getName() +
                " !setTick <mins>" );
            return;
        }
        int timeRemaining = 15 - ((15 + Utils.getRealMinutes(info) - OFFSET) % 15 );
        long daysSinceSet = (System.currentTimeMillis() - tickTime) / (1000*60*60*24);
        info.sendMessage( timeRemaining + " minutes until the next tick (last set " + daysSinceSet + " days ago)" );
    }

    private void setTick( Info info )
    {
        tickSetter = info.getSender();
        tickTime = System.currentTimeMillis();
        OFFSET = Utils.getRealMinutes(info) % 15;
        info.sendMessage( "Tick set to " + OFFSET + " minutes off." );

    }


    private void setTick( Info info, String time )
    {
        int addon = 0;
        try
        {
            addon = Integer.parseInt(time);
        }
        catch ( NumberFormatException e )
        {
            info.sendMessage( "Format exception.  Command should be !setTick # where # is the number of minutes till the next tick." );
        }
        tickSetter = info.getSender();
        tickTime = System.currentTimeMillis();
        OFFSET = ( Utils.getRealMinutes(info) + addon ) % 15;
        info.sendMessage( "Tick set to " + OFFSET + " minutes off." );

    }


    private void tickAlarm( Info info )
    {
        if( OFFSET == -1 )
        {
            info.sendMessage( "Tick not sychronized yet. Wait for the next tick and execute /msg " + info.getBot().getName() +
                " !setTick or find out how long until the next tick and execute /msg " + info.getBot().getName() +
                " !setTick <mins>" );
            return;
        }
        int timeRemaining = 15 - ((15 + Utils.getRealMinutes(info) - OFFSET) % 15 );
        if( timeRemaining < 2 )
        {
            info.sendMessage( Colors.BLUE + Colors.BOLD + "The tick is in " + timeRemaining + " minutes." );
        }
        else
        {
            PingTask ptask = new PingTask( info, "the tick is in one minute." );
            timer.schedule(ptask, ( int )( ( timeRemaining - 1 ) * 60 * 1000 ) );
            info.sendMessage( "I'll alert you.");
        }
    }


    /**
     * Sets the time of the next scheduled raid
     */
    private void setNextRaid( Info info )
    {
        String raidTime = info.getMessage().substring(13);
        raidTime = substituteTimeZone( raidTime );
        try
        {
            SimpleDateFormat parser = new SimpleDateFormat( "yyyy-MM-dd HH:mm z" );
            Date date = parser.parse( raidTime );
            TimeZone z = parser.getTimeZone();
            GregorianCalendar cal = new GregorianCalendar(z);
            cal.setTime(date);
            raids.put( info.getChannel(), cal );
            saveRaids();
            info.sendMessage( "Raid time set." );
        }
        catch (ParseException e)
        {
            info.sendMessage( "Date format was bad.  Date format is like \"2008-11-26 21:45 EST\".  You entered " + raidTime );
        }
    }

    /**
     * Helper function for dealing with custom time zones.  I blame corky.
     * @param
     * @return
     *
     */
    public String substituteTimeZone( String raidTime )
    {
        return raidTime.replaceAll( "gametime", "EST" ).replaceAll( "GT", "EST" ).replaceAll( "GAMETIME", "EST" );
    }

    /**
     * Gives the time of the next scheduled raid
     */
    private final SimpleDateFormat formatter = new SimpleDateFormat( "EEE, d MMM HH:mm z" );
    private void nextRaid( Info info )
    {
        GregorianCalendar nextRaid = raids.get(info.getChannel());
        GregorianCalendar now = Utils.getRealDate(info);
        int diffMins = (int)((nextRaid.getTimeInMillis() - now.getTimeInMillis()) / 1000 / 60 );
        if( nextRaid.compareTo(now) < 0 )
        {
            info.sendMessage( "There is no future raid set." );
            return;
        }
        String tz = info.getMessage().substring(9).trim();
        tz = substituteTimeZone( tz );
        TimeZone timeZone ;
        if( tz.length() == 0 )
            timeZone = tZF("GMT");
        else
            timeZone = tZF( tz );
        formatter.setTimeZone( timeZone );

        String ret = "The next raid is scheduled for " + formatter.format( nextRaid.getTime());
        ret = ret + getTimeDifference(diffMins);
        info.sendMessage( ret );
    }


    /**
     * DOCUMENT ME!
     */
    public void saveRaids(  )
    {
        try
        {
            // setup a stream to a physical file on the filesystem
            FileOutputStream outStream = new FileOutputStream( REPO );

            // attach a stream capable of writing objects to the stream that is connected to the file
            ObjectOutputStream objStream = new ObjectOutputStream( outStream );
            objStream.writeObject( raids );
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
    public void loadRaids(  )
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
            raids = ( HashMap<String, GregorianCalendar> ) oIn.readObject(  );
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
     *  Handles private messages.  These are usually administrative type tasks that only the bot owner should be able to execute.
     */
    public boolean handlePrivateMessage(Info info)
    {
        return false;
    }

    public void handleJoin( Info info ){}

    public void handleNickChange( Info info ){}

    /**
     *  This code borrowed from Piesquared on irc.nexuswar.com
     */
    private TimeZone tZF(String search)
    {
    	if (search.equals(""))
    		return TimeZone.getTimeZone("GMT");
    	search = search.toLowerCase();
    	search = search.replace("+", "\\+");
    	search = search.replace("*", "\\*");
    	search = search.replace("?", "\\?");
    	String[] zones = TimeZone.getAvailableIDs();
    	for(int i=0; i<zones.length; i++)
    		if (zones[i].toLowerCase().equals(search))
    			return TimeZone.getTimeZone(zones[i]);
    	for(int i=0; i<zones.length; i++)
    		if (zones[i].toLowerCase().matches(".*" + search + ".*"))
    			return TimeZone.getTimeZone(zones[i]);
    	return TimeZone.getTimeZone("GMT");
    }


    /**
     * returns in the form " X hours and Y minutes from now/ago" or " RIGHT NOW!"
     * it returns nothing if the difference is > 1 day
     */
    private String getTimeDifference(int mins)
    {
        if(Math.abs(mins) > 60 *24)
        {
            return "";
        }
        boolean neg = mins < 0;
        mins = Math.abs(mins);
        if( mins < 5 )
        {
            return " ****THAT'S RIGHT NOW!****";
        }
        String ret = " (";
        int hrs = mins / 60;
        mins = mins % 60;
        if( hrs > 0 )
        {
            ret = ret + hrs + " hours and ";
        }
        ret = ret + mins + " minutes ";
        if( neg )
        {
            ret = ret + "ago)";
        }
        else ret = ret + "from now)";
        return ret;
    }
}
