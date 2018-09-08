insert into acl_sid values (1, true, 'ADMIN');
insert into acl_class values (1, 'org.aivan.cloud1.objekti.model.Firma');
insert into acl_object_identity(OBJECT_ID_CLASS,OBJECT_ID_IDENTITY,PARENT_OBJECT,OWNER_SID,ENTRIES_INHERITING) values (1,1,NULL,1,true);
insert into acl_object_identity(OBJECT_ID_CLASS,OBJECT_ID_IDENTITY,PARENT_OBJECT,OWNER_SID,ENTRIES_INHERITING) values (1,2,NULL,1,true);
insert into acl_object_identity(OBJECT_ID_CLASS,OBJECT_ID_IDENTITY,PARENT_OBJECT,OWNER_SID,ENTRIES_INHERITING) values (1,3,NULL,1,true);
insert into acl_object_identity(OBJECT_ID_CLASS,OBJECT_ID_IDENTITY,PARENT_OBJECT,OWNER_SID,ENTRIES_INHERITING) values (1,4,NULL,1,true);
insert into acl_object_identity(OBJECT_ID_CLASS,OBJECT_ID_IDENTITY,PARENT_OBJECT,OWNER_SID,ENTRIES_INHERITING) values (1,5,NULL,1,true);

INSERT INTO acl_entry 
  (id, acl_object_identity, ace_order, 
  sid, mask, granting, audit_success, audit_failure) 
  VALUES
  (1, 1, 1, 1, 32, 1, 1, 1),
  (2, 3, 1, 1, 32, 1, 1, 1),
  (3, 4, 1, 1, 32, 1, 1, 1),
  (4, 5, 1, 1, 32, 1, 1, 1),
  (5, 6, 1, 1, 32, 1, 1, 1),
;