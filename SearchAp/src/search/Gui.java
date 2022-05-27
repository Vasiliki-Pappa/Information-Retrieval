package search;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.*;  


public class Gui extends JFrame{	
	JButton search;
	JButton searchTitle;
	JButton searchDirector;
	JButton searchActor;
	JButton searchOrigin;
	JButton searchYear;
	JButton searchGenre;
	JScrollPane scroll;
	String [] apotelesmata;
	JPanel cards;
	JTextPane area; // Text area
	JTextArea areahistory; // Text area for history
	JFrame fh;
	JScrollPane sbrText1 ;
	int count=0;
	JScrollPane sbrText; // Scroll pane for text area
	String currValue;
	String a;
	
	
	public Gui(String title) throws IOException {
		
		Main main=new Main();	   
		Searcher_read_index sr=new Searcher_read_index();		
		JFrame f = new JFrame(title);
	
		//text area
		area=new JTextPane();
	        
	     sbrText = new JScrollPane(area);   
	     sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	     f.getContentPane().add(sbrText);
	     sbrText.setBounds(10,220,1400,550);
	     
	       
		
		f.getContentPane().setBackground(new Color(180,180,180));
		
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	
		
		   //set size and location of frame
		   f.setSize(1200,1600);
		   f.setLocation(10, 10);
		   //make sure it quits when x is clicked
		   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		   
		   f.setDefaultLookAndFeelDecorated(true);
		   
		   
		   //Search movies
		   JLabel labelM = new JLabel("Search Movies: ");
		   labelM.setBounds(10, 50, 200, 30);
		   JTextField motto1 = new JTextField();
		   //set size of the text box
		   motto1.setBounds(100, 50, 300, 30);
		   //add elements to the frame

		   
		   JButton b = new JButton("search");
		   //x y platos kai bathos
		   b.setBounds(400, 50, 100, 30);
		   b.setBackground(new Color(200,100,100));
		   b.addActionListener(new ActionListener()
		   {
			   public void actionPerformed(ActionEvent e)
			   {
			   // Get value of textfield 
			   currValue = motto1.getText();
			   System.out.println(currValue);
			   

			   try {
					 area.setText(" "); 
					 count=0;			 
					 apotelesmata= main.search("all", currValue);
				
					 String a5 = "" ;
					 
					 
					 printGreek();
					 //if dont have this word to index
					 doSeggestions();

					for (int i = 0; i < 10; i++) { //for loop to print the array  
						count+=1;
						
						
						if (apotelesmata[i]==null) {
							continue;
						}else
							
						a5+= apotelesmata[i];
						
						} 
						area.setText(a5);
						dohighLigth(a5,currValue);					
				
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			   
			   }
			   });

		   
		   
		   
		   //title
		   JLabel labelM2 = new JLabel("Title: ");
		   labelM2.setBounds(600, 50, 200, 30);
		   JTextField motto2 = new JTextField();
		   //set size of the text box
		   motto2.setBounds(660, 50, 200, 30);

		   
		   JButton b2 = new JButton("search");
		   //x y platos kai bathos
		   b2.setBounds(860, 50, 100, 30);
		   b2.setBackground(new Color(200,100,100));
		   b2.addActionListener(new ActionListener()
		   {
			   public void actionPerformed(ActionEvent e)
			   {
			   // Get value of textfield 1
			   currValue = motto2.getText();
			   System.out.println(currValue);
			   // Set value for textfield 2
			   //textField2.setText(currValue);
			   
			   try {
					 
					 area.setText(" "); 
					 count=0;
					 apotelesmata= main.search("Title", currValue);
					
					System.out.println(apotelesmata);
					a = "" ;
					 doSeggestions();
					{
					
					
					for (int i = 0; i < 10; i++) {  
						count+=1;
						
						
						if (apotelesmata[i]!=null) {
							a+= apotelesmata[i];
						}else
							continue;
						
						}  
					
					area.setText(a);
					dohighLigth(a,currValue);
					
		
					}
				
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			   
			   }
			   });


		   
		   
		   
		   //Year
		   JLabel labelM3 = new JLabel("Year: ");
		   labelM3.setBounds(600, 80, 200, 30);
		   JTextField motto3 = new JTextField();
		   motto3.setBounds(660, 80, 200, 30);
		   
		   JButton b3 = new JButton("search");
		   //x y platos kai bathos
		   b3.setBounds(860, 80, 100, 30);
		   b3.setBackground(new Color(200,100,100));

		   b3.addActionListener(new ActionListener()
		   {
			   public void actionPerformed(ActionEvent e)
			   {
			   // Get value of textfield 
			   currValue = motto3.getText();
			   System.out.println(currValue);

			   try {

					 area.setText(" ");
					 count=0;
					 apotelesmata= main.search("Year", currValue);

					//System.out.println(apotelesmata);
					String a ="" ;
					doSeggestions();

					for (int i = 0; i < 10; i++) { 
						count+=1;

						if (apotelesmata[i]!=null) {
							a+= apotelesmata[i];
						}else
							continue;
						
						}  area.setText(a);
						dohighLigth(a,currValue);

					
				
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			   
			   }
			   });
		   
		   
		   
		   
		   
		   //Origin
		   JLabel labelM4 = new JLabel("Origin: ");
		   labelM4.setBounds(600, 110, 200, 30);
		   JTextField motto4 = new JTextField();
		   //set size of the text box
		   motto4.setBounds(660, 110, 200, 30);
		  
		   
		   JButton b4 = new JButton("search");
		   //x y platos kai bathos
		   b4.setBounds(860, 110, 100, 30);
		   b4.setBackground(new Color(200,100,100));
		   b4.addActionListener(new ActionListener()
		   {
			   public void actionPerformed(ActionEvent e)
			   {
			   // Get value of textfield 
			   currValue = motto4.getText();
			   System.out.println(currValue);

			   try {
					 area.setText(" ");
					 count=0;
					 apotelesmata= main.search("Origin", currValue);
					//System.out.println("ddddddddddddddddddddddddddddddddddd");
					
					String a ="" ;
					doSeggestions();

					for (int i = 0; i < 10; i++) { 
						count+=1;

						if (apotelesmata[i]!=null) {
							a+= apotelesmata[i];
						}else
							continue;
						
						}  area.setText(a);
						dohighLigth(a,currValue);
				
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			   
			   }
			   });
		   

		   
		   
		   //Director
		   JLabel labelM5 = new JLabel("Director: ");
		   labelM5.setBounds(600, 140, 200, 30);
		   JTextField motto5 = new JTextField();
		   //set size of the text box
		   motto5.setBounds(660, 140, 200, 30);
		   
		 
		   JButton b5 = new JButton("search");
		   //x y platos kai bathos
		   b5.setBounds(860, 140, 100, 30);
		   b5.setBackground(new Color(200,100,100));
		   b5.addActionListener(new ActionListener()
		   {
			   public void actionPerformed(ActionEvent e)
			   {
			   // Get value of textfield 
			   currValue = motto5.getText();
			   System.out.println(currValue);			   
			   try {
					  //apotelesmata=" ";
					 area.setText(" ");
					 count=0;
					  //area.setText(area.getText().replace(area.getSelectedText(),""));
					 apotelesmata= main.search("Director", currValue);
					String a ="" ;
					 doSeggestions();

					for (int i = 0; i < 10; i++) { //for loop to print the array  
						count+=1;

						if (apotelesmata[i]!=null) {
							a+= apotelesmata[i];
						}else
							continue;
						
						}  area.setText(a);
						dohighLigth(a,currValue);
				
				
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			   
			   }
			   });
		  
		   
		   //Genre
		   JLabel labelM6 = new JLabel("Genre: ");
		   labelM6.setBounds(600, 170, 200, 30);
		   JTextField motto6 = new JTextField();
		   //set size of the text box
		   motto6.setBounds(660, 170, 200, 30);
		  
		   
		   JButton b6 = new JButton("search");
		   //x y platos kai bathos
		   b6.setBounds(860, 170, 100, 30);	  
		   b6.setBackground (new Color(200,100,100));
		   b6.addActionListener(new ActionListener()
		   {
			   public void actionPerformed(ActionEvent e)
			   {
			   // Get value of textfield 
			   currValue = motto6.getText();
			   System.out.println(currValue);
			   
			   try {
					  //apotelesmata=" ";
					 area.setText(" ");
					 count=0;
					  //area.setText(area.getText().replace(area.getSelectedText(),""));
					 apotelesmata= main.search("Genre", currValue);
					//System.out.println("ddddddddddddddddddddddddddddddddddd");
					//System.out.println(apotelesmata);
					String a ="" ;
					 doSeggestions();

					for (int i = 0; i < 10; i++) { //for loop to print the array  
						count+=1;

						if (apotelesmata[i]!=null) {
							a+= apotelesmata[i];
						}else
							continue;
						
						}  area.setText(a);
						dohighLigth(a,currValue);
					
				
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			   
			   }
			   });
		   
		
		   
		   //actors
		  /* JLabel labelM7 = new JLabel("Actors: ");
		   labelM7.setBounds(600, 200, 200, 30);
		   JTextField motto7 = new JTextField();
		   //set size of the text box
		   motto7.setBounds(660, 200, 200, 30);

		   
		   JButton b7 = new JButton("search");
		   //x y platos kai bathos
		   b7.setBounds(860, 200, 100, 30);
		   b7.setBackground(new Color(200,100,100));
		   b7.addActionListener(new ActionListener()
		   {
			   public void actionPerformed(ActionEvent e)
			   {
			   // Get value of textfield 1
			   String currValue = motto2.getText();
			   System.out.println(currValue);
			   // Set value for textfield 2
			   //textField2.setText(currValue);
			   
			   try {
					  //apotelesmata=" ";
					 area.setText(" ");
					 
					 count=0;
					  //area.setText(area.getText().replace(area.getSelectedText(),""));
					 apotelesmata= main.search("actors", currValue);
					//System.out.println("ddddddddddddddddddddddddddddddddddd");
					System.out.println(apotelesmata);
					String a = "" ;
					 doSeggestions();
					{
					
					
					for (int i = 0; i < 10; i++) { //for loop to print the array  
						count+=1;
						
						
						if (apotelesmata[i]!=null) {
							a+= apotelesmata[i];
						}else
							continue;
						
						}  
					
					area.setText(a);
					dohighLigth(a,currValue);
					
		
					}
				
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			   
			   }
			   });*/


		   //next 10 results
		   JButton next = new JButton("next_10");
		   //x y platos kai bathos
		   next.setBounds(1300,200,100, 20);
		   next.setBackground(new Color(210,160,180));
		   next.addActionListener(new ActionListener()
		   {
			   public void actionPerformed(ActionEvent e)
			   {
		  
				   int noMore;
				   noMore=0;
				   	String z="";
				    area.setText("");
				    
				    int sun=count+10;
					 doSeggestions();

					for ( int j=count;j < sun; j++) { 
						//count+=1;
				    //apotelesmata.remove(1);
				    //System.out.println("ELAAA DWSEEEE ");
				    
				    if (apotelesmata[j]!=null) {
				    	z+=apotelesmata[j];
					}else
						noMore=1;
						continue;

						}  	count+=10;
						
						if (noMore==1) {
							z+="  THERE ARE NO MORE RESULTS ";
									
						}
				   	area.setText(z);	
				   	
				   	try {
						dohighLigth(z, currValue);
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
			   }
			   });
		   
		   
		   
		   //show history
		   JButton history = new JButton("History");
		   //x y platos kai bathos
		   history.setBounds(10,90,100, 20);
		   history.setBackground(new Color(204,160,180));
		   history.addActionListener(new ActionListener()
		   {
		   public void actionPerformed(ActionEvent e)
		   {
			   String his =main.getHistory();
			   System.out.println(his);
			   
			   fh = new JFrame("History");
			   fh.setSize(300, 300);
			   fh.setLocation(10, 10);
			   //make sure it quits when x is clicked
			   fh.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		   
			   fh.setDefaultLookAndFeelDecorated(true);	   
			  areahistory=new JTextArea();
		
			   
			sbrText1 = new JScrollPane(areahistory);    
		    sbrText1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    fh.getContentPane().add(sbrText1);
		    sbrText1.setBounds(10,10, 300,300);	   		  
		  
		   areahistory.setText(his);
		   
			
		   fh.add(sbrText1);
		   
		   fh.setLayout(null);
		   fh.setVisible(true);

		   }
		   });
		   
		   
		   
		   
		   //delete history
		   JButton delhistory = new JButton("Delete History");
		   //x y platos kai bathos
		   delhistory.setBounds(110,90,150,20);
		   delhistory.setBackground(new Color(204,160,180));
		   delhistory.addActionListener(new ActionListener()
		   {
		   public void actionPerformed(ActionEvent e)
		   {
		   // Get value of textfield 
		   //String currValue = motto6.getText();
			   main.deleteHistory();
		   }
		   });

	       f.add(next);

		   f.add(history);
		   f.add(delhistory);
		   
		   
		   f.add(labelM);
		   f.add(motto1);
		   f.add(b);
		   
		   f.add(labelM2);
		   f.add(motto2); 
		   f.add(b2);
		   
		   f.add(labelM3);
		   f.add(motto3);
		   f.add(b3);
		   
		   f.add(labelM4);
		   f.add(motto4);
		   f.add(b4);
		   
		   f.add(labelM5);
		   f.add(motto5);
		   f.add(b5);
		   
		   f.add(labelM6);
		   f.add(motto6);
		   f.add(b6);
		   
		  // f.add(labelM7);
		  // f.add(motto7);
		  // f.add(b7);
		   
		   //f.add(areahistory);
		   // f.add(area);  
		   
		   f.setLayout(null);
		   f.setVisible(true);
		   

		
	}
	public  void dohighLigth(String a,String currValue) throws BadLocationException{
		DefaultHighlightPainter cyanPainter = new DefaultHighlighter.DefaultHighlightPainter(new Color(250,200,200));
		String a1=a.toLowerCase();
		String currValue1=currValue.toLowerCase();
		int index = a1.indexOf(currValue1);
		while (index >= 0) {
		    System.out.println(index);
		    int indexLast=currValue1.length()+index;
			area.getHighlighter().addHighlight(index,indexLast, cyanPainter);
		    index = a1.indexOf(currValue1, index + 1);

		}
	 
	}
	
	public void printGreek() {
		if(Components.flagGreek==1) {
			area.setText(apotelesmata[0]);
		}
		
	}
	public  void doSeggestions(){

		if(Components.flagSuggestion==1) {
			
			SimpleAttributeSet attributeSet = new SimpleAttributeSet();
			StyleConstants.setItalic(attributeSet, true);
			StyleConstants.setBold(attributeSet, rootPaneCheckingEnabled);
			StyleConstants.setForeground(attributeSet, Color.black);
			//StyleConstants.setBackground(attributeSet, Color.orange);
			area.setCharacterAttributes(attributeSet, true);
			
			String a2 = "" ;
			a2+="Do you mean ?  ";
			a2+="\n";
			a2+="\n";
			for (int i = 0; i < 5; i++) {  
				//count+=1;
				
				
				if (apotelesmata[i]!=null) {
					a2+=" ";
					a2+= apotelesmata[i];
					a2+="\n";
				}else
					continue;
				
				}  
			
			area.setText(a2);
			
		}
	 
	}
	

	
	
	
	}