import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class DataManager {

	private List<String> japanese;
	private List<String> english;
	private List<String> phoneNum;
	private List<String> tags;
	
	private BufferedReader bufferedReader;
	
	public void readData() {
		try {
			japanese = new ArrayList<>();
			english = new ArrayList<>();
			phoneNum = new ArrayList<>();
			tags = new ArrayList<>();
			
			CSVReader reader = new CSVReader(new FileReader("src/source/正規表現.csv"));
			List<String[]> data = reader.readAll();
			
			for(String[] stArr : data) classification(stArr);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void classification(String[] stArr){
		
		String jaPattern = "^[ぁ-ゔ]*$"; 
		String engPattern = "^[a-zA-Z]*$";
		String phonePattern = "^0.*";
		String tagPattern = "^<.*";
		
		for(String str: stArr) {
			if(str.length() != 0) {
				str = str.trim();
				if(str.matches(jaPattern)) japanese.add(str);
				else if(str.matches(engPattern)) english.add(str);
				else if(str.matches(phonePattern)) phoneNum.add(str);
				else if(str.matches(tagPattern)) tags.add(str);
			}
		}
	}
	
	public void printList() {
		System.out.println("日本語");
		System.out.println("--------------");
		for(String str: japanese) System.out.println(str + " ");
		System.out.println("--------------");
		
		System.out.println("english");
		System.out.println("--------------");
		for(String str : english) System.out.println(str + " ");
		System.out.println("--------------");
		
		System.out.println("phoneNum");
		System.out.println("--------------");
		for(String str : phoneNum) System.out.println(str + " ");
		System.out.println("--------------");
		
		System.out.println("tags");
		System.out.println("--------------");
		for(String str : tags) System.out.println(str + " ");
		System.out.println("--------------");
	}
}
