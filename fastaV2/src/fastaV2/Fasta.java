package fastaV2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
		header = ">"; // Valid empty header
		dna_sequence = ""; // empty sequence contains only A,C,T,G.
	}

	/**
	 * Creates a FASTA managed sequence with given header and sequence
	 * 
	 * @param header
	 * @param dna_sequence
	 */
	public Fasta(String header, String dna_sequence)
			throws InvalidParameterException
	{
		this.header = header;
		this.dna_sequence = dna_sequence;

		// check it
		if (!sequenceIsValid(dna_sequence))
			throw new IllegalSequenceException();
		if(!headerIsValid(header))
			throw new IllegalHeaderException();
	}

	/**
	 * Creates a copy of a FASTA managed sequence
	 * 
	 * @param source
	 */
	public Fasta(Fasta source)
	{
		// Don't check it here; Source FASTA is already valid
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
	 * Clears all data (header and DNA sequence)
	 */
	public void clearData()
	{
		header = ">";
		dna_sequence = "";
	}

	public int getDNASequenceLength()
	{
		return dna_sequence.length();
	}

	/**
	 * Create a dotplot of this FASTA and another FASTA sequence.
	 * 
	 * @param other
	 */
	public void dotPlotAgainst(Fasta other)
	{
		// Print first character
		System.out.print(" ");

		// Print first line (data of first sequence)
		for (int x = 0; x < this.getDNASequenceLength(); x++)
		{
			System.out.print(this.getDNASequence().charAt(x));
		}

		System.out.println();

		// Print data
		for (int y = 0; y < other.getDNASequenceLength(); y++)
		{

			System.out.print(other.getDNASequence().charAt(y));

			for (int x = 0; x < this.getDNASequenceLength(); x++)
			{
				char firstSequenceLetter = this.getDNASequence().charAt(x);
				char secondSequenceLetter = other.getDNASequence().charAt(y);

				if (firstSequenceLetter == secondSequenceLetter)
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

	private static boolean headerIsValid(String header)
	{
		// Header size check
		if (header.length() == 0)
			return false;
		// Header '>' check
		if (header.charAt(0) != '>')
			return false;
		// Header character check
		for (int i = 1; i < header.length(); i++)
		{
			char letter = header.charAt(i);

			if (letter == '\r' || letter == '\n')
				return false;
		}

		return true;
	}

	private static boolean sequenceIsValid(String dna_sequence)
	{

		// DNA character check
		for (int i = 1; i < dna_sequence.length(); i++)
		{
			char letter = dna_sequence.charAt(i);

			if (letter != 'A' && letter != 'C' && letter != 'T'
					&& letter != 'G')
				return false;
		}

		return true;
	}

	/**
	 * Print all data of this FASTA managed sequence
	 */
	public void print()
	{
		System.out.printf("%s\n%s (Length: %dbp)\n", this.getHeader(),
				this.getDNASequence(), this.getDNASequenceLength());
	}
	
	public void read(File file) throws IOException, IllegalHeaderException, IllegalSequenceException
	{
		//Open reader
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		//Initialize variables
		String header = "";		
		StringBuffer dna = new StringBuffer();		
		
		
		//First line: header
		header = reader.readLine();
		
		if(header == null)
		{
			reader.close();
			throw new IllegalHeaderException();
		}
		
		//Other lines: dna string		
		String dna_buffer = null;
		
		while((dna_buffer = reader.readLine()) != null)
		{
			//Apply read DNA string to buffer
			dna.append(dna_buffer);
		}
		
		//!!Close reader
		reader.close();
		
		//Test data
		if(!headerIsValid(header))
			throw new IllegalHeaderException("Header " + header + " is invalid!");
		if(!sequenceIsValid(dna.toString()))
			throw new IllegalSequenceException("Sequence " + dna.toString() + " is invalid!");
		
		//Put data in class
		this.header = header;
		this.dna_sequence = dna.toString();
		
	}
	
	public void write(File file) throws IOException
	{
		FileWriter writer = new FileWriter(file);
		
		//Write header
		writer.write(this.header + "\n");
		
		//Write dna sequence
		
		//Always read max. 80 chars
		for(int i = 0; i < dna_sequence.length(); i += 80)
		{
			if(i != 0)
				writer.write("\n");
			
			String dna_substring = this.dna_sequence.substring(i, Math.min(i + 80, this.dna_sequence.length()));
			
			writer.write(dna_substring);
		}
		
		writer.close();
	}

}
