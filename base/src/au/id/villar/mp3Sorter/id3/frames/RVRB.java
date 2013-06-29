package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class RVRB extends Frame {

	private int reberbLeft;
	private int reberbRight;
	private int reberbBounceLeft;
	private int reberbBounceRight;
	private int feedbackLtoL;
	private int feedbackLtoR;
	private int feedbackRtoR;
	private int feedbackRtoL;
	private int premixLtoR;
	private int premixRtoL;

	public RVRB(FrameHeader header) {
		super(header);
	}

	public int getReberbLeft() {
		return reberbLeft;
	}

	public void setReberbLeft(int reberbLeft) {
		this.reberbLeft = reberbLeft;
	}

	public int getReberbRight() {
		return reberbRight;
	}

	public void setReberbRight(int reberbRight) {
		this.reberbRight = reberbRight;
	}

	public int getReberbBounceLeft() {
		return reberbBounceLeft;
	}

	public void setReberbBounceLeft(int reberbBounceLeft) {
		this.reberbBounceLeft = reberbBounceLeft;
	}

	public int getReberbBounceRight() {
		return reberbBounceRight;
	}

	public void setReberbBounceRight(int reberbBounceRight) {
		this.reberbBounceRight = reberbBounceRight;
	}

	public int getFeedbackLtoL() {
		return feedbackLtoL;
	}

	public void setFeedbackLtoL(int feedbackLtoL) {
		this.feedbackLtoL = feedbackLtoL;
	}

	public int getFeedbackLtoR() {
		return feedbackLtoR;
	}

	public void setFeedbackLtoR(int feedbackLtoR) {
		this.feedbackLtoR = feedbackLtoR;
	}

	public int getFeedbackRtoR() {
		return feedbackRtoR;
	}

	public void setFeedbackRtoR(int feedbackRtoR) {
		this.feedbackRtoR = feedbackRtoR;
	}

	public int getFeedbackRtoL() {
		return feedbackRtoL;
	}

	public void setFeedbackRtoL(int feedbackRtoL) {
		this.feedbackRtoL = feedbackRtoL;
	}

	public int getPremixLtoR() {
		return premixLtoR;
	}

	public void setPremixLtoR(int premixLtoR) {
		this.premixLtoR = premixLtoR;
	}

	public int getPremixRtoL() {
		return premixRtoL;
	}

	public void setPremixRtoL(int premixRtoL) {
		this.premixRtoL = premixRtoL;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		reberbLeft = reader.read2Bytes();
		reberbRight = reader.read2Bytes();
		reberbBounceLeft = reader.readBodyByte();
		reberbBounceRight = reader.readBodyByte();
		feedbackLtoL = reader.readBodyByte();
		feedbackLtoR = reader.readBodyByte();
		feedbackRtoR = reader.readBodyByte();
		feedbackRtoL = reader.readBodyByte();
		premixLtoR = reader.readBodyByte();
		premixRtoL = reader.readBodyByte();
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"")
				.append(", reberbLeft: ").append(reberbLeft)
				.append(", reberbRight: ").append(reberbRight)
				.append(", reberbBounceLeft: ").append(reberbBounceLeft)
				.append(", reberbBounceRight: ").append(reberbBounceRight)
				.append(", feedbackLtoL: ").append(feedbackLtoL)
				.append(", feedbackLtoR: ").append(feedbackLtoR)
				.append(", feedbackRtoR: ").append(feedbackRtoR)
				.append(", feedbackRtoL: ").append(feedbackRtoL)
				.append(", premixLtoR: ").append(premixLtoR)
				.append(", premixRtoL: ").append(premixRtoL)
				.toString();
	}

	@Override
	public String getFrameDescription() {
		return "Reverb";
	}
}
