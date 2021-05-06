package gittest.jenkins;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;

public class HTTP2 {
    
    public static void main(String[] args)throws Exception{
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/foo", new MyHandlr());
        server.setExecutor(null);
        server.start();
        System.out.println("Listening on port 8080 (HTTP2)...");
    }

    static class MyHandlr implements HttpHandler{
        @Override
        public void handle(HttpExchange t) throws IOException {
            //String response = "This is the response";
            //t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            BufferedReader fileRead = new BufferedReader(new FileReader(new File("/home/evan/JavaProj/DockerExploration/cds/foo.txt")));
            String line2;
            while((line2 = fileRead.readLine()) != null){
                t.sendResponseHeaders(200, line2.length());
                os.write(line2.getBytes());
            }
            //os.write(response.getBytes());
            os.close();
            }
        
    }
}
