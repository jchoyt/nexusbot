/*
 * :mode=java:tabSize=4:indentSize=4:noTabs=true:
 * :folding=indent:collapseFolds=0:wrap=none:maxLineLen=120:
 *
 * $Source: /var/lib/cvsd/cvsrepo/invasion/nw/nexusbot/src/nexusbot/ChannelCommands.java,v $
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

import org.jibble.pircbot.*;

import java.io.*;

import java.net.*;
import java.text.*;
import java.util.*;


/**
 * This provides functionality that is more closely tied to channel management or dealing with RL objects (like time)
 *
 * @author jchoyt
  */
public class ChannelCommands implements BotCommandHandler
{


    /**
     * NexusBot saves messages to be delivered the next time someone logs in - this is the central mechanism for doing this.
     */
    private Messages messages = new Messages( "messages.ser" );

    /**
     * NexusBot saves nick <-> alt mappings on a per channel basis
     */
    private Alias aliases = new Alias( "aliases.ser" );

    private Timer timer = new Timer(true);

    public List<String> channels = new ArrayList<String>();

    private Map<String, History> histories = new HashMap<String, History>();

    private SimpleDateFormat timeOnly = new SimpleDateFormat( "H:mm" );

    private Map<String, Date> lastSeen = new HashMap<String, Date>();

    private Map<String, String> lastSaid = new HashMap<String, String>();

    private Map<String, String> lastChannel = new HashMap<String, String>();

    private Properties nickTimeZones = new Properties();

    private String TIMEZONES = "timeZones.props";

    private String TIMEZONE_COMMENTS = "This file contains the default timezones for a given nick";

    public ChannelCommands()
    {
        messages.load(  );
        aliases.load();
        try
        {
            nickTimeZones.load(new FileReader( TIMEZONES ));
        } catch (Exception e) {}
    }

