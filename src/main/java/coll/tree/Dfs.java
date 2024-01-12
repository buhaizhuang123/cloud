package coll.tree;

import coll.tree.po.Node;

import java.util.*;

/**
 * @author haizhuangbu
 * @date 2023/9/1 10:54
 * @mark Dfs 深度优先算法
 */
public class Dfs {


    private Map<Node, Boolean> visited = new HashMap<>();

    public void dfsMs() {
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");
        Node G = new Node("G");
        Node H = new Node("H");
        // A-B
        // A-E
        // A-G
        A.add(B, E, G);
        // B-C
        // B-D
        B.add(C, D);
        // E-H
        // E-F
        E.add(F, H);

//        dfs(A);
        Bfs bfs = new Bfs();
        bfs.bfs(A);
    }

    public void dfs(Node start) {
        System.out.println(start.getName());
        visited.put(start, true);
        // 下面节点还有值
        if (start.getLinkList() != null && start.getLinkList().size() > 0) {
            start.getLinkList().stream()
                    .filter(i -> !visited.containsKey(i))
                    .forEach(this::dfs);
        }


    }


    public int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int sub = target - nums[i];
            while (j < nums.length) {
                if (nums[j] == sub) {
                    return new int[]{i, j};
                }
                j++;
            }
        }
        return new int[0];
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>(Collections.emptyList());
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int z = j + 1; z < length; z++) {
                    if (nums[i] + nums[j] + nums[z] == 0) {
                        List<Integer> list1 = Arrays.asList(nums[i], nums[j], nums[z]);
                        for (List<Integer> integers : list) {
                            if (list1 != null && integers.contains(list1.get(0)) && integers.contains(list1.get(1)) && integers.contains(list1.get(2))) {
                                list1 = null;
                            }
                        }
                        if (list1 != null) {
                            list.add(list1);
                        }
                    }
                }
            }

        }
        return list;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        for (int i = 0; i < m; i++) {
            int temp = nums1[i];
            int j = 0;
            while (j < n) {
                if (nums2[j] < temp) {
                    nums1[i] = nums2[j];
                    while (i <= m) {

                    }
                }
                j++;
            }
        }

    }


}
