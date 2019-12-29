package DAL;

import java.io.*;
public class SerializeHelper 
{
	
	public boolean SaveToFile(Object obj,String filePath) {
		boolean result = false;				
		try 
		{								
			FileOutputStream fileOut = new FileOutputStream(filePath); 			 
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(obj);			
			out.close();
			fileOut.close();
			result = true;
		}catch(IOException ex) 
		{
			ex.printStackTrace();
		}
		return result;		
	}
	public Object DeSerializeFromFile(String filePath) 
	{
		Object result = null ;				
		try 
		{
			FileInputStream fileIn = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			result = in.readObject() ;
	        in.close();
	        fileIn.close();
		}
		catch (IOException ex) 
		{			
			ex.printStackTrace();
		}
		catch(ClassNotFoundException ex ) 
		{			
			ex.printStackTrace();
		}		
		return result;
	}
}
