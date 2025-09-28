package br.mack.labirinto.io;

/**
 * Classe utilitária para ler argumentos da linha de comando.
 */
public class CLI {

    /**
     * Retorna o valor de um argumento do tipo String.
     * Ex.: --player=Thiago
     */
    public static String getArg(String[] args, String key, String defaultValue) {
        for (String arg : args) {
            if (arg.startsWith(key + "=")) {
                return arg.substring(key.length() + 1);
            }
        }
        return defaultValue;
    }

    /**
     * Retorna o valor de um argumento do tipo int.
     * Ex.: --seed=3
     */
    public static int getArgInt(String[] args, String key, int defaultValue) {
        for (String arg : args) {
            if (arg.startsWith(key + "=")) {
                try {
                    return Integer.parseInt(arg.substring(key.length() + 1));
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido para " + key + ", usando padrão " + defaultValue);
                    return defaultValue;
                }
            }
        }
        return defaultValue;
    }

    /**
     * Verifica se o argumento existe (flag).
     * Ex.: --help
     */
    public static boolean hasArg(String[] args, String key) {
        for (String arg : args) {
            if (arg.equals(key)) {
                return true;
            }
        }
        return false;
    }
}
