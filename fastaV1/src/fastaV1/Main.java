package fastaV1;

public class Main
{

	public static void main(String[] args)
	{
		Fasta fasta1 = new Fasta(">Sequenz 1", "CCATGCTGCCACAGGGCTTCATGTTGATGCCGGTAGTCTGTGCTCCATCT"); //alternative: CCATGCTGCCACAGGGCTTCATGTTGATGCCGGTAGTCTGTGCTCCATCT
		Fasta fasta2 = new Fasta(">Homo sapiens (human) pyruvate: carbon-dioxide ligase", "ATGCTGAAGTTCCGAACAGTCCATGGGGGCCTGAGGCTCCTGGGAATCCGCCGAACCTCC");
		
		fasta1.print();
		System.out.println();
		fasta2.print();
		
		System.out.println();
		System.out.println();
		System.out.println("-- Dotplot von einer Sequenz zu sich selbst:");
		
		//dotplot (self)
		fasta1.dotPlotAgainst(fasta1);
		
		System.out.println();		
		System.out.println("-- Dotplot beider Sequenzen:");
		
		//dotplot (both)
		fasta1.dotPlotAgainst(fasta2); 
	}

}
