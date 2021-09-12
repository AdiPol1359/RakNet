package me.adipol.raknet.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ServerName {

    private final String motd;
    private final String protocol;
    private final String version;
    private final String onlinePlayers;
    private final String maxPlayers;
    private final String port;

    @Override
    public String toString() {
        return String.join(";", new String[] { "MCPE", motd, protocol, version, onlinePlayers, maxPlayers, "13253860892328930865", "Bedrock Level", "Survival", "1", port, "19133" }) + ";";
    }
}