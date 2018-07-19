package hu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class DeCompress 
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
	DeCompress()
	{
		try
		{
			String NameOfFile=FUN();
			String NameFile="";
			if(NameOfFile!=""&&NameOfFile.contains(".huffman"))
			{
				for(int i=0;i<NameOfFile.length()-8;i++)
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
			  Map<String,String>map=new HashMap<String,String>();
			  File f=new File("Dictionary.txt");
			  Scanner sc=new Scanner(f);
			  
			  if(sc.hasNext())
			  {
				  String c=sc.next();
				  String st=sc.next();
				  map.put(st,c);
			  }
			  while(sc.hasNext())
			  {
				  String c=sc.next();
				  String st=sc.next();
				  map.put(st,c);
			  }
			 // System.out.println(map.keySet());
			  //System.out.println(map.values());
			  File f2=new File(NameFile+".txt");
			  FileWriter fw=new FileWriter(f2);
			  PrintWriter pw=new PrintWriter(fw);
			  
			  for(int i=0;i<Content.length();i++)
			  {
				  String current="";
				  for(int j=i;j<Content.length();j++)
				  {
					  current+=Content.charAt(j);
					 if(map.containsKey(current))
					 {
						  pw.print(map.get(current));
						 i=j;
						 break; 
					 }
				  }
			  }
			  fw.close();
			  pw.close();
			 
			 
			  JOptionPane.showMessageDialog(null, "DeCompression is Complete");
			}
			
			else if(NameOfFile!=""&&NameOfFile.contains(".txt"))
			 {
				JOptionPane.showMessageDialog(null, "File Is Already DeCompressed");
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
