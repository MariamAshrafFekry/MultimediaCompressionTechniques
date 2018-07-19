
import java.util.*;

import javax.swing.JFileChooser;

import java.io.*;
public class Compression
{
	public static String Fun()
	{
		JFileChooser jfile = new JFileChooser();
        if (jfile.showOpenDialog(null)== JFileChooser.APPROVE_OPTION)
        {
          File sfile = jfile.getSelectedFile();
          String s=sfile.getName();
          return s;
        }
		return "";
	}
	public Compression()
	{
		try 
		{
			String p=Fun();
			if(p!=""&&p.charAt(p.length()-1)=='z'&&p.charAt(p.length()-2)=='l'&&p.charAt(p.length()-3)=='.')
			{
				System.out.println("the file is already compressed");
			}
			else if(p!=""&&p.charAt(p.length()-1)=='t'&&p.charAt(p.length()-2)=='x'
			   &&p.charAt(p.length()-3)=='t'&&p.charAt(p.length()-4)=='.')
			{
				String s1="";
				for(int i=0;i<p.length()-4;i++)
				{
					s1+=p.charAt(i);
				}
				
			File fi=new File(p);
			Scanner s = new Scanner(fi);
			FileWriter fw=new FileWriter(s1+".lz");
			PrintWriter pw=new PrintWriter(fw);
	        String st="",past="";
	    if(s.hasNext())
	    {
	    	st=s.next();
	    }
	    while(s.hasNext())
	    {
	    	st+='\n'+s.next();
	    }
	    int lastindx=0;
	    for(int i=0;i<st.length();i++)
	    {
	    	String current="";
	    	for(int j=i;j<st.length();j++)
	    	{
	    		current+=st.charAt(j);
	    		int x=past.lastIndexOf(current);
	    		if(x==-1)
	    		{
	    			pw.print(lastindx+" ");
	    			pw.print(current.length()-1+" ");
	    			pw.print((int)current.charAt(current.length()-1));
	    			pw.print("\r\n");
	    			i=j;
	    			break;
	    		}
	    		else 
	    		{
	    			lastindx=x;
	    		}
	    	}
	    		past+=current;
	      }
	    pw.close();
		}
			else if(p=="")
			{
				System.out.println("no file is choosen");
			}
		else
			{
				System.out.println("error type of file");
			}
		}
		catch (IOException e)
		{
			//System.out.println("file not found");
			//e.printStackTrace();
		}
	}
	public static void main(String[]args)
	{
	}
}
