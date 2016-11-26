[用keytool创建Keystore和Trustsotre文件](http://zjumty.iteye.com/blog/1885356)

JSSE使用Truststore和Keystore文件来提供客户端和服务器之间的安全数据传输。keytool是一个工具可以用来创建包含公钥和密钥的的keystore文件，
并且利用keystore文件来创建只包含公钥的truststore文件。在本文中，我们学习如何通过下面的5步简单的创建truststore和keystore文件：

* 1.生成一个含有一个私钥的keystore文件
* 2.验证新生成的keystor而文件
* 3.导出凭证文件
* 4.把认凭证件导入到truststore文件
* 5.验证新创建的truststore文件

## 第一步 - 生成一个含有一个私钥的keystore文件

```
keytool -genkeypair -alias certificatekey -keyalg RSA -validity 7 -keystore mina-https.keystore
```

