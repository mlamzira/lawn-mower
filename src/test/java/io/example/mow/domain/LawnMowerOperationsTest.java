package io.example.mow.domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static io.example.mow.domain.LawnMowerInstruction.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LawnMowerOperationsTest {

    private Surface surface = new Surface(5, 5);

    @Test
    void execute_DAA_onMower() {
        LawnMowerOperations lawnMowerOperations = new LawnMowerOperations(surface);
        UUID mowerId = lawnMowerOperations.deployMower(Orientation.NORTH, 0, 0);

        lawnMowerOperations.executeOperations(mowerId, List.of(TURN_RIGHT, MOVE_FORWARD, MOVE_FORWARD));

        LawnMower mower = lawnMowerOperations.getMower(mowerId);
        assertThat(mower.orientation()).isEqualTo(Orientation.EAST);
        assertThat(mower.ordinate()).isEqualTo(0);
        assertThat(mower.abscissa()).isEqualTo(2);
    }
    @Test
    void execute_GAA_onMower() {
        LawnMowerOperations lawnMowerOperations = new LawnMowerOperations(surface);
        UUID mowerId = lawnMowerOperations.deployMower(Orientation.EAST, 0, 0);

        lawnMowerOperations.executeOperations(mowerId, List.of(TURN_LEFT, MOVE_FORWARD, MOVE_FORWARD));

        LawnMower mower = lawnMowerOperations.getMower(mowerId);
        assertThat(mower.orientation()).isEqualTo(Orientation.NORTH);
        assertThat(mower.ordinate()).isEqualTo(2);
        assertThat(mower.abscissa()).isEqualTo(0);
    }
}
