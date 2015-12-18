package ch.makery.address.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.UUID;

import base.RateDAL;
import ch.makery.address.MainApp;
import ch.makery.address.model.Rate;


public class MortgageController {
	
	//Create observable list to hold values for ComboBox
	ObservableList<String> ComboTermList = (ObservableList<String>) FXCollections.observableArrayList("15", "30");       
	
	@FXML 
	private TextField txtMonthlyIncome;
	
	@FXML 
	private TextField txtMonthlyExpenses;
	
	@FXML 
	private TextField txtCreditScore;
	
	@FXML 
	private TextField txtHouseCost;
	
	@FXML
	private ComboBox ComboTerm;
	
	@FXML
	private Button btnCalculateMortgage;
	
	@FXML 
	private Text txtOutput;
	

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MortgageController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	//Set the choices for the ComboBox
    	ComboTerm.setItems(ComboTermList);
    	//Hide the Output TextBox 
    	txtOutput.setVisible(false);
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void handleBtnCalculate(){
    	//Convert txtCreditScore to an Integer
    	Integer IntCreditScore = Integer.parseInt(txtCreditScore.getText());
    	//Convert HouseCost to a Double
    	Double NumHouseCost = Double.valueOf(txtHouseCost.getText());
    	//Convert ComboTerm to a Double
    	Integer term_in_years = Integer.parseInt((String) ComboTerm.getValue());
    	
    	//Get the buyer's interest rate from the database
    	Double my_rate = RateDAL.getRate(IntCreditScore);
    	
    	//Calculate the buyer's monthly payment
    	Double monthly_payment = Rate.getPayment(NumHouseCost,
    			0.0, my_rate, term_in_years);
    	

    	if(monthly_payment > (Double.valueOf(txtMonthlyIncome.getText()) )*(.36)){
    		txtOutput.setText("House Cost too high");
    		txtOutput.setVisible(true);
    	}
    	else if(monthly_payment > (Double.valueOf(txtMonthlyIncome.getText())
    			+ Double.valueOf(txtMonthlyExpenses.toString())*(.18)) ){
    		txtOutput.setText("House Cost too high");
    		txtOutput.setVisible(true);
    	}
    	else{
    		txtOutput.setText("Monthly payments =  " + monthly_payment.toString());
    		txtOutput.setVisible(true);
    	}
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}