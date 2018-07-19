package hu;

import java.util.Comparator;

public class Comparing implements Comparator<Node>
{
	public int compare(Node n1, Node n2) 
	{
		if(n1.Frequence>n2.Frequence)
			return 1;
		else if(n1.Frequence<n2.Frequence)
			return -1;
		return 0;
	}
}