# qa-platform
🚀 基于Java 17 + Spring Boot 3 + Spring Security 6 + Vue3 + Element Plus构建的前后端分离的知识问答平台。

### 项目介绍
这是一个高效、便捷、高质量的问答社区。用户通过手机号注册和登录账号，可以在里面发布话题，自己发布的话题可删除和更新，然后其他用户可在该话题下进行讨论，包括点赞、收藏、评论该话题，以及用户之间可互相关注。进入平台主页，可查看热门话题，该热门话题的数据是通过Jsoup库从知乎的热榜上爬取过来的；也可查看自己关注的用户发布的话题；也可查看平台推荐的话题，该话题不是通过推荐算法给你推荐感兴趣的话题，而仅仅是根据话题的点赞量、阅读量、评论数的权重计算出来的受欢迎程度值来推荐的；也可模糊搜索与关键词相关的话题并浏览。进入个人主页，可查看自己关注的用户以及粉丝列表；还可对自己发布的话题进行更新和删除；还可对自己的个人信息（头像、昵称、简介、手机号）进行更改等。通过这个平台，用户能非常方便的参与自己感兴趣的话题进行讨论。

### 功能特性
- **爬虫**：通过Jsoup库对知乎上的热榜问题爬取下来展示到自己平台。
- **话题互动**：用户可创建话题或查找自己喜欢的话题，并进行点赞、评论、收藏、关注作者等。也可浏览人气话题等。
- **用户互动**：用户可修改个人信息，打造自己的个人形象。也可关注其他用户等。
- **人气话题计算公式**：通过该计算公式：综合得分=阅读量*阅读权重+点赞数*点赞权重+评论数*评论权重，来决定话题的受欢迎程度，得分越高就越受欢迎。
- **无状态认证**：基于Spring Security框架集成JWT（JSON Web Token）实现无状态认证。

### 平台安全性和稳定性
- **数据加密**：对用户信息、密码等敏感数据进行加密存储，确保数据安全。
- **系统备份与恢复**：定期对系统进行备份，确保在发生意外时能够快速恢复数据。
- **性能优化**：通过优化数据库查询、缓存策略等方式，提高系统的响应速度和稳定性。

### 演示图

<table>
    <tr>
        <td><img width="1023" alt="1" src="https://github.com/user-attachments/assets/33fb8cdb-4793-4b5b-9873-fa14adc0508c" /></td>
        <td><img src="https://oscimg.oschina.net/oscnet/1cbcf0e6f257c7d3a063c0e3f2ff989e4b3.jpg"/></td>
    </tr>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-8074972883b5ba0622e13246738ebba237a.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-9f88719cdfca9af2e58b352a20e23d43b12.png"/></td>
    </tr>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-39bf2584ec3a529b0d5a3b70d15c9b37646.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-936ec82d1f4872e1bc980927654b6007307.png"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-b2d62ceb95d2dd9b3fbe157bb70d26001e9.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-d67451d308b7a79ad6819723396f7c3d77a.png"/></td>
    </tr>	 
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/5e8c387724954459291aafd5eb52b456f53.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/644e78da53c2e92a95dfda4f76e6d117c4b.jpg"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-8370a0d02977eebf6dbf854c8450293c937.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-49003ed83f60f633e7153609a53a2b644f7.png"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-d4fe726319ece268d4746602c39cffc0621.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-c195234bbcd30be6927f037a6755e6ab69c.png"/></td>
    </tr>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/b6115bc8c31de52951982e509930b20684a.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-5e4daac0bb59612c5038448acbcef235e3a.png"/></td>
    </tr>
</table>
![image](https://github.com/user-attachments/assets/8f85304f-2122-4cf5-b7cb-f93d4fd0a31b)
![image](https://github.com/user-attachments/assets/5b40d31d-4662-475a-a921-513fbe9133ca)
![image](https://github.com/user-attachments/assets/bd2ab385-ad2c-43fa-bc77-3a6a7826f070)
![image](https://github.com/user-attachments/assets/ca71fdb6-ae7e-40a6-85c9-c1dc6b365a83)
![image](https://github.com/user-attachments/assets/52c0585b-9455-4f49-aa7d-cf9a148eba2b)
![image](https://github.com/user-attachments/assets/c14f401c-8c0b-4350-9162-480670304b8f)
![image](https://github.com/user-attachments/assets/76b10588-180f-4854-9519-a1e4b185578f)
