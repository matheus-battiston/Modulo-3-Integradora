package com.integradora;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;



import static org.junit.jupiter.api.Assertions.assertTrue;


import java.io.File;
import java.util.Hashtable;

/**
 * Unit test for simple App.
 */
class AppTest {
    @ParameterizedTest
    @CsvSource ({"3.0,2.0,1.0,texto.txt", "14.0,1.0,1.0,texto2.txt","1.0,1.0,1.0,texto3.txt", "1.0,0.0,0.0,texto4.txt"})
    
    void testApp(Double arg1, Double arg2, Double arg3, String texto) {

        File file = new File(getClass().getResource(texto).getFile());        	

        Hashtable<Character,Double> tabela = new Hashtable<Character,Double>();
		
        if (arg1 > 0.0){
            tabela.put('a', arg1);

        }
        if(arg2 > 0.0){
            tabela.put('b', arg2);
        }
		
        if(arg3>0.0){
            tabela.put('c', arg3);

        }

		        
        assertEquals(tabela, App.calcula_frequencia(file));
    }
}