package kasutajaliidesedApi;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.*;
import java.net.*;

import static java.lang.String.format;
import static kasutajaliidesedApi.utils.ConfigurationProperties.COMMAND_LISTENER_PORT;

public class ApplicationManager {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    private static final int DEFAULT_REMOTE_PORT = 9999;

    private static final String STOP_COMMAND = "stop";

    @Inject
    private static Configuration configuration;

    public static void stopApplication() throws Exception {
        logger.info(format("Stopping application on port [%s]", getRemotePort()));

        BufferedWriter writer = null;
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(InetAddress.getLoopbackAddress(), getRemotePort()), 10000);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            write(STOP_COMMAND + "\n\r", writer);
            logger.info("Stop command sent to application");
            Thread.sleep(300);
        } catch (ConnectException ce) {
            logger.info("Application is not running.");
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    private static int getRemotePort() {
        return configuration.getInt(COMMAND_LISTENER_PORT, DEFAULT_REMOTE_PORT);
    }

    public static boolean isApplicationRunning() {
        boolean isRunning = false;

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(InetAddress.getLoopbackAddress(), getRemotePort()), 10000);
            isRunning = true;
        } catch (Exception ce) {
            logger.info("Application is not running.");
        }

        return isRunning;
    }

    private static void write(String data, BufferedWriter writer) throws IOException {
        writer.write(data);
        writer.flush();
    }

    public static class CommandListener implements Runnable {

        private ServerSocket serverSocket;

        @Override
        public void run() {

            try {
                synchronized (this) {
                    serverSocket = new ServerSocket(getRemotePort(), 10, InetAddress.getLoopbackAddress());
                    serverSocket.setSoTimeout(0);
                    logger.info(format("Command listener started on port [%s]", getRemotePort()));
                }
            } catch (Exception e) {
                logger.error("Error starting command listener!", e);
            }

            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    Thread commandExecutor = new Thread(new CommandExecutor(socket));
                    commandExecutor.setName("command-executor");
                    commandExecutor.setDaemon(true);
                    commandExecutor.start();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

    private static class CommandExecutor implements Runnable {

        private static final char TILDE = '~';
        private static final char SPACE = ' ';
        private Socket socket;

        private CommandExecutor(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                StringBuilder stringBuilder = new StringBuilder();
                int data = 0;
                while ((data = reader.read()) >= 0) {
                    char character = (char) data;
                    if (character >= SPACE && character <= TILDE) {
                        stringBuilder.append(character);
                    } else if (character == '\n') {
                        executeCommand(stringBuilder.toString());
                        stringBuilder = new StringBuilder();
                    }
                }
            } catch (IOException e) {
                // ignore
            } finally {
                IOUtils.closeQuietly(socket);
            }
        }

        private void executeCommand(String command) {
            if (STOP_COMMAND.equals(command)) {
                executeStopCommand();
            }
        }

        private void executeStopCommand() {
            System.exit(0);
        }
    }
}
