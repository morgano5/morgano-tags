package au.id.villar.mp3Sorter.id3.frames;

public class TRCK extends TextFrame {

	TRCK(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Track number/Position in set";
	}
}
