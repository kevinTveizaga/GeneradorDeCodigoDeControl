package modelotest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import modelo.AllegedRC4;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kev
 */
public class AllegedRC4Test {

    private AllegedRC4 aRC4;

    @Before
    public void setUp() {
        aRC4 = new AllegedRC4();
    }

    /**
     * Test of encriptarRC4 method, of class AllegedRC4.
     */
    @Test
    public void testEncriptarRC4() {
        String message = "290400110079rCB7Sv4150312X24189179011589d)5k7N2007070201%3a250031b8";
        String key = "9rCB7Sv4X29d)5k7N%3ab89p-3(5[A71621";
        String expResult = "69DD0A42536C9900C4AE6484726C122ABDBF95D80A4BA403FB7834B3EC2A88595E2149A3D965923BA4547B42B9528AAE7B8CFB9996BA2B58516913057C9D791B6B748A";
        String result = aRC4.encriptarRC4(message, key);
        assertEquals(expResult, result);
    }

    @Test
    public void testEncriptarEJ5() {
        String message = "3004001059533E265B43C1001540449533870147435sdTuyBV2007082516s572667sD35";
        String key = "33E265B43C4435sdTuyBVssD355FC4A6F46sdQWasdA)d56666fDsmp9846636B391803";
        String expected = "AA0197F70906902CC56017591FB1329BB60ABF2FF0CBF26ADFF39B2CCF481AA83CB53BEBB022586CF8484A0C618389F8A3AC33B4F11476D127F8E09DDA386C6C6106F34AEE4F71";
        assertEquals(expected, aRC4.encriptarRC4(message, key));
    }
    
    @Test
    public void testEncriptarRC42() {
        String message = "18isw";
        String key = "9rCB7Sv4X29d)5k7N%3ab89p-3(5[A71621";
        String expResult = "6ADC530514";
        String result = aRC4.encriptarRC4(message, key);
        assertEquals(expResult, result);
    }
}
