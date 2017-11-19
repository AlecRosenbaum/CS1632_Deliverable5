import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.*;
import org.junit.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class DeliverableTest {

    Pairwise _pair;

    @Before
    public void setup() {
        _pair = new Pairwise();
    }


    // Assert that creating a new Pairwise instance does not return null
    @Test
    public void testDeliverableExists() {
        assertNotNull(_pair);
    }

    //This allows us to read directly from console
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();


    //Test and make sure the main function will return an error if no args are passed in
    @Test
    public void testMain_000()throws InterruptedException, ExecutionException {
        _pair.main(new String[] {});
        assertEquals("IPlease enter at least two parameters!\n", systemOutRule.getLog());
    }

    //Test and make sure that the main function will catch a file not found exception
    @Test
    public void testMain_001()throws InterruptedException, ExecutionException {
        _pair.main(new String[] {"foo", "bar"});
        assertEquals("foo bar \n0   0   \n1   1   \n1   0   \n0   1   \n", systemOutRule.getLog());
    }
    /*
    //Test and make sure that an empty file will be processes and not error
    @Test
    public void testMain_002()throws InterruptedException, ExecutionException {
        _del.main(new String[] {"empty.txt"});
        assertEquals("", systemOutRule.getLog());
    }

    //Test and make sure that test file lala.txt works as expected
    @Test
    public void testMain_003()throws InterruptedException, ExecutionException {
        _del.main(new String[] {"sample_files/lala.txt"});
        assertEquals("La - La - Mc - B - O - O\nLanthanum - Lanthanum - Moscovium - Boron - Oxygen - Oxygen\nLa - La - Ra\nLanthanum - Lanthanum - Radium\n", systemOutRule.getLog());
    }

    //Test and make sure that test file noogie.txt works as expected
    @Test
    public void testMain_004()throws InterruptedException, ExecutionException {
        _del.main(new String[] {"sample_files/noogie.txt"});
        assertEquals("La - B - O - O - N\nLanthanum - Boron - Oxygen - Oxygen - Nitrogen\nLa - B - O - O - N\nLanthanum - Boron - Oxygen - Oxygen - Nitrogen\nAl - Li - S - O - N\nAluminum - Lithium - Sulfur - Oxygen - Nitrogen\nB - O - O\nBoron - Oxygen - Oxygen\nCould not create name Bill out of elements.\n", systemOutRule.getLog());
    }

    //Test and make sure that test file tsars.txt works as expected
    @Test
    public void testMain_005()throws InterruptedException, ExecutionException {
        _del.main(new String[] {"sample_files/tsars.txt"});
        assertEquals("Could not create name Tsar Meow out of elements.\nTs - Ar - Ra\nTennessine - Argon - Radium\nTs - Ar - N - I - C - H - O - La - S\nTennessine - Argon - Nitrogen - Iodine - Carbon - Hydrogen - Oxygen - Lanthanum - Sulfur\n", systemOutRule.getLog());
    }



    //Check and make sure that our base cache populated with test name John Jacob Jingleheimer Schmidt
    @Test
    public void testCache_000() {
        _del.populateCache();
        assertEquals("Could not create name John Jacob Jingleheimer Schmidt out of elements.\n", _del.cache.get("John Jacob Jingleheimer Schmidt"));
    }
    //Check and make sure that our base cache populated with test name Tsar Tsar
    @Test
    public void testCache_001() {
        _del.populateCache();
        assertEquals("Ts - Ar - Ts - Ar\nTennessine - Argon - Tennessine - Argon\n", _del.cache.get("Tsar Tsar"));
    }
    //Check and make sure that our base cache populated with test name Laboons
    @Test
    public void testCache_002() {
        _del.populateCache();
        assertEquals("La - B - O - O - N - S\nLanthanum - Boron - Oxygen - Oxygen - Nitrogen - Sulfur\n", _del.cache.get("Laboons "));
    }
    //Check and make sure that our base cache populated with test name Nick Nickelback
    @Test
    public void testCache_003() {
        _del.populateCache();
        assertEquals("Could not create name Nick Nickelback out of elements.\n", _del.cache.get("Nick Nickelback"));
    }

    //Check and make sure that ALL Cahce Elements were added to the hashmap
    @Test
    public void testCache_004() {
        //Populate the elements
        _del.populateCache();

        //Create our own hashmap to test against
        HashMap<String, String> trueCache = new HashMap<String, String>(256);
        trueCache.put("John Jacob Jingleheimer Schmidt", "Could not create name John Jacob Jingleheimer Schmidt out of elements.\n");
        trueCache.put("Lalalalalalalalalala", "La - La - La - La - La - La - La - La - La - La\nLanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum\n");
        trueCache.put("Laboon", "La - B - O - O - N\nLanthanum - Boron - Oxygen - Oxygen - Nitrogen\n");
        trueCache.put("Art Arterson", "Could not create name Art Arterson out of elements.\n");
        trueCache.put("Bob", "B - O - B\nBoron - Oxygen - Boron\n");
        trueCache.put("Coco Atas", "C - O - C - O - At - As\nCarbon - Oxygen - Carbon - Oxygen - Astatine - Arsenic\n");
        trueCache.put("Bob Bobber", "B - O - B - B - O - B - B - Er\nBoron - Oxygen - Boron - Boron - Oxygen - Boron - Boron - Erbium\n");
        trueCache.put("Bob Bobberson", "B - O - B - B - O - B - B - Er - S - O - N\nBoron - Oxygen - Boron - Boron - Oxygen - Boron - Boron - Erbium - Sulfur - Oxygen - Nitrogen\n");
        trueCache.put("Al Bobberson", "Al - B - O - B - B - Er - S - O - N\nAluminum - Boron - Oxygen - Boron - Boron - Erbium - Sulfur - Oxygen - Nitrogen\n");
        trueCache.put("Al Allerson", "Could not create name Al Allerson out of elements.\n");
        trueCache.put("Al Allison", "Al - Al - Li - S - O - N\nAluminum - Aluminum - Lithium - Sulfur - Oxygen - Nitrogen\n");
        trueCache.put("Allison Allisons", "Al - Li - S - O - N - Al - Li - S - O - N - S\nAluminum - Lithium - Sulfur - Oxygen - Nitrogen - Aluminum - Lithium - Sulfur - Oxygen - Nitrogen - Sulfur\n");
        trueCache.put("Laboons ", "La - B - O - O - N - S\nLanthanum - Boron - Oxygen - Oxygen - Nitrogen - Sulfur\n");
        trueCache.put("Bo Bo", "B - O - B - O\nBoron - Oxygen - Boron - Oxygen\n");
        trueCache.put("Bo Bo Bo", "B - O - B - O - B - O\nBoron - Oxygen - Boron - Oxygen - Boron - Oxygen\n");
        trueCache.put("Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo", "B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O\nBoron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen\n");
        trueCache.put("Bob Powers", "B - O - B - P - O - W - Er - S\nBoron - Oxygen - Boron - Phosphorus - Oxygen - Tungsten - Erbium - Sulfur\n");
        trueCache.put("Tiny Powers", "Ti - N - Y - P - O - W - Er - S\nTitanium - Nitrogen - Yttrium - Phosphorus - Oxygen - Tungsten - Erbium - Sulfur\n");
        trueCache.put("Pow Powerson", "P - O - W - P - O - W - Er - S - O - N\nPhosphorus - Oxygen - Tungsten - Phosphorus - Oxygen - Tungsten - Erbium - Sulfur - Oxygen - Nitrogen\n");
        trueCache.put("Nick Nickelback", "Could not create name Nick Nickelback out of elements.\n");
        trueCache.put("Nick Powers", "N - I - C - K - P - O - W - Er - S\nNitrogen - Iodine - Carbon - Potassium - Phosphorus - Oxygen - Tungsten - Erbium - Sulfur\n");
        trueCache.put("Nick Atas", "N - I - C - K - At - As\nNitrogen - Iodine - Carbon - Potassium - Astatine - Arsenic\n");
        trueCache.put("Tiny Nick", "Ti - N - Y - N - I - C - K\nTitanium - Nitrogen - Yttrium - Nitrogen - Iodine - Carbon - Potassium\n");
        trueCache.put("Bob Creat", "B - O - B - C - Re - At\nBoron - Oxygen - Boron - Carbon - Rhenium - Astatine\n");
        trueCache.put("Loopy Creat", "Could not create name Loopy Creat out of elements.\n");
        trueCache.put("Tsar Bomba", "Could not create name Tsar Bomba out of elements.\n");
        trueCache.put("Tsar Boo", "Ts - Ar - B - O - O\nTennessine - Argon - Boron - Oxygen - Oxygen\n");
        trueCache.put("Tsar Tsar", "Ts - Ar - Ts - Ar\nTennessine - Argon - Tennessine - Argon\n");

        for (Map.Entry<String, String> entry : trueCache.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            assertEquals(value, _del.cache.get(key));
        }
    }

    //Check and make sure that ALL Elements were added to the hashmap
    @Test
    public void testElemets_000() {
        //Populate the elements
        _del.populateElems();

        //Create our own hashmap to test against
        HashMap<String, String> trueElements = new HashMap<String, String>(256);
        trueElements.put("AC", "Actinium");
        trueElements.put("AG", "Silver");
        trueElements.put("AL", "Aluminum");
        trueElements.put("AM", "Americium");
        trueElements.put("AR", "Argon");
        trueElements.put("AS", "Arsenic");
        trueElements.put("AT", "Astatine");
        trueElements.put("AU", "Gold");
        trueElements.put("B", "Boron");
        trueElements.put("BA", "Barium");
        trueElements.put("BE", "Beryllium");
        trueElements.put("BH", "Bohrium");
        trueElements.put("BI", "Bismuth");
        trueElements.put("BK", "Berkelium");
        trueElements.put("BR", "Bromine");
        trueElements.put("C", "Carbon");
        trueElements.put("CA", "Calcium");
        trueElements.put("CD", "Cadmium");
        trueElements.put("CE", "Cerium");
        trueElements.put("CF", "Californium");
        trueElements.put("CL", "Chlorine");
        trueElements.put("CM", "Curium");
        trueElements.put("CN", "Copernicium");
        trueElements.put("CO", "Cobalt");
        trueElements.put("CR", "Chromium");
        trueElements.put("CS", "Cesium");
        trueElements.put("CU", "Copper");
        trueElements.put("DB", "Dubnium");
        trueElements.put("DS", "Darmstadtium");
        trueElements.put("DY", "Dysprosium");
        trueElements.put("ER", "Erbium");
        trueElements.put("ES", "Einsteinium");
        trueElements.put("EU", "Europium");
        trueElements.put("F", "Fluorine");
        trueElements.put("FE", "Iron");
        trueElements.put("FL", "Flerovium");
        trueElements.put("FM", "Fermium");
        trueElements.put("FR", "Francium");
        trueElements.put("GA", "Gallium");
        trueElements.put("GD", "Gadolinium");
        trueElements.put("GE", "Germanium");
        trueElements.put("H", "Hydrogen");
        trueElements.put("HE", "Helium");
        trueElements.put("HF", "Hafnium");
        trueElements.put("HG", "Mercury");
        trueElements.put("HO", "Holmium");
        trueElements.put("HS", "Hassium");
        trueElements.put("I", "Iodine");
        trueElements.put("IN", "Indium");
        trueElements.put("IR", "Iridium");
        trueElements.put("K", "Potassium");
        trueElements.put("KR", "Krypton");
        trueElements.put("LA", "Lanthanum");
        trueElements.put("LI", "Lithium");
        trueElements.put("LR", "Lawrencium");
        trueElements.put("LU", "Lutetium");
        trueElements.put("LV", "Livermorium");
        trueElements.put("MC", "Moscovium");
        trueElements.put("MD", "Mendelevium");
        trueElements.put("MG", "Magnesium");
        trueElements.put("MN", "Manganese");
        trueElements.put("MO", "Molybdenum");
        trueElements.put("MT", "Meitnerium");
        trueElements.put("N", "Nitrogen");
        trueElements.put("NA", "Sodium");
        trueElements.put("NB", "Niobium");
        trueElements.put("ND", "Neodymium");
        trueElements.put("NE", "Neon");
        trueElements.put("NH", "Nihonium");
        trueElements.put("NI", "Nickel");
        trueElements.put("NO", "Nobelium");
        trueElements.put("NP", "Neptunium");
        trueElements.put("O", "Oxygen");
        trueElements.put("OG", "Oganesson");
        trueElements.put("OS", "Osmium");
        trueElements.put("P", "Phosphorus");
        trueElements.put("PA", "Protactinium");
        trueElements.put("PB", "Lead");
        trueElements.put("PD", "Palladium");
        trueElements.put("PM", "Promethium");
        trueElements.put("PO", "Polonium");
        trueElements.put("PR", "Praseodymium");
        trueElements.put("PT", "Platinum");
        trueElements.put("PU", "Plutonium");
        trueElements.put("RA", "Radium");
        trueElements.put("RB", "Rubidium");
        trueElements.put("RE", "Rhenium");
        trueElements.put("RF", "Rutherfordium");
        trueElements.put("RG", "Roentgenium");
        trueElements.put("RH", "Rhodium");
        trueElements.put("RN", "Radon");
        trueElements.put("RU", "Ruthenium");
        trueElements.put("S", "Sulfur");
        trueElements.put("SB", "Antimony");
        trueElements.put("SC", "Scandium");
        trueElements.put("SE", "Selenium");
        trueElements.put("SG", "Seaborgium");
        trueElements.put("SI", "Silicon");
        trueElements.put("SM", "Samarium");
        trueElements.put("SN", "Tin");
        trueElements.put("SR", "Strontium");
        trueElements.put("TA", "Tantalum");
        trueElements.put("TB", "Terbium");
        trueElements.put("TC", "Technetium");
        trueElements.put("TE", "Tellurium");
        trueElements.put("TH", "Thorium");
        trueElements.put("TI", "Titanium");
        trueElements.put("TL", "Thallium");
        trueElements.put("TM", "Thulium");
        trueElements.put("TS", "Tennessine");
        trueElements.put("U", "Uranium");
        trueElements.put("V", "Vanadium");
        trueElements.put("W", "Tungsten");
        trueElements.put("XE", "Xenon");
        trueElements.put("Y", "Yttrium");
        trueElements.put("YB", "Ytterbium");
        trueElements.put("ZN", "Zinc");
        trueElements.put("ZR", "Zirconium");

        for (Map.Entry<String, String> entry : trueElements.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            assertEquals(value, _del.elems.get(key));
        }
    }

    //Check and make sure the logic for converting is working WITHOUT THE CACHE with text Nick Nickelback
    @Test
    public void testConvert_000() {
        _del.populateElems();
        assertEquals("Could not create name Nick Nickelback out of elements.\n", _del.readLine("Nick Nickelback"));
    }
    //Check and make sure the logic for converting is working WITHOUT THE CACHE with text Tsar Tsar
    @Test
    public void testConvert_001() {
        _del.populateElems();
        assertEquals("Ts - Ar - Ts - Ar\nTennessine - Argon - Tennessine - Argon\n", _del.readLine("Tsar Tsar"));
    }
    //Check and make sure the logic for converting is working WITHOUT THE CACHE with text Allison Allisons
    @Test
    public void testConvert_002() {
        _del.populateElems();
        assertEquals("Al - Li - S - O - N - Al - Li - S - O - N - S\nAluminum - Lithium - Sulfur - Oxygen - Nitrogen - Aluminum - Lithium - Sulfur - Oxygen - Nitrogen - Sulfur\n", _del.readLine("Allison Allisons"));
    }
    //Check and make sure the logic for converting is working WITHOUT THE CACHE with text BOB BOBBERSON
    @Test
    public void testConvert_003() {
        _del.populateElems();
        assertEquals("B - O - B - B - O - B - B - Er - S - O - N\nBoron - Oxygen - Boron - Boron - Oxygen - Boron - Boron - Erbium - Sulfur - Oxygen - Nitrogen\n", _del.readLine("Bob Bobberson"));
    }

    //Check and make sure all of the test cases for the program work!
    @Test
    public void testConvert_004() {
        //Populate the elements
        _del.populateElems();

        //Create our own hashmap to test against
        HashMap<String, String> trueCache = new HashMap<String, String>(256);
        trueCache.put("John Jacob Jingleheimer Schmidt", "Could not create name John Jacob Jingleheimer Schmidt out of elements.\n");
        trueCache.put("Lalalalalalalalalala", "La - La - La - La - La - La - La - La - La - La\nLanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum\n");
        trueCache.put("Laboon", "La - B - O - O - N\nLanthanum - Boron - Oxygen - Oxygen - Nitrogen\n");
        trueCache.put("Art Arterson", "Could not create name Art Arterson out of elements.\n");
        trueCache.put("Bob", "B - O - B\nBoron - Oxygen - Boron\n");
        trueCache.put("Coco Atas", "C - O - C - O - At - As\nCarbon - Oxygen - Carbon - Oxygen - Astatine - Arsenic\n");
        trueCache.put("Bob Bobber", "B - O - B - B - O - B - B - Er\nBoron - Oxygen - Boron - Boron - Oxygen - Boron - Boron - Erbium\n");
        trueCache.put("Bob Bobberson", "B - O - B - B - O - B - B - Er - S - O - N\nBoron - Oxygen - Boron - Boron - Oxygen - Boron - Boron - Erbium - Sulfur - Oxygen - Nitrogen\n");
        trueCache.put("Al Bobberson", "Al - B - O - B - B - Er - S - O - N\nAluminum - Boron - Oxygen - Boron - Boron - Erbium - Sulfur - Oxygen - Nitrogen\n");
        trueCache.put("Al Allerson", "Could not create name Al Allerson out of elements.\n");
        trueCache.put("Al Allison", "Al - Al - Li - S - O - N\nAluminum - Aluminum - Lithium - Sulfur - Oxygen - Nitrogen\n");
        trueCache.put("Allison Allisons", "Al - Li - S - O - N - Al - Li - S - O - N - S\nAluminum - Lithium - Sulfur - Oxygen - Nitrogen - Aluminum - Lithium - Sulfur - Oxygen - Nitrogen - Sulfur\n");
        trueCache.put("Laboons ", "La - B - O - O - N - S\nLanthanum - Boron - Oxygen - Oxygen - Nitrogen - Sulfur\n");
        trueCache.put("Bo Bo", "B - O - B - O\nBoron - Oxygen - Boron - Oxygen\n");
        trueCache.put("Bo Bo Bo", "B - O - B - O - B - O\nBoron - Oxygen - Boron - Oxygen - Boron - Oxygen\n");
        trueCache.put("Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo", "B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O\nBoron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen\n");
        trueCache.put("Bob Powers", "B - O - B - P - O - W - Er - S\nBoron - Oxygen - Boron - Phosphorus - Oxygen - Tungsten - Erbium - Sulfur\n");
        trueCache.put("Tiny Powers", "Ti - N - Y - P - O - W - Er - S\nTitanium - Nitrogen - Yttrium - Phosphorus - Oxygen - Tungsten - Erbium - Sulfur\n");
        trueCache.put("Pow Powerson", "P - O - W - P - O - W - Er - S - O - N\nPhosphorus - Oxygen - Tungsten - Phosphorus - Oxygen - Tungsten - Erbium - Sulfur - Oxygen - Nitrogen\n");
        trueCache.put("Nick Nickelback", "Could not create name Nick Nickelback out of elements.\n");
        trueCache.put("Nick Powers", "N - I - C - K - P - O - W - Er - S\nNitrogen - Iodine - Carbon - Potassium - Phosphorus - Oxygen - Tungsten - Erbium - Sulfur\n");
        trueCache.put("Nick Atas", "N - I - C - K - At - As\nNitrogen - Iodine - Carbon - Potassium - Astatine - Arsenic\n");
        trueCache.put("Tiny Nick", "Ti - N - Y - N - I - C - K\nTitanium - Nitrogen - Yttrium - Nitrogen - Iodine - Carbon - Potassium\n");
        trueCache.put("Bob Creat", "B - O - B - C - Re - At\nBoron - Oxygen - Boron - Carbon - Rhenium - Astatine\n");
        trueCache.put("Loopy Creat", "Could not create name Loopy Creat out of elements.\n");
        trueCache.put("Tsar Bomba", "Could not create name Tsar Bomba out of elements.\n");
        trueCache.put("Tsar Boo", "Ts - Ar - B - O - O\nTennessine - Argon - Boron - Oxygen - Oxygen\n");
        trueCache.put("Tsar Tsar", "Ts - Ar - Ts - Ar\nTennessine - Argon - Tennessine - Argon\n");

        for (Map.Entry<String, String> entry : trueCache.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            assertEquals(value, _del.readLine(key));
        }
    }

    //Check and make sure all of the test cases for the program work with a cache!
    @Test
    public void testConvert_005() {
        //Populate the elements
        _del.populateElems();
        _del.populateCache();

        //Create our own hashmap to test against
        HashMap<String, String> trueCache = new HashMap<String, String>(256);
        trueCache.put("John Jacob Jingleheimer Schmidt", "Could not create name John Jacob Jingleheimer Schmidt out of elements.\n");
        trueCache.put("Lalalalalalalalalala", "La - La - La - La - La - La - La - La - La - La\nLanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum\n");
        trueCache.put("Laboon", "La - B - O - O - N\nLanthanum - Boron - Oxygen - Oxygen - Nitrogen\n");
        trueCache.put("Art Arterson", "Could not create name Art Arterson out of elements.\n");
        trueCache.put("Bob", "B - O - B\nBoron - Oxygen - Boron\n");
        trueCache.put("Coco Atas", "C - O - C - O - At - As\nCarbon - Oxygen - Carbon - Oxygen - Astatine - Arsenic\n");
        trueCache.put("Bob Bobber", "B - O - B - B - O - B - B - Er\nBoron - Oxygen - Boron - Boron - Oxygen - Boron - Boron - Erbium\n");
        trueCache.put("Bob Bobberson", "B - O - B - B - O - B - B - Er - S - O - N\nBoron - Oxygen - Boron - Boron - Oxygen - Boron - Boron - Erbium - Sulfur - Oxygen - Nitrogen\n");
        trueCache.put("Al Bobberson", "Al - B - O - B - B - Er - S - O - N\nAluminum - Boron - Oxygen - Boron - Boron - Erbium - Sulfur - Oxygen - Nitrogen\n");
        trueCache.put("Al Allerson", "Could not create name Al Allerson out of elements.\n");
        trueCache.put("Al Allison", "Al - Al - Li - S - O - N\nAluminum - Aluminum - Lithium - Sulfur - Oxygen - Nitrogen\n");
        trueCache.put("Allison Allisons", "Al - Li - S - O - N - Al - Li - S - O - N - S\nAluminum - Lithium - Sulfur - Oxygen - Nitrogen - Aluminum - Lithium - Sulfur - Oxygen - Nitrogen - Sulfur\n");
        trueCache.put("Laboons ", "La - B - O - O - N - S\nLanthanum - Boron - Oxygen - Oxygen - Nitrogen - Sulfur\n");
        trueCache.put("Bo Bo", "B - O - B - O\nBoron - Oxygen - Boron - Oxygen\n");
        trueCache.put("Bo Bo Bo", "B - O - B - O - B - O\nBoron - Oxygen - Boron - Oxygen - Boron - Oxygen\n");
        trueCache.put("Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo", "B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O\nBoron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen\n");
        trueCache.put("Bob Powers", "B - O - B - P - O - W - Er - S\nBoron - Oxygen - Boron - Phosphorus - Oxygen - Tungsten - Erbium - Sulfur\n");
        trueCache.put("Tiny Powers", "Ti - N - Y - P - O - W - Er - S\nTitanium - Nitrogen - Yttrium - Phosphorus - Oxygen - Tungsten - Erbium - Sulfur\n");
        trueCache.put("Pow Powerson", "P - O - W - P - O - W - Er - S - O - N\nPhosphorus - Oxygen - Tungsten - Phosphorus - Oxygen - Tungsten - Erbium - Sulfur - Oxygen - Nitrogen\n");
        trueCache.put("Nick Nickelback", "Could not create name Nick Nickelback out of elements.\n");
        trueCache.put("Nick Powers", "N - I - C - K - P - O - W - Er - S\nNitrogen - Iodine - Carbon - Potassium - Phosphorus - Oxygen - Tungsten - Erbium - Sulfur\n");
        trueCache.put("Nick Atas", "N - I - C - K - At - As\nNitrogen - Iodine - Carbon - Potassium - Astatine - Arsenic\n");
        trueCache.put("Tiny Nick", "Ti - N - Y - N - I - C - K\nTitanium - Nitrogen - Yttrium - Nitrogen - Iodine - Carbon - Potassium\n");
        trueCache.put("Bob Creat", "B - O - B - C - Re - At\nBoron - Oxygen - Boron - Carbon - Rhenium - Astatine\n");
        trueCache.put("Loopy Creat", "Could not create name Loopy Creat out of elements.\n");
        trueCache.put("Tsar Bomba", "Could not create name Tsar Bomba out of elements.\n");
        trueCache.put("Tsar Boo", "Ts - Ar - B - O - O\nTennessine - Argon - Boron - Oxygen - Oxygen\n");
        trueCache.put("Tsar Tsar", "Ts - Ar - Ts - Ar\nTennessine - Argon - Tennessine - Argon\n");

        for (Map.Entry<String, String> entry : trueCache.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            assertEquals(value, _del.readLine(key));
        }
    }

    //Test and make sure the main function will return an error if no args are passed in
    @Test
    public void testMain_000()throws InterruptedException, ExecutionException {
        _del.main(new String[] {});
        assertEquals("Incorrect number of arguments. Please provide exactly one argument.\n", systemOutRule.getLog());
    }

    //Test and make sure that the main function will catch a file not found exception
    @Test
    public void testMain_001()throws InterruptedException, ExecutionException {
        _del.main(new String[] {""});
        assertEquals("File not found.\n", systemOutRule.getLog());
    }

    //Test and make sure that an empty file will be processes and not error
    @Test
    public void testMain_002()throws InterruptedException, ExecutionException {
        _del.main(new String[] {"empty.txt"});
        assertEquals("", systemOutRule.getLog());
    }

    //Test and make sure that test file lala.txt works as expected
    @Test
    public void testMain_003()throws InterruptedException, ExecutionException {
        _del.main(new String[] {"sample_files/lala.txt"});
        assertEquals("La - La - Mc - B - O - O\nLanthanum - Lanthanum - Moscovium - Boron - Oxygen - Oxygen\nLa - La - Ra\nLanthanum - Lanthanum - Radium\n", systemOutRule.getLog());
    }

    //Test and make sure that test file noogie.txt works as expected
    @Test
    public void testMain_004()throws InterruptedException, ExecutionException {
        _del.main(new String[] {"sample_files/noogie.txt"});
        assertEquals("La - B - O - O - N\nLanthanum - Boron - Oxygen - Oxygen - Nitrogen\nLa - B - O - O - N\nLanthanum - Boron - Oxygen - Oxygen - Nitrogen\nAl - Li - S - O - N\nAluminum - Lithium - Sulfur - Oxygen - Nitrogen\nB - O - O\nBoron - Oxygen - Oxygen\nCould not create name Bill out of elements.\n", systemOutRule.getLog());
    }

    //Test and make sure that test file tsars.txt works as expected
    @Test
    public void testMain_005()throws InterruptedException, ExecutionException {
        _del.main(new String[] {"sample_files/tsars.txt"});
        assertEquals("Could not create name Tsar Meow out of elements.\nTs - Ar - Ra\nTennessine - Argon - Radium\nTs - Ar - N - I - C - H - O - La - S\nTennessine - Argon - Nitrogen - Iodine - Carbon - Hydrogen - Oxygen - Lanthanum - Sulfur\n", systemOutRule.getLog());
    }

    */
}
