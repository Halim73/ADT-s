/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author halim
 */
import java.util.*;
public class ArrayQueue<E> {
    private E[]array;
    private int front, back;
    private static final int MAX = 50;
    
    public ArrayQueue(int size){
        this.back = this.front = 0;
        this.array = (E[]) new Object[size];
    }
    public ArrayQueue(){
        this(MAX);
    }
    public E get(int i){
        return array[i];
    }
    public E remove(){
        E x = array[front];
        
        front = (front+1)%array.length;
        back--;
        
        if(array.length >= 3*back){
            resize();
        }
        return x;
    }
    public int size(){
        return back-front;
    }
    public void add(E x){
        if(back+1 > array.length){
            resize();
        }
        array[(front+back)%array.length] = x;
        back++;
    }
    private void resize(){
        E[] auxArray = (E[]) new Object[(Math.max(1,2*back))];
        
        for(int i=0;i<back;i++){
            auxArray[i] = array[(front+back)%array.length];
        }
        array = auxArray;
        front = 0;
   
    }
}