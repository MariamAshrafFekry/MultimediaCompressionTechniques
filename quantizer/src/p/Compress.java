package p;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Compress 
{
	Node root;
	int arr[][];
	
	public double CalcDifference(int x,double y)
    {
        return Math.abs(y-x);
    }
  
	public int []Function(int bits,int tmp[][],int width,int height)
	{
	    root=new Node();
	    int avg=0;
	    arr=new int[height][width];
	    for(int i=0;i<width;i++)
	    {
	    	for(int j=0;j<height;j++)
	    	{
	    		arr[j][i]=tmp[j][i]; 
	    		avg+=tmp[j][i];
	    	}
	    }
	    root.Average=avg/tmp.length;
	    return(build(bits,root,width,height));
	}
	 private int[] build(int bits,Node n,int width,int height)
	 {
		 int array[]=new int[1000];
		 int t=0;
	        ArrayList<Node>q=new ArrayList();
	        q.add(n);
	        Node tmp=q.get(0);
	        Node left=new Node();
	        Node right=new Node();
	        left.Average=tmp.Average-1;
	        right.Average=tmp.Average+1;
	        q.add(left);
	        q.add(right);
	        q.remove(0);
	       int lvl=1;
	    	   while(!q.isEmpty())
	        {
	           
	            for(int i=0;i<width;++i)
	            {
	            	for(int k=0;k<height;k++)
	            	{
	            		double mn=1000000;
	                     int idx=0;
	                for(int j=0;j<q.size();++j)
	                {
	                    if(CalcDifference(arr[k][i],q.get(j).Average)<mn)
	                    {
	                        mn=CalcDifference(arr[k][i],q.get(j).Average);
	                        idx=j;
	                    }
	                }
	                
	                q.get(idx).elements.add(arr[k][i]);
	              }
	            }
	            for(int j=0;j<q.size();++j)
	            {
	                q.get(j).CalcAverage();
	            }
	            for(int i=0;i<2*lvl;++i)
	            {
	                    tmp=q.get(0);
	                    left=new Node();
	                    right=new Node();
	                    left.Average=tmp.Average-1;
	                    right.Average=tmp.Average+1;
	                    if(lvl<bits)
	                    {
	                        q.add(left);
	                        q.add(right);
	                    }
	                    else
	                    {
	                    	array[t]=q.get(0).Average;
	                    	t++;
	                    }
	                   // else System.out.println("Average: "+q.get(0).average+"   "+q.get(0).elements.toString());
	                q.remove(0);
	            }
	            if(bits>lvl)
	            lvl++;
	            else break;
	        } 
	    	  return array;
	 }
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
	Compress(int bits) throws IOException
	{
		String StringPath=FileChooser();
		String Name="";
		for(int i=0;i<StringPath.length()-4;i++)
		{
			Name+=StringPath.charAt(i);
		}
	  int pixels[][]=  ReadImage.readImage(StringPath);
	    
	 int array[]=Function(bits,pixels,pixels.length,pixels[0].length);
		File f1=new File(Name+".txt");
		FileWriter fw=new FileWriter(f1);
		PrintWriter pw=new PrintWriter(fw);
		
		pw.println((int)Math.pow(2,bits));
		for(int i=0;i<(1<<bits);i++)
		{
			pw.print(array[i]+" ");
		}
		pw.println();
		pw.println(pixels.length+" "+pixels[0].length);
		for(int i=0;i<pixels.length;i++)
		{
			for(int j=0;j<pixels[0].length;j++)
			{
				int min=999999; int index=-1;
				for(int k=0;k<array.length;k++)
				{
					if(CalcDifference(array[k],pixels[i][j])<min)
					{
						min=(int) CalcDifference(array[k],pixels[i][j]);
						index=k;
					}
				}
				pw.print(index+" ");
			}
		}
		fw.close();
		pw.close();
		 JOptionPane.showMessageDialog(null, "Compression is Complete");
	}
}
