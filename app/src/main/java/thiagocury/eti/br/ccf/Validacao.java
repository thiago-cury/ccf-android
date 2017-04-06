package thiagocury.eti.br.ccf;

/**
 * Created by mauriciormp on 01/03/2016.
 */
public class Validacao {

    public static String converterMaiuscula(String c){
        return c.toUpperCase();
    }//fecha converterMaiuscula

    public static boolean verificarEstado(String estado){
        return estado.matches("^[A-Z]{2}$");
    }//fecha verificarEstado

    public static boolean verificarNome(String nome){
        return nome.matches("^[a-zA-ZáÁéÉíÍóÓúÚçÇãÃõÕ ]{2,80}$");
    }//fecha verificarNome

    public static boolean verificarCpf(String cpf){
        return cpf.matches("^[0-9]{5,12}$");
    }//fecha verificarCpf

    public static boolean verificarNumero(String numero){
        return numero.matches("^[-().:;,a-zA-AáÁéÉíÍóÓúÚçÇãÃõÕ0-9/º ]{1,50}$");
    }//fecha verificarNumero

    public static boolean verificarEmail(String email){
        return email.matches("^([0-9a-zA-Z]+([_.-]?[0-9a-zA-Z]+)*@[0-9a-zA-Z]+[0-9,a-z,A-Z,.,-]*(.){1}[a-zA-Z]{2,4})+$");
    }//fecha verificarEmail

    public static boolean verificarTelefone(String tel){
        return tel.matches("^[1-9]{2}[0-9]{6,14}$");
    }//fecha veriricarTelefone
}//fecha classe Validacao
