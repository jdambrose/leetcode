package hackerrank.interviewprep.trees.hoffmanDecoding;

// https://www.hackerrank.com/challenges/tree-huffman-decoding/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=trees
class Decoding {

/*  
	class Node
		public  int frequency; // the frequency of this tree
    	public  char data;
    	public  Node left, right;
    
*/ 
	void decode(String s, Node root) {
        
		String solution = "";
		
		Node currentNode = root;
		
		for(char c: s.toCharArray()) {
			
			// Gets direction of the Node
			if(c == '0') {
				currentNode = currentNode.left;
			}else if(c == '1') {
				currentNode = currentNode.right;
			}
			
			// Not a null value, we have reached a leaf
			if(currentNode.data != '\u0000') { // Default null char
				solution = solution + String.valueOf(currentNode.data);
				currentNode = root; // Reset to start
			}
		}
       
		System.out.println(solution);
	}

}
