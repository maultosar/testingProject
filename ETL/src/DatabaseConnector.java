/**
* DatabaseConnector.java
* 
* @author  Sharandin Vitaliy
* @version 1.0
* @since   2014-12-10 
*/
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.io.FileWriter;

import org.eclipse.swt.widgets.Text;


public class DatabaseConnector {
	
	/**
	 * Name of JDBC driver.
	 */
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	/**
	 * Link to database.
	 */
	private static String DB_URL = null;
	/**
	 * Connection object, which is necessary for database connection.
	 */
	private Connection connection;
	/**
	 * User login data.
	 */
	private final String user = "root";
	/**
	 * User password data.
	 */
	private final String password = "boogey";
	
	/**
	 * Connects to MySQL database.
	 * @param DB_URL Link to database.
	 */
	public void connectToDatabase(String DB_URL){
		
		this.DB_URL = DB_URL;
		
		try {
			 
			Class.forName(JDBC_DRIVER);
 
		} catch (ClassNotFoundException e) {
 
			Displayer.printToConsole("Where is your MySQL JDBC Driver? Include in your library path!");
 
		}
 
		Displayer.printToConsole("MySQL JDBC Driver Registered!");
		
		connection = null;
 
		try {
 
			connection = DriverManager.getConnection(DB_URL, user,password);
 
		} catch (SQLException e) {
 
			Displayer.printToConsole("Connection Failed! Check correctness of MySQL database address.");
 
		}
 
		if (connection != null && !DB_URL.equals("jdbc:mysql://")) {
			Displayer.printToConsole("You made it, take control of your database now!\n");
		} else {
			Displayer.printToConsole("Failed to make connection!\n");
		}
	}
	/**
	 * Inserts product information into "products" table in database.
	 * @param parser The parser object which stores parsed information about reviews.
	 */
	public void insertProductInfo(Parser parser) throws IOException {
		
		PreparedStatement productInsert = null;
		Statement productCheck = null;
		
		String productInsertSql = "INSERT INTO products ( brand, model, type ) VALUES (?,?,?)";
		String productCheckSql = "SELECT brand, model, type FROM products";
		
		try {
			
			Displayer.printToConsole("Preparing to insert product into Database...");
			
			boolean duplicateEntry = false;
			
			productInsert = connection.prepareStatement(productInsertSql);
			productCheck = connection.createStatement();
	      
			ResultSet productCheckResults = productCheck.executeQuery(productCheckSql);
			
			while(productCheckResults.next()){
				
				String brand = productCheckResults.getString(1);
				String model = productCheckResults.getString(2);
				String type = productCheckResults.getString(3);
				
				if(brand.equals(parser.getBrand()) && model.equals(parser.getModel()) && type.equals(parser.getType())){
					Displayer.printToConsole("Found duplicate product in database!");
					duplicateEntry = true;
				}
				
			}
			
			if(duplicateEntry==false){
			
			productInsert.setString(1, parser.getBrand());
			productInsert.setString(2, parser.getModel());
			productInsert.setString(3, parser.getType());
		
			productInsert.executeUpdate();
			
			Displayer.printToConsole("Product inserted into database!\n");
			
			}
			
			else{Displayer.printToConsole("Duplicate product! Not inserted.\n");}
		    	
		} catch (SQLException e) {
			Displayer.printToConsole("Something went wrong with insertion!\n");
		}finally {
			try {
				productInsert.close();
				productCheck.close();
			} catch (SQLException e) {
				Displayer.printToConsole(e.getMessage());
			}
		}
		
	}
	/**
	 * Inserts reviews information into "reviews" table in database.
	 * @param parser The parser object which stores parsed information about reviews.
	 */
	public void insertReviews(Parser parser) throws IOException, ParseException{
		
		PreparedStatement reviewsInsert = null;
		PreparedStatement productIdSelect = null;
		Statement reviewsCheck = null;
		boolean duplicateEntry = false;
		int duplicateCounter = 0;
		
		Displayer.printToConsole("Inserting reviews into database!");
		
		String reviewsInsertSql = "INSERT INTO reviews ( pros, cons, summary, stars, author, date, advice, reviewUsefullness, product_id ) VALUES (?,?,?,?,?,?,?,?,?)";
		String productIdSelectSql = "SELECT product_id FROM products WHERE brand=? AND model=? AND type=?";
		String reviewsCheckSql = "SELECT pros, cons, summary, stars, author, date, advice, reviewUsefullness, product_id FROM reviews";
		
		try {
			
			Displayer.printToConsole("Preparing to insert reviews...\n");
			
			reviewsCheck = connection.createStatement();
		    ResultSet reviewsCheckResults = reviewsCheck.executeQuery(reviewsCheckSql);
			
		    String productIdChecking = null;
		    productIdSelect = connection.prepareStatement(productIdSelectSql);
			productIdSelect.setString(1, parser.getBrand());
			productIdSelect.setString(2, parser.getModel());
			productIdSelect.setString(3, parser.getType());
			ResultSet productIdResults = productIdSelect.executeQuery();
			
			if(productIdResults.first()){
				productIdChecking = productIdResults.getString(1).toString();
			}
			
			reviewsInsert = connection.prepareStatement(reviewsInsertSql);
			
			for(int i=0;i<parser.getPros().size();i++){
				
				while(reviewsCheckResults.next()){
				
					String pros = reviewsCheckResults.getString(1);
					String cons = reviewsCheckResults.getString(2);
					String summary = reviewsCheckResults.getString(3);
					String stars = reviewsCheckResults.getString(4);
					String author = reviewsCheckResults.getString(5);
					String date = reviewsCheckResults.getString(6);
					String advice = reviewsCheckResults.getString(7);
					String reviewUsefullness = reviewsCheckResults.getString(8);
					String productId = reviewsCheckResults.getString(9); 
							
					if(productId.equals(productIdChecking) &&
						(pros!=null?pros.equals(parser.getPros().get(i)):pros==parser.getPros().get(i)) && 
						(cons!=null?cons.equals(parser.getCons().get(i)):cons==parser.getCons().get(i)) && 
						(summary!=null?summary.equals(parser.getSummary().get(i)):summary==parser.getSummary().get(i)) &&
						(stars!=null?stars.equals(parser.getStars().get(i)):stars==parser.getStars().get(i)) &&
						(author!=null?author.equals(parser.getAuthor().get(i)):author==parser.getAuthor().get(i)) &&
						(date!=null?date.equals(parser.getDate().get(i)):date==parser.getDate().get(i)) &&
						(advice!=null?advice.equals(parser.getAdvice().get(i)):advice==parser.getAdvice().get(i)) &&
						(reviewUsefullness!=null?reviewUsefullness.equals(parser.getReviewUsefullness().get(i)):reviewUsefullness==parser.getReviewUsefullness().get(i))){
						
						Displayer.printToConsole("Found duplicate review in database!");
						duplicateCounter++;
						duplicateEntry = true;
						break;
					}
				
				}
				
				if(duplicateEntry==false){
					reviewsInsert.setString(1, parser.getPros().get(i));
			        reviewsInsert.setString(2, parser.getCons().get(i));
			        reviewsInsert.setString(3, parser.getSummary().get(i));
			        reviewsInsert.setString(4, parser.getStars().get(i));
			        reviewsInsert.setString(5, parser.getAuthor().get(i));
			        reviewsInsert.setString(6, parser.getDate().get(i));
			        reviewsInsert.setString(7, parser.getAdvice().get(i));
			        reviewsInsert.setString(8, parser.getReviewUsefullness().get(i));
			        reviewsInsert.setString(9, productIdChecking);
			        
				    reviewsInsert.executeUpdate();
				    reviewsInsert = connection.prepareStatement(reviewsInsertSql);
				    reviewsCheckResults = reviewsCheck.executeQuery(reviewsCheckSql);
				}
				
				else{
					Displayer.printToConsole("Duplicate review! Not inserted.\n");
					duplicateEntry = false;
					reviewsCheckResults = reviewsCheck.executeQuery(reviewsCheckSql);
				}
				        
			}
			
			Displayer.printToConsole("Overall amount of duplicates is "+duplicateCounter+"\n");
		    Displayer.printToConsole("Reviews are inserted into database!\n");
		    
		} catch (SQLException e) {
			Displayer.printToConsole("Something went wrong with insertion! "+e.getMessage());
		}finally {
			try {
				reviewsInsert.close();
			} catch (SQLException e) {
				Displayer.printToConsole(e.getMessage());;
			}
		}
	}
	/**
	 * Deletes reviews information from "reviews" table in database.
	 * @param productName Product name, which is desired to be deleted.
	 */
	public void deleteReviews(String productName){
		
		PreparedStatement productDelete = null;
		PreparedStatement productIdSelect = null;
		
		String productId = null;
		String productDeleteSql = "DELETE FROM products WHERE brand=? AND model=?";
		
		String[] brandModel = productName.split(" ");
		
		if (brandModel.length!=2) {
			
			Displayer.printToConsole("Check product name you inserted!");
			
		}
		
		else{
			
			try {
				
				productDelete = connection.prepareStatement(productDeleteSql);
				productDelete.setString(1, brandModel[0]);
				productDelete.setString(2, brandModel[1]);
				int deletionResult = productDelete.executeUpdate();
				
				if(deletionResult>0){
					Displayer.printToConsole("Reviews for "+productName+" deleted!");
				}
				
				else{
					Displayer.printToConsole("Didn't delete reviews!");
				}
				
			} catch (SQLException e) {
				Displayer.printToConsole("Something went wrong with deletion!");
			}
			
		}
		
	}
	/**
	 * Exports product and reviews information from "reviews" table to a CSV file.
	 * @param productName Product name, which is desired to be deleted.
	 * @param reviewsAmount Amount of reviews to be exported.
	 */
	public void exportToCsv(String productName, int reviewsAmount){
		
		PreparedStatement productSelect = null;
		PreparedStatement reviewsSelect = null;
		PreparedStatement productIdSelect = null;
		
		String productId = null;
		String productIdSelectSql = "SELECT product_id FROM products WHERE brand=? AND model=?";
		String productSelectSql = "SELECT brand, model, type FROM products WHERE product_id=?";
		String reviewsSelectSql = "SELECT pros, cons, summary, stars, author, date, advice, reviewUsefullness FROM reviews WHERE product_id=?";
		
		String[] brandModel = productName.split(" ");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		String timestamp = sdf.format(Calendar.getInstance().getTime());
		
		if (brandModel.length!=2) {
			
			Displayer.printToConsole("Check product name you inserted!");
			
		}
		
		else{
			
			try {
				
				productIdSelect = connection.prepareStatement(productIdSelectSql);
				productIdSelect.setString(1, brandModel[0]);
				productIdSelect.setString(2, brandModel[1]);
				ResultSet productIdResults = productIdSelect.executeQuery();
				
				if(productIdResults.first()){
					productId = productIdResults.getString(1).toString();
				}
				
				productSelect = connection.prepareStatement(productSelectSql);
				productSelect.setString(1, productId);
				ResultSet productSelectResults = productSelect.executeQuery();
				
				reviewsSelect = connection.prepareStatement(reviewsSelectSql);
				reviewsSelect.setString(1, productId);
				ResultSet reviewsSelectResults = reviewsSelect.executeQuery();
				
				File file = new File("logs\\"+productName+timestamp+".txt");
				file.getParentFile().mkdirs();
				FileWriter writer = new FileWriter(file);
				
				while(productSelectResults.next()){
				    writer.append(productSelectResults.getString(1));
				    writer.append(';');
				    writer.append(productSelectResults.getString(2));
				    writer.append(';');
				    writer.append(productSelectResults.getString(3));
				    writer.append('\n');
				}
				
				
				while(reviewsSelectResults.next()){
					writer.append(reviewsSelectResults.getString(1));
					writer.append(';');
					writer.append(reviewsSelectResults.getString(2));
					writer.append(';');
					writer.append(reviewsSelectResults.getString(3));
					writer.append(';');
					writer.append(reviewsSelectResults.getString(4));
					writer.append(';');
					writer.append(reviewsSelectResults.getString(5));
					writer.append(';');
					writer.append(reviewsSelectResults.getString(6));
					writer.append(';');
					writer.append(reviewsSelectResults.getString(7));
					writer.append(';');
					writer.append(reviewsSelectResults.getString(8));
					writer.append('\n');
					if(reviewsSelectResults.getRow()==reviewsAmount){
						break;
					}
				}
	
			    writer.flush();
			    writer.close();
				
			} catch (SQLException | IOException e) {
				Displayer.printToConsole("Check your CSV section input!");
			}
		
		}
	      
	}
	
}