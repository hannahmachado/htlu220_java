package lab05;

public class BinarySearchSet {

	public double[] storage;
	private int capacity;
	private int numItems;

	public BinarySearchSet() {
		capacity = 6;
		storage = new double[capacity];
		numItems= 0;
	}

	public void printSet() {
		System.out.print("[");
		for (int i = 0; i < numItems; i++)
			System.out.print(storage[i] + " ");
		System.out.println("]");
	}

	public void printFullSet() {
		System.out.print("[");
		for (int i = 0; i < capacity; i++)
			System.out.print(storage[i] + " ");
		System.out.println("]");
	}

	public boolean isEmpty() {
		if (numItems == 0) {
			return true;
		}
		return false;
	}

	public double size() {
		return numItems;
	}

	public void grow() {
		double[] temp = new double[capacity * 2];
		for (int i = 0; i < capacity; i++) {
			temp[i] = storage[i];
		}
		storage = temp;
		capacity = capacity * 2;
	}

	public String toString() {
		String listElements = "The Elements of the list are: ";
		for (int i = 0; i < numItems; i++) {
			listElements += storage[i] + " ";
		}
		return listElements + "The capacity is: " + capacity + " The current size is: " + numItems; 
	}

	public boolean remove(double value) {
		int index = findIndex(storage, value);
      
		if(contains(value) == false){
			return false;
		}
			if (numItems == 0){
			return false;
			}
		for (int i = index; i < numItems - 1; i++) {
			storage[i] = storage[i + 1];
			storage[(numItems--) - 1] = 0.0;
		}
		return true;
	}

	public int findIndex(double[] storage, double val) {
		int index = -1;
		if (numItems == 0)
			return index;
		for (int i = 0; i < numItems; i++) {
			if (storage[i] == val)
				index = i;
		}
		return index;
	}

	public boolean sequential_add(double newVal) {
		int newValIndex = -1;
		for (int i = 0; i < numItems; i++) {
			if (storage[i] == newVal) {
				return false;
			}
			if (storage[i] >= newVal) {
				newValIndex = i;
			}
		}
		for (int i = numItems - 1; i > newValIndex; i--) {
			storage[i + 1] = storage[i];
		}
		storage[newValIndex] = newVal;
		numItems++;
		return true;
	}

	public boolean contains(double value) {
		int min = 0;
		int max = numItems - 1;
		int mid = (max + min) / 2;
		while (min <= max) {
			
			mid = (max + min) / 2;
			
			if (storage[mid] == value) {
				return true;
			} else if (storage[mid] > value) {
				max = mid - 1;

			} else { // storage[mid]< newVal
				min = mid + 1;
			}
		}
		return false;
	}

	public boolean containsAll(double[] elements) {
		
	 for (double i: elements) {
		 if(contains(i) == false)
			 return false;
	 }	 
		return true;
	}

	public boolean binary_add(double newVal) {
		

		int min = 0;
		int max = numItems - 1;
        int mid= (max + min) / 2;
        
        if(numItems==0){
        	storage[0]=newVal;
        	numItems++;
        	return true;
        }

		while (min <= max) {
			mid = (max + min) / 2;
			
			if (storage[mid] == newVal) {
				return false;
			} else if (storage[mid] > newVal) {
				max = mid - 1;

			} else { // storage[mid]< newVal
				min = mid + 1;
			}
		}
	
		
		 if (numItems >= capacity) {
				grow();
			}
		 
		for (int i = numItems-1; i>= mid; i--) {
			storage[i+1] = storage[i];
		}
		
		storage[mid] = newVal;
		numItems++;
		
		printSet();
		printFullSet();
		
		return true;
	}

	public BinarySearchSet(double[] input) {
		for (int i = 0; i < input.length; i++) {
			binary_add(input[i]);
		}
	}

	public void clear() {
		numItems = 0;
		storage = new double[capacity];
	}

}