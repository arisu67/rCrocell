package emu.rcrocell;

import emu.rcrocell.server.GameServer;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Crocell {

    @Getter
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Crocell.class);

    public static void main(String[] args) throws Exception {
        Crocell.getLogger().info("Server Starting");
        Crocell.getLogger().info("Server Version: 0.1.0");

        new GameServer().start(6900);
    }
}
