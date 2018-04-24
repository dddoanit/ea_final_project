INSERT INTO `ea_final_project`.`role` (`id`, `name`) VALUES ('1', 'ADMIN');
INSERT INTO `ea_final_project`.`role` (`id`, `name`) VALUES ('2', 'USER');

INSERT INTO `ea_final_project`.`user` (`id`, `city`, `country`, `email`, `enabled`, `name`,`password`) VALUES ('1', 'Ho Chi Minh', 'Vietnam', 'dddoan@mum.edu', 1, 'Danh Dat Doan','$2a$10$.mxbiC0qCmeHuhrL9HSdGuuEUaKkaWCvoPqiCe/X9sUCOuTlFh5/6');

INSERT INTO `ea_final_project`.`user_roles` (`user_id`, `role_id`) VALUES ('1', '1');

INSERT INTO `ea_final_project`.`skill` (`id`, `name`) VALUES ('1', 'JAVA');
INSERT INTO `ea_final_project`.`skill` (`id`, `name`) VALUES ('2', 'C#');
INSERT INTO `ea_final_project`.`skill` (`id`, `name`) VALUES ('3', 'Front End');
