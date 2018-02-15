//Xin Zhou
class HW1_P2 {
	private static byte[] cipherText = new byte[] { -119, 119, 48, -18, 29, 23, -85, 81, 22, -85, 70, 74, -66, 90, 20, -15, 66, 5, -67, 65, 19, -95, 64, 0, -13, 83, 5, -68, 86, 18, -81, 64, 15, -18, 122, 48, -102, 98, 75, -1, 28, 85, -60 };
  

// key may be of different length and - obviously - contain different values

  	static byte[] key = { -50,-50,-50,-50,0,0};
  	static byte[] key_final = { -50,-50,-50,-50,0,0};
  	static String plainText_1 = "GET /"; 
  	static String plainText_2 = "HTTP/1.1";
  	static boolean[] key_found = new boolean[6];

  	static int keyLength = 999;
  	static int key_final_length = 1;


  	private static void findKey(){
  		for(int i = 0; i < plainText_1.length(); i++){
  			bruteForce(i);
  			if(checkKeyLength()) break;
  			
  			key_final_length++;
  		}
  		// for(int i = 0; i < plainText_1.length(); i++){
  		// 	System.out.print(key_final[i] +  " - ");
  		// }
  	}

  	private static void bruteForce(int index){
		for(int ii = 1; ii < keyLength; ii++){
			if(!key_found[index]){
	  			key[index] += 1;
	  			char p = (char) (cipherText[index] ^ key[index]);

	  			if(p == plainText_1.charAt(index) ) {
	  				//System.out.println( "Found: " + p);
	  				key_found[index] = true;
	  				key_final[index] = key[index]; 
	  			}
			}else{
				//System.out.println( "p" + index + " is found - " );
				break;
			}
  		}
  	}

  	private static boolean checkKeyLength(){
  		String a = "";
  		for (int i = 0; i < cipherText.length; i++) 
	      	a += (char) (cipherText[i] ^ key_final[i % key_final_length]);
	    //System.out.println(a);
	    int aLength = a.length();

	    for(int i = 0; i < plainText_2.length(); i++){
	    	if(plainText_2.charAt(i) != a.charAt(a.length() - plainText_2.length() - 1 + i) ) return false;
	    }
	    //System.out.println("YES");
	    return true;
  	}


  	public static void main(String [] args) {
  		//print();

  		findKey();
     	System.out.println("\nKey length: " + key_final_length);	
     	System.out.print("\nPlain Text: ");
		// decrypt whole ciphertext
	    for (int i = 0; i < cipherText.length; i++) 
	      	System.out.print((char) (cipherText[i] ^ key_final[i % key_final_length]));

      System.out.println();
  		// for(int ii = 1; ii < keyLength; ii++){
  		// 	key[0] += 1;
  		// 	// decrypt whole ciphertext
		  //   for (int i = 0; i < cipherText.length; i++) 
		  //     	System.out.print((char) (cipherText[i] ^ key[i % key.length]));
	   //    	System.out.println(" " + key[0]);
  		// }
     }
}


// -119 