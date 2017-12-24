import javafx.collections.MapChangeListener;


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by yamoeo15 on 11/6/2017.
 */
public class Tree extends Node {
    private PriorityQueue<Node> nodeList = new PriorityQueue<>();                                                         //Priority Queue of Nodes
    private Node root;                                                                                                    //root
    private final Map<Character, String> codes = new HashMap<>();                                                         //Hashmap of codes

    Tree(Map<Character, Integer> charmap) {
        charmap.forEach((k, v) -> nodeList.add(new Node(k, v, null, null)));
    }


    void buildtree(){                                                                                                       //method to construct tree
        while (nodeList.size() > 1) {
            Node left = nodeList.poll();                                                                                    //pull least two frequencies from priority queue
            Node right = nodeList.poll();
            Node parent = new Node('\0', left.freq + right.freq, left, right);                                              //add two mins

            nodeList.add(parent);                                                                                           //create meta-node

        }
        root = nodeList.poll();                                                                                             //pull last node as root
    }

    final Map <Character,String> getcodes() {                                                                               //accessor function for build code function
            buildCodes(root, "");
            return codes;
        }



    private void buildCodes(Node node, String s) {                                                                          // check if leaf
        if (!node.isLeaf()) {
            buildCodes(node.left, s + '0');                                                                                 //recursively label left and right node for codes
            buildCodes(node.right, s + '1');
        } else {
            codes.put(node.ch, s);                                                                                          //read codes into Hashmap under each respective character

        }
    }
}