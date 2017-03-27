<<<<<<< HEAD
create database smacker;
use smacker;
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
    isVerify varchar(1) default '0'				--是否认证 (0:尚未认证；1:认证成功；2:认证失败；3:正在认证)
    school varchar(32) 
)engine=innoDB default charset=utf8

create table t_commodity(
	commodityId varchar(32) primary key,
	commodityName varchar(32) not null,				--物品名称
	commodityCategary varchar(10),					--物品类别
	commodityPicture varchar(255),					--物品图片
	commodityDescribe varchar(255),					--物品描述
	commodityCount int default -1,					--物品数量
	commodityOldNewLevel int default -1,			--新旧程度
	commodityNewPrice int default -1,				--原价
	commodityOldPrice int default -1,				--二手价
	commodityOwnerId varchar(32) not null,			--拥有者
	commodifyCreateDate datetime default current_timestamp,		--创建时间
	wantShopCount int default 0,					--欲购物人数
	isOrder varchar(1) default "0",					--是否被定(0:正在出售,1:已被订购,2:已出售)
	constraint foreign key (commodityOwnerId) references t_user (userId)
)engine=innoDB default charset=utf8

--用来记录订单
create table t_commodityOrder (
	orderId varchar(32) primary key,
	commodityId varchar(32) not null,							--物品Id
	unitPrice int default 0,									--单价
	commodityCount int default 0,								--个数
	userId varchar(32) not null,
	sellerId varchar(32) not null,
	addr varchar(255) not null,									--地址
	status varchar(1) default "0",								--状态(0:订单未创建；1:订单成功；2:订单作废)
	orderDate datetime default current_timestamp,				--创建时间
	constraint foreign key (userId) references t_user(userId),
	constraint foreign key (sellerId) references t_user(userId),
	constraint foreign key (commodityId) references t_commodity(commodityId)
)engine=innoDB default charset=utf8;

--购物车
create table t_shopCar (
	shopCarId varchar(32) primary key,
	userId varchar(32) not null,
	commodityId varchar(32) not null,
	constraint foreign key (userId) references t_user(userId),
	constraint foreign key (commodityId) references t_commodity(commodityId)
=======
create database smacker;
use smacker;
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
    isVerify varchar(1) default '0'				--是否认证 (0:尚未认证；1:认证成功；2:认证失败；3:正在认证)
    school varchar(32) 
)engine=innoDB default charset=utf8

create table t_commodity(
	commodityId varchar(32) primary key,
	commodityName varchar(32) not null,				--物品名称
	commodityCategary varchar(10),					--物品类别
	commodityPicture varchar(255),					--物品图片
	commodityDescribe varchar(255),					--物品描述
	commodityCount int default -1,					--物品数量
	commodityOldNewLevel int default -1,			--新旧程度
	commodityNewPrice int default -1,				--原价
	commodityOldPrice int default -1,				--二手价
	commodityOwnerId varchar(32) not null,			--拥有者
	commodifyCreateDate datetime default current_timestamp,		--创建时间
	wantShopCount int default 0,					--欲购物人数
	isOrder varchar(1) default "0",					--是否被定(0:正在出售,1:已被订购,2:已出售)
	constraint foreign key (commodityOwnerId) references t_user (userId)
)engine=innoDB default charset=utf8

--用来记录订单
create table t_commodityOrder (
	orderId varchar(32) primary key,
	commodityId varchar(32) not null,							--物品Id
	unitPrice int default 0,									--单价
	commodityCount int default 0,								--个数
	userId varchar(32) not null,
	sellerId varchar(32) not null,
	addr varchar(255) not null,									--地址
	status varchar(1) default "0",								--状态(0:订单未创建；1:订单成功；2:订单作废)
	orderDate datetime default current_timestamp,				--创建时间
	constraint foreign key (userId) references t_user(userId),
	constraint foreign key (sellerId) references t_user(userId),
	constraint foreign key (commodityId) references t_commodity(commodityId)
)engine=innoDB default charset=utf8;

--购物车
create table t_shopCar (
	shopCarId varchar(32) primary key,
	userId varchar(32) not null,
	commodityId varchar(32) not null,
	constraint foreign key (userId) references t_user(userId),
	constraint foreign key (commodityId) references t_commodity(commodityId)
>>>>>>> c5500473ca1d5ec9425999976c0c5b2d7cce035b
)engine=innoDB default charset=utf8;