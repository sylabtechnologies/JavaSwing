/**
 * p342 first sound player
 * https://github.com/bethrobson/Head-First-Java
 * 
 * 1. use classic midi messages, n/p
 * * is player sequence track - the model maybe actual device
 * 
 * 2. send messages to the player
 * 
 * = good design for orhestra simulation
 * 
 */
package midiplay;
import javax.sound.midi.*;

public class MidiPlay
{

    public static void main(String[] args)
    {
        MidiPlay mini = new MidiPlay();
        mini.play();
    }
    
   public void play() {

      try {

         // make (and open) a sequencer, make a sequence and track

         Sequencer sequencer = MidiSystem.getSequencer();         
         sequencer.open();
        
         Sequence seq = new Sequence(Sequence.PPQ, 4);
         Track track = seq.createTrack();     

         // now make two midi events (containing a midi message)
         MidiEvent event = null;O
         
         // first make the message
         // then stick the message into a midi event 
         // and add the event to the track
         
         /* change instrument
            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, 40, 0);
            MidiEvent changeInstrument = new MidiEvent(first, 1);
            track.add(changeInstrument);         
         */
         

          ShortMessage a = new ShortMessage();
          a.setMessage(144, 1, 44, 100);
          MidiEvent noteOn = new MidiEvent(a, 1); // <-- means at tick one, the above event happens
          track.add(noteOn);

          ShortMessage b = new ShortMessage();
          b.setMessage(128, 1, 44, 100);
          MidiEvent noteOff = new MidiEvent(b, 16); // <-- means at tick one, the above event happens
          track.add(noteOff);
        
         // add the events to the track
            
          // add the sequence to the sequencer, set timing, and start
          sequencer.setSequence(seq);
         
          sequencer.start();
          // new
          Thread.sleep(1000);
          sequencer.close();
          System.exit(0);
      } catch (Exception ex) {ex.printStackTrace();}
    }    
    
}
