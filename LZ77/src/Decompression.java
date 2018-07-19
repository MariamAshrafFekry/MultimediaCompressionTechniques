
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.io.*;
public class Decompression 
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
	public Decompression ()
	{
		try
		{
			String s1=Fun();
			String s3="";
			for(int i=0;i<s1.length()-4;i++)
			{
				s3+=s1.charAt(i);
			}
			if(s1!=""&&s1.charAt(s1.length()-1)=='t'&&s1.charAt(s1.length()-2)=='x'
			   &&s1.charAt(s1.length()-3)=='t'&&s1.charAt(s1.length()-4)=='.')
				{
				    System.out.println("file is already DeCompressed");
				    return;
				}
			else if(s1!=""&&s1.charAt(s1.length()-1)=='z'
				&&s1.charAt(s1.length()-2)=='l'&&s1.charAt(s1.length()-3)=='.')
			{	
			FileReader fr=new FileReader(s1);
			BufferedReader br=new BufferedReader(fr);
			int NumOfRow=0;
			while(br.readLine()!=null)
			{
				NumOfRow++;
			}
			fr.close();
			
			FileReader fr1=new FileReader(s1);
			BufferedReader br1=new BufferedReader(fr1);
			File fi=new File(s1);
			Scanner s=new Scanner(fi);
			
			int Aposition[]=new int [NumOfRow];
			int ALength[]=new int[NumOfRow];
			int Next_Char[]=new int[NumOfRow];
			int sum=0;
			NumOfRow=0;
			while(br1.readLine()!=null)
			{
				int x=s.nextInt();
				Aposition[NumOfRow]=x;
				int y=s.nextInt();
				ALength[NumOfRow]=y;
				sum+=y;
				int z=s.nextInt();
				Next_Char[NumOfRow]=z;
				NumOfRow++;
			}
			fr1.close();

			int indx=0;
			int a[]=new int[sum+NumOfRow];
			for(int j=0;j<NumOfRow;j++)
			{
					int val=indx;
					int v=ALength[j];
					for(int k=val-Aposition[j];v>0;k++)
				      {
					       a[indx]=a[k];
					       indx++;
					       v--;
			          }
					a[indx]=Next_Char[j];
					indx++;
			}
			FileWriter fw=new FileWriter(s3+".txt");
			PrintWriter pw=new PrintWriter(fw);
			
			for(int l=0;l<sum+NumOfRow;l++)
			{
				pw.print((char)a[l]);
			}
			pw.close();
		}
			else if(s1=="")
			{
				System.out.println("no file is choosen");
			}
			else
			{
				System.out.println("error type of file");
			}
		}
		catch(IOException e)
		{
			System.out.println("file not found");
		}
	}
	public static void main(String []args) 
	{
			
	}

}

