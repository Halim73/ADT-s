/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author halim
 */
import java.util.Random;
import java.util.*;
public class SkiplistSSet<E>{
    private Node sentinel;
    private int numElem;
    private int height;
    private Random rand;
  
    public SkiplistSSet(){
        sentinel = new Node();
        numElem = 0;
        height = 0;
        rand = new Random();
    }
    public E find(E value){
        Node node = findPred(value);
        return node.next[0] == null ? null: (E) node.next[0].value;
    }
    boolean add(E value){
        Node curr = sentinel;
        ArrayStack<Node> stack = new ArrayStack<>();
        int currHeight = height;
        int compare = 0;
        while(currHeight >= 0){
            while(curr.next[currHeight] != null && curr.next[currHeight].value.compareTo(curr.value) < 0){
                curr = curr.next[currHeight];
            }
            if(curr.next[currHeight] != null && compare == 0){
                return false;
            }
            stack.set(currHeight--,curr);
        }
        Node newNode = new Node(value,pickHeight());
        while(height < newNode.height){
            stack.set(++height,sentinel);
        }
        for(int i=0;i<newNode.height;i++){
            newNode.next[i] = stack.get(i).next[i];
            stack.get(i).next[i] = newNode;
        }
        numElem++;
        return true;
    }
    public boolean remove(E value){
        boolean removed =  false;
        Node curr = sentinel;
        int currHeight = height;
        int compare = 0;
        while(currHeight >= 0){
            while(curr.next[currHeight] != null && curr.next[currHeight].value.compareTo(curr.value) < 0){
                curr = curr.next[currHeight];
            }
        }
        if(curr.next[currHeight] != null && compare == 0){
            removed = true;
            curr.next[currHeight] = curr.next[currHeight].next[currHeight];
            
            if(curr == sentinel && curr.next[currHeight] == null){
                height--;
            }
            //rand--;
            numElem--;
            return removed;
        }
        return removed;
    }
    private int pickHeight(){
        int sideOne = 0;
        int sideTwo = 1;
        int random = rand.nextInt(Integer.MAX_VALUE);
        while((random & sideTwo) != 0){
            sideOne++;
            sideTwo <<= 1;
        }
        return sideOne;
    }
    public int size(){
        return numElem;
    }
    public Node findPred(E value){
        Node curr = sentinel;
        int currHeight = height;
        while(currHeight >= 0){
            while(curr.next[currHeight] != null && curr.next[currHeight].value.compareTo(curr.value) < 0){
                curr = curr.next[currHeight];
                currHeight--;
            }
        }
        return curr;
    }
    protected class Node<E>{
        E value;
        int height;
        Node[]next;
        
        public Node(E value,int height){
            this.value = value;
            this.height = height;
            next = new Node[10];
        }
        public Node(E value){
            this(value,0);
        }
        public Node(){
            this(null,0);
        }
    }
}
