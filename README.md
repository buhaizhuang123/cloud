# cloud

## 项目架构:

```springBoot 2.2.2.RELEASE,flowable,SpringCloud Hoxton.SR1,mysql,前端Vue url:https://github.com/buhaizhuang123/hi-vie```

## 登录页面

## 多数据源

## Kafka信息通知

<img width="1424" alt="login" src="https://github.com/buhaizhuang123/cloud/assets/95667080/55075f04-f84a-4aca-b9e7-4aed8a2003cf">
<img width="1436" alt="page" src="https://github.com/buhaizhuang123/cloud/assets/95667080/7c1dfb47-a4cc-4ef1-b2ca-9a9afed493e4">
<img width="1431" alt="flowable" src="https://github.com/buhaizhuang123/cloud/assets/95667080/912cb32d-27d5-4952-a30f-5920905b087c">
<img width="1417" alt="es搜索" src="https://github.com/buhaizhuang123/cloud/assets/95667080/4b2b476a-a548-412b-a161-d9035a9a59d7">
<img width="1440" alt="截屏2024-03-26 14 33 12" src="https://github.com/buhaizhuang123/cloud/assets/95667080/bd9844d3-04aa-413f-9053-30b32d5215a8">
![Flowable 11 18 44](https://github.com/buhaizhuang123/cloud/assets/95667080/128fda13-66a6-4938-a1ff-7a66a1190754)

## 算法

<img width="977" alt="截屏2024-03-26 14 35 37" src="https://github.com/buhaizhuang123/cloud/assets/95667080/63085784-9b5d-4184-a628-080224c2209c">

## spring 事务传递
SUPPORTS : 依赖前置事务、前置事务存在就加入前置事务、不存在就按照无事务执行; 
REQUIRED 依赖前置事务、不存在新建;
MANDATORY 前置事务不存在就抛出异常;
REQUIRES_NEW 每次都是新建事务、不依赖前置、
NOT_SUPPORTED 存在前置事务、就按照非事务方式执行、不依赖. 无事务执行方式;
NEVER 存在事务就抛出异常;
NESTED 安全点事务、相互之间隔离

csdn: https://blog.csdn.net/haizhuang_bu/article/details/137069831?csdn_share_tail=%7B%22type%22%3A%22blog%22%2C%22rType%22%3A%22article%22%2C%22rId%22%3A%22137069831%22%2C%22source%22%3A%22haizhuang_bu%22%7D