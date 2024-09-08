import java.io.File;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Scanner;

/* You are allowed (and expected!) to use either Java's ArrayDeque or LinkedList class to make stacks and queues,
 * and Java's PriorityQueue class to make a priority queue */

public class CookieMonster {

    private int [][] cookieGrid;
    private int numRows;
    private int numCols;
    
    //Constructs a CookieMonster from a file with format:
    //numRows numCols
    //<<rest of the grid, with spaces in between the numbers>>
    public CookieMonster(String fileName) {
		int row = 0;
		int col = 0;
		try
		{
			Scanner input = new Scanner(new File(fileName));

			numRows    = input.nextInt();  
			numCols    = input.nextInt();
			cookieGrid = new int[numRows][numCols];

			for (row = 0; row < numRows; row++) 
				for (col = 0; col < numCols; col++)
					cookieGrid[row][col] = input.nextInt();
			
			input.close();
		}
		catch (Exception e)
		{
			System.out.print("Error creating maze: " + e.toString());
			System.out.println("Error occurred at row: " + row + ", col: " + col);
		}

    }

    public CookieMonster(int [][] cookieGrid) {
        this.cookieGrid = cookieGrid;
        this.numRows    = cookieGrid.length;
        this.numCols    = cookieGrid[0].length;
    }

	/* RECURSIVELY calculates the route which grants the most cookies.
	 * Returns the maximum number of cookies attainable. */
	public int recursiveCookies() {
		return helpRecursive (0, 0, 0);
	}
	public int helpRecursive(int numCookies, int row, int column) {
		if (!canGo(row, column)) {
			return numCookies;
		}
		numCookies += cookieGrid[row][column];
		return Math.max(helpRecursive(numCookies, row + 1, column), helpRecursive(numCookies, row, column+1));
	}

	/* Calculate which route grants the most cookies using a QUEUE.
	 * Returns the maximum number of cookies attainable. */
    /* From any given position, always add the path right before adding the path down */
    public int queueCookies() {
    	ArrayDeque<OrphanScout> orphans = new ArrayDeque<OrphanScout>();
    	int best = 0;
    	if (canGo(0, 0)) {
    		orphans.add(new OrphanScout(0, 0, cookieGrid[0][0]));
    	}
    	while (!orphans.isEmpty()) {
    		OrphanScout removed = orphans.remove();
    		int endingRow = removed.getEndingRow();
    		int endingCol = removed.getEndingCol();
    		int cookies = removed.getCookiesDiscovered();
   			if (canGo(endingRow, endingCol+1)) {
   				orphans.add(new OrphanScout (endingRow, endingCol + 1, cookies + cookieGrid [endingRow][endingCol + 1]));
    		} if (canGo(endingRow + 1, endingCol)) {
    			orphans.add(new OrphanScout (endingRow + 1, endingCol, cookies + cookieGrid [endingRow + 1][endingCol]));
    		} if (!canGo(endingRow, endingCol+1) || !canGo(endingRow+1, endingCol)) {
   				if (cookies > best) {
   					best = cookies;
   				}
   			}
    	}
		return best;
    }

    /* Calculate which route grants the most cookies using a stack.
 	 * Returns the maximum number of cookies attainable. */
    /* From any given position, always add the path right before adding the path down */
    public int stackCookies() {
    	ArrayDeque<OrphanScout> orphans = new ArrayDeque<OrphanScout>();
    	int best = 0;
    	if (canGo(0, 0)) {
    		orphans.push(new OrphanScout(0, 0, cookieGrid[0][0]));
    	}
    	while (!orphans.isEmpty()) {
    		OrphanScout removed = orphans.pop();
    		int endingRow = removed.getEndingRow();
    		int endingCol = removed.getEndingCol();
    		int cookies = removed.getCookiesDiscovered();
   			if (canGo(endingRow, endingCol+1)) {
   				orphans.push(new OrphanScout (endingRow, endingCol + 1, cookies + cookieGrid [endingRow][endingCol + 1]));
    		} if (canGo(endingRow + 1, endingCol)) {
    			orphans.push(new OrphanScout (endingRow + 1, endingCol, cookies + cookieGrid [endingRow + 1][endingCol]));
    		} if (!canGo(endingRow, endingCol+1) || !canGo(endingRow+1, endingCol)) {
   				if (cookies > best) {
   					best = cookies;
   				}
   			}
    	}
		return best;
    }

    /* Calculate which route grants the most cookies using a priority queue.
	 * Returns the maximum number of cookies attainable. */
    /* From any given position, always add the path right before adding the path down */
    public int pqCookies() {
    	PriorityQueue<OrphanScout> orphans = new PriorityQueue<OrphanScout>();
    	if (canGo(0, 0)) {
    		orphans.add(new OrphanScout(0, 0, cookieGrid[0][0]));
    	}
    	int best = 0;
    	while (!orphans.isEmpty()) {
    		OrphanScout removed = orphans.remove();
    		int endingRow = removed.getEndingRow();
    		int endingCol = removed.getEndingCol();
    		int cookies = removed.getCookiesDiscovered();
   			if (canGo(endingRow, endingCol+1)) {
   				orphans.add(new OrphanScout (endingRow, endingCol + 1, cookies + cookieGrid [endingRow][endingCol + 1]));
    		} if (canGo(endingRow + 1, endingCol)) {
    			orphans.add(new OrphanScout (endingRow + 1, endingCol, cookies + cookieGrid [endingRow + 1][endingCol]));
    		} if (!canGo(endingRow, endingCol+1) || !canGo(endingRow+1, endingCol)) {
   				if (cookies > best) {
   					best = cookies;
   				}
   			}
    	}
		return best;
    }
    
    public boolean canGo (int row, int col) {
    	if (row >= cookieGrid.length || col >= cookieGrid[0].length || cookieGrid[row][col] == -1) {
    		return false;
    	} else {
    		return true;
    	}
    }

}
