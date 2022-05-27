package search;


import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Locale;




public class Main {
	static String[] apotelesmata;
	//Directory dir;
	//static Directory dir;
	String indexDir = "INDEX";
	String dataDir = "DATA";
	Reader_input indexer;
	Searcher_read_index searcher;
	static IndexSearcher search;
	private int j;
	private String str;
	private static String s2;
	private static ArrayList<String> history = new ArrayList<String>();



	//main
	  public static void main(String[] args) throws Exception 
	  {
		  try{
		  //Components.dir=new RAMDirectory();
			
		 Components.dir = FSDirectory.open(Paths.get(Components.INDEX_DIR));
			Reader_input index=new Reader_input();
			//index.make_index(Components.dir);
			Searcher_read_index searcher=new Searcher_read_index(); 
		  
		  Gui start;
		  start = new Gui("Searching Machine for Movies");

		  
		  
		  
	}catch (IOException e){
			e.printStackTrace();
		}
		  

	  }
	  
	  public Directory gerdir() {
		  return Components.dir;
	  }
	  
		//Method to index files from directory
	private static void createIndex() throws IOException{
		
			int numIndexed; 
			long startTime = System.currentTimeMillis(); 
			 Reader_input indexer=new Reader_input();
			 indexer.make_index(Components.dir);
			
			long endTime = System.currentTimeMillis();
			
			
			System.out.println(" File indexed, time taken: " +(endTime-startTime)+" ms");
			//return s;
		}
		
		

		// Dimiourgia arxeiou
		public String getHistory(){
			j=1;
			str="";
			for(int i=0; i<history.size(); i=i+2){
				if(history.size()==1){
					str = history.get(i);
					break;
				}else if(j>=history.size()){
					str = str + history.get(i) + "\n" ;
					break;
				}else {
					str = str+ history.get(i) + "\n" + history.get(j) + "\n";
					j = j + 2;
				}
			}
			return  str;
		}

		// Delete the User History
		public void deleteHistory(){
			history = new ArrayList<String>();
		}

		
		
		// Method to get User's search and display the files(documents) that are the most relevant
		public static String[] search(String type,String searchQuery) throws Exception {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			LocalDateTime now = LocalDateTime.now();
			//add search query in history
			history.add("(" + dtf.format(now) + "):  " + "  " + searchQuery);
			String[] splitter = searchQuery.toLowerCase(Locale.ROOT).split("(?!^)");
			
			String[] detectGRLanguage = {"α","β","γ","δ","ε","ζ","η","θ","ι","κ","λ","μ","ν","ξ","ο","π","ρ","σ","τ","υ","φ","χ","ψ","ω"};

			for(int i=0; i<detectGRLanguage.length; i++){
				if(splitter[0].equals(detectGRLanguage[i])){
					s2="Please change your keyboard language from Greek to English" + "\n";
					//s2="";
					//System.out.println(err);
					apotelesmata[0]=s2;
					Components.flagGreek=1;
					return apotelesmata;
				}
			}

			Searcher_read_index searcher1 = new Searcher_read_index();
			//search =searcher1.createSearcher(dir);;
			apotelesmata=searcher1.makeSearch(type, searchQuery);
			
			//System.out.println("EDWww");
			//System.out.println(apotelesmata.length);
			
			if(apotelesmata[0]==null) {
				
				apotelesmata= searcher1.correctWord(searchQuery,type);
				Components.flagSuggestion=1;
				return apotelesmata;
				//System.out.println("EDWww");
			}
			//String type="Title";
			long startTime = System.currentTimeMillis();
			//Searcher_read_index.makeSearch(dir,type,searchQuery);
			long endTime = System.currentTimeMillis();
			
			
			int disp=0;
			return apotelesmata;
			
		
			
		}
		



	

}

