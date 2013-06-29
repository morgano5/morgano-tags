package au.id.villar.mp3Sorter.id3.frames;

public class TOPE extends TextFrame {

	TOPE(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Original artist(s)/performer(s)";
	}
}
