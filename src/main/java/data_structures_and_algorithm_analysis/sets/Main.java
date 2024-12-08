package data_structures_and_algorithm_analysis.sets;

public class Main {

    public static void main(String[] args) {
        DisjointSet disjointSet = new DisjointSet(15);

        disjointSet.union(0,1);
        disjointSet.union(1,2);
        disjointSet.union(0,3);
        disjointSet.union(10,9);
        disjointSet.union(10,11);
        disjointSet.union(12,13);
        disjointSet.union(11,13);
        disjointSet.union(14,13);

        disjointSet.printSets();
    }
}
