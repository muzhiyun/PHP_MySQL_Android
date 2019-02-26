#### 类似一言/一句的诗词推送后端 采用PHP+mysqli方式


造轮子第二次

缅怀第一个PHP+MySQL项目“ofo密码查询平台”  

目前基本逻辑完成,支持*json方式/JavaScript方式/直接包含*三种方式调用  

许多业务逻辑及具体实现参考[一句](http://yijuzhan.com/)为蓝本,表示感谢  
##### 文件说明
###### 必选
* index.php——接口页面
* connet.php——数据库连接配置文件 默认为测试环境账号 请自行修改
------
###### 可选
* test.php——三种调用方式演示页面
* add.php——用于手动增加数据的页面
* input.php——用于手动增加数据的连接文件


##### 部署方式
* 确保正确安装PHP+MySQL及HTTP服务器软件 PHP>5
* 上传connet.php和index.php到网页服务器目录下，请修改connet.php中数据库账号密码等连接参数
* 使用sql文件将数据导入或手动增加数据
* 访问http://yourdomain/yiju/index.php测试



##### 调用方式
* json方式  
    *   调用示例  
````http://yourdomain/yiju/index.php?m=json````   
    *   返回示例  
````{"id":"3092","connet":"\u6ede\u96e8\u901a\u5bb5\u53c8\u5f7b\u660e\uff0c\u767e\u5fe7\u5982\u8349\u96e8\u4e2d\u751f\u3002","ip":"192.168.2.1","time":"2019-01-01 11:46:15"}````

* js方式
    * 调用示例
````<script type="text/javascript" src="http://youdomain/yiju/index.php?m=js"></script><script>document.write("<div>");yiju();document.write("</div>");</script>````
    * 返回示例
````溪上桃花无数，花上有黄鹂。````

* 直接包含
    * 调用示例
````在php文件中直接包含index.php 推荐使用include而不是require````
````include 'index.php';````

    * 返回示例
````溪上桃花无数，花上有黄鹂。````

##### 补充
后期预增加接口调用频率和次数限制  
新手练手项目，如有错误欢迎指出，感谢来访。


