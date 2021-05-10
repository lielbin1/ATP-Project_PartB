package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class SimpleDecompressorInputStream  extends InputStream {
    private InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    public int read() throws IOException {
        return 0;
    }

    public int read(byte[] b) throws IOException {
        ArrayList<Byte> byte_list = new ArrayList();

        for(int i = 0; i < 12; ++i) {
            byte_list.add((byte)this.in.read());
        }


        String read = "";
        int counter = 0;
        String oneOrZero = "0";
       // int current_read = this.in.read();


        int ind =12;
        while(ind != b.length) {
            int current_read = this.in.read();
            read = this.convertToString(oneOrZero,current_read);
            if (oneOrZero.equals("0")){
                oneOrZero="1";
            }else{
                oneOrZero="0";
            }

            for(int i = 0; i < read.length(); ++i) {
                byte_list.add((byte)Character.getNumericValue(read.charAt(i)));
                ind++;
            }

        }
        int i = 0;

        for(Iterator var8 = byte_list.iterator(); var8.hasNext(); ++i) {
            Byte byte_to_enter = (Byte)var8.next();
            b[i] = byte_to_enter;
        }


//        int ind=(int)in.read();
//        while (ind!= -1) {
//            while (ind != 0) {
//                read_str = read_str + oneOrZero;
//                ind--;
//            }
//            if (oneOrZero.equals("0")) {
//                oneOrZero = "1";
//            } else {
//                oneOrZero = "0";
//            }
//        }
//        ind = 12;
//        while (ind != b.length) {
//            for (int i = 0; i < read_str.length(); ++i) {
//                b[ind] = ((byte) Character.getNumericValue(read_str.charAt(i)));
//                ind++;
//            }
//
//        }

        return b.length;
    }


    private String convertToString(String oneOrZero ,int number) {
        String str = "";
        while (number !=0){
            str+=oneOrZero;
            number--;
        }
       // String str = String.valueOf(number);


        return str;
    }
}
