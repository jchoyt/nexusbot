/*
 * :mode=java:tabSize=4:indentSize=4:noTabs=true:
 * :folding=indent:collapseFolds=0:wrap=none:maxLineLen=120:
 *
 * $Source: /var/lib/cvsd/cvsrepo/invasion/nw/nexusbot/src/nexusbot/EditDistance.java,v $
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

/**
 *  Copied wholesale from http://www.merriampark.com/ldjava.htm
 *  Levenshtein Distance Algorithm: Java Implementation
 *  by Chas Emerick
 *  From an email from Chas Emerick to Michael Gilleland, 22 October 2003:
 *
 *  Mr. Gilleland,
 *
 *  As you may know, the Apache Jakarta Commons project had appropriated
 *  your sample implementation of the Levenshtein Distance algorithm for
 *  its commons-lang Java library.  While attempting to use it with two
 *  very large strings, I encountered an OutOfMemoryError, due to the fact
 *  that a matrix is created with the dimensions of the two strings'
 *  lengths.  I know you created the implementation to go with your
 *  (excellent) illustration of the algorithm, so this matrix approach
 *  translates that illustration and tutorial perfectly.
 *
 *  However, as I said, the matrix approach doesn't lend itself to getting
 *  the edit distance of two large strings.  For this purpose, I modified
 *  your implementation to use two single-dimensional arrays; this is
 *  clearly more memory-friendly (although it probably results in some very
 *  slight performance degradation when comparing smaller strings).
 *
 *  I've submitted the modification to the maintainers of the commons-lang
 *  project, and I've appended the relevant method below.
 *
 *  Thanks!
 *
 *  Chas Emerick
 *
 *
 * @author Chas Emerick
 * @version $Revision: 1.1 $
 */
public class EditDistance
{


    /**
     *  checks the full strings and the shortest string vs. the long string truncated to the same lenght as teh longer
     *  one.  This is primarly to check for nicks like "Pruveyor|zzz" and make sure the messages for "Purveyor" get
     *  delivered.
     */
    public static int getEditDistance( String s, String t )
    {
        int score = calcEditDistance( s, t );
        // System.out.println( s + t + score );
        return score;

        /* Need to remove this....very long nick's are giving back too many hits
        int score2;
        if( s.length() == t.length() )
        {
            return score;
        }
        else if(s.length() > t.length() )
        {
            score2 = calcEditDistance( s.substring(0, t.length()), t);
            // System.out.println( s.substring(0, t.length()) + t );
        }
        else
        {
            score2 = calcEditDistance( s, t.substring(0, s.length()));
            // System.out.println( s + t.substring(0, s.length()) );
        }
        // System.out.println( score2 );
        if (score < score2)
        {
            return score;
        }
        else
        {
            return score2;
        } */
    }




    /**
     *  Uses a modified version of the Levenshtein Distance algorithm to find the edit distance between two strings
     * @param s String 1
     * @param t String 2
     *
     * @return The edit distance between s and t.
     */
    public static int calcEditDistance( String s, String t )
    {
        if ( ( s == null ) || ( t == null ) )
        {
            throw new IllegalArgumentException( "Strings must not be null" );
        }

        /*
         *  The difference between this impl. and the previous is that, rather
         *  than creating and retaining a matrix of size s.length()+1 by t.length()+1,
         *  we maintain two single-dimensional arrays of length s.length()+1.  The first, d,
         *  is the 'current working' distance array that maintains the newest distance cost
         *  counts as we iterate through the characters of String s.  Each time we increment
         *  the index of String t we are comparing, d is copied to p, the second int[].  Doing so
         *  allows us to retain the previous cost counts as required by the algorithm (taking
         *  the minimum of the cost count to the left, up one, and diagonally up and to the left
         *  of the current cost count being calculated).  (Note that the arrays aren't really
         *  copied anymore, just switched...this is clearly much better than cloning an array
         *  or doing a System.arraycopy() each time  through the outer loop.)
         *
         *  Effectively, the difference between the two implementations is this one does not
         *  cause an out of memory condition when calculating the LD over two very large strings.
         */
        int n = s.length(  ); // length of s
        int m = t.length(  ); // length of t

        if ( n == 0 )
        {
            return m;
        }
        else if ( m == 0 )
        {
            return n;
        }

        int[] p = new int[n + 1]; //'previous' cost array, horizontally
        int[] d = new int[n + 1]; // cost array, horizontally
        int[] _d; //placeholder to assist in swapping p and d

        // indexes into strings s and t
        int i; // iterates through s
        int j; // iterates through t

        char t_j; // jth character of t

        int cost; // cost

        for ( i = 0; i <= n; i++ )
        {
            p[i] = i;
        }

        for ( j = 1; j <= m; j++ )
        {
            t_j = t.charAt( j - 1 );
            d[0] = j;

            for ( i = 1; i <= n; i++ )
            {
                cost = ( s.charAt( i - 1 ) == t_j ) ? 0 : 1;
                // minimum of cell to the left+1, to the top+1, diagonally left and up +cost
                d[i] = Math.min( Math.min( d[i - 1] + 1, p[i] + 1 ), p[i - 1] + cost );
            }

            // copy current distance counts to 'previous row' distance counts
            _d = p;
            p = d;
            d = _d;
        }

        // our last action in the above loop was to switch d and p, so p now
        // actually has the most recent cost counts
        return p[n];
    }
}

