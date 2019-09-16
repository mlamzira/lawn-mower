package io.example.mow.domain;

import java.util.*;

public class LawnMowerOperations {
    private final MowerFactory mowerFactory = new MowerFactory();
    private final Surface surface;
    private final Map<UUID, LawnMower> mowers = new LinkedHashMap<>();

    public LawnMowerOperations(Surface surface) {

        this.surface = surface;
    }

    public UUID deployMower(Orientation orientation, int abscissa, int ordinate) {
        UUID mowerId = UUID.randomUUID();
        LawnMower lawnMower = mowerFactory.create(orientation, abscissa, ordinate, surface);
        mowers.put(mowerId, lawnMower);
        return mowerId;
    }

    public void executeOperations(UUID mowerId, List<LawnMowerInstruction> instructions) {
        LawnMower lawnMower = mowers.get(mowerId);
        instructions.forEach(instruction -> instruction.execute(lawnMower));
    }

    public LawnMower getMower(UUID mowerId) {
        return mowers.get(mowerId);
    }

    public List<LawnMower> getAllMowers() {
        return new ArrayList<>(mowers.values());
    }
}
