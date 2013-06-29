package au.id.villar.mp3Sorter.id3.frames;

public class TLEN extends TextFrame {

	TLEN(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Length";
	}
}
