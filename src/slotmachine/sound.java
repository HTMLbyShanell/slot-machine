
package slotmachine;
import java.awt.Toolkit;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author Shanell Spann
 */
public class sound {
    public float SAMPLE_RATE = 8000f;
    
    public void playIntro() throws LineUnavailableException
    {
        tone(261,100);
        tone(732,100);
        tone(523,100);
       
    }
    
    public void playLose() throws LineUnavailableException
    {
        tone(223,100);
        tone(432,200);
        tone(223,100);
       
    }
    
    public void playWin() throws LineUnavailableException
    {

        tone(1000,100);
        tone(1000,100);
        tone(1000,100);
    }
    
    

  public void tone(int hz, int msecs) 
     throws LineUnavailableException 
  {
     tone(hz, msecs, 1.0);
  }

  public void tone(int hz, int msecs, double vol)
      throws LineUnavailableException 
  {
    byte[] buf = new byte[1];
    AudioFormat af = 
        new AudioFormat(
            SAMPLE_RATE, // sampleRate
            8,           // sampleSizeInBits
            1,           // channels
            true,        // signed
            false);      // bigEndian
    SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
    sdl.open(af);
    sdl.start();
    
    for (int i=0; i < msecs*8; i++) {
      double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
      buf[0] = (byte)(Math.sin(angle) * 127.0 * vol);
      sdl.write(buf,0,1);
    }
    sdl.drain();
    sdl.stop();
    sdl.close();
  }  
    
}
