import java.io.InputStream;
import java.util.*;


public class SentimentAnalysis {
    public static void main(String[] args) {
        final boolean PRINT_TREES = true;  // whether or not to print extra info about the maps.

        BSTMap<String, Integer> wordFreqs = new BSTMap<String, Integer>();
        BSTMap<String, Integer> wordTotalScores = new BSTMap<String, Integer>();
        Set<String> stopwords = new TreeSet<String>();

        System.out.print("Enter filename: ");
        Scanner scan = new Scanner(System.in);
        String filename = scan.nextLine();

        processFile(filename, wordFreqs, wordTotalScores);

        System.out.println("Number of words is: " + wordFreqs.size());
        System.out.println("Height of the tree is: " + wordFreqs.height());

        if (PRINT_TREES)
        {
            System.out.println("Preorder:  " + wordFreqs.preorderKeys());
            System.out.println("Inorder:   " + wordFreqs.inorderKeys());
            System.out.println("Postorder: " + wordFreqs.postorderKeys());
            printFreqsAndScores(wordFreqs, wordTotalScores);
        }

        removeStopWords(wordFreqs, wordTotalScores, stopwords);

        System.out.println("After removing stopwords:");
        System.out.println("Number of words is: " + wordFreqs.size());
        System.out.println("Height of the tree is: " + wordFreqs.height());

        if (PRINT_TREES)
        {
            System.out.println("Preorder:  " + wordFreqs.preorderKeys());
            System.out.println("Inorder:   " + wordFreqs.inorderKeys());
            System.out.println("Postorder: " + wordFreqs.postorderKeys());
            printFreqsAndScores(wordFreqs, wordTotalScores);
        }

        while (true)
        {
            System.out.print("\nEnter a new review to analyze: ");
            String line = scan.nextLine();
            if (line.equals("quit")) break;

            String[] words = line.split(" ");
            
            double total = 0;
            int num = 0;
            for (String b: words){
                // checks if word is already known and not a stop word
                if (wordTotalScores.containsKey(b)){
                    if (stopwords.contains(b)){
                        //System.out.println("Stop words contains: " + b);
                        System.out.println("Skipping " + b + " (stopword)");
                    }
                    else{
                        num++;
                        double val = wordTotalScores.get(b) / wordFreqs.get(b);
                        total += val;
                        System.out.println("The average sentiment of " + b + " is: " + val);
                    }

                }
            }
            System.out.println("Sentiment score for this review is " + (total/num));
        }
    }

    /**
     * Read the file specified to add proper items to the word frequencies and word scores maps.
     */
    private static void processFile(String filename, BSTMap<String, Integer> wordFreqs, BSTMap<String, Integer> wordTotalScores) {
        InputStream is = SentimentAnalysis.class.getResourceAsStream(filename);
        if (is == null) {
            System.err.println("Bad filename: " + filename);
            System.exit(1);
        }
        Scanner scan = new Scanner(is);

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] words = line.split(" ");

            int rating = Integer.parseInt(words[0]);

            for (int a = 1; a < words.length; a++){
                if (wordFreqs.containsKey(words[a])){
                    int val = wordFreqs.get(words[a]) + 1;
                    wordFreqs.put(words[a], val);
                }
                else{
                    wordFreqs.put(words[a], 1);
                }
                if (wordTotalScores.containsKey(words[a])){
                    int val = wordTotalScores.get(words[a]) + rating;
                    wordTotalScores.put(words[a], val);
                }
                else{
                    wordTotalScores.put(words[a], rating);
                }
            }
        }
        scan.close();
    }

    /**
     * Print a table of the words found in the movie reviews, along with their frequencies and total scores.
     * Hint: Call wordFreqs.inorderKeys() to get a list of the words, and then loop over that list.
     */
    private static void printFreqsAndScores(BSTMap<String, Integer> wordFreqs, BSTMap<String, Integer> wordTotalScores) {
        ArrayList<String> a = (ArrayList<String>) wordTotalScores.inorderKeys();
        ArrayList<String> b = (ArrayList<String>) wordFreqs.inorderKeys();


        for (String c : a){
            System.out.println("word: " + c + ", frequency: " + wordFreqs.get(c) + ", total score: "
                    + wordTotalScores.get(c));
        }


    }

    /**
     * Read the stopwords.txt file and add each word to the stopwords set.  Also remove each word from the
     * word frequencies and word scores maps.
     */
    private static void removeStopWords(BSTMap<String, Integer> wordFreqs, BSTMap<String, Integer> wordTotalScores, Set<String> stopwords) {
        InputStream is = SentimentAnalysis.class.getResourceAsStream("stopwords.txt");
        if (is == null) {
            System.err.println("Bad filename: " + "stopwords.txt");
            System.exit(1);
        }
        Scanner scan = new Scanner(is);

        while (scan.hasNextLine()) {
            String word = scan.nextLine();
            stopwords.add(word);
            wordFreqs.remove(word);
            wordTotalScores.remove(word);
        }

        scan.close();
    }
}
