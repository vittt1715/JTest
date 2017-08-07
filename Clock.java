// File: Clock.java from the package edu.colorado.simulations
// Complete documentation is available from the Clock link in
//   http://www.cs.colorado.edu/~main/docs/

package edu.colorado.simulations;
import java.util.Vector;

/******************************************************************************
* A <CODE>Clock</CODE> object holds one instance of a time value shuch as 
* 9:48 P.M.
*
* <b>Java Source Code for this class:</b>
*   <A HREF="../../../../edu/colorado/simulations/Clock.java">
*   http://www.cs.colorado.edu/~main/edu/colorado/simulations/Clock.java
*   </A>
*
* @author Michael Main 
*   <A HREF="mailto:main@colorado.edu"> (main@colorado.edu) </A>
*
* @version Feb 10, 2016
*
* @see CuckooClock
* @see Clock24
******************************************************************************/
public class Clock
{ 
   private int myHour;
   private int myMinute;
   private boolean myMorning; 

   /**
   * Construct a <CODE>Clock</CODE> that is initially set to midnight.
   * <b>Postcondition:</b>
   *   This <CODE>Clock</CODE> has been initialized with an initial time of 
   *   midnight. 
   **/   
   public Clock( )
   {
      myHour = 12;
      myMinute = 0;
      myMorning = true;
   }
   
   

   /**
   * Move this <CODE>Clock</CODE>'s time by a given number of minutes.
   * @param minutes
   *   the amount to move this <CODE>Clock</CODE>'s time
   * <b>Postcondition:</b>
   *   This <CODE>Clock</CODE>'s time has been moved forward by the indicated
   *   number of minutes. Note: A negative argument moves this
   *   <CODE>Clock</CODE> backward.
   **/
   public void advance(int minutes)
   {
      final int MINUTES_PER_DAY = 24*60;
      final int MINUTES_PER_HOUR = 60;
      
      // Change the minutes so that 0 <= minutes < MINUTES_PER_DAY
      if (minutes < 0)
         minutes += MINUTES_PER_DAY * (1 - minutes/MINUTES_PER_DAY);
      if (minutes >= MINUTES_PER_DAY)
         minutes -= MINUTES_PER_DAY * (minutes/MINUTES_PER_DAY);
         
      // Advance the clock any full hours
      while (minutes+myMinute >= 60)
      {
         minutes -= MINUTES_PER_HOUR;
         myHour++;
         if (myHour == 12)
            myMorning = !myMorning;
         else if (myHour == 13)
            myHour = 1;
      }
      
      // Advance any remaining minutes
      myMinute += minutes;
   }


   /**
   * Test whether the time on one clock is earlier than the time on another.
   * @param c1
   *   a <CODE>Clock</CODE>
   * @param c2
   *   another <CODE>Clock</CODE>
   * @return
   *   Returns <CODE>true</CODE> if the time on <CODE>c1</CODE> is earlier 
   *   than the time on <CODE>c2</CODE> over a usual day (starting at midnight
   *   and continuing through 11:59 P.M.); otherwise returns <CODE>false</CODE>. 
   **/
   public static boolean earlier(Clock c1, Clock c2)
   {
      // Check whether one is morning and the other is not.
      if (c1.isMorning( ) && !c2.isMorning( ))
         return true;
      else if (c2.isMorning( ) && !c1.isMorning( ))
         return false;

      // Check whether one is 12 o�clock and the other is not.
      else if ((c1.getHour( ) == 12) && (c2.getHour( ) != 12))
         return true;
      else if ((c2.getHour( ) == 12) && (c1.getHour( ) != 12))
         return false;

      // Check whether the hours are different from each other.
      else if (c1.getHour( ) < c2.getHour( ))
         return true;
      else if (c2.getHour( ) < c1.getHour( ))
         return false;

      // The hours are the same, so check the minutes.
      else if (c1.getMinute( ) < c2.getMinute( ))
         return true;
      else 
         return false;
   }
   
   
   /**
   * Get the current hour of this <CODE>Clock</CODE>.
   * @return
   *   the current hour (always in the range 1...12)
   **/
   public int getHour( )
   {
      return myHour;
   }
 
   
   /**
   * Get the current minute of this <CODE>Clock</CODE>.
   * @return
   *   the current minute (always in the range 0...59)
   **/
   public int getMinute( )
   {
      return myMinute;
   }


   /**
   * Check whether this <CODE>Clock</CODE>'s time is before noon.
   * @return
   *   If this <CODE>Clock</CODE>'s time lies from 12:00 midnight
   *   to 11:59 A.M. (inclusive), then the return value is
   *   <CODE>true</CODE>; otherwise the return value is <CODE>false</CODE>.   
   **/
   public boolean isMorning( )
   {
      return myMorning;
   }

   
   /**
   * Set the current time of this <CODE>Clock</CODE>.
   * @param hour
   *   the hour to set this <CODE>Clock</CODE> to
   * @param minute
   *   the minute to set this <CODE>Clock</CODE> to
   * @param morning
   *   indication of whether the new time is before noon    
   * <b>Postcondition:</b>
   *   This <CODE>Clock</CODE>'s time has been set to the given hour and
   *   minute (using the usual 12-hour notation). If the third parameter,
   *   <CODE>morning</CODE>, is <CODE>true</CODE>, then this time is from
   *   12:00 midnight to 11:59 A.M. Otherwise this time is from 12:00 noon
   *   to 11:59 P.M.
   * @exception IllegalArgumentException
   *   Indicates that the <CODE>hour</CODE> or <CODE>minute</CODE> is illegal.
   **/
   public void setTime(int hour, int minute, boolean morning)
   {
      if ((1 > hour) || (hour > 12))
         throw new IllegalArgumentException("Illegal hour: " + hour);
      if ((0 > minute) || (minute > 59))
         throw new IllegalArgumentException("Illegal minute: " + minute);
      myHour = hour;
      myMinute = minute;
      myMorning = morning;
   }

   public static <T extends Clock> boolean someMorning(Vector<T> clocks)
   {
      for (T next : clocks)
      {
         if (next.isMorning( ))
            return true;
      }
      return false;
   }

     
}
           
