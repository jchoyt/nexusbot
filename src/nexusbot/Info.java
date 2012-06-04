/*
 * :mode=java:tabSize=4:indentSize=4:noTabs=true:
 * :folding=indent:collapseFolds=0:wrap=none:maxLineLen=120:
 *
 * $Source: /var/lib/cvsd/cvsrepo/invasion/nw/nexusbot/src/nexusbot/Info.java,v $
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
 * This is the main class for nexusbot.  It processes all commands and provides for all interactions with IRC channels.
 *
 * @author jchoyt
  */
public class Info
{
    private String channel;
    private String sender;
    private String login;
    private String hostname;
    private String message;
    private NexusBot bot;

    private Info()
    {
    }


    /**
    Basic constructor for Info
    */
    public Info(NexusBot bot, String channel, String sender, String login, String hostname, String message)
    {
        this.channel  = channel  ;
        this.sender   = sender   ;
        this.login    = login    ;
        this.hostname = hostname ;
        this.message  = message  ;
        this.bot = bot;
    }


    /**
     *  Constructor for Private messages
     */
    public Info(NexusBot bot, String sender, String login, String hostname, String message)
    {
        this.channel  = sender ;
        this.sender   = sender   ;
        this.login    = login    ;
        this.hostname = hostname ;
        this.message  = message  ;
        this.bot = bot;
    }

    /**
	 * Returns the value of bot.
	 */
	public NexusBot getBot()
	{
		return bot;
	}


    public void sendMessage( String channel, String msg )
    {
        bot.sendMessage( channel, msg );
    }


    public void sendMessage( String msg )
    {
        bot.sendMessage( this.channel, msg );
    }


    public void sendAction( String channel, String msg )
    {
        bot.sendAction( channel, msg );
    }


    public void sendAction( String msg )
    {
        bot.sendAction( this.channel, msg );
    }


    /**
	 * Returns the value of message.
	 */
	public String getMessage()
	{
		return message;
	}


    /**
	 * Returns the value of channel.
	 */
	public String getChannel()
	{
		return channel;
	}


	/**
	 * Returns the value of nick.
	 */
	public String getSender()
	{
		return sender;
	}

}
