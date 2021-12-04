package week1;

public class BFShortPath {

	public static void main(String[] args) {
		int ii = 0;
		int vv = 1;

		int[][] A = new int[ii][vv];

		// base case
		A[0][1] = 0;

		for (int j = 2; j < A[0].length; j++) {
			A[0][j] = 1_000_000;
		}

		for (int i = 1; i < A[0].length - 1; i++) {
			for (int v = 1; v < A[i].length; v++) {

				int case1 = A[i - 1][v];

				int w = 0;
				int Cwv = 0;
				int case2 = (A[i - 1][w]) + (Cwv);

				A[i][v] = Math.min(case1, case2);

			}
		}

	}

}
