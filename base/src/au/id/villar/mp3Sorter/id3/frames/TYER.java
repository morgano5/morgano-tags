package au.id.villar.mp3Sorter.id3.frames;

public class TYER extends TextFrame {

	TYER(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Year";
	}
}
