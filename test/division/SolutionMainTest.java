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
		assertEquals(7, SolutionMain.digitCounter(1000000));
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
	}

	@Test
	public void testCalcTablePairs() {
		assertArrayEquals(new int[][] {{8,8}, {4, 4}, {20,20}}, SolutionMain.calcTablePairs(8420, 4).toArray());
		assertArrayEquals(new int[][] {{7,4}, {38, 36}, {24,24}, {5, 4}, {19, 16}}, SolutionMain.calcTablePairs(78459, 4).toArray());
		assertArrayEquals(new int[][] {{126,111}, {150, 148}, {225,222}, {37,37}}, SolutionMain.calcTablePairs(1260257, 37).toArray());
	}
	
	@Test
	public void testoutputStringsFactory() {
		ArrayList<String> testedOutputStrings = SolutionMain.outputStringsFactory(1234, 11);
		ArrayList<String> expectedOutputStrings = new ArrayList<>();
		expectedOutputStrings.add(" 1234|11");
		expectedOutputStrings.add("     |---");
		expectedOutputStrings.add("-11  |112");
		expectedOutputStrings.add(" --");
		expectedOutputStrings.add("  13");
		expectedOutputStrings.add(" -11");
		expectedOutputStrings.add("  --");
		expectedOutputStrings.add("   24");
		expectedOutputStrings.add("  -22");
		expectedOutputStrings.add("   --");
		expectedOutputStrings.add("    2");
		assertArrayEquals(expectedOutputStrings.toArray(), testedOutputStrings.toArray());
		expectedOutputStrings.clear();
		
		testedOutputStrings = SolutionMain.outputStringsFactory(78459, 4);
		expectedOutputStrings.add(" 78459|4");
		expectedOutputStrings.add("      |-----");
		expectedOutputStrings.add("-4    |19614");
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
		expectedOutputStrings.add("     3");
		assertArrayEquals(expectedOutputStrings.toArray(), testedOutputStrings.toArray());
		expectedOutputStrings.clear();
	} 
}
