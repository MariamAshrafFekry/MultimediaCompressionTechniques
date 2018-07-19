package p;

import java.util.ArrayList;

public class Node 
{

    ArrayList<ArrayList<Integer>> Average;
    ArrayList<ArrayList<ArrayList<Integer>>> Elements;
    String Code;

    public Node() 
    {
        Average = new ArrayList<>();
        Elements = new ArrayList<>();
        Code = "";
    }
    
    public void CalculateAverage() 
    {
        int sum = 0;
        for (int k = 0; Elements.size() > 0 && k < Elements.get(0).size(); ++k) {
            ArrayList<Integer> tmp = new ArrayList<>();

            for (int l = 0; l < Elements.get(0).get(0).size(); ++l) {
                sum = 0;
                for (int m = 0; m < Elements.size(); ++m) {
                    sum += Elements.get(m).get(k).get(l);
                }
                tmp.add((int) (sum * 1.0 / Elements.size()));
            }
            Average.add(tmp);

        }
    }
}
