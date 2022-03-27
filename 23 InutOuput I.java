Input/Output

- An I/O stream represents an abstraction of input source or an output destination. 
    OR
  A stream is an abstraction that is used in order to produce or consume data.
- A stream can represent many different kinds of sources and destinations, including disk files, devices, other programs,and memory                arrays.
- Streams support many different kinds of data, including simple bytes, primitive data types, localized characters, and objects. 
- Some streams simply pass on data; others manipulate and transform the data in useful ways.
- A program uses an input stream to read data from a source, , one item at time
- A program uses an output stream to write data to a destination, one item at time .
- The java.io package provides over 60 input/output classes (streams) 
- Streams are combined (piped together) to create a desired data source or sink
- Streams are either byte-oriented or character-oriented
- An IOException may occur during any I/O operation
....................................................................................

Java Support Two Major Types of Streams

1- Byte Stream 

2- Character Stream


...............................................................................................................
Byte Stream

- Programs use byte streams to perform input and output of 8-bit bytes. 
- All byte stream classes are descended from InputStream and OutputStream.

- There are many Byte stream classes in java. Most basic Classes are

1-FileInputStream  (read)

2-FileOutputStream  (Write)
.....................................................................................................................
Example  write Data into file

class Test {
 public static void main(String[] args) throws IOException {
  String Data="Some Data";
  FileOutputStream out= new FileOutputStream("c.txt");
  out.write(Data.getBytes());
  out.close();
}
}
..........................................................................
Example  Read Data from file

class Test {
 public static void main(String[] args) throws IOException {
  FileInputStream in= new FileInputStream("c.txt");
  int i;
  while ((i=in.read())!=-1)
      System.out.print((char)i);   
  in.close();
}
}
............................................................................
Example Read from and write into File (Bytes Stream)

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class Test {

  public static void main(String[] args) throws IOException {
    FileInputStream in = null;
    FileOutputStream out = null;
    try {
        in = new FileInputStream("xanadu.txt");
        out = new FileOutputStream("outagain.txt");
        int c;
        while ((c = in.read()) != -1) {
          out.write(c);
        }
    } finally {
        if (in != null) {
          in.close();
        }
        if (out != null) {
          out.close();
        }
    }
  }
}
.....................................................................
Example Append data 


import java.io.*;
class MyFile {
MyFile (String m) throws Exception{
   msg=m;
   out= new FileOutputStream("c:/temp/Abc.txt",true);
   out.write(msg.getBytes());
   out.close();
   
}
FileOutputStream out;
String msg;
}

class Test {
public static void main(String o[]) throws Exception{
new MyFile(o[0]);

}
}
.................................................................
Example  Read whole file once

import java.io.*;
class MyFile {
MyFile () throws Exception{
try { 
    f= new File("c:/temp/Abc.txt");  
    in= new FileInputStream(f);
    } catch (Exception e ){
       System.out.print("File Does Not Exist ");
       System.exit(0);
   }
    byte a[]= new byte[(int)f.length()];
   in.read(a);
    for (int i=0;i<a.length;i++)
       System.out.print((char)a[i]);          
}
File f;
FileInputStream in;
}
class Test {
public static void main(String o[]) throws Exception{
new MyFile();

}
}
.....................................................................
Rule of Thumb:   Always Close Streams
....................................................................
When to use Byte Streams
-Byte streams should only be used for the most primitive I/O.
....................................................................

Character Stream

-All character stream classes are descended from Reader and Writer.
-character stream classes specialized in file I/O: FileReader and FileWriter

..........................................................................
Example: Read from and write into File (Character Streams)

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Test {

  public static void main(String[] args) throws IOException {

    FileReader inputStream = null;
    FileWriter outputStream = null;

    try {
        inputStream = new FileReader("xanadu.txt");
        outputStream = new FileWriter("characteroutput.txt");

        int c;
        while ((c = inputStream.read()) != -1) {
          outputStream.write(c);
        }
    } finally {
        if (inputStream != null) {
          inputStream.close();
        }
        if (outputStream != null) {
          outputStream.close();
        }
    }
  }

}
................................................................................
File Writer

Constructors
 -    FileWriter(String filename)/FileWriter(File file)
        Creates a output stream using the default encoding
 -    FileWriter(String filename, boolean append)
     Creates a new output stream or appends to the existing output stream (append = true)


Useful Methods

write(String str)/write(char[] buffer)

Writes string or array of chars to the file

write(int char)

Writes a character (int) to the file

