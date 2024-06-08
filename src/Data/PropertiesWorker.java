package Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesWorker {
    private final Properties appProps = new Properties();
    private final String fileName = "app.properties";
    private final String tokenParam = "jwtToken";

    public PropertiesWorker(){
        try {
            File file = new File(fileName);
            if(!file.exists()){
                file.createNewFile();
            }
       } catch (IOException e){ e.printStackTrace(); }
    }

    public void setJwtToken(String token){
        try (FileInputStream fs = new FileInputStream(fileName)){
            appProps.load(fs);
            appProps.setProperty(tokenParam, token);
            saveProperties();
        } catch(IOException e){ e.printStackTrace(); }
    }
    public String getJwtToken(){
        try (FileInputStream fs = new FileInputStream(fileName)){
            appProps.load(fs);
            return appProps.getProperty(tokenParam);
        } catch(IOException e){ e.printStackTrace(); }
        return null;
    }

    private void saveProperties(){
        try(FileOutputStream fs = new FileOutputStream(fileName)) {
            appProps.store(fs, null);
        } catch (IOException e){ e.printStackTrace(); }
    }
}
