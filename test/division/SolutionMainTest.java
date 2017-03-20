package division;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SolutionMainTest {
	@Test
	public void testDigitCounter() {
		assertEquals(1, SolutionMain.digitCounter(0));
		assertEquals(3, SolutionMain.digitCounter(999));
		assertEquals(3, SolutionMain.digitCounter(100));
		assertEquals(3, SolutionMain.digitCounter(-999));
		assertEquals(1, SolutionMain.digitCounter(1));
		assertEquals(2, SolutionMain.digitCounter(-15));
		assertEquals(3, SolutionMain.digitCounter(100.0));
		assertEquals(5, SolutionMain.digitCounter(100.1));
		assertEquals(14, SolutionMain.digitCounter(100.0000000001));
	}
	
	@Test
	public void testDigitSpaces() {
		assertEquals("", SolutionMain.fillStringWithCharacter(0, ' '));
		assertEquals(" ", SolutionMain.fillStringWithCharacter(1, ' '));
		assertEquals("___", SolutionMain.fillStringWithCharacter(3, '_'));
	}
	
	@Test
	public void testGetDigitByPosition() {
		assertEquals(1, SolutionMain.getDigitByPosition(123,0));
		assertEquals(3, SolutionMain.getDigitByPosition(123,2));
		assertEquals(2, SolutionMain.getDigitByPosition(123,1));
		assertEquals(-1, SolutionMain.getDigitByPosition(123.12,3));
		assertEquals(2, SolutionMain.getDigitByPosition(123.12,5));
	}

	@Test
	public void testCalcTablePairs() {
		assertArrayEquals(new int[][] {{8,8}, {4, 4}, {20,20}}, SolutionMain.calcTablePairs(8420, 4).toArray());
		assertArrayEquals(new int[][] {{126,111}, {150, 148}, {225,222}, {37,37}}, SolutionMain.calcTablePairs(1260257, 37).toArray());
		assertArrayEquals(new int[][] {{7,4}, {38, 36}, {24,24}, {5, 4}, {19, 16}, {30, 28},{20, 20}}, SolutionMain.calcTablePairs(78459, 4).toArray());
		assertArrayEquals(new int[][] {{10,9}, {10,9}, {10,9}, {10,9}, {10,9}, {10,9}, {10,9}, {10,9}, {10,9}, {10,9}, {10,9}, 
										{10,9}, {10,9}},SolutionMain.calcTablePairs(1000, 3).toArray());
	}
	
	@Test
	public void testoutputStringsFactory() {
		ArrayList<String> testedOutputStrings = SolutionMain.outputStringsFactory(225, 15);
		ArrayList<String> expectedOutputStrings = new ArrayList<>();
		expectedOutputStrings.add(" 225|15");
		expectedOutputStrings.add("    |--");
		expectedOutputStrings.add("-15 |15");
		expectedOutputStrings.add(" --");
		expectedOutputStrings.add("  75");
		expectedOutputStrings.add(" -75");
		expectedOutputStrings.add("  --");
		expectedOutputStrings.add("   0");
		assertArrayEquals(expectedOutputStrings.toArray(), testedOutputStrings.toArray());
		expectedOutputStrings.clear();
		
		testedOutputStrings = SolutionMain.outputStringsFactory(78459, 4);
		expectedOutputStrings.add(" 78459|4");
		expectedOutputStrings.add("      |--------");
		expectedOutputStrings.add("-4    |19614.75");
		expectedOutputStrings.add(" -");
		expectedOutputStrings.add(" 38");
		expectedOutputStrings.add("-36");
		expectedOutputStrings.add(" --");
		expectedOutputStrings.add("  24");
		expectedOutputStrings.add(" -24");
		expectedOutputStrings.add("  --");
		expectedOutputStrings.add("    5");
		expectedOutputStrings.add("   -4");
		expectedOutputStrings.add("    -");
		expectedOutputStrings.add("    19");
		expectedOutputStrings.add("   -16");
		expectedOutputStrings.add("    --");
		expectedOutputStrings.add("     30");
		expectedOutputStrings.add("    -28");
		expectedOutputStrings.add("     --");
		expectedOutputStrings.add("      20");
		expectedOutputStrings.add("     -20");
		expectedOutputStrings.add("      --");
		expectedOutputStrings.add("       0");
		assertArrayEquals(expectedOutputStrings.toArray(), testedOutputStrings.toArray());
		expectedOutputStrings.clear();
		
		testedOutputStrings = SolutionMain.outputStringsFactory(1000, 3);
		expectedOutputStrings.add(" 1000|3");
		expectedOutputStrings.add("     |-------");
		expectedOutputStrings.add(" -9  |333.(3)");
		expectedOutputStrings.add(" --");
		expectedOutputStrings.add("  10");
		expectedOutputStrings.add("  -9");
		expectedOutputStrings.add("  --");
		expectedOutputStrings.add("   10");
		expectedOutputStrings.add("   -9");
		expectedOutputStrings.add("   --");
		expectedOutputStrings.add("    10");
		expectedOutputStrings.add("    -9");
		expectedOutputStrings.add("    --");
		expectedOutputStrings.add("     1");
		assertArrayEquals(expectedOutputStrings.toArray(), testedOutputStrings.toArray());
		expectedOutputStrings.clear();
				
	} 
}
