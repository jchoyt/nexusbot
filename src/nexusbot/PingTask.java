/*
 * :mode=java:tabSize=4:indentSize=4:noTabs=true:
 * :folding=indent:collapseFolds=0:wrap=none:maxLineLen=120:
 *
 * $Source: /var/lib/cvsd/cvsrepo/invasion/nw/nexusbot/src/nexusbot/PingTask.java,v $
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
import java.util.*;


/**
 * Sets a timer and gives a message after a certain amount of time.
 *
 * @author jchoyt
 */


public class PingTask extends TimerTask
{
    private Info info;
    private String message;
    private int minutes;

    public PingTask( Info info, String message)
    {
        this.info = info;
        this.message = message;
    }

    public void run()
    {
        //ping the channel
        // User[] users = info.getBot().getUsers( info.getChannel() );
        // StringBuilder ret = new StringBuilder(  );
        // if( Utils.shouldDefer(info) ) return;
        // for ( int i = 0; i < users.length; i++ )
        // {
        //     ret.append( users[i].getNick() );
        //     ret.append( " " );
        // }
        // info.sendMessage( ret.toString(  ) );        
        //send the message
        info.sendMessage( Colors.BLUE + Colors.BOLD + info.getSender() + ", " +  message );
    }

}
