import java.util.*;
import java.io.*;
public class Huff
{
  public static void main(String[] args)
  {
    StringBuilder ss= new StringBuilder("");
    if(args.length<1)
    {
      System.out.println("ERROR, invalid input,");
      System.out.println("USAGE: java Huff <filename>");
      return;
    }
    try
    {
      InputStream input = new FileInputStream(args[0]);
      int data = input.read();

      while(data != -1)
      {
        ss.append((char)data);
        data = input.read();
      }
      input.close();
    }
    catch(FileNotFoundException e)
    {
      System.out.println("ERROR, Specified file could not be found");
      return;
    }
    catch(IOException e)
    {
      System.out.println("Error Reading specified file");
    }
    String s = ss.toString();
    Huffman h = new Huffman();
    h.huffmanC(s.getBytes());
    List<Data> list = h.huffmanCodes;
    ArrayList<Integer> list2 = new ArrayList<Integer>();
    HashMap<Byte,String> m = new HashMap<Byte,String>();
    for(Data d : list)
    {
      m.put(d.data,d.target);
    }
    for(byte b:s.getBytes())
    {
      String output=m.get(b);
      try
      {
        for(int i =0;i<output.length();i++)
        {
          list2.add(new Integer(output.charAt(i)-'0'));
        }
      }
      catch(Exception e)
      {
        System.out.println("Error Encoding file");
        return;
      }
    }
    while(list2.size()%8!=0)
    {
      list2.add(0);
    }
    byte[] g = Twiddle.bitsToBytes(list2);
    HuffObject huffOb = new HuffObject();
    huffOb.data = g;
    huffOb.root = h.R;
    try
    {
      FileOutputStream fos = new FileOutputStream(args[0]+".huff");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(huffOb);
      oos.close();
      fos.close();
    }
    catch(IOException e)
    {
      System.out.println("Error Writing compressed file");
    }
  }
}
