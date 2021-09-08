import java.util.Hashtable;
import java.io.*;
import java.util.ArrayList;


public class App {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//FreqTable table = new FreqTable(3);
		
		Hashtable<Character,Double> table = new Hashtable<Character,Double>();
		
		File texto = new File("texto.txt");
		String linha;
		int contador = 0;
		Character aux;
		double result;
		String codifica = "";
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

		
		HuffmanEncoding hf = new HuffmanEncoding(table);
		
		hf.printEncoding();
		Hashtable<Character,String> codificaçao_final = hf.cria_codificaçao();
		System.out.println(codificaçao_final);
		

		try {
			BufferedReader br = new BufferedReader(new FileReader(texto));
			while ((linha = br.readLine()) != null){
				char[] letras = linha.toCharArray();
				for (int i = 0; i < linha.length(); i++){
					codifica = codifica + codificaçao_final.get(letras[i]);
				}

			}
				br.close();
		}
		catch(IOException e) {
				e.printStackTrace();

		}
		System.out.println(codifica);


	}
	
}
