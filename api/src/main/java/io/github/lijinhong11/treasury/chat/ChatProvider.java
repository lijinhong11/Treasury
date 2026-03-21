package io.github.lijinhong11.treasury.chat;

import java.util.UUID;

/**
 * Represents a chat system implementation.
 */
public interface ChatProvider {
    /**
     * Checks whether this chat implementation is enabled.
     *
     * @return true if enabled
     */
    boolean isEnabled();

    /**
     * Gets the name of this chat implementation.
     *
     * @return provider name
     */
    String getName();

    /**
     * Gets the prefix of the specified player.
     *
     * @param player player UUID
     * @return prefix or null if not set
     */
    String getPlayerPrefix(UUID player);

    /**
     * Gets the prefix of the specified player.
     *
     * @param player player name
     * @return prefix or null if not set
     */
    String getPlayerPrefix(String player);

    /**
     * Gets the suffix of the specified player.
     *
     * @param player player UUID
     * @return suffix or null if not set
     */
    String getPlayerSuffix(UUID player);

    /**
     * Gets the suffix of the specified player.
     *
     * @param player player name
     * @return suffix or null if not set
     */
    String getPlayerSuffix(String player);

    /**
     * Sets the prefix of the specified player.
     *
     * @param player player UUID
     * @param prefix prefix to set
     */
    void setPlayerPrefix(UUID player, String prefix);

    /**
     * Sets the prefix of the specified player.
     *
     * @param player player name
     * @param prefix prefix to set
     */
    void setPlayerPrefix(String player, String prefix);


    /**
     * Sets the suffix of the specified player.
     *
     * @param player player UUID
     * @param suffix suffix to set
     */
    void setPlayerSuffix(UUID player, String suffix);

    /**
     * Sets the suffix of the specified player.
     *
     * @param player player name
     * @param suffix suffix to set
     */
    void setPlayerSuffix(String player, String suffix);

    /**
     * Gets the prefix of the specified group.
     *
     * @param group group name
     * @return prefix or null if not set
     */
    String getGroupPrefix(String group);

    /**
     * Gets the suffix of the specified group.
     *
     * @param group group name
     * @return suffix or null if not set
     */
    String getGroupSuffix(String group);

    /**
     * Sets the prefix of the specified group.
     *
     * @param group group name
     * @param prefix prefix to set
     */
    void setGroupPrefix(String group, String prefix);

    /**
     * Sets the suffix of the specified group.
     *
     * @param group group name
     * @param suffix suffix to set
     */
    void setGroupSuffix(String group, String suffix);

    /**
     * Gets a custom meta value for the specified player.
     *
     * @param player player UUID
     * @param key meta key
     * @return meta value or null
     */
    String getPlayerMeta(UUID player, String key);

    /**
     * Gets a custom meta value for the specified player.
     *
     * @param player player name
     * @param key meta key
     * @return meta value or null
     */
    String getPlayerMeta(String player, String key);

    /**
     * Gets a custom meta value for the specified group.
     *
     * @param group group name
     * @param key meta key
     * @return meta value or null
     */
    String getGroupMeta(String group, String key);
}