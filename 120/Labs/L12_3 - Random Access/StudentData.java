/**
 * Read/Write Student data to a random file
 * 
 * @author J. Thompson
 * @version 29-Dec-12
 */
import java.io.IOException;
import java.io.RandomAccessFile;
public class StudentData
{
    private RandomAccessFile students;
    private static final int STUDENT_SIZE = 96;
    private static final int NAME_SIZE = 20;
    public StudentData()
    {
      students = null;
    }
    
    public void open(String fn) throws IOException
    {
      students = new RandomAccessFile(fn,"rw");
    }
    
    public void close() throws IOException
    {
      if (students != null)
        students .close();
      students  = null;
   }
   
    //returns number of students in file
    public int size() throws IOException
    {
      return (int)(students.length()/STUDENT_SIZE);
    }
    
    //ensure lastName, firstName are fixed length Strings
    public String padString(String inStr)
    {
     if (inStr.length() > NAME_SIZE)
       return inStr.substring(0,NAME_SIZE);
      else
       {
        String outStr = inStr;
        for(int i = 0;i < NAME_SIZE-inStr.length(); i++)
           outStr = outStr + " ";
        return outStr;
       } 
    }

    public Student readStudent(int n) throws IOException
    {
      n = n - 901000000;
      students.seek(n * STUDENT_SIZE);
      String lastName = "";
      String firstName = "";
      int studentID = students.readInt();
      for (int i=0; i < NAME_SIZE; i++)
        lastName = lastName + students.readChar();
      for (int i=0; i < NAME_SIZE; i++)
        firstName += students.readChar();
      double gpa = students.readDouble();
      int gradYear = students.readInt();
      return new Student(studentID, lastName, firstName, gpa, gradYear);
    }

    public void writeStudent(Student inS) throws IOException
    {
      int n = inS.getStudentID() - 901000000;
      students.seek(n * STUDENT_SIZE);
      students.writeInt(inS.getStudentID());
      String lastName = padString(inS.getLastName());
      for (int i=0; i < NAME_SIZE;i++)
        students.writeChar(lastName.charAt(i));
      String firstName = padString(inS.getFirstName());
      for (int i=0; i < NAME_SIZE;i++)
        students.writeChar(firstName.charAt(i));
      students.writeDouble(inS.getGPA());
      students.writeInt(inS.getGradYear());
    }
    
    public void loadStudentFile() throws IOException
    {
        for (int i = 0; i < 1000; i++)
        {
            students.seek(i * STUDENT_SIZE);
            students.writeInt(-1);
            for (int j=0; j < NAME_SIZE;j++)
                students.writeChar(' ');
            for (int j=0; j < NAME_SIZE;j++)
                students.writeChar(' ');
            students.writeDouble(0.0);
            students.writeInt(0);
        }
    }
    
}


