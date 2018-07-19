package p;

import java.io.File;
import java.io.FileNotFoundException;
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
			int NumOfBits=input.nextInt();
			int n=(NumOfBits);
			int arr[]=new int[n];
			for(int i=0;i<n;i++)
			{
				arr[i]=input.nextInt();
			}
			int width=input.nextInt();
			int height=input.nextInt();
			int pixels[][]=new int[height][width];
			for(int i=0;i<height;i++)
			{
				for(int j=0;j<width;j++)
				{
					pixels[i][j]=input.nextInt();
				}
			}
			for(int i=0;i<width;i++)
			{
				for(int j=0;j<height;j++)
				{
					pixels[i][j]=arr[pixels[i][j]];
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
