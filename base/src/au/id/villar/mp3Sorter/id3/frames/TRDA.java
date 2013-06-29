package au.id.villar.mp3Sorter.id3.frames;

public class TRDA extends TextFrame {

	TRDA(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Recording dates";
	}
}
