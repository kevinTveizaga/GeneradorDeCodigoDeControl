package modelotest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import modelo.Verhoeff;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Kev
 */
public class VerhoeffTest {

    Verhoeff verhoeff;

    @Before
    public void setUp() {
        verhoeff = new Verhoeff();
    }

    @Test
    public void testGenerateVerhoeff() {
        String num = "4189179011";
        String expResult = "58";
        String result = verhoeff.generateVerhoeff(num);
        String nResult = verhoeff.generateVerhoeff(num + result);

        assertEquals(expResult, result + nResult);
    }

    @Test
    public void testGenerateVerhoeff2() {
        String num = "1503";
        String expResult = "12";
        String result = verhoeff.generateVerhoeff(num);
        String nResult = result + verhoeff.generateVerhoeff(num + result);

        assertEquals(expResult, nResult);
    }

    @Test
    public void testGenerateVerhoeffTimes() {
        String num = "1503";
        String expResult = "12";
        String result = verhoeff.generateVerhoeffTimes(2, num);

        assertEquals(expResult, result);
    }

    @Test
    public void testGenerateVerhoeffTimes2() {
        String num = "4189179011";
        String expResult = "58";
        String result = verhoeff.generateVerhoeffTimes(2, num);

        assertEquals(expResult, result);
    }

    @Test
    public void testGenerateVerhoeffTimes3() {
        String num = "420925371702";
        String expResult = "71621";
        String result = verhoeff.generateVerhoeffTimes(5, num);

        assertEquals(expResult, result);
    }

    @Test
    public void testGenerateVerhoeffTimes4() {
        String num = "2108446419";
        String expResult = "21885";
        String result = verhoeff.generateVerhoeffTimes(5, num);

        assertEquals(expResult, result);
    }

}
