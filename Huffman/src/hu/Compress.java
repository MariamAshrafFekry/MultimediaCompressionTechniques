package hu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Compress 
{
	public String FUN()
	{
		String nameoffile="";
		JFileChooser jfile=new JFileChooser();
		if(jfile.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			File sfile=jfile.getSelectedFile();
			nameoffile=sfile.getName();
			return nameoffile;
		}
		return "";
	}
	
	public Node BuildTree(int[]frequency)
	{
		Queue<Node>queue=new PriorityQueue<Node>(new Comparing());
		for(int i=0;i<frequency.length;i++)
		{
			if(frequency[i]>0)
			{
			   queue.add(new Node(null,null,(char)i,frequency[i]));  
			}
		}
		while(queue.size()>=2)
		{
			Node n1=queue.poll();//to be left node
			Node n2=queue.poll();//to be right node
		    queue.add(new Node(n1,n2,(char)0,n1.Frequence+n2.Frequence));
		}
		return queue.poll();
	}
	public void GetCode(Node root,String code,Map<Character,String>map)
	{
		if(root.Symbol>0&&root.Symbol<=127)
		{
			map.put(root.Symbol,code);
			return ;
		}
		GetCode(root.Left,code+"0",map);
		GetCode(root.Right,code+"1",map);
	}
	Compress()
	{
		try
		{
			String NameOfFile=FUN();
			String NameFile="";
			if(NameOfFile!=""&&NameOfFile.contains(".txt"))
			{
				for(int i=0;i<NameOfFile.length()-4;i++)
				{
					NameFile+=NameOfFile.charAt(i);
				}
			
				File f1=new File(NameOfFile);
				Scanner s=new Scanner(f1);
				String Content="";
			
			  if(s.hasNext())
			   {
				    Content=s.nextLine();
			   }
			  while(s.hasNext())
			   {
			    	Content+='\n'+s.nextLine();
			   }
			
			  int frequency[]=new int[128];
			  for(int i=0;i<128;i++)
			  {
				   frequency[i]=0;
			  }
			  for(int i=0;i<Content.length();i++)
			  {
				  frequency[(int)Content.charAt(i)]++;
			  }
			  Node n=BuildTree(frequency);
			  
			  File f=new File("Dictionary.txt");
			  FileWriter fw=new FileWriter(f);
			  PrintWriter pw=new PrintWriter(fw);
			  
			  Map<Character,String>map=new HashMap<Character,String>();
			
			  String codes="";
			  GetCode(n,codes,map);
			  for(int i=0;i<128;i++)
			  {
				  if(frequency[i]>0)
				  {
					  pw.println((char)i+" "+map.get((char)i));
				  }
			  }
			  fw.close();
			  pw.close();
			  
			  File f2=new File(NameFile+".huffman");
			  FileWriter fw2=new FileWriter(f2);
			  PrintWriter pw2=new PrintWriter(fw2);
			  
			  for(int i=0;i<Content.length();i++)
			  {
				  char c=Content.charAt(i);
				  String code=map.get(c);
				  pw2.print(code);
			  }
			  fw2.close();
			  pw2.close();
			  JOptionPane.showMessageDialog(null, "Compression is Complete");
			}
			else if(NameOfFile!=""&&NameOfFile.contains(".huffman"))
			 {
				JOptionPane.showMessageDialog(null, "File Is Already Compressed");
			 }
			else
			 {
				JOptionPane.showMessageDialog(null, "Invalid File");
			 }
		}
		catch(IOException e)
		{
			
		}
	}
}
