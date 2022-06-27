CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_user`(
IN fullName VARCHAR (255),
IN email VARCHAR(255),
IN mobile VARCHAR(255),
OUT success tinyint(1),
OUT message VARCHAR(255)
)
BEGIN
    DECLARE phone_check int;
    DECLARE email_check int;
	SET success = false;
	SET phone_check = (SELECT COUNT(mobile) FROM users WHERE mobile = users.mobile);
    SET email_check = (SELECT COUNT(email) FROM users WHERE email = users.email);
    IF (phone_check != 0)
    THEN
        SET message = 'Your phone number has been used';
        ELSE
            IF(email_check != 0)
            THEN
                SET message = 'Your email has been used';
                ELSE
                    INSERT INTO users(full_name,age,email,mobile,address)
					VALUES(fullName,age,email,mobile,address);
                    SET message = 'Add new user successful';
                    SET success = true;
            END IF;
    END IF;
END