package com.integradora;
/* -----

 	Integrantes
 
 	Alison Carnetti, 
	Igor Couto, 
	Matheus Battiston, 
	Gustavo Geyer, 
	Michael Leite.

*/

import java.util.Hashtable;
import java.io.*;
import java.util.ArrayList;


public class App {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//FreqTable table = new FreqTable(3);
		
		
		
		String arquivo = "texto.txt";
	
		File texto = new File("texto.txt");

		Hashtable<Character,Double> table = calcula_frequencia(texto);
		HuffmanEncoding hf = new HuffmanEncoding(table);
		
		hf.tabela_de_codificacao();
		Hashtable<Character,String> codificacao_final = hf.get_codigo();
		System.out.println(codificacao_final);
		String dec = hf.codifica_texto(arquivo,codificacao_final);
		
		String palavradecode = hf.decode(codificacao_final, dec);
		System.out.println(palavradecode);

	}

	public static Hashtable<Character,Double> calcula_frequencia(File texto){

		Hashtable<Character,Double> table = new Hashtable<Character,Double>();
		String linha;
		int contador = 0;
		Character aux;
		double result;

		ArrayList<Character> contado = new ArrayList<Character>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(texto));
			while ((linha = br.readLine()) != null){
				char[] letras = linha.toCharArray();
				for (int i = 0; i < linha.length(); i++){
					for (int j = i; j < linha.length(); j++){
						if (letras[i] == letras[j]){
							if (contado.contains(letras[i])){
								contador = contador + 1;
							}else {
								contado.add(letras[i]);
								contador = contador + 1;
							}
						}
					}

					if (table.containsKey(letras[i])== false){
						aux = letras[i];
						result = contador;
						table.put(aux,result);
					}
					contador = 0;
				}

				}
				br.close();
		}
		catch(IOException e) {
				e.printStackTrace();
		
		}
		return table;
	
	}	

}