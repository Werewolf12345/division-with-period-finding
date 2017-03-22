package division;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SolutionMainTest {
	
	@Test
	public void testoutputStringsFactory() {
		ArrayList<String> testedOutputStrings = SolutionMain.makingOutputStrings(225, 15);
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
		
		testedOutputStrings = SolutionMain.makingOutputStrings(78459, 4);
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
		
		testedOutputStrings = SolutionMain.makingOutputStrings(1000, 3);
		expectedOutputStrings.add(" 1000|3");
		expectedOutputStrings.add("     |-------");
		expectedOutputStrings.add(" -9  |333.(3)");
		expectedOutputStrings.add(" --");
		expectedOutputStrings.add("  10");
		expectedOutputStrings.add(" -9");
		expectedOutputStrings.add("   --");
		expectedOutputStrings.add("   10");
		expectedOutputStrings.add("  -9");
		expectedOutputStrings.add("    --");
		expectedOutputStrings.add("    10");
		expectedOutputStrings.add("   -9");
		expectedOutputStrings.add("     --");
		expectedOutputStrings.add("     1");
		assertArrayEquals(expectedOutputStrings.toArray(), testedOutputStrings.toArray());
		expectedOutputStrings.clear();
	}
}