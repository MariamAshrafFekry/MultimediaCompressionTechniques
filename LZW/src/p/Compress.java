package p;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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
			//nameoffile=sfile.getAbsolutePath();
			return nameoffile;
		}
		return "";
	}
	Compress()
	{
		try
		{
		String nameoffile=FUN();
		String name_file="";
		if(nameoffile!=""&&nameoffile.contains(".txt"))
		{
			for(int i=0;i<nameoffile.length()-4;i++)
			{
				name_file+=nameoffile.charAt(i);//new name of file
			}
			File f=new File(nameoffile);
			Scanner s=new Scanner(f);
		    FileWriter fw=new FileWriter(name_file+".lzw");
		    PrintWriter pw=new PrintWriter(fw);
		    String Content="";
		    if(s.hasNext())
		    {
		    	Content=s.nextLine();
		    }
		    while(s.hasNext())
		    {
		    	Content+='\n'+s.nextLine();
		    }
		    Map<String,Integer>Dictionary=new HashMap<String,Integer>();
		    for(int i=0;i<Content.length();i++)
		    {
		    	String z=Content.charAt(i)+"";
		    	Dictionary.put(z,(int)Content.charAt(i));
		    }
		    String past="";
		    int index=128;
		    int arr[]=new int[10001];
		    int j=0;
		   // File file=new File("DictionaryCompressed"+".txt");
		   // FileWriter fw1=new FileWriter(file);
		    //PrintWriter pw1=new PrintWriter(fw1);
		    for(int i=0;i<Content.length();i++)
		    {
		    	char current_char=Content.charAt(i);
		    	if(Dictionary.containsKey(past+current_char))
		    	{
		    		past=past+current_char;
		    	}
		    	else
		    	{
		    		arr[j]=Dictionary.get(past);
		    		j++;
		    		Dictionary.put(past+current_char,index);
		    		//pw1.println(past+current_char+' '+index);
		    		
			    	index++;
			    	past=Content.charAt(i)+"";
		    	}
		    }
		  //  pw1.close();
		  //  fw1.close();
		    
		    if(past!="")
		    {
		    	arr[j]=Dictionary.get(past);
	    		j++;
		    }
		   // System.out.println(j);
		    for(int i=0;i<j;i++)
		    {
		    	pw.print(arr[i]+" ");
		    }
		    pw.close();
		    fw.close();
		    
		    JOptionPane.showMessageDialog(null, "Compression is Complete");
		}
		else if(nameoffile!=""&&nameoffile.contains(".lzw"))
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
			//System.out.println("fail to open project");
		}
		
	}

}
