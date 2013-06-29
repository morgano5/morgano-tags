package au.id.villar.mp3Sorter.id3.frames;

public class TPOS extends TextFrame {

	TPOS(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Part of a set";
	}
}
