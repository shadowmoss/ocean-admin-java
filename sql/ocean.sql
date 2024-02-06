/*
 Navicat Premium Data Transfer

 Source Server         : 虚拟机mysql
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 192.168.148.128:3306
 Source Schema         : ocean

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 04/02/2024 07:22:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '菜单名称',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '菜单的权限标识,当菜单类型为按钮时需要该字段\r\n当将某一个menuDO赋予给角色之后，意味着该角色拥有该菜单资源。\r\n对于后端,需要配合@PreAuthorize注解，配置API接口权限，实现对API接口的控制。\r\n对于前端,配合前端标签,配置该按钮是否展示，避免用户没有该功能权限却可以看到该操作。',
  `type` int(11) NULL DEFAULT NULL COMMENT '菜单类型,1:目录,2:菜单,3:按钮',
  `sort` int(11) NULL DEFAULT NULL COMMENT '显示排序',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父级菜单id',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '菜单路由',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '菜单图标',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '组件路径',
  `component_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '组件名称',
  `status` int(11) NULL DEFAULT NULL COMMENT '菜单状态,0:表示启用,1:表示未启用',
  `visible` int(11) NULL DEFAULT NULL COMMENT '菜单可见性:0:表示可见,1:表示不可见',
  `keep_alive` int(11) NULL DEFAULT NULL COMMENT '是否进行缓存:0:表示启用,1:表示不启用。只有菜单、目录这两种进行使用,是否使用Vue的keep-alive组件对页面进行缓存\r\n如果开启，则必须填写componentName属性，否则无法缓存.',
  `always_show` int(11) NULL DEFAULT NULL COMMENT '是否总是显示该菜单:0:表示总是显示,1:表示当菜单只有一个子菜单时,不展示自身,直接展示子菜单',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `updater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新人',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '是否进行了逻辑删除,0:表示未删除,1表示删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES (8, '系统管理', NULL, 1, 0, 0, '/system', NULL, NULL, NULL, 0, 0, NULL, 0, '2023-08-12 16:35:26', '2023-08-12 16:35:26', NULL, NULL, 0);
INSERT INTO `system_menu` VALUES (9, '用户管理', 'system:user', 2, 1, 8, 'user', NULL, 'system/user/user', 'user', 0, 0, 0, 0, '2023-08-12 16:36:02', '2023-08-12 16:36:02', NULL, NULL, 0);
INSERT INTO `system_menu` VALUES (10, '角色管理', 'system:role', 2, 1, 8, 'role', NULL, 'system/role/role', 'role', 0, 0, 0, 0, '2023-08-12 16:37:09', '2023-08-12 16:37:09', NULL, NULL, 0);
INSERT INTO `system_menu` VALUES (11, '菜单管理', 'system:menu', 2, 1, 8, 'menu', NULL, 'system/menu/menu', 'menu', 0, 0, 0, 0, '2023-08-12 16:37:33', '2023-08-12 16:37:33', NULL, NULL, 0);
INSERT INTO `system_menu` VALUES (12, '用户查询', 'system:user:query', 3, NULL, 9, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '2023-09-09 16:36:43', '2023-09-09 16:36:43', NULL, NULL, 0);
INSERT INTO `system_menu` VALUES (13, '用户新增', 'system:user:insert', 3, NULL, 9, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '2023-09-09 21:25:09', '2023-09-09 21:25:09', NULL, NULL, 0);
INSERT INTO `system_menu` VALUES (14, '用户修改', 'system:user:update', 3, NULL, 9, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '2023-09-09 21:25:26', '2023-09-09 21:25:26', NULL, NULL, 0);
INSERT INTO `system_menu` VALUES (15, '用户删除', 'system:user:delete', 3, NULL, 9, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '2023-09-09 21:25:38', '2023-09-09 21:25:38', NULL, NULL, 0);
INSERT INTO `system_menu` VALUES (16, '角色查询', 'system:role:query', 3, NULL, 10, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '2023-09-09 21:25:59', '2023-09-09 21:25:59', NULL, NULL, 0);
INSERT INTO `system_menu` VALUES (17, '角色新增', 'system:role:insert', 3, NULL, 10, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '2023-09-09 21:26:15', '2023-09-09 21:26:15', NULL, NULL, 0);
INSERT INTO `system_menu` VALUES (18, '角色更新', 'system:role:update', 3, NULL, 10, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '2023-09-09 21:26:29', '2023-09-09 21:26:29', NULL, NULL, 0);
INSERT INTO `system_menu` VALUES (19, '角色删除', 'system:role:delete', 3, NULL, 10, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '2023-09-09 21:26:48', '2023-09-09 21:26:48', NULL, NULL, 0);
INSERT INTO `system_menu` VALUES (20, '菜单查询', 'system:menu:query', 3, NULL, 11, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '2023-09-09 21:27:04', '2023-09-09 21:27:04', NULL, NULL, 0);
INSERT INTO `system_menu` VALUES (21, '菜单新增', 'system:menu:insert', 3, NULL, 11, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '2023-09-09 21:27:21', '2023-09-09 21:27:21', NULL, NULL, 0);
INSERT INTO `system_menu` VALUES (22, '菜单更新', 'system:menu:update', 3, NULL, 11, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '2023-09-09 21:27:33', '2023-09-09 21:27:33', NULL, NULL, 0);
INSERT INTO `system_menu` VALUES (23, '菜单删除', 'system:menu:delete', 3, NULL, 11, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '2023-09-09 21:27:47', '2023-09-09 21:27:47', NULL, NULL, 0);
INSERT INTO `system_menu` VALUES (24, 'OAuth2', '', 2, 1, 8, 'oauth2', NULL, 'system/oauth2/oauth2', 'oauth2', 0, 0, 0, 0, '2023-09-21 11:33:49', '2023-09-21 11:33:49', NULL, NULL, 0);
INSERT INTO `system_menu` VALUES (25, '新增OAuth2客户端', 'system:oauth2:insert', 3, NULL, 24, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '2023-09-21 11:34:15', '2023-09-21 11:34:15', NULL, NULL, 0);
INSERT INTO `system_menu` VALUES (26, '更新OAuth2客户端', 'system:oauth2:update', 3, NULL, 24, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '2023-09-21 11:34:31', '2023-09-21 11:34:31', NULL, NULL, 0);
INSERT INTO `system_menu` VALUES (27, '删除OAuth2客户端', 'system:oauth2:delete', 3, NULL, 24, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '2023-09-21 11:34:51', '2023-09-21 11:34:51', NULL, NULL, 0);

-- ----------------------------
-- Table structure for system_oauth2_access_token
-- ----------------------------
DROP TABLE IF EXISTS `system_oauth2_access_token`;
CREATE TABLE `system_oauth2_access_token`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问令牌主键',
  `user_id` bigint(11) NULL DEFAULT NULL COMMENT '用户id',
  `user_type` int(11) NULL DEFAULT NULL COMMENT '用户类型',
  `access_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '访问令牌值',
  `refresh_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '刷新令牌的值',
  `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户端id',
  `expires_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(0) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除字段,0:未删除,1:已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_oauth2_access_token
-- ----------------------------
INSERT INTO `system_oauth2_access_token` VALUES (31, 12, NULL, '3d965626-acef-4222-bf11-fb43bbb947a0', 'd3338716-5193-4152-8eb0-c298d7de17c5', 'default', '2023-09-29 17:22:09', NULL, '2023-09-29 17:21:59', NULL, '2023-09-29 17:21:59', 1);
INSERT INTO `system_oauth2_access_token` VALUES (32, 12, NULL, '9b788d78-30f4-40e9-8e3c-9b778ff929f0', 'd3338716-5193-4152-8eb0-c298d7de17c5', 'default', '2023-09-29 17:22:35', NULL, '2023-09-29 17:22:25', NULL, '2023-09-29 17:22:25', 0);
INSERT INTO `system_oauth2_access_token` VALUES (33, 12, NULL, '80d18824-7369-4d92-b6d1-3dd37da346f6', '47b94562-34fe-498f-a87b-bc9932342013', 'default', '2023-09-29 17:23:02', NULL, '2023-09-29 17:22:52', NULL, '2023-09-29 17:22:52', 1);
INSERT INTO `system_oauth2_access_token` VALUES (34, 12, NULL, 'e43d37dd-7e1e-4dff-91da-a08e98a7086f', '47b94562-34fe-498f-a87b-bc9932342013', 'default', '2023-09-29 17:23:39', NULL, '2023-09-29 17:23:29', NULL, '2023-09-29 17:23:29', 0);
INSERT INTO `system_oauth2_access_token` VALUES (35, 12, NULL, 'd179385f-1070-4019-bad1-2a66e06449ba', 'a136d4fa-b9f7-4887-bbb8-099687de4f2b', 'default', '2023-09-29 17:24:59', NULL, '2023-09-29 17:24:29', NULL, '2023-09-29 17:24:29', 1);
INSERT INTO `system_oauth2_access_token` VALUES (36, 12, NULL, 'dca0ea68-19a7-4743-a616-4ffd77467137', 'a136d4fa-b9f7-4887-bbb8-099687de4f2b', 'default', '2023-09-29 17:25:29', NULL, '2023-09-29 17:24:59', NULL, '2023-09-29 17:24:59', 0);
INSERT INTO `system_oauth2_access_token` VALUES (37, 12, NULL, '21516429-0bfe-49ca-9cd9-022296a6286c', '3a0c6c93-3a8d-40fd-8e4b-39eeeecfac24', 'default', '2023-09-29 17:26:13', NULL, '2023-09-29 17:25:43', NULL, '2023-09-29 17:25:43', 1);
INSERT INTO `system_oauth2_access_token` VALUES (38, 12, NULL, '123534b0-bee4-47f3-8aed-00898a4b6f64', '3a0c6c93-3a8d-40fd-8e4b-39eeeecfac24', 'default', '2023-09-29 17:26:48', NULL, '2023-09-29 17:26:18', NULL, '2023-09-29 17:26:18', 0);
INSERT INTO `system_oauth2_access_token` VALUES (39, 12, NULL, '379b74d4-3b01-4d8b-9e13-df10a05feb39', 'b84ba9f6-aaec-459c-877d-88e2fa1afd1e', 'default', '2023-09-29 17:27:35', NULL, '2023-09-29 17:27:05', NULL, '2023-09-29 17:27:05', 1);
INSERT INTO `system_oauth2_access_token` VALUES (40, 12, NULL, '147a964c-d7d0-4d0a-9f16-3446f99c26c1', 'b84ba9f6-aaec-459c-877d-88e2fa1afd1e', 'default', '2023-09-29 17:28:20', NULL, '2023-09-29 17:27:50', NULL, '2023-09-29 17:27:50', 0);
INSERT INTO `system_oauth2_access_token` VALUES (41, 12, NULL, 'b7924132-ddc3-41bf-9553-477cbf8ac706', '4c4e3fbc-b9a7-453e-ab55-b412c4ff521c', 'default', '2023-09-29 17:30:01', NULL, '2023-09-29 17:29:31', NULL, '2023-09-29 17:29:31', 1);
INSERT INTO `system_oauth2_access_token` VALUES (42, 12, NULL, '2deedf0f-f718-4ebc-95f3-6396b041d59b', '4c4e3fbc-b9a7-453e-ab55-b412c4ff521c', 'default', '2023-09-29 17:30:34', NULL, '2023-09-29 17:30:04', NULL, '2023-09-29 17:30:04', 0);
INSERT INTO `system_oauth2_access_token` VALUES (43, 12, NULL, 'f9e3b1c4-26ca-4701-9583-689a9ba9e120', '2f61c407-87e4-44a6-8571-700447b24471', 'default', '2023-09-29 17:32:15', NULL, '2023-09-29 17:31:45', NULL, '2023-09-29 17:31:45', 1);
INSERT INTO `system_oauth2_access_token` VALUES (44, 12, NULL, 'b475de77-4438-4516-b751-5b178bd6cd24', '2f61c407-87e4-44a6-8571-700447b24471', 'default', '2023-09-29 17:32:54', NULL, '2023-09-29 17:32:24', NULL, '2023-09-29 17:32:24', 0);
INSERT INTO `system_oauth2_access_token` VALUES (45, 12, NULL, 'cd6fd45e-d2c6-4742-90e3-5d440cdd1042', '90130fc1-d487-42c0-a813-fdbb73b9b4a7', 'default', '2023-09-29 17:34:07', NULL, '2023-09-29 17:33:37', NULL, '2023-09-29 17:33:37', 1);
INSERT INTO `system_oauth2_access_token` VALUES (46, 12, NULL, '33e0ce36-13b6-418e-aee1-9c3ce352b29a', '90130fc1-d487-42c0-a813-fdbb73b9b4a7', 'default', '2023-09-29 17:34:50', NULL, '2023-09-29 17:34:20', NULL, '2023-09-29 17:34:20', 0);
INSERT INTO `system_oauth2_access_token` VALUES (47, 12, NULL, '456cb569-cd84-4429-bb2f-b1e80fa1862b', '25e72966-5ede-429f-953c-7041a3e497e7', 'default', '2023-09-29 17:36:05', NULL, '2023-09-29 17:35:35', NULL, '2023-09-29 17:35:35', 1);
INSERT INTO `system_oauth2_access_token` VALUES (48, 12, NULL, '7fac777d-cab6-4eed-85d2-8512ac599ae9', '25e72966-5ede-429f-953c-7041a3e497e7', 'default', '2023-09-29 17:36:50', NULL, '2023-09-29 17:36:20', NULL, '2023-09-29 17:36:20', 0);
INSERT INTO `system_oauth2_access_token` VALUES (49, 12, NULL, '69bf6523-47cd-4871-83d5-e384703d2f45', '214d073c-8e41-47f8-894b-1a51ca897ffd', 'default', '2023-09-29 17:37:51', NULL, '2023-09-29 17:37:21', NULL, '2023-09-29 17:37:21', 1);
INSERT INTO `system_oauth2_access_token` VALUES (50, 12, NULL, 'd1177d0d-95fe-492c-9e9b-093e7f4eec12', '214d073c-8e41-47f8-894b-1a51ca897ffd', 'default', '2023-09-29 17:38:36', NULL, '2023-09-29 17:38:06', NULL, '2023-09-29 17:38:06', 1);
INSERT INTO `system_oauth2_access_token` VALUES (51, 12, NULL, 'd0c73b79-bdb2-4856-bed6-d57a7553cb07', '214d073c-8e41-47f8-894b-1a51ca897ffd', 'default', '2023-09-29 17:39:08', NULL, '2023-09-29 17:38:38', NULL, '2023-09-29 17:38:38', 1);
INSERT INTO `system_oauth2_access_token` VALUES (52, 12, NULL, '7be04085-bf1c-4361-b3cf-f449ac076fc2', '214d073c-8e41-47f8-894b-1a51ca897ffd', 'default', '2023-09-29 17:40:25', NULL, '2023-09-29 17:39:55', NULL, '2023-09-29 17:39:55', 1);
INSERT INTO `system_oauth2_access_token` VALUES (53, 12, NULL, '9b53779d-8747-451e-8d1d-9d575a400452', '214d073c-8e41-47f8-894b-1a51ca897ffd', 'default', '2023-09-29 17:41:03', NULL, '2023-09-29 17:40:33', NULL, '2023-09-29 17:40:33', 0);
INSERT INTO `system_oauth2_access_token` VALUES (54, 12, NULL, 'd497cab0-af98-47ca-b1bc-d0a82e3d0c7b', '65119003-2e09-4073-995c-0fb287824629', 'default', '2023-09-30 16:34:05', NULL, '2023-09-30 16:04:05', NULL, '2023-09-30 16:04:05', 0);
INSERT INTO `system_oauth2_access_token` VALUES (55, 12, NULL, 'c1aeae98-32ca-4938-be44-d63e6684389a', '85b6eb02-e52f-4667-92ba-7b0294cf074e', 'default', '2023-09-30 16:34:19', NULL, '2023-09-30 16:04:19', NULL, '2023-09-30 16:04:19', 0);
INSERT INTO `system_oauth2_access_token` VALUES (56, 12, NULL, '2b225e2b-a75a-4418-b1ac-81b356a32d42', 'ccf48e0b-1374-442d-8a36-7dc04af385a7', 'default', '2023-09-30 16:35:33', NULL, '2023-09-30 16:05:33', NULL, '2023-09-30 16:05:33', 1);
INSERT INTO `system_oauth2_access_token` VALUES (57, 14, NULL, '81f127de-391b-4494-93d2-9389a56b2fc4', 'f2ee50b3-e83b-472f-b2c8-f269fed39aa6', 'default', '2023-09-30 16:36:41', NULL, '2023-09-30 16:06:41', NULL, '2023-09-30 16:06:41', 1);
INSERT INTO `system_oauth2_access_token` VALUES (58, 12, NULL, 'fa05bf62-f28b-4464-99a9-8bd71d2808e7', 'c2572179-3087-4876-8aa7-6550e002621d', 'default', '2023-09-30 16:40:21', NULL, '2023-09-30 16:10:21', NULL, '2023-09-30 16:10:21', 1);
INSERT INTO `system_oauth2_access_token` VALUES (59, 12, NULL, 'd1db460e-105d-457f-aad8-919fbc1eed36', '1c4c207b-9aa6-4fb6-9f88-56a833bcb841', 'default', '2023-09-30 16:59:39', NULL, '2023-09-30 16:29:39', NULL, '2023-09-30 16:29:39', 0);

-- ----------------------------
-- Table structure for system_oauth2_client
-- ----------------------------
DROP TABLE IF EXISTS `system_oauth2_client`;
CREATE TABLE `system_oauth2_client`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户端编号,授权码模式,密码模式时，需要传递以识别是哪一个客户端，唯一',
  `secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户端秘钥,进行OAuth2请求时的秘钥',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '应用名称',
  `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '引用图标地址',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '描述',
  `status` int(11) NULL DEFAULT NULL COMMENT '0:启用,1:未启用',
  `access_token_validity_seconds` int(11) NULL DEFAULT NULL COMMENT '访问令牌的有效期',
  `refresh_token_validity_seconds` int(11) NULL DEFAULT NULL COMMENT '刷新令牌的有效期',
  `redirect_uris` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '可重定向的URI地址',
  `authorized_grant_types` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '授权类型(哪些操作，可以授权给这个客户端使用)',
  `scopes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '授权范围',
  `auto_approve_scopes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '自动授权的授权范围',
  `authorities` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '权限',
  `resource_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '资源id',
  `additional_information` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '附加信息JSON格式',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建者',
  `updater` varchar(0) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除,0:未删除，1:已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_oauth2_client
-- ----------------------------
INSERT INTO `system_oauth2_client` VALUES (1, 'test_client', 'test', 'test', 'test', 'test', 0, 1800, 43200, '[]', '[\"password\",\"refreshToken\"]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2023-09-27 10:20:27', '2023-09-27 10:20:27', 1);
INSERT INTO `system_oauth2_client` VALUES (2, 'test_client', 'test', 'test', 'test', 'hjhkhkhkjh', 0, 1800, 43200, '[]', '[\"refreshToken\"]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2023-09-27 11:20:37', '2023-09-27 11:46:47', 1);
INSERT INTO `system_oauth2_client` VALUES (3, 'default', 'default', 'Ocean前端客户端', NULL, 'Ocean默认的客户端前端', 0, 1800, 43200, '[]', '[\"password\",\"refreshToken\"]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2023-09-27 11:48:14', '2023-09-27 11:48:14', 0);

-- ----------------------------
-- Table structure for system_oauth2_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `system_oauth2_refresh_token`;
CREATE TABLE `system_oauth2_refresh_token`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `refresh_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '刷新令牌',
  `user_id` bigint(11) NULL DEFAULT NULL COMMENT '用户id',
  `user_type` int(255) NULL DEFAULT NULL COMMENT '用户类型',
  `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户端id',
  `scopes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '授权范围',
  `expires_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建者',
  `updater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除:0:未删除,1:已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_oauth2_refresh_token
-- ----------------------------
INSERT INTO `system_oauth2_refresh_token` VALUES (7, 'd3338716-5193-4152-8eb0-c298d7de17c5', 12, NULL, 'default', NULL, '2023-09-30 05:21:59', NULL, NULL, '2023-09-29 17:21:59', '2023-09-29 17:21:59', 0);
INSERT INTO `system_oauth2_refresh_token` VALUES (8, '47b94562-34fe-498f-a87b-bc9932342013', 12, NULL, 'default', NULL, '2023-09-30 05:22:52', NULL, NULL, '2023-09-29 17:22:52', '2023-09-29 17:22:52', 0);
INSERT INTO `system_oauth2_refresh_token` VALUES (9, 'a136d4fa-b9f7-4887-bbb8-099687de4f2b', 12, NULL, 'default', NULL, '2023-09-30 05:24:29', NULL, NULL, '2023-09-29 17:24:29', '2023-09-29 17:24:29', 0);
INSERT INTO `system_oauth2_refresh_token` VALUES (10, '3a0c6c93-3a8d-40fd-8e4b-39eeeecfac24', 12, NULL, 'default', NULL, '2023-09-30 05:25:43', NULL, NULL, '2023-09-29 17:25:43', '2023-09-29 17:25:43', 0);
INSERT INTO `system_oauth2_refresh_token` VALUES (11, 'b84ba9f6-aaec-459c-877d-88e2fa1afd1e', 12, NULL, 'default', NULL, '2023-09-30 05:27:05', NULL, NULL, '2023-09-29 17:27:05', '2023-09-29 17:27:05', 0);
INSERT INTO `system_oauth2_refresh_token` VALUES (12, '4c4e3fbc-b9a7-453e-ab55-b412c4ff521c', 12, NULL, 'default', NULL, '2023-09-30 05:29:31', NULL, NULL, '2023-09-29 17:29:31', '2023-09-29 17:29:31', 0);
INSERT INTO `system_oauth2_refresh_token` VALUES (13, '2f61c407-87e4-44a6-8571-700447b24471', 12, NULL, 'default', NULL, '2023-09-30 05:31:45', NULL, NULL, '2023-09-29 17:31:45', '2023-09-29 17:31:45', 0);
INSERT INTO `system_oauth2_refresh_token` VALUES (14, '90130fc1-d487-42c0-a813-fdbb73b9b4a7', 12, NULL, 'default', NULL, '2023-09-30 05:33:37', NULL, NULL, '2023-09-29 17:33:37', '2023-09-29 17:33:37', 0);
INSERT INTO `system_oauth2_refresh_token` VALUES (15, '25e72966-5ede-429f-953c-7041a3e497e7', 12, NULL, 'default', NULL, '2023-09-30 05:35:35', NULL, NULL, '2023-09-29 17:35:35', '2023-09-29 17:35:35', 0);
INSERT INTO `system_oauth2_refresh_token` VALUES (16, '214d073c-8e41-47f8-894b-1a51ca897ffd', 12, NULL, 'default', NULL, '2023-09-30 05:37:21', NULL, NULL, '2023-09-29 17:37:21', '2023-09-29 17:37:21', 0);
INSERT INTO `system_oauth2_refresh_token` VALUES (17, '65119003-2e09-4073-995c-0fb287824629', 12, NULL, 'default', NULL, '2023-10-01 04:04:05', NULL, NULL, '2023-09-30 16:04:05', '2023-09-30 16:04:05', 0);
INSERT INTO `system_oauth2_refresh_token` VALUES (18, '85b6eb02-e52f-4667-92ba-7b0294cf074e', 12, NULL, 'default', NULL, '2023-10-01 04:04:19', NULL, NULL, '2023-09-30 16:04:19', '2023-09-30 16:04:19', 0);
INSERT INTO `system_oauth2_refresh_token` VALUES (19, 'ccf48e0b-1374-442d-8a36-7dc04af385a7', 12, NULL, 'default', NULL, '2023-10-01 04:05:33', NULL, NULL, '2023-09-30 16:05:33', '2023-09-30 16:05:33', 0);
INSERT INTO `system_oauth2_refresh_token` VALUES (20, 'f2ee50b3-e83b-472f-b2c8-f269fed39aa6', 14, NULL, 'default', NULL, '2023-10-01 04:06:41', NULL, NULL, '2023-09-30 16:06:41', '2023-09-30 16:06:41', 0);
INSERT INTO `system_oauth2_refresh_token` VALUES (21, 'c2572179-3087-4876-8aa7-6550e002621d', 12, NULL, 'default', NULL, '2023-10-01 04:10:21', NULL, NULL, '2023-09-30 16:10:21', '2023-09-30 16:10:21', 0);
INSERT INTO `system_oauth2_refresh_token` VALUES (22, '1c4c207b-9aa6-4fb6-9f88-56a833bcb841', 12, NULL, 'default', NULL, '2023-10-01 04:29:39', NULL, NULL, '2023-09-30 16:29:39', '2023-09-30 16:29:39', 0);

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '角色名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '角色唯一表示代码',
  `role_order` int(11) NULL DEFAULT NULL COMMENT '角色显示顺序',
  `status` int(11) NULL DEFAULT NULL COMMENT '角色状态,0:表示启用,1:表示未启用',
  `role_type` int(11) NULL DEFAULT NULL COMMENT '角色类型:0:表示内置角色(不可进行删除操作),1:表示用户自定义角色(可进行删除)',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注信息',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建者',
  `updater` varchar(0) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除字段,0:表示未删除,1:表示删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES (5, '超级管理员', 'super_admin', 0, 0, 1, NULL, NULL, NULL, '2023-08-11 15:44:32', '2023-08-11 15:44:45', 0);
INSERT INTO `system_role` VALUES (6, 'admin2', 'super_admin2', 0, 0, 1, NULL, NULL, NULL, '2023-08-12 16:16:03', '2023-08-12 16:16:03', 0);
INSERT INTO `system_role` VALUES (7, 'admin3', 'super_admin3', 0, 1, 1, NULL, NULL, NULL, '2023-08-12 16:16:14', '2023-08-12 16:16:14', 0);
INSERT INTO `system_role` VALUES (8, 'admin4', 'super_admin4', 0, 0, 1, NULL, NULL, NULL, '2023-08-12 16:16:23', '2023-08-12 16:16:23', 0);

-- ----------------------------
-- Table structure for system_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_role_menu`;
CREATE TABLE `system_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单id',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除:0:未删除,1:已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_role_menu
-- ----------------------------
INSERT INTO `system_role_menu` VALUES (29, 5, 8, NULL, '2023-08-29 14:24:01', NULL, '2023-08-29 14:24:01', 0);
INSERT INTO `system_role_menu` VALUES (30, 5, 9, NULL, '2023-08-29 14:24:01', NULL, '2023-08-29 14:24:01', 0);
INSERT INTO `system_role_menu` VALUES (31, 5, 10, NULL, '2023-08-29 14:24:01', NULL, '2023-08-29 14:24:01', 0);
INSERT INTO `system_role_menu` VALUES (32, 5, 11, NULL, '2023-08-29 14:24:01', NULL, '2023-08-29 14:24:01', 0);
INSERT INTO `system_role_menu` VALUES (33, 6, 8, NULL, '2023-08-29 15:34:52', NULL, '2023-08-29 15:34:52', 0);
INSERT INTO `system_role_menu` VALUES (34, 6, 9, NULL, '2023-08-29 15:34:52', NULL, '2023-08-29 15:34:52', 0);
INSERT INTO `system_role_menu` VALUES (35, 6, 10, NULL, '2023-08-29 15:34:52', NULL, '2023-08-29 15:34:52', 0);
INSERT INTO `system_role_menu` VALUES (36, 6, 12, NULL, '2023-09-09 16:52:26', NULL, '2023-09-09 16:52:26', 0);
INSERT INTO `system_role_menu` VALUES (37, 6, 16, NULL, '2023-09-10 11:39:50', NULL, '2023-09-10 11:39:50', 0);
INSERT INTO `system_role_menu` VALUES (38, 6, 13, NULL, '2023-09-10 11:40:19', NULL, '2023-09-10 11:40:19', 0);
INSERT INTO `system_role_menu` VALUES (39, 6, 17, NULL, '2023-09-10 11:40:19', NULL, '2023-09-10 11:40:19', 0);
INSERT INTO `system_role_menu` VALUES (40, 6, 11, NULL, '2023-09-10 11:50:13', NULL, '2023-09-10 11:50:13', 0);
INSERT INTO `system_role_menu` VALUES (41, 6, 20, NULL, '2023-09-10 11:50:13', NULL, '2023-09-10 11:50:13', 0);

-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除:0:未删除,1:已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_user_role
-- ----------------------------
INSERT INTO `system_user_role` VALUES (1, 12, 5, NULL, '2023-08-29 14:25:23', NULL, '2023-08-29 14:25:23', 0);
INSERT INTO `system_user_role` VALUES (2, 12, 6, NULL, '2023-08-29 15:34:59', NULL, '2023-08-29 15:34:59', 0);
INSERT INTO `system_user_role` VALUES (3, 13, 6, NULL, '2023-09-09 15:53:59', NULL, '2023-09-09 15:53:59', 0);
INSERT INTO `system_user_role` VALUES (4, 14, 5, NULL, '2023-09-30 16:06:32', NULL, '2023-09-30 16:06:32', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户头像',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮件',
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电话',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `status` int(11) NULL DEFAULT NULL COMMENT '用户状态:0:启用,1:表示未启用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `updater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新人',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '是否进行了逻辑删除,0:表示未删除,1表示删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (12, 'realadmin', '$2a$10$NXQIP1L2fmJxDSRssxx8fejiEcbtxnPjCWeyPSeS1Q4qigHRFBbHy', '123456', NULL, 'ltx@163.com', '18284806010', '真管理员', NULL, '2023-08-24 19:53:10', '2023-08-24 19:53:10', NULL, NULL, 0);
INSERT INTO `user` VALUES (13, 'testuser', '$2a$10$HKdSQpQjOrmhtJxJmJPNd.H.vQ7MESoZfKu62YW8x0WsmO8vZ9p5u', NULL, NULL, 'test@163.com', '18284806010', '测试权限的用户', NULL, '2023-09-09 15:52:49', '2023-09-09 15:52:49', NULL, NULL, 0);
INSERT INTO `user` VALUES (14, 'testnew', '$2a$10$cV/fVmwdfX9BA/LlzXVsBuVnMXgcAbVnPtWzco8s3EJrEXzsss3D6', NULL, NULL, 'ltx@163.com', '18284806010', NULL, NULL, '2023-09-30 16:06:17', '2023-09-30 16:06:17', NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
