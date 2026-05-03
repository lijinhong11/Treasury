package io.github.lijinhong11.treasury.packet;

import io.github.lijinhong11.treasury.points.PointsProvider;

import java.util.UUID;

public interface IPointsSyncPacket {
    UUID player();

    /**
     * Gets the points provider
     * @return the points provider
     */
    PointsProvider provider();

    /**
     * Gets the points
     * @return the points
     */
    int points();
}
