package TaxTracker;

import java.io.RandomAccessFile;
import java.io.IOException;

/**
 * Creates a Random Access File to Store Student Worker Data
 *
 * @author Nick Alexander
 * @author Brandon Houser
 * @version December 3, 2013
 */
public class StudentData
{
    private RandomAccessFile students;
    private static final int STUDENT_SIZE = 160;
    private static final int NAME_SIZE = 20;
    private static final int ADDRESS_SIZE = 50;

    /**
     * Creates a StudentData Object
     */
    public StudentData()
    {
        students = null;
    }

    /**
     * Opens the Data File
     *
     * @param fn Filename
     * @throws IOException
     */
    public void open(String fn) throws IOException
    {
        students = new RandomAccessFile(fn, "rw");
    }

    /**
     * Closes the Data File
     *
     * @throws IOException
     */
    public void close() throws IOException
    {
        if (students != null)
        {
            students.close();
        }
        students = null;
    }

    /**
     * Returns the Size of the Data File
     *
     * @return Size of the Data File
     * @throws IOException
     */
    public int size() throws IOException
    {
        return (int) (students.length() / STUDENT_SIZE);
    }

    /**
     * Pads the Name String with Whitespace for Random Access
     *
     * @param inStr Name to be Padded
     * @return Name with Padding
     */
    public String padName(String inStr)
    {
        if (inStr.length() > NAME_SIZE)
        {
            return inStr.substring(0, NAME_SIZE);
        }
        else
        {
            String outStr = inStr;
            for (int i = 0; i < NAME_SIZE - inStr.length(); i++)
            {
                outStr = outStr + " ";
            }
            return outStr;
        }
    }

    /**
     * Pads the Address String with Whitespace for Random Access
     *
     * @param inStr Address to be Padded
     * @return Address with Padding
     */
    public String padAddress(String inStr)
    {
        if (inStr.length() > ADDRESS_SIZE)
        {
            return inStr.substring(0, ADDRESS_SIZE);
        }
        else
        {
            String outStr = inStr;
            for (int i = 0; i < ADDRESS_SIZE - inStr.length(); i++)
            {
                outStr = outStr + " ";
            }
            return outStr;
        }
    }

    /**
     * Reads the nth Data Point of the Random Access File
     *
     * @param n Data Point in Random Access File
     * @return Student Object at nth Point
     * @throws IOException
     */
    public Student readStudent(int n) throws IOException
    {
        n = n - 901000000;
        students.seek(n * STUDENT_SIZE);
        String name = "";
        String address = "";
        int id = students.readInt();
        for (int i = 0; i < NAME_SIZE; i++)
        {
            name = name + students.readChar();
        }
        for (int i = 0; i < ADDRESS_SIZE; i++)
        {
            address = address + students.readChar();
        }
        double hours = students.readDouble();
        double rate = students.readDouble();
        return new Student(id, name, address, hours, rate);
    }

    /**
     * Writes Student Object to the Data File
     *
     * @param inS Student Object to Write
     * @throws IOException
     */
    public void writeStudent(Student inS) throws IOException
    {
        int n = inS.getID() - 901000000;
        students.seek(n * STUDENT_SIZE);
        students.writeInt(inS.getID());
        String name = padName(inS.getName());
        for (int i = 0; i < NAME_SIZE; i++)
        {
            students.writeChar(name.charAt(i));
        }
        String address = padAddress(inS.getAddress());
        for (int i = 0; i < ADDRESS_SIZE; i++)
        {
            students.writeChar(address.charAt(i));
        }
        students.writeDouble(inS.getHoursWorked());
        students.writeDouble(inS.getHourlyRate());
    }

    /**
     * Loads the Unused Space of Student File with Blank Data
     *
     * @throws IOException
     */
    public void loadStudentFile() throws IOException
    {
        for (int i = 0; i < 1000; i++)
        {
            students.seek(i * STUDENT_SIZE);
            students.writeInt(-1);
            for (int j = 0; i < NAME_SIZE; i++)
            {
                students.writeChar(' ');
            }
            for (int j = 0; j < ADDRESS_SIZE; j++)
            {
                students.writeChar(' ');
            }
            students.writeDouble(0.0);
            students.writeDouble(0.0);
        }
    }
}
