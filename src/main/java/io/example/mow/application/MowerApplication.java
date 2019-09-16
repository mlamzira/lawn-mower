package io.example.mow.application;

import io.example.mow.domain.LawnMowerInstruction;
import io.example.mow.domain.LawnMowerOperations;
import io.example.mow.domain.Orientation;
import io.example.mow.domain.Surface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class MowerApplication {

    private File configurationFile;
    private Map<UUID, List<LawnMowerInstruction>> allMowersInstructions = new HashMap<>();
    private LawnMowerOperations lawnMowerOperations;

    public MowerApplication(File configurationFile) throws FileNotFoundException {
        this.configurationFile = configurationFile;
        deployMowers();
    }

    private void deployMowers() throws FileNotFoundException {
        try(Scanner scanner = new Scanner(new FileInputStream(configurationFile))) {
            String surface = scanner.nextLine();
            initOperations(surface);
            deployMowers(scanner);
        }
    }

    private void deployMowers(Scanner scanner) {
        while(scanner.hasNext()) {
            String mowerConfiguration = scanner.nextLine();
            String instructions = scanner.nextLine();

            deployMower(mowerConfiguration, instructions);
        }
    }

    public void startMowing() {
        allMowersInstructions.forEach(lawnMowerOperations::executeOperations);
    }

    public void printPositions() {
        lawnMowerOperations.getAllMowers().forEach(lawnMower -> {
            String mowerPosition = lawnMower.abscissa() + " " +
                    lawnMower.ordinate() + " " +
                    lawnMower.orientation().name().charAt(0);
            System.out.println(mowerPosition);
        });
    }

    private void deployMower(String mowerConfiguration, String instructions) {
        String[] tokens = mowerConfiguration.split(" ");
        int abscissa = Integer.parseInt(tokens[0]);
        int ordinate = Integer.parseInt(tokens[1]);
        Orientation orientation = Orientation.from(tokens[2]);

        UUID mowerId = lawnMowerOperations.deployMower(orientation, abscissa, ordinate);

        List<LawnMowerInstruction> mowerInstructions = mapInstruction(instructions);
        allMowersInstructions.put(mowerId, mowerInstructions);
    }

    private List<LawnMowerInstruction> mapInstruction(String instructions) {
        List<LawnMowerInstruction> mowerInstructions = new ArrayList<>();
        for (char rawInstruction : instructions.toCharArray()) {
            LawnMowerInstruction instruction = LawnMowerInstruction.from(rawInstruction);
            mowerInstructions.add(instruction);
        }
        return mowerInstructions;
    }

    private void initOperations(String rawSurface) {
        String[] tokens = rawSurface.split(" ");
        Surface surface = new Surface(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
        lawnMowerOperations = new LawnMowerOperations(surface);
    }
}
