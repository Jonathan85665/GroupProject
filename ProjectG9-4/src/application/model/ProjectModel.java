package application.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class ProjectModel {

	private static HashMap<String, String> h = new HashMap<String, String>();
	private static Properties p = new Properties();
	private static File f;
	private static FileInputStream is = null;
	private static FileOutputStream os = null;

	//In Income: Increase income (budget limit) by x
	public static void incomeIncrease(int x){
		try {
			begin();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR@ BEGIN");
		}
		//System.out.println("INCREASING: "+x);
		//top
		int temp = Integer.valueOf(h.get("INCOME")) + x;
		h.put("INCOME", String.valueOf(temp));
		temp = Integer.valueOf(h.get("FREE")) + x;
		h.put("FREE", String.valueOf(temp));
		//bottom
		try {
			end();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR@ END");
		}
	}
	//In Income:  Decrease income (budget limit) by x
	public static void incomeDecrease(int x){
		try {
			begin();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR@ BEGIN");
		}
		//System.out.println("DECREASING: "+x);
		//top
		if(Integer.valueOf(h.get("INCOME")) < x){
			System.out.println("ERROR: INCOME CANNOT BE REDUCED BELOW ZERO");
		} else if(Integer.valueOf(h.get("FREE")) < x) {
			System.out.println("ERROR: NOT ENOUGH FREE BUDGET TO REDUCE INCOME");
		}
		else{
			int temp = Integer.valueOf(h.get("INCOME")) - x;
			h.put("INCOME", String.valueOf(temp));
			temp = Integer.valueOf(h.get("FREE")) - x;
			h.put("FREE", String.valueOf(temp));
		}
		//bottom
		try {
			end();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR@ END");
		}
	}
	//In Spend: increase x (budget) by y(expense)
	public static void spend(String x, int y){
		try {
			begin();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR@ BEGIN");
		}
		//System.out.println("SPENDING:"+x+" - "+y);
		//top
		int temp = Integer.valueOf(h.get("FREE")) - y;
		if(h.containsKey(x)){
			//FREE - SPEND = NEWFREE (TEMP)
			if(temp < 0){
				System.out.println("ERROR: NOT ENOUGH FREE BUDGET");
			}
			else{
				h.put(x, String.valueOf(Integer.valueOf(h.get(x)) + y));
				h.put("FREE", String.valueOf(temp));
			}
		}
		else if(temp >= 0){
			h.put(x, String.valueOf(y));
			h.put("FREE", String.valueOf(temp));
		}
		else{
			System.out.println("ERROR: NOT ENOUGH FREE BUDGET");
		}
		//bottom
		try {
			end();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR@ END");
		}
	}
	//In Spend: reset non-INCOME values
	public static void reset(){
		try {
			begin();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR@ BEGIN");
		}
		//System.out.println("RESETTING");
		//top
		String temp = h.get("INCOME");
		//loop to set all values in h to 0
		for (String key : h.keySet()) {
			h.put(key, "0");
		}
		//corrects INCOME and FREE
		h.put("INCOME", temp);
		h.put("FREE", temp);
		//bottom
		try {
			end();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR@ END");
		}
	}
	//In View: Load list elements.
	public static List<String> load(){
		try {
			begin();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR@ BEGIN");
		}
		//System.out.println("LOADING");
		//top
		List<String> out = new ArrayList<String>();
		for (String key : h.keySet()) {
			out.add(key+"="+h.get(key));
		}
		//bottom
		try {
			end();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR@ END");
		}
		//return after save
		return out;
	}
	//prepare data saved in file
	public static void begin() throws IOException{
		f = new File("data.properties");
		if(!f.exists()){
			//System.out.print("NF ");
			f.createNewFile();
		}
		//System.out.print("B ");
		is = new FileInputStream(f);
		h = new HashMap<String, String>();
		p = new Properties();
		p.load(is);
		for (String key : p.stringPropertyNames()){
			h.put(key, p.get(key).toString());
		}
		//Adds Income = 0 id not found
		if(!h.containsKey("INCOME")){
			h.put("INCOME", "0");
			if(!h.containsKey("FREE")){
				h.put("FREE", "0");
			}
		} else if(!h.containsKey("FREE")){
			String temp = h.get("INCOME");
			h.put("FREE", temp);
		}
		//By this point, the HashMap should be available for use
		//System.out.println("B");
	}
	//save changes made to the HashMap in a Properties file
	public static void end() throws IOException{
		//System.out.print("E ");
		os = new FileOutputStream(f);
		/*for (String key : h.keySet()) {
			if(!key.equals("FREE") && Integer.valueOf(h.get(key)) == 0){
				h.remove(key);
				System.out.println("REMOVING "+key);
			}
		}*/
		for (HashMap.Entry<String, String> entry : h.entrySet()) {
		    p.put(entry.getKey(), entry.getValue());
		}
		p.store(os, null);
		//By this point, the HashMap should be saved into the data.properties file
		//System.out.println("E");
	}
}
