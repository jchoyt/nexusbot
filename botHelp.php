<?
include 'top.html';
?>
    <div class="content">
        <!--  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
    	<h1>Channel Commands</h1>
        <div class="descr">General channel commands</div>
        <ul>
            <li><b>!ping</b> pings everyone in the room</li>
            <li><b>!ping &lt;message&gt;</b> pings everyone in the room and announces &lt;message&gt;</li>
            <li><b>!weather</b> gives the current in-game day/night description how long until it changes</li>
            <li><b>!gmt</b> gives the current GMT time</li>
            <li><b>!timer &lt;minutes&gt; &lt;message&gt;</b> gives you &lt;message&gt; in the specified number of minutes.  It's a timer with alarm.</li>
            <li><b>!alias &lt;nick&gt; &lt;alt&gt;</b></li>
            <li><b>!unalias &lt;nick&gt;</b> removes a previously registered nick</li>
            <li><b>!whois &lt;nick or alt previously registered with !alias&gt;</b></li>
            <li><b>!history &lt;count&gt;</b> shows the last &lt;count&gt; messages in the channel (50 max).  Uses 10 as the default</li>
            <li><b>!whereis &lt;nick&gt;</b> finds someone or tells you the last time they were active.  If they are online, they are told you are looking for them.</li>
        </ul>
        <div class="descr">Channel management commands (require op or half-op)</div>
        <ul>
            <li><b>!quiet</b> Sets the mode to -m</li>
            <li><b>!talk</b> Sets the mode to +m</li>
            <li><b>!voice &lt;nick&gt;</b> Gives &lt;nick&gt; voice in the channel</li>
            <li><b>!novoice &lt;nick&gt;.</b> Retracts voice from &lt;nick&gt; in the channel</li>
        </ul>
        <!--  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
         <h1>Message commands</h1>
         <div class="descr">Commands for leaving and picking up messages</div>
        <ul>
            <li><b>!leavemessage &lt;nick&gt; &lt;message&gt;</b> to leave a message (<b>!lm</b> works as well)</li>
            <li><b>!getmessages or !gm</b> to retrieve a message</li>
        </ul>
        <!--  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
        <h1>YOCS commands</h1>
         <div class="descr">Commands specific to YOCS or are for fun</div>
         <ul>
            <li><b>!heal [alt name]</b> Reminds everyone in five minutes that you need to be healed.  The alt name is optional.</li>
            <li><b>!order &lt;order number&gt;</b> gives the url for the YOCS order number provided</li>
        </ul>
        <div class="descr">Entertaining commands</div>
        <ul>
            <li><b>!quote</b> randomly selects a quote from it's database (over 300)</b></li>
            <li><b>!quote &lt;term&gt;</b> randomly selects a quote that matches &lt;term&gt;</b></li>
            <li><b>!love &lt;nick&gt;</b> sends a nice message about &lt;nick&gt;</li>
            <li><b>!abuse &lt;nick&gt;</b> subjects &lt;nick&gt; to random brutality</li>
            <li><b>!cookie</b> gives a fortune cookie fortune</li>
            <li><b>!8ball &lt;yes/no question&gt;</b> Gives a Magic 8 Ball-like answer to the question.</li>
            <li><b>!vend</b> dispenses various items</li>
        </ul>
        <!--  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
         <h1>Raid commands</h1>
         <div class="descr">Commands for leading a raid</div>
        <ul>
            <li><b>!setTarget &lt;faction&gt;/&lt;location&gt;</b> sets the target</li>
            <li><b>!setPath &lt;path to target&gt;</b> sets the recommended path to the target</li>
            <li><b>!target</b> retrieves the target</li>
            <li><b>!path</b> retrieves the path</li>
            <li><b>!go</b> gives the "MOVE OUT" command.  If the target is set, it will be repeated here.</li>
            <li><b>!ward</b> gives command to start bashing</li>
            <li><b>!tanks</b> gives command for tanks to go in</li>
            <li><b>!aoe</b> gives command to commence aoe</li>
            <li><b>!pile</b> gives all-in</li>
            <li><b>!clearAll</b> clears path and target info (<b>recommended for end of raid</b>)</li>
            <li><b>!setnextraid &lt;yyyy-MM-dd HH:mm z&gt; sets the time for the next raid</li>
            <li><b>!nextraid &lt;timezone&gt;</b> returns the time for the next raid in the given timezone (e.g., EST, GMT).  Leaving the timezone blank returns the GMT time.</li>
        </ul>
     </div>
<?
include 'bottom.html';
?>
<!-- :wrap=soft:noTabs=true:collapseFolds=0:mode=php: -->

