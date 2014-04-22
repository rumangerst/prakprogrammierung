import java.security.InvalidParameterException;
import java.util.regex.*;

public class Fasta
{
	private String header;
	private String dna_sequence;

	public Fasta()
	{
		resetDNASequence();
		resetHeader();
	}

	/**
	 * Creates a FASTA managed DNA sequence
	 * 
	 * @param header
	 *            Must begin with '>'
	 * @param dna_sequence
	 *            Only ACTG
	 */
	public Fasta(String header, String dna_sequence)
			throws InvalidParameterException
	{
		this.header = header;
		this.dna_sequence = dna_sequence;

		// Check the data!

		if (!dataIsValid())
			throw new InvalidParameterException(
					"Invalid header or invalid DNA string!");
	}

	/**
	 * FASTA copy constructor
	 * 
	 * @param tocopy
	 */
	public Fasta(Fasta tocopy)
	{
		// No need to check here, data is already valid!
		this.header = tocopy.header;
		this.dna_sequence = tocopy.dna_sequence;
	}

	/**
	 * Returns header of the FASTA data, managed by this class
	 * 
	 * @return
	 */
	public String getHeader()
	{
		return this.header;
	}

	/**
	 * Returns DNA sequence
	 * 
	 * @return
	 */
	public String getDNASequence()
	{
		return this.dna_sequence;
	}

	/**
	 * Returns the length of the DNA sequence, managed by this class
	 * 
	 * @return
	 */
	public int getDNASequenceLength()
	{
		return this.dna_sequence.length();
	}

	/**
	 * Resets header to a valid default value
	 */
	public void resetHeader()
	{
		this.header = ">";
	}

	/**
	 * Resets DNA sequence to a valid default value
	 */
	public void resetDNASequence()
	{
		this.dna_sequence = "";
	}

	/**
	 * Return if the header and DNA sequence are valid
	 * 
	 * @return
	 */
	private boolean dataIsValid()
	{
		// /////Check header

		// Header should be at least '>'
		if (header.length() == 0)
			return false;

		if (header.charAt(0) != '>')
			return false;

		// Check header for newline:
		for (int i = 0; i < header.length(); i++)
		{
			char c = header.charAt(i);

			if (c == '\n' || c == '\r')
				return false;
		}

		///////Check dna sequence (without REGEX because REGEX is slow and beheaves strange!)
		
		for (int i = 0; i < dna_sequence.length(); i++)
		{
			char c = dna_sequence.charAt(i);

			if (c != 'A' && c != 'C' && c != 'T' && c != 'G')
				return false;
		}
		
		return true;
	}

	/**
	 * Draws a DOTPLOT of this sequence against the other sequence
	 * 
	 * @param other
	 */
	public void dotPlotAgainst(Fasta other)
	{

	}
}
