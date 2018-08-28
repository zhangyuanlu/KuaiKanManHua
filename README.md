# KuaiKanManHua
本项目采用MVP架构，使用RxJava2+Rerofit2做异步网络请求（支持cookie的保存）、Gson和Jsoup做数据加工，Fresco做图片加载，Realm做数据库，所有数据（甚至UI设计）来源于快看漫画官网，仅用于个人学习使用。
项目完成了首页漫画的展示，漫画搜索（支持自动补全，但不支持模糊查询），漫画章节列表的展示，漫画内容的展示（部分章节属于付费订阅章节，此部分未做处理，可能读到空数据），用户登录（未做注册模块），
用户关注列表的展示以及关注与取消关注的功能。

项目整体运行流畅，但由于数据全部取自网页，图片过大将导致消耗更多的内存（服务器提供给PC端与移动端的应该是两套资源），另外Jsoup的使用需要保证前端代码相对不变，
如果前端代码发生改变，可能造成整个网页解析失败。

由于是第一次在项目中使用多个流行框架，代码的复用性或许还有待提高，如有错误或需要改进的地方，欢迎指正。
![image](https://github.com/zhangyuanlu/KuaiKanManHua/blob/master/screenrecord/kk1.gif)
![image](https://github.com/zhangyuanlu/KuaiKanManHua/blob/master/screenrecord/kk2.gif)
![image](https://github.com/zhangyuanlu/KuaiKanManHua/blob/master/screenrecord/kk3.gif)
![image](https://github.com/zhangyuanlu/KuaiKanManHua/blob/master/screenrecord/kk4.gif)