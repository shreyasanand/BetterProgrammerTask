package sample;

import java.util.List;

public class BetterProgrammerTask {

	public static String getBinaryRepresentation(int n) {
		/*
		 * Please implement this method to return a String with the binary
		 * representation of any number n, where n >= 0. Example: "101" is a
		 * binary representation of 5
		 */

		String binaryString = "";
		binaryString = Integer.toBinaryString(n);
		return binaryString;
	}

	public static double getProbability(int Y, int X) {
		/*
		 * If you roll Y standard six-sided dice, what’s the probability that
		 * you get at least X 4s? To calculate that you should divide the number
		 * of comibnations with X or more 4s by the total number of possible
		 * combinations.
		 */

		double p = 0;

		if (X > Y)
			p = 0;
		if (X == Y)
			p = 1 / Math.pow(6,Y);
		else
			p = 1 / Math.pow(6,X) + (Math.pow(5,Y-X) / Math.pow(6,Y-X))
					+ getProbability(Y, X+1);

		return p;
	}

	public static Change getCorrectChange(int cents) {
		/*
		 * Please implement this method to take cents as a parameter and return
		 * an equal amount in dollars and coins using the minimum number of
		 * coins possible. For example: 164 cents = 1 dollar, 2 quarters, 1 dime
		 * and 4 cents. Return null if the parameter is negative.
		 */

		// Return null if entered parameter is negative
		if (cents < 0) {
			return null;
		}

		// If entered parameter is 0 return a Change object with all 0
		// parameters
		if (cents == 0) {
			return new Change(0, 0, 0, 0, 0);
		}

		int dollars = 0;

		// Get the dollar value
		if (cents >= 100) {
			dollars = cents / 100;
		}

		// Get remaining cents after extracting dollars
		int mod100 = cents % 100;
		
		// Get the quarters
		int quarters = mod100 / 25;

		// Get remaining cents after extracting quarters
		int mod25 = mod100 % 25;
		
		// Get the dimes
		int dimes = mod25 / 10;

		// Get remaining cents after extracting dimes
		int mod10 = mod25 % 10;

		// Get the nickels
		int nickels = mod10 / 5;
		
		// Get the remaining cents after extracting nickels
		int finalCents = mod10 % 5;
		
		return new Change(dollars, quarters, dimes, nickels, finalCents);
	}

	// Please do not change this class
	static class Change {
		private final int _dollars;
		private final int _quarters; // 25 cents
		private final int _dimes; // 10 cents
		private final int _nickels; // 5 cents
		private final int _cents; // 1 cent

		public Change(int dollars, int quarters, int dimes, int nickels,
				int cents) {
			_dollars = dollars;
			_quarters = quarters;
			_dimes = dimes;
			_nickels = nickels;
			_cents = cents;
		}

		public int getDollars() {
			return _dollars;
		}

		public int getQuarters() {
			return _quarters;
		}

		public int getDimes() {
			return _dimes;
		}

		public int getNickels() {
			return _nickels;
		}

		public int getCents() {
			return _cents;
		}
	}

	// Please do not change this interface
	public static interface Node {
		int getValue();
		List<Node> getChildren();
	}

	public static int getLargestRootToLeafSum(Node root) {
		/*
		 * A root-to-leaf path in a tree is a path from a leaf node through all
		 * its ancestors to the root node inclusively. A "root-to-leaf sum" is a
		 * sum of the node values in a root-to-leaf path.
		 * 
		 * Please implement this method to return the largest root-to-leaf sum
		 * in the tree.
		 */

		int largestRootToLeafSum = 0;

		largestRootToLeafSum = getMaxSum(root, largestRootToLeafSum);
		return largestRootToLeafSum;
	}

	public static int getMaxSum(Node root, int sum) {

		// Update sum with root value
		sum += root.getValue();

		// Check if there are no children
		if (root.getChildren() == null)
			return sum;

		// Initialize maxSum to the minimum value of integer
		int maxSum = Integer.MIN_VALUE;

		// Recursively call getMaxSum and update the maxSum
		for (Node child : root.getChildren())
			maxSum = Math.max(maxSum, getMaxSum(child, sum));

		return maxSum;
	}
	
	public static void main(String[] args) {
		System.out.println("Binary representation of 5: "+getBinaryRepresentation(5));
		System.out.println("Probability of 2 4's with 3 6-sided dice: "+getProbability(3,2));
		
		Change c = getCorrectChange(164);
		System.out.println("Correct change for 164 cents: "
								+c.getDollars()  +" dollars "
								+c.getQuarters() +" quarters "
								+c.getDimes()    +" dimes "
								+c.getNickels()  +" nickels "
								+c.getCents()    +" cents");
	}
}