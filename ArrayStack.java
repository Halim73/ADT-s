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
public class ArrayStack<E> {
    private E[] array;
    private int top;
    private static final int MAX = 20;
    
    public ArrayStack(int size){
        E[]array = (E[]) new Object[size];
        top = 0;
    }
    public ArrayStack(){
        this(MAX);
    }
    private void resize(){
        E[] auxArray = (E[]) new Object[(Math.max(1,2*top))];
        
        System.arraycopy(array, 0, auxArray, 0, array.length);
        array = auxArray;
        top = 0;
    }
    public E get(int i){
        return array[i];
    }
    public E set(int i, E elem){
        E temp = array[i];
        array[i] = elem;
        return temp;
    }
    public void add(int i, E elem){
        if(top+1 > array.length){
            resize();
        }
        for(int start=top;start>i;start--){
            array[start] = array[start-1];
        }
        array[i] = elem;
        top++;
    }
    public void remove(int i){
        E temp = array[i];
        for(int start=i;start<top-1;start++){
            array[start] = array[start+1];
        }
        top--;
        if(array.length >= 3*top){
            resize();
        }
    }
    public void push(E elem){
        if((top+1) > array.length){
            resize();
        }
        array[top++] = elem;
    }
    private boolean isEmpty(){
        return top == 0;
    }
    public int size(){
        return top;
    }
    public E pop(){
        if(isEmpty()){
            return null;
        }else{ 
            return array[top--];
        }
    }
}
