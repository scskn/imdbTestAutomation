package test;

import org.junit.Test;
import pages.Page;

public class TestOne extends TestBase {

	Page p1;
	
	@Test
    public void ImdbTest1() 
	{
        p1 = new Page("The Circus"); //Film ismi sa�lanmal�d�r (The Circus/The Jazz Singer)
        p1.getMovieDetails(); //Film detaylar�n� al�r
        p1.checkMovieDetails(); //Film detaylar�n� kontrol eder
        p1.checkImages(); //Film g�rsellerini kontrol eder
    }
	
	@Test
    public void ImdbTest2() 
	{
        p1 = new Page("The Jazz Singer"); //Film ismi sa�lanmal�d�r (The Circus/The Jazz Singer)
        p1.getMovieDetails(); //Film detaylar�n� al�r
        p1.checkMovieDetails(); //Film detaylar�n� kontrol eder
        p1.checkImages(); //Film g�rsellerini kontrol eder
    }
}