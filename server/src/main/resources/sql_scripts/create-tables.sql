CREATE TABLE locations (
	id SERIAL PRIMARY KEY,
  	name VARCHAR(255) NOT NULL,
  	address VARCHAR(255) NOT NULL,
  	capacity INT
);

CREATE TABLE events (
  	id SERIAL PRIMARY KEY,
  	name VARCHAR(255) NOT NULL,
  	event_date DATE NOT NULL,
  	location VARCHAR(255) NOT NULL,
  	type VARCHAR(255) NOT NULL
);

CREATE TABLE events_locations (
  	event_id INTEGER REFERENCES events(id),
  	location_id INTEGER REFERENCES locations(id),
  	PRIMARY KEY(event_id, location_id)
);

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	username VARCHAR(15) UNIQUE NOT NULL,
	password VARCHAR(15) NOT NULL,
	is_admin BOOL NOT NULL
);

CREATE TABLE locations_description (
	la_mare BOOL,
	pe_plaja BOOL,
	la_munte BOOL,
	pe_camp BOOL,
	pe_deal BOOL,
	la_padure BOOL,
	la_periferie BOOL,
	cu_lac BOOL,
	cu_rau BOOL,
	cu_cascada BOOL
);

ALTER TABLE events ADD ticket_price INT;
ALTER TABLE events DROP COLUMN location;
ALTER TABLE users ADD CONSTRAINT username_unique UNIQUE(username);

