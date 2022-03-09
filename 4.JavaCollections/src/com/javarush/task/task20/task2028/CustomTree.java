package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    List<Entry<String>> listTree = new ArrayList<>();


    public CustomTree() {
        this.root = new Entry<>("name");
        listTree.add(root);
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return listTree.size() - 1;
    }

    public String getParent(String s) {
        for(Entry<String> element : listTree) {
            if(element.elementName.equals(s))
                return element.parent.elementName;
        }
        return null;
    }

    @Override
    public boolean add(String s) {
        for(Entry<String> element : listTree) {
            if(element.availableToAddLeftChildren) {
                Entry<String> newElement = new Entry<>(s);
                newElement.parent = element;
                element.leftChild = newElement;
                element.availableToAddLeftChildren = false;
                listTree.add(newElement);
                return true;
            }
            else if(element.availableToAddRightChildren) {
                Entry<String> newElement = new Entry<>(s);
                newElement.parent = element;
                element.rightChild = newElement;
                element.availableToAddRightChildren = false;
                listTree.add(newElement);
                return true;
            }
        }
        return false;
    }

    public boolean remove(Object o) {
        if(!(o instanceof String)) throw new UnsupportedOperationException();
        Queue<Entry<String>> nodes = new LinkedList<>(Collections.singletonList(root));

        while (!nodes.isEmpty()) {
            Entry<String> node = nodes.poll();

            if (node.elementName.equals(o)) {
                if (node.parent.leftChild == node) {
                    node.parent.leftChild = null;
                    node.parent.availableToAddLeftChildren = true;
                }

                if (node.parent.rightChild == node) {
                    node.parent.rightChild = null;
                    node.parent.availableToAddRightChildren = true;
                }

                return true;
            }

            if (node.leftChild != null)
                nodes.offer(node.leftChild);
            if (node.rightChild != null)
                nodes.offer(node.rightChild);
        }

        return false;
    }

    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
}
