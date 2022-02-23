DROP TABLE IF EXISTS Wishlist_item;
DROP TABLE IF EXISTS Cart_item;
DROP TABLE IF EXISTS Opinion;
DROP TABLE IF EXISTS Seller_products;
DROP TABLE IF EXISTS Invoice_items;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Wishlist;
DROP TABLE IF EXISTS Cart;
DROP TABLE IF EXISTS Invoice;
DROP TABLE IF EXISTS token;
DROP TABLE IF EXISTS user_adresses;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Address;

CREATE TABLE product
(
    id            INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    category      VARCHAR(128)  NOT NULL,
    producer_name VARCHAR(64)   NOT NULL,
    name          VARCHAR(32)   NOT NULL,
    description   TEXT          NOT NULL,
    img           VARCHAR(128),
    price_net     NUMERIC(9, 2) NOT NULL,
    price_gross   NUMERIC(9, 2) NOT NULL
);

CREATE TABLE address
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    address     VARCHAR(128) NOT NULL,
    city        VARCHAR(128) NOT NULL,
    country     VARCHAR(128) NOT NULL,
    postal_code VARCHAR(16)  NOT NULL
);

CREATE TABLE user
(
    id           INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_address   INT,
    phone_number VARCHAR(16),
    email        VARCHAR(64)  NOT NULL,
    login        VARCHAR(64)  NOT NULL,
    password     VARCHAR(255) NOT NULL,
    name         VARCHAR(32)  NOT NULL,
    last_name    VARCHAR(32)  NOT NULL,
    role         VARCHAR(255) NOT NULL,
    enabled      BOOLEAN      NOT NULL,
    FOREIGN KEY (id_address) REFERENCES address (id)
);

CREATE TABLE token
(
    id         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    token      VARCHAR(255) NOT NULL,
    id_user    INT          ,
    expiryDate DATE         ,
    FOREIGN KEY (id_user) REFERENCES user (id)
);

CREATE TABLE wishlist
(
    id           INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_user      INT         NOT NULL,
    name         VARCHAR(32) NOT NULL,
    created_DATE DATE        NOT NULL,
    FOREIGN KEY (id_user) REFERENCES user (id)
);

CREATE TABLE wishlist_item
(
    id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_wishlist INT NOT NULL,
    id_product  INT NOT NULL,
    quantity    INT NOT NULL,
    FOREIGN KEY (id_wishlist) REFERENCES wishlist (id),
    FOREIGN KEY (id_product) REFERENCES product (id)
);

CREATE TABLE cart
(
    id          INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_user     INT  NOT NULL,
    upDATE_DATE DATE NOT NULL,
    FOREIGN KEY (id_user) REFERENCES user (id)
);

CREATE TABLE cart_item
(
    id         INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_cart    INT NOT NULL,
    id_product INT NOT NULL,
    quantity   INT NOT NULL,
    FOREIGN KEY (id_cart) REFERENCES cart (id),
    FOREIGN KEY (id_product) REFERENCES product (id)
);

CREATE TABLE opinion
(
    id           INT     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_user      INT     NOT NULL,
    id_product   INT     NOT NULL,
    opinion      TEXT,
    rate         TINYINT NOT NULL,
    opinion_DATE DATE    NOT NULL,
    FOREIGN KEY (id_user) REFERENCES user (id),
    FOREIGN KEY (id_product) REFERENCES product (id)
);

CREATE TABLE seller_products
(
    id                   INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_product           INT           NOT NULL,
    quantity             INT           NOT NULL,
    price_gross_per_item NUMERIC(9, 2) NOT NULL,
    price_net_per_item   NUMERIC(9, 2) NOT NULL,
    FOREIGN KEY (id_product) REFERENCES product (id)
);

CREATE TABLE invoice
(
    id                      INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_user                 INT           NOT NULL,
    order_DATE              DATE          NOT NULL,
    order_total_price_net   DECIMAL(9, 2) NOT NULL,
    order_total_price_gorss DECIMAL(9, 2) NOT NULL,
    payment_method          VARCHAR(32)   NOT NULL,
    document_type           VARCHAR(64)   NOT NULL,
    additional_info         VARCHAR(256),
    FOREIGN KEY (id_user) REFERENCES user (id)
);

CREATE TABLE invoice_items
(
    id                   INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_product           INT           NOT NULL,
    id_invoice           INT           NOT NULL,
    price_gross_per_item DECIMAL(9, 2) NOT NULL,
    price_net_per_item   DECIMAL(9, 2) NOT NULL,
    quantity             INT           NOT NULL,
    total_price          DECIMAL(9, 2) NOT NULL,
    FOREIGN KEY (id_product) REFERENCES product (id),
    FOREIGN KEY (id_invoice) REFERENCES invoice (id)
);