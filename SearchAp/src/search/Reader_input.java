

package search;
import java.io.FileReader;
import com.opencsv.*;
import java.io.IOException;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import java.io. * ;

public class Reader_input {


	public static void make_index(Directory dir) throws IOException
	{
	  
	  
	 
	  
	  IndexWriter writer = createWriter( dir);
	     
	    //Builds an analyzer with the default stop words
	    Analyzer analyzer = new StandardAnalyzer();
	    //readAllDataAtOnce(CSV_FILE_PATH);
	    //Write some docs to RAMDirectory
	    readDataLineByLine(writer, analyzer);
	     
	    writer.close();
	    //Search indexed docs in RAMDirectory
	    


	}


	public static void readDataLineByLine(IndexWriter writer, Analyzer analyzer)
	{

		try {

			FileReader filereader = new FileReader(Components.CSV_FILE_PATH);

			// create csvReader object passing
			// filereader as parameter
			CSVReader csvReader = new CSVReader(filereader);
			String[] nextRecord;


			String overflow ;
			String [] employee = new String [8];
			int k=0;
	// we are going to read data line by line
			while ((nextRecord = csvReader.readNext()) != null) {
				k=0;
	  
				if(nextRecord.length!=8) {
					break;
				}
	    
	  
				for (String cell : nextRecord) {
	    
	    
					if(nextRecord.length==8) {
						employee[k]=cell;
						k++;
					}else {
						k=-3;
						continue;
					}
	    //System.out.println("one ceeeellllllllllllllll"+cell + "\t");
	    
	     //System.out.println("Emp[ Year   =" + employee[0] + ", Title   = " + employee[1] + ",  xwraaa  =" + employee[2] + ", skinothetiw =" + employee[3] + ", ithopoioi   = " + employee[4] + ", eidos  = " + employee[5] + ", wikii= " + employee[6] + ", !!!!!!!!!!perigrafi taineia !!!!!!!!!= " + employee[7] + "\n");
	 

				} if(k<0) {
					continue;
				}
				else {
	    
	  //System.out.println("Emp[ Year   =" +employee[0] + ", Title   = " + employee[1] + ",  xwraaa  =" +employee[2] + ", skinothetiw =" + employee[3] + ", ithopoioi   = " + employee[4] + ", eidos  = " + employee[5] + ", wikii= " + employee[6] + ", !!!!!!!!!!perigrafi taineia !!!!!!!!!= " + employee[7] + "\n");
					indexDoc(writer,employee[0] ,employee[1],employee[2],employee[3],employee[4],employee[5],employee[6],employee[7]);
				}
				System.out.println();
			}
	  
	  
	}catch (Exception e) {
		e.printStackTrace();
	}
}
static void indexDoc(IndexWriter writer, String Release_Year, String Title,String Origin,String Director,String actors ,String Genre,String Page,String Plot ) throws IOException
{
	   Document doc = new Document();
	   doc.add(new TextField("Release Year", Release_Year,Store.YES));
	   doc.add(new TextField("Title", Title, Store.YES));
	   doc.add(new TextField("Origin", Origin, Store.YES));
	   doc.add(new TextField("Director", Director, Store.YES));
	   doc.add(new TextField("actors", actors, Store.YES));
	   doc.add(new TextField("Genre", Genre, Store.YES));
	   doc.add(new TextField("Page", Page, Store.YES));
	   doc.add(new TextField("Plot", Plot, Store.YES));


	   
	   writer.addDocument(doc);
	 }
private static IndexWriter createWriter(Directory dir) throws IOException 
	{
		//FSDirectory dir = FSDirectory.open(Paths.get(dir));
		IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
		config.setOpenMode(OpenMode.CREATE_OR_APPEND);
		IndexWriter writer = new IndexWriter(dir, config);
		
		return writer;
	}


	 
	}

