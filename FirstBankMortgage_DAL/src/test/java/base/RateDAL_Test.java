package base;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.RateDomainModel;

public class RateDAL_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getAllRateDomainModels_Test() {
		
		ArrayList<RateDomainModel> DB_Rate_Domains = RateDAL.getAllRateDomainModels();
		assertNotNull(DB_Rate_Domains);
	}
	
	@Test
	public void getRate_Test_1(){
		double my_rate = RateDAL.getRate(805);
		assertTrue(my_rate == 3.5);
	}
	
	@Test
	public void getRate_Test_2(){
		double my_rate = RateDAL.getRate(775);
		assertTrue(my_rate == 3.75);
	}
	
	@Test
	public void getRate_Test_3(){
		double my_rate = RateDAL.getRate(746);
		assertTrue(my_rate == 4.0);
	}
	
	@Test
	public void getRate_Test_4(){
		double my_rate = RateDAL.getRate(699);
		assertTrue(my_rate == 4.5);
	}
	
	@Test
	public void getRate_Test_5(){
		double my_rate = RateDAL.getRate(633);
		assertTrue(my_rate == 5);
	} 
	
	@Test
	public void getRate_Test_6(){
		double my_rate = RateDAL.getRate(599);
		assertTrue(my_rate == -1);
	}

}
