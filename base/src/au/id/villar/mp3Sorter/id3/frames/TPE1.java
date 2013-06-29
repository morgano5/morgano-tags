package au.id.villar.mp3Sorter.id3.frames;

public class TPE1 extends TextFrame {

	TPE1(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Lead performer(s)/Soloist(s)";
	}
}
