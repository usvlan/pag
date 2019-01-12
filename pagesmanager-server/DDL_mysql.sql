
-- 表：doc
CREATE TABLE doc (
    id          INTEGER      PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR (50) NOT NULL,
    order_index INTEGER      NOT NULL,
    parent_id   INTEGER      NOT NULL,
    project_id  INTEGER      NOT NULL,
    is_show     INTEGER      NOT NULL,
    gmt_create  DATETIME     NOT NULL,
    gmt_update   DATETIME     NOT NULL
);


-- 表：doc_content
CREATE TABLE doc_content (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    doc_id     INTEGER  NOT NULL,
    content    TEXT     NOT NULL,
    gmt_create DATETIME NOT NULL,
    gmt_update DATETIME NOT NULL
);


-- 表：project
CREATE TABLE project (
    id         INTEGER      PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR (50) NOT NULL,
    local_git_path VARCHAR (100),
    git_url        VARCHAR (200),
    git_cmd        VARCHAR (200),
    gmt_create DATETIME     NOT NULL,
    gmt_update DATETIME     NOT NULL
);
