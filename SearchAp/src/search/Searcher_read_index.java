
package search;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TermFrequencyAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.spell.JaroWinklerDistance;
import org.apache.lucene.search.spell.LevenshteinDistance;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.LuceneLevenshteinDistance;
import org.apache.lucene.search.spell.NGramDistance;
import org.apache.lucene.search.spell.PlainTextDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import org.apache.lucene.search.spell.LevenshteinDistance;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.LuceneLevenshteinDistance;
import org.apache.lucene.search.spell.NGramDistance;
import org.apache.lucene.search.spell.PlainTextDictionary;
import org.apache.lucene.search.spell.SpellChecker;



import java.io.IOException;
import java.nio.file.Paths;


public class Searcher_read_index {
	String apotelesmata;
	static SpellChecker s1;

	public String[] makeSearch(String type,String erwtima)throws Exception 
	  {
		
		String pinaka[]=new String[Components.MAX_SEARCH];
		int i;
		i=0;
		apotelesmata="";
		IndexSearcher searcher = createSearcher();
		

		if(type=="Title") {
			TopDocs foundDocs1 = searchByTitle(erwtima, searcher);
		 
		System.out.println("Total Results :: " + foundDocs1.totalHits);
		
		for (ScoreDoc sd : foundDocs1.scoreDocs) 
		{
			Document d = searcher.doc(sd.doc);
			System.out.println(String.format( ""+d.get("Title"))+ "   "+" \n"+String.format(d.get("Plot")+ sd.score +"  \n"+"  \n"));
			apotelesmata=(String.format(  "TITLE: " +d.get("Title"))+ "\n"+  "YEAR: " + d.get("Release Year")+"\n"+  "PLOT: " +String.format(d.get("Plot")+ sd.score+"  \n"+"  \n"+"  \n"));
			pinaka[i]=apotelesmata;
			i++;
		}
		
		
		return pinaka;
		}
		
		if(type=="Year") {
			
		TopDocs foundDocs = searchByReleaseYear(erwtima, searcher);
		 
		System.out.println("Total Results :: " + foundDocs.totalHits);
		
		for (ScoreDoc sd : foundDocs.scoreDocs) 
		{
			Document d = searcher.doc(sd.doc);
			System.out.println(String.format(d.get("Title"))+ "   "+" \n"+String.format(d.get("Plot")+"  \n"+ sd.score));
			apotelesmata=(String.format(  "TITLE: " +d.get("Title"))+" \n"+  "YEAR: " + d.get("Release Year")+"   "+" \n"+  "PLOT: " +String.format(d.get("Plot")+ sd.score+"  \n"+"  \n"+"  \n"));
			pinaka[i]=apotelesmata;
			i++;
		}
		return pinaka;
		
		}
		
		
		
		if(type=="Origin") {
			
		TopDocs foundDocs = searchByOrigin(erwtima, searcher);
		 
		System.out.println("Total Results :: " + foundDocs.totalHits);
		
		for (ScoreDoc sd : foundDocs.scoreDocs) 
		{
			Document d = searcher.doc(sd.doc);
			System.out.println(String.format(d.get("Title"))+ "   "+" \n"+String.format(d.get("Plot")+"  \n"+ sd.score));
			apotelesmata=(String.format(  "TITLE: " +d.get("Title"))+" \n"+  "YEAR: " + d.get("Release Year")+" \n"+ "ORIGIN: " +d.get("Origin")+ " \n"+ "PLOT: "+String.format(d.get("Plot")+ sd.score+"  \n"+"  \n"+"  \n"));
			pinaka[i]=apotelesmata;
			i++;
		}
		return pinaka;
		}
		
		
		
		if(type=="Director") {
			
		TopDocs foundDocs = searchByDirector(erwtima, searcher);
		 
		System.out.println("Toral Results :: " + foundDocs.totalHits);
		
		for (ScoreDoc sd : foundDocs.scoreDocs) 
		{
			Document d = searcher.doc(sd.doc);
			System.out.println(String.format(d.get("Title"))+ "   "+" \n"+String.format(d.get("Plot")+"  \n"+ sd.score));
			apotelesmata=(String.format(  "TITLE: " +d.get("Title"))+  "YEAR: " + d.get("Release Year")+" \n" +  "DIRECTOR: " +d.get("Director")+"   "+" \n"+ "PLOT: "+String.format(d.get("Plot")+ sd.score+"  \n"+"  \n"+"  \n"));
			pinaka[i]=apotelesmata;
			i++;
		}
		return pinaka;
		}
		
		if(type=="Plot") {
			
		TopDocs foundDocs = searchByPlot(erwtima, searcher);
		 
		System.out.println("Toral Results :: " + foundDocs.totalHits);
		
		for (ScoreDoc sd : foundDocs.scoreDocs) 
		{
			Document d = searcher.doc(sd.doc);
			System.out.println(String.format(d.get("Title"))+ "   "+" \n"+String.format(d.get("Plot")+"  \n"+ sd.score));
			apotelesmata=(String.format(  "TITLE: " +d.get("Title"))+  "YEAR: " + d.get("Release Year")+  " PLOT: " +d.get("Plot")+" \n"+ "PLOT: "+String.format(d.get("Plot")+ sd.score+"  \n"+"  \n"+"  \n"));
			pinaka[i]=apotelesmata;
			i++;
		}
		return pinaka;
		}
		
		
		if(type=="Genre") {
			
			TopDocs foundDocs = searchByGenre(erwtima, searcher);
			 
			System.out.println("Toral Results :: " + foundDocs.totalHits);
			
			for (ScoreDoc sd : foundDocs.scoreDocs) 
			{
				Document d = searcher.doc(sd.doc);
				System.out.println(String.format(d.get("Title"))+ "   "+" \n"+String.format(d.get("Plot")+"  \n"+ sd.score));
				apotelesmata=(String.format(  "TITLE: " +d.get("Title")) +"YEAR: " + d.get("Release Year")+" \n"+ "GENRE: " +d.get("Genre")+"   "+" \n"+ "PLOT: "+String.format(d.get("Plot")+ sd.score+"  \n"+"  \n"+"  \n"));
				pinaka[i]=apotelesmata;
				i++;
			}
			return pinaka;
			}
		
		if(type=="Page") {
			
			TopDocs foundDocs = searchByPage(erwtima, searcher);
			 
			System.out.println("Toral Results :: " + foundDocs.totalHits);
			
			for (ScoreDoc sd : foundDocs.scoreDocs) 
			{
				Document d = searcher.doc(sd.doc);
				System.out.println(String.format(d.get("Title"))+ "   "+" \n"+String.format(d.get("Plot")+"  \n"+ sd.score));
				apotelesmata=(String.format(  "TITLE: " +d.get("Title"))+  "  Page : " +d.get("Page")+"   "+" \n"+String.format(d.get("Plot")+ sd.score+"  \n"+"  \n"));
				pinaka[i]=apotelesmata;
				i++;
			}
			return pinaka;
			}
		
		
		if(type=="actors") {
			
			TopDocs foundDocs = searchByactors(erwtima, searcher);
			 
			System.out.println("Total Results :: " + foundDocs.totalHits);
			
			for (ScoreDoc sd : foundDocs.scoreDocs) 
			{
				Document d = searcher.doc(sd.doc);
				//System.out.println(String.format(d.get("Title"))+ "   "+" \n"+String.format(d.get("Plot")+"  \n"+ sd.score));
				System.out.println(String.format(d.get("Title"))+ "   "+" \n"+String.format(d.get("Plot")+"  \n"+ sd.score));
				apotelesmata=(String.format(  "TITLE: " +d.get("Title"))+  "  Actors: " +d.get("actors")+"   "+" \n"+String.format(d.get("Plot")+ sd.score+"  \n"+"  \n"));
				pinaka[i]=apotelesmata;
				i++;			}
			return pinaka;
			
			
			
			}
		if(type=="all") {
			TopDocs foundDocs1 = searchAll(erwtima, searcher);
		 
		System.out.println("Total Results :: " + foundDocs1.totalHits);
		
		for (ScoreDoc sd : foundDocs1.scoreDocs) 
		{
			Document d = searcher.doc(sd.doc);
			
			//System.out.println(String.format(d.get("Title"))+ "   "+" \n"+String.format(d.get("Plot")+"  \n"+ sd.score));

			apotelesmata=( "TITLE: "+ d.get("Title")+"  \n"+  "YEAR: " + d.get("Release Year")+" \n"+ "ORIGIN: " +d.get("Origin")+ "\n"+"ACTORS: "+d.get("actors") +" \n" +  "DIRECTOR: " +d.get("Director")+ "\n"+"PAGE : " +d.get("Page")+"  \n"+ "PLOT: "+ d.get("Plot")+"  \n"+"  \n" );
			
			
			///apotelesmata="edddewelksjdfs";
			pinaka[i]=apotelesmata;
			i++;			}
		
		
		System.out.println(Arrays.toString(pinaka));
		
		return pinaka;
		}
		
		
			
		
		
		else{
		String[] n = null;
		n[0]="!";
		return n ;}
	}
	
	
	
