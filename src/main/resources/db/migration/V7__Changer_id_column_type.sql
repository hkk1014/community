ALTER TABLE `question`
MODIFY COLUMN `creator`  bigint(11) NULL DEFAULT NULL AFTER `gmt_modified`;

ALTER TABLE `comment`
MODIFY COLUMN `commentator`  bigint(11) NOT NULL AFTER `type`;