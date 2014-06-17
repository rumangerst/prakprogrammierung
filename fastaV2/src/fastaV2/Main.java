package fastaV2;

import java.io.File;
import java.io.IOException;

public class Main
{

	public static void main(String[] args)
	{
		Fasta fasta1 = new Fasta(">ENA|M34482|M34482.1 Human cytokeratin 8 (CK8) gene, complete cds.","TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTAACGGATCTCGCTCTTTTTTTTCTTTGGAGATGGAATCTCGCTCTGTCGCCCAGGCTGGAGTGCAGTGGCAAGTCTCAGCTCACTGCAACTCTGCCTCCCGGGTTCAAGTGATTCTCCTGCCTCAGCCTCCTGAGTAGCTGGGATTACACCATGGCCAGCTAATTTTTGTATTTTTAGTAGAGATGGGGTTTCACCATGTTGGTCAGGCTTGTCTTGAACTCCTGACCTCGTGATCCGCCTACCTCAGCCTCCCAAAGTGCTGGGATTACAGGCGTGCACAGCGTGCCCTGGCCTTGGATCTCTTTTTATCTTGCACCTTCAGATGTAGAGGGACGACAGCCACTGTGTGTGTATGTGTATGTGTGTGTGTGTGTGTGTGTGTGCGCGTGTGATGTTTATTCACTCATTTATTTATTCATTCATTCATTCCACAAATATCTACCCAGACCCTCTTGGCACTGCACCAGGTCGTAGGGGTAGAACAGTAACCTGGAAAGATGAGGCAAATGGTTGATTTCAGATTCAAGGCTTTGGACTCCAGCTGTTCTGTCATCCAGCTCAGGCAGGCCCTCATAATCGCTTCAATCAGGGAGAACACAGGAGAGTTTCTCTGGGGTGTCGGCAGCTCAGAGGAGACCCAAATACTAGGAGACCCCTTTTCCCATGCTTCCCAGTCCTCCAGTTTATTTCCCCCAGGAAGGAGGGAGACAAGACCCAGAGTCAGGGTTGTAGTGGCTGGGCGGCCCAGGCAAGTCTGCTTGTTACACGACTTGTGCCAGGACAGGATTTCTTCCAGTTTCATATTCACTGAACTGCCTTTTCCTGGGTTTCTGGGGGTGGTGCTGGAGTGGGCTCCAGGGTTGGAACGGGCCCTTGCGACGCGTCTCTGCTGCCCCCACCTGAGTCTGCCCCGAGGTGGCAGGTGACGGGTTCACGCGACGCCTCTGGCCTAGCCACTCAGGTACGAGGCCTTTCCCCCACTCCCCGGGGCTGGGATCTCTTTTATAAAAGGCCATTCCTGAGAGCTCTCCTCACCAAGAAGCAGCTTCTCCGCTCCTTCTAGGATCTCCGCCTGGTTCGGCCCGCCTGCCTCCACTCCTGCCTCTACCATGTCCATCAGGGTGACCCAGAAGTCCTACAAGGTGTCCACCTCTGGCCCCCGGGCCTTCAGCAGCCGCTCCTACACGAGTGGGCCCGGTTCCCGCATCAGCTCCTCGAGCTTCTCCCGAGTGGGCAGCAGCAACTTTCGCGGTGGCCTGGGCGGCGGCTATGGTGGGGCCAGCGGCATGGGAGGCATCACCGCAGTTACGGTCAACCAGAGCCTGCTGAGCCCCCTTGTCCTGGAGGTGGACCCCAACATCCAGGCCGTGCGCACCCAGGAGAAGGAGCAGATCAAGACCCTCAACAACAAGTTTGCCTCCTTCATAGACAAGGTGAGGGTCCCCTGCGTGGCTGACTGTGCCCCGCAGCCCCTTTCTCCTGGTAGTCCCGGTCCCTATGCACATCTCCAGCCCCCAGCTGGCGTCCTGCTGGGCCTCACCCGCCCTGGGCACACTCTCCCTTCCATCCTCCGACCTCACCCCTCCCGTGCACCTTGGTTTGGGCTGGGTGAGGGTGGGGAGAGGGTCTGGACAGCCGGGATGAATCCTGGGGCTTCCTTCTTCCCTTTTAAACTGGAGGGTCTTGGAAGAGAGAGACAACTTAAGGGTACAGCCTAGTTCCCACCACCCCTCTCTACAAATCCCGTTCTTCCTCAGGTCATTCTGTCCCAAATTATAAAAAATAATAGCGGTTATTGTTCTCACCCCAACCCAGTTCTGACCGTCTTTTAACGTATGCCTGCGGCAGTCCCAGCTGTTCGGGACTACCCTCCTCCAGGTTCGCCTCTTCGCCAGCACTACCCAAGGCTCCCCAGTGGTGCCTTTGTGATTTTTTTTCTTTCTTTTTTTTACATAGGGGTTTGGTGTGATTCTAGCATTCTAGGAGAAGGAAGTGGGTGTCTCGGTTCAAACGGGCAAATATTGATTGAGGCCTTTG");
		Fasta fasta2 = new Fasta();
		
		//Save fasta1
		try
		{
			fasta1.write(new File("fasta1.FASTA"));
		}
		catch (IOException e)
		{
			System.out.println("Fehler: " + e.getMessage());
		}
		
		//Load fasta1 to fasta2
		try
		{
			fasta2.read(new File("fasta1.FASTA"));
		}
		catch (IllegalHeaderException | IllegalSequenceException | IOException e)
		{
			System.out.println("Fehler: " + e.getMessage());
		}
		
		fasta1.print();
		fasta2.print();
	}

}
