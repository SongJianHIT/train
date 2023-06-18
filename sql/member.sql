drop table if exists `member`;
create table `member` (
  `id` bigint not null comment 'id',
  `mobile` varchar(11) comment '手机号',
  primary key (`id`),
  unique key `mobile_unique` (`mobile`)
) engine=innodb default charset=utf8mb4 comment='会员';

drop table if exists `passenger`;
create table `passenger` (
  `id` bigint not null comment 'id',
  `member_id` bigint not null comment '会员id',
  `name` varchar(20) not null comment '姓名',
  `id_card` varchar(18) not null comment '身份证',
  `type` char(1) not null comment '旅客类型|枚举[PassengerTypeEnum]',
  `create_time` datetime(3) comment '新增时间',
  `update_time` datetime(3) comment '修改时间',
  primary key (`id`),
  index `member_id_index` (`member_id`)
) engine=innodb default charset=utf8mb4 comment='乘车人';
CREATE TABLE `undo_log` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `branch_id` bigint(20) NOT NULL,
                            `xid` varchar(100) NOT NULL,
                            `context` varchar(128) NOT NULL,
                            `rollback_info` longblob NOT NULL,
                            `log_status` int(11) NOT NULL,
                            `log_created` datetime NOT NULL,
                            `log_modified` datetime NOT NULL,
                            `ext` varchar(100) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;