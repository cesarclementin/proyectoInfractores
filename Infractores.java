package infractores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Infractores {
	
	private Map<String, Integer> infractores = new HashMap<String, Integer>();
	
	FileReader fr = null;
	BufferedReader br = null;
	
	public void leerInfractores(String entrada) throws FileNotFoundException {
		
		fr = new FileReader(new File(entrada));
		br = new BufferedReader(fr);
		
		String linea;
		
		try {
			while((linea = br.readLine()) != null) {
				
				String datos[] = linea.split(" ");
				
				String patente = datos[0];
				int velocidad = Integer.parseInt(datos[1]);
				
				if(velocidad > 80) {
					if(infractores.containsKey(patente)) {
						infractores.put(patente, infractores.get(patente) + 1);
					}
					else {
						infractores.put(patente, 1);
					}					
				}				
			}	
		}
		catch(IOException e){
	         e.printStackTrace();
	      }finally{	
	    	  
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
		
		
	}
	
	public void escribirMultados(String salida) throws FileNotFoundException {
		
		PrintWriter pr = new PrintWriter(new File(salida));
		
		for(Entry<String, Integer> cadaUno : infractores.entrySet()) {
			
			String linea =  "\n" + cadaUno.getKey() + " "  + cadaUno.getValue() * 50000;
			
			pr.write(linea);
		}
		
		pr.close();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Infractores inf = new Infractores();
		
		inf.leerInfractores("./src/infractores/Infractores.in");
		inf.escribirMultados("./src/infractores/Infractores.out");
		
		
	}
	

}
