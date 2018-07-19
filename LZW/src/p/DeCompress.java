package p;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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
			if(nameoffile!=""&&nameoffile.contains(".lzw"))
			{
				for(int i=0;i<nameoffile.length()-4;i++)
				{
					name_file+=nameoffile.charAt(i);//new name of file
				}
				File f=new File(nameoffile);
				Scanner s=new Scanner(f);
				
				File f1=new File(name_file+".txt");
				FileWriter fr=new FileWriter(f1);
				PrintWriter bw=new PrintWriter(fr);
				
				int count_numbers=0;
				int arr[]=new int[1001];
				
				while(s.hasNext())
				{
					int current_value=s.nextInt();
					arr[count_numbers]=current_value;
					count_numbers++;
				}
				//System.out.println(count_numbers);
			/*	for(int j=0;j<count_numbers;j++)
				{
					System.out.print(arr[j]+" ");
				}*/
				Map<Integer,String>Dictionary=new HashMap<Integer,String>();
				
				for(int i=0;i<=127;i++)
				{
					Dictionary.put(i,""+(char)i);
				}
				String data="";
				int pw,cw,index=128;
				String st_pw="",st_cw="";
				char current;
				
				cw=arr[0];
				st_cw=Dictionary.get(cw);
				//System.out.print(st_cw);
				bw.print(st_cw);
				for(int i=1;i<count_numbers;i++)
				{
					pw=cw;
					cw=arr[i];
					st_cw=Dictionary.get(cw);
					if(Dictionary.containsKey(cw))
					{
						//System.out.print(st_cw);
						if(st_cw.equals("\n"))
						{
							bw.print("\r\n");
						}
						else
						{
							bw.print(st_cw);
						}
						st_pw=Dictionary.get(pw);
						char c=st_cw.charAt(0);
						Dictionary.put(index,st_pw+c);
						index++;
					}
					else
					{
						st_pw=Dictionary.get(pw);
						char c=st_pw.charAt(0);
						//System.out.print(st_pw+c);
						if((st_pw+c).equals("\n"))
						{
							bw.print("\r\n");
						}
						else
						{
							bw.print(st_pw+c);
						}
						Dictionary.put(index,st_pw+c);
						index++;
					}
				}
				bw.close();
				fr.close();
				JOptionPane.showMessageDialog(null, "DeCompressed is Complete");
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
