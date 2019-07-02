package com.company;

import java.util.List;

public class MyLinkedList implements NodeList {

    private ListItem root = null;

    public MyLinkedList(ListItem root) {
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
                    newItem.setPrevious(currentItem);
                    return true;
                }
            }

            else if(comparison > 0){
                //new item is less, insert before
                if(currentItem.previous() != null){
                    //short cut
                    //currentItem.previous().setNext(newItem).setPrevious(currentItem.previous());

                    currentItem.previous().setNext(newItem);
                    newItem.setPrevious(currentItem.previous());
                    newItem.setNext(currentItem);
                    currentItem.setPrevious(newItem);
                }else {
                    // the node with previous is root
                    // short cut
                    //newItem.setNext(this.root).setPrevious(newItem);

                    newItem.setNext(this.root).setPrevious(newItem);
                    this.root = newItem;
                }
                return true;
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
        while(currentItem != null){
            int comparison = currentItem.compareTo(item);
            if(comparison  == 0){
                // found item to delete
                if(currentItem == this.root){
                    this.root = currentItem.next();
                }else {
                    currentItem.previous().setNext(currentItem.next());
                    if(currentItem.next() != null){
                        currentItem.next().setPrevious(currentItem.previous());
                    }
                }
                return true;
            }else if( comparison < 0){
                currentItem.next();
            }else {
                //comparison > 0
                // we are at an item that is passed where it could be based on sort criteria
                return false;

            }
        }


//        ListItem currentItem = this.root;
//
//        while(currentItem != null){
//            if(currentItem.compareTo(item) == 0){
//                currentItem.previous().setNext(currentItem.next());
//                currentItem.next().setPrevious(currentItem.previous());

//        if (currentItem == root){
//            if(currentItem.next() != null) {
//                this.root = currentItem.next();
//            }else{
//                this.root = null;
//            }
//        }

//                currentItem.setNext(null);
//                currentItem.setPrevious(null);
//                currentItem = null;
//                return true;
//            }else if(currentItem.next() != null) {
//                currentItem = currentItem.next();
//            }else {
//                return false;
//            }
//
//        }
//        return false;

        //reached end without finding item to delete

        return false;
    }

    @Override
    public void traverse(ListItem item) {
        if(root == null){
            System.out.println("list empty");
        }else {

            while (root != null) {
                System.out.println(root.getValue());
                root = root.next();
            }

        }

    }
}
