package com.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home implements ActionListener {

    JFrame f;
    JMenuBar bar;
    JMenu item,customer,bill,User;
    JMenuItem itemAdd,itemSearch,customerAdd,customerSearch,billSearch,logOut,logOutExit;

    Home(){
        f= new JFrame("Jewelley Management : DashBoard");
        f.setSize(640,400);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setUndecorated(true);
        f.setLayout(new BorderLayout());

        f.add(new RandomBackground(8));

        bar = new JMenuBar();
        f.setJMenuBar(bar);
        User = new JMenu("User");
        bar.add(User);

        logOut = new JMenuItem("Log Out");
        User.add(logOut);
        logOut.addActionListener(this);

        logOutExit = new JMenuItem("Log out & Exit");
        User.add(logOutExit);
        logOutExit.addActionListener(this);

        item = new JMenu("Item");
        bar.add(item);
        itemAdd = new JMenuItem("Add",new ImageIcon("C:\\Users\\somesh\\IdeaProjects\\JewelleryManagement\\src\\com\\login\\ic_add_box_black_18dp.png"));
        item.add(itemAdd);
        itemSearch = new JMenuItem("Search",new ImageIcon("C:\\Users\\somesh\\IdeaProjects\\JewelleryManagement\\src\\com\\login\\ic_search_black_18dp.png"));
        item.add(itemSearch);
        customer = new JMenu("Customer");
        bar.add(customer);
        customerAdd = new JMenuItem("Add",new ImageIcon("C:\\Users\\somesh\\IdeaProjects\\JewelleryManagement\\src\\com\\login\\ic_add_box_black_18dp.png"));
        customer.add(customerAdd);
        customerSearch = new JMenuItem("Search",new ImageIcon("C:\\Users\\somesh\\IdeaProjects\\JewelleryManagement\\src\\com\\login\\ic_search_black_18dp.png"));
        customer.add(customerSearch);
        bill = new JMenu("Bill");
        bar.add(bill);
        billSearch = new JMenuItem("Search",new ImageIcon("C:\\Users\\somesh\\IdeaProjects\\JewelleryManagement\\src\\com\\login\\ic_search_black_18dp.png"));
        bill.add(billSearch);
        f.setResizable(false);
        customerAdd.addActionListener(this);
        customerSearch.addActionListener(this);
        itemSearch.addActionListener(this);
        itemAdd.addActionListener(this);
        billSearch.addActionListener(this);
        f.setVisible(true);
        f.setIconImage(new ImageIcon("C:\\Users\\somesh\\IdeaProjects\\JewelleryManagement\\src\\com\\login\\icons8-Diamond-100.png").getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) e.getSource();
        if(source == itemAdd){
            new AddItem();
        }else if(source == itemSearch){
            new SearchItem();
        }else if(source == customerAdd){
            new AddCustomer();
        }else if (source == customerSearch){
            new SearchCustomer();
        }else if (source == billSearch){
            new BillView();
        }else if(source == logOut){
            f.dispose();
            new Login();

        }else if (source == logOutExit){
            System.exit(0);
        }
    }
}
