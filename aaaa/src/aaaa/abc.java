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
        
        Frame f = new Frame("lab1");//�������
        String[] WORD=new String[9999];//����
        f.setBounds(500, 300, 400, 600);
        
        f.setLayout(new FlowLayout());
        f.setLayout(null);
        //��Ӱ�ť
        Button bu1 = new Button("1.��������ͼ");
        Button bu2 = new Button("2.չʾ����ͼ");
        Button bu3 = new Button("3.��ѯ�ŽӴ�");
        Button bu4 = new Button("4.�����ŽӴʲ������ı�");
        Button bu5 = new Button("5.���������ʼ����·��");
        Button bu6 = new Button("6.�������");
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
	    
	    //��������ͼ
        bu1.addActionListener(new ActionListener(){ 
        	public void actionPerformed(ActionEvent e) {
        	    Lab1.readTxtFile(filePath, WORD);
        	}	
        });
        //չʾ����ͼ
        bu2.addActionListener(new ActionListener(){ 
        	public void actionPerformed(ActionEvent e) {
        		Lab1.showDirectedGraph(filePath);   	   
        	}	
        });
        
        
        //��ѯ�ŽӴ�
        bu3.addActionListener(new ActionListener(){ 
        	public void actionPerformed(ActionEvent e) {
        		Scanner in = new Scanner(System.in);
                System.out.println("�������һ�����ʣ�");
                String word1 = in.nextLine();
                System.out.println("������ڶ������ʣ�");
                String word2 = in.nextLine();             
//                System.out.println(word1);
//                System.out.println(word2);
//                System.out.println(Lab1.generateNewText(word1)); 
                System.out.println(Lab1.queryBridgeWords (word1, word2));
        	}	
        });
        
        //�����ŽӴ������ı�
        bu4.addActionListener(new ActionListener(){ 
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("�������ı���");
        		Scanner in = new Scanner(System.in);
        		String word1 = in.nextLine();
        		System.out.println(Lab1.generateNewText(word1));  	   
        	}	
        });
        
        //�������·��
        bu5.addActionListener(new ActionListener(){ 
        	public void actionPerformed(ActionEvent e) {
        		Scanner in = new Scanner(System.in);
                System.out.println("�������һ��������Ϊ��㣺");
                String word1 = in.nextLine();
                System.out.println("");
                System.out.println("������ڶ���������Ϊ�յ㣺");
                String word2 = in.nextLine();             
//                System.out.println(word1);
//                System.out.println(word2);
//                System.out.println(Lab1.generateNewText(word1)); 
                Lab1.calcShortestPath (word1, word2);
        	}	
        });
        
        //�������
        bu6.addActionListener(new ActionListener(){ 
        	public void actionPerformed(ActionEvent e) {

        		System.out.println(Lab1.randomWalk());  	   
        	}	
        });
        
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);//�˳�JVM
            }
        });


        // ������ʾ
        f.setVisible(true);
        
    }

}