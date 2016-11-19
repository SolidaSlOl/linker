CREATE TABLE IF NOT EXISTS tags (
  id SERIAL,
  name VARCHAR(30),
  PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS idx_tags_name ON tags (name);

CREATE TABLE IF NOT EXISTS users (
  id SERIAL,
  name VARCHAR(50),
  password VARCHAR(20),
  PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS idx_users_name ON users (name);
CREATE INDEX IF NOT EXISTS idx_users_password ON users (password);

CREATE TABLE IF NOT EXISTS links (
id SERIAL,
actual VARCHAR(1000),
clicks INT,
description VARCHAR(500),
user_id INT,
PRIMARY KEY (id),
FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS link_tag (
  link_id INT NOT NULL,
  tag_id INT NOT NULL,
  PRIMARY KEY (link_id, tag_id),
  FOREIGN KEY (link_id) REFERENCES links(id),
  FOREIGN KEY (tag_id) REFERENCES tags(id)
);
CREATE INDEX IF NOT EXISTS idx_link_tag_tag_id ON link_tag(tag_id);

CREATE TABLE IF NOT EXISTS user_tag (
  user_id INT NOT NULL,
  tag_id INT NOT NULL,
  PRIMARY KEY (user_id, tag_id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (tag_id) REFERENCES tags(id)
);
CREATE INDEX IF NOT EXISTS idx_user_tag_tag_id ON user_tag(tag_id);