package modelo;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Kev
 */
public class Generator {

    private final String llaveDeDosificacion;
    private final String nroAuth, nroFact, nitCI, fTrans, mTrans;
    private final Verhoeff verhoeff;
    private final AllegedRC4 aRC4;
    private final Base64 base64;
    public Generator(String nroAutorizacion, String nroFactura, String nitCICli,
            String fechaTrans, String montoTrans, String llaveDosificacion) {
        nroAuth = nroAutorizacion;
        nroFact = nroFactura;
        nitCI = nitCICli;
        fTrans = fechaTrans;
        mTrans = String.valueOf(Math.round(Float.parseFloat(montoTrans)));
        verhoeff = new Verhoeff();
        aRC4 = new AllegedRC4();
        llaveDeDosificacion = llaveDosificacion;
        base64 = new Base64();
    }

    public String generar() {
        String sNroFact = nroFact + verhoeff.generateVerhoeffTimes(2, nroFact);
        String sNitCi = nitCI + verhoeff.generateVerhoeffTimes(2, nitCI);
        String sFTrans = fTrans + verhoeff.generateVerhoeffTimes(2, fTrans);
        String sMTrans = mTrans + verhoeff.generateVerhoeffTimes(2, mTrans);

        List<String> datosEncriptParcial = enlistarVariablesEntrada(sNroFact, sNitCi, sFTrans, sMTrans);

        long sumatoria = Long.parseLong(sNroFact)
                + Long.parseLong(sNitCi)
                + Long.parseLong(sFTrans)
                + Long.parseLong(sMTrans);

        String digitosVerh = verhoeff.generateVerhoeffTimes(5,
                String.valueOf(sumatoria));
        
        String llave = llaveDeDosificacion + digitosVerh;
        
        List<String> cadenasAdyacentes = sacarCadenasAdyacentes(digitosVerh);
        
        String codigo = String.join("", concatenarValores(datosEncriptParcial, cadenasAdyacentes));
        
        String valorSemiencriptado = aRC4.encriptarRC4(codigo, llave);
        
        List<Integer> sumASCII = sumatoriasParciales(valorSemiencriptado);
        
        int sumatoriaTotal =  sumASCII.stream().collect(Collectors.summingInt(i->i));
        
        String base64Calculada = calcularBase64(sumASCII, sumatoriaTotal, digitosVerh);
                  
        return convAHEX(aRC4.encriptarRC4(base64Calculada, llave));
    }

    public List<String> sacarCadenasAdyacentes(String digitos) {
        List<String> result = new ArrayList<>();
        String llave = llaveDeDosificacion;
        for (int i = 0; i < digitos.length(); i++) {
            int currentDigit = Character.getNumericValue(digitos.charAt(i)) + 1;
            StringBuilder cadena = new StringBuilder();
            for (int j = 0; j < currentDigit; j++) {
                cadena.append(llave.charAt(j));
            }
            llave = llave.substring(currentDigit);
            result.add(cadena.toString());
        }
        return result;
    }

    public List<String> concatenarValores(List<String> datos1, List<String> datos2) {
        List<String> result = new ArrayList<>();
        if (datos1.size() == datos2.size()) {
            for (int i = 0; i < datos1.size(); i++) {
                StringBuilder builder = new StringBuilder();
                builder.append(datos1.get(i));
                builder.append(datos2.get(i));
                result.add(builder.toString());
            }
        }
        return result;
    }
    
    public List<Integer> sumatoriasParciales(String valorSemiencriptado){
        List<Integer> resultado = new ArrayList<>();
        if (!valorSemiencriptado.isEmpty()) {
            for (int i = 0 ; i < 5 ; i++) {
                int sumatoria = 0;
                for (int j = i ; j < valorSemiencriptado.length() ; j= j + 5) {
                    int caracter = (int) valorSemiencriptado.charAt(j);
                    sumatoria += caracter;
                }
                resultado.add(sumatoria);
            }
        }
        return resultado;
    }
    
    public String calcularBase64(List<Integer> sumASCII, int sumatoriaTotal, String digitosVerh) {
        int sumParaBase64 = 0;
        if (sumASCII.size() == digitosVerh.length()) {
            for (int i = 0 ; i < digitosVerh.length() ; i++) {
                int digito = Character.getNumericValue(digitosVerh.charAt(i)) + 1;
                sumParaBase64 += (sumASCII.get(i) * sumatoriaTotal)/digito; 
            }
        }
        return base64.ObtenerBase64(sumParaBase64);
    }
    
    public String convAHEX(String encriptarRC4) {
        StringBuilder builder = new StringBuilder();
        if (encriptarRC4.length() % 2 ==0 ) {
            for(int i = 0 ; i < encriptarRC4.length() - 2 ; i = i + 2) {
                builder.append(encriptarRC4.charAt(i));
                builder.append(encriptarRC4.charAt(i+1));
                builder.append('-');
            }
            builder.append(encriptarRC4.charAt(encriptarRC4.length() - 2));
            builder.append(encriptarRC4.charAt(encriptarRC4.length() - 1));
        }
        return builder.toString();
    }
    
    private List<String> enlistarVariablesEntrada(String sNroFact, String sNitCi, String sFTrans, String sMTrans) {
        List<String> datosEncriptParcial = new ArrayList<>();
        datosEncriptParcial.add(nroAuth);
        datosEncriptParcial.add(sNroFact);
        datosEncriptParcial.add(sNitCi);
        datosEncriptParcial.add(sFTrans);
        datosEncriptParcial.add(sMTrans);
        return datosEncriptParcial;
    }

}
