package week1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import week4.NodeKnapsack;

public class BFShortPath {

	public static void main(String[] args) {

//		Map<Integer, List<EdgeNode>> graph = new HashMap<Integer, List<EdgeNode>>();
//
//		List<EdgeNode> list0 = new ArrayList<EdgeNode>();
//		graph.put(1, list0);
//
//		List<EdgeNode> list = new ArrayList<EdgeNode>();
//		list.add(new EdgeNode(1, 2));
//		graph.put(2, list);
//
//		List<EdgeNode> list2 = new ArrayList<EdgeNode>();
//		list2.add(new EdgeNode(1, 4));
//		list2.add(new EdgeNode(2, 1));
//		graph.put(3, list2);
//
//		List<EdgeNode> list3 = new ArrayList<EdgeNode>();
//		list3.add(new EdgeNode(2, 2));
//		graph.put(4, list3);
//
//		List<EdgeNode> list4 = new ArrayList<EdgeNode>();
//		list4.add(new EdgeNode(4, 2));
//		list4.add(new EdgeNode(3, 4));
//		graph.put(5, list4);
		
		
		
		File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_8_4.txt"); // -36

		Map<Integer, List<EdgeNode>> graph = new HashMap<Integer, List<EdgeNode>>();

		List<Integer> list = new ArrayList<Integer>();
		
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

				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// *****************************************************//

		// testing short path Bellman Ford Algo
		BFShortPath path = new BFShortPath();
		path.shortPath(graph);
	}

	public Integer shortPath(Map<Integer, List<EdgeNode>> graph) {

		int[][] A = new int[graph.size() + 1][graph.size() + 1];

		// base case
		A[0][1] = 0;

		for (int j = 2; j < A[0].length; j++) {
			A[0][j] = 1_000_000;
		}

		for (int i = 1; i < A.length; i++) {

			Set<Integer> vertices = graph.keySet();

			for (Integer vertex : vertices) {

				int case1 = A[i - 1][vertex];

				List<EdgeNode> comingVerticesList = graph.get(vertex);

				int case2 = getMinCase2(comingVerticesList, A, i);

				A[i][vertex] = Math.min(case1, case2);
			}

		}

		// detecting cycle
		Set<Integer> vertices = graph.keySet();
		for (Integer v : vertices) {
			int actualresult = A[A.length - 2][v];
			int additionalresult = A[A.length - 1][v];

			if (actualresult != additionalresult) {
				System.out.println("cycle detected ");
				return null;
			}
		}

		return 0;
	}

	public int getMinCase2(List<EdgeNode> list, int[][] A, int i) {

		if (!list.isEmpty()) {

			int w = list.get(0).getV();
			int Cwv = list.get(0).getEdgeCost();
			int min = (A[i - 1][w]) + (Cwv);

			for (int ii = 1; ii < list.size(); ii++) {
				w = list.get(ii).getV();
				Cwv = list.get(ii).getEdgeCost();
				int min2 = (A[i - 1][w]) + (Cwv);

				min = Math.min(min, min2);

			}

			return min;
		}
		return 1_000_000;
	}

}
