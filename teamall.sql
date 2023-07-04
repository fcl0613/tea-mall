/*
 Navicat Premium Data Transfer

 Source Server         : fcl
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : teamall

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 04/07/2023 16:56:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `link_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人',
  `link_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '城市',
  `county` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区县',
  `province_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `city_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `county_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_address
-- ----------------------------
INSERT INTO `t_address` VALUES (3, '啊达瓦达瓦发', 4, 'wwx', '13565485462', '浙江省', '杭州市', '余杭区', '330000', '330100', '330110');
INSERT INTO `t_address` VALUES (4, '大大吴大维分色法', 4, 'wwx', '18768545147', '浙江省', '杭州市', '余杭区', '330000', '330100', '330110');
INSERT INTO `t_address` VALUES (5, '达娃大地方都是', 6, '住宿费', '15448454151', '福建省', '莆田市', '荔城区', '350000', '350300', '350304');

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin', '13878541265', NULL, '2023-02-19 21:01:50', '2023-02-19 21:01:50');

-- ----------------------------
-- Table structure for t_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_banner`;
CREATE TABLE `t_banner`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `banner_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `banner_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `publish_status` tinyint(3) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_banner
-- ----------------------------
INSERT INTO `t_banner` VALUES (3, '达到五成', '1679742928877.jpg', 0);

-- ----------------------------
-- Table structure for t_cart
-- ----------------------------
DROP TABLE IF EXISTS `t_cart`;
CREATE TABLE `t_cart`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `user_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `goods_count` int(11) NULL DEFAULT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_cart
-- ----------------------------
INSERT INTO `t_cart` VALUES (2, 6, 2, 1);
INSERT INTO `t_cart` VALUES (13, 4, 4, 1);

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) UNSIGNED NULL DEFAULT NULL,
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES (1, 0, '绿茶');
INSERT INTO `t_category` VALUES (2, 0, '红茶');
INSERT INTO `t_category` VALUES (4, 1, '龙井');
INSERT INTO `t_category` VALUES (5, 1, '黄山毛峰');
INSERT INTO `t_category` VALUES (6, 1, '碧螺春');
INSERT INTO `t_category` VALUES (7, 1, '雀舌');
INSERT INTO `t_category` VALUES (8, 1, '太平猴魁');
INSERT INTO `t_category` VALUES (9, 1, '六安瓜片');
INSERT INTO `t_category` VALUES (10, 1, '安吉白茶');
INSERT INTO `t_category` VALUES (11, 0, '乌龙茶');
INSERT INTO `t_category` VALUES (12, 11, '铁观音');
INSERT INTO `t_category` VALUES (13, 11, '武夷岩茶');
INSERT INTO `t_category` VALUES (14, 11, '漳平水仙');
INSERT INTO `t_category` VALUES (15, 2, '金骏眉');
INSERT INTO `t_category` VALUES (16, 2, '正山小种');
INSERT INTO `t_category` VALUES (17, 2, '云南滇红');
INSERT INTO `t_category` VALUES (18, 2, '祁门红茶');
INSERT INTO `t_category` VALUES (19, 2, '政和工夫');
INSERT INTO `t_category` VALUES (20, 2, '坦洋工夫');
INSERT INTO `t_category` VALUES (21, 0, '白茶');
INSERT INTO `t_category` VALUES (22, 21, '白牡丹');
INSERT INTO `t_category` VALUES (23, 21, '牡丹王');
INSERT INTO `t_category` VALUES (24, 21, '白毫银针');
INSERT INTO `t_category` VALUES (25, 21, '贡眉');
INSERT INTO `t_category` VALUES (26, 0, '普洱');
INSERT INTO `t_category` VALUES (27, 26, '生茶');
INSERT INTO `t_category` VALUES (28, 26, '熟茶');
INSERT INTO `t_category` VALUES (29, 0, '花茶');
INSERT INTO `t_category` VALUES (30, 29, '茉莉花茶');
INSERT INTO `t_category` VALUES (31, 29, '花草茶');

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `store_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `goods_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `user_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (1, '1641831054120181761', 4, 3, 4, 'IW26v7b224', '雨前头采碧螺春1号散茶', 'hadanklanflea离开萨达姆费德勒', '1680072257113.jpg', '2023-04-02 13:58:12');

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goods_cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goods_show_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '展示图片组逗号分割',
  `subtitle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '副标题',
  `goods_details` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品详情图片组逗号分割',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `stock` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '库存',
  `category_parent_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '分类父编号',
  `category_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '分类编号',
  `store_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '所属店铺编号',
  `goods_status` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '商品状态0下架1上架',
  `sales` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '销量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES (3, '雨前头采碧螺春1号散茶', '1679541293555.jpg', '1679541308742.jpg,1679541311765.jpg,1679541315857.jpg,', '香气悠长且滋味浓郁', '1679541326983.jpg,1679541330703.jpg,1679541336019.jpg,1679541340218.jpg,', 68.00, 198, 1, 6, 4, 1, 2);
INSERT INTO `t_goods` VALUES (4, '浙江果栗香雨前龙井2号', '1679625857410.png', '1679625908155.png,1679625911380.jpg,1679625915163.jpg,1679625917387.jpg,', '来自龙井核心产区的地道滋味，品味不俗。', '1679625922959.jpg,1679625926944.jpg,1679625930879.jpg,1679625933444.jpg,1679625936604.jpg,', 88.00, 97, 1, 4, 5, 1, 0);
INSERT INTO `t_goods` VALUES (5, '黄山太平猴魁绿茶1号', '1679626029912.png', '1679626051280.png,1679626053984.jpg,1679626056769.jpg,1679626060607.jpg,', '黄山雨前茶，日常爽口畅饮必备。', '1679626063904.jpg,1679626067829.jpg,1679626070725.jpg,1679626074053.jpg,', 99.00, 99, 1, 8, 5, 1, 0);
INSERT INTO `t_goods` VALUES (6, '历史名茶黄山毛峰1号', '1679626153002.jpg', '1679626175612.jpg,1679626177759.jpg,1679626180307.jpg,1679626182260.jpg,', '产自核心产区，芽叶细嫩带香，滋味鲜爽醇厚。', '1679626188906.jpg,1679626191816.jpg,1679626195144.jpg,1679626196856.jpg,1679626199201.jpg,', 58.00, 100, 1, 5, 5, 1, 0);
INSERT INTO `t_goods` VALUES (7, '漳平水仙兰香1号', '1679626271805.jpg', '1679626290542.jpg,1679626292412.jpg,1679626295665.jpg,', '兰香中夹杂桂香，清醇爽口。', '1679626298636.jpg,1679626302166.jpg,1679626305112.jpg,1679626308249.jpg,1679626310183.jpg,', 99.00, 98, 11, 14, 5, 1, 0);
INSERT INTO `t_goods` VALUES (8, '云南凤庆经典蜜香滇红', '1679626386909.png', '1679626420268.png,1679626423794.jpg,1679626425872.jpg,', '一股新鲜、清爽的蜜香', '1679626432712.jpg,1679626434818.jpg,1679626438338.jpg,1679626440587.jpg,', 88.00, 300, 2, 17, 4, 1, 0);
INSERT INTO `t_goods` VALUES (9, '云南糯米香小熟沱普洱', '1679626515092.jpg', '1679626540555.jpg,1679626543763.jpg,1679626546279.jpg,', '融入傣族糯米香叶，淡淡自然糯香符合初尝者口感。', '1679626550048.jpg,1679626552790.jpg,1679626556115.jpg,1679626558755.jpg,', 49.00, 17, 26, 28, 4, 1, 3);

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `store_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `total_price` decimal(10, 2) UNSIGNED NULL DEFAULT NULL,
  `order_status` tinyint(3) UNSIGNED NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `delivery_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `delivery_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `provice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `county` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `delivery_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1641810553100345346', 4, 4, 147.00, 1, '2023-03-31 22:31:54', 'wwx', '18768545147', '浙江省', '嘉兴市', '海宁市', '大大吴大维分色法');
INSERT INTO `t_order` VALUES ('1641810553133899777', 4, 5, 198.00, 1, '2023-03-31 22:31:54', 'wwx', '18768545147', '浙江省', '嘉兴市', '海宁市', '大大吴大维分色法');
INSERT INTO `t_order` VALUES ('1641810944634396673', 4, 4, 68.00, 5, '2023-03-31 22:33:19', 'wwx', '13565485462', '浙江省', '嘉兴市', '海宁市', '啊达瓦达瓦发');
INSERT INTO `t_order` VALUES ('1641810954616840193', 4, 5, 88.00, 1, '2023-03-31 22:33:19', 'wwx', '13565485462', '浙江省', '嘉兴市', '海宁市', '啊达瓦达瓦发');
INSERT INTO `t_order` VALUES ('1641831054120181761', 4, 4, 68.00, 4, '2023-03-31 23:53:22', 'wwx', '13565485462', '浙江省', '嘉兴市', '海宁市', '啊达瓦达瓦发');
INSERT INTO `t_order` VALUES ('1650356004854505473', 6, 5, 88.00, 5, '2023-04-24 12:28:28', '住宿费', '15448454151', '福建省', '莆田市', '荔城区', '达娃大地方都是');
INSERT INTO `t_order` VALUES ('1650356693521473537', 6, 5, 88.00, 5, '2023-04-24 12:31:13', '住宿费', '15448454151', '福建省', '莆田市', '荔城区', '达娃大地方都是');
INSERT INTO `t_order` VALUES ('1650364241020530690', 6, 4, 204.00, 3, '2023-04-24 13:01:12', '住宿费', '15448454151', '福建省', '莆田市', '荔城区', '达娃大地方都是');
INSERT INTO `t_order` VALUES ('1650364241146359810', 6, 5, 99.00, 1, '2023-04-24 13:01:12', '住宿费', '15448454151', '福建省', '莆田市', '荔城区', '达娃大地方都是');
INSERT INTO `t_order` VALUES ('1650374057461886977', 6, 4, 136.00, 3, '2023-04-24 13:40:12', '住宿费', '15448454151', '福建省', '莆田市', '荔城区', '达娃大地方都是');

-- ----------------------------
-- Table structure for t_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_order_detail`;
CREATE TABLE `t_order_detail`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goods_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `goods_count` int(11) NULL DEFAULT NULL,
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goods_cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goods_price` decimal(10, 2) UNSIGNED NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `has_commented` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '订单完成后的评论标志',
  `user_id` int(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order_detail
-- ----------------------------
INSERT INTO `t_order_detail` VALUES (5, '1641810553100345346', 9, 3, '云南糯米香小熟沱普洱', '1679626515092.jpg', 49.00, '2023-03-31 22:31:54', 0, 4);
INSERT INTO `t_order_detail` VALUES (6, '1641810553133899777', 7, 2, '漳平水仙兰香1号', '1679626271805.jpg', 99.00, '2023-03-31 22:31:54', 0, 4);
INSERT INTO `t_order_detail` VALUES (7, '1641810944634396673', 3, 1, '雨前头采碧螺春1号散茶', '1679541293555.jpg', 68.00, '2023-03-31 22:33:19', 0, 4);
INSERT INTO `t_order_detail` VALUES (8, '1641810954616840193', 4, 1, '浙江果栗香雨前龙井2号', '1679625857410.png', 88.00, '2023-03-31 22:33:19', 0, 4);
INSERT INTO `t_order_detail` VALUES (9, '1641831054120181761', 3, 1, '雨前头采碧螺春1号散茶', '1679541293555.jpg', 68.00, '2023-03-31 23:53:22', 2, 4);
INSERT INTO `t_order_detail` VALUES (10, '1650356004854505473', 4, 1, '浙江果栗香雨前龙井2号', '1679625857410.png', 88.00, '2023-04-24 12:28:28', 0, 6);
INSERT INTO `t_order_detail` VALUES (11, '1650356693521473537', 4, 1, '浙江果栗香雨前龙井2号', '1679625857410.png', 88.00, '2023-04-24 12:31:13', 0, 6);
INSERT INTO `t_order_detail` VALUES (12, '1650364241020530690', 3, 3, '雨前头采碧螺春1号散茶', '1679541293555.jpg', 68.00, '2023-04-24 13:01:12', 0, 6);
INSERT INTO `t_order_detail` VALUES (13, '1650364241146359810', 5, 1, '黄山太平猴魁绿茶1号', '1679626029912.png', 99.00, '2023-04-24 13:01:12', 0, 6);
INSERT INTO `t_order_detail` VALUES (14, '1650374057461886977', 3, 2, '雨前头采碧螺春1号散茶', '1679541293555.jpg', 68.00, '2023-04-24 13:40:12', 0, 6);

-- ----------------------------
-- Table structure for t_store
-- ----------------------------
DROP TABLE IF EXISTS `t_store`;
CREATE TABLE `t_store`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `store_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_id` int(10) UNSIGNED NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_store
-- ----------------------------
INSERT INTO `t_store` VALUES (4, '好好茶', 1, '18765554514', '张三');
INSERT INTO `t_store` VALUES (5, '查查哈', 3, '18765554514', '李四');
INSERT INTO `t_store` VALUES (6, '的身份是', 6, '187545451545', '达瓦达瓦');

-- ----------------------------
-- Table structure for t_store_apply
-- ----------------------------
DROP TABLE IF EXISTS `t_store_apply`;
CREATE TABLE `t_store_apply`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(10) UNSIGNED NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `store_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` tinyint(3) UNSIGNED NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申请备注 拒绝原因',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_store_apply
-- ----------------------------
INSERT INTO `t_store_apply` VALUES (1, 1, '张三', '18765554514', '好好茶', 2, '2023-03-20 21:38:35', '你很丑');
INSERT INTO `t_store_apply` VALUES (2, 1, '张三', '18765554514', '好好茶', 1, '2023-03-20 23:05:03', '你太丑了');
INSERT INTO `t_store_apply` VALUES (3, 6, '反对法', '18754484512', '方式发', 0, '2023-04-24 11:09:31', NULL);
INSERT INTO `t_store_apply` VALUES (4, 6, '达瓦达瓦', '187545451545', '的身份是', 1, '2023-04-24 13:02:48', NULL);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '0未知1男2女',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `role` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '0普通用户1茶农',
  `birthday` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'testAccount', 'e10adc3949ba59abbe56e057f20f883e', '13584563258', '330526200001023015', 1, 'default.jpg', 1, NULL, 'dsgsdf');
INSERT INTO `t_user` VALUES (2, 'dhadddas', 'e10adc3949ba59abbe56e057f20f883e', '18754514851', '330521200405200301', 0, 'default.jpg', 0, NULL, 'mjbnnjls4i');
INSERT INTO `t_user` VALUES (3, 'seller1', 'e10adc3949ba59abbe56e057f20f883e', '13584563258', '330526200001023015', 1, 'default.jpg', 1, NULL, 'dsgsdf');
INSERT INTO `t_user` VALUES (4, 'IW26v7b224', 'e10adc3949ba59abbe56e057f20f883e', '13584563256', NULL, 0, '1680072257113.jpg', 0, NULL, 'IW26v7b224');
INSERT INTO `t_user` VALUES (5, 'b2w1JI82R7', 'e10adc3949ba59abbe56e057f20f883e', '13584563252', NULL, 0, 'default.jpg', 0, NULL, 'b2w1JI82R7');
INSERT INTO `t_user` VALUES (6, 'xr0Td27Chp', 'e10adc3949ba59abbe56e057f20f883e', '18754514857', NULL, 2, 'default.jpg', 1, '2023-04-12', 'xr0Td27Chp');

SET FOREIGN_KEY_CHECKS = 1;
