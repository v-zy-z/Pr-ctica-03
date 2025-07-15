/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author LOQ78
 */
public class Utidades {

    public char[][] construir(int tamaño, double porcentajeMuros) {
        int cantidadMuros = (int) Math.round(tamaño * tamaño * porcentajeMuros / 100.0);
        char[][] matriz = new char[tamaño][tamaño];
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                matriz[i][j] = ' ';
            }
        }
        int filaEntrada, columnaEntrada, filaSalida, columnaSalida;
        do {
            filaEntrada = (int) (Math.random() * tamaño);
            columnaEntrada = (int) (Math.random() * tamaño);
            filaSalida = (int) (Math.random() * tamaño);
            columnaSalida = (int) (Math.random() * tamaño);
        } while ((filaEntrada == filaSalida) && (columnaEntrada == columnaSalida));
        matriz[filaEntrada][columnaEntrada] = 'E';
        matriz[filaSalida][columnaSalida] = 'S';
        int murosColocados = 0;
        while (murosColocados < cantidadMuros) {
            int fila = (int) (Math.random() * tamaño);
            int columna = (int) (Math.random() * tamaño);
            if (matriz[fila][columna] == ' ') {
                matriz[fila][columna] = '#';
                murosColocados++;
            }
        }
        return matriz;
    }

    public String[][] crearMatrizAleatoria(int filas, int columnas) {
        String[][] matriz = new String[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = String.format("%.0f", (Math.random() * (filas * columnas)));
            }
        }
        return matriz;
    }

    public String[][] eliminarNumero(String numero, String[][] matriz) {
        for (String[] fila : matriz) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (numero.equals(fila[j])) {
                    fila[j] = " - ";
                }
            }
        }
        return matriz;
    }

    public String[][] eliminarPrimos(String[][] matriz) {
        for (String[] fila : matriz) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (!fila[j].equals(" - ")) {
                    int num = Integer.parseInt(fila[j]);
                    if (esPrimo(num)) {
                        fila[j] = " - ";
                    }
                }
            }
        }
        return matriz;
    }

    private boolean esPrimo(int numero) {
        if (numero <= 1) return false;
        if (numero == 2) return true;
        if (numero % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(numero); i += 2) {
            if (numero % i == 0) return false;
        }
        return true;
    }



    // Máximo número de casas
    private static final int MAX_CASAS = 10;

    // Arreglo para almacenar las casas
    private Casa[] casas = new Casa[MAX_CASAS];
    private int contadorCasas = 0; // Cuántas casas se han agregado

    // Clase interna para representar una Casa
    public static class Casa {
        private double ancho;
        private double largo;
        private double alto;
        private int nroPisos;

        public Casa(double ancho, double largo, double alto, int nroPisos) {
            this.ancho = ancho;
            this.largo = largo;
            this.alto = alto;
            this.nroPisos = nroPisos;
        }

        public double getAncho() { return ancho; }
        public double getLargo() { return largo; }
        public double getAlto() { return alto; }
        public int getNroPisos() { return nroPisos; }

        // Compara si dos casas son homónimas
        public boolean esHomonima(Casa otra) {
            if (otra == null) return false;
            return this.ancho == otra.ancho &&
                   this.largo == otra.largo &&
                   this.alto == otra.alto &&
                   this.nroPisos == otra.nroPisos;
        }
    }

    // Método para agregar una casa al arreglo
    public boolean agregarCasa(double ancho, double largo, double alto, int nroPisos) {
        if (contadorCasas >= MAX_CASAS) {
            return false; // No se puede agregar más
        }
        casas[contadorCasas++] = new Casa(ancho, largo, alto, nroPisos);
        return true;
    }

    // Obtener el arreglo de casas (solo las agregadas)
    public Casa[] obtenerCasas() {
        Casa[] resultado = new Casa[contadorCasas];
        for (int i = 0; i < contadorCasas; i++) {
            resultado[i] = casas[i];
        }
        return resultado;
    }

    // Guardar casas en archivo de texto (simple CSV)
    public void guardarCasas() {
        try (java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter("casas.txt"))) {
            for (int i = 0; i < contadorCasas; i++) {
                Casa c = casas[i];
                pw.println(c.getAncho() + "," + c.getLargo() + "," + c.getAlto() + "," + c.getNroPisos());
            }
        } catch (java.io.IOException e) {
        }
    }

    // Cargar casas desde archivo y llenar el arreglo (sobrescribe lo que haya)
    public void cargarCasas() {
        contadorCasas = 0; // Reiniciar
        java.io.File f = new java.io.File("casas.txt");
        if (!f.exists()) return;
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null && contadorCasas < MAX_CASAS) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    double ancho = Double.parseDouble(partes[0]);
                    double largo = Double.parseDouble(partes[1]);
                    double alto = Double.parseDouble(partes[2]);
                    int pisos = Integer.parseInt(partes[3]);
                    casas[contadorCasas++] = new Casa(ancho, largo, alto, pisos);
                }
            }
        } catch (Exception e) {
        }
    }

    // Buscar pares homónimos, devuelve arreglo de pares [][2] con índices
    public int[][] buscarHomonimas() {
        // Máximo pares posibles: n*(n-1)/2
        int maxPares = contadorCasas * (contadorCasas - 1) / 2;
        int[][] pares = new int[maxPares][2];
        int contadorPares = 0;

        for (int i = 0; i < contadorCasas; i++) {
            for (int j = i + 1; j < contadorCasas; j++) {
                if (casas[i].esHomonima(casas[j])) {
                    pares[contadorPares][0] = i;
                    pares[contadorPares][1] = j;
                    contadorPares++;
                }
            }
        }

        // Copiar solo los pares encontrados a un arreglo exacto
        int[][] resultado = new int[contadorPares][2];
        for (int k = 0; k < contadorPares; k++) {
            resultado[k][0] = pares[k][0];
            resultado[k][1] = pares[k][1];
        }
        return resultado;
    }
}
