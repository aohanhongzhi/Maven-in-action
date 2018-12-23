CREATE TABLE mvn_user(
  ur_id int(11) NOT NULL AUTO_INCREMENT,
  ur_user_name varchar(255) DEFAULT NULL,
  ur_password varchar(255) DEFAULT NULL,
  ur_age int(11) DEFAULT NULL,
  ur_status varchar(255) DEFAULT NULL,
  PRIMARY KEY (ur_id)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

INSERT INTO mvn_user(ur_user_name,ur_password,ur_age,ur_status) VALUES ('zhangsan', '123', 11, 'Active');
INSERT INTO mvn_user(ur_user_name,ur_password,ur_age,ur_status) VALUES ('lisi', '123', 13, 'Inactive');
INSERT INTO mvn_user(ur_user_name,ur_password,ur_age,ur_status) VALUES ('wangwu', '123', 13, 'Active');
