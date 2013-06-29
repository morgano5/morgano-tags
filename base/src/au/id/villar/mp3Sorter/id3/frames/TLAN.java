package au.id.villar.mp3Sorter.id3.frames;

public class TLAN extends TextFrame {

	TLAN(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Language(s)";
	}
}
