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
public class RootishArrayStack<E> {
    private ArrayStack<E[]> blocks;
    private int elements;
    
    public RootishArrayStack(){
        this.blocks = new ArrayStack<E[]>();
        this.elements = 0;
    }
    public int size(){
        return elements;
    }
    protected int indexToBlock(int index){
        double blockID = (-3.0+Math.sqrt(9+8*index)/2);
        int blockNum = (int)Math.round(blockID);
        
        return blockNum;
    }
    public E set(int i, E elem){
        int block = indexToBlock(i);
        int blockIndex = i-block*(block+1)/2;
       
        E temp = blocks.get(block)[blockIndex];
        blocks.get(block)[blockIndex] = elem;
        
        return temp;
    }
    public E remove(int i){
        E temp = get(i);
        
        for(int j=i;j<elements-1;j++){
            set(j,get(j+1));
        }
        
        elements--;
        int size =  blocks.size();
        
        if((size-2)*(size-1)/2 >= elements){
            shrink();
        }
        return temp;
    }
    public void add(int i, E elem){
        int size = blocks.size();
        
        if(size*(size+1)/2 < (elements+1)){
            grow();
        }
        
        elements++;
        
        for(int j=elements-1;j>i;j--){
            set(j,get(j-1));
        }
        set(i,elem);
    }
    protected void shrink(){
        int size = blocks.size();
        
        while((size > 0) && ((size-2)*(size-1)/2 >= elements)){
            blocks.remove(blocks.size()-1);
            size--;
        }
    }
    protected void grow(){
        blocks.add(blocks.size(),(E[]) new Object[blocks.size()+1]);
    }
    public E get(int i){
        int block = indexToBlock(i);
        int blockIndex = i-block*(block+1)/2;
        
        return blocks.get(block)[blockIndex];
    }
}
