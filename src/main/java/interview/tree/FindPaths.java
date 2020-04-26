package interview.tree;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cxq on 2019-12-30 21:37
 */
public class FindPaths {
    public List<List<Integer>> allPath = new ArrayList<List<Integer>>();
    public List<Integer> path = new ArrayList<Integer>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        getAllPath(root);

        List<List<Integer>> result = new ArrayList<List<Integer>>();

        for (List<Integer> path : allPath) {
            int t = 0;
            for (Integer i : path) {
                t = t + i;
            }

            if (t == sum) {
                result.add(path);
            }
        }

        return result;
    }

    public void getAllPath(TreeNode root) {
        if (root == null) return;

        path.add(root.val);

        // if is leaf, add one path
        if (root.left == null && root.right == null) {
            allPath.add(new ArrayList(path));
        } else {
            getAllPath(root.left);
            getAllPath(root.right);
        }

        // backtracking
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        List<Integer> arr = Arrays.asList(10, 5, 12, 4, 7);
        TreeNode bTree = new BuildTree().creatTree(null, arr, 0);
        System.out.println(bTree.val);

        int s = 19;
        List<List<Integer>> res = new FindPaths().pathSum(bTree, s);
        System.out.println(gson.toJson(res));
    }
}