flush() Writes any buffered characters to the file

close() Closes the file stream after performing a flush

getEncoding() Returns the character encoding used by the file stream

....................................................................................
Example: write character data into File (FileWrite Streams) and getEncoding Scheme

import java.io.*;

class Test {
  public static void main(String[] args) {
    FileWriter out = null;
    
    try {
      out = new FileWriter("ucp.txt");
      System.out.println("Encoding: " + out.getEncoding());
      out.write("University of Central Punjab");
      out.close();
      out = null;
    } catch(IOException ioe) {
      System.out.println("IO problem: " + ioe);
      ioe.printStackTrace(); 
      try {
        if (out != null) {
          out.close();
        }
      } catch(IOException ioe2) { }
    }
  }
}

Output

c:\>java Text
   Encoding: Cp1252

c:\> type ucp.txt
     University of Central Punjab

Note:  Cp1252 is Windows Western Europe / Latin-1
......................................................................................
Character Encoding Scheme

- refers to the process of representing information in some form

- To change the system default encoding use
     System.setProperty("file.encoding", "encoding");

- To specify the encoding when creating the output stream, use an OutputStreamWriter
  OutputStreamWriter out = 
     new OutputStreamWriter(
       new FileOutputStream("ucp.txt", "8859_1"));

.....................................................................................
File Reader (Constructor and UseFullMethods)

Constructors

 - FileReader(String filename)/FileReader(File file)

   Creates a input stream using the default encoding

Useful Methods

-read/read(char[] buffer) 
 Reads a single character or array of characters Returns –1 if the end of the steam is reached

- reset() Moves to beginning of stream (file)

- skip()Advances the number of characters

Note:  Wrap a BufferedReader around the FileReader to read full lines of text using readLine
...................................................................................
Example : Read whole file without LOOP (File Reader) using Character Buffer

import java.io.*;
class Test {
  public static void main(String[] args) {
    File file = new File("ucp.txt");
    FileReader in = null;
    if(file.exists()) {
      try {
        in = new FileReader(file);    
        System.out.println("Encoding: " + in.getEncoding());
        char[] buffer = new char[(int)file.length()];
        in.read(buffer);
        System.out.println(buffer);
        in.close();
      } catch(IOException ioe) {
        System.out.println("IO problem: " + ioe);
        ioe.printStackTrace(); 
      }
    }
  }
}

output
c:\> java Test

Encoding: Cp1252
University of Central Punjab
.......................................................................................
Alternatively, could read file one line at a time:

BufferedReader in = new BufferedReader(new FileReader(file));    
   String lineIn;
   while ((lineIn = in.readLine()) != null) {
     System.out.println(lineIn);
   }
...................................................................................
Character Streams That Use Byte Streams

- Character streams are often "wrappers" for byte streams. 

- The character stream uses the byte stream to perform the physical I/O, while the 
  character stream handles translation between characters and bytes.

- FileReader, for example, uses FileInputStream, while FileWriter uses FileOutputStream.

- There are two general-purpose byte-to-character "bridge" streams: InputStream-Reader and OutputStreamWriter.

- Use them to create character streams when there are no prepackaged character stream classes that meet your needs. 

- For an example that creates character streams from network byte streams, refer to the online sockets lesson.

......................................................................................................................

Buffered Streams

- Normally without buffering I/o directly send to the underlying system.
- This means that each read or write request is handled directly by the underlying OS. 
- This can make a program much less efficient
- each  request often triggers disk access, network activity, or some other operation that is relatively expensive.
- To reduce this kind of overhead, the Java platform implements buffered I/O streams. 
- Buffered input streams read data from a memory area known as a buffer; the native 
  input API is called only when the buffer is empty. 
- Similarly, buffered output streams write data to a buffer, and the native 
  output API is called only when the buffer is full.

inputStream =  new BufferedReader(new FileReader("xanadu.txt"));


outputStream =  new BufferedWriter(new FileWriter("characteroutput.txt"));


.......................................................................................................
Line-Oriented I/O

- Character I/O usually occurs in bigger units than single characters.
- One common unit is the line: a string of characters with a line terminator at the end.
- A line terminator can be a carriage-return/line-feed sequence ("\r\n"), 
  a single carriage-return ("\r"), or a single line-feed ("\n"). 
- Supporting all possible line terminators allows programs to read textfiles created
 on any of the widely used operating systems.
.........................................................................................................
Example Read from and write into File (BufferedReader and Printwriter Stream)

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

public class Test {

