package au.id.villar.mp3Sorter.id3.frames;

public class TENC extends TextFrame {

	TENC(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Encoded by";
	}
}
