### 基于SpringBoot + Vue的毕业生就业信息系统.

高校毕业生就业服务平台、就业信息管理系统、校园招聘信息系统、毕业生求职跟踪系统

#### 管理员功能模块介绍：
###### 公告管理：发布就业政策、招聘通知或系统重要公告。公司管理：审核并维护用人单位的基本信息与状态。学生管理：管理毕业生账号、学籍信息及就业状态。公司职位：查看和监管企业发布的所有招聘岗位信息。学生简历：查阅学生上传的简历，支持导出或推荐给企业。公司信息审核：审核企业注册资料，确保招聘主体合法合规。招聘会管理：组织线上/线下招聘会，设置时间与场地安排。招聘会预约：审批企业或学生参与招聘会的预约申请。岗位申请：监控学生投递记录，协调企业与学生匹配流程。消息管理：统一处理平台内学生、企业间的通信内容。公司学生沟通：支持并记录企业与学生之间的面试或咨询互动。数据统计：分析就业率、热门行业、岗位供需等核心指标。

#### 公司功能模块介绍：
###### 公司注册：提交营业执照等资料完成企业账号注册。公司信息修改：更新企业简介、联系方式、招聘需求等信息。公司职位：发布、编辑或下架面向毕业生的招聘岗位。招聘会管理：查看可参加的招聘会并管理本司展位信息。招聘会预约：申请参与校方组织的招聘会并确认席位。岗位审核：查看学生投递简历，审核并安排面试流程。消息管理：接收并回复学生咨询、面试邀约等消息。公司学生沟通：与意向毕业生在线交流，推进招聘进程。数据统计：查看本司岗位投递量、简历下载数、录用转化率等。

#### 学生功能模块介绍：
###### 学生注册：通过学号或学校认证完成个人账号注册。 学生信息修改：完善或更新个人基本信息与求职意向。 个人简历：创建、编辑并上传标准化电子简历供企业查看。 招聘会查看预约：浏览 upcoming 招聘会并在线预约参会。 预约记录：查看已报名的招聘会及审核状态。 岗位申请：向心仪企业投递简历，申请具体招聘职位。 消息管理：接收企业通知、面试安排或系统提醒消息。 公司学生沟通：与招聘企业HR或负责人进行在线沟通。

#### 安装环境

JAVA 环境 

Node.js环境 [https://nodejs.org/en/] 选择14.17

Yarn 打开cmd， 输入npm install -g yarn !!!必须安装完毕nodejs

Mysql 数据库 [https://blog.csdn.net/qq_40303031/article/details/88935262] 一定要把账户和密码记住

redis

Idea 编译器 [https://blog.csdn.net/weixin_44505194/article/details/104452880]

WebStorm OR VScode 编译器 [https://www.jianshu.com/p/d63b5bae9dff]

#### 采用技术及功能

后端：SpringBoot、MybatisPlus、MySQL、Redis、
前端：Vue、Apex、Antd、Axios

平台前端：vue(框架) + vuex(全局缓存) + rue-router(路由) + axios(请求插件) + apex(图表)  + antd-ui(ui组件)

平台后台：springboot(框架) + redis(缓存中间件) + shiro(权限中间件) + mybatisplus(orm) + restful风格接口 + mysql(数据库)

开发环境：windows10 or windows7 ， vscode or webstorm ， idea + lambok

##### 管理员：
公告管理、公司管理、学生管理、公司职位、学生简历、公司信息审核、招聘会管理、招聘会预约、岗位申请、消息管理、公司学生沟通、数据统计

##### 公司：
公司注册、公司信息修改、公司职位、招聘会管理、招聘会预约、岗位审核、消息管理、公司学生沟通、数据统计

##### 学生：
学生注册、学生信息修改，个人简历，招聘会查看预约、预约记录、岗位申请、消息管理、公司学生沟通


#### 前台启动方式
安装所需文件 yarn install 
运行 yarn run dev

#### 默认后台账户密码
[管理员]
admin
1234qwer

[公司]
enterprise
1234qwer

[学生]
fank
1234qwer
#### 项目截图

|  |  |
|---------------------|---------------------|
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477337683.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477497506.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477715466.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477482568.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477702846.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477467370.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477630649.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477452144.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477603216.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477439422.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477585933.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477426360.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477571833.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477412801.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477556024.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477399243.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477542193.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477387634.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477511086.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1728477373245.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/work/936e9baf53eb9a217af4f89c616dc19.png) |

#### 演示视频

暂无

#### 获取方式

Email: fan1ke2ke@gmail.com

WeChat: `Storm_Berserker`

`附带部署与讲解服务，因为要恰饭资源非免费，伸手党勿扰，谢谢理解😭`

> 1.项目纯原创，不做二手贩子 2.一次购买终身有效 3.项目讲解持续到答辩结束 4.非常负责的答辩指导 5.**黑奴价格**

> 项目部署调试不好包退！功能逻辑没讲明白包退！

#### 其它资源

[2025年-答辩顺利通过-客户评价🍜](https://berserker287.github.io/2025/06/18/2025%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2024年-答辩顺利通过-客户评价👻](https://berserker287.github.io/2024/06/06/2024%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2023年-答辩顺利通过-客户评价🐢](https://berserker287.github.io/2023/06/14/2023%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2022年-答辩通过率100%-客户评价🐣](https://berserker287.github.io/2022/05/25/%E9%A1%B9%E7%9B%AE%E4%BA%A4%E6%98%93%E8%AE%B0%E5%BD%95/)

[毕业答辩导师提问的高频问题](https://berserker287.github.io/2023/06/13/%E6%AF%95%E4%B8%9A%E7%AD%94%E8%BE%A9%E5%AF%BC%E5%B8%88%E6%8F%90%E9%97%AE%E7%9A%84%E9%AB%98%E9%A2%91%E9%97%AE%E9%A2%98/)

[50个高频答辩问题-技术篇](https://berserker287.github.io/2023/06/13/50%E4%B8%AA%E9%AB%98%E9%A2%91%E7%AD%94%E8%BE%A9%E9%97%AE%E9%A2%98-%E6%8A%80%E6%9C%AF%E7%AF%87/)

[计算机毕设答辩时都会问到哪些问题？](https://www.zhihu.com/question/31020988)

[计算机专业毕业答辩小tips](https://zhuanlan.zhihu.com/p/145911029)

#### 接JAVAWEB毕设，纯原创，价格公道，诚信第一

`网站建设、小程序、H5、APP、各种系统 选题+开题报告+任务书+程序定制+安装调试+项目讲解+论文+答辩PPT`

More info: [悲伤的橘子树](https://berserker287.github.io/)

<p><img align="center" src="https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/%E5%90%88%E4%BD%9C%E7%89%A9%E6%96%99%E6%A0%B7%E5%BC%8F%20(3).png" alt="fankekeke" /></p>
