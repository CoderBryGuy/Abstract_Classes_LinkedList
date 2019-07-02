package com.company;

import java.util.List;

public class SearchTree implements NodeList {

    private ListItem root = null;

    public SearchTree(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem newItem) {
        if(this.root == null){
            //this item will be head
            this.root = newItem;
            return true;
        }

        ListItem currentItem = this.root;
        while(currentItem != null){
            int comparison = (currentItem.compareTo(newItem));

            if(comparison < 0){
                // new item is greater
                if(currentItem.next() != null){
                    currentItem = currentItem.next();
                }else {
                    //short cut
                    //currentItem.setNext((newItem).setPrevious(currentItem);
                    currentItem.setNext(newItem);
                    return true;
                }
            }

            else if(comparison > 0){
                //new item is less, insert before
                if(currentItem.previous() != null){
                  currentItem = currentItem.previous();
                }else {
                    currentItem.setPrevious(newItem);
                    return true;
                }
            }

            else  {
                // values are equal
                System.out.println(newItem.getValue() + " is already present, not added");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        if(item != null){
            System.out.println("deleting item " + item.getValue());
        }

        ListItem currentItem = this.root;
        ListItem parentItem = currentItem;

        while(currentItem != null){
            int comparison = (currentItem.compareTo(item));
            if(comparison < 0){
                parentItem = currentItem;
                currentItem = currentItem.next();
            }else if( comparison > 0){
                parentItem = currentItem;
                currentItem = currentItem.previous();
            }else {
                //equal
                performRemoval(currentItem, parentItem);
                return true;
            }
        }

        return false;
    }

    private void performRemoval(ListItem item, ListItem parent) {
        if(item.next() == null){
            if(parent.next() == item){
                parent.setNext(item.previous());
            }else if(parent.previous() == item){
                parent.setPrevious(item.previous());
            }else {
                this.root = item.previous();
            }
        }else  if (item.previous() == null){
            if(parent.next() == item){
                parent.setNext(item.next());
            }else if(parent.previous() == item){
                parent.setPrevious(item.next());
            }else{
                this.root = item.next();
            }
        }else {
            ListItem current = item.next();
            ListItem leftMostParent = item;
            
            while(current.previous() != null) {
                leftMostParent = current;
                current = current.previous();
            }
            item.setValue(current.getValue());

            if(leftMostParent == item){
                item.setNext(current.next());
            }else{
                leftMostParent.setPrevious(current.next());
            }
        }


    }

    @Override
    public void traverse(ListItem item) {
        //recursive method
        if(root != null){
            traverse(root.previous());
            System.out.println((root.getValue()));
            traverse(root.next());
        }

    }
}