package org.example.Records;

import java.util.Date;

public record Event(int id, String name, Date eventDate, String location, String type) {
}
