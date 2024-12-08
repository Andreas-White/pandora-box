package data_structures_and_algorithm_analysis.sets;

public class DisjointSet {

    private final int[] sets;

    public DisjointSet(int setsSize) {
        sets = new int[setsSize];
        for (int i = 0; i < setsSize; i++) {
            sets[i] = -1;
        }
    }

    public int find(int node) {
//        if (sets[node] < 0)
//            return node;
//
//        return sets[node] = find(sets[node]);
        while (sets[node] >= 0) {
            node = sets[node];
        }

        return node;
    }

    public void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);

        if(sets[root2] < sets[root1])       // root2 is deeper
            sets[root1] = root2;            // Make root2 new root
        else {
            if(sets[root1] == sets[root2])
                 sets[root1]--;             // Update height if same
            sets[root2] = root1;            // Make root1 new root
        }
    }

    public void printSets() {
        for (int i = 0; i < sets.length; i++) {
            if (i == find(i))
                System.out.printf("%d is Root with size: %d\n", i, Math.abs(sets[i]));
            else System.out.printf("Root of %d: %d\n", i, find(i));
        }
    }
}
