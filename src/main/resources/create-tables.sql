CREATE TABLE locations (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  capacity INT
);

CREATE TABLE events (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  eventDate DATE NOT NULL,
  location VARCHAR(255) NOT NULL,
  type VARCHAR(255) NOT NULL
);

CREATE TABLE events_locations (
  event_id INTEGER REFERENCES events(id),
  location_id INTEGER REFERENCES locations(id),
  PRIMARY KEY(event_id, location_id)
);
/
SELECT * from events;
