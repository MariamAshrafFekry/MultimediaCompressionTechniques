package p;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class Compress 
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
    public Compress(int bits, int length, int width) throws IOException 
    {
    	String StringPath=FileChooser();
		String Name="";
		for(int i=0;i<StringPath.length()-4;i++)
		{
			Name+=StringPath.charAt(i);
		}
		File f1=new File(Name+".txt");
		FileWriter fw=new FileWriter(f1);
		PrintWriter pw=new PrintWriter(fw);
		
	  int pixel[][]=  ReadImage.readImage(StringPath);
	  
        Queue<Node> queue = new LinkedList<>();
        Map<ArrayList<ArrayList<Integer>>, String> Code = new HashMap<>();
        Node root = new Node();
        int pixels[][] = new int[pixel.length + (length - (pixel.length % length))][pixel[0].length + (width - (pixel[0].length % width))];
        for (int i = 0; i < pixel.length; ++i) 
        {
            for (int j = 0; j < pixel[i].length; ++j)
            {
                pixels[i][j] = pixel[i][j];
            }
        }
        for (int i = 0; i < pixels.length; i += length)
        {
            for (int j = 0; j < pixels[i].length; j += width) 
            {
                ArrayList<ArrayList<Integer>> tmp2 = new ArrayList<>();
                for (int k = 0; k < length; ++k) 
                {
                    ArrayList<Integer> tmp1 = new ArrayList<>();
                    for (int l = 0; l < width; ++l) 
                    {
                        tmp1.add(pixels[i + k][l + j]);
                    }
                    tmp2.add(tmp1);
                }
                root.Elements.add(tmp2);
            }
        }
        ArrayList<ArrayList<ArrayList<Integer>>> Values=new ArrayList<>();
        for (int i = 0; i < root.Elements.size(); ++i)
        {
                    ArrayList<ArrayList<Integer>> t1 = new ArrayList<>();
                    for (int l = 0; l < root.Elements.get(0).size(); ++l)
                    {
                        ArrayList<Integer> t2 = new ArrayList<>();
                        for(int k=0;k<root.Elements.get(0).get(0).size();++k)
                        {
                            t2.add(root.Elements.get(i).get(l).get(k));
                        }
                        t1.add(t2);
                    }
                    Values.add(t1);
                }
        root.CalculateAverage();
        queue.add(root);

        for (int k = 0; k < bits; ++k) 
        {
            for (int j = 0; j < (1 << k); ++j) 
            {
                Node parent = queue.poll();
                Node left = new Node();
                Node right = new Node();
                ArrayList<ArrayList<Integer>> t11 = new ArrayList<>();

                for (int i = 0; i < parent.Average.size(); ++i)
                {
                    ArrayList<Integer> t1 = new ArrayList<>();
                    for (int l = 0; l < parent.Average.get(0).size(); ++l) 
                    {
                        t1.add(parent.Average.get(i).get(l) - 1);
                    }
                     left.Average.add(t1);
                }
                ArrayList<ArrayList<Integer>> t22 = new ArrayList<>();
                left.Code = parent.Code + "0";
                for (int i = 0; i < parent.Average.size(); ++i)
                {
                    ArrayList<Integer> t2 = new ArrayList<>();
                    for (int l = 0; l < parent.Average.get(0).size(); ++l)
                    {
                        t2.add(parent.Average.get(i).get(l) + 1);
                    }
                    right.Average.add(t2);
                }
                right.Code = parent.Code + "1";
                queue.add(left);
                queue.add(right);
            }

            for (int i = 0; i < Values.size(); ++i) 
            {
                int idx = 0, mn = 1000000000, cnt1 = 0, cnt2 = 0;

                for (Node element : queue) {
                    if (dist(element.Average, Values.get(i)) < mn) 
                    {
                        mn = dist(element.Average, Values.get(i));
                        idx = cnt1;
                    }
                    cnt1++;
                }
                for (Node element : queue)
                {

                    if (cnt2 == idx)
                    {
                        element.Elements.add(Values.get(i));
                        Code.put(Values.get(i), element.Code);

                        break;
                    }
                    cnt2++;
                }

            }
            for (Node element : queue) 
            {
                element.CalculateAverage();
            }

        }

        boolean swap = true;

        while (swap) 
        {
            swap = false;
            for (Node e1 : queue)
            {
                for (int i = 0; i < e1.Elements.size(); ++i) 
                {
                    int idx = 0, mn = dist(e1.Average, e1.Elements.get(i)), cnt1 = 0, cnt2 = 0;
                    for (Node e2 : queue) {
                        if (dist(e2.Average, e1.Elements.get(i)) < mn)
                        {
                            e2.Elements.add(e1.Elements.get(i));
                            Code.put(e1.Elements.get(i), e2.Code);
                            e1.Elements.remove(i);
                            swap = true;
                            --i;
                            break;
                        }
                    }
                }
            }
        }
        pw.println(bits);
        pw.println(length+" "+width);

        while(!queue.isEmpty())
        {
        	Node x=queue.poll();
        	pw.println(x.Code);
        	for(int i=0;i<width;i++)
        	{
        		for(int j=0;j<length;j++)
        		{
        			pw.print(x.Average.get(i).get(j)+" ");
        		}
        		pw.println();
        	}
        }
        pw.println(pixels.length+" "+pixels[0].length);
        for (int i = 0; i < Values.size(); ++i) 
        {
            pw.print(Code.get(Values.get(i))+" ");
        }
        fw.close();
        pw.close();
        JOptionPane.showMessageDialog(null, "Compression is Complete");
    }

    private static int dist(ArrayList<ArrayList<Integer>> vec1, ArrayList<ArrayList<Integer>> vec2) 
    {
        int distance = 0;
        for (int i = 0; i < vec1.size() && i < vec2.size(); ++i) {
            for (int j = 0; j < vec1.get(i).size() && j < vec2.get(i).size(); ++j)
            {
                distance += (vec1.get(i).get(j) - vec2.get(i).get(j)) * (vec1.get(i).get(j) - vec2.get(i).get(j));
            }
        }
        return (int) Math.sqrt(distance);
    }
}
