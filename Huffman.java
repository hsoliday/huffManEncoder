import java.util.ArrayList;
import java.util.PriorityQueue;
public class Huffman
{
  public ArrayList<Data> huffmanCodes;
  public Node R;
  public Huffman()
  {
    huffmanCodes = new ArrayList<Data>();
  }
  public void huffmanC(byte[] byteArr)
  {
    int[] tmp = new int[256];
    for(int i = 0;i<tmp.length;i++)
    {
      tmp[i]=0;
    }
    for(byte b:byteArr)
    {
      tmp[b]++;
    }
    PriorityQueue<Node> pq =new PriorityQueue<Node>();
    for(int i = 0;i<tmp.length;i++)
    {
      if(tmp[i]!=0)
      {
        Node n = new Node();
        n.leaf =true;
        n.value = tmp[i];
        n.data = (byte)i;
        pq.add(n);
      }
    }
    while(pq.size()>1)
    {
      Node n1= pq.poll();
      Node n2= pq.poll();
      Node n3 = new Node();
      n3.value = n1.value+n2.value;
      n3.left = n1;
      n3.right = n2;
      pq.add(n3);
    }
    Node root= pq.poll();
    R=root;
    breadthFirstSearch(root,"");
  }
  public void breadthFirstSearch(Node n, String s)
  {
    if(!n.leaf)
    {
      breadthFirstSearch(n.left,s+"0");
      breadthFirstSearch(n.right,s+"1");
    }
    else
    {
      Data d = new Data(s,n.data);
      huffmanCodes.add(d);
    }

  }
}
