package IO;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;
    private byte[] current_maze;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    public void write(int b) throws IOException {
    }

    public void write(byte[] b) throws IOException {
        ArrayList<Byte> finish_byte = new ArrayList<>();

        for(int i=0; i<12; ++i){
            finish_byte.add(b[i]);

        }

        int divide_eight = 8 - (b.length - 12) % 8;
        if (divide_eight == 8) {
            divide_eight = 0;
        }

        finish_byte.add((byte)divide_eight);
        String mazeStr = "";

        byte[] justTheMaze = new byte[b.length-12];

        for(int i = 0; i < divide_eight; ++i) {
            mazeStr = mazeStr + '0';
        }
        int counter = 0;
        for(int i=12; i<b.length;i++)
        {
            justTheMaze[counter] = b[i];
            counter++;
        }

        mazeStr = mazeStr+Arrays.toString(justTheMaze);
        mazeStr = mazeStr.replace("]", "");
        mazeStr = mazeStr.replace("[", "");
        mazeStr = mazeStr.replace(" ", "");
        mazeStr = mazeStr.replace(",", "");

        for(int i = 0; i != mazeStr.length(); i += 8) {
            finish_byte.add((byte)Integer.parseInt(mazeStr.substring(i, i + 8), 2));
        }


        byte[] byte_array = new byte[finish_byte.size()];

        for(int i = 0; i < finish_byte.size(); ++i){
            byte_array[i] = (Byte)finish_byte.get(i);
        }


        if (this.out instanceof ObjectOutputStream) {
            try {
                ((ObjectOutputStream)this.out).writeObject(byte_array);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.out.write(byte_array);
        }

        this.out.flush();
    }

}


