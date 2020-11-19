package BinarySearchTree;

import BinarySearchTree.printer.BinaryTrees;

public class TestMain {

    public static void main(String[] args) {
        int[] data = { 7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
//
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinarySearchTree<Integer> bst1 = new BinarySearchTree<Integer>();
        for (int i = 0; i < 30; i++) {
            bst1.add((int) (Math.random() * 100));
        }
        BinaryTrees.println(bst);
//        bst.levelOrderTraversal();
        int num = bst.height();
        System.out.println(num);
        System.out.println(bst.isComplete());
        bst.remove(1);
        bst.remove(12);
        BinaryTrees.println(bst);
        bst.clear();
        BinaryTrees.println(bst);
        System.out.println(bst.size());

//        bst.levelOrder(new BinarySearchTree.Visitor<Integer>() {
//            @Override
//            public void visit(Integer element) {
//                System.out.print("_"+element+"_");
//            }
//        });
//        BinarySearchTree<Person> bst2 = new BinarySearchTree<Person>();
//        bst2.add(new Person(12));
//        bst2.add(new Person(15));
//
//        BinarySearchTree<Person> bst3 = new BinarySearchTree<Person>();
//        bst2.add(new Person(12));
//        bst2.add(new Person(15));
//        BinaryTrees.println(bst2);
    }

//    private static class PersonComparator implements Comparator<Person> {
//        @Override
//        public int compare(Person e1, Person e2) {
//            return e1.getAge() - e2.getAge();
//        }
//    }
//    private static class PersonComparator2 implements Comparator<Person> {
//        @Override
//        public int compare(Person e1, Person e2) {
//            return e2.getAge() - e1.getAge();
//        }
//    }
}
