class Solution {
    public int numUniqueEmails(String[] emails) {
         HashSet<String> set = new HashSet<>();

        for (String email : emails) {
            String[] parts = email.split("@");
            String local = parts[0];
            String domain = parts[1];
            int plus = local.indexOf('+');
            if (plus != -1) {
                local = local.substring(0, plus);
            }
            local = local.replace(".", "");
            String actualEmail = local + "@" + domain;
            set.add(actualEmail);
        }

        return set.size();
    }
}