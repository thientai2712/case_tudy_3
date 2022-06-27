CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_update_product`(
    IN id int,
    IN fullName VARCHAR (255),
    IN price INT,
    IN quantity INT(255),
    IN description VARCHAR(255),
    OUT message VARCHAR(255)
)
BEGIN
    SET message = "";
    UPDATE products AS p
    SET
        p.name = fullName,
        p.price = price,
        p.quantity = quantity,
        p.description = description,
        p.updatedAT = NOW()
    WHERE p.id = id;
    SET message = 'Update product success';
END