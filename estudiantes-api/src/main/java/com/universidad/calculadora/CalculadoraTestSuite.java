package com.universidad.calculadora;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("🧪 Suite Completa: Calculadora")
class CalculadoraTestSuite {

    private Calculadora calc;

    // Se ejecuta antes de CADA prueba para asegurar un estado limpio
    @BeforeEach
    void init() {
        calc = new Calculadora();
    }

    // PASO 3: Pruebas de valorAbsoluto (cubre positivo, negativo y cero)
    @Nested
    @DisplayName("Tests de valorAbsoluto")
    class ValorAbsTests {
        @Test void positivo() { assertEquals(5, calc.valorAbsoluto(5));  }
        @Test void negativo() { assertEquals(8, calc.valorAbsoluto(-8)); }
        @Test void cero()     { assertEquals(0, calc.valorAbsoluto(0));  }
    }

    // PASO 4: Pruebas de esNumeroPrimo (cubre casos positivos, negativos y bordes críticos como 0 y 1)
    @Nested
    @DisplayName("Tests de esNumeroPrimo")
    class PrimoTests {
        @Test void primoTrue()  { assertTrue(calc.esNumeroPrimo(7));   }
        @Test void primoFalse() { assertFalse(calc.esNumeroPrimo(4));  }
        @Test void borde1()     { assertFalse(calc.esNumeroPrimo(1));  }
        @Test void borde0()     { assertFalse(calc.esNumeroPrimo(0));  }
    }

    // PASOS 5 y 6: Operaciones básicas y División con excepciones
    @Nested
    @DisplayName("Tests de operaciones matemáticas")
    class OpsTests {
        @Test void suma()   { assertEquals(5,  calc.sumar(2,3));      }
        @Test void resta()  { assertEquals(1,  calc.restar(3,2));     }
        @Test void mult()   { assertEquals(12, calc.multiplicar(3,4));}

        // El delta (0.001) es la tolerancia para la precisión de los decimales (double)
        @Test void div()    { assertEquals(5.0, calc.dividir(10,2), 0.001); }

        // Verifica que falla correctamente lanzando la excepción esperada
        @Test void divEx()  {
            assertThrows(ArithmeticException.class, () -> calc.dividir(5, 0));
        }
    }
    @Nested
    @DisplayName("Tests de esPalindromo")
    class PalindromoTests {
        // Casos Positivos
        @Test void testAna()      { assertTrue(calc.esPalindromo("Ana")); }
        @Test void testRacecar()  { assertTrue(calc.esPalindromo("racecar")); }
        @Test void testConEspacios() { assertTrue(calc.esPalindromo("A man a plan a canal Panama")); }

        // Casos Negativos
        @Test void testHello()    { assertFalse(calc.esPalindromo("Hello")); }
        @Test void testJava()     { assertFalse(calc.esPalindromo("Java"));  }

        // Edge Cases (Casos límite)
        @Test void testNull()     { assertFalse(calc.esPalindromo(null));  }
        @Test void testVacio()    { assertFalse(calc.esPalindromo(""));    }
    }
}