package aaaa;


import java.awt.Button;
import java.awt.Desktop.Action;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;



import javax.xml.bind.Marshaller.Listener;

import org.omg.CORBA.PUBLIC_MEMBER;




public class abc {
	
	 
    public static void main(String[] args) {
        
        Frame f = new Frame("lab1");//建立框架
        String[] WORD=new String[9999];//句子
        f.setBounds(500, 300, 400, 600);
        
        f.setLayout(new FlowLayout());
        f.setLayout(null);
        //添加按钮
        Button bu1 = new Button("1.生成有向图");
        Button bu2 = new Button("2.展示有向图");
        Button bu3 = new Button("3.查询桥接词");
        Button bu4 = new Button("4.根据桥接词产生新文本");
        Button bu5 = new Button("5.计算两单词间最短路径");
        Button bu6 = new Button("6.随机游走");
        f.add(bu1);
        f.add(bu2);
        f.add(bu3);
        f.add(bu4);
        f.add(bu5);
        f.add(bu6);
        bu1.setSize(150, 50);
        bu1.setLocation(120, 50);
        bu2.setSize(150, 50);
        bu2.setLocation(120, 120);
        bu3.setSize(150, 50);
        bu3.setLocation(120, 190);
        bu4.setSize(150, 50);
        bu4.setLocation(120, 260);
        bu5.setSize(150, 50);
        bu5.setLocation(120, 330);
        bu6.setSize(150, 50);
        bu6.setLocation(120, 400);
        String filePath = "C:\\Users\\hasee\\Desktop\\test1.txt";
	    lab1 Lab1 = new lab1();
	    
	  
        bu1.addActionListener(new ActionListener(){ 
        	public void actionPerformed(ActionEvent e) {
        	    Lab1.readTxtFile(filePath, WORD);
        	}	
        });
        //展示有向图
        bu2.addActionListener(new ActionListener(){ 
        	public void actionPerformed(ActionEvent e) {
        		Lab1.showDirectedGraph(filePath);   	   
        	}	
        });
        
        
        //查询桥接词
        bu3.addActionListener(new ActionListener(){ 
        	public void actionPerformed(ActionEvent e) {
        		Scanner in = new Scanner(System.in);
                System.out.println("请输入第一个单词：");
                String word1 = in.nextLine();
                System.out.println("请输入第二个单词：");
                String word2 = in.nextLine();             
//                System.out.println(word1);
//                System.out.println(word2);
//                System.out.println(Lab1.generateNewText(word1)); 
                System.out.println(Lab1.queryBridgeWords (word1, word2));
        	}	
        });
        
        //根据桥接词生成文本
        bu4.addActionListener(new ActionListener(){ 
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("请输入文本：");
        		Scanner in = new Scanner(System.in);
        		String word1 = in.nextLine();
        		System.out.println(Lab1.generateNewText(word1));  	   
        	}	
        });
        
        //计算最短路径
        bu5.addActionListener(new ActionListener(){ 
        	public void actionPerformed(ActionEvent e) {
        		Scanner in = new Scanner(System.in);
                System.out.println("请输入第一个单词作为起点：");
                String word1 = in.nextLine();
                System.out.println("");
                System.out.println("请输入第二个单词作为终点：");
                String word2 = in.nextLine();             
//                System.out.println(word1);
//                System.out.println(word2);
//                System.out.println(Lab1.generateNewText(word1)); 
                Lab1.calcShortestPath (word1, word2);
        	}	
        });
        
        //随机游走
        bu6.addActionListener(new ActionListener(){ 
        	public void actionPerformed(ActionEvent e) {

        		System.out.println(Lab1.randomWalk());  	   
        	}	
        });
        
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);//退出JVM
            }
        });


        // 窗体显示
        f.setVisible(true);
        
    }

}