package au.id.villar.mp3Sorter.id3.frames;

public class TCON extends TextFrame {

	TCON(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Content type";
	}
}