    public boolean handleMessage(Info info)
    {
        lastSeen.put(info.getSender(), new Date());
        lastSaid.put(info.getSender(), info.getMessage());
        lastChannel.put(info.getSender(), info.getChannel());
        //store the message in the moving history
        String message = info.getMessage();
        String historyMsg = info.getSender() + "(" + timeOnly.format( new Date() ) + "): " + message;

        String channel = info.getChannel();
        if( channel != null )
        {
            histories.get( info.getChannel().toLowerCase() ).add(historyMsg);
            System.out.println( "added " + historyMsg + " to " + info.getChannel() );
        }

        //check for commands to process
        if ( message.equalsIgnoreCase( "!ping" ) )
        {
            sendNames( info );
        }
        else if ( message.matches( "!ping .+") )
        {
            sendNames(info);
            String msg = Colors.BLUE + Colors.BOLD + message.substring( 6 );
            info.sendMessage( msg );
        }
        if ( message.equalsIgnoreCase( "!raid" ) )
        {
            sendNames( info );
            String msg = Colors.RED + Colors.BOLD + info.getSender() + " announces we are being raided!  Prepare to repel boarders!";
            info.sendMessage( msg );
        }
        else if ( message.equalsIgnoreCase( "!weather" ) )
        {
            weather(info);
        }
        else if ( message.startsWith( "!timer " ) )
        {
            message = message.substring( 7 );  //trim off "!timer "
            int timeBreak = message.indexOf( ' ' );
            String time = message.substring(0, timeBreak);
            String toDeliver = message.substring( timeBreak );
            float duration;
            try
            {
                duration = Float.parseFloat( time );
            }
            catch(NumberFormatException e)
            {
                info.sendMessage(info.getSender() + ": " + time + " is not a number, try again.");
                return false;
            }
            EggTimerTask task = new EggTimerTask(info, info.getSender() + ": " + toDeliver);
            timer.schedule(task, ( int )( duration * 60 * 1000 ) );
            info.sendMessage("I'll remind you, " + info.getSender() + ".");
        }
        else if (message.equalsIgnoreCase("!gmt"))
        {
            info.sendMessage( Utils.getRealTime(info) );
        }
        else if ( message.equalsIgnoreCase( "!quiet" ) || message.equalsIgnoreCase( "!quite" ))
        {
            quiet(info);
        }
        else if ( message.equalsIgnoreCase( "!talk" ) )
        {
            talk(info);
        }
        else if ( message.startsWith( "!voice" ) )
        {
            voice(info);
        }
        else if ( message.startsWith( "!novoice" ) )
        {
            novoice(info);
        }
        else if (message.startsWith( "!alias" ) )
        {
            registerAlias( info );
        }
        else if (message.startsWith( "!unalias" ) )
        {
            clearAlias( info );
        }
        else if (message.startsWith( "!whois" ) )
        {
            reportAlias( info );
        }
        else if (message.startsWith( "!flash" ) )
        {
            List<String> stooges = Arrays.asList( "BeerWench" );
            if( Utils.isPresent(info, stooges) && Math.random() < 0.6)
            {
                info.sendAction( "covers " + Utils.getRandomUser( info ) + "'s eyes" );
            }
        }
        else if (message.startsWith( "!beer" ) )
        {
            List<String> stooges = Arrays.asList( "BeerWench" );
            if( Utils.isPresent(info, stooges) && Math.random() < 0.1 )
            {
                info.sendAction( "follows behind BeerWench with coasters fastidiously cleaning up spilt beer and peanut shells" );
            }
        }
        else if ( message.startsWith( "!history" ) )
        {
            int count;
            String countString = message.substring( 8 ).trim();  //trim off "!timer "
            if(countString.equals(""))
            {
                count = 10;
            }
            else
            {

                try
                {
                    count = Integer.parseInt( countString );
                }
                catch(NumberFormatException e)
                {
                    count = 10;
                }
            }
            count++;  //truncating the !history message
            History hist = histories.get(info.getChannel().toLowerCase());
            if( hist == null )
            {
                return true;
            }
            if( hist.size() < count )
            {
                count = hist.size();
            }
            for(int i = hist.size() - count; i < hist.size()-1; i++)
            {
                info.sendMessage(info.getSender(), String.valueOf(hist.get(i)) );
            }

        }
        else if( message.startsWith( "!whereis " ) )
        {
            String who =  message.substring( 9 ).trim();
            whereIs( info, who );
        }
        else if( message.startsWith( "!setTimezone " ) )
        {
            String rest =  message.substring( 13 ).trim();
            setTimezone( info, rest );
        }
        else if ( message.equalsIgnoreCase( "!nukefromorbit" ) && info.getBot().isOwner(info.getSender()) )
        {
            User[] users = info.getBot().getUsers( info.getChannel() );
            NexusBot bot = info.getBot();
            for(int i = 0; i < users.length; i++)
            {
                if( !users[i].getNick().equals(bot.getName()) )
                {
                    bot.kick( info.getChannel(), users[i].getNick());
                }
            }
        }
        else
            return false;
        return true;
    }

    /**
     *  Handles private messages.  These are usually administrative type tasks that only the bot owner should be able to execute.
     */
    public boolean handlePrivateMessage(Info info)
    {

        if( !NexusBot.isOwner(info.getSender() ) )
        {
            return false;
        }
        String message = info.getMessage();
        if( message.startsWith( "!join " ) )
        {
            String login = message.substring( 6 ).trim();
            join( login, info.getBot() );
        }
        else if( message.startsWith( "!part " ) )
        {
            String channel = message.substring( 6 );
            if( channel.charAt(0) != '#' )
            {
                channel = "#" + channel;
            }
            channels.remove( channel );
            info.getBot().partChannel( channel, "I do my bidding." );
        }
        else if( message.equals( "!shutdown" ) )
        {
            info.getBot().quitServer( info.getBot().getNick() + " is shutting down." );
            System.exit(0);
        }
        else if( message.startsWith( "!identify "))
        {
             String passwd = message.substring( 10 );
             info.getBot().identify( passwd );
        }
        else if( message.equals("!help") )
        {
            info.sendMessage( info.getSender(), "All normal channel commands work here, plus the following: !reviewall, !shutdown, !join <channel>, !part <channel>, !dm <nick>, !identify <password>" );
        }
        else if ( message.equalsIgnoreCase( "!diagnostics" ) )
        {
            diagnostics( info );
        }
        else if ( message.startsWith( "!aliases "))
        {
             String channel = message.substring( 9 );
             aliases.exportAliases( channel, info );
        }
        /*
        else if ( message.startsWith( "!say "))
        {
            String msg = message.substring(5);
            String channel = msg.substring( 0, msg.indexOf(" ") );
            if( channel.charAt(0) != '#' )
            {
                channel = "#" + channel;
            }
            info.sendMessage( channel, msg.substring(msg.indexOf(" ")).trim() );
        }
        else if ( message.startsWith( "!do "))
        {
            String msg = message.substring(4);
            String channel = msg.substring( 0, msg.indexOf(" ") );
            if( channel.charAt(0) != '#' )
            {
                channel = "#" + channel;
            }
            info.sendAction( channel, msg.substring(msg.indexOf(" ")).trim() );
        }
         */
        else
            return false;
        return true;
    }


