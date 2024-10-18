package com.newsdatapipeline.backend.Services;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.stereotype.Service;
import java.util.Properties;
import edu.stanford.nlp.pipeline.*;
@Service
public class ArticlesService {
    public boolean isMentionsTheLocation(String article, String location){
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
        props.setProperty("ner.useSUTime", "false");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        String text = article;
        String settlement = location.split(" ")[0];
        Annotation document = new Annotation(text);
        pipeline.annotate(document);
        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String ner = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                if ((ner.equals("CITY")|ner.equals("LOCATION")|ner.equals("STATE_OR_PROVINCE")) & word.equalsIgnoreCase(settlement)) {
                    return true;
                }
            }
        }
        return false;
    }
}
