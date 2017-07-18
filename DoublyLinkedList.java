/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author halim
 */
public class DoublyLinkedList<E> {
    private Node dummy = new Node();
    private int size;
    
    public DoublyLinkedList(E value){
        Node head = new Node(value,dummy,dummy);
        dummy.next = dummy.prev = head;
        size = 0;
    }
    public DoublyLinkedList(){
        dummy.next = dummy.prev = dummy;
        size = 0;
    }
    public E get(int i){
        return (E)getNode(i).value;
    }
    public E set(int i,E elem){
        Node node = getNode(i);
        E temp = (E)node.value;
        node.value = elem;
        return temp;
    }
    public void add(int i, E elem){
        addBefore(getNode(i),elem);
    }
    public E remove(int i){
        Node node = getNode(i);
        E temp = (E)node.value;
        remove(node);
        return temp;
    }
    private void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }
    private Node addBefore(Node other,E value){
        Node node = new Node(value,other.prev,other);
        node.next.prev = node;
        node.prev.next = node;
        return node;
    }
    public Node getNode(int i){
        Node current = null;
        if(i < size/2){
            current = dummy.next;
            for(int j=0;j<i;j++){
                current = current.next;
            }
        }else{
            
            current = current.prev;
            for(int j=size;j>i;j--){
                current = current.prev;
            }
        }
        return current;
    }
    protected class Node<E>{
        E value;
        Node prev;
        Node next;
        
        public Node(E value,Node prev,Node next){
            this.value = value;
            this.prev = prev;
            this.next= next;
        }
        public Node(E value,Node next){
            this(value,null,next);
        }
        public Node(E value){
            this(value,null,null);
        }
        public Node(){
            this(null,null,null);
        }
    }
}
