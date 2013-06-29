package au.id.villar.mp3Sorter.id3.frames;

public class TRSN extends TextFrame {

	TRSN(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Internet radio station name";
	}
}
