package interview.tree;

import java.util.List;

/**
 * Created by cxq on 2019-12-30 21:37
 */
public class BuildTree {
    public TreeNode creatTree(TreeNode root, List<Integer> arr, int i) {
        if (i < arr.size()) {
            root = new TreeNode(arr.get(i));
            creatTree(root.left, arr, 2 * i + 1);
            creatTree(root.right, arr, 2 * i + 2);
            return root;
        } else {
            return root;
        }
    }
}
