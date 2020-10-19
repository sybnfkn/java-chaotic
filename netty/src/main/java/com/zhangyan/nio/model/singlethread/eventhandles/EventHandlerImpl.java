package com.zhangyan.nio.model.singlethread.eventhandles;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangyan on 2019/2/5
 */
public class EventHandlerImpl implements EventHandler {

    private static ExecutorService poll = Executors.newFixedThreadPool(10);

    private boolean runWithThreadpoll = false;
    private State readState = new ReadState(this);
    private State writeState = new WriteState();
    private State currentState = readState;

    public EventHandlerImpl(boolean runWithThreadpoll) {
        this.runWithThreadpoll = runWithThreadpoll;
        this.currentState = readState;
    }

    @Override
    public void process(final SelectionKey key) {
        /*// 正在处理
        if (currentState.isProcessing()) {
            return;
        }
        currentState.startProcessing();
        if (runWithThreadpoll) {
            poll.submit(() -> currentState.process(key));
        } else {
            currentState.process(key);
        }*/
    }

    private interface State {
        void process(SelectionKey key);
        boolean isProcessing();
        void startProcessing();
    }

    private class WriteState implements State {

        private boolean isProcessing = false;

        @Override
        public void process(SelectionKey key) {
            SocketChannel sc = (SocketChannel) key.channel();
            try {
                String content = "bye";
                sc.write(ByteBuffer.wrap(content.getBytes()));
                // 不能使用cancel, 每个key对应一个channel, 这样操作导致整个channel不可用
//                key.cancel();
                // 取消通道的写事件
                key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                isProcessing = false;
            }

        }

        @Override
        public boolean isProcessing() {
            return isProcessing;
        }

        @Override
        public void startProcessing() {
            isProcessing = true;
        }
    }

    private class ReadState implements State {

        private boolean isProcessing = false;
        private EventHandler eventHandler;

        public ReadState(EventHandler eventHandler) {
            this.eventHandler = eventHandler;
        }

        @Override
        public void process(SelectionKey key) {
            SocketChannel sc = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(16);
            try {
                /*sc.read(byteBuffer);
                byteBuffer.flip();
                String request = String.valueOf(StandardCharsets.UTF_8.decode(byteBuffer));
                System.out.println(">>>>>" + request);*/

                Selector selector = key.selector();
                // 注册写事件
                sc.register(selector, SelectionKey.OP_WRITE, this.eventHandler);
                // 修改状态
                currentState = writeState;
                // 为了唤醒阻塞在selector.select上的线程，让该线程及时去处理其他事情
                // 比如处理写事件
                selector.wakeup();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                isProcessing = false;
            }

        }

        @Override
        public boolean isProcessing() {
            return isProcessing;
        }

        @Override
        public void startProcessing() {
            isProcessing = true;
        }
    }

}
