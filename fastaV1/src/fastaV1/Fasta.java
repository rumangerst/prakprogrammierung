package fastaV1;

import java.security.InvalidParameterException;

public class Fasta
{
	private String header;
	private String dna_sequence;
	
	/**
	 * Creates an empty FASTA managed sequence
	 */
	public Fasta()
	{
		header = ">";
		dna_sequence = "";
	}
	
	/**
	 * Creates a FASTA managed sequence with given header and sequence
	 * @param header
	 * @param dna_sequence
	 */
	public Fasta(String header, String dna_sequence) throws InvalidParameterException
	{
		this.header = header;
		this.dna_sequence = dna_sequence;
		
		//check it
		if(!dataIsValid())
			throw new InvalidParameterException("Invalid FASTA data!");
	}
	
	/**
	 * Creates a copy of a FASTA managed sequence
	 * @param source
	 */
	public Fasta(Fasta source)
	{
		//Don't check it here; Source FASTA is already valid
		this.header = source.header;
		this.dna_sequence = source.dna_sequence;
	}
	
	public String getHeader()
	{
		return header;
	}
	
	public String getDNASequence()
	{
		return dna_sequence;
	}
	
	/**
	 * Resets Header to '>'
	 */
	public void resetHeader()
	{
		header = ">";
	}
	
	/**
	 * Resets DNA sequence to an empty sequence
	 */
	public void resetDNASequence()
	{
		dna_sequence = "";
	}
	
	public int getDNASequenceLength()
	{
		return dna_sequence.length();
	}
	
	/**
	 * Create a dotplot of this FASTA and another FASTA sequence.
	 * @param other
	 */
	public void dotPlotAgainst(Fasta other)
	{
		//Print first character
		System.out.print(" ");
		
		//Print first line (data of first sequence)
		for(int x = 0; x < this.getDNASequenceLength(); x++)
		{
			System.out.print(this.getDNASequence().charAt(x));
		}
		
		System.out.println();
		
		//Print data
		for(int y = 0; y < other.getDNASequenceLength(); y++)
		{
			
			System.out.print(other.getDNASequence().charAt(y));
			
			for(int x = 0; x < this.getDNASequenceLength(); x++)
			{
				char firstSequenceLetter = this.getDNASequence().charAt(x);
				char secondSequenceLetter = other.getDNASequence().charAt(y);
				
				if(firstSequenceLetter == secondSequenceLetter)
				{
					System.out.print('*');
				}
				else
				{
					System.out.print(' ');
				}
			}		
			
			System.out.println();
			
		}
	}
	
	private boolean dataIsValid()
	{
		//Header size check
		if(header.length() == 0)
			return false;
		//Header '>' check
		if(header.charAt(0) != '>')
			return false;
		//Header character check
		for(int i = 1; i < header.length(); i++)
		{
			char letter = header.charAt(i);
			
			if(letter == '\r' || letter == '\n')
				return false;
		}
		
		//DNA character check
		for(int i = 1; i < dna_sequence.length(); i++)
		{
			char letter = dna_sequence.charAt(i);
			
			if(letter != 'A' && letter != 'C' && letter != 'T' && letter != 'G')
				return false;
		}
		
		return true;
	}
	
	/**
	 * Print all data of this FASTA managed sequence
	 */
	public void print()
	{
		System.out.printf("%s\n%s (Length: %dbp)\n", this.getHeader(), this.getDNASequence(), this.getDNASequenceLength());
	}
	
}
