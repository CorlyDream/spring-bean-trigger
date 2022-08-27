# spring-bean-trigger

通过 http 接口，调用 spring 中 bean

示例：cc.corly.springbean.trigger.example.Main

通过 http 接口调用 spring 中的 bean，方便处理数据

## 使用

依赖中引入

```xml
<dependency>
  <groupId>cc.corly</groupId>
  <artifactId>spring-bean-trigger</artifactId>
  <version>1.0.2</version>
</dependency>
```

程序启动的时候添加如下代码

```java
BeanHttpExpose.build(9009, triggerService);
```

暴露端口，通过 http 调用。

```shell
curl --location --request POST 'http://127.0.0.1:9009/demoBean/testCall' \
--header 'Content-Type: application/json' \
--data-raw '["125082"]'
```

调用的 url 格式：http://127.0.0.1:9009/{beanName}/{methodName}

post body 中是个字符串数组，对应调用方法的入参
