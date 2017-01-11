package lab4.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.InterfaceAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Created by ihb on 08.12.16.
 */
class Server {

    void run() throws IOException {

        Selector selector = Selector.open();
        ServerSocketChannel socket = ServerSocketChannel.open();
        InetSocketAddress addr = new InetSocketAddress("localhost", 8000);


        socket.bind(addr);

        socket.configureBlocking(false);

        int ops = socket.validOps();
        SelectionKey selectKey = socket.register(selector, ops, null);

        log("Server running");
        while (true) {

            log("waiting message..");
            selector.select();

            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();

            while (it.hasNext()) {
                SelectionKey key = it.next();

                if (key.isAcceptable()) {
                    SocketChannel client = socket.accept();

                    client.configureBlocking(false);

                    client.register(selector, SelectionKey.OP_READ);
                    log("Connection Accepted: " + client.getLocalAddress() + "\n");
                } else if (key.isReadable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(256);
                    client.read(buffer);
                    String req = new String(buffer.array()).trim();

                    log("get " + req);

                    if ("disconnect".equals(req)) {
                        key.channel().close();
                    } else {
                        int dotsCount = Integer.parseInt(req);

                        new Thread(() -> {
                            byte[] response = String.valueOf(calculatePi(dotsCount)).getBytes();
                            ByteBuffer bufferResponse = ByteBuffer.wrap(response);
                            try {
                                client.write(bufferResponse);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }).run();
                    }
                }

                it.remove();
            }
        }
    }

    private double calculatePi(int dotsCount) {
        double x, y;
        int rectSize = 100;
        int radius = rectSize / 2;
        double dotsInCircle = 0;

        Random random = new Random();

        for (int i = 0; i < dotsCount; i++) {
            x = (random.nextInt(rectSize * 2)) - rectSize;
            y = (random.nextInt(rectSize * 2)) - rectSize;
            if (isDotInCircle(x, y, radius)) {
                dotsInCircle++;
            }
        }

        return (dotsInCircle / dotsCount) * 4;
    }

    private boolean isDotInCircle(double x, double y, int radius) {
        return x * x + y * y < radius * radius * 4;
    }

    private void log(String str) {
        System.out.println(str);
    }
}
