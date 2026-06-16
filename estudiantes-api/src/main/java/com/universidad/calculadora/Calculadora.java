package com.universidad.calculadora;

public class Calculadora {

    public int sumar(int a, int b)          { return a + b; }
    public int restar(int a, int b)         { return a - b; }
    public int multiplicar(int a, int b)    { return a * b; }

    public double dividir(double a, double b) {
        if (b == 0)
            throw new ArithmeticException("División por cero no permitida");
        return a / b;
    }

    public int valorAbsoluto(int n)         { return Math.abs(n); }

    public boolean esNumeroPrimo(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0) return false;
        return true;
    }
    public boolean esPalindromo(String texto) {
        if (texto == null) return false;
        String limpio = texto.toLowerCase().replaceAll("[^a-z0-9]", "");
        String invertido = new StringBuilder(limpio).reverse().toString();
        return limpio.equals(invertido);
    }
}
