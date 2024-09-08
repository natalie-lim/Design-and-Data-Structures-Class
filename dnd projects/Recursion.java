import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class Recursion {
	//How many subsets are there of the numbers 1...n
	// that don't contain any consecutive integers?
	public static long nonConsecutiveSubsets(int n) {
		if (n<=0) {
			return 1;
		}
		return nonConsecutiveSubsets(n-2) + nonConsecutiveSubsets(n-1);

	}

	//A kid at the bottom of the stairs can jump up 1, 2, or 3 stairs at a time.
	//How many different ways can they jump up n stairs?
	// Jumping 1-1-2 is considered different than jumping 1-2-1
	public static long waysToJumpUpStairs(int n) {
		if (n==1 || n==0) {
			return 1;
		}
		if (n<0) {
			return 0;
		}
		return waysToJumpUpStairs (n-1) + waysToJumpUpStairs(n-2) + waysToJumpUpStairs(n-3);
	}

	//Prints the value of every node in the singly linked list with the given head, but in reverse
	public static void reverseList(ListNode head) {
		if (head == null) {
			System.out.println();
		}
		else if (head.getNext() == null) {
			System.out.println (head.getValue ());
		} else {
			reverseList (head.getNext()); // T(k-1)
			System.out.println (head.getValue()); // T(k) = 1 + T(k-1)
		}
	}

	//For the given 2D array of Strings, replaces the String at index[x][y]
	//with "infected" unless the String is "vaccinated";
	// also, any Strings they are orthogonally adjacent to
	//that are not "vaccinated" will also be infected, and any adjacent to
	//them as well etc.
	
	//starts in the starting coordinate then spreads until it either hits an edge, hits a vaccinated square, or hits an infected square (otherwise infinite)
	public static void infect(String[][] grid, int x, int y) {
		int height = grid.length;
		int width = grid[0].length;
		if (y < width && x < height && y >= 0 && x >= 0  
				&& !Objects.equals(grid[x][y], "vaccinated") && !Objects.equals(grid[x][y], "infected")) {
			grid[x][y] = "infected";
			infect(grid, x + 1, y);
			infect(grid, x - 1, y);
			infect(grid, x, y + 1);
			infect(grid, x, y - 1);
		}
	}
	
	//List contains a single String to start.
	//Prints all the permutations of str on separate lines
	//You may assume that str has no repeated characters
	//Order is your choice
	public static void permute(String str) {
		helpPermute("",str);
	}
	//adds a letter to the prefix, adds all the letters possible to that prefix, adds all the letters possible to the first two letters, and so forth
	public static void helpPermute (String prefix, String remaining) {
		if (remaining.length() == 0) {
			System.out.println (prefix);
		} else {
			for (int i = 0; i < remaining.length(); i++) {
				helpPermute  ((prefix + remaining.substring(i, i+1)), remaining.substring(0, i) + remaining.substring(i+1));
			}
		}
	}
	

	//Prints all the subsets of str on separate lines
	//You may assume that str has no repeated characters
	//For example, subsets("abc") would print out "", "a", "b", "c", "ab", "ac", "bc", "abc"
	
	//  Below are all the subsets of __bc__ with prefix __a__
	// abc, ab, ac, a AND NOW: bc, b, c, ""
	// dabc, dab, dac, da, dbc, db, dc, d, AND NOW abc, ab, ac, a AND NOW: bc, b, c, ""
	// Below are all the subsets of c with prefix b
	// bc, b AND NOW c ""
	
	//Order is your choice
	
	public static void subsets(String str) {
		helpSubsets ("", str);
		System.out.println();
	}
	
	//prints the prefix, attached to the subsets of the suffix
	public static void helpSubsets(String prefix, String remaining) {
		if (remaining.length() == 0) {
			System.out.print ("");
		} else {
			for (int i = 0; i < remaining.length(); i++) {
				helpSubsets  ((prefix + remaining.substring(i, i+1)), remaining.substring(i+1));
				System.out.println (prefix + remaining.substring(i, i+1));
			}
		}
	}

	//Performs a quickSort on the given array of ints
	//Use the middle element (index n/2) as the pivot
	//make a tester that generates random array of random size
	public static void quickSort(int[] ints) {
		helpQuickSort(ints, 0, ints.length-1); 
	}
	public static void helpQuickSort(int[] ints, int startIdx, int endIdx) {
		if (startIdx != endIdx && endIdx < ints.length && startIdx >= 0 && startIdx <= endIdx) {
			int frontIdx = startIdx;
			int backIdx = endIdx;
			int pivotIdx = startIdx + (endIdx-startIdx)/2;
			int pivot = ints[pivotIdx];
			while (frontIdx < backIdx) {
				int frontValue = ints[frontIdx];
				int backValue = ints[backIdx];
				//if the back value is the pivot but it needs to be exchanged with the front value
				//pivot index becomes the front index
				if (backIdx == pivotIdx && frontValue >= pivot) {
					ints[frontIdx] = backValue;
					ints[backIdx] = frontValue;
					pivotIdx = frontIdx;
					backIdx--;
				}
				//if the front value is the pivot but it needs to be exchanged with the back value
				//pivot index becomes the back index
				else if (frontIdx == pivotIdx && backValue <= pivot) {
					ints[frontIdx] = backValue;
					ints[backIdx] = frontValue;
					pivotIdx = backIdx;
					frontIdx++;
				}
				//if the back value and the front value should be exchanged
				else if (frontValue > pivot && backValue < pivot) {
					ints[frontIdx] = backValue;
					ints[backIdx] = frontValue;
					backIdx--;
					frontIdx++;
				//if the front index hits the pivot but none of the requirements for switching have been met
				//if the front value should be switched but the backvalue isn't less than the pivot value
				} else if ((frontIdx == pivotIdx && backValue > frontValue) ||  (frontValue > pivot)) {
					backIdx--;
				}
				//if the back index hits the pivot but none of the requirements for switching have been met
				//if the back value should be switched but the frontValue isn't greater than the pivot value
				else if ((backIdx == pivotIdx && frontValue < backValue) || (backValue < pivot)) {
					frontIdx++;
				} else {
					backIdx--;
					frontIdx++;
				}
			}
			if (endIdx - startIdx > 1) {
				helpQuickSort(ints, startIdx, pivotIdx - 1);
				helpQuickSort(ints, pivotIdx+1, endIdx);
			}
		}
	}


	//Prints a sequence of moves (one on each line)
	// to complete a Towers of Hanoi problem with
	//n disks starting on tower 0, ending on tower 1.
	// The towers are number 0, 1, 2, and each move should be of
	//the form "1 -> 2", meaning "take the top disk of tower 1 and 
	//put it on tower 2" etc.
	//recursive helper method
	public static void solveHanoi(int n) {
		helpSolveHanoi (n, 0, 1);
	}
	//moves a specified number of disks from a start peg to the target peg
	public static void helpSolveHanoi(int n, int startTower, int targetTower) {
		int nonTarget = 3 - (startTower + targetTower);
		if (n == 1) {
			System.out.println (startTower + " -> " + targetTower);
		} else {
			helpSolveHanoi (n-1, startTower, nonTarget);
			System.out.println (startTower + " -> " + targetTower);
			helpSolveHanoi (n-1, nonTarget, targetTower);
		}	
	}
	
	// You are partaking in a scavenger hunt!
	// You've gotten a secret map to find many of the more difficult
	// items, but they are only available at VERY specific times at
	// specific places.  You have an array, times[], that lists at which
	// MINUTE an item is available.
	// Items in the ScavHunt are worth varying numbers of points.
	// You also have an array, points[], same length as times[],
	// that lists how many points each of the corresponding items is worth.
	// Problem is: to get from one location to the other takes 5 minutes,
	// so if there is an item, for example, available at time 23 and another
	// at time 27, it's just not possible for you to make it to both: you'll
	// have to choose!
	// (but you COULD make it from a place at time 23 to another at time 28)
	// Write a method that returns the maximum POINTS you can get.
	// 3 base case
	
	// times:  [23, 27, 31, 35, 45, 47])
	// points: [5, 23, 55, 23, 1, 9]
	
	// hints:
	// think about nonconsecutive subsets or subsets itself
	// two obvious options: 
	// 1. go to the thing at times[startIndex]
	
	// 2. or we don't
			// just the next idx
	
	
	// math.max is useful
	
	// you don't know which one is correct, must do both, which one better is the one you return
	// repeat scavHunt starting at the some later index
		// must be helpful to write a helper method for the next index
	public static int scavHunt(int[] times, int[] points) {
		return helpScavHunt (times, points, 0);
	}
	public static int helpScavHunt(int[] times, int[] points, int startIndex) {
		if (startIndex < 0 || startIndex > times.length - 1) {
			return 0;
		}
		//decide to go to first option
		int firstOption = points[startIndex] + helpScavHunt(times, points, findNextIdx(times, startIndex));
		//which one is better? going to the first option or not
		return Math.max(firstOption, helpScavHunt(times, points, startIndex+1));
	}
	public static int findNextIdx(int[] times, int startIndex) {
		for (int i = startIndex + 1; i < times.length; i++) {
			if (times[i] - times[startIndex] >= 5) {
				return i;
			}
		}
		return -1;
	}

}
