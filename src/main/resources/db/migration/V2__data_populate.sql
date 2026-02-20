INSERT INTO categories (name) VALUES
                                  ('Electronics'),
                                  ('Clothing'),
                                  ('Books');
INSERT INTO users (name, email, password) VALUES
                                              ('John Doe', 'john@example.com', 'password123'),
                                              ('Alice Smith', 'alice@example.com', 'password123'),
                                              ('Bob Brown', 'bob@example.com', 'password123');
INSERT INTO profiles (id, bio, phone_number, date_of_birth, loyalty_points) VALUES
                                                                                (1, 'Tech enthusiast', '1234567890', '1995-05-10', 120),
                                                                                (2, 'Fashion lover', '9876543210', '1998-08-22', 250),
                                                                                (3, 'Book reader', '5556667777', '1992-03-15', 75);
INSERT INTO addresses (street, city, state, zip, user_id) VALUES
                                                              ('123 Main St', 'New York', 'NY', '10001', 1),
                                                              ('45 Park Ave', 'Chicago', 'IL', '60007', 2),
                                                              ('78 Lake View', 'Dallas', 'TX', '75001', 3);
INSERT INTO products (name, price, description, category_id) VALUES
                                                                 ('iPhone 15', 999.99, 'Latest Apple smartphone', 1),
                                                                 ('Samsung TV', 799.99, '4K Smart TV', 1),
                                                                 ('T-Shirt', 19.99, 'Cotton t-shirt', 2),
                                                                 ('Jeans', 49.99, 'Slim fit jeans', 2),
                                                                 ('Spring Boot Book', 29.99, 'Learn Spring Boot', 3);
INSERT INTO wishlist (product_id, user_id) VALUES
                                               (1, 1),
                                               (2, 1),
                                               (3, 2),
                                               (5, 3);
