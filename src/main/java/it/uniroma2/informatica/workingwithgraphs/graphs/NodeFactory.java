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

/**
 * E' una classe che in base a cosa gli viene passato crea un oggetto
 * corrispondente ai casi che sia "soggetto" "oggetto" "modificatore"
 */
public class NodeFactory {

	/**
	 * E' un metodo che crea un oggetto Word nel caso in cui la frase che gli
	 * viene passata contiene un complemento oggetto e prende come parametri
	 * sentence (la frase) e il parser lessico della libreria stanford
	 */
	public Node createSubjects(String sentence, LexicalizedParser lp) {
		List<String> labels = new ArrayList<String>();

		List<String> words = new ArrayList<String>();
		words = returnWords(sentence, lp);
		labels = returnLabels(sentence, lp);
		for (int i = 0; i < labels.size(); i++) {
			if (labels.get(i).equals("nsubj")) {
				return new Word("nsubj", words.get(i));
			}
		}
		return null;
	}

	/**
	 * E' un metodo che crea un oggetto Variable nel caso in cui la stringa
	 * contiene una modificatore e prende come parametri sentence (la frase) e
	 * il parser lessico della libreria stanford
	 */
	public Variable createModifiers(String sentence, LexicalizedParser lp) {

		List<String> labels = new ArrayList<String>();
		List<String> words = new ArrayList<String>();
		words = returnWords(sentence, lp);
		labels = returnLabels(sentence, lp);
		int i;
		for (i = 0; i < labels.size(); i++) {
			if (labels.get(i).equals("nmod")) {
				return new Variable("nmod", words.get(i));
			}
		}
		return null;
	}

	/**
	 * E' un metodo che crea un oggetto Word nel caso in cui la frase che gli
	 * viene passata contiene un complemento oggetto e prende come parametri
	 * sentence (la frase) e il parser lessico della libreria stanford
	 */
	public Node createObjects(String sentence, LexicalizedParser lp) {
		List<String> labels = new ArrayList<String>();
		List<String> words = new ArrayList<String>();
		words = returnWords(sentence, lp);
		labels = returnLabels(sentence, lp);
		for (int i = 0; labels.get(i) != null; i++) {
			if (labels.get(i).equals("dobj")) {
				return new Word("dobj", words.get(i));
			}
		}
		return null;
	}

	/**
	 * Il seguente metodo restituisce una lista di stringhe che contiene le
	 * etichette della frase e prende come parametri sentence (la frase) e un
	 * oggetto di tipo LexicalizedParser della libreria stanford
	 */
	public List<String> returnLabels(String sentence, LexicalizedParser lp) {

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
		List<String> listOfWords = new ArrayList<String>();
		for (int j = 0; j < listaGrammaticale.size(); j++) {

			if (listaGrammaticale.get(j).startsWith("nmod") || listaGrammaticale.get(j).startsWith("nsubj")
					|| listaGrammaticale.get(j).startsWith("dobj")) {
				String partOfTheSentence = listaGrammaticale.get(j);
				if (listaGrammaticale.get(j).startsWith("nmod")) {
					String[] parts = partOfTheSentence.split("[:]");
					listOfWords.add(parts[0]);
				} else {
					String[] part = partOfTheSentence.split("[(]");
					listOfWords.add(part[0]);
				}
			}
		}
		return listOfWords;
	}

	/**
	 * Il seguente metodo restituisce una lista di stringhe che contiene le
	 * parole delle frasi e prende come parametri sentence (la frase) e un
	 * oggetto di tipo LexicalizedParser
	 */
	public List<String> returnWords(String sentence, LexicalizedParser lp) {

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
		List<String> listOfWords = new ArrayList<String>();
		for (int j = 0; j < listaGrammaticale.size(); j++) {

			if (listaGrammaticale.get(j).startsWith("nmod") || listaGrammaticale.get(j).startsWith("nsubj")
					|| listaGrammaticale.get(j).startsWith("dobj")) {
				String partOfTheSentence = listaGrammaticale.get(j);
				if (listaGrammaticale.get(j).startsWith("nmod")) {
					String[] parts = partOfTheSentence.split("[:]");
					listOfWords.add(parts[1]);
				} else {
					String[] part = partOfTheSentence.split("[(]");
					listOfWords.add(part[1]);
				}
			}
		}
		return listOfWords;
	}
}
