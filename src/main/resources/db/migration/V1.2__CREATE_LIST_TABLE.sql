CREATE TABLE list (
    list_id INT,
    title VARCHAR(50),
    list_description VARCHAR(50),
    item_id INT,
    PRIMARY KEY (list_id),
    CONSTRAINT fk_item_id FOREIGN KEY (item_id)
    REFERENCES item (item_id) ON UPDATE CASCADE
);

