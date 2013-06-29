package au.id.villar.mp3Sorter.id3.frames;

public class TBPM extends TextFrame {

	TBPM(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "BPM (beats per minute)";
	}
}
