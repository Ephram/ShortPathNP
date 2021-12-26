package week1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FWShortPath3 {

	public static void main(String[] args) {

		// Map<Integer, List<EdgeNode>> graph = new HashMap<Integer, List<EdgeNode>>();
		//
		// List<EdgeNode> list0 = new ArrayList<EdgeNode>();
		// graph.put(1, list0);
		//
		// List<EdgeNode> list = new ArrayList<EdgeNode>();
		// list.add(new EdgeNode(1, 2));
		// graph.put(2, list);
		//
		// List<EdgeNode> list2 = new ArrayList<EdgeNode>();
		// list2.add(new EdgeNode(1, 4));
		// list2.add(new EdgeNode(2, 1));
		// graph.put(3, list2);
		//
		// List<EdgeNode> list3 = new ArrayList<EdgeNode>();
		// list3.add(new EdgeNode(2, 2));
		// graph.put(4, list3);
		//
		// List<EdgeNode> list4 = new ArrayList<EdgeNode>();
		// list4.add(new EdgeNode(4, 2));
		// list4.add(new EdgeNode(3, 4));
		// graph.put(5, list4);

		// File file = new File("D:\\javamars20172\\ShortPathNP\\src\\week1\\input_random_8_4.txt"); //-36

		// File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_9_8.txt"); // null
		// File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_10_8.txt"); // -41

		// File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_14_16.txt"); // -130 //
		// path 1 12
		// 15

		// File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_15_16.txt"); // -100 //
		// path 3 14

		// File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_20_32.txt"); // -242 X
		// File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_22_64.txt"); // -431

		// File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_21_64.txt"); // null

		// File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_30_256.txt"); // -961

		// File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_39_1024.txt"); // -2123

		// File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_40_1024.txt"); // -2361

		// File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_44_2048.txt"); // -3127

		// File file = new File("D:\\javamars20172\\ShortPathNP\\src\\week1\\t1case.txt"); // -2
		File file = new File("D:\\javamars20172\\ShortPathNP\\src\\week1\\t3casegeeksforgeeks.txt");

		// File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\t2case.txt"); // null

		// File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\g1.txt"); // null
		// File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\g2.txt"); //null
		// File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\g3.txt"); // right answer -19

		// File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\testcaseAbdulBariYoutube.txt"); // -2
		// File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\testcaseAbdulBariYoutube2.txt"); // null

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

		// testing short path Bellman Ford Algo
		FWShortPath3 path = new FWShortPath3();
		path.shortPath(numberofVertices, graph);
	}

	public void shortPath(int n, Map<Integer, List<EdgeNode>> graph) {
		int i = n + 1;
		int j = n + 1;
		int k = n + 1;

		int[][][] A = new int[i][j][k];

		for (int ii = 0; ii < i; ii++) {
			for (int jj = 0; jj < j; jj++) {
				if (ii == jj) {
					A[ii][jj][0] = 0;
				} else if (graph.containsKey(jj)) {
					List<EdgeNode> edgeNodeList = graph.get(jj);
					for (int e = 0; e < edgeNodeList.size(); e++) {
						EdgeNode edge = edgeNodeList.get(e);

						if (ii == edge.getV()) {
							A[ii][jj][0] = edge.getEdgeCost();
						}
					}
				} 

			}
		}

	}

}
