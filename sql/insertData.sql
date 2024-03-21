-- 添加用户信息
INSERT INTO `shiro`.`t_user` (`id`, `username`, `password`, `salt`) VALUES (1, 'xiaochen', 'b81f9fd16871f518865c680de016ff5c', '%ut0ZmSH');
INSERT INTO `shiro`.`t_user` (`id`, `username`, `password`, `salt`) VALUES (2, 'zhangsan', 'a7c5d959a1a8ee1b983cd991bb32ad8e', '@MoM7X5G');
-- 添加角色信息
INSERT INTO `shiro`.`t_role` (`id`, `name`) VALUES (1, 'admin');
INSERT INTO `shiro`.`t_role` (`id`, `name`) VALUES (2, 'user');
INSERT INTO `shiro`.`t_role` (`id`, `name`) VALUES (3, 'product');
-- 添加用户角色信息
INSERT INTO `shiro`.`t_user_role` (`id`, `userid`, `roleid`) VALUES (1, 1, 1);
INSERT INTO `shiro`.`t_user_role` (`id`, `userid`, `roleid`) VALUES (2, 2, 2);
INSERT INTO `shiro`.`t_user_role` (`id`, `userid`, `roleid`) VALUES (3, 2, 3);
-- 权限表数据
INSERT INTO `shiro`.`t_pers` (`id`, `name`, `url`) VALUES (1, 'user:*:*', NULL);
INSERT INTO `shiro`.`t_pers` (`id`, `name`, `url`) VALUES (2, 'product:*:01', NULL);
INSERT INTO `shiro`.`t_pers` (`id`, `name`, `url`) VALUES (3, 'order:*:*', NULL);
-- 基于角色的权限，一个角色可以有多个权限
INSERT INTO `shiro`.`t_role_perms` (`id`, `roleid`, `permsid`) VALUES (1, 1, 1);
INSERT INTO `shiro`.`t_role_perms` (`id`, `roleid`, `permsid`) VALUES (2, 1, 2);
INSERT INTO `shiro`.`t_role_perms` (`id`, `roleid`, `permsid`) VALUES (3, 1, 3);
INSERT INTO `shiro`.`t_role_perms` (`id`, `roleid`, `permsid`) VALUES (4, 2, 1);
INSERT INTO `shiro`.`t_role_perms` (`id`, `roleid`, `permsid`) VALUES (5, 3, 2);