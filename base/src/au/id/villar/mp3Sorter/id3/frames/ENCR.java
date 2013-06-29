package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class ENCR extends Frame {

	private String ownerId;
	private int methodSymbol;
	private byte[] encryptionData;

	public ENCR(FrameHeader header) {
		super(header);
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public int getMethodSymbol() {
		return methodSymbol;
	}

	public void setMethodSymbol(int methodSymbol) {
		this.methodSymbol = methodSymbol;
	}

	public byte[] getEncryptionData() {
		return encryptionData;
	}

	public void setEncryptionData(byte[] encryptionData) {
		this.encryptionData = encryptionData;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		ownerId = reader.readString(false);
		methodSymbol = reader.readBodyByte();
		if(reader.getFrameCounter() > 0)
			encryptionData = reader.readRestOfFrame();
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", ownerId: ").append(ownerId)
				.append(", methodSymbol: ").append(methodSymbol).toString();
	}

	@Override
	public String getFrameDescription() {
		return "Encryption method registration";
	}
}
