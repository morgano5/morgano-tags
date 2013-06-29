package au.id.villar.mp3Sorter.id3.frames;

public class TSRC extends TextFrame {

	TSRC(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "ISRC (international standard recording code)";
	}
}
