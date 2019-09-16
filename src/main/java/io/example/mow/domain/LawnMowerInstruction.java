package io.example.mow.domain;

import java.util.Arrays;
import java.util.function.Consumer;

public enum LawnMowerInstruction {
    TURN_RIGHT(LawnMower::turnRight, 'D'),
    TURN_LEFT(LawnMower::turnLeft, 'G'),
    MOVE_FORWARD(LawnMower::moveForward, 'A');

    private Consumer<LawnMower> instruction;
    private char instructionCharacter;

    LawnMowerInstruction(Consumer<LawnMower> instruction, char instructionCharacter) {
        this.instruction = instruction;
        this.instructionCharacter = instructionCharacter;
    }

    public static LawnMowerInstruction from(char rawInstruction) {
        return Arrays.stream(values())
                .filter(instruction -> instruction.instructionCharacter == rawInstruction)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("unknown command :" + rawInstruction));
    }

    public void execute(LawnMower mower) {
        instruction.accept(mower);
    }
}
