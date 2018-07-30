import java.io.Serializable;
public class Node implements Comparable<Node>,Serializable
{
  public Node left,right;
  public boolean leaf;
  public int value;
  public byte data;
  public int compareTo(Node other)
  {
    return ((int)value)-((int)other.value);
  }
}
