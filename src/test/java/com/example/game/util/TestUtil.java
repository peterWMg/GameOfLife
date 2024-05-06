package com.example.game.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestUtil {

    public TestUtil() {
    }

    public String loadResultFromFile(String filePath) throws IOException, URISyntaxException {
        URL res = getClass().getClassLoader().getResource(filePath);
        if (res == null) {
            throw new IllegalArgumentException("File not found!");
        } else {
            return new String(Files.readAllBytes(Paths.get(res.toURI())));
        }
    }
}
