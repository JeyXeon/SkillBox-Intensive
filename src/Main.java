import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import java.io.FileInputStream;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        String ACCESS_TOKEN = "z7Dohinj3mAAAAAAAAAAEtIhd7iWnHPuWsINsp-hwZG1KBriZitNmEJObcRoZyqP";
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        JavaSoundRecorder recorder = new JavaSoundRecorder();
        String fileName = "file.wav";
        recorder.recordSound(10000, fileName);
        try {
            Thread.sleep(15000);
            InputStream in = new FileInputStream(fileName);
            client.files().uploadBuilder("/new" + fileName).uploadAndFinish(in);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
}