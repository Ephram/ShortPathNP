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

public class BFShortPathv2 {

	public static void main(String[] args) {

//		File file = new File("D:\\javamars20172\\ShortPathNP\\src\\week1\\testcaseAbdulBariYoutube3.txt"); 
//		File file = new File("D:\\javamars20172\\ShortPathNP\\src\\week1\\t1case.txt");
//		File file = new File("D:\\javamars20172\\ShortPathNP\\src\\week1\\input_random_8_4.txt"); //-36
//		File file = new File("D:\\javamars20172\\ShortPathNP\\src\\week1\\input_random_10_8.txt"); //-41
//		File file = new File("D:\\javamars20172\\ShortPathNP\\src\\week1\\input_random_14_16.txt"); //-130




//		File file = new File("D:\\javamars20172\\ShortPathNP\\src\\week1\\input_random_39_1024.txt"); //-2123
		
		
		File file = new File("D:\\javamars20172\\ShortPathNP\\src\\week1\\g3.txt"); //-19
		
	

		Map<Integer, List<EdgeNode>> graph = new HashMap<Integer, List<EdgeNode>>();

		int numberofVertices = 0;
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String[] line = scanner.nextLine().trim().split(" ");

				if (line.length == 2) {
					numberofVertices = Integer.parseInt(line[0]);

				}

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
		BFShortPathv2 path = new BFShortPathv2();
		System.out.println(path.shortPath(graph, numberofVertices));
	}

	public Integer shortPath(Map<Integer, List<EdgeNode>> graph, int numberofVertices) {

		int[] vxMinDistArr = new int[numberofVertices + 1];

		// Vertices minimum distances array
		vxMinDistArr[1] = 0;

		for (int i = 2; i < vxMinDistArr.length; i++) {
			vxMinDistArr[i] = 1_000_000_000;
		}

		for (int i = 1; i < vxMinDistArr.length; i++) {

			Set<Integer> vertices = graph.keySet();
			for (Integer vertex : vertices) {
				List<EdgeNode> comingVerticesList = graph.get(vertex);
				for (int j = 0; j < comingVerticesList.size(); j++) {
					int uDist = vxMinDistArr[comingVerticesList.get(j).getV()];
					int cUV = comingVerticesList.get(j).getEdgeCost();
					int vDist = vxMinDistArr[vertex];

					if ((uDist) + (cUV) < vDist) {
						vxMinDistArr[vertex] = uDist + cUV;
					}
				}

			}

		}
		
		Arrays.sort(vxMinDistArr);
		System.out.println(vxMinDistArr[0]);
		System.out.println("do something");
		return 0;
	}

}
