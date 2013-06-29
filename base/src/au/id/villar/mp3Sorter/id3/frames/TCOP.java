package au.id.villar.mp3Sorter.id3.frames;

public class TCOP extends TextFrame {

	TCOP(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Copyright message";
	}
}
