import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TemplateProcessor {
    private String contents;

    public TemplateProcessor(String fileName) throws IOException {
        InputStream is = new FileInputStream(fileName);
        try (is) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder contentsBuilder = new StringBuilder();
            br.lines().forEach(l -> contentsBuilder.append(l).append("\n"));
            this.contents = contentsBuilder.toString();
        }
    }

    public String replace(java.util.Map<String, String> variableAssignments) {
        return variableAssignments.keySet().stream().reduce(contents,
                (acc, key) -> acc.replaceAll(key, variableAssignments.get(key)));
    }
}