	private static TopDocs searchByReleaseYear(String ReleaseYear, IndexSearcher searcher) throws Exception
	{
		QueryParser qp = new QueryParser("Release Year", new StandardAnalyzer());
		Query ReleaseYearQuery = qp.parse(ReleaseYear);
		TopDocs hits = searcher.search(ReleaseYearQuery, Components.MAX_SEARCH);
	
		return hits;
	}
	public static TopDocs searchByTitle(String Title, IndexSearcher searcher) throws Exception
	{
		QueryParser qp = new QueryParser("Title", new StandardAnalyzer());
		Query TitleQuery = qp.parse(Title);
		TopDocs hits = searcher.search(TitleQuery, Components.MAX_SEARCH);
		return hits;
	}
	private static TopDocs searchByDirector(String Director, IndexSearcher searcher) throws Exception
	{
		QueryParser qp = new QueryParser("Director", new StandardAnalyzer());
		Query DirectorQuery = qp.parse(Director);
		TopDocs hits = searcher.search(DirectorQuery, Components.MAX_SEARCH);
		return hits;
	}
	private static TopDocs searchByOrigin(String Origin, IndexSearcher searcher) throws Exception
	{
		QueryParser qp = new QueryParser("Origin", new StandardAnalyzer());
		Query originQuery = qp.parse(Origin);
		TopDocs hits = searcher.search(originQuery, Components.MAX_SEARCH);
		return hits;
	}
	private static TopDocs searchByGenre(String Genre, IndexSearcher searcher) throws Exception
	{
		QueryParser qp = new QueryParser("Genre", new StandardAnalyzer());
		Query GenreQuery = qp.parse(Genre);
		TopDocs hits = searcher.search(GenreQuery, Components.MAX_SEARCH);
		return hits;
	}
	private static TopDocs searchByPage(String Page, IndexSearcher searcher) throws Exception
	{
		QueryParser qp = new QueryParser("Page", new StandardAnalyzer());
		Query PageQuery = qp.parse(Page);
		TopDocs hits = searcher.search(PageQuery, Components.MAX_SEARCH);
		return hits;
	}
	private static TopDocs searchByPlot(String Plot, IndexSearcher searcher) throws Exception
	{
		QueryParser qp = new QueryParser("Plot", new StandardAnalyzer());
		Query PlotQuery = qp.parse(Plot);
		TopDocs hits = searcher.search(PlotQuery, Components.MAX_SEARCH);
		return hits;
	}
	
