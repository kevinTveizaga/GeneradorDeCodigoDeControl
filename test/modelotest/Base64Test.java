package modelotest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import modelo.Base64;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kev
 */
public class Base64Test {
    
    private Base64 instance;
    
    @Before
    public void setUp() {
        instance = new Base64();
    }
      
    /**
     * Test of encode method, of class Base64.
     */
    @Test
    public void testEncode() {
        int numero = 19058106;
        String expResult = "18isw";
        String result = instance.ObtenerBase64(numero);
        assertEquals(expResult, result);
    }
    
}
