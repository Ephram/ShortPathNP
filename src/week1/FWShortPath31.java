package week1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FWShortPath31 {

	public static void main(String[] args) {

		// File file = new File("D:\\javamars20172\\ShortPathNP\\src\\week1\\input_random_8_4.txt"); // -36

//		File file = new File("D:\\javamars20172\\ShortPathNP\\src\\week1\\input_random_10_8.txt"); // -41
		 File file = new File("D:\\javamars20172\\ShortPathNP\\src\\week1\\input_random_22_64.txt"); // -431

		// File file = new
		// File("D:\\javamars20172\\ShortPathNP\\src\\week1\\t1case.txt"); // -2
		// File file = new File("D:\\javamars20172\\ShortPathNP\\src\\week1\\t3casegeeksforgeeks.txt");

		// File file = new
		// File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\t2case.txt"); //
		// null

		// File file = new
		// File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\g1.txt"); // null
		// File file = new
		// File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\g2.txt"); //null
		// File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\g3.txt"); // right answer -19
		// File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\t3case.txt"); // right answer -8
		// File file = new File("D:\\javamars20172\\ShortPathNP\\src\\week1\\t4case.txt"); // right answer -10

		// File file = new
		// File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\testcaseAbdulBariYoutube.txt");
		// // -2
		// File file = new
		// File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\testcaseAbdulBariYoutube2.txt");
		// // null

		Map<Integer, List<EdgeNode>> graph = new HashMap<Integer, List<EdgeNode>>();

		List<Integer> list = new ArrayList<Integer>();

		int numberofVertices = 0;

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String[] line = scanner.nextLine().trim().split(" ");

				if (line.length != 2) {
					int v = Integer.parseInt(line[1]);

					if (!graph.containsKey(v)) {
						List<EdgeNode> edgeNodeList = new ArrayList<EdgeNode>();
						graph.put(v, edgeNodeList);
					}

					if (graph.containsKey(v)) {
						List<EdgeNode> nodeV = graph.get(v);

						int headV = Integer.parseInt(line[0]);
						int edgeCost = Integer.parseInt(line[2]);

						nodeV.add(new EdgeNode(headV, edgeCost));
					}

				} else {
					numberofVertices = Integer.parseInt(line[0]);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// *****************************************************//

		// testing short path Floyd Warshall
		FWShortPath31 path = new FWShortPath31();
		path.shortPath(numberofVertices, graph);
	}

	public void shortPath(int n, Map<Integer, List<EdgeNode>> graph) {

		int i = n + 1;
		int j = n + 1;
		int k = n + 1;

		int[][][] A = new int[k][i][j];
		int[][] next = new int[i][j];

		int kk = 0;

		// base case
		boolean isInfinity = true;

		for (int ii = 1; ii < i; ii++) {
			for (int jj = 1; jj < j; jj++) {
				if (ii == jj) {
					A[kk][ii][jj] = 0;
					isInfinity = false;
					next[ii][jj] = jj;

				} else if (graph.containsKey(jj)) {
					List<EdgeNode> edgeNodeList = graph.get(jj);
					for (int e = 0; e < edgeNodeList.size(); e++) {
						EdgeNode edge = edgeNodeList.get(e);

						if (ii == edge.getV()) {
							A[kk][ii][jj] = edge.getEdgeCost();
							isInfinity = false;
							next[ii][jj] = jj;
						}
					}
				}

				if (isInfinity) {
					A[kk][ii][jj] = 1_000_000_000;
				}
				isInfinity = true;

			}
			// end of jj forloop

		}
		// end of ii forloop
		// base case end

		int minimumOfMin = 1_000_000_000;
		int minI = 0, minJ = 0;

		for (kk = 1; kk < k; kk++) {
			for (int ii = 1; ii < i; ii++) {
				for (int jj = 1; jj < j; jj++) {

					int case1 = A[kk - 1][ii][jj];
					int case2 = (A[kk - 1][ii][kk]) + (A[kk - 1][kk][jj]);

					if (case1 > 1_000_000_00) {
						case1 = 1_000_000_000;
					}

					if (case2 > 1_000_000_00) {
						case2 = 1_000_000_000;
					}

					int min = Math.min(case1, case2);
					A[kk][ii][jj] = min;

					if (case2 < case1) {
						next[ii][jj] = next[ii][kk];
						System.out.println(ii + " - " + jj + " -- " + ii + " - " + kk);
						System.out.println(next[ii][jj] + "-" + next[ii][kk]);
					}

					if (min < minimumOfMin) {
						minimumOfMin = min;
						minI = ii;
						minJ = jj;

					}

				}
			}
		}

		// getting path
		List<Integer> list = new ArrayList<>();
		list.add(minI);
		int pathNum = next[minI][minJ];
		list.add(pathNum);

		while (minJ != pathNum) {
			int tempPathnum = pathNum;
			pathNum = next[pathNum][minJ];
			list.add(pathNum);
		}
		// end of getting path

		System.out.println("min of mins" + minimumOfMin);

	}
	// end shortpath method

}
