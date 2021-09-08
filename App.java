import java.util.Hashtable;
import java.io.*;
import java.util.ArrayList;


public class App {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//FreqTable table = new FreqTable(3);
		
		
		
		String arquivo = "texto.txt";
	
		Hashtable<Character,Double> table = calcula_frequencia(arquivo);
		HuffmanEncoding hf = new HuffmanEncoding(table);
		
		hf.tabela_de_codificaçao();
		Hashtable<Character,String> codificaçao_final = hf.get_codigo();
		System.out.println(codificaçao_final);
		hf.codifica_texto(arquivo,codificaçao_final);
		

	}

	public static Hashtable<Character,Double> calcula_frequencia(String arquivo){

		Hashtable<Character,Double> table = new Hashtable<Character,Double>();
		File texto = new File("texto.txt");
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
