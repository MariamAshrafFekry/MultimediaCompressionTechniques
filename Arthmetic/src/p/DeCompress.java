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
		String nameoffile=FUN();
		String name_file="";
		if(nameoffile!=""&&nameoffile.contains(".code"))
		{
			for(int i=0;i<nameoffile.length()-5;i++)
			{
				name_file+=nameoffile.charAt(i);
			}
			File f=new File(nameoffile);
			Scanner s=new Scanner(f);
		    FileWriter fw=new FileWriter(name_file+".txt");
		    PrintWriter pw=new PrintWriter(fw);
		    String Content="";
		    float Code=0;
		    int Length=0;
		    if(s.hasNext())
		    {
		    	Length=s.nextInt();
		    	Code=s.nextFloat();
		    }
		    int j=0;
		    char Symbol[]=new char[Length];
		    float probability[]=new float[Length];
		    while(s.hasNext())
		    {
		    	Symbol[j]=(char)s.nextInt();
		    	probability[j]=s.nextFloat();
		    	j++;
		    }
            float LowerRange[]=new float[Length];
            float UpperRange[]=new float[Length];
            
            LowerRange[0]=0;
            UpperRange[0]=probability[0];
            for(int i=1;i<j;i++)
            {
            	LowerRange[i]=UpperRange[i-1];
            	UpperRange[i]=probability[i]+UpperRange[i-1];
            }
          float newcode=0;
            float Lower[]=new float[Length];
            float Upper[]=new float[Length];
            for(int i=0;i<1;i++)
	        {
	        	for(int k=0;k<Length;k++)
	            {
	            	if(LowerRange[k]<=Code&&Code<UpperRange[k])
	            	{
	            		 Lower[0]=LowerRange[k];
	            		 Upper[0]=UpperRange[k];
	            		Content+=Symbol[k];
	            		break;
	            	}
	            }
	        }
            for(int i=1;i<Length;i++)
	        {
            	newcode=(Code-Lower[i-1])/(Upper[i-1]-Lower[i-1]);
	        	for(int k=0;k<Length;k++)
	            {
	            	if(LowerRange[k]<=newcode&&newcode<UpperRange[k])
	            	{
	            		 Lower[i]=Lower[i-1]+(Upper[i-1]-Lower[i-1])*LowerRange[k];
	            		 Upper[i]=Lower[i-1]+(Upper[i-1]-Lower[i-1])*UpperRange[k];
	            		Content+=Symbol[k];
	            		break;
	            	}
	            }
	        }
           pw.println(Content);
           fw.close();
           pw.close();

		    JOptionPane.showMessageDialog(null, "DeCompression is Complete");
		}
		else if(nameoffile!=""&&nameoffile.contains(".txt"))
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
