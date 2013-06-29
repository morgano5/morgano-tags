package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;
import java.math.BigInteger;

public class RVAD extends Frame {

	private RelativeVolumeAdjustment right;
	private RelativeVolumeAdjustment left;
	private RelativeVolumeAdjustment rightBack;
	private RelativeVolumeAdjustment leftBack;
	private RelativeVolumeAdjustment center;
	private RelativeVolumeAdjustment bass;

	public RVAD(FrameHeader header) {
		super(header);
	}

	public RelativeVolumeAdjustment getRight() {
		return right;
	}

	public void setRight(RelativeVolumeAdjustment right) {
		this.right = right;
	}

	public RelativeVolumeAdjustment getLeft() {
		return left;
	}

	public void setLeft(RelativeVolumeAdjustment left) {
		this.left = left;
	}

	public RelativeVolumeAdjustment getRightBack() {
		return rightBack;
	}

	public void setRightBack(RelativeVolumeAdjustment rightBack) {
		this.rightBack = rightBack;
	}

	public RelativeVolumeAdjustment getLeftBack() {
		return leftBack;
	}

	public void setLeftBack(RelativeVolumeAdjustment leftBack) {
		this.leftBack = leftBack;
	}

	public RelativeVolumeAdjustment getCenter() {
		return center;
	}

	public void setCenter(RelativeVolumeAdjustment center) {
		this.center = center;
	}

	public RelativeVolumeAdjustment getBass() {
		return bass;
	}

	public void setBass(RelativeVolumeAdjustment bass) {
		this.bass = bass;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		int flags = reader.readBodyByte();
		int numBytes = reader.readBodyByte();
		numBytes = numBytes / 8 + (numBytes % 8 != 0? 1: 0);
		BigInteger changeRight;
		BigInteger changeLeft;
		BigInteger peakRight;
		BigInteger peakLeft;
		boolean end;

		changeRight = reader.readBigInteger(numBytes, ((flags & 0b000001) == 0));
		changeLeft = reader.readBigInteger(numBytes, ((flags & 0b000010) == 0));
		end = (reader.getFrameCounter() == 0);
		peakRight = end? BigInteger.ZERO: reader.readBigInteger(numBytes, ((flags & 0b000001) == 0));
		peakLeft = end? BigInteger.ZERO: reader.readBigInteger(numBytes, ((flags & 0b000010) == 0));
		right = new RelativeVolumeAdjustment(changeRight, peakRight);
		left = new RelativeVolumeAdjustment(changeLeft, peakLeft);
		if((reader.getFrameCounter() == 0))
			return;
		changeRight = reader.readBigInteger(numBytes, ((flags & 0b000100) == 0));
		changeLeft = reader.readBigInteger(numBytes, ((flags & 0b001000) == 0));
		end = (reader.getFrameCounter() == 0);
		peakRight = end? BigInteger.ZERO: reader.readBigInteger(numBytes, ((flags & 0b000100) == 0));
		peakLeft = end? BigInteger.ZERO: reader.readBigInteger(numBytes, ((flags & 0b001000) == 0));
		rightBack = new RelativeVolumeAdjustment(changeRight, peakRight);
		leftBack = new RelativeVolumeAdjustment(changeLeft, peakLeft);
		if((reader.getFrameCounter() == 0))
			return;
		changeRight = reader.readBigInteger(numBytes, ((flags & 0b010000) == 0));
		end = (reader.getFrameCounter() == 0);
		peakRight = end? BigInteger.ZERO: reader.readBigInteger(numBytes, ((flags & 0b010000) == 0));
		center = new RelativeVolumeAdjustment(changeRight, peakRight);
		if((reader.getFrameCounter() == 0))
			return;
		changeLeft = reader.readBigInteger(numBytes, ((flags & 0b100000) == 0));
		end = (reader.getFrameCounter() == 0);
		peakLeft = end? BigInteger.ZERO: reader.readBigInteger(numBytes, ((flags & 0b100000) == 0));
		bass = new RelativeVolumeAdjustment(changeLeft, peakLeft);
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", right: ").append(right)
				.append(", left: ").append(left)
				.append(", right back: ").append(rightBack)
				.append(", left back: ").append(leftBack)
				.append(", center: ").append(center)
				.append(", bass: ").append(bass)
				.toString();
	}

	public static class RelativeVolumeAdjustment {
		private BigInteger change;
		private BigInteger peak;

		public RelativeVolumeAdjustment(BigInteger change, BigInteger peak) {
			this.change = change;
			this.peak = peak;
		}

		public BigInteger getChange() { return change; }
		public BigInteger getPeak() { return peak; }

		@Override
		public String toString() {
			return new StringBuilder().append("change: ").append(change).append(", peak: ").append(peak).toString();
		}
	}

	@Override
	public String getFrameDescription() {
		return "Relative volume adjustment";
	}
}
