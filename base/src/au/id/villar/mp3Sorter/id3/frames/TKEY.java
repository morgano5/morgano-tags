package au.id.villar.mp3Sorter.id3.frames;

public class TKEY extends TextFrame {

	TKEY(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Initial key";
	}
}
