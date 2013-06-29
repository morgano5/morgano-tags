package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;
import java.math.BigInteger;

public class POPM extends Frame {

	private String email;
	private int rating;
	private BigInteger counter;

	public POPM(FrameHeader header) {
		super(header);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public BigInteger getCounter() {
		return counter;
	}

	public void setCounter(BigInteger counter) {
		this.counter = counter;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		email = reader.readString(false);
		rating = reader.readBodyByte();
		counter = new BigInteger(reader.readRestOfFrame());
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", email: ").append(email).append(", rating: ")
				.append(rating).append(", counter: ").append(counter).toString();
	}

	@Override
	public String getFrameDescription() {
		return "Popularimeter";
	}
}
