package au.id.villar.mp3Sorter.id3.frames;

public class TFLT extends TextFrame {

	TFLT(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "File type";
	}
}
