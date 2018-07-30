import java.io.*;
public class Puff
{
  public static void main(String[] args) {
    try
    {
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(args[0]));
      HuffObject g = (HuffObject)ois.readObject();
      String outFile = args[0];
      FileOutputStream fos = new FileOutputStream(outFile.substring(0,outFile.length()-5));
      fos.write(g.decode());
      ois.close();
      fos.close();
    }
    catch(FileNotFoundException e)
    {
      System.out.println("Specified file cold not be found");
    }
    catch(IOException e)
    {
      System.out.println("Error reading file");
    }
    catch(ClassNotFoundException e)
    {
      System.out.println("Unsupported file type");
    }

  }
}
