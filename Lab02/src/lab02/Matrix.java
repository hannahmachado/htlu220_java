package lab02;

public class Matrix {
	//global variables
	
	int numRows;
	int numColumns;
	int data[][];
	
	// default constructor
	public Matrix(){}
	
	// constructor 1 - Constructor for new zero matrix
	public Matrix(int rowDim, int colDim){
		numRows = rowDim;
		numColumns= colDim;
		data = new int[numRows][numColumns];
		
		for(int i=0; i<numRows; i++){
			for( int j= 0; j<numColumns; j++){
				data[i][j] = 0;
			}
		}
		/*
		* TODO: write a constructor that would create a matrix
		* of correct size and initialize it to 0. 
		*/
	}
	
	
	// constructor 2 - Constructor with data for new matrix (automatically determines dimensions)
     public Matrix(int d[][])
	{
    	numRows= d.length;
    	numColumns= d[0].length;
    	data = new int[numRows][numColumns];
    	  for(int i=0; i<numRows; i++){
			 for( int j= 0; j<numColumns; j++){
				data[i][j] = d[i][j];
			}
			 
		}
    
		/* 1) put the numRows to be the number of 1D arrays in the 2D array
		*  2) specify the numColumns and set it
		*  3) be careful of special cases you are supposed to handle them properly
		*  4) create a new matrix to hold the data
		*  5) copy the data over
		*/
	}	
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		String s = "\n";
		 for (int i=0; i<numRows; i++){
			 for (int j=0; j<numColumns; j++){
				 s+= (data[i][j]+" ");
			 }
			 s+="\n";
		 }
		
		/*
		 * TODO: replace the below return statement with the correct code, you must return a String that represents this 
		 * 			matrix, as specified in the instruction for M1 - anything else IS NOT acceptable
		 */
		return s; // placeholder		
	}
	
	// *** you will implement the rest of the methods for your assignment
	// *** don't touch them before finishing the lab portion
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public boolean equals(Object o)
	{
		if(!(o instanceof Matrix)) // make sure the Object we're comparing to is a Matrix
			return false;
		Matrix m = (Matrix)o; // if the above was not true, we know it's safe to treat 'o' as a Matrix
		
		/*
		 * TODO: replace the below return statement with the correct code, you must return the correct value
		 * 			after determining if this matrix is equal to the input matrix
		 */
	
	          for(int row=0; row<data.length; row++){
	             for(int col=0; col== data[row].length;col++){
	            	 if(this.data[row][col]!= m.data[row][col]){ 
	            		 return false;
	            	 }
	             }
	          }
	     
		return true; // placeholder
	}
	/*
	 * TODO: replace the below return statement with the correct code, 
	 *  This function must check if the two matrices are compatible for multiplication, if not, return null.
	 *  If they are compatible, determine the dimensions of the resulting matrix, and fill it in with
	 *  the correct values for matrix multiplication
	 */

	public Matrix times(Matrix m){
		m.numRows= m.data.length;
		m.numColumns= m.data[0].length;
		this.numRows= this.data.length;
		this.numColumns= this.data[0].length;
		if (this.numColumns==m.numRows){
			Matrix result= new Matrix ();
			result.data= new int[this.numRows][m.numColumns];
			for(int i=0; i<this.numRows;i++){
			 	  for (int j=0; j<m.numColumns; j++){
			 		  for(int k=0; k<this.numColumns;k++){
			 			 result.data[i][j] = this.data[i][k] + m.data[k][j];  
			 		  }
				  }
			}
			return result;
			
		}

		return null; // placeholder
	}
	
	public Matrix plus(Matrix m)
	{
		/*
		 * TODO: replace the below return statement with the correct code, 
		 *  This function must check if the two matrices are compatible for addition, if not, return null.
		 *  If they are compatible, create a new matrix and fill it in with
		 *  the correct values for matrix addition
		 */	
		m.numRows= m.data.length;
		m.numColumns= m.data[0].length;
		this.numRows= this.data.length;
		this.numColumns= this.data[0].length;
		 if(m.numRows== this.numRows && m.numColumns==this.numColumns){
			 Matrix result= new Matrix ();
			 result.data= new int[this.numRows][this.numColumns];
		    for(int i=0; i<m.numRows;i++){
		 	  for (int j=0; j<m.numColumns; j++){
				result.data[i][j] = this.data[i][j] + m.data[i][j];
			  }
		    }
		 return result; 
     	}	
	return null; // placeholder
   }
}