import java.util.HashMap;
import java.util.Map;
import java.lang.*;

public class MoveRobot {

    private static String[] MoveRobot(int[] rightMostPosition, String robotFist, String robotFistCommand, String robotSecond, String robotSecondCommand) {
    	StringBuilder firstCurrentPosition = new StringBuilder(robotFist);
        for (int i = 0; i < robotFistCommand.length(); i++){
        	char current = robotFistCommand.charAt(i);
        	if (current == 'L' || current == 'R'){
        		helperFunction(firstCurrentPosition, current);
        	}else if (current == 'M'){
        		move(firstCurrentPosition);
        	}
        }

        StringBuilder secondCurrentPosition = new StringBuilder(robotSecond);
        for (int i = 0; i < robotSecondCommand.length(); i++){
        	char current = robotSecondCommand.charAt(i);
        	if (current == 'L' || current == 'R'){
        		helperFunction(secondCurrentPosition, current);
        	}else if (current == 'M'){
        		move(secondCurrentPosition);
        	}
        }
        String[] output = {firstCurrentPosition.toString(), secondCurrentPosition.toString()};
        return output;
    }

    private static void helperFunction(StringBuilder currentPosition, char instruction){
    	int length = currentPosition.length();
    	if (instruction == 'R'){
    		if (currentPosition.charAt(length -1) == 'N'){
	    		currentPosition.setCharAt(2, 'E');
	    	}else if (currentPosition.charAt(length -1) == 'E'){
	    		currentPosition.setCharAt(2, 'S');
	    	}else if (currentPosition.charAt(length -1) == 'S'){
	    		currentPosition.setCharAt(2, 'W');
	    	}else if (currentPosition.charAt(length -1) == 'W'){
	    		currentPosition.setCharAt(2, 'N');
	    	}
    	}else if (instruction == 'L'){
    		if (currentPosition.charAt(length -1) == 'N'){
	    		currentPosition.setCharAt(2, 'W');
	    	}else if (currentPosition.charAt(length -1) == 'E'){
	    		currentPosition.setCharAt(2, 'N');
	    	}else if (currentPosition.charAt(length -1) == 'S'){
	    		currentPosition.setCharAt(2, 'E');
	    	}else if (currentPosition.charAt(length -1) == 'W'){
	    		currentPosition.setCharAt(2, 'S');
	    	}
    	}
    } 

    private static void move(StringBuilder currentPosition){
    	int length = currentPosition.length();
    	int zero = (int) '0';
    	int coordinate = -1;
    	int newPosition = 0;
    	if (currentPosition.charAt(length -1) == 'N'){
    		newPosition = currentPosition.charAt(1) - zero + 1; 
	    	coordinate = 1;
    	}else if (currentPosition.charAt(length -1) == 'E'){
    		newPosition = currentPosition.charAt(0) - zero + 1;
	    	coordinate = 0;
    	}else if (currentPosition.charAt(length -1) == 'S'){
    		newPosition = currentPosition.charAt(1) - zero - 1; 
	    	coordinate = 1;
    	}else if (currentPosition.charAt(length -1) == 'W'){
    		newPosition = currentPosition.charAt(0) - zero - 1;
    		coordinate = 0;
    	}
    	char newChar = (char) (zero + newPosition);
    	currentPosition.setCharAt(coordinate, newChar);
    }

    public static void main(String[] args) {
        int[] rightMostPosition = {5,5};
        String robotFist = "12N";
        String robotFistCommand = "LMLMLMLMM";
        String robotSecond = "33E";
        String robotSecondCommand = "MMRMMRMRRM";
        String[] newPosition = MoveRobot(rightMostPosition, robotFist, robotFistCommand, robotSecond, robotSecondCommand );
        for ( int i =0 ; i < newPosition.length ; i++ ){
        	System.out.println(newPosition[i]);
        }
    }
}
