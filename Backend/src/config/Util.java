package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author admin
 */
public class Util {

    private Properties props;
    private static Util instance;

    public static Util getInstance() throws IOException {

        if (instance == null) {
            instance = new Util();
        }

        return instance;
    }

    private Util() throws IOException {

        props = new Properties();
        props.load(new FileInputStream("Backend/src/config/.env"));
    }

    public String get(String key) {
        return props.getProperty(key);
    }
}
