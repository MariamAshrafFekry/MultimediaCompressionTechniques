package p;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class DeCompress
{
	public String FileChooser()
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
	DeCompress() throws FileNotFoundException
	{
		String StringPath=FileChooser();
		if(StringPath!=""&&StringPath.contains(".txt"))
		{
			File f1=new File(StringPath);
			Scanner input=new Scanner(f1);
			String Name="";
			for(int i=0;i<StringPath.length()-4;i++)
			{
				Name+=StringPath.charAt(i);
			}
			int n=input.nextInt();
			int NumOfVectors=(int)Math.pow(n, 2);
			int wid=input.nextInt();
			int len=input.nextInt();
			
	        Map<String,ArrayList<ArrayList<Integer>>> map = new HashMap<>();
			for(int i=0;i<NumOfVectors;i++)
			{
				String s=input.next();
				ArrayList<ArrayList<Integer>>array=new ArrayList<ArrayList<Integer>>();
				for(int j=0;j<len;j++)
				{
					ArrayList<Integer>arr=new ArrayList<Integer>();
					for(int k=0;k<wid;k++)
					{
						arr.add(input.nextInt());
					}
					array.add(arr);
				}
				
				map.put(s,array);
			}
			
			int width=input.nextInt();
			int height=input.nextInt();
			int pixels[][]=new int[height][width];
			ArrayList<ArrayList<Integer>>list=new ArrayList<ArrayList<Integer>>();

			for(int i=0;i<height;i+=len)
			{
				for(int j=0;j<width;j+=wid)
				{
					String x=input.next();
					list=(map.get(x));
					for(int k=0;k<list.size();k++)
					{
						for(int l=0;l<list.get(k).size();l++)
						pixels[i+k][j+l]=list.get(k).get(l);
					}
				}
			}
			
			WriteImage.writeImage(pixels,Name+"image.jpg",width,height);

			  JOptionPane.showMessageDialog(null, "DeCompression is Complete");
			}
			
		else if(StringPath!=""&&StringPath.contains(".txt"))
		 {
			JOptionPane.showMessageDialog(null, "File Is Already DeCompressed");
		 }
		else
		 {
			JOptionPane.showMessageDialog(null, "Invalid File");
		 }
	}
}
