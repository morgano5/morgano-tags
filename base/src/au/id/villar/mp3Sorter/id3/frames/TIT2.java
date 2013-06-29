package au.id.villar.mp3Sorter.id3.frames;

public class TIT2 extends TextFrame {

	TIT2(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Title/songname/content description";
	}
}
