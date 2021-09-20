package reusableMethods;

import java.io.FileInputStream;
import java.util.Properties;

public class propertiesOperations {

    static Properties prop = new Properties();

    public static String getPropertyValues(String key) throws Exception {

        /**
         * load data from property file
         */
        String propertyFilePath = System.getProperty("user.dir") + "/src/test/resources/Features/config.properties";
        FileInputStream fis = new FileInputStream(propertyFilePath);
        prop.load(fis);


        /**
         *read data
         */
        String value = prop.get(key).toString();
        if (value.isEmpty()) {
            throw new Exception("Value is not specified for key: " + key + "in property file");
        }
        return value;
    }
}
