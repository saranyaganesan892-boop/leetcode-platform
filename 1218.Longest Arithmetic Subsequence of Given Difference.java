import java.util.HashMap;

class Solution {
    public int longestSubsequence(int[] arr, int difference) {

        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 1;

        for (int num : arr) {

            int prev = num - difference;

            int length = map.getOrDefault(prev, 0) + 1;

            map.put(num, length);

            max = Math.max(max, length);
        }

        return max;
    }
}
