package org.aries.mina_https.server;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.service.IoHandlerAdapter;  
import org.apache.mina.core.session.IoSession;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
  
public class SSLServerHandler extends IoHandlerAdapter  
{  
    private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());  
    private int idleTimeout = 10;  
  
    @Override  
    public void sessionOpened(IoSession session)  
    {  
        // 设置会话闲置为10秒  
        session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, idleTimeout);  
        session.setAttribute("Values: ");  
    }  
  
    @Override  
    public void messageReceived(IoSession session, Object message)  
    {  
        System.out.println("Message received in the server..");  
        System.out.println("Message is: " + message.toString());  
    }  
  
    @Override  
    public void sessionIdle(IoSession session, IdleStatus status)  
    {  
        logger.info("Transaction is idle for " + idleTimeout + "secs, So disconnecting..");  
        // 把空闲的会话关闭  
        session.close();  
    }  
  
    @Override  
    public void exceptionCaught(IoSession session, Throwable cause)  
    {  
       // 出现异常是也关闭连接  
        session.close();  
    }  
}  