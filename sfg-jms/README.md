# JMS Messaging

To run this project either:

- Start the embedded ActiveMQ broker by uncommenting the ActiveMQ server configuration from **Application.java** 
and the two dependencies from **pom.xml**

Or

- Run ActiveMQ in docker: 

```console
docker run -it --rm \
     -p 8161:8161 \
     -p 61616:61616 \
     vromero/activemq-artemis
```
Default username and password of `artemis` / `simetraehcapa`.
Check the [`web console`](http://127.0.0.1:8161).

Finally: 

> `mvn spring-boot:run` 

