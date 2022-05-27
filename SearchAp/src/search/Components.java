package search;

import java.net.URI;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public class Components {
	
	//to path pou pairnei to csv arxeio
	public static final String CSV_FILE_PATH= "C:\\Users\\User\\Desktop\\ANAKTISI_PL\\Search\\Data\\wiki_movie_plots_deduped.csv";
	

	 public static final int MAX_SEARCH =100;
	
	 //ta pedia pou periexei to arxeio
	 public static final String[] CONTENTS_CSV= {"Release Year","Title","Origin","Director","actors","Genre","Page","Plot"};
	
	public static Directory dir;
	 static int flagSuggestion=0 ;
	 static int flagGreek=0;
	
	 //to path pou dimiourgeitai to euretirio
	public static final String INDEX_DIR= "C:\\Users\\User\\Desktop\\document_anaktisi\\output";
	public static final String spellIndexDirectory="C:\\Users\\User\\Desktop\\document_anaktisi\\spell";
}