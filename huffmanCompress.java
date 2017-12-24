import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yamoeo15 on 11/2/2017.
 */
public class huffmanCompress extends Application {

    //Haashmap of characters pulled form input
    static  Map<Character, Integer> map = new HashMap<>();
    final AtomicInteger totalBits = new AtomicInteger();

    public static void main(String[] args) {
        Application.launch(args);                                                                                           //launch GUI application
        //PriorityQueue<Node> freqList = new PriorityQueue<>();
        //Collections.sort(freqList);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {                                                                 //start application
        String word = null;

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("txt", "*.txt"));
        fc.setTitle("Open Text File");


        File input = fc.showOpenDialog(primaryStage);

        try (BufferedReader br = new BufferedReader(new FileReader(input))) {                                                //Try / Catch for reading input line by line
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {                                                                                          //while there is input...
                sb.append(line);                                                                                            //read and append to previous string
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            word = sb.toString();                                                                                           //seperate into words
        } catch (Exception ex) {                                                                                            //catch block
            System.out.println("Error in reading File: " + ex);
            System.exit(1);
        }
        if (input == null) {                                                                                               //No input selected
            System.out.println("No file Selected");
            System.exit(1);
        }
        float charTotal = word.length();                                                                                  //characters within input
        System.out.println("Number of Characters: " + charTotal);

        for (int i = 0; i < word.length(); i++) {                                                                          //iterate word and put char into map
            char ch = word.charAt(i);
            if (!map.containsKey(ch)) {
                map.put(ch, 1);
            } else {
                int val = map.get(ch);                                                                                     //if present- increment value or count
                map.put(ch, ++val);
            }
        }

        System.out.println("Character Frequency");

        map.forEach((k,v) -> {                                                                                             //Lambda function to fix "\n" in input
         String s =k.toString();
            s.replace("\n", "\\n").replace("\t","\\t");
            System.out.printf("%s : %d\n", s,v);
        });

                                                                                                                            //build tree
        Tree huffTree = new Tree(map);
        huffTree.buildtree();

        //interpret codes
        final Map<Character,String> huffmap = huffTree.getcodes();                                                          // pull codes method from tree
        System.out.println("---------Character Codes --------------");

        huffmap.forEach((k,v) -> {
            totalBits.set(totalBits.get() + map.get(k) * v.length());
            String s = k.toString();
            s.replace("\n", "\\n").replace("\t","\\t");
            System.out.printf("%s : %s\n", s,v);

        });
System.out.print("Average Bits Per Character: " + totalBits.get()/charTotal);                                               //average bits per character

    }


}

