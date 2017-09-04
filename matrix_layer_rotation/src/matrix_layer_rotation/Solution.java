package matrix_layer_rotation;

import java.io.*;
import java.util.*;

public class Solution {
	
	static int rows;
	static int columns;
	static ArrayList<ArrayList<Integer>> layersMatrix;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        rows = scanner.nextInt();
        columns = scanner.nextInt();
        int rotations = scanner.nextInt();
        
        int[][] matrix = new int[rows][columns];
        
        for (int r = 0; r < rows; r++) {
        	for (int c = 0; c < columns; c++) {
                matrix[r][c] = scanner.nextInt();
        	}
	    }
		layersMatrix = new ArrayList<ArrayList<Integer>>();
        int layers = Math.min(rows, columns) / 2;
        for (int l = 0; l < layers; l++) {
        	int layerLength = ((rows - 1) * 2) + ((columns -1) * 2) - (l * 8);
        	stripLayer(l, layerLength, matrix);
//    		ArrayList<Integer> layer = layersMatrix.get(l);
    		Collections.rotate(layersMatrix.get(l), -rotations);
        	applyLayer(l, layerLength, matrix);
        }
        
        for (int r = 0; r < rows; r++) {
        	for (int c = 0; c < columns; c++) {
                System.out.print(matrix[r][c] + " ");
        	}
        	System.out.println();
	    }
    }


	static void stripLayer(int l, int layerLength, int[][] matrix) {
		ArrayList<Integer> layer = new ArrayList<Integer>();
		modLayer(l, layerLength, matrix, layer, "strip");
		layersMatrix.add(l,layer);
		return;
	}


	private static void applyLayer(int l, int layerLength, int[][] matrix) {
		ArrayList<Integer> layer = layersMatrix.get(l);
		modLayer(l, layerLength, matrix, layer, "apply");
		layersMatrix.set(l,layer);
		return;
	}


	private static void modLayer(int l, int layerLength, int[][] matrix, ArrayList<Integer> layer, String mode) {
		int r = l;
		int c = l;
		int rMax = rows - l - 1;
		int cMax = columns - l - 1;
		char direction = 'e';
		for (int i = 0; i < layerLength; i++) {
			if (mode == "apply") matrix[r][c] = layer.get(i);
			if (mode == "strip") layer.add(i, matrix[r][c]);
			switch (direction) {
			case 'e':
				if (c < cMax) {
					c++;
				} else {
					direction = 's';
					r++;
				}
				break;
			case 's':
				if (r < rMax) {
					r++;
				} else {
					direction = 'w';
					c--;
				}
				break;
			case 'w':
				if (c > l) {
					c--;
				} else {
					direction = 'n';
					r--;
				}
				break;
			case 'n':
				r--;
			}
		}
	}
	
}