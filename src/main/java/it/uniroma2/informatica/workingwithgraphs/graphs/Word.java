package it.uniroma2.informatica.workingwithgraphs.graphs;

public class Word extends Node {

	private String surface;

	public Word(String label, String name) {
		super(label, name);
	}

	public String getSurface() {
		return surface;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

	public String toString() {
		return "Word [" + super.toString() + "]";
	}

}
