package au.id.villar.mp3Sorter.id3.frames;

public class TIT3 extends TextFrame {

	TIT3(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Subtitle/Description refinement";
	}
}