  public static void main(String[] args) throws IOException {
    BufferedReader inputStream = null;
    PrintWriter outputStream = null;

    try {
        inputStream =
	  new BufferedReader(new FileReader("xanadu.txt"));
	outputStream =
	  new PrintWriter(new
			  FileWriter("characteroutput.txt"));
	String l;
	while ((l = inputStream.readLine()) != null) {
	  outputStream.println(l);
        }
    } finally {
        if (inputStream != null) {
          inputStream.close();
        }
        if (outputStream != null) {
          outputStream.close();
        }
    }
  }

}
..............................................................................................
Flushing Buffered Streams
- It often makes sense to write out a buffer at critical points, without waiting for it to fill.
- known as flushing the buffer.
..............................................................................................
Exampe    Console Input using BufferedReader

import java.io.*;

class Test{
  public static void main(String[] args) {
    BufferedReader keyboard;
    String line;  
    try {
      System.out.print("Enter value: ");
      System.out.flush();
      keyboard = new BufferedReader(
                  new InputStreamReader(System.in));
      line = keyboard.readLine();
    } catch(IOException e) {
      System.out.println("Error reading input!");  }
    }
  }
}

...................................................................................................
Scanning
- Objects of type Scanner are useful for breaking down formatted input into tokens 
  and translating individual tokens according to their data types.

Example  Read and Tokenize file using Scanner Class

import java.io.*;
import java.util.Scanner;

class Test {

  public static void main(String[] args) throws IOException {
    Scanner s = null;

    try {
      s = new Scanner(new BufferedReader(new
                      FileReader("xanadu.txt")));

      while (s.hasNext()) {
        System.out.println( s.next()  );
      }
    } finally {
        if (s != null) {
          s.close();
        }
    }
  }
}

Output

In
Xanadu
did
Kubla
Khan
A
stately
pleasure-dome
....

...................................................................................................
Regular Expressions in Scanner Class

-invoke useDelimiter() for specifying a regular expression.
- For example, suppose you wanted the token separator to be a comma, optionally followed by white space.
 You would invoke: s.useDelimiter(",\\s*");

Example  , used as token Seprator

import java.io.*;
import java.util.Scanner;

class Test {

  public static void main(String[] args) throws IOException {
    Scanner s = null;

    try {
      s = new Scanner(new BufferedReader(new
                      FileReader("xanadu.txt")));
      s.useDelimiter(",\\s*");
      while (s.hasNext()) {
        System.out.println(s.next());
      }
    } finally {
        if (s != null) {
          s.close();
        }
    }
  }
}

....................................................................................................
Translating Individual Tokens
-Scanner also supports tokens for all of the Java language's primitive types 
(except for char), as well as BigInteger and BigDecimal.
- Also, numeric values can use thousands separators.
-in a US locale, Scanner correctly reads the string "32,767" as representing an integer value.

EXample  Tokenize string class based on double Literals

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;

class Test {
  public static void main(String[] args) throws IOException {
    Scanner s = null;
    double sum = 0;
    try {
      s = new Scanner(new
            BufferedReader(new FileReader("usnumbers.txt")));
      s.useLocale(Locale.US);

      while (s.hasNext()) {
        if (s.hasNextDouble()) {
              sum =sum+  s.nextDouble();
          } else {
              s.next();
          }
      }
    } finally {
        s.close();
    }
    System.out.println("sum="+sum);
  }
}

.............................................................................
Input File usnumbers.txt
8.5
32,767
3.14159
1,000,000.1

output 
1032778.74159
.................................................................................
Object Input/Output Streams  (for Object Persistence)

- Saving object on non-Volatile storage are called persistence

- whole object can be streamed

- Object class should implement serializable Methods

- Serialization is a method of sending objects onto streams by value

- Create Binary File of Objects
...............................................................................
Example:  Write student objects on Disk

import java.io.*;
class Student implements Serializable{
 private String name;
 public Student() { }
 public Student(String n) {name=n;}
 public String getName(){return name;}
 public void setName(String n){name=n;}
 }
 class MyFile{
 MyFile(String n) {fname=n;
 try{
    out = new ObjectOutputStream(new
        BufferedOutputStream(new FileOutputStream(fname)));
    }catch (Exception e ) 
       {System.out.println(e);}
}
void write(Student p){
  try{
   out.writeObject(p);
  }catch (Exception e ) 
     {System.out.println(e);}
}
void close(){
  try{
     out.close();
     }catch (Exception e ) 
        {System.out.println(e);}
}
private ObjectOutputStream out;
private String fname;
}
class Test {
public static void main(String o[]){
 MyFile file= new MyFile("Student.dat");
 Student s1=new Student("Shoaib");
 Student s2=new Student("Farooq");
 file.write(s1);
 file.write(s2);
 file.close();
}
}

