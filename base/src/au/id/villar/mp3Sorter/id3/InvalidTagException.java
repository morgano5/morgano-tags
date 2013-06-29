package au.id.villar.mp3Sorter.id3;

public class InvalidTagException extends Exception {

	private static final long serialVersionUID = -3345102115123526049L;

	public InvalidTagException() {
	}

	public InvalidTagException(String message) {
		super(message);
	}
}
