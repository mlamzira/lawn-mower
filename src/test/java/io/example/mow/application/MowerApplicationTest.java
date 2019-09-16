package io.example.mow.application;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

class MowerApplicationTest {

    @Test
    void applicationExample() throws FileNotFoundException {
        URL resource = MowerApplicationTest.class.getResource("/mowers.txt");
        MowerApplication mowerApplication = new MowerApplication(new File(resource.getFile()));

        mowerApplication.startMowing();

        mowerApplication.printPositions();
    }
}
