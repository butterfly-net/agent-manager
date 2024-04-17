package it.unibz.butterfly_net.template.application;

import io.javalin.Javalin;
import it.unibz.butterfly_net.template.core.utils.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpServer implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(HttpServer.class);
    private final int PORT;

    public HttpServer() {
        try {
            String configPort = Config.getInstance().property("SERVER_PORT");
            PORT = Integer.parseInt(configPort);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        logger.info("running");
        Javalin app = Javalin.create();

        app.before(ctx -> {
            String message = String.format("%s %s", ctx.method(), ctx.path());
            logger.info(message);
        });

        // INSERT CONCRETE OPERATION

        app.start(PORT);
    }
}
