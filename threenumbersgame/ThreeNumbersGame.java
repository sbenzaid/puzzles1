/*
 * Three Numbers Game
 *
 * Problem statement: http://devdraft.com/challenges/2014/q4/numbersgame
 */

import java.util.*;
import java.io.*;

public class ThreeNumbersGame {
    
    public class Node {
        final int a;
        final int b;
        final int c;
        
        final int depth;
        
        boolean loser;
        
        Set<Node> children = null;
        
        public Node(int a, int b, int c, int depth) {
            this.a = a;
            this.b = b;
            this.c = c;
            
            this.depth = depth;
            
            populateChildren();
        }
        
        public void populateChildren() {
            children = new HashSet<Node>();
            
            if (a != ((b + c) / 2)) {
                children.add(new Node((b + c) / 2, b, c, depth + 1));
            }
            if (b != ((a + c) / 2)) {
                children.add(new Node((a + c) / 2, a, c, depth + 1));
            }
            if (c != ((a + b) / 2)) {
                children.add(new Node((a + c) / 2, a, b, depth + 1));
            }
            
            if (isLeaf()) {
                loser = true;
            } else if ((depth % 2) == 0) {
                loser = true;
                for (Node n : children) {
                    if (n.isLoser()) {
                        loser = false;
                        break;
                    }
                }
            } else {
                loser = false;
                for (Node n : children) {
                    if (!(n.isLoser())) {
                        loser = true;
                        break;
                    }
                }
            }
        }
        
        public boolean isLeaf() {
            return (children.size() == 0);
        }
        
        public boolean isLoser() {
            return loser;
        }
    }
    
    public void processTestCase(String nums) {
        String[] vals = nums.split(" ");
        Node root = new Node(Integer.parseInt(vals[0]), Integer.parseInt(vals[1]), Integer.parseInt(vals[2]), 0);
        if (root.isLoser()) {
            System.out.println("Second");
        } else {
            System.out.println("First");
        }
    }

    public static void main(String[] args) {
        Scanner inFile = null;
        try {
            inFile = new Scanner(new File(args[0]));
        } catch (IOException e) {
            System.out.println("Error reading file: " + args[0]);
            System.exit(0);
        }
        
        int numCases = Integer.parseInt(inFile.nextLine());

        ThreeNumbersGame tng = new ThreeNumbersGame();
        
        while (inFile.hasNextLine()) {
            tng.processTestCase(inFile.nextLine());
        }
        
        inFile.close();
    }
}
