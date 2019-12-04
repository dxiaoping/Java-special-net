create database java_knowledge_base;

use java_knowledge_base;

# 用户表
drop table `user`;
create table `user` (
  `phone` bigint(20) not null comment '手机号',
  `role` int(2) not null default 2 comment '用户角色',
  `name` varchar(20) default null comment '用户名',
  `password` varchar(20) not null default '123456' comment '用户密码',
  `security_question` varchar(100) default null comment '密保问题',
  `security_answer` varchar(100) default null comment '密保答案',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  primary key (phone)
) comment = '用户信息表';


alter table user
  add column `create_time` datetime DEFAULT NULL
COMMENT '注册时间'';

alter table user
  change column securityAnswer security_answer varchar(100) default null
comment ''密保答案'';
alter table user
  change column securityQuestion security_question varchar(100) default null
comment ''密保问题'';

# 目录表
drop table menu;
create table `menu` (
  `id` bigint(20) not null comment ''目录Id'',
  `parent_id` bigint(20) not null comment ''父目录Id'',
  `name` varchar(20) not null comment ''目录名称'',
  `url` varchar(150) not null default ''#'' comment ''目录链接'',
  primary key (id)
) comment = ''目录表'';
alter table menu change `url` `href` varchar(150) not null default ''#'' comment ''目录链接'';

drop table `content`;
create table `content` (
  `id` bigint(20) not null comment ''内容主体Id'',
  `name` varchar(300) comment ''知识点名称'',
  `menu_id` bigint(20) not null comment ''父目录Id'',
  `user_id` bigint(20) not null comment ''用户手机号，标识上传用户'',
  `text` varchar(300) comment ''文本信息'',
  `img` varchar(300) comment ''图片地址'',
  `video` varchar(300) comment ''视频地址'',
  `code` varchar(300) comment ''源码'',
  `create_time` datetime comment ''创建时间'',
  `update_time` datetime comment ''更新时间'',
  primary key (id)
)
  comment = ''内容窗体'';
use java_knowledge_base;
alter table content change create_time `create_time` datetime comment ''创建时间'';
alter table content change update_time `update_time` datetime comment ''更新时间'';
alter table content change img `img` varchar(300) comment ''图片地址'';
alter table content change video `video` varchar(300) comment ''视频地址'';
alter table content change `url` `href` varchar(150) default null comment ''外部链接'';
# 附件表
drop table enclosures;
create table enclosures (
  `id` bigint(20) not null comment ''附件Id'',
  `content_id` bigint(20) not null comment ''content对象Id'',
  `name` varchar(30) not null comment ''附件名称'',
  `url` varchar(50) not null comment ''附件地址'',
  `create_time` datetime default null comment ''创建时间'',
  `user_id` bigint(20) not null comment ''上传用户'',
  primary key (id)
) comment = ''附件表'';

alter table enclosures
  change column url url varchar(300) not null comment ''附件地址'';

# 一级目录限定在101~199以内
# 二级目录约定在1001~9999
# 三级目录约定在10001~99999
# content约定在100001~999999
# 附件预定在1000001~9999999
insert into menu (id, parent_id, name) VALUES (101, 0, ''基础模块'');
insert into menu (id, parent_id, name)VALUES (102, 0, ''进阶模块'');
insert into menu (id, parent_id, name)VALUES (103, 0, ''实例教程'');
insert into menu (id, parent_id, name)VALUES (104, 0, ''拓展知识'');
insert into menu (id, parent_id, name)VALUES (105, 0, ''作品展示'');
insert into menu (id, parent_id, name)VALUES (106, 0, ''相关资源'');


insert into menu (id, parent_id, name)VALUES (1001, 101, ''数组'');
insert into menu (id, parent_id, name)VALUES (1002, 101, ''链表'');
insert into menu (id, parent_id, name)VALUES (1003, 101, ''循环'');
insert into menu (id, parent_id, name)VALUES (1004, 101, ''判断'');
insert into menu (id, parent_id, name)VALUES (1005, 101, ''异常'');
insert into menu (id, parent_id, name)VALUES (1019, 101, ''环境安装'');

insert into menu (id, parent_id, name)VALUES (1006, 102, ''Spring'');
insert into menu (id, parent_id, name)VALUES (1007, 102, ''SpringMVC'');
insert into menu (id, parent_id, name)VALUES (10001, 1006, ''Bean加载过程'');
insert into menu (id, parent_id, name)VALUES (10002, 1006, ''IOC详解'');

# 实例教程模块
insert into menu (id, parent_id, name)VALUES (1008, 103, ''数组实例教程'');
insert into menu (id, parent_id, name)VALUES (1009, 103, ''循环实例教程'');
insert into menu (id, parent_id, name)VALUES (1010, 103, ''函数实例教程'');
insert into menu (id, parent_id, name)VALUES (1011, 103, ''线程实例教程'');

# 相关资源模块
insert into menu (id, parent_id,href, name)VALUES (1012, 106,''http://www.imooc.com/learn/85'',''慕课网'');
insert into menu (id, parent_id,href, name)VALUES (1013, 106,''https://www.aliyun.com'',''阿里云官网'');
insert into menu (id, parent_id,href, name)VALUES (1014, 106,''https://mvnrepository.com'',''Maven仓库'');
insert into menu (id, parent_id,href, name)VALUES (1015, 106,''https://www.nowcoder.com'',''牛客网'');
insert into menu (id, parent_id,href, name)VALUES (1016, 106,''https://leetcode-cn.com'',''leetCode'');
insert into menu (id, parent_id,href, name)VALUES (1017, 106,''https://www.cnblogs.com/skywang12345/category/455711.html'',''精选博客之Java深入解析'');
insert into menu (id, parent_id,href, name)VALUES (1018, 106,''https://www.bilibili.com/'',''哔哩哔哩'');



insert into content(id, menu_id, user_id, text, img, video, code)
VALUES (100001, 1001, 15574902295,''textUrl'',''imgUrl'',''videoUrl'',''codeUrl'');


insert into user (phone, role, name, password)
VALUES (15574902295, 0, ''小平'', ''asdzxc'');

insert into user (phone, role, name, password)
VALUES (15574902295, 0, ''小平'', ''asdzxc'');

insert into enclosures(id,content_id,user_id,name,url)
values (1002001,1111111,15574902295,''1111111111'',''111111111'');


SHOW VARIABLES LIKE ''auto_inc%'';