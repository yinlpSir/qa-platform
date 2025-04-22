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
        <td><img alt="1" src="https://github.com/user-attachments/assets/33fb8cdb-4793-4b5b-9873-fa14adc0508c" /></td>
        <td><img alt="2" src="https://github.com/user-attachments/assets/2e688bb9-fa67-473e-9f92-2a126f3884ff" /></td>
    </tr>
    <tr>
        <td><img width="982" alt="3" src="https://github.com/user-attachments/assets/91638207-9f6e-4ef0-8efe-f70d863c680e" /></td>
        <td><img alt="4" src="https://github.com/user-attachments/assets/3aed951e-e13f-43b3-9557-5ec29366bce3"/></td>
    </tr>
    <tr>
        <td><img width="982" alt="5" src="https://github.com/user-attachments/assets/921153ca-6205-4009-b392-bec94d9c5d11" /></td>
        <td><img width="982" alt="6" src="https://github.com/user-attachments/assets/fbdf04ff-f566-445a-80c9-8c1ec264e1bd"/></td>
    </tr>
	<tr>
        <td><img width="970" alt="7" src="https://github.com/user-attachments/assets/11c6177b-7562-4e3b-9703-4fad44b83bc8" /></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-d67451d308b7a79ad6819723396f7c3d77a.png"/></td>
    </tr>	 
</table>




