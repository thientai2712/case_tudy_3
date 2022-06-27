CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_product`(
IN fullName VARCHAR (255),
IN price DOUBLE,
IN quantity INT,
IN description VARCHAR(255),
OUT success tinyint(1),
OUT message VARCHAR(255)
)
BEGIN
    DECLARE price_check int;
    DECLARE quantity_check int;
	SET success = false;
	SET price_check = (SELECT COUNT(price) FROM products WHERE price = products.price);
    SET quantity_check = (SELECT COUNT(quantity) FROM products WHERE quantity = products.quantity);
    IF (price_check != 0)
    THEN
        SET message = 'Price is not valid';
ELSE
            IF(quantity_check != 0)
            THEN
                SET message = 'Quantity is not valid';
ELSE
                    INSERT INTO products(name,price,quantity,description,createdAt)
					VALUES(fullName,price,quantity,description,NOW());
                    SET message = 'Add new user successful';
                    SET success = true;
END IF;
END IF;
END