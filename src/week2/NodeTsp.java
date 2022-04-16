package week2;

public class NodeTsp {
	private int cost;
	private int v;

	public NodeTsp(int v, int cost) {
		this.v = v;
		this.cost = cost;

	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}

}
