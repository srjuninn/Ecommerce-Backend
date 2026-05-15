USE ecommerce_dev;

INSERT INTO user (id, name, email, phone, password, roles)
VALUES (UUID(), 'Aline Carvalho', 'aline.carvalho@example.com', '11987654301', 'senha123', 'ROLE_USER'),
       (UUID(), 'Ana Santos', 'ana.santos@example.com', '11987654302', 'senha123', 'ROLE_USER'),
       (UUID(), 'João Silva', 'joao.silva@example.com', '11987654315', 'senha123', 'ROLE_ADMIN'),
       (UUID(), 'Pedro Lima', 'pedro.lima@example.com', '11987654323', 'senha123', 'ROLE_ADMIN'),
       (UUID(), 'Maria Oliveira', 'maria.oliveira@example.com', '11987654320', 'senha123', 'ROLE_USER'),
       (UUID(), 'Bruno Ferreira', 'bruno.ferreira@example.com', '11987654305', 'senha123', 'ROLE_USER'),
       (UUID(), 'Fernanda Costa', 'fernanda.costa@example.com', '11987654312', 'senha123', 'ROLE_USER'),
       (UUID(), 'Lucas Rocha', 'lucas.rocha@example.com', '11987654318', 'senha123', 'ROLE_USER'),
       (UUID(), 'Juliana Martins', 'juliana.martins@example.com', '11987654316', 'senha123', 'ROLE_USER'),
       (UUID(), 'Rafael Monteiro', 'rafael.monteiro@example.com', '11987654324', 'senha123', 'ROLE_USER');


INSERT INTO product_table (id, name, description, price, imgURL)
VALUES (UUID(), 'Notebook Dell Inspiron', 'Notebook para uso diário com 8GB RAM e SSD 256GB', 3500.00,
        'https://example.com/img/notebook-dell.jpg'),
       (UUID(), 'Smartphone Samsung Galaxy S25', 'Celular com tela AMOLED e câmera tripla', 4200.00,
        'https://example.com/img/galaxy-s25.jpg'),
       (UUID(), 'Monitor LG UltraWide', 'Monitor 29 polegadas ultrawide para produtividade', 1800.00,
        'https://example.com/img/monitor-lg.jpg'),
       (UUID(), 'Teclado Mecânico Redragon', 'Teclado gamer com switches azuis', 350.00,
        'https://example.com/img/teclado-redragon.jpg'),
       (UUID(), 'Mouse Gamer Logitech G502', 'Mouse com 11 botões programáveis', 420.00,
        'https://example.com/img/mouse-logitech.jpg'),
       (UUID(), 'Headset HyperX Cloud II', 'Headset gamer com som surround 7.1', 600.00,
        'https://example.com/img/headset-hyperx.jpg'),
       (UUID(), 'Smartwatch Samsung Galaxy Watch 6', 'Relógio inteligente com bateria de longa duração', 2200.00,
        'https://example.com/img/galaxywatch6.jpg'),
       (UUID(), 'Caixa de Som JBL Charge 5', 'Caixa de som portátil à prova d’água', 900.00,
        'https://example.com/img/jbl-charge5.jpg'),
       (UUID(), 'SSD Kingston 1TB', 'SSD rápido para notebooks e desktops', 700.00,
        'https://example.com/img/ssd-kingston.jpg'),
       (UUID(), 'Console PlayStation 5', 'Console de última geração da Sony', 4500.00,
        'https://example.com/img/ps5.jpg');


INSERT INTO order_table (id, moment, status, cliente_id)
VALUES (UUID(), NOW(), 0, (SELECT id FROM user WHERE email = 'aline.carvalho@example.com')),
       (UUID(), NOW(), 1, (SELECT id FROM user WHERE email = 'ana.santos@example.com')),
       (UUID(), NOW(), 2, (SELECT id FROM user WHERE email = 'joao.silva@example.com')),
       (UUID(), NOW(), 3, (SELECT id FROM user WHERE email = 'pedro.lima@example.com')),
       (UUID(), NOW(), 4, (SELECT id FROM user WHERE email = 'maria.oliveira@example.com')),
       (UUID(), NOW(), 1, (SELECT id FROM user WHERE email = 'bruno.ferreira@example.com')),
       (UUID(), NOW(), 0, (SELECT id FROM user WHERE email = 'fernanda.costa@example.com')),
       (UUID(), NOW(), 2, (SELECT id FROM user WHERE email = 'lucas.rocha@example.com')),
       (UUID(), NOW(), 3, (SELECT id FROM user WHERE email = 'juliana.martins@example.com')),
       (UUID(), NOW(), 4, (SELECT id FROM user WHERE email = 'rafael.monteiro@example.com'));


INSERT INTO order_item_table (order_id, product_id, quantity, price)
VALUES ((SELECT id
         FROM order_table
         WHERE cliente_id = (SELECT id FROM user WHERE email = 'aline.carvalho@example.com')),
        (SELECT id FROM product_table WHERE name = 'Notebook Dell Inspiron'), 1, 3500.00),
       ((SELECT id
         FROM order_table
         WHERE cliente_id = (SELECT id FROM user WHERE email = 'aline.carvalho@example.com')),
        (SELECT id FROM product_table WHERE name = 'Mouse Gamer Logitech G502'), 2, 420.00),
       ((SELECT id
         FROM order_table
         WHERE cliente_id = (SELECT id FROM user WHERE email = 'aline.carvalho@example.com')),
        (SELECT id FROM product_table WHERE name = 'Headset HyperX Cloud II'), 1, 600.00);

INSERT INTO order_item_table (order_id, product_id, quantity, price)
VALUES ((SELECT id
         FROM order_table
         WHERE cliente_id = (SELECT id FROM user WHERE email = 'ana.santos@example.com')),
        (SELECT id FROM product_table WHERE name = 'Smartphone Samsung Galaxy S25'), 1, 4200.00),
       ((SELECT id
         FROM order_table
         WHERE cliente_id = (SELECT id FROM user WHERE email = 'ana.santos@example.com')),
        (SELECT id FROM product_table WHERE name = 'Smartwatch Samsung Galaxy Watch 6'), 1, 2200.00),
       ((SELECT id
         FROM order_table
         WHERE cliente_id = (SELECT id FROM user WHERE email = 'ana.santos@example.com')),
        (SELECT id FROM product_table WHERE name = 'Caixa de Som JBL Charge 5'), 1, 900.00);

INSERT INTO order_item_table (order_id, product_id, quantity, price)
VALUES ((SELECT id
         FROM order_table
         WHERE cliente_id = (SELECT id FROM user WHERE email = 'joao.silva@example.com')),
        (SELECT id FROM product_table WHERE name = 'Monitor LG UltraWide'), 2, 1800.00),
       ((SELECT id
         FROM order_table
         WHERE cliente_id = (SELECT id FROM user WHERE email = 'joao.silva@example.com')),
        (SELECT id FROM product_table WHERE name = 'Teclado Mecânico Redragon'), 1, 350.00),
       ((SELECT id
         FROM order_table
         WHERE cliente_id = (SELECT id FROM user WHERE email = 'joao.silva@example.com')),
        (SELECT id FROM product_table WHERE name = 'SSD Kingston 1TB'), 1, 700.00);