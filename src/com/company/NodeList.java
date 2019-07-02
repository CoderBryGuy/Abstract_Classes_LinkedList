package com.company;

import java.util.List;

public interface NodeList {

    ListItem getRoot();
    boolean addItem(ListItem newItem);
    boolean removeItem(ListItem item);
    void traverse(ListItem item);
}
