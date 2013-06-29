package au.id.villar.mp3Sorter.id3.frames;

public class TDAT extends TextFrame {

	TDAT(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Date";
	}
}
