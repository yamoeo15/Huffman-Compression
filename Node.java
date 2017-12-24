/**
 * Created by yamoeo15 on 11/5/2017.
 */
class Node implements Comparable<Node> {

     char ch = ' ';
     int freq = 0;
      Node left;
      Node right;

Node() {}


    //boolean isLeaf = false;
    //private int huffmanLen = 0;
    Node(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    Node(char ch, int freq, Node left, Node right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        assert ((left == null) && (right == null)) || ((left != null) && (right != null));
        return (left == null) && (right == null);
    }

    public int compareTo(Node that) {
        return this.freq - that.freq;
    }
}


