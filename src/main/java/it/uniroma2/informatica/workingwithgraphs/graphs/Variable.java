package it.uniroma2.informatica.workingwithgraphs.graphs;

public class Variable extends Node {

	private Word value = null;

	public Variable(String label, String name) {
		super(label, name);
	}

	public Word getValue() {
		return value;
	}

	public void setValue(Word value) {
		this.value = value;
	}

	public String toString() {
		return "Variable [" + super.toString() + "]";
	}

}