    /**
     *  Set's the default timezone for the sender
     */
    protected void setTimezone( Info info, String message )
    {
        try
        {
            String stripped = message.substring( message.indexOf(" ") ).trim(  );
            nickTimeZones.setProperty( info.getSender(), stripped );
            nickTimeZones.store( new FileWriter( TIMEZONES ), TIMEZONE_COMMENTS );
        }
        catch ( Exception e )
        {}
        info.sendMessage( "Default timezone set");
    }

    /**
     *  given a set of login info (either a channel or a channel/password pair, log in to the channel
     */
    public void join(String login, PircBot bot)
    {
           int space = login.indexOf(' ');
           String channel;
           if(space == -1)
           {
               //no password
               if( login.charAt(0) == '#' )
               {
                   channel = login;
               }
               else channel = "#" + login;
               channels.add( channel );
               bot.joinChannel( channel);
           }
           else
           {
               channel = login.substring(0, space);
               String password = login.substring(space);
               if( channel.charAt(0) != '#' )
               {
                   channel = "#" + channel;
               }
               channels.add( channel );
               bot.joinChannel( channel, password );
           }
    }


    /**
     *  returns if the user has voice or is an op on the channel
     */
    public boolean hasVoice(String nick, String channel)
    {
        return false;
    }

    /**
     * Sends the names of everyone in the channel to the channel - used to "ping" everyone if something important happens.
     *
     * @param channel DOCUMENT ME!
     */
    public void sendNames( Info info )
    {
        User[] users = info.getBot().getUsers( info.getChannel() );
        StringBuilder ret = new StringBuilder(  );

        if( Utils.shouldDefer(info) ) return;

        for ( int i = 0; i < users.length; i++ )
        {
            ret.append( users[i].getNick() );
            ret.append( " " );
        }

        info.sendMessage( ret.toString(  ) );
    }

    /**
     *  Finds whether it's daytime or nighttime in the Nexus based on the actual time
     */
    public void weather( Info info )
    {
        try
        {
            String time = Utils.getRealTime(info);
            SimpleDateFormat df = new SimpleDateFormat( "MMM. d, HH:mm:ss z");
            Calendar cal = df.getCalendar();
            Date now = df.parse( time );
            int hour = cal.get(Calendar.HOUR_OF_DAY) + 1;
            int minutes = cal.get(Calendar.MINUTE);
            String message = null;
            switch( hour )
            {
                case 1:
                case 5:
                case 9:
                case 13:
                case 17:
                case 21:
                    //first hour of night
                    message = "It is dark outside. Night has fallen. (" + (120 - minutes) + " minutes till dawn)";
                    break;
                case 2:
                case 6:
                case 10:
                case 14:
                case 18:
                case 22:
                    //second hour night
                    message = "You can make out the sun in the East as it begins to rise. (" + (60 - minutes) + " minutes till dawn)";
                    break;
                case 3:
                case 7:
                case 11:
                case 15:
                case 19:
                case 23:
                    //first hour day
                    message = "The sun is high overhead. (" + (120 - minutes) + " minutes till nightfall)";
                    break;
                case 4:
                case 8:
                case 12:
                case 16:
                case 20:
                case 0:
                    //second hour day
                    message = "The sun is low in the West. (" + (60 - minutes) + " minutes till nightfall)";
                    break;
            }
            info.sendMessage( message );
        }
        catch (Exception e)
        {}
    }

    /**
     *  Sets the channel mode to Moderated
     */
    public void quiet( Info info )
    {
        info.getBot().setMode( info.getChannel(), "+m");
    }

