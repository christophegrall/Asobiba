CREATE TABLE IF NOT EXISTS rooms (
  room_id   INT PRIMARY KEY AUTO_INCREMENT,
  title     VARCHAR(64) NOT NULL,
  room_name VARCHAR(32) NOT NULL,
  UNIQUE INDEX idx_room_name(room_name)
);

CREATE TABLE IF NOT EXISTS room_comments (
  comment_id   INT PRIMARY KEY AUTO_INCREMENT,
  room_id      INT         NOT NULL,
  `comment`    TEXT        NOT NULL,
  user_name    VARCHAR(32) NOT NULL,
  commented_at DATETIME    NOT NULL,
  CONSTRAINT `comment_room_id_fk` FOREIGN KEY (room_id) REFERENCES `rooms` (`room_id`)
);


CREATE TABLE IF NOT EXISTS room_image (
  `room_id` INT PRIMARY KEY,
  `data`    LONGBLOB NOT NULL,
  CONSTRAINT `room_image_fk` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`)
);
