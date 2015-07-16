## MavenSpringMVC
基于Maven包管理的，SpringMVC框架搭建的web学习项目


## 项目导入过程
+ 下载源代码
` $ git clone https://github.com/dotuian/MavenSpringMVC.git MavenSpringMVC ` 

+ 将源码文件装换成Eclipse项目
` $ mvn eclipse:eclipse -Dwtpversion=2.0' ` 
> Please remember that adding “-Dwtpversion=2.0″ is necessary, otherwise using only “mvn eclipse:eclipse” will convert it to only normal java application, and you will not be able to run it as other eclipse web applications.

+ 将项目导入到Eclipse中

+ 在项目属性设置中将 **Java Build Path**, **Java Complier** JDK的版本调整为1.8
+ 将**Project Facets **中**Java**也调整为1.8，**Dynamic Web module**的版本调整为3.0于web.xml中一致

 
