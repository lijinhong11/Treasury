package io.github.lijinhong11.treasury.permission;

import java.util.Collection;
import java.util.UUID;

/**
 * Represents a permission group in a permission system.
 */
public interface PermissionGroup {
    /**
     * Gets the unique name of this group.
     *
     * @return group name
     */
    String getName();

    /**
     * Gets all permission nodes assigned to this group.
     *
     * @return collection of permission nodes
     */
    Collection<String> getPermissions();

    /**
     * Gets all groups this group inherits from.
     * <br>
     * Implementations may return an empty collection if
     * inheritance is not supported.
     *
     * @return parent groups
     */
    Collection<PermissionGroup> getParents();

    /**
     * Gets all players that belong to this group.
     *
     * @return collection of player UUIDs
     */
    Collection<UUID> getMembers();

    /**
     * Gets the weight (priority) of this group.
     * <br>
     * Higher weights typically mean higher priority.
     * If the permission system does not support weights,
     * implementations should return 0.
     *
     * @return group weight
     */
    int getWeight();
}