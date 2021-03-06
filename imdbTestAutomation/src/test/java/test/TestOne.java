package test;

import org.junit.Test;
import pages.Page;

public class TestOne extends TestBase {

	Page p1;
	
	@Test
    public void ImdbTest1() 
	{
        p1 = new Page("The Circus"); //Film ismi sağlanmalıdır (The Circus/The Jazz Singer)
        p1.getMovieDetails(); //Film detaylarını alır
        p1.checkMovieDetails(); //Film detaylarını kontrol eder
        p1.checkImages(); //Film görsellerini kontrol eder
    }
	
	@Test
    public void ImdbTest2() 
	{
        p1 = new Page("The Jazz Singer"); //Film ismi sağlanmalıdır (The Circus/The Jazz Singer)
        p1.getMovieDetails(); //Film detaylarını alır
        p1.checkMovieDetails(); //Film detaylarını kontrol eder
        p1.checkImages(); //Film görsellerini kontrol eder
    }
}