package com.apple.mywork;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for the Decryption.
 *
 * @see Decryption
 */
public class DecryptionTest {
    private  static Decryption decryption;

    @BeforeClass
    public  static void initDecryption() {
        decryption = new Decryption();
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
    public void testDecrypt1() {
        String result = decryption.decrypt(5, "toioynnkpheleaigshareconhtomesnlewx");

        assertTrue(result.equals("theresnoplacelikehomeonasnowynightx"));
    }

    @Test
    public void testDecrypt2() {
        String result = decryption.decrypt(3, "ttyohhieneesiaabss");

        assertTrue(result.equals("thisistheeasyoneab"));
    }

    @Test
    public void testDecrypt3() {
        String result = decryption.decrypt(3, "");

        assertTrue(result.equals(""));
    }

    @Test
    public void testDecrypt4() {
        String result = decryption.decrypt(3,null);

        assertTrue(result==null);
    }

    @Test
    public void testDecrypt5() {
        String result = decryption.decrypt(5, "toioynnkpheleaigshareconhtomesnlew");

        assertTrue(result.equals("theresnoplacelikehomeonasnowynightx"));
    }


}
