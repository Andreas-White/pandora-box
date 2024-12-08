package aoc2023.day8;

import aoc2023.Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8Q1 {

    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/aoc2023/day8/input.txt";
        List<String> lines = Utils.readFile(filePath);
        System.out.println(navigation(lines.subList(2, lines.size()), lines.getFirst()));
    }

    public static class TreeNode {
        public String value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(String value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    public static int navigation(List<String> lines, String instructions){
        Map<String, TreeNode> nodeMap = new HashMap<>();

        // Parse each line
        for (String line : lines) {
            String[] parts = line.split(" = ");
            String nodeName = parts[0].trim().replaceAll("\r", "");
            String[] childNames = parts[1].replaceAll("[()]", "").split(", ");

            // Create or get the node
            TreeNode parentNode = nodeMap.computeIfAbsent(nodeName, TreeNode::new);

            // Assign children to the parent node
            if (childNames.length > 0) {
                TreeNode leftChild = nodeMap.computeIfAbsent(childNames[0].replaceAll("\r", ""), TreeNode::new);
                parentNode.left = leftChild;
            }
            if (childNames.length > 1) {
                TreeNode rightChild = nodeMap.computeIfAbsent(childNames[1].replaceAll("\r", ""), TreeNode::new);
                parentNode.right = rightChild;
            }
        }

        TreeNode root = nodeMap.get("AAA");
        int count = 0;
        char[] steps = instructions.toCharArray();
        for (int i=0;i<steps.length;i++){
            count++;
            if (steps[i] == 'L'){
                root = root.left;
            } else {
                root = root.right;
            }
            if (root.value.equals("ZZZ")){
                return count;
            }
            if (i == steps.length-1){
                i = -1;
            }
        }
        return 0;
    }
}
