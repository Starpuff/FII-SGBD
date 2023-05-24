--- TRIGGER
-- Create the trigger function
CREATE OR REPLACE FUNCTION prevent_duplicate_events()
    RETURNS TRIGGER AS $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM events_locations el
        JOIN events e ON e.id = el.event_id
        WHERE el.location_id = NEW.location_id
        AND e.event_date = NEW.event_date
    ) THEN
        RAISE EXCEPTION 'An event already exists at the same location on the same day.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create the trigger
CREATE TRIGGER check_duplicate_events
    BEFORE INSERT ON events
    FOR EACH ROW
    EXECUTE FUNCTION prevent_duplicate_events();

-- TRIGGER FOR LOCATIONS
CREATE OR REPLACE FUNCTION prevent_duplicate_locations()
	RETURNS TRIGGER AS $$
BEGIN
	IF EXISTS (
		SELECT 1
		FROM locations
		where address = new.address
	) THEN
		RAISE EXCEPTION 'A location already exists at this address.';
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_duplicate_locations
	BEFORE INSERT ON locations
	FOR EACH ROW
	EXECUTE FUNCTION prevent_duplicate_locations();
