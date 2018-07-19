package hu;


public class Node 
{
	int Frequence;
	char Symbol;
	Node Right;
	Node Left;
	Node()
	{
	}
    Node(Node l,Node r,char s,int f)
	{
		Symbol=s;
		Frequence=f;
		Left=l;
		Right=r;
	}
}
