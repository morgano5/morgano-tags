package au.id.villar.mp3Sorter.id3.frames;

public class TOLY extends TextFrame {

	TOLY(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Original lyricist(s)/text writer(s)";
	}
}
