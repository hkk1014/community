ALTER TABLE `notification`
ADD COLUMN `notifier_name`  varchar(100) NULL AFTER `notifier`,
ADD COLUMN `outer_title`  varchar(256) NULL AFTER `outerId`;
