DROP database IF EXISTS task_manager_api;

CREATE DATABASE task_manager_api;

USE task_manager_api;

CREATE TABLE IF NOT EXISTS users
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(45),
    password VARCHAR(60),
    active TINYINT DEFAULT 1,
    first_name VARCHAR(45),
    last_name VARCHAR(45),
    roles VARCHAR(45)
);

CREATE TABLE IF NOT EXISTS tasks
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(45),
    completed TINYINT DEFAULT 0,
    user_id INT NOT NULL
);

ALTER TABLE tasks 
ADD foreign key (user_id) references users(id);