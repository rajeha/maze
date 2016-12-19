import java.util.Random;

/*
 * Maze.java
 * ---------
 * Generates a random maze
 * 
 * Based on code in Algorithms, 4th Edition
 * (http://algs4.cs.princeton.edu/41graph/Maze.java)
 */
public class RandomMaze {
	private int N; // maze will be of size NxN
	private boolean[][] top; // tells us whether there's a wall to the top of a square
	private boolean[][] right;
	private boolean[][] bottom;
	private boolean[][] left;
	private boolean[][] visited;

	public RandomMaze(int N) {
		this.N = N;
		init();
		generate();
	}

	private void init() {
		// initially, all neighbors are visited
		visited = new boolean[N + 2][N + 2];
		for (int x = 0; x < N + 2; x++) {
			visited[x][0] = true;
			visited[x][N + 1] = true;
		}
		
		for (int y = 0; y < N + 2; y++) {
			visited[0][y] = true;
			visited[N + 1][y] = true;
		}

		// initially, all walls are present
		top = new boolean[N + 2][N + 2];
		right = new boolean[N + 2][N + 2];
		bottom = new boolean[N + 2][N + 2];
		left = new boolean[N + 2][N + 2];
		for (int x = 0; x < N + 2; x++) {
			for (int y = 0; y < N + 2; y++) {
				top[x][y] = true;
				right[x][y] = true;
				bottom[x][y] = true;
				left[x][y] = true;
			}
		}
	}

	private void generate(int x, int y) { // build the maze
		visited[x][y] = true;

		// while there is an unvisited neighbor
		while (!visited[x][y + 1] || !visited[x + 1][y] || !visited[x][y - 1] || !visited[x - 1][y]) {
			while (true) {
				Random rand = new Random();
				int r = rand.nextInt(4); // pick random neighbor
				
				if (r == 0 && !visited[x][y + 1]) {
					top[x][y] = false;
					bottom[x][y + 1] = false;
					generate(x, y + 1);
					break;
				} else if (r == 1 && !visited[x + 1][y]) {
					right[x][y] = false;
					left[x + 1][y] = false;
					generate(x + 1, y);
					break;
				} else if (r == 2 && !visited[x][y - 1]) {
					bottom[x][y] = false;
					top[x][y - 1] = false;
					generate(x, y - 1);
					break;
				} else if (r == 3 && !visited[x - 1][y]) {
					left[x][y] = false;
					right[x - 1][y] = false;
					generate(x - 1, y);
					break;
				}
			}
		}
	}

	private void generate() { // generate the maze starting from bottom left
		generate(1, 1);
	}

	public void setVisited(int x, int y, boolean state) {
		visited[x][y] = state;
	}

	public boolean isVisited(int x, int y) {
		return visited[x][y];
	}

	public boolean Bottom(int x, int y) {
		return bottom[x][y];
	}

	public boolean Top(int x, int y) {
		return top[x][y];
	}

	public boolean Left(int x, int y) {
		return left[x][y];
	}

	public boolean Right(int x, int y) {
		return right[x][y];
	}
}
