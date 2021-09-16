import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.io.File;
import java.net.SocketPermission;
import java.io.*;

public class HuffmanEncoding {

	Hashtable<Character,Double> ft;
	BinTree bt;
	Hashtable<Character,String> tabela_final = new Hashtable<Character,String>();


	private class Node{
		
		private char character;
		private double freqVal;
		private Node leftChild;
		private Node rightChild;
		
		public Node(){
			
		}
		
		public Node(char character, double freqVal){
			
			this.character = character;
			this.freqVal = freqVal;
			
		}
		
		public Node(double freqVal, Node leftChild, Node rightChild){
			
			this.character = character;
			this.freqVal = freqVal;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
			
		}

		private void setCharacter(char character){
			this.character = character;

		}

		public void setLeftChild(Node leftChild){
		
			this.leftChild = leftChild;
			
		}
		
		public void setRightChild(Node rightChild){
			
			this.rightChild = rightChild;
			
		}
		
		public void setFreq(Double freq){
			
			freqVal = freq;
			
		}
		
		public Node getLeftChild(){
			
			return leftChild;
			
		}
		
		public Node getRightChild(){
			
			return rightChild;
			
		}
		
		public Double getFreq(){
			
			return freqVal;
			
		}
		
	}		
	
	private class BinTree{
		
		private Node root;
		
		public BinTree(Node root){
			
			this.root = root;
			
		}		
		
		public Node getRoot(){
			
			return root;
			
		}
		
		public Double getFreq(){
			
			return root.freqVal;
			
		}

		private String print(){
			
			return printAux(root);
			
		}
		
		private String printAux(Node n){
			
			if(n == null) return "";
			
			return "(["+n.character+","+n.freqVal+"],"+printAux(n.leftChild)+","+printAux(n.rightChild)+")";
			
		}
		
		@Override
		public String toString() {

			return this.print();
		}
		
		
	}
	
	
	public HuffmanEncoding(Hashtable<Character,Double> frequencyTable){
		
		ft = frequencyTable;
		bt = buildBinTree();
		
	}
	
	private BinTree buildBinTree(){
		
		int n = ft.size();
		
		List<BinTree> Q = new ArrayList<BinTree>();
		
		for(Character c : ft.keySet()){
			
			Node tempNode = new Node(c,ft.get(c));
			BinTree temp = new BinTree(tempNode);
			Q.add(temp);
			
		}
		
		for(int i=1; i < n; i++){
			
			Node z = new Node();
			z.setLeftChild(extractMin(Q).getRoot());
			z.setRightChild(extractMin(Q).getRoot());
			z.setFreq(z.getLeftChild().getFreq() + z.getRightChild().getFreq());
			Q.add(new BinTree(z));
			
		}
		
		return extractMin(Q);
		
	}
	
	private BinTree extractMin(List<BinTree> Q){
				
		BinTree result = Q.get(0);
		int iRemove = 0;
		for(int i=1; i < Q.size(); i++){
			
			if(Q.get(i).getFreq() < result.getFreq()){
				result = Q.get(i);
				iRemove = i;
			}
			
		}
		
		Q.remove(iRemove);
		return result;
		
	}
	

	public void tabela_de_codificacao(){
		
		tabela_de_codificacao_aux(bt.getRoot(),"");
		
	}
	
	private void tabela_de_codificacao_aux(Node n, String code){

		
		if(n.getLeftChild() == null && n.getRightChild() == null){
			tabela_final.put(n.character,code);
			return;
		}
		
		tabela_de_codificacao_aux(n.getLeftChild(), code+"0");
		tabela_de_codificacao_aux(n.getRightChild(), code+"1");
	}
	
	public Hashtable<Character,String> get_codigo(){

		return tabela_final;
	}

	public String decode(Hashtable<Character, String> code, String text){

		StringBuilder auxSB = new StringBuilder();
		StringBuilder auxSB1 = new StringBuilder();
		String key = null;
		for (int i = 0; i < text.length(); i++) {
			auxSB1.append(text.charAt(i));
			if (code.containsValue(auxSB1.toString())) {
				
				for(Map.Entry entry: code.entrySet()){
					if(auxSB1.toString().equals(entry.getValue())){
						key = entry.getKey().toString();
						break;
					}
				}

				auxSB.append(key);
				auxSB1.setLength(0);
			}
		}
		return auxSB.toString();
    }

	public String codifica_texto(String texto, Hashtable<Character,String> codificacao_final) {
		String codifica = "";
		String linha;

		try {
			BufferedReader br = new BufferedReader(new FileReader(texto));
			while ((linha = br.readLine()) != null){
				char[] letras = linha.toCharArray();
				for (int i = 0; i < linha.length(); i++){
					codifica = codifica + codificacao_final.get(letras[i]);
				}
			}
				br.close();
		}
		catch(IOException e) {
				e.printStackTrace();

		}
		System.out.println(codifica);


		return codifica;
	}
	
}
