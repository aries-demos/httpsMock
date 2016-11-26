# httpsMock
https实现原理的小demo

[参考博客](http://blog.csdn.net/chuhx/article/details/51490193)


CertifcateUtils 	证书操作类
DesCoder 	Des对称加密和解密工具类
HttpsMockBase 	https父类
HttpsMockClient 	client类
HttpsMockServer 	服务器类
SocketUtils 	socket工具类


# Https-Mina
使用mina实现https的服务端
## 1. 创建SSLContext
SSLContext是用来创建SSLSocket或SSLEngine的。在下面的例子中有一个类SSLContextGenerator用来创建SSLContext。
要进行加密的传输，我们还需要两个密钥文件：keystore和truststore。要创建这两个文件可以参见“用keytool创建Keystore和Trustsotre文件”。
在SSLContextGenerator里我们使用了下面两个工厂类：

* KeyStoreFactory - 创建和配置KeyStore实例的工厂类。
* SSLContextFactory - 创建和配置SSLContext实例。

## 2. 服务器部分
服务器部分有两个类，SSLServer 和 SSLServerHandler。 SSLServer类里，我们用MINA的SSLFilter来加密和解密传输的数据。
同时这个类会立即触发SSL的Handshake的处理(如果你不想立即触发handshake处理，可以指定构造函数的autostart参数为false)。

注意：SSLFilter只用在TCP/IP的连接。

IoAcceptor接口用来接受客户端过来的连接请求，触发IoHandler中的事件。在本例中使用了两个Filter，LoggingFilter用来记录所有的事件和请求。
第二个是ProtocolCodecFilter用来把进来的ByteBuffer转换成POJO的消息对象。SSLFilter要放在过滤器链的最前端，以保证后面的Filter里利用到的数据是已经解密过的。

SSLServerHandler类有4个方法。sessionOpened在会话打开是被调用，可以在这个方法里进行会话的初始配置如会话空闲时间。
messageReceived方法在接收到客户端发来的消息后被调用。会话闲置10秒后会调用sessionIdle方法，我们在这里把会话结束掉。
当异常发生后messageReceived方法会被调用，我们在这个方法里关闭会话。

* [Mina SSL Filter安全加密过滤器相关知识介绍](http://blog.csdn.net/boonya/article/details/51683988)
* [HTTPS的Socket实现代码 ](http://blog.csdn.net/xxb2008/article/details/24402281)
* [Apache Mina-SSL配置](http://zjumty.iteye.com/blog/1885453)
* [SslFilter-API](http://mina.apache.org/mina-project/apidocs/org/apache/mina/filter/ssl/SslFilter.html)

# 最后
可以使用wireshark抓取https的通信包进行分析https的传输过程。

[用Wireshark简单分析HTTPS传输过程-抓包过程](http://www.freebuf.com/articles/system/37900.html)

