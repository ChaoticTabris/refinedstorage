package refinedstorage.api.network;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Represents a node in the storage network.
 */
public interface INetworkNode {
    /**
     * Called every tile entity tick.
     */
    void updateNode();

    /**
     * @return If this node can send a connectivity update
     */
    boolean canSendConnectivityUpdate();

    /**
     * @return The energy usage of this node
     */
    int getEnergyUsage();

    /**
     * @return The position of this node in the world
     */
    BlockPos getPosition();

    /**
     * Called when the neighbor of this node is changed. Typically used to check if there is still a connection to the network.
     *
     * @param world The world
     */
    void refreshConnection(World world);

    /**
     * Called when a connection is found to the network
     *
     * @param world   The world
     * @param network The network we're trying to connect to
     */
    void connect(World world, INetworkMaster network);

    /**
     * Called when a connection is lost to the network
     *
     * @param world The world
     */
    void disconnect(World world);

    /**
     * @return If we are connected
     */
    boolean isConnected();

    /**
     * @return If {@link INetworkNode#canUpdate()} can get called. Typically checks for connection status and redstone mode.
     */
    boolean canUpdate();

    /**
     * @return The network
     */
    INetworkMaster getNetwork();
}
