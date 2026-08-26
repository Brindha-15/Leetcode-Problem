class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        String ans = "";

        for (int i = 0; i < s.length(); i++) {
            int count = 0;

            for (int j = i; j < s.length(); j++) {

                if (s.charAt(j) == '1') {
                    count++;
                }

                if (count == k) {
                    String temp = s.substring(i, j + 1);

                    if (ans.equals("") ||
                        temp.length() < ans.length() ||
                        (temp.length() == ans.length() &&
                         temp.compareTo(ans) < 0)) {

                        ans = temp;
                    }

                    break;
                }

                if (count > k) {
                    break;
                }
            }
        }

        return ans;
    }
}