    /**
     *  Sets the channel mode to unModerated
     */
    public void talk( Info info )
    {
        info.getBot().setMode( info.getChannel(), "-m");
    }

    /**
     *  Gives voice to nick in message
     */
    public void voice( Info info )
    {
        String namelist = info.getMessage().substring(6);
        String[] names = namelist.split(" ");
        namelist = "+vvvvvv" + namelist;
        info.getBot().setMode( info.getChannel(), namelist );
    }

    /**
     *  Removes voice from nick in message
     */
    public void novoice( Info info )
    {
        String namelist = info.getMessage().substring(8);
        String[] names = namelist.split(" ");
        namelist = "-vvvvvv" + namelist;
        info.getBot().setMode( info.getChannel(), namelist );
    }


    public void showAllMessages( Info info )
    {
        List m = messages.reviewAllMessages(  );

        if ( m != null )
        {
            if ( m.size(  ) == 0 )
            {
                info.sendMessage( "No messages pending." );
            }
            else
            {
                for ( Object o : m )
                {
                    info.sendMessage( String.valueOf( o ) );
                }
            }
       }
    }


    /**
     *  lists all the channels the bot is in.
     */
    private void diagnostics( Info info )
    {
        info.sendMessage(info.getSender(), "I am on " + Arrays.toString( info.getBot().getChannels() ) );
    }

    public boolean showHelp( Info info )
    {
        info.sendMessage( "Channel commands:");
        info.sendMessage( "     general commands are !ping, !weather, !gmt, !timer, !alias <nick> <alt>, and !whois <nick or alt previously registered with !alias>.");
        info.sendMessage( "     channel management commands are !quiet, !talk, !voice <nick>, !novoice <nick>.");
        return true;
    };

    public void handleJoin( Info info )
    {
        // System.out.println( "Sender: " + info.getSender() + " my name " + info.getBot().getName() );
        if(info.getSender().equals(info.getBot().getName()))
        {
            histories.put( info.getChannel().toLowerCase(), new History(50));
            System.out.println( "adding a new history structure for " + info.getChannel() );
        }
    }

    public void registerAlias( Info info )
    {
        String stripped = info.getMessage().substring( info.getMessage().indexOf(" ") ).trim(  );
        int breakpt = stripped.indexOf( ' ' );
        String nick = stripped.substring( 0, breakpt ).trim(  );
        String alt = stripped.substring( breakpt ).trim();
        aliases.putAlias( info.getChannel(), nick, alt );
        info.sendMessage( "Nick <-> aliases mapping saved." );
    }

    public void reportAlias( Info info )
    {
        String name = info.getMessage().substring( info.getMessage().indexOf(" ") ).trim(  );
        aliases.showAlias( info, name );
    }


    public void clearAlias( Info info )
    {
        String name = info.getMessage().substring( info.getMessage().indexOf(" ") ).trim(  );
        aliases.removeAlias( info.getChannel(), name );
    }


    public void whereIs( Info info, String who )
    {
        List<String> found = new ArrayList<String>();
        String[] channels = info.getBot().getChannels();
        for(String channel : channels)
        {
            User[] users = info.getBot().getUsers(channel);
            for(User user : users)
            {
                if( user.getNick().equals(who) )
                {
                    found.add(channel);
                }
            }
        }
        String msg = null;
        if( found.size() == 0 )
        {
            msg = who + " isn't in any channel I'm logged into.";
            Date when = lastSeen.get( who );
            if( when != null )
            {
                msg = msg + "  They were last seen on " + when + " in " + lastChannel.get( who ) + " when they said \"" + lastSaid.get(who) + "\"";
            }
        }
        else
        {
            msg = who + " is currently in the following channel(s): ";
            for(String channel : found)
            {
                msg = msg + channel + ",";
            }
            msg = msg + " and they last said \"" + lastSaid.get(who) + "\"";
            info.sendMessage( who, info.getSender() + " was looking for you in " + info.getChannel() );
        }
        info.sendMessage(msg);
    }

    public void handleNickChange( Info info ){}

    public String getAlias( String channel, String nick)
    {
        return aliases.getAlias( channel, nick );
    }
}