	private static TopDocs searchByactors(String actors, IndexSearcher searcher) throws Exception
	{
		QueryParser qp = new QueryParser("actors", new StandardAnalyzer());
		Query PlotQuery = qp.parse(actors);
		TopDocs hits = searcher.search(PlotQuery, Components.MAX_SEARCH);
		return hits;
	}
	private static TopDocs searchAll(String searchQ, IndexSearcher searcher) throws Exception
	{
	MultiFieldQueryParser queryParser = new MultiFieldQueryParser(Components.CONTENTS_CSV, new StandardAnalyzer());
	//QueryParser qp =new QueryParser(Components.CONTENTS, new StandardAnalyzer());
	Query q = queryParser.parse(searchQ);
	TopDocs hits = searcher.search(q, Components.MAX_SEARCH);
	return hits;
	}



	static IndexSearcher createSearcher() throws IOException {
		Directory dir = FSDirectory.open(Paths.get(Components.INDEX_DIR));
		IndexReader reader = DirectoryReader.open(dir);
		IndexSearcher searcher = new IndexSearcher(reader);
		return searcher;
	}


	
	
	
	
	
	

	public static String [] correctWord(String Query,String pedio) throws Exception {
		//dimiourgia spell index
		Directory spell1 = FSDirectory.open(Paths.get(Components.spellIndexDirectory));

		s1=createspell(spell1,pedio);
		s1.setAccuracy(0.1f);
		//s1.getComparator();
		String[] suggestions =null;
		List<String> apo;

		suggestions = s1.suggestSimilar(Query, 5);
		System.out.println("smpikaaaaaaaa stin spellll" );
		for(String element:suggestions) {
		System.out.println("suggestion " +element);
		}



		return suggestions;

		}

		public static List<String> tokenizeString(Analyzer analyzer, String value) {
		List<String> result = new ArrayList<String>();
		try {

		TokenStream stream = analyzer.tokenStream(null, new StringReader(value));




		stream.reset();
		while (stream.incrementToken()) {
		result.add(stream.getAttribute(CharTermAttribute.class).toString());
		}
		} catch (IOException e) {
		// not thrown b/c we're using a string reader...
		throw new RuntimeException(e);
		}
		return result;
		}
		public static SpellChecker createspell(Directory spell,String pedio) throws Exception {
		SpellChecker s = null ;




		s = new SpellChecker(spell);//dimiourgia index gia spelll check

		IndexWriterConfig config = new IndexWriterConfig(new KeywordAnalyzer());
		IndexReader reader = DirectoryReader.open(Components.dir);
		s.indexDictionary(new LuceneDictionary(reader,pedio),config,true);
		System.out.println("dimiourgithikeeee");




		return s;


		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	

}





