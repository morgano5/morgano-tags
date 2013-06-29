package au.id.villar.mp3Sorter.id3.frames;

public class TIT1 extends TextFrame {

	TIT1(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Content group description";
	}
}
