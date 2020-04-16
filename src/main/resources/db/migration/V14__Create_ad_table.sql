CREATE TABLE `NewTable` (
`id`  bigint NOT NULL AUTO_INCREMENT ,
`title`  varchar(256) NULL ,
`image`  varchar(256) NULL ,
`gmt_start`  bigint NULL ,
`gmt_end`  bigint NULL ,
`gmt_create`  bigint NULL ,
`gmt_modified`  bigint NULL ,
`status`  int NULL ,
`pos`  varchar(10) NOT NULL ,
PRIMARY KEY (`id`)
)
;
