package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.RateDomainModel;
import domain.StudentDomainModel;
import util.HibernateUtil;

public class RateDAL {
	

	public static double getRate(int GivenCreditScore) {
		//Initialize Variables
		int best_rate = -1;
		ArrayList<RateDomainModel> AllRateDomains = new ArrayList<RateDomainModel>();
		ArrayList<Integer> AllRates = new ArrayList<Integer>();
		
		//Get RateDomainModels from Table TBLRATE
		AllRateDomains = getAllRateDomainModels();
		
		//Get All MinCreditScores from AllRateDomains
		for(RateDomainModel RateDomain : AllRateDomains){
			AllRates.add(RateDomain.getMinCreditScore());
		}
		//Sort AllRates from lowest to highest 
		Collections.sort(AllRates);
		
		//Figure out which tier the GivenCredit Score is in
		for(int i = 0; i < AllRates.size(); i++){
			/*If the GivenCreditRate is lower than the lowest MinCreditRate 
			*in the Table, return the initial best_rate of -1  */
			if( i == 0 && GivenCreditScore < AllRateDomains.get(i).getMinCreditScore() ){
				return best_rate;
			}
			
			if( i == (AllRates.size() - 1) && GivenCreditScore >= AllRateDomains.get(i).getMinCreditScore() ){
				best_rate = AllRates.get(i);
				return best_rate;
			}
			
			//Determine which tier the given credit Score is in
			if(GivenCreditScore < AllRateDomains.get(i).getMinCreditScore() ){
				best_rate = AllRates.get(i - 1);
				return best_rate;
				
			}
		}
		//
		return best_rate;
	}
	
	public static ArrayList<RateDomainModel> getAllRateDomainModels() {
		Session my_session = HibernateUtil.getSessionFactory().openSession();
		Transaction my_transaction = null;		
		ArrayList<RateDomainModel> allRateDomains = new ArrayList<RateDomainModel>();
		
		try {
			my_transaction = my_session.beginTransaction();	
			
			List DB_Rates = my_session.createQuery("SELECT * FROM TBLRATE").list();
			for (Iterator iterator = DB_Rates.iterator(); iterator.hasNext();) {
				RateDomainModel DBrate = (RateDomainModel) iterator.next();
				allRateDomains.add(DBrate);

			}
			
			my_transaction.commit();
		} catch (HibernateException e) {
			if (my_transaction != null)
				my_transaction.rollback();
			e.printStackTrace();
		} finally {
			my_session.close();
		}
		return allRateDomains;
	}

}