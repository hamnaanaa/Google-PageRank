import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.IntStream;

public class Webserver {
    private static String PATH = "data/";

    static String getPATH() {
        return PATH;
    }

    public static void main(String[] args) throws IOException {
        TemplateProcessor tp = new TemplateProcessor("html/search.html");
        LinkedDocumentCollection ldc;
        {
            LinkedDocumentCollection temp = new LinkedDocumentCollection();
            temp.appendDocument(new LinkedDocument("I.txt", "", "", null, null, "link:A.txt link:E.txt", "I.txt"));
            ldc = temp.crawl();
        }

        @SuppressWarnings("resource")
        ServerSocket serverSocket = new ServerSocket(8000);
        IntStream.iterate(0, i -> i + 1).forEach(i -> {
            Socket client;
            try {
                client = serverSocket.accept();
                System.out.println("*** Client connected!");
                WebserverThread wst = new WebserverThread(tp, ldc, client);
                wst.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
