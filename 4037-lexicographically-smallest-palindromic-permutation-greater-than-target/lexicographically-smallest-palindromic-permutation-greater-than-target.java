class Solution {
    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();
        int half = n / 2;

        int[] freq = new int[26];

        // Count characters in s
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Check if palindrome is possible
        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        // Number of each character needed in left half
        int[] cnt = new int[26];

        for (int i = 0; i < 26; i++) {
            cnt[i] = freq[i] / 2;
        }

        /*
         * CASE 1:
         * Try to keep the left half exactly equal
         * to target's left half.
         */
        int[] temp = cnt.clone();
        boolean possible = true;

        for (int i = 0; i < half; i++) {

            int c = target.charAt(i) - 'a';

            if (temp[c] == 0) {
                possible = false;
                break;
            }

            temp[c]--;
        }

        if (possible) {

            char[] left = new char[half];

            for (int i = 0; i < half; i++) {
                left[i] = target.charAt(i);
            }

            String candidate = makePalindrome(left, middle);

            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        /*
         * CASE 2:
         * Make the left half greater than target's
         * left half.
         *
         * Start from RIGHT to LEFT because changing
         * a later position gives a smaller answer.
         */
        for (int change = half - 1; change >= 0; change--) {

            temp = cnt.clone();

            // Match everything before 'change'
            boolean ok = true;

            for (int i = 0; i < change; i++) {

                int c = target.charAt(i) - 'a';

                if (temp[c] == 0) {
                    ok = false;
                    break;
                }

                temp[c]--;
            }

            if (!ok) {
                continue;
            }

            // Try the smallest character greater than target[change]
            int targetChar = target.charAt(change) - 'a';

            for (int c = targetChar + 1; c < 26; c++) {

                if (temp[c] == 0) {
                    continue;
                }

                char[] left = new char[half];

                // Copy prefix
                for (int i = 0; i < change; i++) {
                    left[i] = target.charAt(i);
                }

                // Make this position bigger
                left[change] = (char) ('a' + c);

                temp[c]--;

                // Fill remaining positions with smallest characters
                int pos = change + 1;

                for (int x = 0; x < 26; x++) {
                    while (temp[x] > 0) {
                        left[pos] = (char) ('a' + x);
                        pos++;
                        temp[x]--;
                    }
                }

                return makePalindrome(left, middle);
            }
        }

        return "";
    }

    private String makePalindrome(char[] left, char middle) {

        StringBuilder ans = new StringBuilder();

        // Left half
        for (char c : left) {
            ans.append(c);
        }

        // Middle
        if (middle != 0) {
            ans.append(middle);
        }

        // Right half
        for (int i = left.length - 1; i >= 0; i--) {
            ans.append(left[i]);
        }

        return ans.toString();
    }
}