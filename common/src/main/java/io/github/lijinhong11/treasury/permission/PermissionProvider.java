package io.github.lijinhong11.treasury.permission;

import java.util.Collection;
import java.util.UUID;

/**
 * Represents a permission system implementation.
 */
public interface PermissionProvider {
    /**
     * Checks if permission implementation is enabled.
     *
     * @return true if enabled
     */
    boolean isEnabled();

    /**
     * Gets the unique name of permission implementation.
     *
     * @return the unique name of the permission implementation
     */
    String getName();

    /**
     * Checks whether the specified player has the given permission node.
     *
     * @param player player UUID
     * @param permission permission node
     * @return true if the player has the permission
     */
    boolean hasPermission(UUID player, String permission);

    /**
     * Grants a permission node to the specified player.
     *
     * @param player player UUID
     * @param permission permission node
     * @return true if the permission was successfully added
     */
    boolean addPermission(UUID player, String permission);

    /**
     * Removes a permission node from the specified player.
     *
     * @param player player UUID
     * @param permission permission node
     * @return true if the permission was successfully removed
     */
    boolean removePermission(UUID player, String permission);

    /**
     * Gets all permissions assigned to the specified player.
     *
     * @param player player UUID
     * @return collection of permission nodes
     */
    Collection<String> getPermissions(UUID player);

    /**
     * Checks if the specified player belongs to the given group.
     *
     * @param player player UUID
     * @param group group name
     * @return true if the player is in the group
     */
    boolean inGroup(UUID player, String group);

    /**
     * Adds the specified player to the given group.
     *
     * @param player player UUID
     * @param group group name
     * @return true if the player was successfully added
     */
    boolean addGroup(UUID player, String group);

    /**
     * Removes the specified player from the given group.
     *
     * @param player player UUID
     * @param group group name
     * @return true if the player was successfully removed
     */
    boolean removeGroup(UUID player, String group);

    /**
     * Gets all groups the specified player belongs to.
     *
     * @param player player UUID
     * @return collection of group names
     */
    Collection<PermissionGroup> getGroups(UUID player);

    /**
     * Gets the primary group of the specified player.
     *
     * @param player player UUID
     * @return primary group name
     */
    PermissionGroup getPrimaryGroup(UUID player);

    /**
     * Gets all groups in the permission implementation.
     *
     * @return a collection of all permission groups
     */
    Collection<PermissionGroup> getAllGroups();
}