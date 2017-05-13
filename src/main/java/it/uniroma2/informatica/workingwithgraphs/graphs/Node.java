package it.uniroma2.informatica.workingwithgraphs.graphs;

public class Node {
	private String label;
	private String name;

	public Node(String label, String name) {
		this.label = label;
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return " label=" + label + ", name=" + name;
	}
}
