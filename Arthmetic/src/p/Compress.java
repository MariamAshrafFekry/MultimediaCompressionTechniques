package p;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
		    FileWriter fw=new FileWriter(name_file+".code");
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
		    int a[]=new int[128];
		    for(int i=0;i<Content.length();i++)
		    {
		    	a[(int)Content.charAt(i)]++;
		    }
		    char Symbol[]=new char[128];
		    float probability[]=new float[128];
		    int j=0;
		    for(int i=0;i<128;i++)
		    {
		    	if(a[i]>0)
		    	{
		    		probability[j]=(float)a[i]/Content.length();
		    		Symbol[j]=(char)i;
		    		j++;
		    	}
		    }
            float LowerRange[]=new float[j];
            float UpperRange[]=new float[j];
            float Lower[]=new float[Content.length()];
            float Upper[]=new float[Content.length()];
            
            LowerRange[0]=0;
            UpperRange[0]=probability[0];
            for(int i=1;i<j;i++)
            {
            	LowerRange[i]=UpperRange[i-1];
            	UpperRange[i]=probability[i]+UpperRange[i-1];
            }
            
            for(int i=0;i<j;i++)
            {
            	if(Content.charAt(0)==Symbol[i])
            	{
            		Lower[0]=LowerRange[i];
            		Upper[0]=UpperRange[i];
            		break;
            	}
            }
            for(int i=1;i<Content.length();i++)
            {
            	int c=0;
            	for(int k=0;k<j;k++)
            	{
            		if(Content.charAt(i)==Symbol[k])
            		{
            		    c=k;
            			break;
            		}
            	}
            	Lower[i]=Lower[i-1]+(Upper[i-1]-Lower[i-1])*(LowerRange[c]);
            	Upper[i]=Lower[i-1]+(Upper[i-1]-Lower[i-1])*UpperRange[c];
            }
            float Code=(Lower[Content.length()-1]+Upper[Content.length()-1])/2;
            pw.println(Content.length());
            pw.println(Code);
            for(int i=0;i<j;i++)
            {
            	pw.println((int)Symbol[i]+" "+probability[i]);
            }
           fw.close();
           pw.close();

		    JOptionPane.showMessageDialog(null, "Compression is Complete");
		}
		else if(nameoffile!=""&&nameoffile.contains(".code"))
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
