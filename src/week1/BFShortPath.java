package week1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

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

		
		
//		File file = new File("D:\\javamars20172\\ShortPathNP\\src\\week1\\input_random_8_4.txt"); //-36

//		File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_9_8.txt"); // null
//		File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_10_8.txt"); // -41

//		File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_14_16.txt"); // -130 //
		// path 1 12
		// 15

//		File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_15_16.txt"); // -100 //
		// path 3 14

//		File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_20_32.txt"); // -242 X
//		File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_22_64.txt"); // -431

//		File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_21_64.txt"); // null

//		File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_30_256.txt"); // -961

//		File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_39_1024.txt"); // -2123

//		File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_40_1024.txt"); // -2361

//		File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\input_random_44_2048.txt"); // -3127

		File file = new File("D:\\javamars20172\\ShortPathNP\\src\\week1\\t1case.txt"); //-2
		
		
//		File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\t2case.txt"); // null

//		File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\g1.txt"); // null
//		File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\g2.txt"); //null
//		File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\g3.txt"); // right answer -19
			
//		File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\testcaseAbdulBariYoutube.txt"); //  -2
//		File file = new File("D:\\programming\\projects\\ShortPathNP\\src\\week1\\testcaseAbdulBariYoutube2.txt"); //  null
		
		

		
		
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
		BFShortPath path = new BFShortPath();
		System.out.println(path.shortPath(graph, numberofVertices));
	}

	public Integer shortPath(Map<Integer, List<EdgeNode>> graph, int numberofVertices) {
		int minmin = 1_000_000_000;
		int[][] A = new int[numberofVertices + 1][numberofVertices + 1];

		// base case
		A[0][1] = 0;

		for (int j = 2; j < A[0].length; j++) {
			A[0][j] = 1_000_000_000;
		}

		for (int i = 1; i < A.length; i++) {

			Set<Integer> vertices = graph.keySet();

			for (Integer vertex : vertices) {

				int case1 = A[i - 1][vertex];

				List<EdgeNode> comingVerticesList = graph.get(vertex);

				int case2 = getMinCase2(comingVerticesList, A, i);

				A[i][vertex] = Math.min(case1, case2);
				if (minmin > A[i][vertex]) {
					minmin = A[i][vertex];
					System.out.println(minmin + " minimal per round " + vertex + " vertex");

				}
			}
		}

		// detecting cycle
		Set<Integer> vertices = graph.keySet();
		int min = 1_000_000_000;
		for (Integer v : vertices) {
			int actualresult = A[A.length - 2][v];
			int additionalresult = A[A.length - 1][v];

			if (actualresult < min) {
				min = actualresult;
			}
			if (actualresult != additionalresult) {
				System.out.println("cycle detected ");
				return null;
			}
		}

		// getting last V

		Integer[] arr = graph.keySet().toArray(new Integer[graph.size()]);
		
		Arrays.sort(arr);
		
		int minSum = A[A.length - 2][A.length - 1];
		int saveV = A.length - 1;

		for (int i = saveV-1; i > 0; i--) {
			if (minSum > A[A.length - 2][i]) {
				minSum = A[A.length - 2][i];
				saveV = i;
			}

		}

		List<Integer> pathList = reverseDetection(A, numberofVertices, saveV, graph);
		System.out.println(" path list " + pathList.size());
		for (int i = 0; i < pathList.size(); i++) {
			System.out.println(pathList.get(i));
		}
		return min;
	}

	public List<Integer> reverseDetection(int[][] A, int i, int v, Map<Integer, List<EdgeNode>> graph) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(v);
		int minmin = -1_000_000_000;

		try {
			while (/* graph.get(v).isEmpty() != true */ /* v > 1 */ minmin < 0) {

				List<EdgeNode> edgeNodes = graph.get(v);

				int w = edgeNodes.get(0).getV();
				int Cwv = edgeNodes.get(0).getEdgeCost();
				int min = (A[i - 1][w]) + (Cwv);

				for (int ii = 1; ii < edgeNodes.size(); ii++) {
					int w2 = edgeNodes.get(ii).getV();
					int Cwv2 = edgeNodes.get(ii).getEdgeCost();
					int min2 = (A[i - 1][w2]) + (Cwv2);

					if (min > min2) {
						w = w2;
						min = min2;

//						if (min > minmin) {
//							minmin = min;
//						}
					}

				}
				v = w;
				minmin = min;
				System.out.println(i);
				System.out.println(w);
				list.add(w);
				edgeNodes = null;
			}
		} catch (NullPointerException e) {
			System.out.println("catched");
		}

		return list;
	}

	public int getMinCase2(List<EdgeNode> list, int[][] A, int i) {

		if (!list.isEmpty()) {

			int w = list.get(0).getV();
			int Cwv = list.get(0).getEdgeCost();
			int min = (A[i - 1][w]) + (Cwv);

			for (int ii = 1; ii < list.size(); ii++) {
				int w2 = list.get(ii).getV();
				int Cwv2 = list.get(ii).getEdgeCost();
				int min2 = (A[i - 1][w2]) + (Cwv2);

				min = Math.min(min, min2);

			}

			return min;
		}
		return 1_000_000;
	}

}
