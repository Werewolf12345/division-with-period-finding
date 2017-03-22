package division;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SolutionMain {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int divided;
        int divisor;

        // getting data
        try {
            divided = Math.abs(Integer.parseInt(reader.readLine()));
            divisor = Math.abs(Integer.parseInt(reader.readLine()));

            if (divisor == 0) {
                throw new NumberFormatException("divisor shouldn't be zero!");
                }

        } catch (NumberFormatException ex) {
            System.out.println("Wrong number format : " + ex.getMessage());
            return;		// The end...
        }
        reader.close();

        // calculating result and forming output strings
        ArrayList<String> printedOutputStrings = makingOutputStrings(divided, divisor);

        // printing strings to console
        for(String string : printedOutputStrings) {
            System.out.println(string);
        }
    }

    public static ArrayList<String> makingOutputStrings(int divided, int divisor) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<int[]> tablePairs = calcTablePairs(divided, divisor);
        double quotient = (double) divided / divisor;
        
        String quotientStringRepresentation = generateQuotientStringRepresentation(quotient);
        int digitsInQuot = quotientStringRepresentation.replaceAll("\\D", "").length();

        // drawing head

        int spacesAfterDivided;
        int leftSpaces;

        if(digitCounter(divided) >= digitCounter(tablePairs.get(0)[1])) {
            spacesAfterDivided = 0;
        } else {
            spacesAfterDivided = digitCounter(tablePairs.get(0)[1]) - digitCounter(divided);
        }

        result.add(" " + divided + fillStringWithCharacter(spacesAfterDivided,' ')+ "|" + divisor);	
        result.add(fillStringWithCharacter(digitCounter(divided) + spacesAfterDivided + 1,' ')+"|"+
                fillStringWithCharacter(Math.max(quotientStringRepresentation.length(), digitCounter(divisor)),'-'));
        leftSpaces = digitCounter(tablePairs.get(0)[0]) - digitCounter(tablePairs.get(0)[1]);
        result.add(fillStringWithCharacter(leftSpaces, ' ') + "-" + tablePairs.get(0)[1] + 
                fillStringWithCharacter((digitCounter(divided)-
                        digitCounter(tablePairs.get(0)[1])) - leftSpaces,' ')+"|"+ quotientStringRepresentation);
        leftSpaces++;
        result.add(fillStringWithCharacter(1, ' ')
                + fillStringWithCharacter(digitCounter(tablePairs.get(0)[0]),'-'));

        // drawing table
        for(int i = 1; i < digitsInQuot; i++){ 	
            leftSpaces += digitCounter(tablePairs.get(i-1)[1]) - digitCounter(tablePairs.get(i-1)[0] - tablePairs.get(i-1)[1]);

            if(tablePairs.get(i-1)[0] - tablePairs.get(i-1)[1] == 0){	// special case - pair equal
                leftSpaces++;
            }

            result.add(fillStringWithCharacter(leftSpaces, ' ') + tablePairs.get(i)[0]);
            result.add(fillStringWithCharacter(leftSpaces-1, ' ') + "-" + tablePairs.get(i)[1]);
            leftSpaces += digitCounter(tablePairs.get(i)[0]) - digitCounter(tablePairs.get(i)[1]);
            result.add(fillStringWithCharacter(leftSpaces, ' ') + 
                    fillStringWithCharacter(digitCounter(tablePairs.get(i)[0]), '-'));
        }

        // final reminder printing
        leftSpaces += digitCounter(tablePairs.get(tablePairs.size()-1)[1]) - digitCounter(tablePairs.get(tablePairs.size()-1)[0] 
                - tablePairs.get(tablePairs.size()-1)[1]);
        result.add(fillStringWithCharacter(leftSpaces, ' ') + (tablePairs.get(tablePairs.size()-1)[0] 
                - tablePairs.get(tablePairs.size()-1)[1]));

        return result;
    }
    
    private static String generateQuotientStringRepresentation (double quotient) {
        List<Object> splitted = splitIntoIntegerAndFractionalParts(quotient);
        Integer integerPart = (Integer) splitted.get(0);
        String fractionalPart = (String) splitted.get(1);
        String quotientStringRepresentation;

        if (fractionalPart.equals("0")) { // has no fractional part
            quotientStringRepresentation = integerPart.toString();
        } else { // has fractional part
            int circleCounter = 1; // trying to find period
            int position = 1;
            boolean hasPeriod = false;
            String period = "";
            final int ZERO_AND_DOT_LENGH = 2;

            while (!hasPeriod
                    && position < (fractionalPart.length() - ZERO_AND_DOT_LENGH) - 1) {
                position++;
                period = fractionalPart.substring(position, position + 1);
                circleCounter = fractionalPart.indexOf(period, position + 1);
                if (circleCounter != -1) { // we got it
                    hasPeriod = true;
                }
            }

            if (hasPeriod) {
                quotientStringRepresentation = integerPart
                        + fractionalPart.substring(1, position) + "("
                        + fractionalPart.substring(position, circleCounter)
                        + ")";
            } else {
                quotientStringRepresentation = integerPart
                        + fractionalPart.substring(1);
            }
        }
        return quotientStringRepresentation;
    }

    /**
     * @return List of integer pairs represents "divided" - "divisor" pair in the resulting column
     */
    private static ArrayList<int[]> calcTablePairs(int divided, int divisor)
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
                reminder = result.get(result.size()-1)[0] - result.get(result.size()-1)[1];	// calculating last pair reminder
            }

            if(positionInDivided < digitCounter(divided)){		// taking down digit from divided or 0 if out of bounds
                numberDown = Integer.parseInt(String.valueOf(reminder) + getDigitByPosition(divided, positionInDivided++));
            } else {
                numberDown = Integer.parseInt(String.valueOf(reminder) + "0");
            }

            while(numberDown < divisor) {		// taking down next digit until numberDown > divisor
                if (positionInDivided < digitCounter(divided)){
                    numberDown = Integer.parseInt(String.valueOf(numberDown)
                            + String.valueOf(getDigitByPosition(divided, positionInDivided++)));
                } else {
                    numberDown = Integer.parseInt(String.valueOf(numberDown) + "0");
                }
            }

            int nextQuotientDigit = getDigitByPosition(quotient, positionInQuotient++);
            // taking next digit from quotient
            while(nextQuotientDigit <= 0 && positionInQuotient < digitCounter(quotient)){
                nextQuotientDigit = getDigitByPosition(quotient, positionInQuotient++);
            } 

            currentPair[0] = numberDown;
            currentPair[1] = nextQuotientDigit * divisor;

            result.add(currentPair);
        }
        return result;
    }

    private static int getDigitByPosition(double doubleNumber, int position) 
    {
        // formatting number according our requirements and converting to string
        String string =  BigDecimal.valueOf(doubleNumber).setScale(10, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        char currentChar = string.charAt(position);
        int result;

        if(currentChar == '.') {
            result = -1;			// return -1 if its dot 
        } else {
            result = Integer.parseInt(String.valueOf(currentChar));
        }

        return result;
    }

    private static String fillStringWithCharacter(int numberOfCharacters, char character) {
        StringBuilder resultingString = new StringBuilder();

        for (int i = 0; i < numberOfCharacters; i++) {
            resultingString.append(character);
        }
        return resultingString.toString();
    }

    private static int digitCounter(double doubleNumber) {
        int counter = 0;
        List<Object> splitted = splitIntoIntegerAndFractionalParts(doubleNumber);

        Integer integerPart = (Integer) splitted.get(0);
        String fractionalPart = (String) splitted.get(1);

        // counting digits in fractional part
        counter = fractionalPart.length() - 1;

        // counting digits in integer part
        do {
            integerPart /= 10;
            counter++;
        } while (integerPart != 0);

        return counter;
    }

    /**
     * @return List of two objects, first (0) is integer part representation as Integer object and
     * second (1) is the fractional part representation as String object ("0.X...")
    */
    private static List<Object> splitIntoIntegerAndFractionalParts(final double doubleNumber) {
        final int OUR_SCALE = 10;
        
        // formatting number according our requirements
        BigDecimal bigDecimalRepresentation = BigDecimal.valueOf(doubleNumber).setScale(OUR_SCALE, BigDecimal.ROUND_DOWN);
        bigDecimalRepresentation = bigDecimalRepresentation.stripTrailingZeros();

        // split into integer and fractional parts
        Integer integerPart = bigDecimalRepresentation.intValue();
        String fractPartStr = bigDecimalRepresentation.subtract(BigDecimal.valueOf(integerPart)).toPlainString();

        // building result
        List<Object> result = new ArrayList<>();
        result.add(integerPart);
        result.add(fractPartStr);
        return result;
    }	
}
