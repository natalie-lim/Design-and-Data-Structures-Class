import java.util.ArrayList;
import java.util.Scanner;

public class ArrayUtils {
    
    /* countOdds
     * This method should return the number of odd integers in the array.
     * Odd integers are not divisible by 2.
     * 0 is an even integer.
     */
    public static int countOdds(int[] nums) {
    	if (nums == null) {
    		return 0;
    	}
    	int numOddInts = 0;
    	for (int i = 0; i < nums.length; i++) {
    		if ((nums[i] % 2 == 1 || nums[i] % 2 == -1) && nums[i] != 0) {
    			numOddInts++;
    		}
    	}
    	return numOddInts;
    }
    
    
    /* onDiagonal
     * This method takes a 2D array of integers, and a single integer parameter.
     * It returns true if and only if the value of the parameter lies on a diagonal of the
     * 2D array.
     * 
     * Diagonals are illustrated here (X is on diagonal, . is not):
     * 
     * X.X  X..X  X...X
     * .X.  .XX.  .X.X.  etc.
     * X.X  .XX.  ..X..
     *      X..X  .X.X.
     *            X...X
     *            
     * 2D arrays that are not square do not have diagonals, and should always return false.
     */
    public static boolean onDiagonal(int[][] matrix, int test) {
    	if (matrix == null) {
    		return false;
    	}
    	if (matrix.length != matrix[0].length) {
    		return false;
    	}
    	boolean onDiagonal = false;
    	//Front to back
   		for (int i = 0; i < matrix.length; i++) {
   			if (matrix[i][i] == test) {
   				onDiagonal = true;
    		}
    	}
    	//back to front
    	int i = 0;
    	for (int k = matrix[0].length-1; k >= 0; k--) {
   			if (matrix[i][k] == test) {
    			onDiagonal = true;
    		} else {
   				i++;
   			}
   		}
    	return (onDiagonal);
    }
    
    /* addElements
     * This method takes two 1D integer arrays: list and increment.
     * The list array is mutated such that the ith element of list will be incremented by the ith
     * element of increment.
     * 
     * e.g.             list -> (0, 0, 1, 1,  2,  2)
     *             increment -> (4, 5, 6, 7,  8,  9)
     * after returning, list -> (4, 5, 7, 8, 10, 11)
     * 
     * If increment is not as long as list, treat unspecified values as 0.
     * 
     * e.g.             list -> (0, 0, 1, 1, 2, 2)
     *             increment -> (4, 5, 6, 7)
     * after returning, list -> (4, 5, 7, 8, 2, 2)
     * 
     * If increment has more elements than list, ignore the extra elements.
     */
    public static void addElements(int[] list, int[] increment) {
    	if (list != null && increment != null) {
    		int limit = Math.min(list.length, increment.length);
    		for (int i = 0; i < limit; i++) {
    			list[i] = list[i] + increment[i];
    	
    		}
    	}
    }
    
    /* embiggen
     * Merges two 1D integer arrays into a single 2D integer array.
     * The [i][j] element of the resulting array is whichever value is larger: array1[i] or array2[j].
     * includes if there is nothing before index i
     * [1, 5, 24, 7, 2, 7]
     * [23, 6, 1, 78, 2, 6]
     */
    public static int[][] embiggen(int[] array1, int[] array2) {
    	if ((array1 == null) || (array2 == null)) {
    		return null;
    	}
        int[][] embiggened = new int[array1.length][array2.length];
        for (int i = 0; i < embiggened.length; i++) {
        	for (int j = 0; j < embiggened[0].length; j++) {
        		int bigger = Math.max (array1[i], array2[j]);
        		embiggened[i][j] = bigger;
        	}
        }
        return embiggened;
    }
    
    /* getPeakIndex
     * A "mountain array" is
     * an array for which there is an index i < array.length such that
     * the array is strictly increasing before index i, and
     * decreasing after index i.
     * Index i would be the max, the "peak".
     * If the array is a mountain array, the index of the peak is returned.
     * If the array is NOT a mountain array, -1 is returned!
     * { 0, 2, 9, 100, 8, 3, 0, -1 }
     */
    public static int getPeakIndex(int[] array) {
    	if (array == null) {
    		return -1;
    	}
    	if (array.length == 1) {
    		return 0;
    	}
    	int peak = -1;
    	for (int i = 1; i < array.length; i++) {
    		if (array[i] == array[i-1]) {
    			return -1;
    		}
    		else if (array[i] < array[i-1]) {
    			peak = i-1;
    			for (int k = i; k < array.length; k++) {
    				if (array[k] >= array[k-1]) {
    					return -1;
    				}
    			}
    			return peak;
    		} else {
    			peak = i;
    		}
    	}
    	return peak;
    }
}