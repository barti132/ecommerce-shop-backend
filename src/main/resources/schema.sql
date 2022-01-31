DROP TABLE IF EXISTS Wishlist_item;
DROP TABLE IF EXISTS Cart_item;
DROP TABLE IF EXISTS Opinion;
DROP TABLE IF EXISTS Seller_products;
DROP TABLE IF EXISTS Invoice_items;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Wishlist;
DROP TABLE IF EXISTS Cart;
DROP TABLE IF EXISTS Invoice;
DROP TABLE IF EXISTS Client;
DROP TABLE IF EXISTS Seller;
DROP TABLE IF EXISTS Address;
DROP TABLE IF EXISTS Mail;

CREATE TABLE product(
                        id_product INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        category VARCHAR(128) NOT NULL,
                        sub_category VARCHAR(128) NOT NULL,
                        producer_name VARCHAR(64) NOT NULL,
                        name VARCHAR(32) NOT NULL,
                        description TEXT NOT NULL,
                        img VARCHAR(128)
);

CREATE TABLE address(
                        id_address INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        address VARCHAR(128) NOT NULL,
                        city VARCHAR(128) NOT NULL,
                        country VARCHAR(128) NOT NULL,
                        postal_code VARCHAR(16) NOT NULL,
                        additional_info VARCHAR(128)
);

CREATE TABLE client(
                       id_client INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       id_address INT NOT NULL,
                       phone_number VARCHAR(16) NOT NULL,
                       email VARCHAR(64) NOT NULL,
                       login VARCHAR(32) NOT NULL,
                       password VARCHAR(32) NOT NULL,
                       name VARCHAR(32) NOT NULL,
                       last_name VARCHAR(32) NOT NULL,
                       is_active TINYINT NOT NULL,
                       is_admin TINYINT NOT NULL,
                       FOREIGN KEY (id_address) REFERENCES address(id_address)
);

CREATE TABLE wishlist(
                         id_wishlist INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         id_client INT NOT NULL,
                         name VARCHAR(32) NOT NULL,
                         created_DATE DATE NOT NULL,
                         FOREIGN KEY (id_client) REFERENCES client(id_client)
);

CREATE TABLE wishlist_item(
                              id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                              id_wishlist INT NOT NULL,
                              id_product INT NOT NULL,
                              quantity INT NOT NULL,
                              price_net DECIMAL(9,2) NOT NULL,
                              FOREIGN KEY (id_wishlist) REFERENCES wishlist(id_wishlist),
                              FOREIGN KEY (id_product) REFERENCES product(id_product)
);

CREATE TABLE cart(
                     id_cart INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     id_client INT NOT NULL,
                     upDATE_DATE DATE NOT NULL,
                     FOREIGN KEY (id_client) REFERENCES client(id_client)
);

CREATE TABLE cart_item(
                          id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          id_cart INT NOT NULL,
                          id_product INT NOT NULL,
                          quantity INT NOT NULL,
                          price_net DECIMAL(9,2) NOT NULL,
                          FOREIGN KEY (id_cart) REFERENCES cart(id_cart),
                          FOREIGN KEY (id_product) REFERENCES product(id_product)
);

CREATE TABLE opinion(
                        id_opinion INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        id_client INT NOT NULL,
                        id_product INT NOT NULL,
                        opinion TEXT,
                        rate TINYINT NOT NULL,
                        opinion_DATE DATE NOT NULL,
                        FOREIGN KEY (id_client) REFERENCES client(id_client),
                        FOREIGN KEY (id_product) REFERENCES product(id_product)
);

CREATE TABLE seller(
                       id_seller INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       id_address INT NOT NULL,
                       seller_name VARCHAR(128) NOT NULL,
                       description TEXT NOT NULL,
                       email VARCHAR(64) NOT NULL,
                       login VARCHAR(32) NOT NULL,
                       password VARCHAR(32) NOT NULL,
                       is_active TINYINT NOT NULL,
                       FOREIGN KEY (id_address) REFERENCES address(id_address)
);

CREATE TABLE seller_products(
                                id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                id_seller INT NOT NULL,
                                id_product INT NOT NULL,
                                quantity INT NOT NULL,
                                price_gross_per_item DECIMAL(9,2) NOT NULL,
                                price_net_per_item DECIMAL(9,2) NOT NULL,
                                FOREIGN KEY (id_seller) REFERENCES seller(id_seller),
                                FOREIGN KEY (id_product) REFERENCES product(id_product)
);

CREATE TABLE invoice(
                        id_invoice INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        id_client INT NOT NULL,
                        id_seller INT NOT NULL,
                        order_DATE DATE NOT NULL,
                        order_total_price_net DECIMAL(9,2) NOT NULL,
                        order_total_price_gorss DECIMAL(9,2) NOT NULL,
                        payment_method VARCHAR(32) NOT NULL,
                        document_type VARCHAR(64) NOT NULL,
                        additional_info VARCHAR(256),
                        FOREIGN KEY (id_client) REFERENCES client(id_client),
                        FOREIGN KEY (id_seller) REFERENCES seller(id_seller)
);

CREATE TABLE invoice_items(
                              id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                              id_product INT NOT NULL,
                              id_invoice INT NOT NULL,
                              price_gross_per_item DECIMAL(9,2) NOT NULL,
                              price_net_per_item DECIMAL(9,2) NOT NULL,
                              quantity INT NOT NULL,
                              total_price DECIMAL(9,2) NOT NULL,
                              FOREIGN KEY (id_product) REFERENCES product(id_product),
                              FOREIGN KEY (id_invoice) REFERENCES invoice(id_invoice)
);

CREATE TABLE mail(
                     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     title VARCHAR(128) NOT NULL,
                     content TEXT NOT NULL,
                     `to` VARCHAR(128) NOT NULL,
                     `from` VARCHAR(128) NOT NULL,
                     send_DATE DATE NOT NULL
);