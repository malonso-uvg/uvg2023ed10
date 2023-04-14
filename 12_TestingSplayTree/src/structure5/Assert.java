// Routines used for testing assertions.
// (c) 2001 duane a. bailey

package structure5;

/**
 * A library of assertion testing and debugging procedures.
 * <p>
 * This class of static methods provides basic assertion testing facilities.
 * An assertion is a condition that is expected to be true at a certain
 * point in the code.  Each of the assertion-based routines in this class
 * perform a verification of the condition, and do nothing (aside from
 * testing side-effects) if the condition holds.  If the condition fails,
 * however, the assertion throws an exception and prints the associated
 * message, that describes the condition that failed.  Basic support is 
 * provided for testing general conditions, and pre- and postconditions.
 * There is also a facility for throwing a failed condition for code that
 * should not be executed.
 * <p>
 * Features similar to assertion testing are expected to be incorporated
 * into the Java 2 language beginning in SDK 1.4.
 * <p>
 * The debugging facilities provide control that is slightly improved
 * over print statements.
 *
 * @author, 2001 duane a. bailey
 * @version $Id: Assert.java 22 2006-08-21 19:27:26Z bailey $
 * @since Java Structures, 1st edition
 */
public class Assert
{
    /**
     * The current level of debugging; generally 0 upward.
     * A level of 0 is generally considered off.
     */
    protected static int debugLevel = 0;
    /**
     * @pre An Assert cannot be constructed
     */
    private Assert()
    {
        Assert.fail("Attempt to construct an Assert!?");
    }

    /**
     * Increase the verbosity of the debugging messages.
     *
     * @post debugging level is increased
     */
    public static void debugging()
    {
        debugLevel++;
    }

    /**
     * Explictly set the debugging level (0 = none)
     *
     * @param level the desired level of verosity
     * @pre level >= 0
     * @post the level of debugging is set to <code>level</code>
     * @return the old level
     */
    public static int debugLevel(int level)
    {
        int oldLevel = debugLevel;
        debugLevel = level;
        return oldLevel;
    }

    /**
     * Set up a level 1 debugging message.
     *
     * @param message a string to be printed if debugging level is 1 or more
     * @post prints message if the debugging level is 1 or more
     */
    public static void debug(String message)
    {
        debug(1,message);
    }

    /**
     * Set up a debugging message at a specific level.
     *
     * @param level the level that triggers the printing of message
     * @param message the message to be printed at the desired level
     * @pre level >= 1 and message is non-null
     * @post prints message if debugging level is <code>level</code> or more
     */
    public static void debug(int level,String message)
    {
        if (level <= debugLevel) System.err.println(message);
    }

    /**
     * Test a precondition.  If the assertion fails the message
     * indicates that a precondition failed.  A precondition is something
     * you require to be true for the method to be executed correctly.
     *
     * @param test A boolean expression describing precondition.
     * @param message A string describing precondition.
     * @pre Result of precondition test
     * @post Does nothing if test true, otherwise abort w/message
     */
    static public void pre(boolean test, String message)
    {
        if (test == false) throw new FailedPrecondition(message);
    }

    /**
     * Test a postcondition.  If the assertion fails, the message
     * indicates that a postcondition failed.  A postcondition is
     * something expected to be true after a method invocation,
     * provided the preconditions are met.
     *
     * @param test A boolean expression describing postcondition.
     * @param message A string describing postcondition.
     * @pre Result of postcondition test
     * @post Does nothing if test true, otherwise abort w/message
     */
    static public void post(boolean test, String message)
    {
        if (test == false) {
            throw new FailedPostcondition(message);
        }
    }

    /**
     * Test a general condition.  If the assertion fails, the message
     * indicates that a general condition failed.  The condition may
     * be anything that needs to be verified during the course of program
     * execution.
     *
     * @param test A boolean expression describing the condition.
     * @param message A string describing the condition.
     * @pre result of general condition test
     * @post does nothing if test true, otherwise abort w/message
     */
    static public void condition(boolean test, String message)
    {
        if (test == false) throw new FailedAssertion(message);
    }

    /**
     * Test a loop invariant.  If the assertion fails, the message
     * indicates that an invariant failed.  The condition may
     * be anything that needs to be verified during the course of program
     * execution.
     *
     * @param test A boolean expression describing the condition.
     * @param message A string describing the condition.
     * @pre result of an invariant test
     * @post does nothing if test true, otherwise abort w/message
     */
    static public void invariant(boolean test, String message)
    {
        if (test == false) throw new FailedInvariant(message);
    }

    /**
     * Indicate certain failure.  Stops the program with a message
     * indicating why failure occurred.
     *
     * @param message A string describing the reason for failure.
     * @post Throws error with message
     */
    static public void fail(String message)
    {
        throw new FailedAssertion(message);
    }
}

