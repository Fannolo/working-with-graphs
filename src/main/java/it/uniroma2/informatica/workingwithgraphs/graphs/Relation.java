package it.uniroma2.informatica.workingwithgraphs.graphs;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;

public class Relation {
	private Node in;
	private Node out;
	private String label;
	private String name;

	public Relation(Node in, Node out, String label, String name) {
		this.in = in;
		this.out = out;
		this.label = label;
		this.name = name;
	}

	public Node getIn() {
		return in;
	}

	public void setIn(Node in) {
		this.in = in;
	}

	public Node getOut() {
		return out;
	}

	public void setOut(Node out) {
		this.out = out;
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

	/**
	 * Questo metodo riconosce il verbo all'interno di una Stringa tramite le
	 * funzioni (tipo parser) della libreria stanford e sempre con questa divide
	 * la frase in un albero, infine assegna i corrispettivi label e name
	 * (etichetta e nome del verbo) al costruttore dell'oggetto Relation.
	 */
	public void recognizeVerb(String sentence, LexicalizedParser lp) {
		List<CoreLabel> rawWords = Sentence.toCoreLabelList(sentence);
		Tree parse = lp.apply(rawWords);

		TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
		Tokenizer<CoreLabel> tok = tokenizerFactory.getTokenizer(new StringReader(sentence));
		List<CoreLabel> rawWords2 = tok.tokenize();
		parse = lp.apply(rawWords2);

		// PennTreebankLanguagePack for English
		TreebankLanguagePack tlp = lp.treebankLanguagePack();
		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
		GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
		List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();

		String lista = null;
		List<String> listaGrammaticale = new ArrayList<String>();
		for (int i = 0; i < tdl.size(); i++) {
			lista = tdl.get(i).toString();
			listaGrammaticale.add(lista);
		}

		for (int j = 0; j < listaGrammaticale.size(); j++) {
			if (listaGrammaticale.get(j).startsWith("root")) {
				String partOfTheSentence = listaGrammaticale.get(j);
				System.out.println(partOfTheSentence);
				String[] parts = partOfTheSentence.split("[(]");
				setLabel(parts[0]);
				setName(parts[1]);

			}
		}

	}

	public String toString() {
		return "Relation [in=" + in + ", out=" + out + ", label=" + label + ", name=" + name + "]";
	}

}
