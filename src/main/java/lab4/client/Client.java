package lab4.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * Created by ihb on 01.12.16.
 */
class Client {
    void run() throws IOException, InterruptedException {

        InetSocketAddress addr = new InetSocketAddress("localhost", 8000);
        SocketChannel client = SocketChannel.open(addr);


        log("Connecting to Server on port 1111...");

        ArrayList<String> requestMessages = new ArrayList<String>();

        // create a ArrayList with companyName list
        requestMessages.add("100");
        requestMessages.add("1000");
        requestMessages.add("10000");
        requestMessages.add("100000");
        requestMessages.add("1000000");
        requestMessages.add("10000000");
        requestMessages.add("100000000");
        requestMessages.add("disconnect");

        for (String request : requestMessages) {

            byte[] requestMessage = request.getBytes();
            ByteBuffer bufferRequest = ByteBuffer.wrap(requestMessage);
            client.write(bufferRequest);
            log("sending: " + request);
            bufferRequest.clear();

            ByteBuffer bufferResponse = ByteBuffer.allocate(256);
            try {
                client.read(bufferResponse);
            } catch (Exception e){
                //e.printStackTrace();
                return;
            }
            String pi =  new String(bufferResponse.array()).trim();

            log(pi);
            Thread.sleep(100);
        }
        client.finishConnect();
        client.close();
    }



    private static void log(String str) {
        System.out.println(str);
    }
}
