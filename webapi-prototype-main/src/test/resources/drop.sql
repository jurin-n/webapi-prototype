ALTER TABLE t_team_t_member DROP CONSTRAINT tteamtmemberTeamID
ALTER TABLE t_team_t_member DROP CONSTRAINT ttmtmembermmbersID
ALTER TABLE t_permissions DROP CONSTRAINT tpermissionsRoleID
ALTER TABLE t_User_t_Role DROP CONSTRAINT tUsertRoleroles_ID
ALTER TABLE t_User_t_Role DROP CONSTRAINT t_User_t_Role_ID
DROP TABLE t_PracticeMenu
DROP TABLE t_PracticePlan
DROP TABLE t_PracticeMember
DROP TABLE t_PracticeRecord
DROP TABLE t_member
DROP TABLE t_team
DROP TABLE t_Role
DROP TABLE t_User
DROP TABLE t_team_t_member
DROP TABLE t_permissions
DROP TABLE t_User_t_Role
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN'