Note : Binary file can not be seen correctly in text editor
.............................................................................
Example:  read student objects from Disk

import java.io.*;
class Student implements Serializable{
private String name;
public Student() { }
public Student(String n) {name=n;}
public String getName(){return name;}
public void setName(String n){name=n;}
}
class MyFile{
MyFile(String n) {fname=n;
try {
in = new ObjectInputStream(new
        BufferedInputStream(new FileInputStream(fname)));
} catch (IOException e)
     {System.out.println(e);}
}
Student read(){
 Student st=null;
 try {
     st=(Student)in.readObject();
     return st;
     }catch (Exception e)
     {System.out.println(e);}
  return st;       
}
void close() {
try {
  in.close();
   }catch (IOException e)
     {System.out.println(e);}
}
private ObjectInputStream in;
private String fname;
}
class Test {
public static void main(String o[]){
MyFile file= new MyFile("Student.dat");
Student s1=file.read();
Student s2=file.read();
System.out.println(s1.getName());
System.out.println(s2.getName());
file.close();
}
}
.......................................................................................
Example:  read student objects from Disk ( read All Objects One  by one)

import java.io.*;

class Student implements Serializable{
 private String name;
 public Student() { }
 public Student(String n) {name=n;}
 public String getName(){return name;}
 public void setName(String n){name=n;}
 }

class MyFile{
 MyFile(String n) {fname=n;
 try {
   in = new ObjectInputStream(new
        BufferedInputStream(new FileInputStream(fname)));
 } catch (IOException e)
     {System.out.println(e);}
}
Student read(){
 Student st=null;
 try {
     st=(Student)in.readObject();
     return st;
     }catch (Exception e)
     {System.out.println(e);}
  return st;       
}
void readAll(){
 Student st=null;
 
 try {
     while((st=(Student)in.readObject())!=null)
         System.out.println(st.getName());
     }catch (Exception e)
        {return;}
}
void open() {
try {
  in = new ObjectInputStream(new
        BufferedInputStream(new FileInputStream(fname)));
   }catch (IOException e)
     {System.out.println(e);}
}
void close() {
try {
  in.close();
   }catch (IOException e)
     {System.out.println(e);}
}
private ObjectInputStream in;
private String fname;
}
class Test {
public static void main(String o[]){
MyFile file= new MyFile("Student.dat");
System.out.println(".....Printing All Objects.....");
file.readAll();
System.out.println(".....End of Printing All Objects.....");
file.close();
file.open();
System.out.println(".....Read One by One.....");
Student s1=file.read();
Student s2=file.read();
System.out.println(s1.getName());
System.out.println(s2.getName());
System.out.println(".....End of Read One by One.....");
file.close();
}
}
..............................................................................
Example:  Write student objects on Disk using Java Frame

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
class Student implements Serializable{
private String id;
private String name;
private String cgpa;
public Student() { }
public Student(String d,String n,String cg) {id=d;name=n;cgpa=cg;}
public String getName(){return name;}
public void setName(String n){name=n;}
public String getId(){return id;}
public void setId(String d){id=d;}
public String getCgpa(){return cgpa;}
public void setCgpa(String cg){cgpa=cg;}

}
class MyFrame extends JFrame implements ActionListener{
MyFrame() {
file=new MyFile("student.dat");
setLayout(new FlowLayout());
l1= new Label("UCPId:");
l2= new Label("Name:");
l3= new Label("CGPA:");
l4= new Label("                  ");
id=new TextField(20);
name=new TextField(20);
cgpa=new TextField(20);
b= new Button("Save");
b.addActionListener(this);
add(l1);add(id);add(l2);add(name);
add(l3);add(cgpa);add(b);add(l4);
setSize(260,150);
setVisible(true);
}
public void actionPerformed(ActionEvent e ) {
String ids=id.getText();;
String names=name.getText();
String cgpas=cgpa.getText();
if (!ids.matches(RE[0])){
    l4.setText("Id Incorrect");
    return;
}
if (!names.matches(RE[1])){
    l4.setText("name Incorrect");
    return;
}
if (!cgpas.matches(RE[2])){
    l4.setText("cgpa Incorrect");
    return;
}
Student st=new Student(ids,names,cgpas);
file.write(st);
l4.setText("Saved");

}
String RE[]={"[Ll][1-9][FRSfrs][0-9][0-9](?i)((bscs)|(bba)|(mba)|(mscs))[0-9][0-9][0-9][0-9]",
		"[a-zA-Z ]+","(([0-3]\\.[0-9][0-9]?)|(4\\.0?))"};
Label l1,l2,l3,l4;
TextField id;
TextField name;
TextField cgpa;
Button b;
MyFile file;
}

