ALTER TABLE  USERS ADD CONSTRAINT fk_USERS_ROLE FOREIGN KEY (ROLE_ID) REFERENCES  ROLES (ID);
ALTER TABLE  TOKENS ADD CONSTRAINT fk_TOKENS_USER FOREIGN KEY (USER_ID) REFERENCES  USERS (ID);