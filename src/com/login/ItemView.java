package com.login;

import javax.swing.*;
import java.awt.*;

public class ItemView extends JPanel{

    JLabel item_id,label_id;
    JLabel item_name,label_name;
    JLabel item_category,label_category;
    JLabel item_weight,label_weight;
    JLabel item_status,label_status;

    ItemView(Item item){
        super();
        setLayout(null);

        JLabel label1 = new JLabel("<html><h1><font color='black'>Item View</font><h1></html>");
        label1.setBounds(10, 10, 400, 40);
        add(label1);

        item_id = new JLabel("ID : ");
        item_id.setBounds(0, 60, 100, 40);
        add(item_id);

        item_name = new JLabel("NAME : ");
        item_name.setBounds(0, 110, 100, 40);
        add(item_name);

        item_category = new JLabel("CATEGORY : ");
        item_category.setBounds(0, 160, 100, 40);
        add(item_category);

        item_weight = new JLabel("WEIGHT : ");
        item_weight.setBounds(0, 210, 100, 40);
        add(item_weight);

        item_status = new JLabel("STATUS : ");
        item_status.setBounds(0, 260, 100, 40);
        add(item_status);

        label_id = new JLabel(String.valueOf(item.getId()));
        label_id.setBounds(100, 60, 100, 40);
        add(label_id);

        label_name = new JLabel(item.getName());
        label_name.setBounds(100, 110, 100, 40);
        add(label_name);

        label_category = new JLabel(item.getCategory());
        label_category.setBounds(100, 160, 100, 40);
        add(label_category);

        label_weight = new JLabel(item.getWeight());
        label_weight.setBounds(100, 210, 100, 40);
        add(label_weight);

        label_status = new JLabel(item.getStatus());
        label_status.setBounds(100, 260, 100, 40);
        add(label_status);

        setOpaque(true);
        setBackground(new Color(0,0,0,0));
    }
}
