import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
public class HuffObject implements Serializable
{
  byte[] data;
  Node root;
  public byte[] decode()
  {
    List<Integer> list = Twiddle.bytesToBits(data);
    ArrayList<Byte> list2=new ArrayList<Byte>();
    Node tmp = root;
    for(int i =0;i<list.size();i++)
    {
        if(list.get(i).intValue()==1)
        {
          if(tmp.right!=null)
          {
            tmp = tmp.right;
          }
          else
          {
            tmp = root;
          }
        }
        else
        {
          if(tmp.left!=null)
          {
            tmp = tmp.left;
          }
          else
          {
            tmp = root;
          }
        }
        if(tmp.leaf)
        {
        list2.add(tmp.data);
        tmp = root;
        }
    }
    byte[] g = new byte[list2.size()];
    int holder=0;
    for(Byte b:list2)
    {
      g[holder++]=b.byteValue();
    }
    return g;
}
}
