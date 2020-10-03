package dynamicprogramming.knapsack01;

public class Knapsack {

	public static void main(String[] args) {
		int[] weights = {1,2,3,5};
		int[] profit = {1,6,10,16};
		Knapsack k = new Knapsack();
		int maxProfit = k.solveKnapsack(weights, profit, 7);
		System.out.printf("topdown: %d", maxProfit);
		maxProfit = k.solveKnapsack(weights, profit, 6);
		System.out.printf(" %d\n", maxProfit);
		
		
		maxProfit = k.bottomupKnapsack(weights, profit, 7);
		System.out.printf("bottomup: %d", maxProfit);
		maxProfit = k.bottomupKnapsack(weights, profit, 6);
		System.out.printf(" %d\n", maxProfit);
	}
	
	public int solveKnapsack(int[] weights, int[] profit, int capacity) {
		Integer[][] dp = new Integer[weights.length][capacity+1];
		return recursiveKnapsack(weights, profit, capacity, 0, dp);
	}
	
	public int recursiveKnapsack(int[] weights, int[] profit, int capacity, int currIndex, Integer[][] dp) {
		if(capacity == 0 || weights.length == 0 || profit.length == 0 || currIndex >= profit.length) {
			return 0;
		}

		if(dp[currIndex][capacity] != null) {
			return dp[currIndex][capacity];
		}
		
		
		int profit1 = 0;
		if(weights[currIndex] <= capacity) {
			profit1 = profit[currIndex] + recursiveKnapsack(weights, profit, capacity-weights[currIndex], currIndex+1, dp);
		}
		int profit2 = recursiveKnapsack(weights, profit, capacity, currIndex+1, dp);
		
		dp[currIndex][capacity] =  Math.max(profit1, profit2);
		return dp[currIndex][capacity];
	}
	
	public int bottomupKnapsack(int[] weights, int[] profit, int capacity) {
		if(weights.length == 0 || profit.length == 0 || capacity <= 0) {
			return 0;
		}
		
		int n = weights.length;
		int[][] dp = new int[n][capacity+1];
		
		for(int i=0; i<n; i++) {
			dp[i][0] = 0;
		}
		
		
		for(int i=0; i<=capacity; i++) {
			if(weights[0] <= i) {
				dp[0][i] = profit[0];
			}
		}
		
		
		for(int i=1; i<n; i++) {
			for(int c=1; c<=capacity; c++) {
				int profit1 = 0, profit2 = 0;
				if(weights[i] <= c) {
					profit1 = profit[i] + dp[i-1][c - weights[i]];
				}
				profit2 = dp[i-1][c];
				dp[i][c] = Math.max(profit1, profit2);
			}
		}
		return dp[n-1][capacity];
	}
}
