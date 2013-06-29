package au.id.villar.mp3Sorter.id3.frames;

public class TPE4 extends TextFrame {

	TPE4(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Interpreted, remixed, or otherwise modified by";
	}
}
