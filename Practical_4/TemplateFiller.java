public class TemplateFiller {
    public static String fillTemplate(String template, String[] names, String[] values) {
        StringBuilder result = new StringBuilder();
        int start = 0;
        while (true) {
            int open = template.indexOf("{", start);
            if (open == -1) {
                result.append(template.substring(start));
                break;
            }
            result.append(template.substring(start, open));
            int close = template.indexOf("}", open);
            if (close == -1) {
                result.append(template.substring(open));
                break;
            }
            String placeholder = template.substring(open + 1, close);
            String replacement = "[?]";
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(placeholder)) {
                    replacement = values[i];
                    break;
                }
            }
            result.append(replacement);
            start = close + 1;
        }
        return result.toString();
    }
}
