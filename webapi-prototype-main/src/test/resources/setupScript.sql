#ロールセットアップ
INSERT INTO t_Role (ID, VALUE, DESCRIPTION) values ('role101', 'ADMIN','スリプトでセットアップ1')
INSERT INTO t_Role (ID, VALUE, DESCRIPTION) values ('role102', 'MEMBER','スリプトでセットアップ2')

#パーミッションセットアップ
insert into t_permissions(role_id,permissions) values('role101','writePlan')
insert into t_permissions(role_id,permissions) values('role101','readPlan')
insert into t_permissions(role_id,permissions) values('role101','writeMenu')
insert into t_permissions(role_id,permissions) values('role101','readRecord')
insert into t_permissions(role_id,permissions) values('role101','readMenu')
insert into t_permissions(role_id,permissions) values('role101','writeMember')
insert into t_permissions(role_id,permissions) values('role101','writeRecord')
insert into t_permissions(role_id,permissions) values('role101','readMember')
insert into t_permissions(role_id,permissions) values('role102','readPlan')
insert into t_permissions(role_id,permissions) values('role102','readRecord')
insert into t_permissions(role_id,permissions) values('role102','readMenu')
insert into t_permissions(role_id,permissions) values('role102','readMember')

#テストユーザセットアップ
INSERT INTO T_USER (NAME, STATUS, ID ) values ('test user', 0,'user001')
