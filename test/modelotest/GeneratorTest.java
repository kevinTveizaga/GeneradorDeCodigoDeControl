package modelotest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import modelo.Generator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
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
public class GeneratorTest {

    private Generator generador;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        generador = new Generator("29040011007", "1503", "4189179011", 
                                  "20070702", "2500", 
                                  "9rCB7Sv4X29d)5k7N%3ab89p-3(5[A");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of generar method, of class Generator.
     */
    @Test
    public void testGenerar() {
        String expResult = "6A-DC-53-05-14";
        String result = generador.generar();
        assertEquals(expResult, result);
    }

    @Test
    public void testGenerarEj1() {
        generador = new Generator("79040011859" , "152", "1026469026", 
                                  "20070728","135",
                                  "A3Fs4s$)2cvD(eY667A5C4A2rsdf53kw9654E2B23s24df35F5");
        String expResult = "FB-A6-E4-78";
        assertEquals(expResult, generador.generar());
    }
    
    @Test
    public void testGenerarEj5() {
        generador = new Generator("30040010595" , "10015", "953387014", 
                                  "20070825","5725.90",
                                  "33E265B43C4435sdTuyBVssD355FC4A6F46sdQWasdA)d56666fDsmp9846636B3");
        String expResult = "A8-6B-FD-82-16";
        assertEquals(expResult, generador.generar());
    }
    
    @Test
    public void testSacarCadenasAdyacentes() {
        List<String> expected = new ArrayList<>(Arrays.asList("9rCB7Sv4",
                "X2", "9d)5k7N", "%3a", "b8"));
        List<String> result = generador.sacarCadenasAdyacentes("71621");
        assertThat(result, is(expected));
    }

    @Test
    public void testConcatenarValores() {
        List<String> expected = new ArrayList<>(
                Arrays.asList("290400110079rCB7Sv4",
                        "150312X2",
                        "4189179011589d)5k7N",
                        "2007070201%3a",
                        "250031b8"));
        List<String> datos = new ArrayList<>((Arrays.asList("29040011007",
                "150312",
                "418917901158",
                "2007070201",
                "250031")));
        List<String> result = generador.concatenarValores(datos,
                generador.sacarCadenasAdyacentes("71621"));
        assertThat(result, is(expected));
    }
    @Test
    public void testSumatoriasParciales() {
        List<Integer> expected = new ArrayList<>(Arrays.asList(1548,
                                                 1537, 1540, 1565, 1530));
        String valorSemiencriptado = "69DD0A42536C9900C4AE6484726C122ABDBF95D80A4BA403FB7834B3EC2A88595E2149A3D965923BA4547B42B9528AAE7B8CFB9996BA2B58516913057C9D791B6B748A";
        
        assertThat(generador.sumatoriasParciales(valorSemiencriptado), is(expected));
    }

    @Test
    public void testCalcularBase64() {
        List<Integer> sumasASCII = new ArrayList<>(Arrays.asList(1548,
                                                 1537, 1540, 1565, 1530));
        int sumatoriaTotal = 7720;
        String digitosVerhoeff = "71621";
        String expected = "18isw";
        assertEquals(expected, generador.calcularBase64(sumasASCII, sumatoriaTotal, digitosVerhoeff));
    }
            
    @Test
    public void testConvAHEX() {
        String expected = "6A-DC-53-05-14";
        assertEquals(expected, generador.convAHEX("6ADC530514"));
    }
}
