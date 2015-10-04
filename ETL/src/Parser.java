/**
* Parser.java
* 
* @author  Sharandin Vitaliy
* @version 1.0
* @since   2014-12-10 
*/
import java.awt.List;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;

import org.eclipse.swt.widgets.Text;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {
	/**
	 * The document object is necessary to store parsed webpage.
	 */
	private Document doc;
	/**
	 * Number of pages with reviews on Ceneo.pl for current product.
	 */
	private int numberOfPages;
	/**
	 * Container attribute of reviews.
	 */
	private ArrayList<String> pros, cons, summary, stars, author, date, advice, reviewUsefullness;
	/**
	 * Product information attribute.
	 */
	private String type, brand, model;
	/**
	 * Url for reviews from Ceneo.pl.
	 */
	private String url;
	/**
	 * Initializes array lists.
	 */
	public Parser(){
		pros =new ArrayList<>();
		cons =new ArrayList<>();
		summary =new ArrayList<>();
		stars =new ArrayList<>();
		author =new ArrayList<>();
		advice =new ArrayList<>();
		date = new ArrayList<>();
		reviewUsefullness = new ArrayList<>();
	}
	/**
	 * Prints the name of product, which page was parsed.
	 */
	public void printParsingResults(){
		Displayer.printToConsole("Reviews for "+getBrand()+" "+getModel()+" parsed"+"\n");
	}
	/**
	 * Iterates through review webpages from Ceneo.pl.
	 */
	public void extractAllReviews() throws IOException, ParseException{
		
		String mainUrlAddress = url.substring(0, url.indexOf('#'));
		
		extractProductInfo();
		extractPageProductReviews();
		
		for(int i=2;i<=getAmountOfPages();i++){
			String currentPage = mainUrlAddress+"/opinie-"+i;
			connectToWebPage(currentPage);
			extractPageProductReviews();
		}
		
	}
	/**
	 * Connects to Ceneo.pl webpage using starting link from reviews page.
	 * @param url Url from Ceneo.pl reviews webpage. 
	 */
	public void connectToWebPage(String url) throws IOException {
		
		this.url = url;
		
				doc = Jsoup.connect(url).get();
				
	}
	/**
	 * Parses and extracts information about product from Ceneo.pl.
	 */
	public void extractProductInfo(){
		Elements nameElements = doc.getElementsByTag("h1");
		Elements typeElements = doc.getElementsByTag("strong");
		
		String[] productName = nameElements.get(0).text().split(" ");
		
		Elements siblingsForType = typeElements.get(0).siblingElements();
		
		type = siblingsForType.last().child(0).text();
		brand = productName[0];
		model = productName[1];
	}
	/**
	 * Parses and extracts information for one review page on Ceneo.pl.
	 */
	public void extractPageProductReviews() throws ParseException{
		SimpleDateFormat sourceFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat databaseFormat = new SimpleDateFormat("yyyy-MM-dd");
        Elements reviews = doc.getElementsByClass("product-review");
		
        for(int i=0;i<reviews.size();i++){
        	
        	String prosElements="";
    		String consElements="";
        	
			stars.add(reviews.get(i).child(0).child(0).child(1).text()); 
			  
			if(reviews.get(i).child(0).child(0).siblingElements().hasClass("product-review-summary") && (reviews.get(i).child(0).child(0).siblingElements().hasClass("product-review-pz")||reviews.get(i).child(0).child(0).siblingElements().hasClass("product-review-pu"))){
				int authorDateCounter = 0;
				
				advice.add(reviews.get(i).child(0).child(1).child(0).text());
				
				if(reviews.get(i).child(0).child(0).siblingElements().hasClass("product-review-pz-store")){authorDateCounter=4;}
				else{authorDateCounter=3;}
				
				String authorDate = reviews.get(i).child(0).child(authorDateCounter).text();
				
				if(authorDate.contains("(a)")){
					author.add(authorDate.substring(authorDate.indexOf("(a)")+4, authorDate.indexOf("dnia")-1));
				}
				
				else{
					author.add(authorDate.substring(authorDate.indexOf("napisał")+7, authorDate.indexOf("dnia")-1));
				}
				
				Date sourceDate = sourceFormat.parse(authorDate.substring(authorDate.indexOf("dnia")+5, authorDate.indexOf("r.")-1));
				date.add(databaseFormat.format(sourceDate));
			}
			
			else if(!reviews.get(i).child(0).child(0).siblingElements().hasClass("product-review-summary") && (reviews.get(i).child(0).child(0).siblingElements().hasClass("product-review-pz")||reviews.get(i).child(0).child(0).siblingElements().hasClass("product-review-pu"))){
				advice.add(null);
				String authorDate = reviews.get(i).child(0).child(2).text();
				
				if(authorDate.contains("(a)")){
					author.add(authorDate.substring(authorDate.indexOf("(a)")+4, authorDate.indexOf("dnia")-1));
				}
				
				else{
					author.add(authorDate.substring(authorDate.indexOf("napisał")+7, authorDate.indexOf("dnia")-1));
				}
				
				Date sourceDate = sourceFormat.parse(authorDate.substring(authorDate.indexOf("dnia")+5, authorDate.indexOf("r.")-1));
				date.add(databaseFormat.format(sourceDate));
			}
			
			else if(reviews.get(i).child(0).child(0).siblingElements().hasClass("product-review-summary") && (!reviews.get(i).child(0).child(0).siblingElements().hasClass("product-review-pz") && !reviews.get(i).child(0).child(0).siblingElements().hasClass("product-review-pu"))){
				advice.add(reviews.get(i).child(0).child(1).child(0).text());
				String authorDate = reviews.get(i).child(0).child(2).text();
				
				if(authorDate.contains("(a)")){
					author.add(authorDate.substring(authorDate.indexOf("(a)")+4, authorDate.indexOf("dnia")-1));
				}
				
				else{
					author.add(authorDate.substring(authorDate.indexOf("napisał")+7, authorDate.indexOf("dnia")-1));
				}
				
				Date sourceDate = sourceFormat.parse(authorDate.substring(authorDate.indexOf("dnia")+5, authorDate.indexOf("r.")-1));
				date.add(databaseFormat.format(sourceDate));
			}
			
			else{
				advice.add(null);
				String authorDate = reviews.get(i).child(0).child(1).text();
				
				if(authorDate.contains("(a)")){
					author.add(authorDate.substring(authorDate.indexOf("(a)")+4, authorDate.indexOf("dnia")-1));
				}
				
				else{
					author.add(authorDate.substring(authorDate.indexOf("napisał")+7, authorDate.indexOf("dnia")-1));
				}
				
				Date sourceDate = sourceFormat.parse(authorDate.substring(authorDate.indexOf("dnia")+5, authorDate.indexOf("r.")-1));
				date.add(databaseFormat.format(sourceDate));
			}
			
			if(reviews.get(i).child(1).child(0).hasClass("product-review-origin")){
				
				summary.add(reviews.get(i).child(1).child(1).text());
				
				if(reviews.get(i).child(1).child(2).children().isEmpty()){
					pros.add(null);
					cons.add(null);
				}
				
				else if("pros".equals(reviews.get(i).child(1).child(2).child(0).className()) && !reviews.get(i).child(1).child(2).child(0).siblingElements().hasClass("cons")){
					for(int j=0;j<reviews.get(i).child(1).child(2).child(1).child(0).children().size();j++){
						prosElements += reviews.get(i).child(1).child(2).child(1).child(0).child(j).text()+",";
					}
					pros.add(prosElements);
					cons.add(null);
				}
				
				else if("cons".equals(reviews.get(i).child(1).child(2).child(0).className())){
					pros.add(null);
					for(int j=0;j<reviews.get(i).child(1).child(2).child(1).child(0).children().size();j++){
						consElements += reviews.get(i).child(1).child(2).child(1).child(0).child(j).text()+",";
					}
					cons.add(consElements);
				}
				
				else {
					for(int j=0;j<reviews.get(i).child(1).child(2).child(1).child(0).children().size();j++){
						prosElements += reviews.get(i).child(1).child(2).child(1).child(0).child(j).text()+",";
					}
					for(int j=0;j<reviews.get(i).child(1).child(2).child(3).child(0).children().size();j++){
						consElements += reviews.get(i).child(1).child(2).child(3).child(0).child(j).text()+",";
					}
					pros.add(prosElements);
					cons.add(consElements);
				}
				reviewUsefullness.add(reviews.get(i).child(1).child(3).child(0).child(2).child(0).text());
			}
			
			else{
				summary.add(reviews.get(i).child(1).child(0).text());
				
				if(reviews.get(i).child(1).child(1).children().isEmpty()){
					pros.add(null);
					cons.add(null);
				}
				
				else if("pros".equals(reviews.get(i).child(1).child(1).child(0).className()) && !reviews.get(i).child(1).child(1).child(0).siblingElements().hasClass("cons")){
					for(int j=0;j<reviews.get(i).child(1).child(1).child(1).child(0).children().size();j++){
						prosElements += reviews.get(i).child(1).child(1).child(1).child(0).child(j).text()+",";
					}
					pros.add(prosElements);
					cons.add(null);
				}
				
				else if("cons".equals(reviews.get(i).child(1).child(1).child(0).className())){
					pros.add(null);
					for(int j=0;j<reviews.get(i).child(1).child(1).child(1).child(0).children().size();j++){
						consElements += reviews.get(i).child(1).child(1).child(1).child(0).child(j).text()+",";
					}
					cons.add(consElements);
				}
				
				else{
					for(int j=0;j<reviews.get(i).child(1).child(1).child(1).child(0).children().size();j++){
						prosElements += reviews.get(i).child(1).child(1).child(1).child(0).child(j).text()+",";
					}
					for(int j=0;j<reviews.get(i).child(1).child(1).child(3).child(0).children().size();j++){
						consElements += reviews.get(i).child(1).child(1).child(3).child(0).child(j).text()+",";
					}
					pros.add(prosElements);
					cons.add(consElements);
				}
				reviewUsefullness.add(reviews.get(i).child(1).child(2).child(0).child(2).child(0).text());
			}
			
		}
	}
	/**
	 * Extracts amount of pages to parse.
	 */
	public int getAmountOfPages(){
		
		Element pageElement = doc.getElementById("page-counter");
		
		if(null!=pageElement){
			numberOfPages = Integer.parseInt(pageElement.attr("data-pagecount"));
		}
		else{numberOfPages=0;}
		
		return numberOfPages;
		
	}
	/**
	 * Getter for whole parsed webpage. 
	 */
	public Document getDoc() {
		return this.doc;
	}
	/**
	 * Getter for number of pages to parse.
	 */
	public int getNumberOfPages() {
		return this.numberOfPages;
	}
	/**
	 * Getter for pros container.
	 */
	public ArrayList<String> getPros() {
		return this.pros;
	}
	/**
	 * Getter for cons container.
	 */
	public ArrayList<String> getCons() {
		return this.cons;
	}
	/**
	 * Getter for summary container.
	 */
	public ArrayList<String> getSummary() {
		return this.summary;
	}
	/**
	 * Getter for stars container.
	 */
	public ArrayList<String> getStars() {
		return this.stars;
	}
	/**
	 * Getter for Author container.
	 */
	public ArrayList<String> getAuthor() {
		return this.author;
	}
	/**
	 * Getter for date container.
	 */
	public ArrayList<String> getDate() {
		return this.date;
	}
	/**
	 * Getter for advice container.
	 */
	public ArrayList<String> getAdvice() {
		return this.advice;
	}
	/**
	 * Getter for reviewUsefullness container.
	 */
	public ArrayList<String> getReviewUsefullness() {
		return this.reviewUsefullness;
	}
	/**
	 * Getter for product type container.
	 */
	public String getType() {
		return this.type;
	}
	/**
	 * Getter for product brand container.
	 */
	public String getBrand() {
		return this.brand;
	}
	/**
	 * Getter for product model.
	 */
	public String getModel() {
		return this.model;
	}
	/**
	 * Getter for Ceneo.pl webpage.
	 */
	public String getUrl() {
		return this.url;
	}
	
}
