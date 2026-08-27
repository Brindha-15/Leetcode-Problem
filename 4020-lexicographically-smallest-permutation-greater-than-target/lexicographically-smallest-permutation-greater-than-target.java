class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        // Try every position from right to left
        for (int i = n - 1; i >= 0; i--) {

            int[] freq = new int[26];

            // Count all characters of s
            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }

            // Use target characters before i
            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';

                if (freq[c] == 0) {
                    possible = false;
                    break;
                }

                freq[c]--;
            }

            if (!possible)
                continue;

            // At position i, find a character
            // greater than target[i]
            int cur = target.charAt(i) - 'a';

            for (int c = cur + 1; c < 26; c++) {

                if (freq[c] > 0) {

                    char[] ans = target.toCharArray();

                    // Make this position greater
                    ans[i] = (char) ('a' + c);

                    freq[c]--;

                    // Fill remaining characters
                    // in smallest order
                    int k = i + 1;

                    for (int x = 0; x < 26; x++) {
                        while (freq[x] > 0) {
                            ans[k++] = (char) ('a' + x);
                            freq[x]--;
                        }
                    }

                    return new String(ans);
                }
            }
        }

        return "";
    }
}