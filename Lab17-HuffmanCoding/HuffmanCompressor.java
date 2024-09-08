import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class HuffmanCompressor {
    public static int[] getFrequency(String file)
    {
        int[] freq = new int[HuffmanTree.EOF];
        try {
            BufferedReader scanner = new BufferedReader(new FileReader(file));
            while (scanner.ready())
            {
                freq[scanner.read()]++;
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return freq;
    }
    int[] countFrequencies(String file)
    {
        int[] countRepeats = new int[256];
        return countRepeats;
    }
    
    static void compress(String file, String newFile, String shortFile)
    {
        HuffmanTree huffman = new HuffmanTree(getFrequency(file));
        huffman.write(newFile);
        huffman.encode(new BitOutputStream(shortFile), file);
    }
    
    static void expand(String codeFile, String newFile, String shortFile)
    {
        new HuffmanTree(codeFile).decode(new BitInputStream(shortFile), newFile);
    }
}