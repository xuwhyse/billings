package com.awhyse.concurrent.akka.server;

import com.awhyse.concurrent.akka.client.Greeter;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

//  java akka.Main HelloWorld
public class HelloWord extends UntypedActor {

	/**
	 * @param args
	 *            author:xumin 2016-5-9 下午7:46:42
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 创建系统  
        final ActorSystem system = ActorSystem.create("helloakka");//"helloakka"
        // 创建一个接待者  
        final ActorRef helloWord = system.actorOf(Props.create(HelloWord.class), "helloWord"); 
        final ActorRef greeter = system.actorOf(Props.create(Greeter.class), "greeter");
        
        greeter.tell(Greeter.Msg.GREET,helloWord);
	}
	//这个是自己重写的    启动这个Actor时调用的方法??
	@Override
	public void preStart() {
		//getContext()是HelloWord的上下文
//		final ActorRef greeter = getContext().actorOf(
//				Props.create(Greeter.class), "greeter");
//		//getSelf()就是ActorRef  HelloWord 。
//		greeter.tell(Greeter.Msg.GREET, getSelf());
//		System.err.println("preStart"); 
	}

	// UntypedActor必须实现的(抽象函数)
	@Override
	public void onReceive(Object msg) throws Exception {
		 if (msg == Greeter.Msg.DONE) {
//	           getContext().stop(getSelf());
			 System.err.println("HelloWord onReceive "); 
	       } else {
	           unhandled(msg);
	       }
	}

}
