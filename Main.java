package com.company;
public class Main {

	static int checkNeighbour(int [][] city, int x, int y) {
		try {
			return city[x][y];
		} catch (IndexOutOfBoundsException e) {
			return 0;
		}
	}
	static  int countNeighbours(int [][] city, int x, int y) {
		// finding number of neighbours that are alive
		int aliveNeighbours = 0;


		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <=1 ; j++) {
				if(!(j==0 && i==0))
					aliveNeighbours+=checkNeighbour(city,x+i,y+j);
			}
		}
		/*
		aliveNeighbours += checkNeighbour(city, x-1, y-1);
		aliveNeighbours += checkNeighbour(city, x-1, y);
		aliveNeighbours += checkNeighbour(city, x-1, y+1);
		aliveNeighbours += checkNeighbour(city, x, y-1);
		aliveNeighbours += checkNeighbour(city, x, y+1);
		aliveNeighbours += checkNeighbour(city, x+1, y-1);
		aliveNeighbours += checkNeighbour(city, x+1, y);
		aliveNeighbours += checkNeighbour(city, x+1, y+1);
		*/

		return aliveNeighbours;
	}

	static int[][] nextGeneration(int[][] city, int dimensions){
		int[][] future = new int[dimensions][dimensions];

		// loop through every house
		for (int l = 1; l < dimensions - 1; l++) {
			for (int m = 1; m < dimensions - 1; m++) {
				int aliveNeighbours = countNeighbours(city, l, m);
					// human is lonely and dies
				if ((city[l][m] == 1) && (aliveNeighbours < 2))
					future[l][m] = 0;

					// human dies due to over population
				else if ((city[l][m] == 1) && (aliveNeighbours > 3))
					future[l][m] = 0;

					// new human is born
				else if ((city[l][m] == 0) && (aliveNeighbours == 3))
					future[l][m] = 1;

					// remains the same
				else
					future[l][m] = city[l][m];
			}
		}

		System.out.println("Next Generation");
		printField(dimensions, future);
		return future;
	}

    public static void main(String[] args) {
		int dimension = 10;
		int [][] city = new int[10][10];
		city[4][4] = 1;
		city[4][5] = 1;
		city[4][6] = 1;
		city[3][6] = 1;
		city[2][5] = 1;
		int generations = 10;

			//original generation
			System.out.println("Original Generation");
		printField(dimension, city);
		// loop for 10 generations??????
			System.out.println();
			for(int i = 0; i < generations; i++){
				city = nextGeneration(city, dimension);
			}
	}

	private static void printField(int dimension, int[][] city) {
		for (int j = 0; j < dimension; j++) {
			for (int k = 0; k < dimension; k++) {
				if (city[j][k] == 0)
					System.out.print(".");
				else
					System.out.print("#");
			}
			System.out.println();
		}
	}
}
