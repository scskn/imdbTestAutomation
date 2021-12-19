package test;

import org.junit.Test;
import pages.Page;

public class TestOne extends TestBase {

	Page p1;
	
	@Test
    public void ImdbTest1() 
	{
        p1 = new Page("The Circus"); //Film ismi saðlanmalýdýr (The Circus/The Jazz Singer)
        p1.getMovieDetails(); //Film detaylarýný alýr
        p1.checkMovieDetails(); //Film detaylarýný kontrol eder
        p1.checkImages(); //Film görsellerini kontrol eder
    }
	
	@Test
    public void ImdbTest2() 
	{
        p1 = new Page("The Jazz Singer"); //Film ismi saðlanmalýdýr (The Circus/The Jazz Singer)
        p1.getMovieDetails(); //Film detaylarýný alýr
        p1.checkMovieDetails(); //Film detaylarýný kontrol eder
        p1.checkImages(); //Film görsellerini kontrol eder
    }
}