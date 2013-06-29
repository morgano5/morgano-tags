package au.id.villar.mp3Sorter.id3.frames;

public class TORY extends TextFrame {

	TORY(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Original release year";
	}
}
