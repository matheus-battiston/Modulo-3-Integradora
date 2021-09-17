package com.integradora;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Hashtable;

/**
 * Unit test for simple App.
 */
class AppTest {
    @Test
    void testApp() {

        File file = new File(getClass().getResource("texto.txt").getFile());        	

        Hashtable<Character,Double> tabela = new Hashtable<Character,Double>();
		
		tabela.put('a', 3.0);
		tabela.put('b', 2.0);
		tabela.put('c', 1.0);

		        
        assertEquals(tabela, App.calcula_frequencia(file));
    }
}