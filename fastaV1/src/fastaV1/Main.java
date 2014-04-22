package fastaV1;

public class Main
{

	public static void main(String[] args)
	{
		Fasta fasta1 = new Fasta(">Sequenz 1", "CCATGCTGCCACAGGGCTTCATGTTGATGCCGGTAGTCTGTGCTCCATCT"); //alternative: CCATGCTGCCACAGGGCTTCATGTTGATGCCGGTAGTCTGTGCTCCATCT
		Fasta fasta2 = new Fasta(">Homo sapiens (human) pyruvate: carbon-dioxide ligase", "ATGCTGAAGTTCCGAACAGTCCATGGGGGCCTGAGGCTCCTGGGAATCCGCCGAACCTCC");
		
		fasta1.print();
		fasta2.print();
		
		//dotplot (self)
		fasta1.dotPlotAgainst(fasta1);
		
		//dotplot (both)
		fasta1.dotPlotAgainst(fasta2);
		
		

	}

}
