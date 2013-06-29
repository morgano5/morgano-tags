package au.id.villar.mp3Sorter.id3.frames;

public class TPE3 extends TextFrame {

	TPE3(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Conductor/performer refinement";
	}
}
