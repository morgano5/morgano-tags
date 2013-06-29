package au.id.villar.mp3Sorter.id3.frames;

public class TRSO extends TextFrame {

	TRSO(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Internet radio station owner";
	}
}
