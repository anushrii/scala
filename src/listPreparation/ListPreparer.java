package listPreparation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class ListPreparer {
	
	public static void main(String[] args) throws FileNotFoundException{
	         List<Item> itemList1 = readItemList(null) ;   
	         List<Item>itemList2 = readItemList(null);
			 List<Item> itemList3 = combineItemLists(itemList1, itemList2);
			 writeItemList(itemList3);
	}


	/** Choose a file and read it in as a list of lines. 
	 * @throws FileNotFoundException */
	public static List<String>  readFileAsListOfLines() throws FileNotFoundException{
		// Choosing a file and reading it
		final JFileChooser chooser = new JFileChooser();
		int response = chooser.showOpenDialog(null);
		File filePath = chooser.getSelectedFile(); 
		 if (response == JFileChooser.APPROVE_OPTION) {
			 return readFileAsListOfLines(filePath);
		      }
		 else return readFileAsListOfLines();
	
	 }

	 /** Read in the named file as a list of lines. 
	 * @throws FileNotFoundException */
	public static List<String> readFileAsListOfLines(File file) throws FileNotFoundException {
		Scanner s = new Scanner(file);
		ArrayList<String> list = new ArrayList<String>();
		while (s.hasNextLine()){
		    list.add(s.nextLine());
		}
		s.close();
		return list;

		
	}
	
	/** Read in the File as a list of lines. 
	 * @throws FileNotFoundException */
	public static List<String> readFileAsListOfLines(String fileName) throws FileNotFoundException {
	    File file = new File(fileName);
	    return  readFileAsListOfLines(file);
	}

	 /** Choose a file and save the given item list on it. */
	private static void writeItemList(List<Item> list) throws FileNotFoundException {
		final JFileChooser chooser = new JFileChooser();
		int response = chooser.showSaveDialog(null);
		File filePath = chooser.getSelectedFile(); 
		 if (response == JFileChooser.APPROVE_OPTION) {
			  PrintWriter stream = new PrintWriter(filePath);
		      for (Item item: list) stream.println(item);
		      stream.close();
		 }
	}


	 /** Find stimulus stimulus in item list. */
	public static Item findStimulus(Item goal, List<Item> list){
		for( Item item:list){
			if(item.equalStimulus(goal)) return item;
		}
		return null;	
	}

	public static List<Item> combineItemLists(List<Item> list1, List<Item> list2) {
		List<Item>  list3 = new ArrayList<Item>() ;
     for(Item item:list1){
    	 list3.add(item);
     }
     for(Item item2:list2){
    	 Item item1 = findStimulus(item2, list1);
    	 int wrong = 1;
		if(item1 == null )  wrong  = 0;
    	 switch (wrong){
    	 case 1 :   if (!( item2.equalResponse(item1))) {
           System.out.println("1 " + item1.getStimulus() + " || " + item1.getResponse());
           System.out.println("2 " + item2.getStimulus() + " || " + item2.getResponse());
           System.out.println("Which? ");
           Scanner s = new Scanner(System.in);
           String str = s.next();
           str = str.trim();
           switch(str) {
           case "1": 
           case "2": item1.setResponse(item2.getResponse());
           default : item1.setResponse(str);
           }
         }
    	 default:  list3.add(item2);
         }
       }
       return list3;
    	 
     }


	/** Given a File, file name, or None, read in a list of Items. 
	 * @throws FileNotFoundException */
	public static List<Item> readItemList(Object o) throws FileNotFoundException {
		List<Item> item =  new ArrayList<Item>();
		List<String> lines = new ArrayList<String>();
		if (o == null) lines =  readFileAsListOfLines();
		if (o instanceof String) lines =  readFileAsListOfLines(o.toString());
		if (o instanceof File) lines =  readFileAsListOfLines((File) o);
		
		for (String line: lines)
		{
		 item.add(Item.apply(line));
		}
		return item;
	}
}
