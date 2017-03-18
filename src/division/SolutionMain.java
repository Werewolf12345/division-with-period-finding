package division;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

public class SolutionMain {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int divided;
		int divisor;
				
		// getting data
		try {
			divided = Math.abs(Integer.parseInt(reader.readLine()));
			divisor = Math.abs(Integer.parseInt(reader.readLine()));
			
			if (divisor == 0) throw new NumberFormatException("divisor shouldn't be zero!");
			if (divisor >= divided) throw new NumberFormatException("divisor should be less than divided!");
		} catch (NumberFormatException ex) {
			System.out.println("Wrong number format : " + ex.getMessage());
			return;		// The end...
		}
		reader.close();
						
		// calculating result and forming output strings
		ArrayList<String> printedOutputStrings = outputStringsFactory(divided, divisor);
		
		// printing strings to console
		for(String string : printedOutputStrings) {
			System.out.println(string);
		}
	}
		
	public static ArrayList<String> outputStringsFactory(int divided, int divisor) {
		ArrayList<String> result = new ArrayList<>();
		ArrayList<int[]> tablePairs = calcTablePairs(divided, divisor);
		double quotient = (double) divided / divisor;
		int spaces = 1;
		
		// drawing head
				result.add(" " + divided + "|" + divisor);	
				
				result.add(fillStringWithCharacter((digitCounter(divided)+1),' ')+"|"+
								fillStringWithCharacter((digitCounter(quotient)),'-'));
				
				result.add("-" + tablePairs.get(0)[1] + 
						fillStringWithCharacter((digitCounter(divided)-
								digitCounter(tablePairs.get(0)[1])),' ')+"|"+ 
								BigDecimal.valueOf(quotient).setScale(10, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
				
				result.add(" " + fillStringWithCharacter(digitCounter(tablePairs.get(0)[1]),'-'));
				
				// drawing table
				for(int i = 1; i < tablePairs.size(); i++){ 	
					spaces += digitCounter(tablePairs.get(i-1)[1]) - digitCounter(tablePairs.get(i-1)[0] - tablePairs.get(i-1)[1]);
					
					if(tablePairs.get(i-1)[0] - tablePairs.get(i-1)[1] == 0){	// special case
						spaces++;
					}
								
					result.add(fillStringWithCharacter(spaces, ' ') + tablePairs.get(i)[0]);
					result.add(fillStringWithCharacter(spaces-1 , ' ') + "-" + tablePairs.get(i)[1]);
					result.add(fillStringWithCharacter(spaces, ' ') + 
							fillStringWithCharacter(digitCounter(tablePairs.get(i)[1]), '-'));
				}
				
				// final reminder printing
				spaces += digitCounter(tablePairs.get(tablePairs.size()-1)[1]) - digitCounter(tablePairs.get(tablePairs.size()-1)[0] 
						- tablePairs.get(tablePairs.size()-1)[1]);
				
				result.add(fillStringWithCharacter(spaces, ' ') + (tablePairs.get(tablePairs.size()-1)[0] 
						- tablePairs.get(tablePairs.size()-1)[1]));
		
		return result;
	}
	
	public static ArrayList<int[]> calcTablePairs(int divided, int divisor)
	{
		ArrayList<int[]> result = new ArrayList<>();
		Double quotient = (double) divided / divisor;
		int positionInDivided = 0;
		int positionInQuotient = 0;
		int reminder = 0;
		int numberDown = 0;
						
		while(positionInQuotient < digitCounter(quotient)){
			int[] currentPair = new int[2];
			
			if(result.size() > 0 ){
				reminder = result.get(result.size()-1)[0] - result.get(result.size()-1)[1];
			}
			
			if(positionInDivided < digitCounter(divided)){
				numberDown = Integer.parseInt(String.valueOf(reminder) + getDigitByPosition(divided, positionInDivided++));
			} else {
				numberDown = Integer.parseInt(String.valueOf(reminder) + "0");
			}
			
			while(numberDown < divisor && numberDown > 0){
				numberDown = Integer.parseInt(String.valueOf(numberDown)
							+ String.valueOf(getDigitByPosition(divided, positionInDivided++)));
			}
			
			int nextQuotinentDigit = getDigitByPosition(quotient, positionInQuotient++);
			while(nextQuotinentDigit <= 0 && positionInQuotient < digitCounter(quotient)){
				nextQuotinentDigit = getDigitByPosition(quotient, positionInQuotient++);
			} 
								
			currentPair[0] = numberDown;
			currentPair[1] = nextQuotinentDigit * divisor;
							
			result.add(currentPair);
		}
		return result;
	}
	
	public static int getDigitByPosition(double doubleNumber, int position) 
	{
		String string =  BigDecimal.valueOf(doubleNumber).setScale(10, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
		char currentChar = string.charAt(position);
		int result;
		
		if(currentChar == '.') {
			result = -1;
		} else {
			result = Integer.parseInt(String.valueOf(currentChar));
		}
		
		return result;
	}
	
	public static String fillStringWithCharacter(int numberOfCharacters, char character) 
	{
	    StringBuilder sb = new StringBuilder();

	    for(int i=0; i < numberOfCharacters; i++)
	    {
	        sb.append(character);
	    }
	    return sb.toString();
	}
		
	public static int digitCounter(double doubleNumber) 
	{
		int counter = 0;
		BigDecimal tmp = BigDecimal.valueOf(doubleNumber).setScale(10, BigDecimal.ROUND_DOWN).stripTrailingZeros();
		int integerPart = tmp.intValue();
		BigDecimal fractionalPart = tmp.subtract(BigDecimal.valueOf(integerPart));
		
		if(fractionalPart.compareTo(BigDecimal.valueOf(0)) != 0) {
			counter = fractionalPart.toPlainString().length() - 1;
		}
		
		do {
			integerPart /= 10;
			counter++;
		} while (integerPart != 0);
		
		return counter;
	}
}