class MyFile{
 MyFile(String n) {fname=n;
 try{
     out = new ObjectOutputStream(new
        BufferedOutputStream(new FileOutputStream(fname)));
    }catch (Exception e ) 
     {System.out.println(e);}
}
void write(Student p){
  try{
     out.writeObject(p);
  }catch (Exception e ) 
     {System.out.println(e);}
}
void close(){
 try{
    out.close();
    }catch (Exception e ) 
       {System.out.println(e);}
}
 private ObjectOutputStream out;
 private String fname;
}

class Test{
 public static void main(String o[]){
     MyFrame mf=new MyFrame();
 }
}

..........................................................................

Example : Store different type objects into single File

import java.io.*;
import java.math.BigDecimal;
import java.util.Calendar;

public class ObjectStreams {

  static final String dataFile = "invoicedata";

  static final BigDecimal[] prices = {
    new BigDecimal("19.99"),
    new BigDecimal("9.99"),
    new BigDecimal("15.99"),
    new BigDecimal("3.99"),
    new BigDecimal("4.99") };

  static final int[] units = { 12, 8, 13, 29, 50 };

  static final String[] descs = {
    "Java T-shirt",
    "Java Mug",
    "Duke Juggling Dolls",
    "Java Pin",
    "Java Key Chain" };

  public static void main(String[] args)
    throws IOException, ClassNotFoundException {

    ObjectOutputStream out = null;
    try {
      out = new ObjectOutputStream(new
        BufferedOutputStream(new FileOutputStream(dataFile)));

        out.writeObject(Calendar.getInstance());
        for (int i = 0; i < prices.length; i ++) {
          out.writeObject(prices[i]);
          out.writeInt(units[i]);
          out.writeUTF(descs[i]);
        }
      } finally {
        out.close();
      }

      ObjectInputStream in = null;
      try {
        in = new ObjectInputStream(new
          BufferedInputStream(new FileInputStream(dataFile)));

        Calendar date = null;
        BigDecimal price;
        int unit;
        String desc;

      BigDecimal total = new BigDecimal(0);

      date = (Calendar) in.readObject();

      System.out.format ("On %tA, %<tB %<te, %<tY:%n", date);

      try {
        while (true) {
          price = (BigDecimal) in.readObject();
          unit = in.readInt();
          desc = in.readUTF();
          System.out.format("You ordered %d units of %s " +
                            "at $%.2f%n", unit, desc, price);
          total = total.add(price.multiply(new
                                           BigDecimal(unit)));
        }
      } catch (EOFException e) {}
      System.out.format("For a TOTAL of: $%.2f%n", total);
    } finally {
        in.close();
    }
  }
}

......................................................................................
Same object written twice will get Same Object retrieved twice


Object ob = new Object();
out.writeObject(ob);
out.writeObject(ob);



Each writeObject has to be matched by a readObject, so the code that 
 reads the stream back will look something like this:

Object ob1 = in.readObject();
Object ob2 = in.readObject();



This results in two variables, ob1 and ob2, that are references to a single object.

..........................................................................................
Example: Read urdu from text File 


import java.awt.*;
import javax.swing.*;
import java.io.*;
class MyFrame{
  MyFrame() {
try {
 JFrame f = new JFrame("UCP");
 String s1;
 String s2="";
 TextArea ta=new TextArea();
 FileInputStream fis = new FileInputStream("c:\\java\\a.txt");
 BufferedReader br = new BufferedReader
                     (new InputStreamReader(fis, "UTF-8"));
 while( (s1 = br.readLine()) != null){
    s2 = s2+s1;
 }
  ta.setText(s2);
  f.add(ta); 
  f.setSize(400,400);
  f.setVisible(true);
 }catch (Exception e ) {System.out.println(e); }
 }
}
class Test{
public static void main(String a[]){
    MyFrame frame= new MyFrame();
  
}
}


....................................................................................
If your Source file contains Extended Characters

javac  -encoding UTF-8  Test.java















