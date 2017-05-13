package it.uniroma2.informatica.workingwithgraphs.graphs;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Runner {

	public static void main(String[] args) {
		/**
		 * L'oggetto Logger è necessario per sopprimere i warnings di log4j che
		 * vengono stampati nella console al momento dell'esecuzione
		 */
		Logger.getRootLogger().setLevel(Level.OFF);
		String parserModel = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
		LexicalizedParser lp = LexicalizedParser.loadModel(parserModel);
		String sentence = "The cat eats a dog with chocolate";
		System.out.println(sentence);
		NodeFactory lel = new NodeFactory();
		Relation verbo = new Relation(lel.createSubjects(sentence, lp), lel.createObjects(sentence, lp), null, null);
		verbo.recognizeVerb(sentence, lp);
		System.out.println(verbo);
		System.out.println(lel.createSubjects(sentence, lp));
		System.out.println(lel.createObjects(sentence, lp));
		System.out.println(lel.createModifiers(sentence, lp));
	}
}
