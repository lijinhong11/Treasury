package io.github.lijinhong11.treasury.packet;

import io.github.lijinhong11.treasury.points.PointsProvider;

import java.util.UUID;

/**
 * A points synchronization packet
 */
public interface IPointsSyncPacket {
    /**
     * Gets the player
     *
     * @return the player
     */
    UUID player();

    /**
     * Gets the points provider
     *
     * @return the points provider
     */
    PointsProvider provider();

    /**
     * Gets the points
     *
     * @return the points
     */
    int points();
}
