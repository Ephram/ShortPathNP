package week1;

public class EdgeNode {
	private int edgeCost;
	private int v;

	public EdgeNode(int v, int edgeCost) {
		this.v = v;
		this.edgeCost = edgeCost;

	}

	public int getEdgeCost() {
		return edgeCost;
	}

	public void setEdgeCost(int edgeCost) {
		this.edgeCost = edgeCost;
	}

	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}

}
