import java.util.*;
import java.util.stream.*;

/**
 * Class for pairwise.
 */
public class Pairwise {

    private ArrayList<ArrayList<Boolean>> options;
    private ArrayList<String> names;

    /**
     * Constructs the object.
     *
     * @param      names  The names
     */
    public Pairwise(ArrayList<String> names) {
        this.names = names; 
    }

    /**
     * generate and minimuze pairwise testing based on this object's stored names
     * 
     * @return     The minimized options
     */
    public ArrayList<ArrayList<Boolean>> generateMinimized() {
        this.options = genBruteforce(this.names.size());
        this.options = minimize(this.options);
        return this.options;
    }

    /**
     * entry point for recursive truthtable generation
     *
     * @param      n     number of options
     */
    public ArrayList<ArrayList<Boolean>> genBruteforce(int n) {
        return this.genBruteforce(new ArrayList<Boolean>(), n);
    }

    /**
     * recursive function for truthtable generation
     *
     * @param      prog  current generation progress
     * @param      rem   remaining parameters
     */
    private ArrayList<ArrayList<Boolean>> genBruteforce(ArrayList<Boolean> prog, int rem) {
        if (rem == 0) {
            ArrayList<ArrayList<Boolean>> progList = new ArrayList<ArrayList<Boolean>>();
            progList.add(prog);
            return progList;
        }

        ArrayList<Boolean> zero = new ArrayList<Boolean>();
        zero.add(false);
        zero.addAll(prog);
        ArrayList<Boolean> one = new ArrayList<Boolean>();
        one.add(true);
        one.addAll(prog);

        ArrayList<ArrayList<Boolean>> all = new ArrayList<ArrayList<Boolean>>();
        all.addAll(this.genBruteforce(zero, rem - 1));
        all.addAll(this.genBruteforce(one, rem - 1));
        return all;
    }

    /**
     * minimize a list of options to a n=2 covering array
     *
     * @param      options  starting set of options
     */
    public ArrayList<ArrayList<Boolean>> minimize(ArrayList<ArrayList<Boolean>> options) {
        ArrayList<ArrayList<Boolean>> minimized = new ArrayList<ArrayList<Boolean>>();
        minimized.add(options.get(0));
        minimized.add(options.get(options.size() - 1));

        ArrayList<ArrayList<Boolean>> bruteforcePair = this.genBruteforce(2);

        for (ArrayList<Integer> pair : this.genPairs(options.get(0).size())) {
            for (ArrayList<Boolean> bruteforce : bruteforcePair) {

                // check if pair is already captured in minified list
                boolean breakNow = false;
                for (ArrayList<Boolean> min : minimized) {
                    if (min.get(pair.get(0)) == bruteforce.get(0) && min.get(pair.get(1)) == bruteforce.get(1)) {
                        breakNow = true;
                        break;
                    }
                }
                if (breakNow) {
                    continue;
                }

                // find line that captures current pair
                for (ArrayList<Boolean> option : options) {
                    if (option.get(pair.get(0)) == bruteforce.get(0) && option.get(pair.get(1)) == bruteforce.get(1)) {
                        minimized.add(option);
                        break;
                    }
                }
            }
        }

        return minimized;
    }

    /**
     * Pretty print this object's data
     *
     * @return     returns the pretty print string
     */
    public String prettify() {
        return this.prettify(this.names, this.options);
    }

    /**
     * generic pretty print
     *
     * @param      names    headers
     * @param      options  values to print
     */
    public String prettify(ArrayList<String> names, ArrayList<ArrayList<Boolean>> options) {
        int maxLength = 0;
        for (String name : names) {
            if (name.length() > maxLength) {
                maxLength = name.length();
            }
        }
        maxLength += 1;

        if (maxLength > 11) {
            maxLength = 11;
        }

        StringBuilder retStr = new StringBuilder();

        // print all names
        for (String name : names) {
            retStr.append(String.format("%1$-" + maxLength + "s", name.substring(0, Math.min(name.length(), 10))));
        }
        retStr.append("\n");

        for (ArrayList<Boolean> row : options) {
            for (Boolean val : row) {
                retStr.append(String.format("%1$-" + maxLength + "s", val ? "1" : "0"));
            }
            retStr.append("\n");
        }

        return retStr.toString();
    }

    /**
     * generate combinatorix pairs or two for n items
     *
     * @param      n     number of items for generate pairs for
     */
    public ArrayList<ArrayList<Integer>> genPairs(int n) {
        int k = 2;

        ArrayList<ArrayList<Integer>> subsets = new ArrayList<ArrayList<Integer>>();

        int[] s = new int[k];                  // here we'll keep indices

        // first index sequence: 0, 1, 2, ...
        for (int i = 0; i < k - 1; i++) {
            s[i] = i;
        }

        subsets.add((ArrayList) Arrays.stream(s).boxed().collect(Collectors.toList()));
        while (true) {
            int i;
            // find position of item that can be incremented
            i = k - 1;
            while (i >= 0 && s[i] == n - k + i) {
                i--;
            }
            if (i < 0) {
                break;
            }
            s[i]++;                    // increment this item
            for (++i; i < k; i++) {    // fill up remaining items
                s[i] = s[i - 1] + 1;
            }
            subsets.add((ArrayList) Arrays.stream(s).boxed().collect(Collectors.toList()));
        }

        return subsets;
    }

    /**
     * main function - run the file
     *
     * @param      args  The arguments
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please enter at least two parameters!");
            System.exit(1);
        }
        Pairwise asdf = new Pairwise(new ArrayList<String>(Arrays.asList(args)));
        asdf.generateMinimized();
        System.out.println(asdf.prettify());
    }
}