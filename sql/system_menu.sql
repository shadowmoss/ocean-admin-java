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

 Date: 02/08/2023 16:45:21
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
