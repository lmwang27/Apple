package com.apple.mywork;

import org.junit.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TreeMap;

import static org.junit.Assert.*;

/**
 * Test class for the PageVisitors.
 *
 * @see PageVisitors
 */
public class PageVisitorsTest {
    private  static PageVisitors pageVisitors;

    @BeforeClass
    public  static void initPageVisitors() {
        pageVisitors = new PageVisitors("files/logfile");

    }

    @Before
    public void beforeEachTest() {
        System.out.println("This is executed before each Test");
    }

    @After
    public void afterEachTest() {
        System.out.println("This is executed after each Test");
    }

    @Test
    public void testNumOfVistorsPerPage() throws IOException {
        try{
            TreeMap<String,Integer> results = pageVisitors.numOfVistorsPerPage();
            for(String product: results.keySet()){
                System.out.println(product + ":" + results.get(product));
            }
            assertTrue(results.get("p1")==3);
            assertTrue(results.get("p3")==1);
            assertTrue(results.get("p11")==2);
            assertTrue(results.get("p12")==2);
            assertTrue(results.get("p89")==1);

        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Test
    public void testNumOfVisitorBetweenTimes1() {
        try{
            int res = pageVisitors.numOfVisitorBetweenTimes("2019-01-01 00", "2019-01-03 00");
            System.out.println(res);
            assertTrue(res ==5);

        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Test
    public void testNumOfVisitorBetweenTimes2() {
        try{
            int res = pageVisitors.numOfVisitorBetweenTimes("2019-01-01 00", "");
            System.out.println(res);
            assertTrue(res ==0);

        }catch(Exception e){
            System.out.println(e);
        }
    }
    @Test
    public void testNumOfVisitorBetweenTimes3() {
        try{
            int res = pageVisitors.numOfVisitorBetweenTimes("", "");
            System.out.println(res);
            assertTrue(res ==0);

        }catch(Exception e){
            System.out.println(e);
        }
    }


    @Test(expected = FileNotFoundException.class)
    @Ignore
    public void testReadFile() throws IOException {

        pageVisitors.readFile("logfile");
    }




}
