package week2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DPTsp {

	private Map<Integer, List<NodeTsp>> graph = new HashMap<Integer, List<NodeTsp>>();

	public int tspSolution(Map<Integer, List<NodeTsp>> graph) {
		int graphSize = 0;

		if (graph.isEmpty()) {
			return 0;
		} else {
			graphSize = graph.size();
		}

		double A[][] = new double[graphSize + 1][graphSize + 1];

		// base case
		if (graphSize == 1) {
			return 0;
		} else {
			Integer graphArray[] = new Integer[(graphSize + 1)];
			graphArray = graph.keySet().toArray(graphArray);

			for (int s = 1; s < graphArray.length; s++) {
				A[s][1] = 1000000;
			}
		}

		// base case end
		for (int m = 2; m < graphSize; m++) {
			Integer graphArray[] = new Integer[(graphSize + 1)];
			graphArray = graph.keySet().toArray(graphArray);

			for (; m < graphArray.length; m++) {
				for (int j = m; j < graphArray.length; j++) {
					if (j == 1) {
						continue;	
					}
				}
			}
		}

	}

	public static void main(String[] args) {

	}

}
