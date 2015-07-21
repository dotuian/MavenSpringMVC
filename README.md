## MavenSpringMVC
基于Maven包管理的，SpringMVC框架搭建的web学习项目

## 项目导入过程
+ 下载源代码

> `$ git clone https://github.com/dotuian/MavenSpringMVC.git MavenSpringMVC` 

+ 将源码文件装换成Eclipse项目

> `$ mvn eclipse:eclipse -Dwtpversion=2.0'`

	Please remember that adding “-Dwtpversion=2.0″ is necessary, otherwise using only “mvn eclipse:eclipse” will convert it to only normal java application, and you will not be able to run it as other eclipse web applications.

+ 将项目导入到Eclipse中，然后调整项目属性

>1. 将**Java Build Path** -- **Libraries** 中JDK的版本调整为`1.8`
2. 将 **Java Complier** -- **Complier compliance level** 修改为 `1.8`
3. 将**Project Facets** 中 **Java** 也调整为`1.8`， **Dynamic Web module** 的版本调整为`3.0`与web.xml中一致
4. 将 **Resource** 中 **Text file encoding** 修改为  `UTF-8`

+ 选择项目，右键 -- Configure → Convert to Maven project 


