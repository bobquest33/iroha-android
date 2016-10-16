package io.soramitsu.irohaandroid.utils;

import java.io.IOException;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;


public class NetworkMockUtil {
    private static MockWebServer mockWebServer;

    private NetworkMockUtil() {
    }

    public static void createMockWebServer(Dispatcher dispatcher)
            throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.setDispatcher(dispatcher);
        mockWebServer.start();
    }

    public static void shutdownMockWebServer() throws IOException {
        mockWebServer.shutdown();
    }

    public static String call(String url) {
        return mockWebServer.url(url).toString();
    }
}