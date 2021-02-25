【Redis】
1>安装Redis
$ wget http://download.redis.io/releases/redis-6.0.6.tar.gz
$ tar xzf redis-6.0.6.tar.gz
$ cd redis-6.0.6
$ make

2>启动Redis
---服务端---
$ src/redis-server --daemonize yes
---客户端---
$ src/redis-cli

---通配符删除---
./redis-cli keys "FLASH_SALE_ADD_ORDER*" | xargs redis-cli del


【rabbitmq】
1>Mac安装docker
https://www.runoob.com/docker/macos-docker-install.html

2>安装rabbitmq
搜索：
docker search rabbitmq:management
拉取镜像：
docker pull rabbitmq:management
运行镜像：
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 docker.io/rabbitmq:management
查看日志：
docker logs -f rabbitmq
访问,默认的用户名和密码都是guest：
http://localhost:15672

【Jmeter】
https://jmeter.apache.org/usermanual/get-started.html#running