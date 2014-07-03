package com.interview.java.basics.model.collection;

/**
 * Created_By: stefanie
 * Date: 14-7-1
 * Time: 下午11:08
 */
public class ArrayList<T> implements List<T> {
    static int N = 2;

    T[] array = (T[]) new Object[N];
    int size = 0;

    @Override
    public void add(T element) {
        add(size, element);
    }

    @Override
    public void add(int index, T element) {
        if(size >= array.length)    expand();
        int i = size > 0 ? size - 1 : size;
        for (; i > index; i--)  array[i + 1] = array[i];
        array[index] = element;
        size++;
    }

    @Override
    public T get(int index) {
        return checkIndex(index)?  array[index]: null;
    }

    @Override
    public void set(int index, T element) {
        if(checkIndex(index))  array[index] = element;
    }

    @Override
    public int indexOf(T element) {
        for(int i = 0; i < size; i++){
            if(array[i].equals(element))    return i;
        }
        return -1;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    @Override
    public T remove(int index) {
        if(!checkIndex(index)) return null;
        T temp = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        if (size < array.length / 4)    shrink();
        return temp;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public T[] toArray() {
        T[] arr = (T[]) new Object[size];
        for(int i = 0; i < size; i++){
            arr[i] = array[i];
        }
        return arr;
    }

    private void expand(){
        N*=2;
        copy();
    }

    private void shrink(){
        N = N / 2;
        copy();
    }

    private void copy(){
        T[] newArray = (T[]) new Object[N];
        for(int i = 0; i < size; i++){
            newArray[i] = array[i];
        }
        array = newArray;
    }

    private boolean checkIndex(int index){
        if(index >= 0 && index < size) return true;
        else return false;
    }
}
