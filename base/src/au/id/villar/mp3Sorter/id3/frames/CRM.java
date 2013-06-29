package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class CRM extends Frame {
	private String ownerId;
	private String explanation;
	private byte[] encryptedDatablock;

	public CRM(FrameHeader header) {
		super(header);
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public byte[] getEncryptedDatablock() {
		return encryptedDatablock;
	}

	public void setEncryptedDatablock(byte[] encryptedDatablock) {
		this.encryptedDatablock = encryptedDatablock;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		ownerId = reader.readString(false);
		explanation = reader.readString(false);
		encryptedDatablock = reader.readRestOfFrame();
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"")
				.append(", owner ID: ").append(ownerId).append(", explanation: ").append(explanation).toString();
	}

	@Override
	String getFrameDescription() {
		return "Encrypted meta frame";
	}
}
