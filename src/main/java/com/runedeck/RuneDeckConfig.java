package com.runedeck;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("runedeck")
public interface RuneDeckConfig extends Config {

    @ConfigItem(keyName = "socketPort", name = "Socket Port", description = "The port to use to communicate with the stream deck")
    default int getSocketPort() {
        return 42069;
    }
}
