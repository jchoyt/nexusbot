/*
 * :mode=java:tabSize=4:indentSize=4:noTabs=true:
 * :folding=indent:collapseFolds=0:wrap=none:maxLineLen=120:
 *
 * $Source: /var/lib/cvsd/cvsrepo/invasion/nw/nexusbot/src/nexusbot/YocsCommands.java,v $
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
public class YocsCommands implements BotCommandHandler
{
    /**
     * The list of quotes to use - these are loaded from a file with the Utils.loadFile command.
     */
    private List<String> quotes = new ArrayList<String>(  );

    /**
     * The list of nice things to do for people.  These are loaded from a file with the Utils.loadFile command.
     */
    private List<String> loves = new ArrayList<String>(  );

    /**
     * The list of ways to abuse people.  These are loaded from a file with the Utils.loadFile command.
     */
    private List<String> abuses = new ArrayList<String>(  );

    /**
     * 8 ball-like asnwers.  These are loaded from a file with the Utils.loadFile command.
     */
    private List<String> eightBallAnswers = new ArrayList<String>(  );

    private List<String> fortunes = new ArrayList<String>(  );

    private List<String> pants = new ArrayList<String>(  );

    private List<String> vend = new ArrayList<String>(  );

    private List<String> pantsFail = new ArrayList<String>(  );

    private List<String> facts = new ArrayList<String>(  );

    private List<String> oddities = new ArrayList<String>();

    private List<String> oddityTargets = Arrays.asList( "Player_1", "EugeneKay", "EugeneK", "EugenePhone","EugeneKaway","EugeNetBooK", "EugenePhoneInPocket", "Guye", "iGuy", "Dergones", "Johhan", "Thenixon", "Teksura", "Liche", "TylerRilm", "MacDre", "Baalhrezem", "Deadeyez", "Bruno", "BobQGeneric", "Deady", "Darque", "Teegan", "rufio", "CyAdora", "karmafred", "karma", "Rose", "zenny", "Daeriel", "Kotagchez", "Fist", "Gringo", "deli", "Tinted_Green" );

    private List<String> eugenes = Arrays.asList( "EugeneKay", "EugeneK", "EugenePhone","EugeneKaway","EugeNetBooK", "EugenePhoneInPocket" );

    private Inventory inventory = new Inventory("inventory.txt");

    private long tookPants = 0;

    // private final String orderCheckUrl = "http://yocs.freehostia.com/checkNewOrders.php";

    private boolean hasEksPants = false;


    public YocsCommands()
    {
        reload();
    }

    private Timer timer = new Timer(true);

    /**
     */
    public boolean handleMessage(Info info)
    {
        String message = info.getMessage();
        if ( message.equalsIgnoreCase( "!quote" ) )
        {
            sendQuote( info, quotes );
        }
        if ( message.startsWith( "!quote " ) )
        {
            sendQuote( info, quotes, message.substring( 7 ) );
        }
        else if ( message.startsWith( "!love " ) )
        {
            String nick = message.substring( 6 );
            giveLove( info, nick );
        }
        else if ( message.startsWith( "!hate" ) )
        {
            info.sendMessage( "Love must be sincere. Hate what is evil; cling to what is good. Be devoted to one another in brotherly love. Honor one another above yourselves. Never be lacking in zeal, but keep your spiritual fervor, serving the Lord. Be joyful in hope, patient in affliction, faithful in prayer. Share with God's people who are in need. Practice hospitality.  Bless those who persecute you; bless and do not curse. (Romans 12)" );
        }
        else if ( message.startsWith( "!abuse " ) )
        {
            String nick = message.substring( 7 );
            // System.out.println( "abusing " + nick );
            abuse( info, nick );
        }
        else if ( message.equalsIgnoreCase( "!cookie" ) )
        {
            sendQuote( info, fortunes );
        }

        else if ( message.equalsIgnoreCase( "!reload" ) )
        {
            reload(  );
            info.getBot().reload();
            info.sendMessage( "I have reloaded the various lists, as instructed." );
        }
        else if ( message.startsWith( "!8ball " ) )
        {
            eightBall( info );
        }
        else if ( message.equalsIgnoreCase( "!getPants" ) || message.equalsIgnoreCase( "!gp" )  )
        {
            if( hasEksPants )
            {
                getPants( info );
            }
            else
            {
                info.sendAction("points at Eugene. He is wearing his pants. You can use !takepants to try to remove them.....");
            }
        }
        else if ( message.equalsIgnoreCase( "!takePants" ) || message.equalsIgnoreCase( "!tp" ) )
        {
            if( hasEksPants )
            {
                info.sendMessage( "Eugene's pants are hidden somewhere. Use !getpants to search for them.");
            }
            else
            {
                double randomValue = Math.random(  );
                if ( info.getSender().indexOf("Eugene") > -1 )
                {
                    info.sendAction( "watches while Eugene takes off his pants, folds them neatly, hands them to the bot." );
                    info.sendAction( "hides the pants while Eugene covers his eyes and counts to 20.");
                    hasEksPants = true;
                    tookPants = System.currentTimeMillis();
                }
                else if ( info.getSender().equals( info.getBot().getLogin() ) || randomValue < 0.5 )
                {
                    info.sendAction("wrestles Eugene to the floor and takes his pants.  It hides them somewhere. Use !getpants to search for them.");
                    hasEksPants = true;
                    tookPants = System.currentTimeMillis();
                }
                else
                {
                    sendQuote(info, pantsFail);
                }
            }
        }
        else if ( message.equalsIgnoreCase( "!hasPants" ) )
        {
            if( hasEksPants )
            {
                info.sendMessage( "Yes! I have hidden Eugene's pants.  Use !getpants to search for them.");
            }
            else
            {
                info.sendMessage( "Eugene is fully panted. Ask me to !takepants and I will try to remove them.");
            }

        }
        else if ( message.equalsIgnoreCase( "!vend" ) )
        {
            double randomValue = Math.random(  );
            if( randomValue < 0.25 && inventory.getInventoryCount()>0)
            {
                // info.sendMessage("Let's see what I have for you from my special inventory..." );
                //TODO get from inventory.
                info.sendAction( "gives " + info.getSender() + " " + inventory.getItem() );
            }
            else
                sendQuote( info, vend );
        }
        else if ( message.equalsIgnoreCase( "!inventory" ) )
        {
            info.sendMessage("Kind strangers have given me " + inventory.getInventoryCount() + " items that I have not distributed yet. You can !give me your items to be handed out later." );
        }
        else if ( message.startsWith( "!give " ) )
        {
            String item = message.substring( 6 );
            inventory.addItem( item + " (provided by " + info.getSender() + ", " + new Date() + ")");
            info.sendAction("puts the thoughtful gift in a pouch marked \"For someone special\".");
        }
        else if ( message.equalsIgnoreCase("!fact") )
        {
            sendQuote(info, facts);
        }
        else if ( message.startsWith( "!addquote " ) )
        {
            String newQuote = message.substring( 10 ).trim();
            addQuote( info, newQuote );
        }
        else if ( message.startsWith( "!im " ) && info.getSender().startsWith("Sofia"))
        {
                double randomValue = Math.random(  );
                if( randomValue < 0.2 )
                {
                    info.sendMessage( info.getSender() + " my dear, corky has explained to me your eternal trouble with leaving messages for people ...  "  +
                        " your inability to hit the 'l' instead of the 'i' is legendary and it pains us to no end to see your struggle.  But we know you can overcome this debilitating affliction.  " +
                        "Corky has confided in me his powerful love for you, but he's ashamed of your typing skills.  Please.  The man's heart is torn over this.  Learn to hit the 'l'!" );
                }
                else
                {
                    info.sendMessage( "Poor girl.  It's !lm.  Ell.  It's an 'l'.  Next to the 'k'.  That's a good girl.  Here, have a present.");
                    sendQuote( info, vend );
                }
        }
        else
        {
            if( !hasEksPants && Utils.isPresent(info, eugenes) )
            {
                double randomValue = Math.random(  );
                if( randomValue < 0.001 )
                {
                    info.sendMessage("That reminds me, " + info.getSender() + "!" );
                    takePants(info);
                }
            }
            if ( hasEksPants && System.currentTimeMillis() > tookPants + 1 * 60 * 60 * 1000)
            {//return pants after an hour
                info.sendAction("gives Eugene back his pants back and makes a note to use an easier hiding place next time.  Use !takepants to remove his pants again.");
                hasEksPants = false;
            }
            return false;
        }
        return true;
    }

    /**
     *  Handles private messages.  These are usually administrative type tasks that only the bot owner should be able to execute.
     */
    public boolean handlePrivateMessage(Info info)
    {
        return false;
    }

    /**
     *  Sends a random selection from quotelist
     */
    private void sendQuote( Info info, List<String> quotelist )
    {
        int MAXLENGTH = 475;

        if ( quotes.size(  ) == 0 )
        {
            info.sendMessage( "Internal Bot Error. Please don't do that again." );
        }

        double randomValue = Math.random(  );
        int quoteNum = ( int ) ( randomValue * quotelist.size(  ) );
        String quote = quotelist.get( quoteNum );

        while ( quote.length(  ) > MAXLENGTH )
        {
            int breakPt = quote.lastIndexOf(" ", MAXLENGTH );
            info.sendMessage( quote.substring( 0, breakPt ) );
            quote = quote.substring( breakPt - 1 );
        }

        if ( quote.startsWith( "/me " ) )
        {
            info.sendAction(  quote.substring( 4 ) );
        }
        else
        {
            info.sendMessage( quote );
        }
    }


    /**
     *  Sends a random selection from quotelist
     */
    private void sendQuote( Info info, List<String> quotelist, String match )
    {
        int MAXLENGTH = 475;
        match = match.toLowerCase();
        if ( quotes.size(  ) == 0 )
        {
            info.sendMessage( "Internal Bot Error." );
        }

        double randomValue = Math.random(  );
        int quoteNum = ( int ) ( randomValue * quotelist.size(  ) );
        String quote = quotelist.get( quoteNum ).toLowerCase();
        int tries = 0;
        while( !quote.contains(match) && tries < 300 )
        {
            tries++;
            randomValue = Math.random(  );
            quoteNum = ( int ) ( randomValue * quotelist.size(  ) );
            quote = quotelist.get( quoteNum ).toLowerCase();
        }
        if( tries < 300 )
        {
            quote = quotelist.get( quoteNum );
        }
        else
            quote = "I didn't find a quote that matched \"" + match +"\"";
        while ( quote.length(  ) > MAXLENGTH )
        {
            int breakPt = quote.lastIndexOf(" ", MAXLENGTH );
            info.sendMessage( quote.substring( 0, breakPt ) );
            quote = quote.substring( breakPt - 1 );
        }

        if ( quote.startsWith( "/me " ) )
        {
            info.sendAction(  quote.substring( 4 ) );
        }
        else
        {
            info.sendMessage( quote );
        }
    }


    /**
     * Reloads all the various text files.  !reload makes the bot do this.
     */
    private void reload(  )
    {
        quotes = Utils.loadFile( "quotes.txt", true );
        abuses = Utils.loadFile( "abuse.txt", true );
        loves = Utils.loadFile( "love.txt", true );
        eightBallAnswers = Utils.loadFile( "8ball.txt", true );
        fortunes = Utils.loadFile( "fortunes.txt", true );
        pants = Utils.loadFile( "pants.txt", true );
        vend = Utils.loadFile( "vend.txt", true);
        pantsFail = Utils.loadFile( "failPants.txt", true);
        facts = Utils.loadFile( "facts.txt", true);
        oddities = Utils.loadFile( "oddities.txt", true);
        inventory.load();
    }

    /**
     * Sends a loving, reassuring message to someone.
     *
     * @param channel DOCUMENT ME!
     * @param sender DOCUMENT ME!
     * @param nick DOCUMENT ME!
     */
    private void giveLove( Info info, String nick )
    {
        String sender = info.getSender();
        if ( loves.size(  ) == 0 )
        {
            info.sendMessage( "Internal Bot Error. Please don't do that again." );
        }
        if ( nick.equalsIgnoreCase( "me" ) || nick.equalsIgnoreCase( sender ) )
        {
            info.sendAction( "eyes " + sender +
                " speculatively.  \"A little needy, are we?  Well, if it makes you feel better....\"" );
            if ( nick.equalsIgnoreCase( "me" ) )
            {
                nick = sender;
            }
        }

        double randomValue = Math.random(  );
        int quoteNum = ( int ) ( randomValue * loves.size(  ) );
        String quote = ( loves.get( quoteNum ) ).replaceAll( "<user>", nick ).replaceAll( "<sender>", sender )
                         .replaceAll( "<random>", Utils.getRandomUser( info ) );

        if ( quote.startsWith( "/me " ) )
        {
            info.sendAction(  quote.substring( 4 ) );
        }
        else
        {
            info.sendMessage( quote );
        }
    }


    /**
     * The bot attacks and kills/maims/humiliates someone
     *
     * @param channel DOCUMENT ME!
     * @param sender DOCUMENT ME!
     * @param nick DOCUMENT ME!
     */
    private void abuse( Info info, String nick )
    {
        String sender = info.getSender();
        if ( abuses.size(  ) == 0 )
        {
            info.sendMessage( "Internal Bot Error. Please don't do that again." );
        }
        if ( nick.equalsIgnoreCase( info.getBot().getName() ) || nick.equals( info.getBot().getLogin() ) )
            nick = sender;

        double randomValue = Math.random(  );
        int quoteNum = ( int ) ( randomValue * abuses.size(  ) );
        String quote = ( abuses.get( quoteNum ) ).replaceAll( "<sender>", sender )
                         .replaceAll( "<random>", Utils.getRandomUser( info ) ).replaceAll("<target>", nick);

        info.sendAction(  quote );
    }


    /**
     *  Gives customized Magic 8 Ball answers
     */
    private void eightBall( Info info )
    {
        String sender = info.getSender();
        if ( eightBallAnswers.size(  ) == 0 )
        {
            info.sendMessage( "Internal Bot Error. Please don't do that again." );
        }
        double randomValue = Math.random(  );
        int quoteNum = ( int ) ( randomValue * eightBallAnswers.size(  ) );
        String quote = eightBallAnswers.get( quoteNum );
        if( quote.equals( "abuse" ) )
        {
            info.sendAction( "looks annoyed to be asked such a trivial question.");
            abuseSender( info );
        }
        else
            info.sendAction( quote );
    }

    /**
     *  abuse the sender
     */
    private void abuseSender( Info info )
    {
        String sender = info.getSender();
        if ( abuses.size(  ) == 0 )
        {
            info.sendMessage( "Internal Bot Error. Please don't do that again." );
        }
        double randomValue = Math.random(  );
        int quoteNum = ( int ) ( randomValue * abuses.size(  ) );
        String quote = abuses.get( quoteNum ).replaceAll( "<target>", sender );
        info.sendAction( quote );
    }


    protected void takePants( Info info )
    {
        hasEksPants = true;
        tookPants = System.currentTimeMillis();
        info.sendAction("takes Eugene's pants.  Use !getpants to search for them.");
    }

    protected void getPants( Info info )
    {
        if( info.getSender().indexOf( "Eugene" ) > -1 )
        {
             double randomValue = Math.random(  );
             if(randomValue < 0.9 )
             {
                 sendQuote(info, pants);
             }
             else
             {
                 info.sendMessage("You search and find your pants!  For God's sake, please put them on.");
                 hasEksPants = false;
             }
        }
        else
        {
             double randomValue = Math.random(  );
             if( randomValue < 0.03 )
             {
                 info.sendMessage("Have you *seen* Eugene?  Are you sure you want to find his pants?");
             }
             else if(randomValue < 0.9 )
             {
                 sendQuote(info, pants);
             }
             else
             {
                 info.sendMessage("You search and find Eugene's pants!  What you do with them is up to you.  I'm out of it, now.");
                 hasEksPants = false;
             }
        }
    }


    public void handleJoin( Info info )
    {
        String sender = info.getSender();
        if( info.getSender().equals("Eugene") )
        {
             double randomValue = Math.random(  );
             if( randomValue < 0.1 )
             {
                 takePants(info);
             }
        }
        if(  Math.random() < 0.01 && oddityTargets.contains( info.getSender() ) )
        {
            int quoteNum = ( int ) ( Math.random() * oddities.size() );
            String quote = ( oddities.get( quoteNum ) ).replaceAll( "<sender>", sender )
                        .replaceAll( "<random>", Utils.getRandomUser( info ) );

            if ( quote.startsWith( "/me " ) )
            {
                info.sendAction(  quote.substring( 4 ) );
            }
            else
            {
                info.sendMessage( quote );
            }
        }
    }
    public void handleNickChange( Info info ){}

    public void addQuote(Info info, String newQuote)
    {
        quotes.add( newQuote );
        try
        {
            FileWriter w = new FileWriter( "quotes.txt", true);
            w.write( newQuote + "\n" );
            w.close();
        }
        catch (Exception e)
        {
            e.printStackTrace(  );
        }
        info.sendMessage( "Quote added.  It had better be worthy." );
    }
}
