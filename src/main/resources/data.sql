
INSERT INTO T_Permission (Prm_Id, Prm_Name, version) VALUES (
   1, 'User', 0);
INSERT INTO T_Permission (Prm_Id, Prm_Name, version) VALUES (
   2, 'Exploit', 0);

INSERT INTO T_Role (Rle_Id, Rle_Name, version) VALUES (
   1, 'Admin', 0);
INSERT INTO T_Role (Rle_Id, Rle_Name, version) VALUES (
   2, 'User', 0);
INSERT INTO T_Role (Rle_Id, Rle_Name, version) VALUES (
   3, 'Reporter', 0);

INSERT INTO T_Rel_Role_Permission (Rle_Id, Prm_Id, Rel_Create, Rel_Write, Rel_Read, Rel_Delete) VALUES (
   1, 1, true ,true ,true ,true );
INSERT INTO T_Rel_Role_Permission (Rle_Id, Prm_Id, Rel_Create, Rel_Write, Rel_Read, Rel_Delete) VALUES (
   1, 2, true ,true ,true ,true );

INSERT INTO T_Rel_Role_Permission (Rle_Id, Prm_Id, Rel_Create, Rel_Write, Rel_Read, Rel_Delete) VALUES (
   2, 1, false ,false ,true ,false );

INSERT INTO T_User (Usr_Id, Usr_Email, Usr_First_Name, Usr_Last_Name, Usr_Password, version, Rle_Id) VALUES (
   1, 'admin@gmail.com','inzo','dialo','$2a$10$rqxw1qSae5Z1l5RgtTWehOZilOZ8YVC0fLkNMcVmfjuou5tr/klFC',0, 1);
INSERT INTO T_User (Usr_Id, Usr_Email, Usr_First_Name, Usr_Last_Name, Usr_Password, version, Rle_Id) VALUES (
   2, 'user@gmail.com','inzo','dialo','$2a$10$rqxw1qSae5Z1l5RgtTWehOZilOZ8YVC0fLkNMcVmfjuou5tr/klFC',0, 2);
INSERT INTO T_User (Usr_Id, Usr_Email, Usr_First_Name, Usr_Last_Name, Usr_Password, version, Rle_Id) VALUES (
   3, 'reporter@gmail.com','inzo','dialo','$2a$10$rqxw1qSae5Z1l5RgtTWehOZilOZ8YVC0fLkNMcVmfjuou5tr/klFC',0, 3);