package au.id.villar.mp3Sorter.id3.frames;

public class TPE2 extends TextFrame {

	TPE2(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Band/orchestra/accompaniment";
	}
}
