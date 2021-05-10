package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in ;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    public int read() throws IOException {
        return 0;
    }


    public int read(byte[] b) throws IOException {

        for(int i = 0; i < 12; ++i) {

            b[i]=((byte)this.in.read());

        }

        int numOfZero = this.in.read();
        String read = "";
        read = this.convertToBinary(this.in.read());
        int add_zeroes = 8 - read.length();

        int i;
        for(i = 0; i < add_zeroes; ++i) {
            read = "0" + read;
        }
    int ind=12;
        for(i = numOfZero; i < read.length(); ++i) {
            b[ind]=((byte)Character.getNumericValue(read.charAt(i)));
            ind++;
        }

        while(ind != b.length) {
            byte current_read = (byte)this.in.read();
            read = this.convertToBinary(current_read);
            add_zeroes = 8 - read.length();

            for(i = 0; i < add_zeroes; ++i) {
                read = "0" + read;
            }

            for(i = 0; i < read.length(); ++i) {
                b[ind]=((byte)Character.getNumericValue(read.charAt(i)));
                ind++;
            }

        }

        return b.length;
    }

    private String convertToBinary(int number) {
        number &= 255;

        String str;
        for(str = ""; number > 0; number /= 2) {
            str = (number % 2 == 0 ? '0' : '1') + str;
        }

        return str;
    }




}




