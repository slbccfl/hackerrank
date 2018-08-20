package emas_supercomputer;

import java.io.*;
import java.util.*;

public class Solution {
	
	
	static Integer[][] score;
    static int rows;
    static int columns;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String line = scanner.nextLine();
        String parms[] = line.split("\\s");
        rows = Integer.parseInt(parms[0]);
        columns = Integer.parseInt(parms[1]);
        char[][] matrix = new char[rows][columns];
        score = new Integer[rows][columns];
        
        for (int r = 0; r < rows; r++) {
        	line = scanner.nextLine();
        	for (int c = 0; c < columns; c++) {
                matrix[r][c] = line.charAt(c);
        	}
	    }
        System.out.println(findOptimalScorePair(matrix));
    }

	private static int findOptimalScorePair(char[][] matrix) {
		int highScore = -1;
		int maxRange = 0;
		int firstCellScore = 0;
        for (int r = 0; r < rows; r++) {
        	for (int c = 0; c < columns; c++) {
        		firstCellScore = 0;
        		if (matrix[r][c] == 'G') {
        			maxRange = scoreCell(matrix, r, c);
        			for (int tempMaxRange = maxRange; tempMaxRange >= 0; tempMaxRange--) {
            			firstCellScore = 1 + (tempMaxRange * 4);
            			char[][] tempMatrix = new char[rows][columns];
            			for (int r2 = 0;r2 < rows; r2++) tempMatrix[r2] = matrix[r2].clone();
                		int secondCellScore = findMatrixHighScore(tagPlus(tempMatrix, tempMaxRange, r, c));
                		int matrixScore = firstCellScore * secondCellScore;
            			if (matrixScore > highScore) {
            				highScore = matrixScore;
            			}
        				
        			}
        		}
    		}
    	}
		return highScore;
	}

	private static int findMatrixHighScore(char[][] matrix) {
		int highScore = -1;
		int maxRange = 0;
		int cellScore = 0;
        for (int r = 0; r < rows; r++) {
        	for (int c = 0; c < columns; c++) {
        		cellScore = 0;
        		if (matrix[r][c] == 'G') {
            		if (r != 0 && c != 0 && rows-r-1 != 0 && columns-c-1 != 0) {
            			maxRange = scoreCell(matrix, r, c);
            		}
            		cellScore = 1 + (maxRange * 4);
        		}
    			score[r][c] = cellScore;
    			if (cellScore > highScore) {
    				highScore = cellScore;
    			}
    		}
    	}
		return highScore;
	}

	private static char[][] tagPlus(char[][] matrix, int maxRange, Integer highRow, Integer highCol) {
		matrix[highRow][highCol] = '+';
        for (int i = 1; i <= maxRange ; i++) {
        	matrix[highRow-i][highCol] = '+';
        	matrix[highRow+i][highCol] = '+';
        	matrix[highRow][highCol-i] = '+';
        	matrix[highRow][highCol+i] = '+';
        }
        return matrix;
	}

	private static int scoreCell(char[][] matrix, int r, int c) {
		int maxRange;
		int cellScore;
		maxRange = Math.min(columns - c - 1, Math.min(c, Math.min(r, rows - r - 1)));
		for (int i = 1; i <= maxRange; i++) {
			if (matrix[r-i][c] != 'G' ||
					matrix[r+i][c] !=  'G' ||
					matrix[r][c-i] !=  'G' ||
					matrix[r][c+i] !=  'G') {
				maxRange = i - 1;
				break;
			}
		}
		return maxRange;
	}
    
}