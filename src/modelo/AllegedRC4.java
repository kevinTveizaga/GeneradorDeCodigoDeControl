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
public class AllegedRC4 {

    public String encriptarRC4(String message, String key) {
        int[] state = new int[256];
        int x = 0;
        int y = 0;
        int index1 = 0;
        int index2 = 0;
        int temp;
        StringBuffer cifrado = new StringBuffer();

        for (int i = 0; i <= 255; i++) {
            state[i] = i;
        }

        for (int i = 0; i <= 255; i++) {
            index2 = (key.charAt(i % key.length()) + state[i] + index2) % 256;
            int aux = state[i];
            state[i] = state[index2];
            state[index2] = aux;
            index1 = (index1 + 1) % key.length();
        }

        for (int i = 0; i < message.length(); i++) {
            x = (x + 1) % 256;
            y = (state[x] + y) % 256;
            int aux = state[x];
            state[x] = state[y];
            state[y] = aux;
            temp = state[(state[x] + state[y]) % 256];
            int a = message.charAt(i);
            cifrado.append(Integer.toHexString((temp ^ a)).length() == 2 ? Integer.toHexString((temp ^ a)).toUpperCase() : ("0" + Integer.toHexString((temp ^ a)).toUpperCase()));

        }
        return cifrado.toString();
    }

}
