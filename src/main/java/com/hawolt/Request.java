package com.hawolt;


import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.util.function.Supplier;

/**
 * Created by: Niklas
 * Date: 22.05.2019
 * Time: 22:29
 */

public class Request {
    private HttpURLConnection connection;

    public Request(String endpoint) throws IOException {
        this(endpoint, null);
    }

    public Request(String endpoint, Proxy proxy) throws IOException {
        this(endpoint, proxy, Method.GET, false);
    }

    public Request(String endpoint, Method method, boolean output) throws IOException {
        this(endpoint, null, method, output);
    }

    public Request(String endpoint, Proxy proxy, Method method, boolean output) throws IOException {
        connection = BasicHttp.open(endpoint, proxy);
        connection.setRequestMethod(method.name());
        connection.setDoOutput(output);
    }

    public Request(String endpoint, Proxy proxy, String method, boolean output) throws IOException {
        connection = BasicHttp.open(endpoint, proxy);
        connection.setRequestMethod(method);
        connection.setDoOutput(output);
    }

    public void addHeader(String key, String value) {
        connection.setRequestProperty(key, value);
    }

    public void write(String output) throws IOException {
        try (OutputStream out = connection.getOutputStream()) {
            out.write(output.getBytes());
            out.flush();
        }
    }

    public HttpURLConnection getConnection() {
        return connection;
    }

}
