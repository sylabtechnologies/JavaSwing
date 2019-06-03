// https://stackoverflow.com/questions/3732109/simple-http-server-in-java-using-only-java-se-api
// 

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.util.Date;

public class HtmlServer {
    static int hits = 0;

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
        // port 8000 seems like a good choice for chat
        // good for password intercepter!?
        server.createContext("/test", new MyHandler());
        server.createContext("/advice", new MyAdvice());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            hits++;
            String response = new Date().toString().substring(0, 20);
            response += "\tThis is the response " + String.valueOf(hits);
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static String[] adviceList = {"Take smaller bites", "Go for the tight jeans. No they do NOT make you look fat",
        "One word: inappropriate", "Just for today, be honest.  Tell your boss what you *really* think", 
        "You might want to get a haircut"};

    private static String getAdvice() {
        int random = (int) (Math.random() * adviceList.length);
        return adviceList[random];
    }
    
    static class MyAdvice implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            hits++;
            String response = new Date().toString().substring(0, 20);
            response += "\t#" + String.valueOf(hits) + "\t\t";
            response += "You should: " + getAdvice();
                    
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    
}
        
