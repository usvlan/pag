--
-- 由SQLiteStudio v3.1.1 产生的文件 周五 十一月 2 15:36:39 2018
--
-- 文本编码：UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- 表：doc
CREATE TABLE doc (
    id          INTEGER      PRIMARY KEY AUTOINCREMENT,
    name        VARCHAR (50) NOT NULL,
    content_id  INTEGER      NOT NULL,
    order_index INTEGER      NOT NULL,
    parent_id   INTEGER      NOT NULL,
    project_id  INTEGER      NOT NULL,
    is_show     INTEGER      NOT NULL,
    gmt_create  DATETIME     NOT NULL,
    gm_update   DATETIME     NOT NULL
);


-- 表：doc_content
CREATE TABLE doc_content (
    id         INTEGER  PRIMARY KEY AUTOINCREMENT,
    content    TEXT     NOT NULL,
    doc_id     INTEGER  NOT NULL,
    gmt_create DATETIME NOT NULL,
    gmt_update DATETIME NOT NULL
);


-- 表：project
CREATE TABLE project (
    id         INTEGER      PRIMARY KEY AUTOINCREMENT,
    name       VARCHAR (50) NOT NULL,
    local_git_path VARCHAR (100),
    git_cmd        VARCHAR (200),
    gmt_create DATETIME     NOT NULL,
    gmt_update DATETIME     NOT NULL
);


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
