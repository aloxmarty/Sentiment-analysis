import java.util.ArrayList;
import java.util.List;

public class BSTTester {
    public static void main(String[] args) {
        //testPutAndGet1();
        //testPutAndGet2();
        //testheight();
        //testpreorder();
        //testpostorder();
        //testinorder();
        //testsize();
        //testcontains();
        testremove();
        //testTraversals1();
        //testTraversals2();
        // Add more testing code to test things like
        // - traversals/size/height for deeper or more complex trees
        // - containsKey, remove
        // - putting a duplicate key value (should replace the old value with the new one)
    }

    private static void testremove() {
        System.out.println("Testing contains");
        BSTMap<Integer, Double> accounts = new BSTMap<Integer, Double>();
        accounts.put(123, 50.23);
        accounts.put(124, 100.81);
        accounts.put(300, 100.81);
        accounts.put(78, 72.11);
        accounts.put(79, 100.81);
        accounts.put(3, 100.81);
        accounts.put(7, 72.11);
        accounts.put(9, 100.81);
        System.out.println(accounts.inorderKeys());
        accounts.remove(300);
        System.out.println(accounts.inorderKeys());
    }

    private static void testcontains() {
        System.out.println("Testing contains");
        BSTMap<Integer, Double> accounts = new BSTMap<Integer, Double>();
        accounts.put(123, 50.23);
        accounts.put(456, 100.81);
        accounts.put(78, 72.11);
        System.out.println(accounts.containsKey(4567));
        accounts.put(12, 50.23);
        accounts.put(45, 100.81);
        accounts.put(7, 72.11);
        System.out.println(accounts.containsKey(7));
        accounts.put(13, 50.23);
        accounts.put(4, 100.81);
        accounts.put(718, 72.11);
        System.out.println(accounts.containsKey(4));
    }

    private static void testsize() {
        System.out.println("Testing size");
        BSTMap<Integer, Double> accounts = new BSTMap<Integer, Double>();
        accounts.put(123, 50.23);
        accounts.put(456, 100.81);
        accounts.put(78, 72.11);
        accounts.put(12, 50.23);
        accounts.put(45, 100.81);
        accounts.put(7, 72.11);
        accounts.put(13, 50.23);
        accounts.put(4, 100.81);
        accounts.put(718, 72.11);
        System.out.println(accounts.size());
    }

    private static void testinorder() {
        System.out.println("Testing inorder");
        BSTMap<Integer, Double> accounts = new BSTMap<Integer, Double>();
        accounts.put(123, 50.23);
        accounts.put(456, 100.81);
        accounts.put(78, 72.11);
        accounts.put(12, 50.23);
        accounts.put(45, 100.81);
        accounts.put(7, 72.11);
        accounts.put(13, 50.23);
        accounts.put(4, 100.81);
        accounts.put(718, 72.11);
        System.out.println(accounts.inorderKeys());
    }

    private static void testpostorder() {
        System.out.println("Testing postorder");
        BSTMap<Integer, Double> accounts = new BSTMap<Integer, Double>();
        accounts.put(123, 50.23);
        accounts.put(456, 100.81);
        accounts.put(78, 72.11);
        accounts.put(12, 50.23);
        accounts.put(45, 100.81);
        accounts.put(7, 72.11);
        accounts.put(13, 50.23);
        accounts.put(4, 100.81);
        accounts.put(718, 72.11);
        System.out.println(accounts.postorderKeys());
    }

    private static void testpreorder() {
        System.out.println("Testing preorder");
        BSTMap<Integer, Double> accounts = new BSTMap<Integer, Double>();
        accounts.put(123, 50.23);
        accounts.put(456, 100.81);
        accounts.put(78, 72.11);
        accounts.put(12, 50.23);
        accounts.put(45, 100.81);
        accounts.put(7, 72.11);
        accounts.put(13, 50.23);
        accounts.put(4, 100.81);
        accounts.put(718, 72.11);
        System.out.println(accounts.preorderKeys());
    }

    private static void testheight() {
        System.out.println("Testing height");
        BSTMap<Integer, Double> accounts = new BSTMap<Integer, Double>();
        System.out.println("Height: " + accounts.height());
        accounts.put(123, 50.23);
        System.out.println("Height: " + accounts.height());
        accounts.put(456, 100.81);
        System.out.println("Height: " + accounts.height());
        accounts.put(78, 72.11);
        System.out.println("Height: " + accounts.height());

    }

    // One put and one get per map (tree won't be deeper than the root).
    private static void testPutAndGet1() {
        System.out.println("Testing put/get 1");
        // Simulate a bank account map.
        BSTMap<Integer, Double> accounts = new BSTMap<Integer, Double>();
        accounts.put(123, 50.23);
        System.out.println("Get: " + accounts.get(123)); // get the balance for account 123

        // Simulate an address book.
        BSTMap<String, String> emails = new BSTMap<String, String>();
        emails.put("Elvis Presley", "elvis@graceland.com");
        System.out.println("Get: " + emails.get("Elvis Presley")); // get Elvis's email address
    }

    // Multiple puts and gets, testing making deeper trees.
    private static void testPutAndGet2() {
        System.out.println("Testing put/get 2");
        BSTMap<Integer, Double> accounts = new BSTMap<Integer, Double>();
        accounts.put(123, 50.23);
        accounts.put(456, 100.81);
        accounts.put(78, 72.11);
        System.out.println("Get: " + accounts.get(123));
        System.out.println("Get: " + accounts.get(456));
        System.out.println("Get: " + accounts.get(78));
        System.out.println(accounts.size());

        BSTMap<String, String> emails = new BSTMap<String, String>();
        emails.put("Elvis Presley", "elvis@graceland.com");
        emails.put("Albus Dumbledore", "dumbledore@hogwarts.edu");
        emails.put("Dorothy Gale", "dorothy@oz.org");
        emails.put("Grace Hopper", "hopper@navy.mil");  // look her up!
        System.out.println("Get: " + emails.get("Elvis Presley"));
        System.out.println("Get: " + emails.get("Albus Dumbledore"));
        System.out.println("Get: " + emails.get("Dorothy Gale"));
        System.out.println("Get: " + emails.get("Grace Hopper"));
        System.out.println(emails.size());
    }

    private static void testTraversals1() {
        System.out.println("Testing traversals 1");
        BSTMap<Integer, Integer> map = createBst(List.of(4, 2, 6, 1, 3, 5, 7));
        System.out.println("Preorder:  " + map.preorderKeys());
        System.out.println("Inorder:   " + map.inorderKeys());
        System.out.println("Postorder: " + map.postorderKeys());
        System.out.println("size=" + map.size() + " height=" + map.height());
    }

    private static void testTraversals2() {
        System.out.println("Testing traversals 2");
        BSTMap<Integer, Integer> map = createBst(List.of(1, 2, 3, 4, 5, 6, 7));
        System.out.println("Preorder:  " + map.preorderKeys());
        System.out.println("Inorder:   " + map.inorderKeys());
        System.out.println("Postorder: " + map.postorderKeys());
        System.out.println("size=" + map.size() + " height=" + map.height());
    }

    /**
     * Helper method to create a BST map from the numbers in a list.  It just uses zero
     * for every value, since this is mostly used to test the traversal methods.
     */
    private static BSTMap<Integer, Integer> createBst(List<Integer> list) {
        BSTMap<Integer, Integer> map = new BSTMap<>();
        for (int x = 0; x < list.size(); x++)
        {
            map.put(list.get(x), 0);
        }
        return map;
    }
}
