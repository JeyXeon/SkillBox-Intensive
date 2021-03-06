import javax.sound.sampled.*;
import java.io.File;

public class JavaSoundRecorder
{
    private final AudioFileFormat.Type fileType;
    private TargetDataLine line;
    private final AudioFormat audioFormat;

    public JavaSoundRecorder()
    {
        fileType = AudioFileFormat.Type.WAVE;

        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        audioFormat = new AudioFormat(
                sampleRate, sampleSizeInBits, channels, signed, bigEndian
        );
        DataLine.Info info = new DataLine.Info(
                TargetDataLine.class, audioFormat
        );
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void recordSound(long milliseconds, String filePath)
    {
        File file = new File(filePath);
        start(file);
        delayFinish(milliseconds);
    }

    private void start(File file)
    {
        new Thread(() ->
        {
            try {
                line.open(audioFormat);
                line.start();
                AudioInputStream ais = new AudioInputStream(line);
                AudioSystem.write(ais, fileType, file);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    private void delayFinish(long delayTime)
    {
        new Thread(() ->
        {
            try
            {
                Thread.sleep(delayTime);
                line.stop();
                line.close();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}