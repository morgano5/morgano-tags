package au.id.villar.mp3Sorter.id3.frames;

public class TCOM extends TextFrame {

	TCOM(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Composer";
	}
}
