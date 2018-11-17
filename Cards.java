import java.util.Map;
import java.lang.*;
import java.util.*;

public class Cards {

	// Single Round
    private static int[] singleRound(Deque<Integer> deck) {
	    int[] afterRound = new int[deck.size()];
	    int j = 0;
	    Iterator iterator = deck.iterator(); 
	    while (iterator.hasNext()){
	    	int lastElement = deck.pollLast();
	    	afterRound[j] = lastElement;
	    	j++;
	    	if (iterator.hasNext()){
	    		lastElement = deck.pollLast();
	    		deck.addFirst(lastElement);
	    	}
	    }
	    for (int i = 0; i < afterRound.length/2; i++){
	    	int temp = afterRound[i];
	    	afterRound[i] = afterRound[afterRound.length -i -1];
	    	afterRound[afterRound.length -i -1] = temp;
	    }
	    return afterRound;
	}

	private static int[] cycles(int[] deck) {
		int[] group = new int[deck.length];
		Arrays.fill(group, 1);
		for ( int i = 0; i < deck.length; i++){
			int index = i;
			while (deck[index] != i){
				index = deck[index];
				group[i] = group[i] + 1;
			}
		}
		return group;
	}

	// Compute LCM in a array
	private static int computeLCM(int[] deck) {
		int length = deck.length;
		int product = 1;
		for ( int i = 0; i < length; i++ ){
	        int gcd = computeGCD(product, deck[i]);
	        product = product * (deck[i] / gcd);
	    }
		return product;
	}

	// Compute GCD
	private static int computeGCD(int a, int b){
	    if (b == 0) {
	        return a;
	    }else{
	        return computeGCD(b, a%b);
	    }
	}

	public static void main(String[] args) {
	 	int count = 52;
	 	Deque<Integer> deck = new LinkedList<Integer>(); 
	 	for ( int i = 0 ; i < count ; i++ ){
	 		deck.addFirst(i);
	 	}
	 	int[] afterRound = singleRound(deck);
	 	int[] cycle = cycles(afterRound);
	 	System.out.println("Number of rounds required is"  + computeLCM(cycle));
	}
}