package IO;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;
    private byte[] current_maze;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }



    public void write(int b) throws IOException {

    }

    public void write(byte[] b) throws IOException {
        ArrayList<Byte> finish_byte = new ArrayList<>();

        for(int i=0; i<12; ++i){
            finish_byte.add(b[i]);

        }

        String mazeStr = "" ;
        byte[] justTheMaze = new byte[b.length-12];

        int counter = 0;
        for(int i=12; i<b.length;i++)
        {
            justTheMaze[counter] = b[i];
            counter++;
        }


        mazeStr = Arrays.toString(justTheMaze);
        mazeStr = mazeStr.replace("]", "");
        mazeStr = mazeStr.replace("[", "");
        mazeStr = mazeStr.replace(" ", "");
        mazeStr = mazeStr.replace(",", "");

        int byteCounter = 0;
        counter=0;
        int ind = 0;  //111111000000000011111

        while (ind != mazeStr.length()){
            if(Integer.parseInt(String.valueOf(mazeStr.charAt(ind)))==byteCounter){

                counter++;
                if(ind == mazeStr.length()-1){
                    finish_byte.add((byte)counter);
                }

                ind++;
            }
            else{
                finish_byte.add((byte)counter);
                if(byteCounter==0){
                    byteCounter=1;
                }else{
                    byteCounter=0;
                }
                counter=0;
            }

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
