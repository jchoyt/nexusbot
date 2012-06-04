/*
 * :mode=java:tabSize=4:indentSize=4:noTabs=true:
 * :folding=indent:collapseFolds=0:wrap=none:maxLineLen=120:
 *
 * $Source: /var/lib/cvsd/cvsrepo/invasion/nw/nexusbot/src/nexusbot/ShCommands.java,v $
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
import java.sql.*;


/**
 * This provides functionality that is more closely tied to channel management or dealing with RL objects (like time)
 *
 * @author jchoyt
 */
public class ShCommands implements BotCommandHandler {

    /**
     * DOCUMENT ME!
     */
    private Map <String, Properties> shlocs = new HashMap <String,
             Properties>();


    private List <String> planes = Arrays.asList("Paradise", "Purgatorio",
             "Stygia", "The Sewers", "Valhalla", "Nimbus", "Stygian Warrens", "Nifleheim");


    Connection conn;

    public ShCommands(NexusBot bot) {
        try {
            Class.forName(bot.getProperty("db_driver"));
        } catch (java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection(NexusBot bot)
    {
        try {
            conn = DriverManager.getConnection (bot.getProperty("db_url"), bot.getProperty("db_username"), bot.getProperty("db_password") );
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public boolean handleMessage(Info info) {
        return false;
    }


    protected void setShLocation(Info info, String [] args) {
        //args[]  should be (xcoord, ycoord, plane, faction)
        if (! planes.contains(args[2])) {
            info.sendMessage("Usage incorrect.  Proper formating is: !shset x~y~plane~faction  Better yet, use http://yocs.freehostia.com/ShSearch.php if you have psychometry.");
        }
        PreparedStatement s = null;
        try {
            String faction = args[3];
            String delete = "DELETE from sh where faction = ?";
            conn = getConnection( info.getBot() );
            s = conn.prepareStatement(delete);
            try {
                s.setString(1, faction);
                s.execute();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                closeAll( s, null, null, null );
            }


            String insert = "Insert into sh (x, y, plane, faction) values (?,?,?,?)";
            s = conn.prepareStatement(insert);
            s.setInt(1, Integer.parseInt(args[0]));
            s.setInt(2, Integer.parseInt(args[1]));
            s.setString(3, args[2]);
            s.setString(4, args[3]);
            s.executeUpdate();
            info.sendMessage("The location of " + faction + " has been saved");
        } catch (Exception e) {
            info.sendMessage("SH location not saved.  An exception occured (" + e.getMessage() + ")");
            e.printStackTrace();
        } finally {
            closeAll( s, null, null, conn );
        }
    }


    protected void shQuery(Info info, String filter) {
        Statement s = null;
        ResultSet rs = null;
        try {
            String query = "select faction, alignment, x, y, plane from hg where pppp";
            conn = getConnection( info.getBot() );
            s = conn.createStatement();
            query = query.replaceAll("pppp", filter);
            // s.setString( 1, filter );
            rs = s.executeQuery(query);
            while (rs.next()) {
                info.sendMessage(rs.getString("faction") + " (" + rs.getString("alignment") + ") located at (" + rs.getString("x") + ", " + rs.getString("y") + ") in " + rs.getString("plane"));
            }
        } catch (Exception e) {
            info.sendMessage("An exception occured (" + e.getMessage() + ")");
            e.printStackTrace();
        } finally {
            closeAll( null, rs, s, conn );
        }
    }

    protected void showShLocation(Info info, String faction) {
        if (! shlocs.containsKey(info.getChannel())) {
            info.sendMessage("No SH locations set for " + info.getChannel());
            return;
        }

        Properties channelLocs = shlocs.get(info.getChannel());
        /*
         *  do the edit distance thing...check the nick's first
         */
        double score = 0.0;

        for (Enumeration e = channelLocs.propertyNames() ; e.hasMoreElements() ;) {
            String name = (String) e.nextElement();
            score = (1.0 * EditDistance.getEditDistance(faction.toLowerCase(),
                     name.toLowerCase())) / faction.length();


            if (score < 0.20) {
                info.sendMessage(name + " is located at " + channelLocs.getProperty(name));
            }
            if (score == 0.0) {
                return;
            }
        }

    }

    /**
     *  Parses faction info and puts it into the database.
     *  @parameter args - faction info.  In the order:  faction link, level, karma, members, honor, alignment
     */
    protected void setFactionInfo(Info info, String [] args) {
        //parse the faction name and id out - <a href="/factions/view.do?factionID=3471">Busty's Littlest Whorehouse</a>
        String match = "factionID=";
        int start = args[0].indexOf(match) + match.length();
        int end = args[0].indexOf("\"", start);
        String factionId = args[0].substring(start, end);
        match = ">";
        start = args[0].indexOf(match) + match.length();
        end = args[0].indexOf("<", start);
        String factionName = args[0].substring(start, end);


        // do the db stuff
        PreparedStatement s = null;
        try {
            String faction = args[3];
            String delete = "DELETE from factions where faction = ?";
            conn = getConnection( info.getBot() );
            s = conn.prepareStatement(delete);
            try {
                s.setString(1, factionName);
                s.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            s.close();
            String insert = "Insert into factions (faction, level, karma, members, honor, alignment) values (?,?,?,?,?,?)";
            s = conn.prepareStatement(insert);
            s.setString(1, factionName);
            s.setInt(2, Integer.parseInt(args[1]));
            s.setInt(3, Integer.parseInt(args[2]));
            s.setInt(4, Integer.parseInt(args[3]));
            s.setInt(5, Integer.parseInt(args[4]));
            s.setString(6, args[5]);
            s.executeUpdate();
            info.sendMessage("The information for " + factionName + " has been saved");
        } catch (Exception e) {
            info.sendMessage("Faction info not saved.  An exception occured (" + e.getMessage() + ")");
            e.printStackTrace();
        } finally {
            closeAll( s, null, null, conn );
        }
    }


    public void handleJoin(Info info) {
    }

    public void handleNickChange(Info info) {
    }


    /**
     *  Handles private messages.  These are usually administrative type tasks that only the bot owner should be able to execute.
     */
    public boolean handlePrivateMessage(Info info) {
        String message = info.getMessage();
        String sender = info.getSender();
        if (message.startsWith("!shset ")) {
            String stripped = message.substring(message.indexOf(" ")).trim();
            setShLocation(info, stripped.split("~"));
        } else{
            if (message.startsWith("!shget ")) {
                String faction = message.substring(message.indexOf(" ")).trim();
                showShLocation(info, faction);
            } else{
                if (message.startsWith("!logFaction ")) {
                    // e.g., !logFaction <a href="/factions/view.do?factionID=3471">Busty's Littlest Whorehouse</a>~11~114~11~40~Good
                    String stripped = message.substring(message.indexOf(" ")).trim();
                    setFactionInfo(info, stripped.split("~"));
                } else{
                    if (message.startsWith("!shquery ")) {
                        String filter = message.substring(message.indexOf(" ")).trim();
                        shQuery(info, filter);
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }


    private static void closeAll( PreparedStatement ps, ResultSet rs, Statement s, Connection conn)
    {
        try{
            if( ps != null )
            {
                ps.close();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        try{
            if( rs != null )
            {
                rs.close();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        try{
            if( s != null )
            {
                s.close();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        try{
            if( conn != null )
            {
                conn.close();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}



