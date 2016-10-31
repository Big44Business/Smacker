create database Smacker;
use Smacker;
create table t_user(
	userId varchar(32) primary key,
	userNickName varchar(32) not null,
	userEmail varchar(30) unique,				--邮箱
	userPassword varchar(20) not null,
    userGender varchar(2),						--性别
    userIdCard varchar(10),						--身份证
    userIntroduce varchar(255),					--简介
    userBirthday datetime,						--生日
    userCreateDate datetime default current_timestamp,		--创建时间
    userPhoto varchar(255),						--头像
    userAddress varchar(20),					--地址
    userIp varchar(20),
    userRealName varchar(20),					--真实姓名
    userRealPhoto varchar(255),					--真实照片
    userTel varchar(20) unique,					--用户电话
    userWeChat varchar(20) unique,
    isVerify varchar(1) default '0', 			--是否认证 (0:尚未认证；1:认证成功；2:认证失败；3:正在认证)

)engine=innoDB default charset=utf8

create table t_commodity(
	commodityId varchar(32) primary key,
	commodityName varchar(32) not null,				--物品名称
	commodityCategary varchar(10),					--物品类别
	commodityStatus int default -1;					--物品状态
	commodityPicture varchar(255),					--物品图片
	commodityDescribe varchar(255),					--物品描述
	commodityCount int default -1;					--物品数量
	commodityOldNewLevel int default -1;			--新旧程度
	commodityNewPrice int default -1;				--原价
	commodityOldPrice int default -1;				--二手价
	commodityOwner varchar(32) not null;			--拥有者
	commodifyCreateDate datetime default current_timestamp;		--创建时间
	wantShopCount int default 0;					--欲购物人数
)engine=innoDB default charset=utf8

