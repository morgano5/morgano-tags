package au.id.villar.mp3Sorter.id3.frames;

public class TSIZ extends TextFrame {

	TSIZ(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Size";
	}
}
