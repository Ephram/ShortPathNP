package week1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BFShortPath {

	public static void main(String[] args) {

		Map<Integer, List<EdgeNode>> graph = new HashMap<Integer, List<EdgeNode>>();

		List<EdgeNode> list0 = new ArrayList<EdgeNode>();
		graph.put(1, list0);

		List<EdgeNode> list = new ArrayList<EdgeNode>();
		list.add(new EdgeNode(1, 2));
		graph.put(2, list);

		List<EdgeNode> list2 = new ArrayList<EdgeNode>();
		list2.add(new EdgeNode(1, 4));
		list2.add(new EdgeNode(2, 1));
		graph.put(3, list2);

		List<EdgeNode> list3 = new ArrayList<EdgeNode>();
		list3.add(new EdgeNode(2, 2));
		graph.put(4, list3);

		List<EdgeNode> list4 = new ArrayList<EdgeNode>();
		list4.add(new EdgeNode(4, 2));
		list4.add(new EdgeNode(3, 4));
		graph.put(5, list4);

		BFShortPath path = new BFShortPath();
		path.shortPath(graph);

	}

	public void shortPath(Map<Integer, List<EdgeNode>> graph) {
		int ii = 0;
		int vv = 1;

		int[][] A = new int[graph.size()][graph.size() + 1];

		// base case
		A[0][1] = 0;

		for (int j = 2; j < A[0].length; j++) {
			A[0][j] = 1_000_000;
		}

		for (int i = 1; i < A.length - 1; i++) {
			
			
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
