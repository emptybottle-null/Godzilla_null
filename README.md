# Godzilla_null
哥斯拉二开，基于公开的免杀哥斯拉更改的流量特征。请求包改成json请求!

# 一、环境

对冰蝎的jar包进行反编译

https://www.decompiler.com/

下载后得到反编译后的压缩包

![反编译后的压缩包](G:\Code\java\Godzilla\Godzilla_null\Godzilla_null\README.assets\反编译后的压缩包.jpg)

接下来在idea生成一个新项目

![image-20250207094203590](G:\Code\java\Godzilla\Godzilla_null\Godzilla_null\README.assets\image-20250207094203590.png)

生成项目后我们来到项目目录下把前面反编译的压缩包解压到项目目录下  同时新建一个lib文件夹把冰蝎原项目放入lib中  

![新建lib文件夹](G:\Code\java\Godzilla\Godzilla_null\Godzilla_null\README.assets\新建lib文件夹.jpg)

这时候我们再回到IDEA

设置一下项目结构SDK为1.8+即可  

![项目结构](G:\Code\java\Godzilla\Godzilla_null\Godzilla_null\README.assets\项目结构.jpg)

![模块结构](G:\Code\java\Godzilla\Godzilla_null\Godzilla_null\README.assets\模块结构.jpg)

然后选中打勾，应用。

![工件](G:\Code\java\Godzilla\Godzilla_null\Godzilla_null\README.assets\工件.jpg)

![工件2](G:\Code\java\Godzilla\Godzilla_null\Godzilla_null\README.assets\工件2.jpg)



![工件3](G:\Code\java\Godzilla\Godzilla_null\Godzilla_null\README.assets\工件3.jpg)



# 二、修改代码

这里先修改工具标注进行测试！

在IDEA中直接全局搜索t00ls关键字

![修改标识](G:\Code\java\Godzilla\Godzilla_null\Godzilla_null\README.assets\修改标识.jpg)

看要改的文件在哪个目录下，不能直接在源码里面修改。



发现在net文件夹里 我们选中这个net文件夹复制到项目根目录的src资源目录下  因为待会我们编译的时候idea是对src目录下的内容进行编译­的

![复制代码](G:\Code\java\Godzilla\Godzilla_null\Godzilla_null\README.assets\复制代码.jpg)

我们只保留这两个文件上面是改ui标注的  下面是待会修改流量特征要用到的   把其余的文件直接删除即可  否则编译会慢而且会莫名其妙报错  所以我们尽量是要改什么就留什么

![修改代码](G:\Code\java\Godzilla\Godzilla_null\Godzilla_null\README.assets\修改代码.jpg)

# 三、构建

改完后点击构建项目，构建完后你会发现多了个out目录，然后最后构建工件

![构建](G:\Code\java\Godzilla\Godzilla_null\Godzilla_null\README.assets\构建.png)

# 四、运行

启动的时候一定会提示你数据文件丢失

需要把原项目里自带的  data.db 放到项目根目录下就可以正常启动了

![运行](G:\Code\java\Godzilla\Godzilla_null\Godzilla_null\README.assets\运行.png)

![运行结果](G:\Code\java\Godzilla\Godzilla_null\Godzilla_null\README.assets\运行结果.png)







主要做了请求方式、标识、cookie自带；的更改，在其他的流量特征也做了简单的更改。

![image-20250215214948237](G:\Code\java\Godzilla\Godzilla_null\Godzilla_null\README.assets\image-20250215214948237.png)

![image-20250215215027282](G:\Code\java\Godzilla\Godzilla_null\Godzilla_null\README.assets\image-20250215215027282.png)

