package com.awhyse.io.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import com.awhyse.io.nio.server.NioServer;

public class JAVANIOClient {

	private SocketChannel socketChannel;
    private Selector selector;
    private volatile boolean isClose = false;
    private ThreadPoolExecutor threadPool ;
    
    public AtomicLong readCount = new AtomicLong(0);
    
    //=============================================================================
    /**
	 * @param args
	 * author:xumin 
	 * 2016-9-20 上午11:14:26
	 */
	public static void main(String[] args) {
		JAVANIOClient client = new JAVANIOClient();
        client.start();
	}
	
	//=============================================================================
    public JAVANIOClient() {
        try {
        	//获取全局选择器,听多个通道上感兴趣的事件（如accept事件、read就绪事件、write就绪事件）
        	selector = Selector.open();
        	
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            InetSocketAddress host = new InetSocketAddress("127.0.0.1", NioServer.serverPort);
            
            socketChannel.connect(host);//连接远程服务器
//            socketChannel.socket().connect(host, connectionTimeOut_ms);
//            socketChannel.socket().setSoTimeout(timeout);//读超时
            
            //SelectionKey.OP_ACCEPT,read,write
            socketChannel.register(selector, SelectionKey.OP_CONNECT);//注册到选择器
        } catch (Exception e) {
            System.out.println("创建客户端连接异常Client2" + e.getMessage());
            close();
        }
 
    }
	private void close() {
		try {
			if (threadPool != null) 
				threadPool.shutdown();//回收线程池
            isClose = true;
            if (socketChannel != null) 
                socketChannel.close();//关闭连接
            if (selector != null) 
                selector.close();//关闭选择器
        } catch (IOException e) {
            System.out.println("关闭客户端异常：" + e.getMessage());
        }
	}
	private void start() {
		// TODO Auto-generated method stub
		threadPool = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(20));//线程池初始化大小，最大，keepAliveTime，工作队列容量
//		threadPool.execute(new ReceiveMessageHandler(scTemp))isClose;
		//这个是客户端线程
		Thread td = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(!isClose){
					try {
						// select()阻塞，等待有事件发生唤醒  
						int selected = selector.select();
						System.err.println("selected："+selected);
						if (selected > 0) {
							Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
							while (selectedKeys.hasNext()) { 
								SelectionKey selectionKey = selectedKeys.next();
								selectedKeys.remove();
								SocketChannel scTemp = (SocketChannel) selectionKey.channel();
								if(selectionKey.isReadable()){
									//读的概率比较大放第一,这边保证同步。等组装解析成完整的一个协议包后再异步
									System.err.println("isReadable");
									syncRead(scTemp);
									
								}else if(selectionKey.isWritable()){
									//写的事件
									System.err.println("isWritable");
								}else if(selectionKey.isConnectable()){
									//最初的时候注册了一个连接事件,等到服务器响应时，注册读
									System.err.println("isConnectable");
		                            scTemp.finishConnect();
		                            scTemp.register(selector, SelectionKey.OP_READ);
								}else if(selectionKey.isAcceptable()){
									System.err.println("isAcceptable");
								}else{
									System.err.println("其他事件");
								}
								
							}
						}
					} catch (Exception e) {
						System.out.println("客户端启动或运行异常[start]：" + e.getMessage());
	                    close();
					}
				}
				
			}
		});
		td.setName("client11");
		td.start();
	}

	/**
	 * scTemp里的数据要是没读完，会一直有可读消息事件过来
	 * @param scTemp
	 * @throws IOException
	 * author:xumin 
	 * 2016-9-27 上午10:13:17
	 */
	protected void syncRead(SocketChannel scTemp) throws IOException {
		// TODO Auto-generated method stub
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		scTemp.read(buffer);
		int length = buffer.position();
		byte[] dest = new byte[length];
		System.arraycopy(buffer.array(), 0, dest, 0, length);
        String message = new String(dest);
        
        System.out.println("recevie message from server:, size:" + buffer.position() + " msg: " + message);
	}
	

}

