// A stream for reading basic types from input.
// (c) 1998, 2001 duane a. bailey

package structure5;
import java.io.*;
import java.net.URL;

/**
 * A ReadStream provides reasonable access to the typewritten
 * data on an input stream.  Usually, a ReadStream is constructed
 * with no parameters, causing the ReadStream to open access to
 * System.in.
 * <P>
 * The access methods allow one to read from the stream, much as is done
 * with Pascal.  
 * <P>
 * Typical usage:
 * <P>
 * To read everything typed in System.in into a vector we could
 * use the following:
 * <P>
 * <pre>
 * public static void main(String[] argv){
 *      ReadStream r = new {@link #ReadStream()};
 *      Vector buffer = new Vector();
 *      while(!r.{@link #eof()}){
 *          buffer.add(r.{@link #readString()});
 *      }
 *      System.out.println(buffer);
 *      System.out.println(buffer.size());
 *   }
 * }
 * </pre>
 * 
 * @version $Id: ReadStream.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
public class ReadStream extends FilterInputStream 
{
    /**
     * The underlying data stream.      
     */
    protected DataInputStream strm;
    /**
     * True iff we've seen the end-of-file
     */
    protected boolean atEOF;            // are we at the end-of-file
    /**
     * The buffer to hold pushback characters
     */
    protected char buffer[];            // pushback buffer
    /**
     * The number of characters to be stored in buffer.
     */
    protected int buffersize;           // current size of pushback buffer      
    /**
     */
    protected int buffertop;            // top element of pushback stack

    /**
     * Whether or not accept the CR as part of previous newline.
     */
    protected boolean absorbNL = false; // absorb NL if next (part of cr)

    /**
     * Construct an empty ReadStream, obtaining info from System.in.
     *
     * @post constructs a pascal-like stream based on System.in
     */
    public ReadStream()
    {
        this(System.in);
    }

    /**
     * Construct a ReadStream based on pre-existing input stream.
     *
     * @pre strm is a valid input stream
     * @post constructs a pascal-like stream based on strm
     * 
     * @param strm The pre-existing input stream.
     */
    public ReadStream(InputStream strm)
    {
  
        // This stream filters input from a data input stream
        // which filters input from strm
        super(new DataInputStream(strm));
        this.strm = (DataInputStream)in;
        atEOF = false;
        buffer = new char[8];
        buffersize = 8;
        buffertop = -1;
    }


    /**
     * Determine if we've seen end-of-file.
     *
     * @pre are we at the end-of-file?
     * 
     * @return True if the next character to be read is EOF.
     */
    public boolean eof()
    {
        // have we already detected EOF?
        if (atEOF) return true;
        // check stream by attempting a read
        getFirst();
        return atEOF;
    }

    private static boolean isWhite(char c)
    {
        return Character.isWhitespace(c); /* JDK 1.1 */
    }

    /**
     * Read (but don't consume) next char in stream.
     * @post returns next character in stream, without consuming it
     * 
     * @return The next character to be read.
     */
    public char getFirst()
    {
        char c = readChar();
        pushbackChar(c);
        return c;
    }

    /**
     * Return true if the next character to be read is an end-of-line mark.
     *
     * @post returns true if next stream char is an eoln char
     * 
     * @return True iff the next character is an end-of-line mark.
     */
    public boolean eoln()
    {
        char c = getFirst();
        return eof() || (c == '\n') || (c == '\r');
    }

    /**
     * Read characters up to and including the end-of-line
     * mark.
     *
     * @post reads input stream until end-of-line (\r or \n)
     */
    public void readln()
    {
        readLine();
    }

    /**
     * Consume all the white-space characters until EOF or
     * other data.
     *
     * @post input pointer is at EOF, or nonwhitespace char
     */
    public void skipWhite()
    {
        char c;
        for (c = readChar(); isWhite(c);  c = readChar());
        pushbackChar(c);
    }

    /**
     * Skip white space and read in the next non-whitespace word
     * as a string.
     *
     * @post reads next word as a string
     * 
     * @return The next word on the input.
     */
    public String readString()
    {
        char buffer[] = new char[512];
        char c = 0;
        int count = 0;

        skipWhite();
        while (!eof())
        {
            c = readChar();
            if (isWhite(c))
            {
                pushbackChar(c);
                break;
            }
            buffer[count++] = c;
        }
        return new String(buffer,0,count);
    }

    private boolean acceptChar(char c)
    {
        char d = readChar();
        if (Character.toLowerCase(c) ==
            Character.toLowerCase(d)) return true;
        pushbackChar(d);
        return false;
    }

    private boolean acceptWord(String s)
    {
        char c;
        skipWhite();
        for (int i = 0; i < s.length(); i++)
        {
            if (!acceptChar(s.charAt(i))) {
                for (int j = i-1; j>= 0; j--) {
                    pushbackChar(s.charAt(j));
                }
                return false;
            }
        }
        return true;
    }

    /**
     * Read the next word "true" or "false" as a boolean.
     *
     * @post returns next boolean value read from input
     * 
     * @return The value true or false, depending on input.
     */
    public boolean readBoolean()
    {
        if (acceptWord("true")) return true;
        else if (!acceptWord("false")) Assert.fail("Boolean not found on input.");
        return false;
    }

    /**
     * Read next character, whitespace or not.  Fail on eof.
     *
     * @post returns next character, or 0 for eof
     * 
     * @return The next character, or the value 0 indicating EOF.
     */
    public char readChar()
    {
        char c = (char)0;
        try {
            if (atEOF) return (char)0;
            if (buffertop >= 0) {
                c = buffer[buffertop--];
            } else {
                c = (char)strm.readByte();
            }
        }
        catch (EOFException e) {
            atEOF = true;
        }
        catch (IOException e) {
            Assert.fail("Input error free.");
        }
        finally {
            if (absorbNL && (c == '\n')) {
                absorbNL = false;
                c = readChar();
            }
            absorbNL = c == '\r';
        }
        return c;
    }

    /**
     * Return character to input stream for reading at later time.
     *
     * @post pushes back character, possibly clearing EOF;
     *       if c == 0, does nothing
     * 
     * @param c The character to push back onto input stream.
     */
    public void pushbackChar(char c)
    {
        if (c == (char)0) return;
        atEOF = false;
        buffertop++;
        if (buffertop == buffersize) {
            // buffer too small, extend it.
            char old[] = buffer;
            buffersize = buffersize*2;
            buffer = new char[buffersize];
            for (int i = 0; i < buffertop; i++)
            {
                buffer[i] = old[i];
            }
        }
        buffer[buffertop] = c;
        absorbNL = false;
    }

    /**
     * Reads the next double value from input stream.
     * Whitespace is skipped beforehand.
     * CURRENTLY NOT WORKING.
     *
     * @post reads in double value
     * 
     * @return The next double found on input.
     */
    public double readDouble()
    {
        StringBuffer sb = new StringBuffer();
        char c;
        skipWhite();
        if (acceptChar('+')) sb.append('+');
        else if (acceptChar('-')) sb.append('-');
        c = readChar();
        while (Character.isDigit(c))
        {
            sb.append(c);
            c = readChar();
        }
        pushbackChar(c);
        if (acceptChar('.')) {
            sb.append('.');
            c = readChar();
            while (Character.isDigit(c))
            {
                sb.append(c);
                c = readChar();
            }
            pushbackChar(c);
        }
        if (acceptChar('E'))
        {
            sb.append('E');
            if (acceptChar('+')) sb.append('+');
            else if (acceptChar('-')) sb.append('-');
            c = readChar();
            while (Character.isDigit(c))
            {
                sb.append(c);
                c = readChar();
            }
            pushbackChar(c);
        }
        String s = sb.toString();
        //      System.out.println("["+s+"]");
        return Double.valueOf(s).doubleValue();
    }

    /**
     * Read floating point value from input
     * (Currently not working).
     * Skips whitespace before reading.
     *
     * @post reads floating point value and returns value
     * 
     * @return Next floating point number.
     */
    public float readFloat()
    {
        return (float)readDouble();
    }

    /**
     * Read an array of bytes from input.
     *
     * @param b The array of bytes; holds result.
     */
    public void readFully(byte b[]) throws IOException
    {
        strm.readFully(b);
    }

    /**
     * Read input into byte array.
     *
     * @param b Target array of bytes.
     * @param off Offset into byte array to start reading.
     * @param len Number of bytes to be read.
     */
    public void readFully(byte b[], int off, int len)
        throws IOException
    {
        strm.readFully(b,off,len);
    }

    /**
     * Reads an integer from input stream.
     *
     * @post reads a short integer from stream
     * 
     * @return The integer read form input.
     */
    public short readShort()
    {
        return (short)readLong();
    }

    /**
     * Reads an integer from input stream.
     *
     * @post reads an integer from stream
     * 
     * @return The integer read form input.
     */
    public int readInt()
    {
        return (int)readLong();
    }

    /**
     * Read a (potentially long) input.
     * @post reads a long integer from stream
     * 
     * @return The integer read from input.
     */
    public long readLong()
    {
        boolean negate = false;
        int digitsRead = 0;
        long value = 0;
        int base = 10;
        char c;
        int d;
        skipWhite();
        if (eof()) return 0;
        for (;;)
        {
            if (eof()) break;
            c = readChar();
            if (digitsRead == 0) {
                if (c == '-') {
                    negate = true;
                    continue;
                }
            }
            if ((digitsRead == 0) && (c == '0')) {
                base = 8;
                digitsRead++;
                continue;
            }
            if ((digitsRead == 1) && (base == 8) && ((c == 'x') ||
                                                     (c == 'X'))) {
                base = 16;
                digitsRead++;
                continue;
            }
            d = c - '0';
            if ((c >= 'a') && (c <= 'f')) {
                d = c - 'a' + 10;
            } else if ((c >= 'A') && (c <= 'F')) {
                d = c - 'A' + 10;
            }
            if ((d < 0) || (d >= base)) {
                pushbackChar(c);
                break;
            }
            digitsRead++;
            value = value*base+d;
        }
        if (negate) value = -value;
        return value;
    }

    /**
     * Read the remainder of line, including end-of-line mark.
     *
     * @post reads remainder of line, returns as string
     * 
     * @return The string containing all the characters to end-of-line.
     */
    public String readLine()
    {
        StringBuffer result = new StringBuffer();
        char c;
        while (!eoln())
        {
            result.append(readChar());
        }
        readChar();
        return result.toString();
    }

    /**
     * Read unicode from input.
     *
     * @return String version of UTF character.
     */
    public String readUTF() throws IOException
    {
        return strm.readUTF();
    }
}

