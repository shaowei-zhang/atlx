/*
 Navicat Premium Data Transfer

 Source Server         : WRR
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : atlx

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 25/05/2022 18:40:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for food_collection
-- ----------------------------
DROP TABLE IF EXISTS `food_collection`;
CREATE TABLE `food_collection`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `food_local_id` int NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of food_collection
-- ----------------------------
INSERT INTO `food_collection` VALUES (1, 31, 'zhangsan');
INSERT INTO `food_collection` VALUES (3, 33, 'zhangsan');
INSERT INTO `food_collection` VALUES (23, 31, 'lisi');
INSERT INTO `food_collection` VALUES (24, 31, 'lisi');
INSERT INTO `food_collection` VALUES (27, 34, 'lisi');
INSERT INTO `food_collection` VALUES (28, 33, 'lisi');
INSERT INTO `food_collection` VALUES (29, 33, 'lisi');
INSERT INTO `food_collection` VALUES (34, 47, '陈浩');

-- ----------------------------
-- Table structure for food_local
-- ----------------------------
DROP TABLE IF EXISTS `food_local`;
CREATE TABLE `food_local`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `region_id` int NULL DEFAULT NULL,
  `food_img` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `food_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `food_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `food_introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT 0.00,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk`(`region_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of food_local
-- ----------------------------
INSERT INTO `food_local` VALUES (46, 2, 'http://localhost:8088/food/1e575bda5987af7cc535434696bad887.jpeg', '冰爽凉粉', '面食类', '冰粉，是著名的夏季小吃，起源于云、贵地区的一道著名小吃，后传入四川彭山（武阳），并在川渝地区受到广泛欢迎。冰凉香甜，嫩滑爽口，生津解暑，清凉降火。作为夏天的一道美食，因其爽滑、透明、冰爽、美味、价廉而倍受人们欢迎。', 0.01);
INSERT INTO `food_local` VALUES (47, 2, 'http://localhost:8088/food/37a307f982f7d6362df575223c4d4fce.jpeg', '横行霸王宴席', '烧烤类', '中国食蟹的历史悠久，《逸周书·五会解》、《周礼·天官·疱人》中均有记载。螃蟹乃食中珍味，素有“一盘蟹，顶桌菜”的民谚。它不但味美，且营养丰富，是一种高蛋白的补品。', 340.00);
INSERT INTO `food_local` VALUES (48, 2, 'http://localhost:8088/food/540c407d6cf8ad35cbd9f74aebacfd61.jpeg', '旋风狮子头', '肉食类', '狮子头是中国淮扬菜系中的一道传统菜肴。传说狮子头做法始于隋朝，是在隋炀帝游幸时，厨师以扬州万松山、金钱墩、象牙林、葵花岗四大名景为主题做成了松鼠桂鱼、金钱虾饼、象牙鸡条和葵花斩肉四道菜，据说原名葵花斩肉、葵花肉丸，唐代郇国公韦陟的家厨韦巨元做松鼠桂鱼，金钱虾饼，象牙鸡条，葵花斩肉四道名菜，令座中宾客叹服，葵花斩肉被改名为狮子头。此菜口感软糯滑腻，健康营养。', 420.00);
INSERT INTO `food_local` VALUES (50, 4, 'http://localhost:8088/food/4739916deb9af92f21c4cb4133f157c5.jpeg', '安徽板面', '面食类', '狮子头是中国淮扬菜系中的一道传统菜肴。传说狮子头做法始于隋朝，是在隋炀帝游幸时，厨师以扬州万松山、金钱墩、象牙林、葵花岗四大名景为主题做成了松鼠桂鱼、金钱虾饼、象牙鸡条和葵花斩肉四道菜，据说原名葵花斩肉、葵花肉丸，唐代郇国公韦陟的家厨韦巨元做松鼠桂鱼，金钱虾饼，象牙鸡条，葵花斩肉四道名菜，令座中宾客叹服，葵花斩肉被改名为狮子头。此菜口感软糯滑腻，健康营养。', 50.00);
INSERT INTO `food_local` VALUES (51, 1, 'http://localhost:8088/food/ab457ac6dacec95c3fa81c3b9c5fb407.jpeg', '鲜滑润喉豆腐乳', '面食类', '腐乳又叫豆腐乳，是中国的传统美食，它的口感好，营养丰富，闻起来臭臭的，但吃起来却让人回味无穷。在那个物质匮乏的年代，有一罐豆腐乳吃，已经是很不错的生活水平了。就算没有菜，只要有腐乳，就可以用它配米饭，配粥，配馒头，一样吃得非常香。腐乳有很多种吃法，直接配饭吃，或者刷火锅做蘸酱，或者是炒菜里面添加点腐乳增香，每一种吃法都让人回味无穷', 55.00);
INSERT INTO `food_local` VALUES (52, 2, 'http://localhost:8088/food/crayfish-866400_1920.jpg', '油炸小龙虾', '肉食类', '小龙虾学名克氏原螯虾，原产自北美洲，1918年由美国引入日本，1929年再由日本引入中国，生长在中国南方的河湖池沼中。 改革开放以来，随着湖南人遍布全国推广湘菜，特别是湖南卫视的传播，麻辣小龙虾（口味虾）风靡一时，众多演艺明星来湖南做节目时必然忘不了吃麻辣小龙虾。 20世纪末，麻辣小龙虾开始传遍全国，成为人们夏夜街边啤酒摊的经典小吃。', 270.00);
INSERT INTO `food_local` VALUES (53, 2, 'http://localhost:8088/food/dimsum-2097947_1920.jpg', '灌汤包', '面食类', '灌汤包，是一种汉族特色小吃。制作原料主要有面粉、温水、猪五花肉等。早在北宋市场上已有售卖，当时称灌浆馒头或灌汤包子。著名产地开封。 [1] ', 360.00);
INSERT INTO `food_local` VALUES (54, 4, 'http://localhost:8088/food/dumplings-632206_1920.jpg', '馄饨', '面食类', '馄饨（汉语拼音hún tun [6]  ；粤语：wɐn3 tɐn1，音同“云吞”；山东话：hún dùn；英文名：Wonton或Huntun），是起源于中国的一种民间传统面食，后分化出饺子。 [1]  [5]  制作馄饨时，先用薄面皮包肉馅儿，下锅后煮熟，食用时一般带汤。', 410.00);
INSERT INTO `food_local` VALUES (55, 1, 'http://localhost:8088/food/pizza-3010062_1920.jpg', '披萨', '面食类', '比萨（Pizza），又称为比萨饼、匹萨、批萨、披萨、意大利馅饼，是一种发源于意大利的食品，在全球颇受欢迎。比萨饼的通常做法是用发酵的圆面饼上面覆盖番茄酱、奶酪以及其他配料，并由烤炉烤制而成。 奶酪通常用马苏里拉干酪，也有混用几种奶酪的形式，包括帕马森干酪、罗马乳酪（romano）、意大利乡村软酪（ricotta）或蒙特瑞·杰克干酪（Monterey Jack）、马苏里拉奶酪等。 [1]  世界上最长的披萨产自美国加州，长达1930米，成为舌尖上的吉尼斯。', 555.00);
INSERT INTO `food_local` VALUES (56, 2, 'http://localhost:8088/food/roasted-duck-1508975_1920.jpg', '北京烤鸭', '肉食类', '烤鸭是具有世界声誉的北京著名菜，起源于中国南北朝时期，《食珍录》中已记有炙鸭，在当时是宫廷名菜。用料为优质肉食鸭，果木炭火烤制，色泽红润，肉质肥而不腻，外脆里嫩。北京烤鸭分为两大流派，而北京最著名的烤鸭店也即是两派的代表。它以色泽红艳，肉质细嫩，味道醇厚，肥而不腻的特色，被誉为“天下美味”。', 541.00);
INSERT INTO `food_local` VALUES (57, 2, 'http://localhost:8088/food/xiaolongxia-008.jpg', '香辣小龙虾', '肉食类', '香辣小龙虾又叫长沙口味虾、麻辣小龙虾、香辣小龙虾、十三香小龙虾，甚至简称麻小。香辣小龙虾主要材料有小龙虾、辣椒等，是湖南省著名传统小吃，以小龙虾制成，口味辣鲜香，色泽红亮，质地滑嫩，滋味香辣。', 421.00);
INSERT INTO `food_local` VALUES (58, 2, 'http://localhost:8088/food/2022-05-24-16-01-17.jpg', '火锅全鱼宴', '汤水类', '火锅一般是指以锅为器具，以热源烧锅，以水或汤烧开来涮煮各类食物的烹调方式，同时亦可指这种烹调方式所用的锅具。其特色为边煮边吃，或是锅本身具有保温效果，吃的时候食物仍热气腾腾，汤物合一。世界各地均有类似的料理，但主要在东亚地方特别盛行。火锅现吃现烫，辣咸鲜，油而不腻，解郁除湿，适于山川之气候，今发展为鸳鸯锅，麻辣、清淡各别，各取所需，根据个人的喜欢加不同的汤料、食物，老少皆宜，至冬之佳品。', 78.00);

-- ----------------------------
-- Table structure for food_message
-- ----------------------------
DROP TABLE IF EXISTS `food_message`;
CREATE TABLE `food_message`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `comment_date` datetime NULL DEFAULT NULL,
  `food_local_id` int NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of food_message
-- ----------------------------
INSERT INTO `food_message` VALUES (1, '真难吃', '2022-04-26 00:00:00', 31, 'zhangsan');
INSERT INTO `food_message` VALUES (2, '是的真难吃', '2022-05-18 00:00:00', 32, 'lisi');
INSERT INTO `food_message` VALUES (3, '服了', '2022-05-23 00:00:00', 31, 'lisi');
INSERT INTO `food_message` VALUES (4, '真尼玛服了', '2022-05-23 00:00:00', 31, 'lisi');
INSERT INTO `food_message` VALUES (5, '好吃', '2022-05-23 00:00:00', 33, 'lisi');
INSERT INTO `food_message` VALUES (6, 'ccc', '2022-05-23 00:00:00', 31, 'lisi');
INSERT INTO `food_message` VALUES (7, '还行', '2022-05-23 00:00:00', 35, 'lisi');
INSERT INTO `food_message` VALUES (8, '味道一级棒', '2022-05-23 00:00:00', 34, 'lisi');
INSERT INTO `food_message` VALUES (10, '真好吃', '2022-05-25 16:16:30', 46, 'lisi');

-- ----------------------------
-- Table structure for food_order
-- ----------------------------
DROP TABLE IF EXISTS `food_order`;
CREATE TABLE `food_order`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `food_local_id` int NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `place_order_datetime` datetime(6) NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `alipay_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_di3h52v749q3fxsf6o7dos6jj`(`number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of food_order
-- ----------------------------
INSERT INTO `food_order` VALUES (5, 33, '41151', '2022-05-24 17:06:22.664000', '2', NULL, NULL, NULL);
INSERT INTO `food_order` VALUES (6, 33, '33647', '2022-05-24 17:11:11.364000', '2', NULL, NULL, NULL);
INSERT INTO `food_order` VALUES (7, 33, '1927', '2022-05-25 09:55:53.754000', '7', NULL, NULL, NULL);
INSERT INTO `food_order` VALUES (10, 46, '49357', '2022-05-25 17:17:55.802000', '26', NULL, NULL, NULL);
INSERT INTO `food_order` VALUES (13, 46, '4749', '2022-05-25 18:05:54.038000', '28', NULL, NULL, NULL);
INSERT INTO `food_order` VALUES (14, 46, '7832', '2022-05-25 18:06:13.893000', '28', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `region_id` int NULL DEFAULT NULL,
  `hotel_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hotel_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hotel_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hotel_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT 0.00,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `region_id`(`region_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES (14, 2, '北京光明路1号', '维也纳酒店', '维也纳酒店创立于1993年，以\"舒适典雅、顶尖美食、品质豪华、安全环保、音乐艺术、引领健康\"为产品核心价值。旗下有维纳斯皇家、维也纳国际、维也纳和3好酒店四大品牌。截止2014年，维也纳酒店拥有超过30000间客房、拥有超过2000万注册会员。已开和拟开的分店网络遍布全国80个大中城市，在全国拥有300多家分店，并以每年新开60-80家分店的速度发展。', 'http://localhost:8088/hotel/src=http___p9.itc.cn_images01_20210514_a1bfbf86d38d4d50b710753dc09c9b83.png&refer=http___p9.itc.webp', 300.00);
INSERT INTO `hotel` VALUES (15, 2, '北京龙坪路3', '汉庭酒店', '汉庭酒店是华住酒店集团的创始品牌。华住创始人季琦先生，在连续成功创办了“携程旅行网”(NASDAQ:CTRP)、“如家快捷酒店”(NASDAQ:HMIN) 两家纳斯达克上市公司之后，于2005年第三次创业，推出汉庭酒店，并曾一度以“汉庭”命名集团，后于2012年正式启用“华住”作为集团名称。', 'http://localhost:8088/hotel/src=http___ak-d.tripcdn.com_images_0206i120008d28jlnA0E2_R_600_400_R5_D.jpg&refer=http___ak-d.tripcdn.webp', 278.00);
INSERT INTO `hotel` VALUES (16, 6, '南京和平路9号', '首旅如家酒店', '首旅如家是首旅如家酒店集团2017年发布的一款app，截止2016年12月，首旅如家酒店集团在国内360余个城市运营近3200余家酒店，覆盖“高端”、“中高端”、“商旅型”、“休闲度假”、“长租公寓”、“联盟酒店”全系列的酒店业务', 'http://localhost:8088/hotel/src=http___p1.itc.cn_images01_20210924_8b69f87c7a4f452f98980041183f99d3.jpeg&refer=http___p1.itc.webp', 350.00);
INSERT INTO `hotel` VALUES (17, 6, '南京松平路3号', '锦江之星', '锦江之星旅馆有限公司系亚洲规模最大的综合性旅游企业集团——锦江国际集团旗下的一家经营管理国内首创、中国最大的经济型连锁旅馆“锦江之星”的专业公司，是亚洲最大酒店集团——上海锦江国际酒店（集团）股份有限公司的子公司。公司创立。', 'http://localhost:8088/hotel/src=http___imgs.ebrun.com_resources_2021_05_2021_05_26_2021052638416219911560574.jpg&refer=http___imgs.ebrun.webp', 400.00);
INSERT INTO `hotel` VALUES (18, 8, '四川', '全季酒店', '全季酒店是华住集团旗下中档酒店品牌 [18]  。已覆盖中国31个省级行政区，截至2021年4月，开业已近1200家。', 'http://localhost:8088/hotel/u=3364180566,2351000762&fm=253&fmt=auto&app=138&f=JPEG.webp', 350.00);
INSERT INTO `hotel` VALUES (19, 8, '四川', '格林豪泰酒店', '格林豪泰（GreenTree Inn）酒店管理集团，是由董事长徐曙光先生率携多家美国公司于2004年11月联手在中国创办的专业化酒店管理集团，现是中国商务连锁酒店的第一品牌。在中国市场，格林豪泰旗下拥有格林东方酒店、格林豪泰酒店、格林联盟酒店、青皮树酒店4个优质品牌及多个子品牌。', 'http://localhost:8088/hotel/u=268400223,3049263935&fm=253&fmt=auto&app=138&f=JPEG.webp', 700.00);
INSERT INTO `hotel` VALUES (20, 10, '安徽', '7天连锁酒店', '7天酒店（7 Days Inn）是锦江酒店（中国区） [1]  旗下经济型酒店品牌 [2-3]  ，成立于2005年，经过快速发展，分店已覆盖全国370个城市 [4-5]  ，规模达到3000家', 'http://localhost:8088/hotel/u=4079482055,4013251831&fm=253&fmt=auto&app=138&f=JPEG.webp', 420.00);
INSERT INTO `hotel` VALUES (21, 10, '安徽', '速8酒店', '速8酒店是世界著名的连锁酒店品牌， [1]  隶属于全球知名的酒店集团——温德姆酒店集团，在美国、加拿大、中国、巴西均有运营中和筹建中的酒店。', 'http://localhost:8088/hotel/src=http___bkimg.cdn.bcebos.com_pic_a50f4bfbfbedab6408d0913bf736afc378311eae&refer=http___bkimg.cdn.bcebos.webp', 410.00);
INSERT INTO `hotel` VALUES (22, 3, '武汉', '尚客优酒店', '尚客优成立于2009年 [1]  ，隶属于尚美生活集团旗下，定位于三四线城市连锁酒店 [2]  。酒店通过设计美、美睡眠的“美美睡”品牌理念，为用户在旅途中提供安静、舒适的酒店睡眠环境', 'http://localhost:8088/hotel/src=http___ak-d.tripcdn.com_images_0201x1200090mi7txE325_R_600_400_R5_D.jpg&refer=http___ak-d.tripcdn.webp', 520.00);
INSERT INTO `hotel` VALUES (23, 3, '武汉', '都市118酒店', '都市118连锁酒店是一家以酒店业为主，涉足多个领域的大型综合性企业，其注册母公司为山东星辉盈联酒店投资有限公司。集团旗下拥有含舍hotel、都市118·精选连锁酒店、都市花园连锁酒店、都市118连锁酒店、青年·都市迷 你连锁酒店、我的地盘主题酒店、都市MINI·精选连锁酒店、单色等八大连锁酒店品牌和MY ZONE COFFEE（我的地盘咖啡）咖啡品牌 [1]  以及都市酒店管理学院。', 'http://localhost:8088/hotel/u=1196397784,2930867335&fm=253&fmt=auto&app=138&f=JPEG.webp', 1000.00);
INSERT INTO `hotel` VALUES (24, 9, '江苏', '民居', '中国各地的居住建筑，又称民居。民居包含住宅以及由其延伸的居住环境。居住建筑是最基本的建筑类型，在世界建筑史上出现最早，分布最广，数量也最多。由于中国疆域辽阔，民族众多，历史悠久，各地的地理气候条件和生活方式不尽相同，因此，中国的民居建筑样式和风格在世界建筑史上也较为鲜见。', 'http://localhost:8088/hotel/src=http___pic1.zhimg.com_v2-f613ac1768d9b2ab57de7dceebe8d56a_1440w.jpg_source=172ae18b&refer=http___pic1.zhimg.webp', 70.00);
INSERT INTO `hotel` VALUES (25, 2, '北京', '四合院', '四合院，又称四合房，是中国的一种传统合院式建筑，其格局为一个院子四面建有房屋，从四面将庭院合围在中间，故名四合院。', 'http://localhost:8088/hotel/u=2626160107,1002642497&fm=253&fmt=auto&app=138&f=JPEG.webp', 5000.00);

-- ----------------------------
-- Table structure for hotel_collection
-- ----------------------------
DROP TABLE IF EXISTS `hotel_collection`;
CREATE TABLE `hotel_collection`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `hotel_id` int NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel_collection
-- ----------------------------
INSERT INTO `hotel_collection` VALUES (1, NULL, NULL);
INSERT INTO `hotel_collection` VALUES (2, 4, 'lisi');
INSERT INTO `hotel_collection` VALUES (3, 4, '陈浩');
INSERT INTO `hotel_collection` VALUES (4, 4, '陈浩');

-- ----------------------------
-- Table structure for hotel_message
-- ----------------------------
DROP TABLE IF EXISTS `hotel_message`;
CREATE TABLE `hotel_message`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `comment_date` datetime NULL DEFAULT NULL,
  `hotel_id` int NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel_message
-- ----------------------------
INSERT INTO `hotel_message` VALUES (1, '一点都不舒服', '2022-05-24 17:22:13', 4, '陈浩');
INSERT INTO `hotel_message` VALUES (3, '对对对', '2022-05-24 17:25:22', 4, '陈浩');
INSERT INTO `hotel_message` VALUES (4, '挺舒服的', '2022-05-25 16:14:52', 15, 'lisi');
INSERT INTO `hotel_message` VALUES (5, 'asxasxa', '2022-05-25 18:09:50', 14, '陈浩');

-- ----------------------------
-- Table structure for hotel_order
-- ----------------------------
DROP TABLE IF EXISTS `hotel_order`;
CREATE TABLE `hotel_order`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `hotel_id` int NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `place_order_datetime` datetime(6) NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `alipay_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_f0cl358m048s4gx6jiy0owhkq`(`number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel_order
-- ----------------------------
INSERT INTO `hotel_order` VALUES (4, 1, '酒店订单3', '2022-05-09 11:18:16.227000', '1', NULL, NULL, NULL);
INSERT INTO `hotel_order` VALUES (5, 4, '97566', '2022-05-24 17:08:43.487000', '2', NULL, NULL, NULL);
INSERT INTO `hotel_order` VALUES (7, 4, '33546', '2022-05-25 10:02:24.552000', '7', NULL, NULL, NULL);
INSERT INTO `hotel_order` VALUES (8, 16, '34072', '2022-05-25 10:45:33.930000', '7', NULL, NULL, NULL);
INSERT INTO `hotel_order` VALUES (9, 15, '27330', '2022-05-25 17:27:02.985000', '27', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1-1', '管理员查询', 'admin:query');
INSERT INTO `permission` VALUES ('1-2', '管理员修改', 'admin:update');
INSERT INTO `permission` VALUES ('1-3', '管理员删除', 'admin:delete');
INSERT INTO `permission` VALUES ('1-4', '管理员添加', 'admin:insert');
INSERT INTO `permission` VALUES ('2-1', '用户查询', 'user:query');
INSERT INTO `permission` VALUES ('2-2', '用户修改', 'user:update');
INSERT INTO `permission` VALUES ('2-3', '用户删除', 'user:delete');
INSERT INTO `permission` VALUES ('2-4', '用户添加', 'user:insert');
INSERT INTO `permission` VALUES ('3-1', '超级管理员查询', 'superAdmin:query');
INSERT INTO `permission` VALUES ('3-2', '超级管理员查询', 'superAdmin:update');
INSERT INTO `permission` VALUES ('3-3', '超级管理员查询', 'superAdmin:delete');
INSERT INTO `permission` VALUES ('3-4', '超级管理员查询', 'superAdmin:insert');

-- ----------------------------
-- Table structure for region
-- ----------------------------
DROP TABLE IF EXISTS `region`;
CREATE TABLE `region`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `region_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_bstmen2pk463hcijuosun6g72`(`region_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of region
-- ----------------------------
INSERT INTO `region` VALUES (2, '北京');
INSERT INTO `region` VALUES (6, '南京');
INSERT INTO `region` VALUES (8, '四川');
INSERT INTO `region` VALUES (10, '安徽');
INSERT INTO `region` VALUES (3, '武汉');
INSERT INTO `region` VALUES (9, '江苏');
INSERT INTO `region` VALUES (7, '湖南');
INSERT INTO `region` VALUES (1, '芜湖');
INSERT INTO `region` VALUES (4, '蒙城');
INSERT INTO `region` VALUES (5, '重庆');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '普通用户', 'user');
INSERT INTO `role` VALUES (2, '管理员', 'admin');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `permission_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, '1-1', 2);
INSERT INTO `role_permission` VALUES (2, '1-2', 2);
INSERT INTO `role_permission` VALUES (3, '1-3', 2);
INSERT INTO `role_permission` VALUES (4, '1-4', 2);
INSERT INTO `role_permission` VALUES (5, '2-1', 1);
INSERT INTO `role_permission` VALUES (6, '2-2', 1);
INSERT INTO `role_permission` VALUES (7, '2-3', 1);
INSERT INTO `role_permission` VALUES (8, '2-4', 1);
INSERT INTO `role_permission` VALUES (9, '2-1', 2);
INSERT INTO `role_permission` VALUES (10, '2-2', 2);
INSERT INTO `role_permission` VALUES (11, '2-3', 2);
INSERT INTO `role_permission` VALUES (12, '2-4', 2);

-- ----------------------------
-- Table structure for scenic
-- ----------------------------
DROP TABLE IF EXISTS `scenic`;
CREATE TABLE `scenic`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `region_id` int NULL DEFAULT NULL,
  `scenic_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scenic_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scenic_introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT 0.00,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of scenic
-- ----------------------------
INSERT INTO `scenic` VALUES (13, 2, 'http://localhost:8088/scenic/2022-05-24-20-04-16.jpg', '长城', '长城（the Great Wall [122]  ），又称万里长城，是中国古代的军事防御工事，是一道高大、坚固而且连绵不断的长垣，用以限隔敌骑的行动。长城不是一道单纯孤立的城墙，而是以城墙为主体，同大量的城、障、亭、标相结合的防御体系。', 175.00);
INSERT INTO `scenic` VALUES (14, 2, 'http://localhost:8088/scenic/2022-05-24-19-55-20.jpg', '天安门', '天安门（Tian\'anmen），坐落在中华人民共和国首都北京市的中心、故宫的南端，与天安门广场以及人民英雄纪念碑、毛主席纪念堂、人民大会堂、中国国家博物馆隔长安街相望，占地面积4800平方米，以杰出的建筑艺术和特殊的政治地位为世人所瞩目。', 255.00);
INSERT INTO `scenic` VALUES (15, 2, 'http://localhost:8088/scenic/2022-05-24-19-54-31.jpg', '天坛', '天坛公园在北京市南部，东城区永定门内大街东侧。占地约273万平方米。天坛始建于明永乐十八年（1420年），清乾隆、光绪时曾重修改建。现为世界文化遗产，全国重点文物保护单位，国家AAAAA级旅游景区，全国文明风景旅游区示范点。', 355.00);
INSERT INTO `scenic` VALUES (16, 2, 'http://localhost:8088/scenic/2022-05-24-19-54-50.jpg', '故宫', '北京故宫是中国明清两代的皇家宫殿，旧称紫禁城，位于北京中轴线的中心。北京故宫以三大殿为中心，占地面积约72万平方米，建筑面积约15万平方米，有大小宫殿七十多座，房屋九千余间。', 425.00);
INSERT INTO `scenic` VALUES (17, 6, 'http://localhost:8088/scenic/2022-05-24-20-13-59.jpg', '南京总统府', '南京总统府位于南京市玄武区长江路292号，是中国近代建筑遗存中规模最大、保存最完整的建筑群，也是南京民国建筑的主要代表之一，中国近代历史的重要遗址。南京总统府自近代以来，多次成为中国政治军事的中枢、重大事件的策源地，中国一系列重大事件或在这里发生，或与这里密切相关，许多重要人物都在此活动过。', 148.00);
INSERT INTO `scenic` VALUES (18, 6, 'http://localhost:8088/scenic/2022-05-24-20-09-48.jpg', '夫子庙', '南京夫子庙位于南京市秦淮区秦淮河北岸贡院街、江南贡院以西，地处夫子庙秦淮风光带核心区，即南京孔庙、南京文庙、文宣王庙，为供奉祭祀孔子之地，是中国第一所国家最高学府、中国四大文庙之一，中国古代文化枢纽之地、金陵历史人文荟萃之地，不仅是明清时期南京的文教中心，同时也是居东南各省之冠的文教建筑群。', 120.00);
INSERT INTO `scenic` VALUES (19, 6, 'http://localhost:8088/scenic/2022-05-24-20-12-02.jpg', '鸡鸣寺', '鸡鸣寺（Jiming Temple）位于南京市玄武区鸡笼山东麓山阜上，又称古鸡鸣寺，始建于西晋永康元年（300年），已有一千七百多年的历史，是南京最古老的梵刹和皇家寺庙之一，香火一直旺盛不衰，自古有“南朝第一寺”，“南朝四百八十寺”之首的美誉，南朝时期与栖霞寺、定山寺齐名，是南朝时期中国的佛教中心。', 110.00);
INSERT INTO `scenic` VALUES (20, 3, 'http://localhost:8088/scenic/2022-05-24-20-19-08.jpg', '黄鹤楼', ' 黄鹤楼是蜚声中外的历史名胜，与湖南岳阳楼、江西滕王阁、山东蓬莱阁合称中国四大名楼，号称\"天下江山第一楼\"。它雄踞长江之滨，蛇山之首，背倚万户林立的武昌城，面临汹涌浩荡的扬子江，相对古雅清俊晴川阁，刚好位于长江和京广线的交叉处，即东西水路与南北陆路的交汇点上。历代名士，崔颢、李白、白居易、贾岛、陆游、杨慎、张居正等都先后到这里游乐，吟诗作赋。', 135.00);
INSERT INTO `scenic` VALUES (21, 7, 'http://localhost:8088/scenic/2022-05-24-20-25-04.jpg', '张家界', '张家界，原称“大庸”，湖南省辖地级市，位于湖南西北部，澧水中上游，属武陵山区腹地， [1]  总面积9516平方千米，辖2个市辖区（永定区、武陵源区）、2个县（慈利县、桑植县） [61]  ；因旅游建市，是中国最重要的旅游城市之一，是湘鄂渝黔革命根据地的发源地和中心区域。2021年，张家界市实现地区生产总值580.29亿元，', 55.00);
INSERT INTO `scenic` VALUES (22, 8, 'http://localhost:8088/scenic/2022-05-24-20-26-12 (1).jpg', '乐山大佛', '乐山大佛，又名凌云大佛，位于四川省乐山市南岷江东岸凌云寺侧，濒大渡河、青衣江和岷江三江汇流处。大佛为弥勒佛坐像，通高71米，是中国最大的一尊摩崖石刻造像。', 75.00);
INSERT INTO `scenic` VALUES (23, 9, 'http://localhost:8088/scenic/2022-05-24-20-25-38 (1).jpg', '苏州园林', '苏州古典园林，亦称“苏州园林”，是位于江苏省苏州市境内的中国古典园林的总称。苏州古典园林溯源于春秋，发展于晋唐，繁荣于两宋，全盛于明清。 [1]  苏州素有“园林之城”的美誉，境内私家园林始建于前6世纪，清末时城内外有园林170多处，现存50多处。', 115.00);
INSERT INTO `scenic` VALUES (24, 10, 'http://localhost:8088/scenic/2022-05-24-20-29-26 (1).jpg', '黄山迎客松', '迎客松在黄山玉屏楼右侧、文殊洞之上，倚青狮石破石而生，高10米，胸径0.64米，地径75厘米，枝下高2.5米，树龄至少已有1300年，黄山“四绝”之一。其一侧枝桠伸出，如人伸出一只臂膀欢迎远道而来的客人，另一只手优雅地斜插在裤兜里，雍容大度，姿态优美。', 131.00);

-- ----------------------------
-- Table structure for scenic_collection
-- ----------------------------
DROP TABLE IF EXISTS `scenic_collection`;
CREATE TABLE `scenic_collection`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `scenic_id` int NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenic_collection
-- ----------------------------
INSERT INTO `scenic_collection` VALUES (2, 1, 'zhangsan');
INSERT INTO `scenic_collection` VALUES (4, 4, 'zhangsan');
INSERT INTO `scenic_collection` VALUES (5, 1, 'zhangsan');
INSERT INTO `scenic_collection` VALUES (6, 3, '陈浩');
INSERT INTO `scenic_collection` VALUES (7, 3, '陈浩');
INSERT INTO `scenic_collection` VALUES (8, 14, 'lisi');
INSERT INTO `scenic_collection` VALUES (9, 13, '陈浩');

-- ----------------------------
-- Table structure for scenic_message
-- ----------------------------
DROP TABLE IF EXISTS `scenic_message`;
CREATE TABLE `scenic_message`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `comment_date` datetime NULL DEFAULT NULL,
  `scenic_id` int NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenic_message
-- ----------------------------
INSERT INTO `scenic_message` VALUES (1, '真美', '2022-05-23 00:00:00', 3, 'lisi');
INSERT INTO `scenic_message` VALUES (3, 'www', '2022-05-24 17:45:52', 2, '陈浩');
INSERT INTO `scenic_message` VALUES (5, '娃娃', '2022-05-24 17:46:06', 2, '陈浩');
INSERT INTO `scenic_message` VALUES (6, '真的好壮观', '2022-05-25 16:17:55', 15, 'lisi');
INSERT INTO `scenic_message` VALUES (7, '下次还去\n', '2022-05-25 16:18:09', 15, 'lisi');
INSERT INTO `scenic_message` VALUES (8, 'jisahsa oc', '2022-05-25 16:44:30', 14, 'lisi');
INSERT INTO `scenic_message` VALUES (10, 'jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj', '2022-05-25 18:09:08', 13, '陈浩');

-- ----------------------------
-- Table structure for scenic_order
-- ----------------------------
DROP TABLE IF EXISTS `scenic_order`;
CREATE TABLE `scenic_order`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `place_order_datetime` datetime(6) NULL DEFAULT NULL,
  `scenic_id` int NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `alipay_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_sqvk6cfof8nd5fih2k07ues1p`(`number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenic_order
-- ----------------------------
INSERT INTO `scenic_order` VALUES (4, '93327', '2022-05-24 17:08:32.006000', 2, '2', NULL, NULL, NULL);
INSERT INTO `scenic_order` VALUES (7, '23295', '2022-05-25 18:09:13.752000', 13, '29', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_children_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_children_menu`;
CREATE TABLE `sys_children_menu`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `father_menu_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_3qwn0f1pcr8r71v2arpld7hnd`(`component`) USING BTREE,
  UNIQUE INDEX `UK_o2mpk4ybdixhext91jwsqw617`(`name`) USING BTREE,
  UNIQUE INDEX `UK_527ly97ojb69yl2j3mcw7u046`(`path`) USING BTREE,
  UNIQUE INDEX `UK_8s3vmhk7homwf9dgcyd6a0kg`(`title`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_children_menu
-- ----------------------------
INSERT INTO `sys_children_menu` VALUES (1, '用户管理', 'UserManage', 'el-icon-user-solid', '/backstage/UserManage', '/backstage/userManage', 1);
INSERT INTO `sys_children_menu` VALUES (2, '住宿管理', 'HotelManage', 'el-icon-s-release', '/backstage/HotelManage', '/backstage/hotelManage', 1);
INSERT INTO `sys_children_menu` VALUES (3, '美食管理', 'FoodManage', 'el-icon-s-shop', '/backstage/FoodManage', '/backstage/foodManage', 1);
INSERT INTO `sys_children_menu` VALUES (4, '路线管理', 'planManage', 'el-icon-s-promotion', '/backstage/PlanManage', '/backstage/planManage', 1);
INSERT INTO `sys_children_menu` VALUES (5, '景区管理', 'scenicManage', 'el-icon-s-flag', '/backstage/ScenicManage', '/backstage/scenicManage', 1);
INSERT INTO `sys_children_menu` VALUES (6, '景区预定管理', 'scenicOrderManage', 'el-icon-tickets', '/backstage/order/ScenicOrderManage', '/backstage/order/scenicOrderManage', 2);
INSERT INTO `sys_children_menu` VALUES (7, '酒店预定管理', 'hotelOrderManage', 'el-icon-school', '/backstage/order/HotelOrderManage', '/backstage/order/hotelOrderManage', 2);
INSERT INTO `sys_children_menu` VALUES (8, '美食预定管理', 'foodOrderManage', 'el-icon-dish', '/backstage/order/FoodOrderManage', '/backstage/order/foodOrderManage', 2);

-- ----------------------------
-- Table structure for sys_father_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_father_menu`;
CREATE TABLE `sys_father_menu`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_iclclyc2yf70ld40ojvngnlll`(`name`) USING BTREE,
  UNIQUE INDEX `UK_5tsxqlvka75x97591qm4aifmd`(`title`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_father_menu
-- ----------------------------
INSERT INTO `sys_father_menu` VALUES (1, 'el-icon-s-tools', 'SysManage', '系统管理');
INSERT INTO `sys_father_menu` VALUES (2, 'el-icon-menu', 'OrderManage', '订单管理');

-- ----------------------------
-- Table structure for tourist_routes
-- ----------------------------
DROP TABLE IF EXISTS `tourist_routes`;
CREATE TABLE `tourist_routes`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `region_id` int NULL DEFAULT NULL,
  `end_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pass_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tourist_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lnglat1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lnglat2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lnglat3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tourist_routes
-- ----------------------------
INSERT INTO `tourist_routes` VALUES (1, 1, '张家界大峡谷玻璃桥', '张家界十里画廊', ' 金鞭溪', '张家界', '早餐后出发十里画廊景区观看食指峰，三姊妹，采药老人等峡谷风光，后游览金鞭溪景区潺潺而流的溪水，是夏日避暑圣地。下午体验首座跨峡谷玻璃桥，也是来张家界必打卡的网红景点。', 'http://localhost:8088/touristRoutes/1.1.png', '110.49236,29.371994', '110.476504,29.348445', '110.707724,29.401329', NULL);
INSERT INTO `tourist_routes` VALUES (2, 2, '古文化街', '津湾广场', '天津意式风情区', '天津文化', '上午在天津站周边逛世纪钟、解放桥、津湾广场三处紧邻的景点。中午到意大利风情街吃饭，可以欣赏到欧式小洋楼，各种角度都适合拍照哦。下午在古文化街里，赏泥人张彩塑，看杨柳青年画，品大碗茶，把玩古玩字画等，感受天津古文化。晚上可以乘坐天津之眼，欣赏海河的夜色。因时常需要排队，最好避开节假日，傍晚之前到达，摩天轮30分钟旋转一周。回到酒店附近时可以在到邻近的津湾广场看下夜景。', 'http://localhost:8088/touristRoutes/2.1.png', '117.21447,39.137217', '117.204183,39.140575', '117.199184,39.149532', NULL);
INSERT INTO `tourist_routes` VALUES (3, 3, '泗溪廊桥风景区', '乌岩岭国家级自然保护区', '南浦溪景区', '丽江温州', '乌岩岭山地生态系统完整，山岳风光齐全，自然景象美丽，山原村舍野素，民风民俗浓郁。', 'http://localhost:8088/touristRoutes/3.1.png', '119.668984,27.716821', '119.948363,27.607677', '120.006169,27.476248', NULL);
INSERT INTO `tourist_routes` VALUES (4, 4, '三塔倒影公园', '大理古城', '崇圣寺三塔文化旅游区', '大理古城', '今日到达大理后，先游玩大理古城。漫步在古城的各条街道，感受古城的生活气息；之后前往崇圣寺三塔公园，近距离接触大理国时期皇家寺院仅存的古迹；再到三塔南侧的三塔倒影公园，这里可以拍到三塔在水潭里的倒影，最佳拍摄时间是16:00-17:00。傍晚回到大理古城，开始大理的夜生活，夜间的人民路才真正的热闹起来。', 'http://localhost:8088/touristRoutes/4.1.png', '100.170478,25.700801', '100.149979,25.710056', '100.156953,25.707528', NULL);
INSERT INTO `tourist_routes` VALUES (5, 5, '神堂峪自然风景区', '白河湾', '白河北村', '探寻白河之美', '从北京市区出发，一路向北，从钢筋水泥的城市心脏到120公里开外的白河峡谷，纵深山水之间，感受自然之美。', 'http://localhost:8088/touristRoutes/5.1.png', '116.696001,40.677708', '116.688568,40.669949', '116.61996,40.445536', NULL);
INSERT INTO `tourist_routes` VALUES (6, 6, '惠远寺', '甲居藏寨', '塔公草原', '四川稻城亚丁环线', '今天前往中国最美丽的村庄-甲居蔵寨。远眺被藏族人民誉为神山的-雅拉雪山，前往游览有\"小大昭寺\"之称的塔公寺，观木雅金塔，一路前行至摄影天堂新都桥。', 'http://localhost:8088/touristRoutes/6.png', '101.880837,30.923236', '101.561489,30.335754', '101.501764,30.567204', NULL);
INSERT INTO `tourist_routes` VALUES (7, 7, '昌辉鸡饭店', '海南铜鼓岭景区', '东郊椰林风景区', '海南逐浪度假线 ', '文昌有两宝——文昌鸡和文昌卫星发射基地。这里还拥有琼东第一峰铜鼓岭，能登高俯瞰湛蓝的月亮湾。而东郊椰林有着中国最大的椰子树群，可尽情实现“椰子自由”。', 'http://localhost:8088/touristRoutes/7.png', '111.028365,19.674434', '110.870755,19.536883', '110.761467,19.604544', NULL);
INSERT INTO `tourist_routes` VALUES (8, 8, '侨福楼', '福建土楼永定景区', '土楼王·承启楼', '高北土楼', '早上睡懒觉，或者自由参观塔下村感受宁静的小桥流水人家气息，午餐后游览永定高北土楼景区，穿梭在福建王承启楼四层四圈四百间的宏大规模里，感受客家人的智慧。同时还有1楼11博士的侨福楼、正方形的世泽楼。参观完毕后后返回厦门。', 'http://localhost:8088/touristRoutes/8.png', '116.981453,24.657901', '117.015539,24.66697', '117.01484,24.66718', NULL);
INSERT INTO `tourist_routes` VALUES (9, 9, '东关街历史街区', '瘦西湖', '个园', '春行江苏浪漫赏樱花', '春天到瘦西湖赏百花，尤其扬州独有的琼花，扬州的春天符合人们对江南的印象，到扬州最美园林个园，参观江南古色古香园林景观。', 'http://localhost:8088/touristRoutes/9.png', '119.428092,32.41478', '119.450355,32.404945', '119.449777,32.40225', NULL);
INSERT INTO `tourist_routes` VALUES (10, 10, '御水古温泉', '浪漫海岸国际旅游度假区', '温德姆酒店望海餐厅', '食在粤西南 ', '茂名电白坐拥浪漫海岸与御水古温泉两大旅游区，这里沙白细腻，有诸多度假型酒店。多日的旅途疲劳后，不妨在此享受“小马尔代夫”的浪漫。', 'http://localhost:8088/touristRoutes/10.png', '111.148858,21.46929', '111.15005,21.468733', '111.186059,21.593906', NULL);
INSERT INTO `tourist_routes` VALUES (11, 11, '金坑梯田', '龙脊梯田', '龙脊大寨', '少数民族风情', '上午从桂林市区驾车到龙脊梯田景区，再到龙脊景区中唯一有缆车上下山的金坑大寨梯田，可根据个人体力选择缆车或者步行上山，直达最高观景点“金佛顶”，在上面可以俯瞰整个梯田景观，360°感受大自然的结晶与劳动的智慧。', 'http://localhost:8088/touristRoutes/11.png', '110.146532,25.776829', '110.169629,25.811687', '110.158796,25.811731', NULL);
INSERT INTO `tourist_routes` VALUES (12, 12, '姊妹湖', '红草地', '毛垭大草原', '芒康', '前往稻城红草地，接着来到毛垭大草原，沿318国道翻越海子山，山上布满了第四季冰期留下的古冰体遗迹，其中姊妹湖就是冰融湖，雪山雄壮，海子蔚蓝清澈。下午路过巴塘，来到芒康县城，芒康就是进入西藏后的第一个县城。', 'http://localhost:8088/touristRoutes/12.png', '100.239814,29.109192', '99.6854,30.300105', '99.554108,30.287875', NULL);
INSERT INTO `tourist_routes` VALUES (13, 13, '陡坡塘瀑布', '黄果树瀑布', '天星桥风景区', '贵州', '贵州之间景点分散，若想在景点游玩时间更为充足，则需要早些出发，建议提前一天到达贵阳，从贵阳到黄果树瀑布车程大约1个半小时，黄果树景区较大，需乘坐观光车，其中天星桥精华景点皆在下半段，所以一定要走全程。', 'http://localhost:8088/touristRoutes/13.png', '105.679142,25.985778', '105.68322,25.94822', '105.683558,26.003546', NULL);
INSERT INTO `tourist_routes` VALUES (14, 14, '雅丹国家地质公园', '河仓城', '汉代长城遗址', '敦煌', '今日出发前往甘肃境内，在中午之前到达玉门关，需注意的是，从大柴旦前往路途较远，且经过多个无人区，若是自驾需做好应急准备。玉门关是前往雅丹地质公园的必经场所，与汉长城、河仓城在一起，可以一同参观。下午在雅丹地质公园游览，因时常会有劲风恐怖的啸叫所以又称为“魔鬼城”，这里是典型的风蚀性地貌，十分壮阔，需要乘坐景区巴士游玩，注意不要走到无人区。', 'http://localhost:8088/touristRoutes/14.png', '93.977471,40.415224', '93.822298,40.362975', '93.246061,40.519512', NULL);
INSERT INTO `tourist_routes` VALUES (16, 16, '烟台月亮湾', '八仙过海景区(', '东炮台海滨风景区', '蓬莱 烟台 青岛', '围绕蓬莱八仙主题游览，上午游览5A景区八仙过海景区，下午游览烟台东炮台+月亮湾景区。最后返回青岛', 'http://localhost:8088/touristRoutes/16.png', '120.782709,37.831199', '121.442644,37.540758', '121.44019,37.541578', NULL);
INSERT INTO `tourist_routes` VALUES (17, 17, '云冈石窟', '恒山', '悬空寺', '大同', '早晨从太原出发到有\"人天北柱,绝塞名山\"之称的北岳恒山，参观世界建筑奇葩悬空寺，后游览世界文化遗产北魏时期的佛教艺术宝库云冈石窟。', 'http://localhost:8088/touristRoutes/17.png', '113.731052,39.667618', '113.721979,39.666616', '113.144291,40.119273', NULL);
INSERT INTO `tourist_routes` VALUES (18, 18, '张家界黄龙洞', '毛泽东铜像', '毛泽东同志故居', '长沙 韶山 张家界', '早上前往伟人故居—韶山，游览毛主席铜像，毛主席故居，后乘车赴张家界，沿途欣赏有八百里之称洞庭湖平原风光。抵达张家界后游览世界溶洞全能冠军黄龙洞', 'http://localhost:8088/touristRoutes/18.png', '112.496037,27.910273', '112.49946,27.914741', '110.624985,29.373999', NULL);
INSERT INTO `tourist_routes` VALUES (19, 19, '武昌起义军政府旧址', '黄鹤楼', '辛亥革命博物馆', '武汉', '今天前往武汉绝对的地标建筑黄鹤楼，之后前往辛亥革命博物馆和武昌起义军政府旧址，这里见证了清政府的覆灭、辛亥革命的爆发；昙华林，这里集中了几十处近百年的武汉老建筑，晚上前往楚河汉街﻿。', 'http://localhost:8088/touristRoutes/19.png', '114.309043,30.550317', '114.312357,30.542703', '114.312826,30.547562', NULL);
INSERT INTO `tourist_routes` VALUES (20, 20, '屯溪老街', '宏村', '西递', '宏村', '宏村是徽派民居里最入画的，被称为“中国画里的乡村”。清晨和黄昏是最佳的拍摄时间，人少景好～屯溪老街和黎阳水街被誉为“徽州的丽江古城”，有数不清的饭馆与茶舍，在这里可以品尝到徽州特色美食，感受小城人的慢生活慢时光～', 'http://localhost:8088/touristRoutes/20.png', '117.996734,30.008708', '118.00355,29.910232', '118.315383,29.715124', NULL);
INSERT INTO `tourist_routes` VALUES (21, 21, '三清山-西海岸景区', '三清山风景名胜区', '三清山-南清园景区', '三清山 南昌', '早餐后登游步道步行或乘索道上山，游南\r\n清园景区，西海岸景区，阳光海岸景区，之后游步道步行或乘索道下山后返回南昌市区。', 'http://localhost:8088/touristRoutes/21.png', '118.076681,28.911597', '118.076626,28.911592', '118.066692,28.91057', NULL);
INSERT INTO `tourist_routes` VALUES (22, 22, '泉瀑峡', '云台山', '潭瀑峡', '河南', '早起喝一碗胡辣汤，从郑州出发前往“北方的九寨沟”之称的云台山，这里有红色丹霞绝壁，亚洲单级落差最大的瀑布，被联合国教科文组织评选为全球首批世界地质公园。', 'http://localhost:8088/touristRoutes/22.png', '113.382019,35.461608', '113.351226,35.474418', '113.349358,35.474337', NULL);
INSERT INTO `tourist_routes` VALUES (23, NULL, '额尔古纳湿地', '呼伦贝尔大草原', '莫尔格勒河', '海拉尔 额尔古纳', '早上深入呼伦贝尔草原腹地，看野花遍地，牛羊成群，学习成吉思汗文化；随后前往宝藏村落，在额尔古纳湿地，一次览遍森林、河流、沼泽、山地等自然风情。', 'http://localhost:8088/touristRoutes/23.png', '117.95893,49.493591', '119.78542,49.517765', '120.157973,50.253244', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` int NULL DEFAULT 1,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_dnq7r8jcmlft7l8l4j79l1h74`(`account`) USING BTREE,
  UNIQUE INDEX `UK_ob8kqyqqgmefl0aco34akdtpe`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'lisi', 'http://localhost:8088/user/imgs1e575bda5987af7cc535434696bad887.jpeg', '3037164535@qq.com', 'jNS0WVUNP7FPi8PcXKQE5dxTM0Nd1wr32igqQQCOkB0=', 2, '22e09821-1bce-406b-9193-40c109a5e17e', '李四');
INSERT INTO `user` VALUES (3, 'wangwu', NULL, NULL, 'ixuiZWtXIbliPbBXLcLzqaRiihAw5IHmAX8IdxC2lu4=', 3, 'c18ae5af-a90f-4144-86fe-a341b9a2925c', '王五');
INSERT INTO `user` VALUES (4, 'mazi', 'http://localhost:8088/user/imgs14.png', 'xaxXAXXXXXA', '732L/E2dsJRCxRJngx8q+ZzndiVvjY5HQthsGwUHDQU=', 2, '602a826a-745b-4022-aa8d-53fb850eb6d1', '麻子');
INSERT INTO `user` VALUES (6, '许菲', NULL, '1690571959@qq.com', '9y8RJKkNiym22gmpYclEBBipTGiHhuGW8uBwQLO7QhI=', 2, 'e898029d-addc-4b9a-9ca5-8b2d938edfcb', 'xf');
INSERT INTO `user` VALUES (29, '陈浩', 'http://localhost:8088/user/imgs2022-05-24-20-26-12 (1).jpg', '2658414819@qq.com', '8EAXEMuQ0nbjsSOeJTOY0MSkQ2iaCR1W8JVmPSBJ0nc=', 1, '1a466798-cb5b-4bb0-9de5-9c3fd5aaf08d', NULL);

SET FOREIGN_KEY_CHECKS = 1;
