import java.util.*;

public class BTreePrinter {

    public static <T extends Comparable<?>> void printBSTnode(BSTnode<T> root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printBSTnodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printBSTnodeInternal(List<BSTnode<T>> BSTnodes, int level, int maxLevel) {
        if (BSTnodes.isEmpty() || BTreePrinter.isAllElementsNull(BSTnodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<BSTnode<T>> newBSTnodes = new ArrayList<BSTnode<T>>();
        for (BSTnode<T> BSTnode : BSTnodes) {
            if (BSTnode != null) {
                System.out.print(BSTnode.getData());
                newBSTnodes.add(BSTnode.left);
                newBSTnodes.add(BSTnode.right);
            } else {
                newBSTnodes.add(null);
                newBSTnodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < BSTnodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (BSTnodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (BSTnodes.get(j).left != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (BSTnodes.get(j).right != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printBSTnodeInternal(newBSTnodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(BSTnode<T> BSTnode) {
        if (BSTnode == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(BSTnode.left), BTreePrinter.maxLevel(BSTnode.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}