package au.id.villar.mp3Sorter.id3.frames;

public class TOFN extends TextFrame {

	TOFN(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Original filename";
	}
}
