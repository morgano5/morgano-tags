package au.id.villar.mp3Sorter.id3.frames;

public class TMED extends TextFrame {

	TMED(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Media type";
	}
}
