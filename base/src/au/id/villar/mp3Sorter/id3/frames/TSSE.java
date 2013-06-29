package au.id.villar.mp3Sorter.id3.frames;

public class TSSE extends TextFrame {

	TSSE(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Software/Hardware and settings used for encoding";
	}
}
