package au.id.villar.mp3Sorter.id3.frames;

public class TPUB extends TextFrame {

	TPUB(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Publisher";
	}
}
