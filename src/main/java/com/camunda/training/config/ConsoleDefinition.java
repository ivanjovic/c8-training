package com.camunda.training.config;

import com.camunda.training.util.Range;

import java.time.LocalDate;
import java.time.Year;
import java.util.Map;

public class ConsoleDefinition {

    public static final Map<Range, String> CONSOLES = Map.of(
            new Range(1972, 1980), "Magnavox Odyssey",
            new Range(1976, 1992), "Atari 2600",
            new Range(1983, 2003), "Nintendo Entertainment System (NES)",
            new Range(1987, 2004), "Sega",
            new Range(1993, 2006), "Sony Playstation 1",
            new Range(1998, 2013), "Sony Playstation 2",
            new Range(2005, 2017), "Nintendo Wii",
            new Range(2012, 2020), "Nintendo Switch",
            new Range(2020, Year.MAX_VALUE), "Playstation 5"
    );
}
