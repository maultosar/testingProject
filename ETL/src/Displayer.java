/**
* Displayer.java
* 
* @author  Sharandin Vitaliy
* @version 1.0
* @since   2014-12-10 
*/
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.ParseException;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.custom.StyledText;



public class Displayer {

	private Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_4;
	private Text txtJdbcmysql;
	private static Text console;
	/**
	 * Parser object.
	 */
	private Parser parser = null;
	/**
	 * DatabaseConnector object.
	 */
	private DatabaseConnector dbc = null;
	
	/**
	 * Launches the application.
	 * @param args
	 */
	public static void main(String[] args) {
			try{
				Displayer window = new Displayer();
				window.open();
			}
			catch(Exception e){
				Displayer.printToConsole(e.getMessage());
			}
	}
	/**
	 * Prints the output to GUI console.
	 * @param text takes String as an input
	 */
	public static void printToConsole(String text){
		console.append(text);
		console.append("\n");
	}
	/**
	 * Opens the main GUI window.
	 */
	public void open() {
		 Display display = Display.getDefault();
	        createContents();
	
	        shell.open();
	        shell.layout();
	        while (!shell.isDisposed()) {
	        	try{
		            if (!display.readAndDispatch()) {
		                display.sleep();
		            }
	        	}catch(Exception ex){
	        		Displayer.printToConsole("Error: "+ex.getMessage());
	        	}
	        }
	        
	}
	/**
	 * Creates contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		shell.setImage(SWTResourceManager.getImage("D:\\Programms install\\SourceCodes\\Java\\ETL\\icon175x175.jpeg"));
		shell.setToolTipText("");
		shell.setSize(1008, 580);
		shell.setText("Product reviews collector");
		shell.setLayout(null);

		text = new Text(shell, SWT.BORDER);
		text.setBounds(10, 74, 433, 26);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(10, 106, 122, 32);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				String url = text.getText();
				
				parser = new Parser();
				
					try {
						parser.connectToWebPage(url);
						parser.extractAllReviews();
						parser.printParsingResults();
					} catch (IOException | ParseException e1) {
						Displayer.printToConsole(e1.getMessage());
					}
					
			}
		});
		btnNewButton.setText("Parse webpage");
		
		Label lblCeneoplProduct = new Label(shell, SWT.NONE);
		lblCeneoplProduct.setBounds(10, 48, 232, 20);
		lblCeneoplProduct.setText("Ceneo.pl product webpage URL:");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(10, 356, 115, 26);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(10, 451, 115, 26);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(10, 306, 160, 44);
		lblNewLabel.setText("Name of product\r\n(pattern: Brand Model):");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(10, 401, 200, 44);
		lblNewLabel_1.setText("Amount of reviews to export\r\n(e.g.: 10)");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(46, 267, 115, 33);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblNewLabel_2.setText("CSV Export");
		
		Label lblCleanUp = new Label(shell, SWT.NONE);
		lblCleanUp.setBounds(260, 267, 189, 32);
		lblCleanUp.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblCleanUp.setText("Database clean up");
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(0, 259, 450, 2);
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setBounds(10, 484, 97, 30);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				String productName = text_1.getText();
				Integer reviewsAmount = Integer.parseInt(text_2.getText());
					
				if(!productName.equals("") && !reviewsAmount.equals(0)){
					dbc.exportToCsv(productName, reviewsAmount);
				}
				
				else{
					Displayer.printToConsole("Check your CSV section input!");
				}
				
			}
		});
		btnNewButton_1.setText("Generate file");
		
		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label_1.setBounds(216, 259, 2, 272);
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setBounds(240, 356, 115, 26);
		
		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.setBounds(184, 216, 259, 32);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
					
					try {
						dbc.insertProductInfo(parser);
						dbc.insertReviews(parser);
					} catch (ParseException | IOException | IllegalArgumentException e2) {
						Displayer.printToConsole(e2.getMessage());
					} catch (NullPointerException e2) {
						Displayer.printToConsole("Nothing retrieved from parser.\n");
					}
			
				
			}
		});
		btnNewButton_2.setText("Transform and load data to database");
		
		Button btnDeleteEntries = new Button(shell, SWT.NONE);
		btnDeleteEntries.setBounds(240, 388, 104, 30);
		btnDeleteEntries.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				String productName = text_4.getText();
				
				if(!productName.equals("")){
					dbc.deleteReviews(productName);
				}
				
				else{
					Displayer.printToConsole("Check your database clean up section input!");
				}
				
			}
		});
		btnDeleteEntries.setText("Delete entries");
		
		console = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		console.setBounds(448, 0, 542, 531);
		console.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		console.setText("Welcome to product reviews collector v1.0 \r\nⒸ Vitaliy Sharandin (QvQ)\r\n\t\t\t\t\t\t\t\t\t\t\t\t    (   )\r\n\t\t\t\t\t\t\t\t\t\t\t\t    \"\t  \"\tCyberOw1 dev team\r\n\r\n\r\n\r\n");
		console.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		
		txtJdbcmysql = new Text(shell, SWT.BORDER);
		txtJdbcmysql.setBounds(10, 184, 433, 26);
		txtJdbcmysql.setText("jdbc:mysql://");
		txtJdbcmysql.setToolTipText("");
		
		Label lblDatabase = new Label(shell, SWT.NONE);
		lblDatabase.setBounds(10, 158, 176, 20);
		lblDatabase.setText("MySQL database address:");
		
		Button btnConnectToDatabase = new Button(shell, SWT.NONE);
		btnConnectToDatabase.setBounds(10, 216, 154, 32);
		btnConnectToDatabase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dbc = new DatabaseConnector();
				
				dbc.connectToDatabase(txtJdbcmysql.getText());
				
			}
		});
		btnConnectToDatabase.setText("Connect to database");
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(240, 306, 160, 44);
		label_2.setText("Name of product\r\n(pattern: Brand Model):");
		
		Label lblExtractionAndDatabase = new Label(shell, SWT.NONE);
		lblExtractionAndDatabase.setBounds(10, 10, 433, 32);
		lblExtractionAndDatabase.setText("Extraction of data and database enrichment");
		lblExtractionAndDatabase.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));

	}
}
