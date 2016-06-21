package com.awhyse.concurrent.akka.client;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {

	public static enum Msg {
		GREET, DONE
	}

	@Override
	public void onReceive(Object msg) {
		if (msg == Msg.GREET) {
			System.out.println("Hello World!");
			//获取发送者ActorRef ，告诉它消息+告诉它的人
			getSender().tell(Msg.DONE, getSelf());
		} else {
			unhandled(msg);
		}
	}

}
