package modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kev
 */
public class Base64 {
    private final char[] DICCIONARIO = new char[] {
                           '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
                           'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
                           'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
                           'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 
                           'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
                           'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
                           'y', 'z', '+', '/' };
    public String ObtenerBase64(int numero) {
        int cociente = 1;
        int resto = 0;
        String palabra = "";
        while(cociente > 0) {
            cociente = numero / 64;
            resto = numero % 64;
            palabra = DICCIONARIO[resto] + palabra;
            numero = cociente;
        } 
        return palabra;
    }
    
}