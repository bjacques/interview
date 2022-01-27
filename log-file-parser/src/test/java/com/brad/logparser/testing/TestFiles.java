package com.brad.logparser.testing;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TestFiles {
    public static Path getPathFor(String filename) {
        return Paths.get("src","test", "resources", filename);
    }
}
