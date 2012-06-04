/*
 * :mode=java:tabSize=4:indentSize=4:noTabs=true:
 * :folding=indent:collapseFolds=0:wrap=none:maxLineLen=120:
 *
 * $Source: /var/lib/cvsd/cvsrepo/invasion/nw/nexusbot/src/nexusbot/BotCommandHandler.java,v $
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
import java.util.*;
import java.net.*;
import java.text.*;
import java.util.*;


/**
 *  Provides an interface for pluggable IRC command handlers.  Calling code should create an Info object and pass it in
 *  to the implemnting class's handleMessage() method.  Fire and forget...you just need to check the return method to see
 *  if this handler handled the command.
 *
 * @author jchoyt
  */
public interface BotCommandHandler
{
    /**
     *  Handles the commands this handler is capable of.  Returns true if the command was handled, false if not.
     */
    public boolean handleMessage( Info info );

    public boolean handlePrivateMessage( Info info );

    public void handleJoin( Info info );

    public void handleNickChange( Info info );  //the sender member of Info gets the new nick


}
