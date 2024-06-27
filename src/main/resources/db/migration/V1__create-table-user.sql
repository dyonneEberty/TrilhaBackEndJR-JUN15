CREATE TABLE "users" (
	"id"	INTEGER NOT NULL UNIQUE,
	"login"	TEXT NOT NULL,
	"password"	TEXT NOT NULL,
	"role"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);