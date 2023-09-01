# 影视作品推荐系统

这是一个基于Spring Boot和Thymeleaf的简单影视作品推荐系统。它允许用户浏览影视作品列表、搜索影视作品、登录和注册以及评论影视作品。管理员可以对影视作品进行添加、编辑和删除操作。

![登录页面](https://i.imgs.ovh/i/2023/09/01/64f159ef27a91.png)

![首页](https://i.imgs.ovh/i/2023/09/01/64f1707c087e6.png)

## 功能

- 用户可以浏览影视作品列表
- 用户可以搜索影视作品
- 用户可以登录和注册
- 用户可以对影视作品进行评分和评论
- 管理员可以添加、编辑和删除影视作品

## 技术栈

- Spring Boot
- Spring Security
- Thymeleaf
- MySQL
- Bootstrap

## 如何运行

### 环境要求

- JDK 20 或更高
- MySQL 8 或更高

### 步骤

1. 克隆项目到本地：

    ```bash
    https://github.com/chenduowei/movie-recommendation-system.git
    ```

2. 使用您喜欢的IDE导入项目，并确保Maven正确解析项目中的依赖关系。

3. 在MySQL中创建一个名为`movierecommender`的数据库：

    ```sql
    CREATE DATABASE movierecommender;
    ```

4. 在`src/main/resources`目录下创建一个名为`application.properties`的文件，并根据您的MySQL设置填写以下内容：

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/movierecommender?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=chen
   spring.jpa.hibernate.ddl-auto=update
   server.address=0.0.0.0
   ```

5. 运行项目，访问`http://localhost:8080`，您将看到影视作品推荐系统的主页。

## 如何贡献

我们欢迎您对这个项目提出建议和贡献。请通过提交issue或者拉取请求来提出您的建议。

## 许可

本项目遵循MIT许可。
