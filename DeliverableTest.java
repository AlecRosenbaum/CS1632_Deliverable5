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
        ArrayList<String> al = new ArrayList<String>();
        _pair = new Pairwise(al);
    }


    // Assert that creating a new Pairwise instance does not return null
    @Test
    public void testPairwiseExists() {
        assertNotNull(_pair);
    }

    //This allows us to read directly from console
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();


    //Test and make sure the main function will return an error if no args are passed in
    @Test
    public void testMain_000()throws InterruptedException, ExecutionException {
        _pair.main(new String[] {});
        assertEquals("Please enter at least two parameters!\n", systemOutRule.getLog());
    }
    
    //Test and make sure the main function will return an error if only one arg is passed in
    @Test
    public void testMain_001()throws InterruptedException, ExecutionException {
        _pair.main(new String[] {"Zoom"});
        assertEquals("Please enter at least two parameters!\n", systemOutRule.getLog());
    }   

    //Test and make sure that the main function will output the proper text for an input of size 2
    @Test
    public void testMain_002()throws InterruptedException, ExecutionException {
        _pair.main(new String[] {"foo", "bar"});
        assertEquals("foo bar \n0   0   \n1   1   \n1   0   \n0   1   \n\n", systemOutRule.getLog());
    }

    //Test and make sure that the main function will output the proper text for an input of size 2 and retain the order of inputs!
    @Test
    public void testMain_003()throws InterruptedException, ExecutionException {
        _pair.main(new String[] {"bar", "foo"});
        assertEquals("bar foo \n0   0   \n1   1   \n1   0   \n0   1   \n\n", systemOutRule.getLog());
    }

    //Test and make sure that the main function will output the proper text for an input of size 3
    @Test
    public void testMain_004()throws InterruptedException, ExecutionException {
        _pair.main(new String[] {"wow", "baz", "fun"});
        assertEquals("wow baz fun \n0   0   0   \n1   1   1   \n1   0   0   \n0   1   0   \n0   0   1   \n\n", systemOutRule.getLog());
    }

    //Test and make sure that the main function will output the proper text for an input of size 4
    @Test
    public void testMain_005()throws InterruptedException, ExecutionException {
        _pair.main(new String[] {"foo", "baz", "wow", "wacky"});
        assertEquals("foo   baz   wow   wacky \n0     0     0     0     \n1     1     1     1     \n1     0     0     0     \n0     1     0     0     \n0     0     1     0     \n0     0     0     1     \n\n", systemOutRule.getLog());
    }

    //Test and make sure that the Gen Pairs method will return the expected 
    //arraylist<arraylist<Integer>> for an input size of 2
    @Test
    public void testGenPairs_000(){
        ArrayList<ArrayList<Integer>> subsets = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(0);
        list.add(0);
        subsets.add(list);
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list2.add(0);
        list2.add(1);
        subsets.add(list2);

        assertEquals(subsets, _pair.genPairs(2));
    }

    //Test and make sure that the Prettify method will return the expected 
    //string with a spacing size of 3 for each of the input names
    @Test
    public void testPrettify_000(){
        ArrayList<String> names = new ArrayList<String>();
        names.add("foo");
        names.add("baz");
        ArrayList<ArrayList<Boolean>> options = new ArrayList<ArrayList<Boolean>>();

        ArrayList<Boolean> list = new ArrayList<Boolean>();
        list.add(false);
        list.add(false);
        options.add(list);
        ArrayList<Boolean> list2 = new ArrayList<Boolean>();
        list2.add(false);
        list2.add(true);
        options.add(list2);

        assertEquals("foo baz \n0   0   \n0   1   \n", _pair.prettify(names,options));
    }


    //Test and make sure that the Prettify method will return the expected 
    //string with a spacing size of 4 for each of the input names
    @Test
    public void testPrettify_001(){
        ArrayList<String> names = new ArrayList<String>();
        names.add("zoom");
        names.add("fast");
        ArrayList<ArrayList<Boolean>> options = new ArrayList<ArrayList<Boolean>>();

        ArrayList<Boolean> list = new ArrayList<Boolean>();
        list.add(false);
        list.add(false);
        options.add(list);
        ArrayList<Boolean> list2 = new ArrayList<Boolean>();
        list2.add(false);
        list2.add(true);
        options.add(list2);

        assertEquals("zoom fast \n0    0    \n0    1    \n", _pair.prettify(names,options));
    }

    //Test and make sure that the Minomoze method will return the expected 
    //arraylist<arraylist<boolean>> list which is optimal pattern for size 3
    @Test
    public void testMinimize_000(){

        ArrayList<ArrayList<Boolean>> options = new ArrayList<ArrayList<Boolean>>();

        ArrayList<Boolean> list = new ArrayList<Boolean>();
        list.add(false);
        list.add(false);        
        list.add(false);
        options.add(list);
        ArrayList<Boolean> list2 = new ArrayList<Boolean>();
        list2.add(false);
        list2.add(false);
        list2.add(true);
        options.add(list2);        
        ArrayList<Boolean> list3 = new ArrayList<Boolean>();
        list3.add(false);
        list3.add(true);        
        list3.add(false);
        options.add(list3);
        ArrayList<Boolean> list4 = new ArrayList<Boolean>();
        list4.add(false);
        list4.add(true);
        list4.add(true);
        options.add(list4);
        ArrayList<Boolean> list5 = new ArrayList<Boolean>();
        list5.add(true);
        list5.add(false);        
        list5.add(false);
        options.add(list5);
        ArrayList<Boolean> list6 = new ArrayList<Boolean>();
        list6.add(true);
        list6.add(false);
        list6.add(true);
        options.add(list6);
        ArrayList<Boolean> list7 = new ArrayList<Boolean>();
        list7.add(true);
        list7.add(true);        
        list7.add(false);
        options.add(list7);
        ArrayList<Boolean> list8 = new ArrayList<Boolean>();
        list8.add(true);
        list8.add(true);
        list8.add(true);
        options.add(list8);

        ArrayList<ArrayList<Boolean>> results = new ArrayList<ArrayList<Boolean>>();
        results.add(list);
        results.add(list8);
        results.add(list5);
        results.add(list3);
        results.add(list2);       

        assertEquals(results, _pair.minimize(options));
    }

    //Test and make sure that the Generate Brute Force method will return the expected 
    //arraylist<arraylist<boolean>> list which is the base truth table for size 3
    @Test
    public void testGenBruteforce_000(){

        ArrayList<ArrayList<Boolean>> options = new ArrayList<ArrayList<Boolean>>();

        ArrayList<Boolean> list = new ArrayList<Boolean>();
        list.add(false);
        list.add(false);        
        list.add(false);
        options.add(list);
        ArrayList<Boolean> list5 = new ArrayList<Boolean>();
        list5.add(true);
        list5.add(false);        
        list5.add(false);
        options.add(list5);
        ArrayList<Boolean> list3 = new ArrayList<Boolean>();
        list3.add(false);
        list3.add(true);        
        list3.add(false);
        options.add(list3);
        ArrayList<Boolean> list7 = new ArrayList<Boolean>();
        list7.add(true);
        list7.add(true);        
        list7.add(false);
        options.add(list7);
        ArrayList<Boolean> list2 = new ArrayList<Boolean>();
        list2.add(false);
        list2.add(false);
        list2.add(true);
        options.add(list2);        
        ArrayList<Boolean> list6 = new ArrayList<Boolean>();
        list6.add(true);
        list6.add(false);
        list6.add(true);
        options.add(list6);
        ArrayList<Boolean> list4 = new ArrayList<Boolean>();
        list4.add(false);
        list4.add(true);
        list4.add(true);
        options.add(list4);
        ArrayList<Boolean> list8 = new ArrayList<Boolean>();
        list8.add(true);
        list8.add(true);
        list8.add(true);
        options.add(list8);       

        assertEquals(options, _pair.genBruteforce(3));
    }

    //Test and make sure that the Generate Brute Force method will return the expected 
    //arraylist<arraylist<boolean>> list which is the base truth table for size 2
    @Test
    public void testGenBruteforce_001(){

        ArrayList<ArrayList<Boolean>> options = new ArrayList<ArrayList<Boolean>>();

        ArrayList<Boolean> list = new ArrayList<Boolean>();
        list.add(false);
        list.add(false);        
        options.add(list);
        ArrayList<Boolean> list5 = new ArrayList<Boolean>();
        list5.add(true);
        list5.add(false);        
        options.add(list5);
        ArrayList<Boolean> list3 = new ArrayList<Boolean>();
        list3.add(false);
        list3.add(true);        
        options.add(list3);
        ArrayList<Boolean> list7 = new ArrayList<Boolean>();
        list7.add(true);
        list7.add(true);        
        options.add(list7);
         

        assertEquals(options, _pair.genBruteforce(2));
    }

    //Test and make sure that the Generate Minimized method will return the expected 
    //arraylist<arraylist<boolean>> list which is optimized for a input of size 2
    @Test
    public void testGenerateMinimized_000(){

        ArrayList<ArrayList<Boolean>> options = new ArrayList<ArrayList<Boolean>>();

        ArrayList<Boolean> list = new ArrayList<Boolean>();
        list.add(false);
        list.add(false);        
        options.add(list);
        ArrayList<Boolean> list7 = new ArrayList<Boolean>();
        list7.add(true);
        list7.add(true);        
        options.add(list7);
        ArrayList<Boolean> list5 = new ArrayList<Boolean>();
        list5.add(true);
        list5.add(false);        
        options.add(list5);
        ArrayList<Boolean> list3 = new ArrayList<Boolean>();
        list3.add(false);
        list3.add(true);        
        options.add(list3);

         
        ArrayList<String> al = new ArrayList<String>();
        al.add("Foo");
        al.add("Bar");
        Pairwise thePair = new Pairwise(al);

        assertEquals(options, thePair.generateMinimized());
    }

    //Test and make sure that the Generate Minimized method will return the expected 
    //arraylist<arraylist<boolean>> list which is optimized for a input of size 3
    @Test
    public void testGenerateMinimized_001(){

        ArrayList<ArrayList<Boolean>> options = new ArrayList<ArrayList<Boolean>>();

        ArrayList<Boolean> list = new ArrayList<Boolean>();
        list.add(false);
        list.add(false);        
        list.add(false);
        options.add(list);
        ArrayList<Boolean> list2 = new ArrayList<Boolean>();
        list2.add(false);
        list2.add(false);
        list2.add(true);
        options.add(list2);        
        ArrayList<Boolean> list3 = new ArrayList<Boolean>();
        list3.add(false);
        list3.add(true);        
        list3.add(false);
        options.add(list3);
        ArrayList<Boolean> list4 = new ArrayList<Boolean>();
        list4.add(false);
        list4.add(true);
        list4.add(true);
        options.add(list4);
        ArrayList<Boolean> list5 = new ArrayList<Boolean>();
        list5.add(true);
        list5.add(false);        
        list5.add(false);
        options.add(list5);
        ArrayList<Boolean> list6 = new ArrayList<Boolean>();
        list6.add(true);
        list6.add(false);
        list6.add(true);
        options.add(list6);
        ArrayList<Boolean> list7 = new ArrayList<Boolean>();
        list7.add(true);
        list7.add(true);        
        list7.add(false);
        options.add(list7);
        ArrayList<Boolean> list8 = new ArrayList<Boolean>();
        list8.add(true);
        list8.add(true);
        list8.add(true);
        options.add(list8);

        ArrayList<ArrayList<Boolean>> results = new ArrayList<ArrayList<Boolean>>();
        results.add(list);
        results.add(list8);
        results.add(list5);
        results.add(list3);
        results.add(list2);  

         
        ArrayList<String> al = new ArrayList<String>();
        al.add("Foo");
        al.add("Bar");
        al.add("Baz");
        Pairwise thePair = new Pairwise(al);

        assertEquals(results, thePair.generateMinimized());
    }
}
