import java.util.Map;
import java.lang.*;
import java.util.*;

public class Cards {

    private static int[] singleRound(Deque<Integer> deck) {
	    int[] afterRound = new int[deck.size()];
	    int j = 0;
	    Iterator iterator = deck.iterator(); 
	    while (iterator.hasNext()){
	    	int element1 = deck.pollLast();
	    	System.out.println(element1);
	    	afterRound[j] = element1;
	    	j++;
	    	if (iterator.hasNext()){
	    		int element2 = deck.pollLast();
	    		deck.addFirst(element2);
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

	private static int computeLCM(int[] deck) {
		int length = deck.length;
		int product = 1;
		for ( int i = 0; i < length; i++ ){
	        int gcd = computeGCD(product, deck[i]);
	        product = product * (deck[i] / gcd);
	    }
		return product;
	}

	private static int computeGCD(int a, int b){
	    if (b == 0) {
	        return a;
	    }else{
	        return computeGCD(b, a%b);
	    }
	}

	public static void main(String[] args) {
	 	int count = 4;
	 	Deque<Integer> deque = new LinkedList<Integer>(); 
	 	for ( int i = 0 ; i < count ; i++ ){
	 		deque.addLast(i);
	 	}
	 	int[] afterRound = singleRound(deque);
	 	System.out.println(computeLCM(cycles(afterRound)));
	}
}