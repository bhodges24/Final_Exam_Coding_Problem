package ch.makery.address.model;

import domain.RateDomainModel;

public class Rate extends RateDomainModel {
	
	public static double getPayment(double present_value,
    		double future_value, double rate, int term_in_years){
		//FinalExam
				//	Normally this kind of method would be in a BLL, but alas...
				
				//	Figure out payment based on:
				//	Interest rate
				//	PV
				//	FV (make FV = 0, unless you want a balloon payment
				//	Compounding = True
				//	Number of Payments (passed in)
		//Convert term to months
		Integer term_in_months = term_in_years*12;
		
		//Calculate monthly payment using Apache FinanceLib function
    	double monthly_payment = org.apache.poi.ss.formula.functions.FinanceLib.pmt(rate, 
    			term_in_months, present_value, future_value, false);
    	
    	return monthly_payment;
		
	}
}
