DROP TABLE IF EXISTS `doc`;
CREATE TABLE `doc` (
  `id` integer AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `order_index` integer NOT NULL,
  `parent_id` integer NOT NULL,
  `project_id` integer NOT NULL,
  `is_show` integer NOT NULL,
  `is_deleted` integer NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_update` datetime NOT NULL,
  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `doc_content`;
CREATE TABLE `doc_content` (
  `id` integer AUTO_INCREMENT,
  `content` text NOT NULL,
  `doc_id` integer NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_update` datetime NOT NULL,
  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` integer AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `local_git_path` varchar(100),
  `git_url` varchar(200),
  `git_cmd` varchar(200),
  `gmt_create` datetime NOT NULL,
  `gmt_update` datetime NOT NULL,
  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `template`;
CREATE TABLE `template` (
  `id` integer NOT NULL AUTO_INCREMENT,
  `name` varchar(100),
  `content` MEDIUMTEXT,
  PRIMARY KEY (`id`)
);

