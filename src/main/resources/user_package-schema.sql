DROP SCHEMA user_package CASCADE;
-- Create the user_package schema
CREATE SCHEMA user_package;

-- Create a function to create a new user within the user_package schema
CREATE FUNCTION user_package.create_user(
    p_username VARCHAR(15),
    p_password VARCHAR(15),
    p_is_admin BOOL
) RETURNS VARCHAR(255) AS $$
DECLARE
	result VARCHAR(255);
BEGIN
    BEGIN
		--Attempt to insert the new user
		INSERT INTO user_package.users (username, password, is_admin)
		VALUES (p_username, p_password, p_is_admin);

		--If the insertion is successful, set the result message
		result := 'The user has been SUCCESSFULLY registered';
	EXCEPTION
		WHEN others THEN
			--If an exception occurs, set the result message
			result := 'The user FAILED to register';
	END;
	return result;
END;
$$ LANGUAGE plpgsql;

-- Create a function to get a user by ID within the user_package schema
CREATE FUNCTION user_package.get_user_by_id(p_id INTEGER) RETURNS user_package.users AS $$
DECLARE
    user_row user_package.users;
	result VARCHAR(255);
BEGIN
	BEGIN
    -- Your implementation here
     	SELECT * INTO user_row FROM user_package.users WHERE id = p_id;
		result:= 'The user with the given ID has been found';
	EXCEPTION
		when others THEN
			result:= 'ERROR: The user with the given ID has NOT been found';
			RETURN null;
	END;
    RETURN user_row;
END;
$$ LANGUAGE plpgsql;

-- Create a function to get a user by username within the user_package schema
CREATE FUNCTION user_package.get_user_by_username(p_username VARCHAR(15)) RETURNS user_package.users AS $$
DECLARE
    user_row user_package.users;
	result varchar(255);
BEGIN
	BEGIN
    	SELECT * INTO user_row FROM user_package.users WHERE username = p_username;
		result := 'The user with the given username has been found';
	EXCEPTION
		when others then
			result := 'The user with the given username has NOT been found';
			return null;
	END;
    RETURN user_row;
END;
$$ LANGUAGE plpgsql;

-- Create a function to update an existing user within the user_package schema
CREATE FUNCTION user_package.update_user(
    p_id INTEGER,
    p_username VARCHAR(15),
    p_password VARCHAR(15),
    p_is_admin BOOL
) RETURNS varchar(255) AS $$
DECLARE
	result varchar(255);
BEGIN
    -- Your implementation here
	BEGIN
    	UPDATE user_package.users
     	SET
        	username = p_username,
         	password = p_password,
         	is_admin = p_is_admin
     	WHERE
         	id = p_id;
		result := 'The information of the given user has been SUCCESSFULLY updated';
	EXCEPTION
		when others then
			result := 'The information of the given user COULD NOT be changed';
	END;
	return result;
END;
$$ LANGUAGE plpgsql;

-- Create a function to delete a user by ID within the user_package schema
CREATE FUNCTION user_package.delete_user(p_id INTEGER) RETURNS VARCHAR(255) AS $$
DECLARE
	result varchar(255);
BEGIN
	BEGIN
    	DELETE FROM user_package.users WHERE id = p_id;
		result := 'The user was SUCCESSFULLY deleted';
	EXCEPTION
		when others then
			result := 'The user COULD NOT be deleted';
	END;
	return result;
END;
$$ LANGUAGE plpgsql;
/
