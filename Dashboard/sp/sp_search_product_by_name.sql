CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_search_product_by_name`(
    IN name VARCHAR(10),
    OUT success BOOLEAN,
    OUT message VARCHAR(255)
)
BEGIN
    SET success = false;

    if (SELECT SELECT COUNT(name) FROM products WHERE name = products.name)
     THEN
SELECT
    p.id,
    p.name,
    p.price,
    p.quantity,
    p.description
FROM products AS p
WHERE
    DATE( p.name) = name;

SET success = true;
ELSE
        SET message = 'Name is invalid';
END IF;
END