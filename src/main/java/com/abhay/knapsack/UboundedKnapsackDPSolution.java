package com.abhay.knapsack;

import java.util.Arrays;
import java.util.Scanner;

public class UboundedKnapsackDPSolution {

	static int getMaxValSolution1(int[] valueArray, int[] wtArray, int targetSum) {
		int resArray[] = new int[targetSum + 1];
		for (int i = 0; i <= targetSum; i++) {
			for (int j = 0; j < valueArray.length; j++) {
				if (wtArray[j] <= i) {
					resArray[i] = max(resArray[i], resArray[i - wtArray[j]] + valueArray[j]);
				}
			}
		}
		return resArray[targetSum];
	}

	private static int max(int i, int j) {
		return (i > j) ? i : j;
	}

	static int getMaxValDivideAndConquerSolution2(int[] valueArray, int[] weightArray, int targetSum) {
		int[] d1Array, d2Array;
		int index, resultIndex;

		d1Array = new int[valueArray.length];
		d2Array = new int[valueArray.length];

		if (targetSum == 0) {
			return (0);
		}

		for (index = 0; index < valueArray.length; index++) {
			if (targetSum >= weightArray[index]) {
				d1Array[index] = getMaxValDivideAndConquerSolution2(valueArray, weightArray,
						targetSum - weightArray[index]);
			} else {
				d1Array[index] = 0;
			}
		}

		for (index = 0; index < valueArray.length; index++) {
			if (targetSum >= weightArray[index]) {
				d2Array[index] = d1Array[index] + valueArray[index];
			} else {
				d2Array[index] = 0;
			}
		}

		resultIndex = d2Array[0];
		for (index = 1; index < valueArray.length; index++)
			if (d2Array[index] > resultIndex)
				resultIndex = d2Array[index];

		return resultIndex;
	}

	static int getMaxValDPSolution3(int[] valueArray, int[] weightArray, int targetSum) {
		int[] d1Array, d2Array, resultArray;
		int index, resultIndex;

		d1Array = new int[valueArray.length];
		d2Array = new int[valueArray.length];
		resultArray = new int[targetSum + 1];
		resultArray[0] = 0;

		for (resultIndex = 1; resultIndex <= targetSum; resultIndex++) {

			for (index = 0; index < valueArray.length; index++) {
				if (resultIndex >= weightArray[index]) {
					d1Array[index] = resultArray[resultIndex - weightArray[index]];
				} else {
					d1Array[index] = 0;
				}
			}

			for (index = 0; index < valueArray.length; index++) {
				if (resultIndex >= weightArray[index]) {
					d2Array[index] = d1Array[index] + valueArray[index];
				} else {
					d2Array[index] = 0;
				}
			}

			// Search closest match to targetSum
			resultArray[resultIndex] = d2Array[0];
			for (index = 1; index < valueArray.length; index++) {
				if (d2Array[index] > resultArray[resultIndex])
					resultArray[resultIndex] = d2Array[index];
			}
		}

		return resultArray[targetSum];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCases = Integer.parseInt(sc.nextLine());

		for (int i = 0; i < testCases; i++) {
			String firstLine = sc.nextLine();
			String[] firstInputArr = firstLine.split(" ");
			int targetSum = Integer.parseInt(firstInputArr[1]);
			String arrayData = sc.nextLine();
			String[] inputArray = arrayData.split(" ");
			int[] valueArray = Arrays.stream(inputArray).mapToInt(Integer::parseInt).toArray();
			int[] wtArray = new int[valueArray.length];
			// In this problem the weight of each element is same as the value of element.
			wtArray = Arrays.copyOf(valueArray, valueArray.length);
			//System.out.println(getMaxValSolution1(valueArray, wtArray, targetSum));
			//System.out.println(getMaxValDivideAndConquerSolution2(valueArray, wtArray, targetSum));
			System.out.println(getMaxValDPSolution3(valueArray, wtArray, targetSum));
		}

		sc.close();
	}
}
