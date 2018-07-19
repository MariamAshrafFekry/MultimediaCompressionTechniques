package p;

import java.util.ArrayList;

public class Node
{
	int Average;
	ArrayList<Integer>elements;
	public Node()
	{
		Average=0;
		elements=new ArrayList();
	}
	public Node(int a,ArrayList<Integer>ar)
	{
		Average=a;
		elements=ar;
	}
	public void CalcAverage()
    {
        int sum=0;
        for(int i=0;i<elements.size();++i)
        {
            sum+=elements.get(i);
        }
        Average=sum/Math.max(1,elements.size());
    }

}
