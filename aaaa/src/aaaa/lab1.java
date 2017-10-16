package aaaa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;
import java.lang.*;

public class lab1 {
    private int j;
    private int index;//this.index表示顶点数
    private int[][] matrix;//this.matrix表示邻接矩阵
    private String[] vexs;//顶点
    private String[][] edges;//边表
    private String[] word;//句子
    private int[] prev;
    
    void readTxtFile(String filePath,String[] word){
    	
        try {
            this.j =0;
            int num = 0;
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
 
                while((lineTxt = bufferedReader.readLine()) != null){	
                	if(lineTxt.isEmpty()) {
                		continue;
                	}
                    String line = lineTxt;
                    line = line.replaceFirst("[^a-zA-Z]*", "");
                    String[] words = line.split("[^a-zA-Z]+");
                    for (int i = 0;i < words.length; i++) {
                        words[i]= words[i].toLowerCase();
                        word[this.j] = words[i];
                        this.j++;
                
                	}
                    num++;
                }
                read.close();
                
            }else{
                System.out.println("找不到指定的文件");
              
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
            
        }
    }


    void showDirectedGraph(String filePath){

        this.word = new String[2000];
        readTxtFile(filePath,this.word);
        this.vexs = new String[this.j];
        this.edges = new String[2][this.j-1];
        this.index = 1;//index表示顶点个数
        this.vexs[0] = this.word[0];
        for (int i = 1;i<this.j;i++){
            boolean flag = false;
            String temp = this.word[i];
            for (int j = 0;j<this.index;j++) {
                 if (this.vexs[j].equals(temp)) {
                    flag = true;
                    break;
                }
            }
            if (flag == false){
                this.vexs[this.index] = temp;
                this.index++;
            }
        }
        for (int k = 0;k<(this.j-1);k++){
            //this.j-1表示边数
            this.edges[0][k] = this.word[k];
            this.edges[1][k] = this.word[k+1];
        }
        this.matrix= new int [this.index][this.index];
        for (int i =0;i<this.index;i++){
            for (int j = 0;j<this.index;j++){
                this.matrix[i][j] = 0;
            }
        }
        for (int i = 0;i<(this.j-1);i++){
            int p1 = getPosition(this.edges[0][i]);
            int p2 = getPosition(this.edges[1][i]);
            this.matrix[p1][p2]++;
        }
        for (int i =0;i<this.index;i++){
            for (int j = 0;j<this.index;j++){
                System.out.print(this.matrix[i][j]+" ");
            }
            System.out.println();
        }
        GraphViz gv=new GraphViz();
        gv.addln(gv.start_graph());
        for (int i =0;i<this.index;i++){
            for (int j = 0;j<this.index;j++){
                if (this.matrix[i][j] != 0)
                    gv.addln(this.vexs[i]+"->"+this.vexs[j]+"[lab = "+this.matrix[i][j]+"]");
            }
        }
        gv.addln(gv.end_graph());
//        for (int i = 0;i<this.index;i++){
//            System.out.println(vexs[i]);
//        }
//        for (int i = 0;i<(this.j-1);i++){
//            System.out.println(edges[0][i] + " " + edges[1][i]);
//        }
       
        String type = "jpg";
      
        File out = new File("C:\\pic\\lab1."+ type); 
        gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
        
        try {
        	openFile("C:\\pic\\lab1.jpg");
        }catch(IOException e) {
        	e.printStackTrace();
        }
       
    }
    


    int getPosition (String str){
          for (int i = 0;i<this.index;i++){
              if (this.vexs[i].equals(str)) return i;
          }
          return -1;
    }


    String queryBridgeWords (String word1, String word2) {
        boolean flag1 = false, flag2 = false;
        boolean flag = false;
        StringBuilder bridgewords = new StringBuilder();
        for (int i = 0; i < this.index; i++) {
            if (this.vexs[i].equals(word1)) {
                flag1 = true;
                break;
            }
        }
        if (flag1 == false) return ("No " + "\"" + word1 + "\"" + " in the graph!");
        for (int i = 0; i < this.index; i++) {
            if (this.vexs[i].equals(word2)) {
                flag2 = true;
                break;
            }
        }
        if (flag2 == false) return ("No " + "\"" + word2 + "\"" + " in the graph!");
        if (flag1 == true && flag2 == true) {
            for (int i = 0; i < this.j - 1; i++) {
                if (this.word[i].equals(word1)) {
                    for (int k = 1; k < this.j; k++) {
                        if (this.word[k].equals(word2) && this.word[k - 1].equals(this.word[i + 1])) {
                            flag = true;
                            if (bridgewords.indexOf(this.word[k-1]) == -1) bridgewords.append(this.word[k - 1] + " ");
                        }
                    }
                }
            }
            if (flag == false) return ("No bridge words from \"" + word1 + "\" to \"" + word2 + "\"!");
        }
        return ("The bridgewords from \"" + word1 + "\" to \"" + word2 + "\" are:" + bridgewords.toString());
    }


