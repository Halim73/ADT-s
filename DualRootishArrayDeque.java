/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author halim
 */
public class DualArrayDequeue<E> {
    private RootishArrayStack<E> front;
    private RootishArrayStack<E> back;
    private int size;
    
    public DualArrayDequeue(){
        this.front = new RootishArrayStack<>();
        this.back = new RootishArrayStack<>();
        size = 0;   
    }
    protected void balance(){
        if(3*front.size() < back.size() || 3*back.size() < front.size()){
            int size = front.size()+back.size();
            int newFront = size/2;
            
            RootishArrayStack<E> auxFront =  new RootishArrayStack<>();
            
            for(int i=0;i<newFront;i++){
                auxFront.set(newFront-i-1,front.get(i));
            }
            
            int newBack = size-newFront;
            RootishArrayStack<E>auxBack = new RootishArrayStack<>();
            
            for(int i=0;i<newBack;i++){
                auxBack.set(i,back.get(newFront+1));
            }
            
            front = auxFront;
            back = auxBack;
            
        }
    }
    public E remove(int i){
        E elem;
        if(i < front.size()){
            elem =  front.remove(front.size()-i-1);
        }else{
            elem = back.remove(i-front.size());
        }
        balance();
        return elem;
    }
    public void add(int i, E elem){
        if(i < front.size()){
            front.add(front.size()-i,elem);
        }else{
            back.add(i-front.size(),elem);
        }
        balance();
    }
    public E set(int i,E elem){
        if(i < front.size()){
            return front.set(front.size()-i-1, elem);
        }else{
            return back.set(i-front.size(),elem);
        }
    }
    public E get(int i){
        if(i < front.size()){
            return front.get(front.size()-i-1);
        }else{
            return back.get(i-front.size());
        }
    }
}
