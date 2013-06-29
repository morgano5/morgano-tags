package au.id.villar.mp3Sorter.id3.frames;

public class TOWN extends TextFrame {

	TOWN(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "File owner/licensee";
	}
}
