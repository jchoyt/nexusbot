package nexusbot;

/**
 *  Copied wholesale from http://oreilly.com/pub/h/1975
 *  IRC Hacks by Paul Mutton
 */
import java.util.*;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
  */
public class History extends ArrayList
{
    /**
     * DOCUMENT ME!
     */
    private int maxListSize;

    /**
     * Creates a new ContextList object.
     *
     * @param maxListSize DOCUMENT ME!
     */
    public History( int maxListSize )
    {
        this.maxListSize = maxListSize;
    }

    /**
     * DOCUMENT ME!
     *
     * @param o DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean add( Object o )
    {
        if ( this.size(  ) >= maxListSize )
        {
            // Remove the first item if the list is full.
            this.remove( 0 );
        }

        // Add the new item to the end of the list.
        return super.add( o );
    }
}

