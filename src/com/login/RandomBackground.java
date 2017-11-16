package com.login;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RandomBackground extends JLabel {

    String path;
    RandomBackground(int abc){
        switch (abc){
            case 1:
                path = "C:\\Users\\somesh\\IdeaProjects\\JewelleryManagement\\src\\com\\login\\IMG-20171024-WA0020.jpg";
                break;
            case 2:
                path = "C:\\Users\\somesh\\IdeaProjects\\JewelleryManagement\\src\\com\\login\\IMG-20171024-WA0019.jpg";
                break;
            case 3:
                path = "C:\\Users\\somesh\\IdeaProjects\\JewelleryManagement\\src\\com\\login\\IMG-20171024-WA0018.jpg";
                break;
            case 4:
                path = "C:\\Users\\somesh\\IdeaProjects\\JewelleryManagement\\src\\com\\login\\IMG-20171024-WA0016.jpg";
                break;
            case 5:
                path = "C:\\Users\\somesh\\IdeaProjects\\JewelleryManagement\\src\\com\\login\\IMG-20171024-WA0015.jpg";
                break;
            case 6:
                path = "C:\\Users\\somesh\\IdeaProjects\\JewelleryManagement\\src\\com\\login\\IMG-20171024-WA0014.jpg";
                break;
            case 7:
                path = "C:\\Users\\somesh\\IdeaProjects\\JewelleryManagement\\src\\com\\login\\IMG-20171024-WA0013.jpg";
                break;
            default:
                path = "C:\\Users\\somesh\\IdeaProjects\\JewelleryManagement\\src\\com\\login\\home.png";
        }
        setIcon(new ImageIcon(path));
//        setHorizontalAlignment(CENTER);
 //       setVerticalAlignment(CENTER);
        setLayout(null);
    }
}
