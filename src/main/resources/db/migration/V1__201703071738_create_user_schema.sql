CREATE TABLE "users"(
    "id" UUID NOT NULL PRIMARY KEY,
    "username" VARCHAR NOT NULL,
    "email" VARCHAR NOT NULL NOT NULL,
    "password" VARCHAR NOT NULL NOT NULL,
    "salt" VARCHAR NOT NULL NOT NULL,
    "created_on" TIMESTAMP NOT NULL
);

CREATE TABLE "user_roles"(
    "id" BIGSERIAL PRIMARY KEY,
    "name" VARCHAR NOT NULL
)

CREATE TABLE "current_user_roles"(
    "id" BIGSERIAL PRIMARY KEY,
    "role_id" BIGSERIAL PRIMARY KEY,
    "user_id" UUID
);
ALTER TABLE "current_user_roles" ADD CONSTRAINT "current_user_roles_user_fk"
    FOREIGN KEY ("user_id") REFERENCES "users"("id") ON DELETE CASCADE ON UPDATE CASCADE;



CREATE TABLE "password_reset_codes"(
  "id" UUID NOT NULL PRIMARY KEY,
  "code" VARCHAR NOT NULL,
  "user_id" UUID NOT NULL,
  "valid_to" TIMESTAMP NOT NULL
);
ALTER TABLE "password_reset_codes" ADD CONSTRAINT "password_reset_codes_user_fk"
  FOREIGN KEY("user_id") REFERENCES "users"("id") ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE "tokens"(
  "id" UUID NOT NULL PRIMARY KEY,
  "selector" VARCHAR NOT NULL,
  "user_id" UUID NOT NULL,
  "valid_to" TIMESTAMP NOT NULL
);
ALTER TABLE "tokens" ADD CONSTRAINT "tokens_user_fk"
  FOREIGN KEY("user_id") REFERENCES "users"("id") ON DELETE CASCADE ON UPDATE CASCADE;
