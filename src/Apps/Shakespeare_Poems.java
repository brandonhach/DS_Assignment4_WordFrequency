package Apps;

import bridges.connect.Bridges;
import bridges.data_src_dependent.Shakespeare;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.didion.jwnl.JWNL;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.dictionary.Dictionary;
import net.didion.jwnl.data.POS;
import viz.WordBSTDictionary;
import viz.FreqWordMaxBTree;
import viz.StandardDictionary;

public class Shakespeare_Poems {
    final static int FREQUENCY_THRESHOLD = 150;

    public static String[] splitLyrics(String lyrics) { // splits raw lyrics string into a parsable array
        lyrics = lyrics.replaceAll("\\[.+\\]", ""); // removes the titles of song stage ex [Intro]
        lyrics = lyrics.trim();
        String[] lyricsSplit = lyrics.split("\\s+");

        for (int i = 0; i < lyricsSplit.length; i++) { // clears special characters from individual terms
            lyricsSplit[i] = lyricsSplit[i].replaceAll("\\W+$", "");
            lyricsSplit[i] = lyricsSplit[i].replaceAll("^\\W+", "");
            lyricsSplit[i] = lyricsSplit[i].trim();
        }

        return lyricsSplit;
    }

    public static boolean wordOfInterest(String word) {
        try {
            IndexWord fword = null;
            Dictionary dict = Dictionary.getInstance();
            fword = dict.lookupIndexWord(POS.NOUN, word);
            if (fword != null)
                return true;
            fword = dict.lookupIndexWord(POS.ADJECTIVE, word);
            if (fword != null)
                return true;
            fword = dict.lookupIndexWord(POS.ADVERB, word);
            if (fword != null)
                return true;
            fword = dict.lookupIndexWord(POS.VERB, word);
            if (fword != null)
                return true;
        } catch (Exception ex) {
            return false;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        /*
         * try {
         * // initialize JWNL (this must be done before JWNL can be used)
         * JWNL.initialize(new FileInputStream("./config/file_properties.xml"));
         * } catch (Exception ex) {
         * ex.printStackTrace();
         * System.exit(-1);
         * }
         */
        // Initialize a Bridges connection with your credentials
        // Bridges bridges = new Bridges(17, "BRIDGES_USER_ID", "BRIDGES_API_KEY");
        Bridges bridges = new Bridges(17, "tricha64", "434127810532");

        // Set an assignment title
        bridges.setTitle("Most Frequent Shakespeare Words");
        bridges.setDescription("Identify those words that appeared in Shakespeare words for more than 100 times.");

        // StandardDictionary<String, Integer> my_dict = new StandardDictionary<String,
        // Integer>();

        // Build the binary-search-tree-based dictioary by reading all words and
        // counting the occurrece of each word
        WordBSTDictionary<String, Integer> my_dict = new WordBSTDictionary<>();

        // Read Shakespear poes
        List<Shakespeare> shksp_list = bridges.getDataSource().getShakespeareData("poems", true);// "poems",
                                                                                                 // true);//"plays",
                                                                                                 // "sonnets"
        for (Shakespeare po1 : shksp_list) {
            // Shakespeare po1 = shksp_list.get(0);
            // Tokenize
            String[] wordlist = splitLyrics(po1.getText());

            // Insert each word to the binary-search-tree-based dictioary
            for (String word : wordlist) {
                // if (wordOfInterest(word))
                {
                    Integer freq = my_dict.get(word);
                    my_dict.set(word, freq == null ? 1 : new Integer(freq.intValue() + 1));
                }
            }
        }

        // System.out.println(my_dict.size());

        // convert WordBSTDictionary to FreqWordheap
        FreqWordMaxBTree<Integer, String> freq_word_heap;
        freq_word_heap = new FreqWordMaxBTree<>();
        for (Iterator<Map.Entry<String, Integer>> it = my_dict.iterator(); it.hasNext();) {
            Map.Entry<String, Integer> e = it.next();

            if (e.getValue() > FREQUENCY_THRESHOLD)
                freq_word_heap.insert(e.getValue(), e.getKey());
        }
        freq_word_heap.visualize(bridges);

        for (Iterator<Map.Entry<Integer, String>> it = freq_word_heap.iterator(); it.hasNext();) {
            Map.Entry<Integer, String> e = it.next();
            System.out.println(e.getKey() + " " + e.getValue());
            // String words[] = e.getValue().split(", ");
            // for (String word: words)
            // System.out.println(word);
        }
    }
}
