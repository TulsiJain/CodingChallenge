import java.util.Map;
import java.lang.*;
import java.util.*;

public class Cards {

    private static int[] singleRound(Stack<Integer> deck) {
	    int[] newList = new int[deck.size()];
	    int j = 0;
	    while (!deck.empty()){
	    	int element1 = deck.pop();
	    	newList[j] = element1;
	    	j++;
	    	if (!deck.empty()){
	    		int element2 = deck.pop();
	    		deck.push(element2);
	    	}
	    }
	    for (int i = 0; i < newList.length/2; i++){
	    	int temp = newList[i];
	    	newList[i] = newList[newList.length -i -1];
	    	newList[newList.length -i -1] = temp;
	    }
	    return newList;
	}

	private static int[] cycles(int[] deck) {
		int[] group = new int[deck.length];
		Arrays.fill(group, 1);
		for ( int i =0; i < deck.length; i++ ){
			int index = i;
			while (deck[index] != i){
				index = deck[index];
				group[i] = group[i] + 1;
			}
		}
		return group;
	}

	private static int computeLCM(int[] deck) {
		int length = deck.length;
		int product = 1;
		for ( int i = 0; i < length; i++ ){
	        int gcd = computeGCD(product, deck[i]);
	        product = product * (deck[i] / gcd);
	    }
		return product;
	}

	private static int computeGCD( int a, int b ){
	    if (b == 0) {
	        return a;
	    }else{
	        return computeGCD(b, a%b);
	    }
	}

	public static void main(String[] args) {
	 	int count = 5;
	 	Stack<Integer> initialStack = new Stack<Integer>();
	 	for ( int i = 0 ; i < count ; i++ ){
	 		initialStack.add(i);
	 	}
	 	System.out.println(computeLCM(cycles(singleRound(initialStack))));
	}
}