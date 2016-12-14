package cn.niot.service;

import java.util.Random;


public class RandomChar {
	private static RandomChar randomChar = new RandomChar();

	// public static Connection connection = null;
	public static RandomChar getRandomChar() {
		return randomChar;
    }
	
	public static char randomizeArray(char [] charList, int len){
		Random r1 = new Random();
	    if(len==1)
	    	return charList[0];
		char [] resCharArray = new char [len];
		char resChar = 'a';
		int i = 0;
		for (i = 0; i < len; i++){
			resCharArray[i] = charList[i];
		}
		
		int index = 0;
		char tmp = '0';
	    for(i = 0; i < len; i++)  
	    {	    	
	    	do {
	    		
	    		//double random = Math.random();
	    		double random = r1.nextDouble();
		        index = (int) Math.floor(random * (len - i)); 
		        if (index >= len){
		        	index = len - 1;
		        }
		        
		        if (index < 0){
		        	index = 0;
		        }
	    	}while (index == i); 
	    	
	    	tmp = resCharArray[i];  
            resCharArray[i] = resCharArray[index];  
            resCharArray[index] = tmp;	    		  
	    } 
	    double rand = r1.nextDouble();
        int index_res = (int) Math.floor(rand * len); 
        if (index_res >= len){
        	index_res = len - 1;
        }
        
        if (index_res < 0){
        	index_res = 0;
        }
	    resChar = resCharArray[index_res];	    
		return resChar;
	}
	
	public static int generateRandomInt(int maxInt) {
		int [] intArray = new int[maxInt];
		if (1 == maxInt) {
			return 0;
		}

		int i = 0;
		for (i = 0; i < maxInt; i++) {
			intArray[i] = i;
		}

		int index = 0;
		int tmp = 0;
		int count = 0;
		int maxcount = 5;
		Random r1=new Random();
		for (i = 0; i < maxInt; i++) {
			do {
				double random = r1.nextDouble();
				index = (int) Math.floor(random * (maxInt - i));
				if (index >= maxInt) {
					index = maxInt - 1;
				}

				if (index < 0) {
					index = 0;
				}
				
				count = count + 1;
			} while ((index == i) && (count < maxcount));

			tmp = intArray[i];
			intArray[i] = intArray[index];
			intArray[index] = tmp;
		}
		double rand = r1.nextDouble();
		int index_res = (int) Math.floor(rand * maxInt);
		if (index_res >= maxInt) {
			index_res = maxInt - 1;
		}

		if (index_res < 0) {
			index_res = 0;
		}
		return intArray[index_res];
	}

	
}

