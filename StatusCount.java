

/**
 * Filename:   StatusCount.java
 * Project:    Coding Challenge
 * Authors:    Tulsi Jain
 */

public class StatusCount {
	String status;
	int count = 0;

	StatusCount(String statusValue, int countValue){
		this.status = statusValue;
		this.count = countValue;
	}

	public int getCount(){
		return count;
	}

	public String getStatus(){
		return status;
	} 


	public void increaseBy(int value){
		count  = count + value;
	}  
}