    String generateNewText(String inputTxt){
        String[] inputwords = inputTxt.split("[^a-zA-Z]+");
        for (int i = 0;i<inputwords.length;i++) inputwords[i] = inputwords[i].toLowerCase();
        StringBuilder outputwords = new StringBuilder();
        String[] bridgeword = new String[1000];
        outputwords.append(inputwords[0]+" ");
        for (int i = 0;i<inputwords.length-1;i++){
            int count = 0;
            for (int m = 0; m < this.j - 1; m++) {
                if (this.word[m].equals(inputwords[i])) {
                    for (int n = 1; n < this.j;n++) {
                        if (this.word[n].equals(inputwords[i+1]) && this.word[n - 1].equals(this.word[m + 1])) {
                            bridgeword[count] = word[n-1];
                            count++;
                        }
                    }
                }
            }
            if (count != 0) {
                Random rand = new Random(10);
                int random = rand.nextInt(count);
                outputwords.append(bridgeword[random]+" ");
            }
            outputwords.append(inputwords[i+1]+" ");
        }
        return outputwords.toString();
    }


    void Dijkstra(int n) {
        int[] s = new int[1000];
        int[] dist = new int[1000];
        prev = new int[1000];
        int[][] c=new int[1000][1000];
        for(int i = 0;i<this.index;i++){
            for(int j = 0;j<this.index;j++){
                if (this.matrix[i][j] != 0) c[i][j] = this.matrix[i][j];
                else if (this.matrix[i][j] == 0) c[i][j] = 999999;
            }
        }
        for(int i=0; i<this.index; i++) {
            dist[i] = c[n][i];
            s[i] = 0;
            if(dist[i] == 999999) prev[i] = 0;
            else prev[i] = n;
        }
        dist[n] = 0;
        s[n] = 1;
        for(int i=1; i<this.index; ++i) {
            int tmp = 999999;
            int u = n;
            for(int j=0; j<this.index; ++j) {
                if ((s[j] == 0) && dist[j] < tmp) {
                    u = j;
                    tmp = dist[j];
                }
            }
            s[u] = 1;
            for(int j=0; j<this.index; ++j)
                if((s[j] == 0) &&c[u][j]<999999)
                {
                    int newdist = dist[u] + c[u][j];
                    if(newdist < dist[j])
                    {
                        dist[j] = newdist;
                        prev[j] = u;
                    }
                }
        }
    }
    String calcShortestPath(String word1, String word2) {
        int v = getPosition(word1);
        int u = getPosition(word2);
        Dijkstra(v);
        int[] que = new int[1000];
        int tot = 1;
        que[tot] = u;
        tot++;
        int tmp = prev[u];
        while(tmp != v)
        {
            que[tot] = tmp;
            tot++;
            tmp = prev[tmp];
        }
        que[tot] = v;
        for(int i=tot; i>=1; --i){
            if(i != 1) System.out.print(this.vexs[que[i]]+"->");
            else System.out.println(this.vexs[que[i]]);
        }
        return "";
    }


    String randomWalk(){
        Random rand = new Random();
        int random = rand.nextInt(this.index);
        int n = 0;
        String randomword = this.vexs[random];
        String[] nextword = new String[1000];
        String[][] passedges = new String[2][1000];
        String[] path = new String[1000];
        boolean flag1 = false,flag2 = false;
        while(flag1 == false&&flag2 == false){
            flag1 = true;
            int count = 0;
            for (int i = 0;i<this.index;i++){
                if (this.matrix[getPosition(randomword)][i] != 0){
                    flag1 = false;
                    nextword[count] = this.vexs[i];
                    count++;
                }
            }
            if (flag1 == false){
                int random1 = rand.nextInt(count);
                String randomword1 = randomword;
                randomword = nextword[random1];
                path[n] = randomword1;
                n++;
                for (int i = 0;i<n;i++){
                    if (randomword1.equals(passedges[0][i])&&randomword.equals(passedges[1][i])){
                        flag2 = true;
                        path[n] = randomword;
                        break;
                    }
                    else{
                        passedges[0][n] = randomword1;
                        passedges[1][n] = randomword;
                    }
                }
            }
        }
        StringBuilder outputwords = new StringBuilder();
        if (flag2 == true) {
        	for (int i = 0; i<n+1;i++){
                outputwords.append(path[i]+" ");
            }
        }
        else {
        	for (int i = 0; i<n;i++){
            outputwords.append(path[i]+" ");
        	}
        }
        
//        outputwords.append(randomword);
        return outputwords.toString();
    }

//    public  lab1(){
    	
//    	String filePath = "C:\\Users\\hasee\\Desktop\\test1.txt";
////    	String filePath = new String();
//        showDirectedGraph(filePath);
//        int h = getPosition("have");
//        System.out.println(h);
//        Scanner s1 = new Scanner(System.in);
//        String word1 = s1.nextLine();
//        Scanner s2 = new Scanner(System.in);
//        String word2 = s2.nextLine();
//////        System.out.println(word1);
//////        System.out.println(word2);
//////        System.out.println(generateNewText(word1));
//        System.out.println(calcShortestPath(word1, word2));
////        System.out.println(randomWalk());
////
//    }
    public void openFile(String path) throws IOException{
    	Runtime.getRuntime().exec("cmd /c Start " + path);
    }
    
    public static void main(String[] args){
        lab1 m = new lab1();
    }
}